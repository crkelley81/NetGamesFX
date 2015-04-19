package netgamesfx.ui;

import netgamesfx.ui.screen.ChooseGameScreen;
import netgamesfx.ui.screen.MainScreen;
import io.datafx.controller.flow.Flow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class NetGamesFX extends Application {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(NetGamesFX.class, args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        Flow flow = createFlow();
        flow.startInStage(stage);
        
        stage.getScene().getStylesheets().add("/netgamesfx/ui/NetGamesFX.css");
//        stage.setTitle("NetGamesFX v0.1");
        stage.sizeToScene();
        stage.show();
    }

    private Flow createFlow() {
        return new Flow(MainScreen.class)
                .withLink(MainScreen.class, "startLocalGame", ChooseGameScreen.class)
                .withGlobalLink("mainMenu", MainScreen.class)
                .withGlobalTaskAction("quit", () -> Platform.exit());
    }
}
