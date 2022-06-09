import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;


public class Controller {

    @FXML
    private ListView<String> listView;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressLabel;

    // We'll bind the progress of this progressBar with the progress property of our 'task' so that they work in sync :D
    private Task<ObservableList<String>> task;

    // ! However it's sensible to use a Service to Run tasks than a Task instance itself, for we can rerun them and it's more systematic.
    private Service<ObservableList<String>> service;

    public void initialize()
    {
        // When using the Service to run the task.
        {
            service = new EmployeeService();
        
            // Better is to bind the ListView Items to the task.value such that they are directly linked :)
            listView.itemsProperty().bind(service.valueProperty());
    
            // Binding progress property of progressBar to that of the property in 'task'
            progressBar.progressProperty().bind(service.progressProperty());
    
            // Binding message property of label's text
            progressLabel.textProperty().bind(service.messageProperty());
    
            // Binding visibility 
            progressBar.visibleProperty().bind(service.runningProperty());
            progressLabel.visibleProperty().bind(service.runningProperty());
        }

        // When using the Task instance
        {
        //     task = new Task<ObservableList<String>>() {
        //         @Override
        //         protected ObservableList<String> call() throws Exception {
        //             Thread.sleep(1000);
                    
        //             String data[] = { "Pretend that", 
        //             "This is",
        //             "The result of",
        //             "A DataBase query",
        //             "that was fetched",
        //             "by an another thread.",
        //             ";p" };
    
        //             final ObservableList<String> list = FXCollections.observableArrayList();
                    
        //             Random random = new Random();
    
        //             for(int i =0; i<data.length; i++)
        //             {
        //                 Thread.sleep(random.nextInt(1000));
        //                 list.add(data[i]);
        //                 this.updateProgress(i+1, data.length);  // here 'this' refers to the Task's instance.
        //                 this.updateMessage("Added \"" + data[i] + "\"");
        //             }
    
        //             Thread.sleep(500);
    
        //             return list;
        //         }
                
        //     };
        
        //     // Better is to bind the ListView Items to the task.value such that they are directly linked :)
        //     listView.itemsProperty().bind(task.valueProperty());
    
        //     // Binding progress property of progressBar to that of the property in 'task'
        //     progressBar.progressProperty().bind(task.progressProperty());
    
        //     // Binding message property of label's text
        //     progressLabel.textProperty().bind(task.messageProperty());
    
        //     // Binding visibility 
        //     progressBar.visibleProperty().bind(task.runningProperty());
        //     progressLabel.visibleProperty().bind(task.runningProperty());
        }
    }

    @FXML 
    public void buttonPressedService(){
        if( service.getState() == State.SUCCEEDED)
        {
            service.reset();
            service.start();
        }
        else if(service.getState() == State.READY)
        {
            service.start();
        }
        // if(service.getState() == State.RUNNING) ; // Nothing
    }

    @FXML
    public void buttonPressedTask()
    {
        new Thread(task).start();
    }


}
