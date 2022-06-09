import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Albums
 */
public class Album {

    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty artist;

    public Album()
    {
        id = new SimpleIntegerProperty();
        artist = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getArtist() {
        return artist.get();
    }

    public void setArtist(int artist) {
        this.artist.set(artist);
    }

    
    
}