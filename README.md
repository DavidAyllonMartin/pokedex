# Basic Pokedex

## Descripci�n

Basic Pokedex es una aplicaci�n JavaFX que muestra informaci�n detallada sobre los Pok�mon. Utiliza una interfaz gr�fica para presentar datos descargados de archivos JSON proporcionados por [PokeAPI](https://pokeapi.co/), y ofrece una experiencia interactiva para explorar la informaci�n de cada Pok�mon.

## Funcionalidades Principales

- Descarga y carga datos JSON de Pok�mon desde [PokeAPI](https://pokeapi.co/).
- Por defecto, muestra informaci�n detallada sobre los 251 primeros Pok�mon.
- Interfaz gr�fica amigable para el usuario.
- Una peque�a animaci�n de transici�n para mejorar la experiencia visual.

## Requisitos

- Java 8 o superior
- JavaFX

## Configuraci�n

Puedes personalizar el rango de Pok�mon que se cargar�n inicialmente modificando las siguientes constantes en el archivo `PokedexApplication.java`:

```java
final int STARTING_ID = 1;
final int FINAL_ID = 251;
```
Aunque no es la naturaleza de la aplicaci�n, puedes ajustar estos valores seg�n tus preferencias para cargar un rango espec�fico de Pok�mon al iniciar la aplicaci�n.

## Uso
Al iniciar la aplicaci�n, se cargar�n autom�ticamente los datos de los Pok�mon dentro del rango especificado.
Navega a trav�s de la lista de Pok�mon en la interfaz gr�fica.
Haz clic en un Pok�mon para ver informaci�n detallada.

