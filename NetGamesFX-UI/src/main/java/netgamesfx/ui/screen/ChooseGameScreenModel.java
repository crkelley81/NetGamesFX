/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netgamesfx.ui.screen;

import com.guigarage.controls.Media;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import netgamesfx.engine.game.GameDefinition;
import org.reactfx.value.Val;
import org.reactfx.value.Var;

/**
 *
 * @author Christopher
 */
public class ChooseGameScreenModel {
    private ObjectProperty<GameDefinitionAdapter> selectedGameAdapterProperty = new SimpleObjectProperty<>(this, "selectedGameAdapter");
    private ObservableList<GameDefinitionAdapter> installedGames = FXCollections.observableArrayList();
    
    public final ObservableList<GameDefinitionAdapter> getInstalledGames() {
        return FXCollections.unmodifiableObservableList(installedGames);
    }

    public final ObjectProperty<GameDefinitionAdapter> selectedGameAdapterProperty() {
        return selectedGameAdapterProperty;
    }
    
    public final Val<GameDefinition> selectedGameProperty() {
        return Val.wrap(selectedGameAdapterProperty())
                .map(a -> a.gd);
    }
    
    public final Val<String> titleProperty() {
        return selectedGameProperty()
                .map(GameDefinition::getTitle)
                .orElseConst("Choose a game");
    }
    
    public final ObservableBooleanValue disablePlayButtonProperty() {
        return selectedGameAdapterProperty.isNull();
    }

    public final void setInstalledGames(List<GameDefinition> list) {
        this.installedGames.removeAll();
        this.installedGames.addAll(list.stream().map(GameDefinitionAdapter::new).collect(Collectors.toList()));
    }
    
    public static class GameDefinitionAdapter implements Media {
        private final GameDefinition gd;
        private final SimpleStringProperty titleProperty;
        private final SimpleStringProperty descriptionProperty;
        private final SimpleObjectProperty<Image> imageProperty;

        public GameDefinitionAdapter(final GameDefinition gd) {
            this.gd = gd;
            
            titleProperty = new SimpleStringProperty(this, "title", gd.getTitle());
            descriptionProperty = new SimpleStringProperty(this, "description", "This is the description");
            imageProperty = new SimpleObjectProperty<>(this, "image");
        }
        
        @Override
        public StringProperty titleProperty() {
            return titleProperty;
        }

        @Override
        public StringProperty descriptionProperty() {
            return descriptionProperty;
        }

        @Override
        public ObjectProperty<Image> imageProperty() {
            return imageProperty;
        }
        
        
    }
}
