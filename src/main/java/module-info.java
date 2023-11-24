module org.ielena.pokedex {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens org.ielena.pokedex to javafx.fxml;
    exports org.ielena.pokedex;
    exports org.ielena.pokedex.controller;
    opens org.ielena.pokedex.controller to javafx.fxml;
}