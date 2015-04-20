/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netgamesfx.ui.screen;

import com.guigarage.controls.SimpleMediaListCell;
import io.datafx.controller.FXMLController;
import io.datafx.controller.context.ConcurrencyProvider;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.core.concurrent.ObservableExecutor;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.web.WebView;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import netgamesfx.engine.game.GameDefinition;
import netgamesfx.engine.game.GameManager;
import netgamesfx.ui.screen.ChooseGameScreenModel.GameDefinitionAdapter;
import org.reactfx.EventStream;
import org.reactfx.util.Try;

/**
 *
 * @author Christopher
 */
@FXMLController("ChooseGameScreen.fxml")
public class ChooseGameScreen {
    private static final Logger LOG = Logger.getLogger(ChooseGameScreen.class.getName());
    
    @Inject private GameManager manager;
    @Inject private ChooseGameScreenModel model;
    @ConcurrencyProvider private ObservableExecutor executor;
    
    @FXML private void refreshGamesList() {
        LOG.info("Refresh the games list");
        
        CompletableFuture.supplyAsync(() -> manager.findInstalledGames())//, executor)
                .thenAccept(list -> model.setInstalledGames(list));
    }
    
    @PostConstruct
    public void doInit() {
        assert model != null : "Model should have been injected by flow";
        assert gamesList != null : "gamesList should have been injected; check FXML";
        assert executor != null : "Executor should have been injected by flow";
        
        gamesList.setCellFactory(v -> new SimpleMediaListCell());
        gamesList.setItems(model.getInstalledGames());
        model.selectedGameAdapterProperty().bind(gamesList.getSelectionModel().selectedItemProperty());
        
        gameTitleLbl.textProperty().bind(model.titleProperty());
        playBtn.disableProperty().bind(model.disablePlayButtonProperty());
        
        EventStream<GameDefinition> stream = model.selectedGameProperty().values();
        EventStream<?> cancelImpulse = stream;
        
        stream.successionEnds(Duration.ofMillis(500))
                .mapToTask(this::loadGameHtml)
                .awaitLatest(cancelImpulse)
                .subscribe(this::updateGameHtml);
        
        refreshGamesList();
    }
    
    private Task<String> loadGameHtml(final GameDefinition gd) {
        return new Task<String>() {

            @Override
            protected String call() throws Exception {
                return gd.getDescriptionAsHtml();
            }
        };
    }
    
    private void updateGameHtml(final Try<String> html) {
        gameView.getEngine().loadContent(html.getOrElse("<html></html>"));
    }
    
    @FXML private ListView<GameDefinitionAdapter> gamesList;
    @FXML private WebView gameView;
    @FXML private Label gameTitleLbl;
    @FXML @ActionTrigger("mainMenu") private Button mainMenuBtn;
    @FXML @ActionTrigger("d") private Button playBtn;
}
