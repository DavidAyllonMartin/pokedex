package org.ielena.pokedex.model.mediator;

import org.ielena.pokedex.controller.Controller;

public interface Mediator {
    void notify(Controller controller, int id);
}
