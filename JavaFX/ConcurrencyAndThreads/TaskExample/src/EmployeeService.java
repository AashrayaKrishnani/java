import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class EmployeeService extends Service<ObservableList<String>> {

    @Override
    protected Task<ObservableList<String>> createTask() {
        
        return new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws Exception {
                Thread.sleep(1000);
                
                String data[] = { "Pretend that", 
                "This is",
                "The result of",
                "A DataBase query",
                "that was fetched",
                "by an another thread.",
                ";p" };

                final ObservableList<String> list = FXCollections.observableArrayList();
                
                Random random = new Random();

                for(int i =0; i<data.length; i++)
                {
                    Thread.sleep(random.nextInt(1000));
                    list.add(data[i]);
                    this.updateProgress(i+1, data.length);  // here 'this' refers to the Task's instance.
                    this.updateMessage("Added \"" + data[i] + "\"");
                }

                Thread.sleep(500);

                return list;
            }
            
        };
    }
    
    
}
