package org.ielena.pokedex.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;

public class ImageDownloader {

    public static void descargarImagen(String urlImagen, String rutaDestino, int nuevoAncho, int nuevoAlto) throws IOException {
        URL url = new URL(urlImagen);
        try (InputStream in = url.openStream()) {
            BufferedImage originalImage = ImageIO.read(in);

            // Redimensionar la imagen
            BufferedImage resizedImage = resizeImage(originalImage, nuevoAncho, nuevoAlto);

            Path destino = Path.of(rutaDestino);
            Files.createDirectories(destino.getParent());

            // Guardar la imagen redimensionada en formato PNG
            ImageIO.write(resizedImage, "png", destino.toFile());
        }
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int nuevoAncho, int nuevoAlto) {
        Image tmp = originalImage.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(nuevoAncho, nuevoAlto, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }

    public static void main(String[] args) {
        try {
            descargarImagen("URL_DE_LA_IMAGEN", "RUTA_DESTINO", 175, 175);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

