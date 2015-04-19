/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netgamesfx.ui.screen;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import netgamesfx.engine.game.GameDefinition;
import org.reactfx.value.Val;
import org.reactfx.value.Var;

/**
 *
 * @author Christopher
 */
public class ChooseGameScreenModel {
    private ObjectProperty<GameDefinition> selectedGameProperty = new SimpleObjectProperty<>(this, "selectedGame");
    private ObservableList<GameDefinition> installedGames = FXCollections.observableArrayList();
    
    public final ObservableList<GameDefinition> getInstalledGames() {
        return FXCollections.unmodifiableObservableList(installedGames);
    }

    public final Var<GameDefinition> selectedGameProperty() {
        return Val.wrap(selectedGameProperty).asVar(v -> selectedGameProperty.set(v));
    }
    
    public final Val<String> titleProperty() {
        return selectedGameProperty()
                .map(GameDefinition::getTitle)
                .orElseConst("Choose a game");
    }
    
    public final ObservableBooleanValue disablePlayButtonProperty() {
        return selectedGameProperty.isNull();
    }
}
