package org.ielena.pokedex.model;
import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class AgregadorPokeAPI {
    public static Pokemon json2Pokemon(int id){

        Pokemon pokemon = null;

        try {
            URL url=new URL("https://pokeapi.co/api/v2/pokemon/"+ id);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();

            if (responseCode != 200) {

                System.out.println("Error" + responseCode);

            }else {
                StringBuilder informationstring= new StringBuilder();
                Scanner sc=new Scanner(url.openStream());
                while(sc.hasNext()) {
                    informationstring.append(sc.nextLine());
                }
                sc.close();

                FileWriter fileWriter = new FileWriter("src/main/resources/org/ielena/pokedex/JSONs/"+id+".json");
                fileWriter.write(informationstring.toString());

                JSONTokener parser = new JSONTokener(informationstring.toString());
                JSONObject pokemonJSON = new JSONObject(parser);

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

                String imagenURL = pokemonJSON.getJSONObject("sprites").getJSONObject("other").getJSONObject("official-artwork").getString("front_default");
                String spriteURL = pokemonJSON.getJSONObject("sprites").getString("front_default");

                Image imagen = null;
                try {
                    imagen = new Image(new URL(imagenURL).openStream());
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Image sprite = null;
                try {
                    sprite = new Image(new URL(spriteURL).openStream());
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                String rutaIMG = "src/main/resources/org/ielena/pokedex/img/images/" + id + ".png";
                String rutaSprite = "src/main/resources/org/ielena/pokedex/img/sprites/" + id + ".png";

                descargarImagen(imagenURL, rutaIMG);
                descargarImagen(spriteURL, rutaSprite);

                pokemon = new Pokemon(id, nombre, tipo1, tipo2, altura, peso, HP, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad, imagen, sprite);
                System.out.println(id + "terminado");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pokemon;
    }
    /*public static Pokemon json2Pokemon(int id, String rutaJSON){
        Pokemon pokemon = null;

        JSONTokener parser = new JSONTokener(rutaJSON);
        JSONObject pokemonJSON = new JSONObject(parser);

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

        String rutaIMG = "src/main/resources/com/dam/fragments/img/images/" + id + ".png";
        String rutaSprite = "src/main/resources/com/dam/fragments/img/sprites/" + id + ".png";


        pokemon = new Pokemon(id, nombre, tipo1, tipo2, altura, peso, HP, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad, spriteURL, imagenURL);
        System.out.println(id + "terminado");

        return pokemon;
    }*/

    public static void descargarImagen(String urlImagen, String rutaDestino) throws IOException {
        URL url = new URL(urlImagen);
        try (InputStream in = url.openStream()) {
            Path destino = Path.of(rutaDestino);
            Files.copy(in, destino, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}

