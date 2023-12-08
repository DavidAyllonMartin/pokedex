package org.ielena.pokedex.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

public class ImageDownloader {

    public static void downloadAndResizeImageFromUrl(String imageUrl, String filePath, int newWidth, int newHeight) throws IOException {
        URI uri = URI.create(imageUrl);
        try (InputStream imageStream = uri.toURL().openStream()) {
            BufferedImage originalImage = ImageIO.read(imageStream);

            // Resize the image
            BufferedImage resizedImage = resizeImage(originalImage, newWidth, newHeight);

            Path destiny = Path.of(filePath);
            Files.createDirectories(destiny.getParent());

            // Save the image
            ImageIO.write(resizedImage, "png", destiny.toFile());
        }
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int newWidth, int newHeight) {
        Image tmp = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }
}

