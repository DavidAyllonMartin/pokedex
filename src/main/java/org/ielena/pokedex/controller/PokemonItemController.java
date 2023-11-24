package org.ielena.pokedex.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import org.ielena.pokedex.Callback;
import org.ielena.pokedex.model.ColorAnalyzer;
import org.ielena.pokedex.model.Pokemon;

public class PokemonItemController {

    @FXML private Label name;
    @FXML private Label id;
    @FXML private Label type1;
    @FXML private Label type2;
    @FXML private ImageView image;
    @FXML private HBox pokemonCard;
    @FXML private void click(MouseEvent mouseEvent){callback.onClick(pokemon);}
    private Pokemon pokemon;
    private Callback callback;

    public void setData(Pokemon pokemon, Callback callback){
        this.pokemon = pokemon;
        this.callback = callback;

        id.setText(String.format("#%03d", pokemon.getId()));
        name.setText(pokemon.getNombre());
        type1.setText(pokemon.getTipo1());
        type2.setText(pokemon.getTipo2());
        image.setImage(pokemon.getImagen());

        Color dominantColor = ColorAnalyzer.getDominantColor(pokemon.getImagen());
        String backgroundColor = String.format("-fx-background-color: rgba(%d, %d, %d, 1.0);",
                (int) (dominantColor.getRed() * 255),
                (int) (dominantColor.getGreen() * 255),
                (int) (dominantColor.getBlue() * 255));
        pokemonCard.setStyle(backgroundColor);
    }
}
