/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netgamesfx.engine.game;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.logging.Logger;

/**
 *
 * @author Christopher
 */
public class GameManager {
    private static final Logger LOG = Logger.getLogger(GameManager.class.getName());
    
    /**
     * Find all installed games. Returns a CompletionStage that will hold the
     * results when complete.
     * 
     * @param result 
     */
    public final List<GameDefinition> findInstalledGames() {
        LOG.info("Scanning classpath to find installed games ... ");
        final ServiceLoader<GameDefinition> loader = ServiceLoader.load(GameDefinition.class);
        final List<GameDefinition> result = new ArrayList<>();
        loader.forEach(o -> {
            LOG.info("Found game " + o);
            result.add(o);
                });
        return result;
    }
}
