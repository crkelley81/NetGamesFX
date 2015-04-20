/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netgamesfx.engine.game.impl;

import netgamesfx.engine.game.GameDefinition;

/**
 *
 * @author Christopher
 */
public class ClassicRiskGameDefinition extends GameDefinition {
        @Override
    public String getTitle() {
        return "Classic Risk v1";
    }

    @Override
    public String getDescriptionAsHtml() {
        return "<html><body>This is the description of Classic Risk.</body></html>";
    }
}
