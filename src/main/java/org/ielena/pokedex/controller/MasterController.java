package org.ielena.pokedex.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.ielena.pokedex.Mediator;
import org.ielena.pokedex.PokedexApplication;
import org.ielena.pokedex.model.Pokemon;
import org.ielena.pokedex.model.SceneTransition;

import java.io.IOException;

public class MasterController implements Mediator {

    //Attributes
    private PokedexController pokedexController;
    private Scene pokedexView;
    private PokemonInfoController pokemonInfoController;
    private Scene pokemonInfo;
    private Stage primaryStage;

    //Constructor
    public MasterController(Scene pokedexView, PokedexController pokedexController, Scene pokemonInfo, PokemonInfoController pokemonInfoController, Stage primaryStage) {
        setPokedexView(pokedexView);
        setPokedexController(pokedexController);
        setPokemonInfo(pokemonInfo);
        setPokemonInfoController(pokemonInfoController);
        setPrimaryStage(primaryStage);
    }

    //Getters and setters
    public Scene getPokedexView() {
        return pokedexView;
    }

    public void setPokedexView(Scene pokedexView) {
        this.pokedexView = pokedexView;
    }

    public PokedexController getPokedexController() {
        return pokedexController;
    }

    public void setPokedexController(PokedexController pokedexController) {
        this.pokedexController = pokedexController;
    }

    public Scene getPokemonInfo() {
        return pokemonInfo;
    }

    public void setPokemonInfo(Scene pokemonInfo) {
        this.pokemonInfo = pokemonInfo;
    }

    public PokemonInfoController getPokemonInfoController() {
        return pokemonInfoController;
    }

    public void setPokemonInfoController(PokemonInfoController pokemonInfoController) {
        this.pokemonInfoController = pokemonInfoController;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void notify(Controller controller) {
        if (controller instanceof PokemonItemController pokemonItemController){
            Pokemon pokemon = pokemonItemController.getPokemon();
            cambiarVentanaEstadisticas(pokemon);
        } else if (controller instanceof PokemonInfoController) {
            cambiarVentanaPrincipal();
        }
    }

    private void cambiarVentanaEstadisticas(Pokemon pokemon) {
        // Añadir transición de desvanecimiento
        //SceneTransition.fadeTransition(primaryStage.getScene().getRoot(), 1.0, 0.0, 500);
        FXMLLoader fxmlLoader = new FXMLLoader(PokedexApplication.class.getResource("pokemon-info.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PokemonInfoController controller = fxmlLoader.getController();
        controller.setMediator(this);
        setPokemonInfo(scene);
        setPokemonInfoController(controller);

        pokemonInfoController.setData(pokemon);
        primaryStage.setScene(pokemonInfo);

        // Añadir transición de aparición
        //SceneTransition.fadeTransition(primaryStage.getScene().getRoot(), 0.0, 1.0, 500);

        primaryStage.show();
    }

    private void cambiarVentanaPrincipal() {
        // Añadir transición de desvanecimiento
        //SceneTransition.fadeTransition(primaryStage.getScene().getRoot(), 1.0, 0.0, 500);

        primaryStage.setScene(pokedexView);

        // Añadir transición de aparición
        //SceneTransition.fadeTransition(primaryStage.getScene().getRoot(), 0.0, 1.0, 500);

        primaryStage.show();
    }
}
