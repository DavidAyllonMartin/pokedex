package org.ielena.pokedex.model;

import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Clase que representa a un pokemon
 *
 * @author David Ayllón Martín
 * @version 1.0
 * @date 24/04/2023
 */
public class Pokemon implements Comparable<Pokemon> {

    //Atributos
    public static final int MAX_STAT = 255;
    private int id;
    private String nombre;
    private String tipo1;
    private String tipo2;
    private int altura;
    private int peso;
    private int HP;
    private int ataque;
    private int defensa;
    private int ataqueEspecial;
    private int defensaEspecial;
    private int velocidad;
    private Image imagen;

    //Constructores
    public Pokemon() {
        this.id = 0;
        this.nombre = "";
        this.tipo1 = "";
        this.tipo2 = "";
        this.altura = 0;
        this.peso = 0;
        this.HP = 0;
        this.ataque = 0;
        this.defensa = 0;
        this.ataqueEspecial = 0;
        this.defensaEspecial = 0;
        this.velocidad = 0;
    }

    public Pokemon(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Pokemon(int id, String nombre, String tipo1, String tipo2) {
        this(id, nombre);
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.altura = 0;
        this.peso = 0;
        this.HP = 0;
        this.ataque = 0;
        this.defensa = 0;
        this.ataqueEspecial = 0;
        this.defensaEspecial = 0;
        this.velocidad = 0;
    }

    public Pokemon(int id, String nombre, String tipo1, String tipo2, int altura, int peso) {
        this(id, nombre, tipo1, tipo2);
        this.altura = altura;
        this.peso = peso;
    }

    public Pokemon(int id, String nombre, String tipo1, String tipo2, int altura, int peso, int HP, int ataque, int defensa, int ataqueEspecial, int defensaEspecial, int velocidad) {
        this(id, nombre, tipo1, tipo2, altura, peso);
        this.HP = HP;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
        this.velocidad = velocidad;
    }

    public Pokemon(int id, String nombre, String tipo1, String tipo2, int altura, int peso, int HP, int ataque, int defensa, int ataqueEspecial, int defensaEspecial, int velocidad, Image imagen) {
        this(id, nombre, tipo1, tipo2, altura, peso, HP, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad);
        this.imagen = imagen;
    }


    //Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return capitalizeFirstLetter(nombre);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo1() {
        return capitalizeFirstLetter(tipo1);
    }

    public void setTipo1(String tipo1) {
        this.tipo1 = tipo1;
    }

    public String getTipo2() {
        return capitalizeFirstLetter(tipo2);
    }

    public void setTipo2(String tipo2) {
        this.tipo2 = tipo2;
    }

    public double getAltura() {
        //La altura está almacenada en decímetros
        return altura / 10.0;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public double getPeso() {
        //El peso está almacenado en hectogramos
        return peso / 10.0;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getAtaqueEspecial() {
        return ataqueEspecial;
    }

    public void setAtaqueEspecial(int ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
    }

    public int getDefensaEspecial() {
        return defensaEspecial;
    }

    public void setDefensaEspecial(int defensaEspecial) {
        this.defensaEspecial = defensaEspecial;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    private String capitalizeFirstLetter(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        } else {
            return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
        }
    }

    public static Pokemon json2Pokemon(Path jsonFile) {
        StringBuilder textJSON = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(jsonFile)) {
            String line;
            while ((line = br.readLine()) != null) {
                textJSON.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONTokener parser = new JSONTokener(textJSON.toString());
        JSONObject pokemonJSON = new JSONObject(parser);

        // Extract Pokemon details
        int id = pokemonJSON.getInt("id");
        String nombre = pokemonJSON.getString("name");
        String[] tipos = new String[2];

        JSONArray types = pokemonJSON.getJSONArray("types");
        for (int j = 0; j < types.length(); j++) {
            tipos[j] = types.getJSONObject(j).getJSONObject("type").getString("name");
        }

        String tipo1 = tipos[0];
        String tipo2 = tipos[1];

        int altura = pokemonJSON.getInt("height");
        int peso = pokemonJSON.getInt("weight");

        JSONArray stats = pokemonJSON.getJSONArray("stats");
        int HP = stats.getJSONObject(0).getInt("base_stat");
        int ataque = stats.getJSONObject(1).getInt("base_stat");
        int defensa = stats.getJSONObject(2).getInt("base_stat");
        int ataqueEspecial = stats.getJSONObject(3).getInt("base_stat");
        int defensaEspecial = stats.getJSONObject(4).getInt("base_stat");
        int velocidad = stats.getJSONObject(5).getInt("base_stat");

        String imagenURL = pokemonJSON.getJSONObject("sprites").getJSONObject("other")
                .getJSONObject("official-artwork").getString("front_default");

        Path imgPath = Paths.get(String.format("src/main/resources/org/ielena/pokedex/img/images/%d.png", id));

        if (!Files.exists(imgPath)) {
            try {
                ImageDownloader.descargarImagen(imagenURL, imgPath.toString(), 175, 175);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Image imagen = new Image(imgPath.toUri().toString());

        return new Pokemon(id, nombre, tipo1, tipo2, altura, peso, HP, ataque, defensa,
                ataqueEspecial, defensaEspecial, velocidad, imagen);
    }



    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo1='" + tipo1 + '\'' +
                ", tipo2='" + tipo2 + '\'' +
                ", altura=" + altura +
                ", peso=" + peso +
                ", HP=" + HP +
                ", ataque=" + ataque +
                ", defensa=" + defensa +
                ", ataqueEspecial=" + ataqueEspecial +
                ", defensaEspecial=" + defensaEspecial +
                ", velocidad=" + velocidad +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return id == pokemon.id && Objects.equals(nombre, pokemon.nombre);
    }

    @Override
    public int compareTo(Pokemon o) {
        return this.id - o.getId();
    }
}


