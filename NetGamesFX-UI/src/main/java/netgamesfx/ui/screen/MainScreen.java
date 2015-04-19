/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netgamesfx.ui.screen;

import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.action.ActionTrigger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author Christopher
 */
@FXMLController("MainScreen.fxml")
public class MainScreen {
    
    
    @FXML @ActionTrigger("quit")
    private Button quitBtn;
}
