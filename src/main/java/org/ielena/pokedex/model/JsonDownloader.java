package org.ielena.pokedex.model;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class JsonDownloader {

    private static final String API_URL = "https://pokeapi.co/api/v2/pokemon/";

    public static void downloadJSON(int id, Path jsonPath) {
        try {
            String jsonContent = fetchJsonFromApi(id);
            saveJsonToFile(jsonContent, jsonPath);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al descargar y guardar el archivo JSON", e);
        }
    }

    private static String fetchJsonFromApi(int id) throws IOException, InterruptedException {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + id))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new IOException("Error al realizar la solicitud HTTP. Código de respuesta: " + response.statusCode());
            }

            return response.body();
        }
    }

    private static void saveJsonToFile(String jsonContent, Path jsonPath) {
        try {
            Files.writeString(jsonPath, jsonContent, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir el archivo JSON", e);
        }
    }
}
