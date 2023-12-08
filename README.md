# Basic Pokedex

## Descripción

Basic Pokedex es una aplicación JavaFX que muestra información detallada sobre los Pokémon. Utiliza una interfaz gráfica para presentar datos descargados de archivos JSON proporcionados por [PokeAPI](https://pokeapi.co/), y ofrece una experiencia interactiva para explorar la información de cada Pokémon.

## Funcionalidades Principales

- Descarga y carga datos JSON de Pokémon desde [PokeAPI](https://pokeapi.co/).
- Por defecto, muestra información detallada sobre los 251 primeros Pokémon.
- Interfaz gráfica amigable para el usuario.
- Una pequeña animación de transición para mejorar la experiencia visual.

## Requisitos

- Java 8 o superior
- JavaFX

## Configuración

Puedes personalizar el rango de Pokémon que se cargarán inicialmente modificando las siguientes constantes en el archivo `PokedexApplication.java`:

```java
final int STARTING_ID = 1;
final int FINAL_ID = 251;
```
Aunque no es la naturaleza de la aplicación, puedes ajustar estos valores según tus preferencias para cargar un rango específico de Pokémon al iniciar la aplicación.

## Uso
Al iniciar la aplicación, se cargarán automáticamente los datos de los Pokémon dentro del rango especificado.
Navega a través de la lista de Pokémon en la interfaz gráfica.
Haz clic en un Pokémon para ver información detallada.

