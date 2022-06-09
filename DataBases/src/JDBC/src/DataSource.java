import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/**
 * DataSource
 */
public class DataSource {

    public static final String DB_NAME = "music.db";
    public static final String DB_PATH = "jdbc:sqlite:" + DB_NAME;

    public static final String ARTISTS = "artists";
    public static final String ARTISTS_ID = "_id";
    public static final String ARTISTS_NAME = "name";
    public static final int ARTISTS_ID_INDEX = 1;
    public static final int ARTISTS_NAME_INDEX = 2;

    public static final String ALBUMS = "albums";
    public static final String ALBUMS_ID = "_id";
    public static final String ALBUMS_NAME = "name";
    public static final String ALBUMS_ARTIST = "artist";
    public static final int ALBUMS_ID_INDEX = 1;
    public static final int ALBUMS_NAME_INDEX = 2;
    public static final int ALBUMS_ARTIST_INDEX = 3;


    public static final String SONGS = "songs";
    public static final String SONGS_ID = "_id";
    public static final String SONGS_TRACK = "track";
    public static final String SONGS_TITLE = "title";
    public static final String SONGS_ALBUM = "album";
    public static final int SONGS_ID_INDEX = 1;
    public static final int SONGS_TRACK_INDEX = 2;
    public static final int SONGS_TITLE_INDEX = 3;
    public static final int SONGS_ALBUM_INDEX = 4;

    public static final String FULL_VIEW = "full_view";
    public static final String CREATE_FULL_VIEW  = "CREATE VIEW IF NOT EXISTS " + FULL_VIEW  + " AS SELECT " +
    ARTISTS + "." + ARTISTS_NAME + " AS " + ALBUMS_ARTIST + ", " + ALBUMS + "." + ALBUMS_NAME + " AS " + SONGS_ALBUM + ", "+
    SONGS +  "." + SONGS_TRACK + ", " + SONGS +  "." + SONGS_TITLE + " AS " + SONGS_TITLE 
    + " FROM " + ALBUMS + " JOIN " + ARTISTS + " ON " + ARTISTS + "." + ARTISTS_ID + " = " + ALBUMS + "." + ALBUMS_ARTIST
            + " JOIN " + SONGS + " ON " + SONGS + "." + SONGS_ALBUM + " = " + ALBUMS + "." + ALBUMS_ID 
            + " ORDER BY " + ARTISTS+"."+ARTISTS_NAME + ", " + ALBUMS+"."+ALBUMS_NAME + " COLLATE NOCASE "; 
    public static final String QUERY_ARTISTS_FOR_SONG = "SELECT " + ALBUMS_ARTIST + ", " + SONGS_ALBUM + ", " + SONGS_TRACK  
            + " FROM " + FULL_VIEW + " WHERE "  + SONGS_TITLE + " = ? ";


    public static final String INSERT_ARTIST = "INSERT INTO " + ARTISTS + " (" + ARTISTS_NAME + ") VALUES (?)" ;
    public static final String INSERT_ALBUM = "INSERT INTO " + ALBUMS + " (" + ALBUMS_NAME + ", " + ALBUMS_ARTIST+ ") VALUES (?, ?)" ;
    public static final String INSERT_SONG = "INSERT INTO " + SONGS + " (" + SONGS_TITLE + ", " + SONGS_TRACK + ", " + SONGS_ALBUM +  ") VALUES (?, ?, ?)" ;

    public static final String QUERY_ARTIST = "SELECT " + ARTISTS_ID_INDEX + " FROM " + ARTISTS + " WHERE " + ARTISTS_NAME + " = ?";
    public static final String QUERY_ALBUM = "SELECT " + ALBUMS_ID_INDEX + " FROM " + ALBUMS + " WHERE " + ALBUMS_NAME + " = ?";

    public static final int ORDER_BY_NONE = 0;
    public static final int ORDER_BY_ASC = 1;
    public static final int ORDER_BY_DSC = 2;

    private Connection cnc;
    private PreparedStatement queryArtistsForSong;

    private PreparedStatement insertArtist;
    private PreparedStatement insertAlbum;
    private PreparedStatement insertSong;

    private PreparedStatement queryArtist;
    private PreparedStatement queryAlbum;

