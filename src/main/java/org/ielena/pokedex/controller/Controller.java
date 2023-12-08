package org.ielena.pokedex.controller;

import org.ielena.pokedex.model.mediator.Mediator;

public abstract class Controller {
    private Mediator mediator;

    public Mediator getMediator() {
        return mediator;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
}
