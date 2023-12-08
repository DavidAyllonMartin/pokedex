package org.ielena.pokedex.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.ielena.pokedex.PokedexApplication;
import org.ielena.pokedex.model.Pokemon;

import java.io.IOException;
import java.util.List;

public class PokedexController extends Controller {
    @FXML
    public VBox container;

    public void loadPokemonItems(List<Pokemon> pokemons) {
        for (Pokemon pokemon : pokemons) {
            try {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(PokedexApplication.class.getResource("views/pokemon-item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
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