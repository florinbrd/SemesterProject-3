import javaModel.DataModel;
import javaModel.IDataModel;
import network.Client;
import view.ViewHandler;
import viewmodel.ViewModelProvider;
import javafx.application.Application;
import javafx.stage.Stage;


public class Run extends Application {

    public static void main(String[] args) {
        Application.launch(Run.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        IDataModel dataModel = new DataModel();
        Client client = new Client();
        client.run();
        System.out.println(dataModel.setClient(client));
        ViewModelProvider vmp = new ViewModelProvider(dataModel);
        ViewHandler vh = new ViewHandler(stage, vmp);


        vh.start();
    }
}