    public boolean open()
    {
        try
        {
            cnc = DriverManager.getConnection(DB_PATH);
            queryArtistsForSong = cnc.prepareStatement(QUERY_ARTISTS_FOR_SONG);

            insertArtist = cnc.prepareStatement(INSERT_ARTIST, PreparedStatement.RETURN_GENERATED_KEYS);
            insertAlbum = cnc.prepareStatement(INSERT_ALBUM, PreparedStatement.RETURN_GENERATED_KEYS);
            insertSong = cnc.prepareStatement(INSERT_SONG);

            queryArtist = cnc.prepareStatement(QUERY_ARTIST, PreparedStatement.RETURN_GENERATED_KEYS);
            queryAlbum = cnc.prepareStatement(QUERY_ALBUM, PreparedStatement.RETURN_GENERATED_KEYS);
            
            return true;
        } catch(SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public boolean close()
    {
        try
        {

            if(queryArtistsForSong!=null)
                queryArtistsForSong.close();

            if(insertArtist!=null)
                insertArtist.close();
            if(insertAlbum!=null)
                insertAlbum.close();
            if(insertSong!=null)
                insertSong.close();

            if(queryAlbum!=null)
                queryAlbum.close();
            if(queryArtist!=null)
                queryArtist.close();

            if(cnc != null)
                cnc.close();

            return true;
        } catch(SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }
    
    public List<Artist> queryArtists(int sortOrder)
    {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(ARTISTS);

        if(sortOrder != ORDER_BY_NONE)
        {
            sb.append(" ORDER BY ");
            sb.append(ARTISTS_NAME);
            sb.append(" COLLATE NOCASE ");

            if(sortOrder == ORDER_BY_DSC)
                sb.append(" DESC");
        }

        List<Artist> artists = null;

        try(Statement statement = cnc.createStatement();
            ResultSet rs = statement.executeQuery(sb.toString()))
        {
            artists = new ArrayList<>();

            while(rs.next())
            {
                Artist artist = new Artist();
                artist.setId(rs.getInt(ARTISTS_ID_INDEX));
                artist.setName(rs.getString(ARTISTS_NAME_INDEX));

                artists.add(artist);
            }

            return artists;
        } catch(SQLException e)
        {
            e.printStackTrace();
        }


        return artists;
    }

    public boolean check(String s)
    {
        return !(s.contains(";") || s.contains("--") || s.contains("\""));
    }

    public List<Album> queryAlbumsForArtist(String artistName, int sortOrder)
    {
        if(!check(artistName))
        {
            System.out.println("Men know how to deal with Injections bud! ;p");
            return null;
        }

        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(ALBUMS);
        sb.append(".");
        sb.append(ALBUMS_NAME);
        sb.append(" FROM ");
        sb.append(ALBUMS);
        sb.append(" JOIN ");
        sb.append(ARTISTS);
        sb.append(" ON ");
        sb.append(ALBUMS);
        sb.append(".");
        sb.append(ALBUMS_ARTIST);
        sb.append(" = ");
        sb.append(ARTISTS);
        sb.append(".");
        sb.append(ARTISTS_ID);
        sb.append(" WHERE ");
        sb.append(ARTISTS);
        sb.append(".");
        sb.append(ARTISTS_NAME);
        sb.append(" = ");
        sb.append("'" + artistName + "'");

        if(sortOrder != ORDER_BY_NONE)
        {
            sb.append(" ORDER BY ");
            sb.append(ALBUMS);
            sb.append(".");
            sb.append(ALBUMS_NAME);
            sb.append(" COLLATE NOCASE ");

            if(sortOrder == ORDER_BY_DSC)
                sb.append(" DESC");
        }

        List<Album> albums = null;

        try(Statement statement = cnc.createStatement();
            ResultSet rs = statement.executeQuery(sb.toString()))
        {
            albums = new ArrayList<>();

            while(rs.next())
            {
                Album album = new Album();
                album.setName(rs.getString(1));
                albums.add(album);
            }

            return albums;
        } catch(SQLException e)
        {
            e.printStackTrace();
        }

        return albums;

    }

    public List<SongArtist> queryArtistsForSong(String song, int sortOrder)
    {
        List<SongArtist> songArtists = null;

        createFullView();


        try
        {
            queryArtistsForSong.setString(1, song);
            ResultSet rs = queryArtistsForSong.executeQuery();
            songArtists = new ArrayList<>();

            while(rs.next())
            {
                SongArtist songArtist = new SongArtist();
                songArtist.setArtist(rs.getString(1));
                songArtist.setAlbum(rs.getString(2));
                songArtist.setTrack(rs.getInt(3));

                songArtists.add(songArtist);
            }

            return songArtists;

        } catch(SQLException e)
        {
            System.out.println("[!] Query: " + queryArtistsForSong.toString());
            e.printStackTrace();
        }

        return songArtists;
    }

    public void queryMetadata(String table)
    {
        String sql = "SELECT * FROM " + table;
        if(!check(table)) 
            return; 

        try(Statement statement = cnc.createStatement();
            ResultSet rs = statement.executeQuery(sql))
        {
            ResultSetMetaData meta = rs.getMetaData();

            for(int i=1, colCount = meta.getColumnCount(); i<=colCount ; i++)
            {
                System.out.println("Index=" + i + ", Column Name = '" + meta.getColumnName(i) + "'" );
            }
            
        } catch(SQLException e)
        {
            e.printStackTrace();
        }        
    }

    public int getCount(String table)
    {
        String sql = "SELECT COUNT(*) FROM " + table;
        if(!check(table)) 
            return -1; 

       

        try(Statement statement = cnc.createStatement();
            ResultSet rs = statement.executeQuery(sql))
            {
                return rs.getInt(1);
            } catch(SQLException e)
            {
                System.out.println("Query failed: " + e.getMessage());
                return -1;
            }
    }

    public boolean createFullView()
    {
        try(Statement statement = cnc.createStatement())
            {
                statement.execute(CREATE_FULL_VIEW);
                return true;
            } catch(SQLException e)
            {
                System.out.println("Query failed: " + e.getMessage());
                return false;
            }
    }

    private int insertArtist(String name) throws SQLException
    {
        queryArtist.setString(1, name);
        ResultSet rs= queryArtist.executeQuery();
    
        if(rs.next())
            return rs.getInt(1);    // Returning id number of already present artist.

        // Else adding the new entry for artist.
        insertArtist.setString(1, name);
        int affectedRows = insertArtist.executeUpdate();

        if(affectedRows != 1)
            throw new SQLException("Couldn't add the artist!");
    
        ResultSet generatedKeys = insertArtist.getGeneratedKeys();

        if(generatedKeys.next())
            return generatedKeys.getInt(1);
        else
            throw new SQLException("Couldn't get _id for artist");
            
    }

    private int insertAlbum(String name, int artistID) throws SQLException
    {
        queryAlbum.setString(1, name);
        ResultSet rs= queryAlbum.executeQuery();
    
        if(rs.next())
            return rs.getInt(1);    // Returning id number of already present artist.

        // Else adding the new entry for artist.
        insertAlbum.setString(1, name);
        insertAlbum.setInt(2, artistID);
        int affectedRows = insertAlbum.executeUpdate();

        if(affectedRows != 1)
            throw new SQLException("Couldn't add the album!");
    
        ResultSet generatedKeys = insertAlbum.getGeneratedKeys();

        if(generatedKeys.next())
            return generatedKeys.getInt(1);
        else
            throw new SQLException("Couldn't get _id for album");
            
    }

    public void insertSong(String title, int track, String album, String artist)
    {
        try
        {
            cnc.setAutoCommit(false);   // Begin Transaction.


            int artistId = insertArtist(artist);
            int albumId = insertAlbum(album, artistId);

            insertSong.setString(1, title);
            insertSong.setInt(2, track);
            insertSong.setInt(3, albumId);

            if(insertSong.executeUpdate() == 1)
                cnc.commit();
            else
                throw new SQLException("Error Inserting Song");
            
            System.out.println("Successfully Inserted.");

        } catch(Exception e1)
        {
            System.out.println("Exception in inserting song: " + e1.getMessage());
            try{
                System.out.println("Trying to perform RollBack :D");
                cnc.rollback();
            } catch(SQLException e2)
            {
                System.out.println("Error performing RollBack: " + e2.getMessage());
            }
        }
        finally{
            try{
                System.out.println("Turning AutoCommit back ON");
                cnc.setAutoCommit(true);    // Finished Transaction
            } catch(SQLException e)
            {
                System.out.println("Couldn't turn back on AutoCommit: " + e.getMessage());
            }
        }
    }
}