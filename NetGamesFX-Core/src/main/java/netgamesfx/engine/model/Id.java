/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netgamesfx.engine.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author christopher
 */
public final class Id implements Serializable, StringValue {
    
    public static Id from(final String id) {
        return new Id(id);
    }

    private final String id;
    
    private Id(final String id) {
        this.id = Objects.requireNonNull(id);
    }
      
    public final String stringValue() {
        return id;
    } 
}
