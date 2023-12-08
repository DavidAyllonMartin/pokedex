package org.ielena.pokedex;

import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.ielena.pokedex.controller.MasterController;
import org.ielena.pokedex.controller.PokedexController;
import org.ielena.pokedex.controller.ProgressMessage;
import org.ielena.pokedex.model.JsonDownloader;
import org.ielena.pokedex.model.Pokemon;
import org.ielena.pokedex.model.SceneTransition;
import org.ielena.pokedex.preloader.AppPreloader;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PokedexApplication extends Application {

    private MasterController mediator;
    private List<Pokemon> pokemonList = new ArrayList<>();

    @Override
    public void init(){

        int STARTING_ID = 1;
        int FINAL_ID = 151;

        double totalPokemons = FINAL_ID - STARTING_ID;

        for (int i = STARTING_ID; i <= FINAL_ID; i++) {
            Path pokemonJSON = Paths.get(String.format("src/main/resources/org/ielena/pokedex/JSONs/%d.json", i));
            if (Files.notExists(pokemonJSON)){
                JsonDownloader.downloadJSON(i, pokemonJSON);
            }
            Pokemon pokemon = Pokemon.json2Pokemon(pokemonJSON);
            pokemonList.add(pokemon);
            notifyPreloader(new ProgressMessage((i - STARTING_ID - 1)  / totalPokemons, String.format("Loading %s", pokemon.getNombre())));
        }
        notifyPreloader(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));

    }

    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("Pokedex");
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/icon.png")));
        stage.getIcons().add(icon);
        stage.setResizable(false);
        FXMLLoader pokedexView = new FXMLLoader(PokedexApplication.class.getResource("pokedex-view.fxml"));
        Scene pokedexViewScene = new Scene(pokedexView.load());
        PokedexController pokedexController = pokedexView.getController();
        this.mediator = new MasterController(pokedexViewScene, pokedexController, null, null, null);
        this.mediator.setPrimaryStage(stage);
        pokedexController.setMediator(this.mediator);
        pokedexController.loadPokemonItems(this.pokemonList);
        stage.setScene(pokedexViewScene);
        SceneTransition.fadeTransition(stage.getScene().getRoot(), 0.0, 1.0, 1000);
        stage.show();
    }

    public static void main(String[] args) {
        System.setProperty("javafx.preloader", AppPreloader.class.getName());
        launch();
    }
}