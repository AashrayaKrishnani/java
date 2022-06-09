import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    @FXML
    private TableView artistTable;
    @FXML
    private ProgressBar progressBar;

    public void listArtists() 
    {
        Task<ObservableList<Artist>> task = new GetAllArtistsTask();
        artistTable.itemsProperty().bind(task.valueProperty());
        progressBar.visibleProperty().bind(task.runningProperty());
        progressBar.progressProperty().bind(task.progressProperty());
        
        new Thread(task).start();
    }

    @FXML 
    public void listAlbumsForArtist()
    {
        final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();
        if(artist == null)
        {
            System.out.println("No Artist Selected");
            return;
        }

        Task<ObservableList<Album>> task = new Task<ObservableList<Album>>()
        {
            @Override
            protected ObservableList<Album> call(){
                return FXCollections.observableArrayList(DataSource.getInstance().queryAlbumForArtistId(artist.getId()));
            }
            
        };

        artistTable.itemsProperty().bind(task.valueProperty());
        progressBar.visibleProperty().bind(task.runningProperty());
        progressBar.progressProperty().bind(task.progressProperty());

        new Thread(task).start();

    }


}

class GetAllArtistsTask extends Task<ObservableList<Artist>>{

    @Override
    public ObservableList<Artist> call() {
        return FXCollections.observableArrayList(
            DataSource.getInstance().queryArtists(DataSource.ORDER_BY_ASC)
        );
    }
    
}