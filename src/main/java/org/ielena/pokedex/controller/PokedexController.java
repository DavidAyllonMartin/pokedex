package org.ielena.pokedex.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.ielena.pokedex.PokedexApplication;
import org.ielena.pokedex.model.Pokemon;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PokedexController extends Controller implements Initializable {
    @FXML public VBox container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loadPokemonItems(List<Pokemon> pokemons){
        for (Pokemon pokemon : pokemons){
            try {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(PokedexApplication.class.getResource("pokemon-item.fxml"));
                AnchorPane anchorPane         = fxmlLoader.load();
                PokemonItemController itemController = fxmlLoader.getController();
                itemController.setMediator(getMediator());

                itemController.setData(pokemon);

                container.getChildren().add(anchorPane);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}