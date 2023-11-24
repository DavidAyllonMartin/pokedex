package org.ielena.pokedex.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ColorAnalyzer {

    public static Color getDominantColor(Image image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        javafx.scene.image.PixelReader pixelReader = image.getPixelReader();

        double totalRed = 0;
        double totalGreen = 0;
        double totalBlue = 0;
        double totalAlpha = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixelColor = pixelReader.getColor(x, y);

                totalRed += pixelColor.getRed() * pixelColor.getOpacity();
                totalGreen += pixelColor.getGreen() * pixelColor.getOpacity();
                totalBlue += pixelColor.getBlue() * pixelColor.getOpacity();
                totalAlpha += pixelColor.getOpacity();
            }
        }

        double averageRed = totalRed / totalAlpha;
        double averageGreen = totalGreen / totalAlpha;
        double averageBlue = totalBlue / totalAlpha;

        // Ajustar la ponderación de cada componente en función de su contribución relativa
        double maxComponent = Math.max(Math.max(averageRed, averageGreen), averageBlue);
        double scaleFactor = 0.8 / maxComponent;

        averageRed *= scaleFactor;
        averageGreen *= scaleFactor;
        averageBlue *= scaleFactor;

        return Color.color(averageRed, averageGreen, averageBlue);
    }
}
