package org.ielena.pokedex.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.ielena.pokedex.model.ColorAnalyzer;
import org.ielena.pokedex.model.Pokemon;

import java.net.URL;
import java.util.ResourceBundle;

public class PokemonInfoController extends Controller implements Initializable {

    @FXML private Label id, name, type, height, weight, hp, attack, defense, specialAttack, specialDefense, speed;
    @FXML private ImageView image;
    @FXML private ProgressBar attack_pb, hp_pb, defense_pb, specialAttack_pb, specialDefense_pb, speed_pb;
    @FXML private AnchorPane infoPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setData(Pokemon pokemon){
        Color dominantColor = ColorAnalyzer.getDominantColor(pokemon.getImagen());
        String backgroundColor = String.format("-fx-background-color: rgba(%d, %d, %d, 1.0);",
                (int) (dominantColor.getRed() * 255),
                (int) (dominantColor.getGreen() * 255),
                (int) (dominantColor.getBlue() * 255));
        infoPane.setStyle(backgroundColor);
        id.setText(String.format("#%03d", pokemon.getId()));
        name.setText(pokemon.getNombre());
        if (pokemon.getTipo2() == null){
            type.setText(pokemon.getTipo1());
        }else {
            type.setText(pokemon.getTipo1()+ " / " + pokemon.getTipo2());
        }
        image.setImage(pokemon.getImagen());
        height.setText(String.format("%.1f m", pokemon.getAltura()));
        weight.setText(String.format("%.1f kg", pokemon.getPeso()));
        hp.setText(String.valueOf(pokemon.getHP()));
        attack.setText(String.valueOf(pokemon.getAtaque()));
        defense.setText(String.valueOf(pokemon.getDefensa()));
        specialAttack.setText(String.valueOf(pokemon.getAtaqueEspecial()));
        specialDefense.setText(String.valueOf(pokemon.getDefensaEspecial()));
        speed.setText(String.valueOf(pokemon.getVelocidad()));

        attack_pb.setProgress(pokemon.getAtaque()/ (double) Pokemon.MAX_STAT);
        changeColorProgressBar(attack_pb);
        defense_pb.setProgress(pokemon.getDefensa()/(double) Pokemon.MAX_STAT);
        changeColorProgressBar(defense_pb);
        specialAttack_pb.setProgress(pokemon.getAtaqueEspecial()/(double) Pokemon.MAX_STAT);
        changeColorProgressBar(specialAttack_pb);
        specialDefense_pb.setProgress(pokemon.getDefensaEspecial()/(double) Pokemon.MAX_STAT);
        changeColorProgressBar(specialDefense_pb);
        speed_pb.setProgress(pokemon.getVelocidad()/(double) Pokemon.MAX_STAT);
        changeColorProgressBar(speed_pb);
        hp_pb.setProgress(pokemon.getHP()/(double) Pokemon.MAX_STAT);
        changeColorProgressBar(hp_pb);
    }

    private void changeColorProgressBar(ProgressBar progressBar){
        double progress = progressBar.getProgress();
        if (progress < (36.0/Pokemon.MAX_STAT)){
            progressBar.getStyleClass().add("progress-bar-low");
        }else if (progress < (75.0/Pokemon.MAX_STAT)) {
            progressBar.getStyleClass().add("progress-bar-mid");
        }else if (progress < (100.0/Pokemon.MAX_STAT)) {
            progressBar.getStyleClass().add("progress-bar-high");
        }else {
            progressBar.getStyleClass().add("progress-bar-extraHigh");
        }
    }

    public void click(MouseEvent mouseEvent) {
        getMediator().notify(this);
    }
}
