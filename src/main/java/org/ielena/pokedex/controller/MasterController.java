package org.ielena.pokedex.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.ielena.pokedex.PokedexApplication;
import org.ielena.pokedex.model.Pokemon;
import org.ielena.pokedex.model.mediator.Mediator;

import java.io.IOException;

public class MasterController implements Mediator {

    //Attributes
    private Scene pokedexView;
    private Stage primaryStage;

    //Constructor
    public MasterController(Scene pokedexView, Stage primaryStage) {
        setPokedexView(pokedexView);
        setPrimaryStage(primaryStage);
    }

    //Getters and setters
    public Scene getPokedexView() {
        return pokedexView;
    }

    public void setPokedexView(Scene pokedexView) {
        this.pokedexView = pokedexView;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void notify(Controller controller, int id) {
        if (controller instanceof PokemonItemController pokemonItemController) {
            // A switch statement for handling different cases as the application expands
            if (id == 1) {
                changeToInfoWindow(pokemonItemController.getPokemon());
            }
        } else if (controller instanceof PokemonInfoController) {
            // A switch statement for handling different cases as the application expands
            if (id == 1) {
                changeToMainWindow();
            }
        }
    }

    private void changeToInfoWindow(Pokemon pokemon) {
        FXMLLoader fxmlLoader = new FXMLLoader(PokedexApplication.class.getResource("views/pokemon-info.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PokemonInfoController controller = fxmlLoader.getController();
        controller.setMediator(this);
        controller.setData(pokemon);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void changeToMainWindow() {
        primaryStage.setScene(pokedexView);
        primaryStage.show();
    }
}
