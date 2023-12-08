package org.ielena.pokedex.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import org.ielena.pokedex.model.Pokemon;
import org.ielena.pokedex.utils.ColorAnalyzer;

public class PokemonItemController extends Controller {
    private Pokemon pokemon;

    @FXML
    private Label name, id, type1, type2;
    @FXML
    private ImageView image;
    @FXML
    private HBox pokemonCard;

    @FXML
    private void click1(MouseEvent mouseEvent) {
        getMediator().notify(this, 1);
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void setData(Pokemon pokemon) {
        this.pokemon = pokemon;

        id.setText(String.format("#%03d", pokemon.getId()));
        name.setText(pokemon.getName());
        type1.setText(pokemon.getType1());
        type2.setText(pokemon.getType2());
        image.setImage(pokemon.getImage());

        setBackgroundColor(pokemon);
    }

    private void setBackgroundColor(Pokemon pokemon) {
        Color dominantColor = ColorAnalyzer.getDominantColor(pokemon.getImage());
        String backgroundColor = String.format("-fx-background-color: rgba(%d, %d, %d, 1.0);",
                (int) (dominantColor.getRed() * 255),
                (int) (dominantColor.getGreen() * 255),
                (int) (dominantColor.getBlue() * 255));
        pokemonCard.setStyle(backgroundColor);
    }
}
