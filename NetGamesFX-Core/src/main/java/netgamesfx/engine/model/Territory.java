/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netgamesfx.engine.model;

import java.util.Objects;

/**
 *
 * @author christopher
 */
public final class Territory implements Identifiable {
    private final Id id;

    @Override
    public Id getId() {
        return id;
    }
    
    private Territory(final Id id) {
        this.id = Objects.requireNonNull(id);
    }    
}
