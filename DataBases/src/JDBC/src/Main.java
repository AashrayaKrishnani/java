import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        DataSource dataSource = new DataSource();

        if(!dataSource.open())
        {
            System.out.println("Error Opening DB.");
            System.exit(1);   
        }

        // List<Artist> artists = dataSource.queryArtists(DataSource.ORDER_BY_NONE);

        // for(Artist artist: artists)
        // {
        //     System.out.println(artist.getId() + ". " + artist.getName());
        // }

        // List<Album> albums = dataSource.queryAlbumsForArtist("Iron Maiden", DataSource.ORDER_BY_NONE);

        // for(Album album: albums)
        // {
        //     System.out.println(album.getName());
        // }

        // List<SongArtist> songArtists = dataSource.queryArtistsForSong("Go Your Own Way", DataSource.ORDER_BY_NONE);

        // for(SongArtist songArtist: songArtists)
        // {
        //     System.out.println("Artist: " + songArtist.getArtist() + ", Album: " + songArtist.getAlbum() + ", Track: " + songArtist.getTrack() );
        // }

        // System.out.println(dataSource.getCount(DataSource.SONGS));

        // dataSource.createFullView();

        dataSource.insertSong("Touch Of Brown", 2,  "In the Dark", "Grateful Dead");

        dataSource.close();

    }
}
