package org.ielena.pokedex.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.ielena.pokedex.Callback;
import org.ielena.pokedex.PokedexApplication;
import org.ielena.pokedex.model.AgregadorPokeAPI;
import org.ielena.pokedex.model.Pokemon;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PokedexController implements Initializable {

    @FXML
    public VBox container;
    private void generateTestData(Callback callback) {

        for (int i = 1; i < 4; i++) {
            Pokemon pokemon = AgregadorPokeAPI.json2Pokemon(i);
            callback.onClick(pokemon);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        generateTestData(pokemon -> {

            try {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(PokedexApplication.class.getResource("pokemon-item.fxml"));
                AnchorPane anchorPane         = fxmlLoader.load();
                PokemonItemController itemController = fxmlLoader.getController();

                itemController.setData(pokemon, data ->{});

                container.getChildren().add(anchorPane);


            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}