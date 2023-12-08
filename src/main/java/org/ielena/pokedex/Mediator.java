package org.ielena.pokedex;

import org.ielena.pokedex.controller.Controller;

public interface Mediator {
    public void notify(Controller controller);
}
