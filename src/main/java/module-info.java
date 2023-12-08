module org.ielena.pokedex {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.desktop;
    requires java.net.http;


    opens org.ielena.pokedex to javafx.fxml;
    exports org.ielena.pokedex;
    exports org.ielena.pokedex.controller;
    exports org.ielena.pokedex.model;
    opens org.ielena.pokedex.controller to javafx.fxml;
    exports org.ielena.pokedex.preloader;
    opens org.ielena.pokedex.preloader to javafx.fxml;
    exports org.ielena.pokedex.preloader.notification;
    opens org.ielena.pokedex.preloader.notification to javafx.fxml;
    opens org.ielena.pokedex.model to javafx.fxml;
    exports org.ielena.pokedex.utils;
    opens org.ielena.pokedex.utils to javafx.fxml;
    exports org.ielena.pokedex.model.mediator;
    opens org.ielena.pokedex.model.mediator to javafx.fxml;
}