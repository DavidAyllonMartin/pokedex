package org.ielena.pokedex.model;

import javafx.scene.image.Image;

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
    private Image sprite;
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

    public Pokemon(int id, String nombre, String tipo1, String tipo2, int altura, int peso, int HP, int ataque, int defensa, int ataqueEspecial, int defensaEspecial, int velocidad, Image imagen, Image sprite) {
        this(id, nombre, tipo1, tipo2, altura, peso, HP, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad);
        this.imagen = imagen;
        this.sprite = sprite;
    }


    //Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo1() {
        return tipo1;
    }

    public void setTipo1(String tipo1) {
        this.tipo1 = tipo1;
    }

    public String getTipo2() {
        return tipo2;
    }

    public void setTipo2(String tipo2) {
        this.tipo2 = tipo2;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
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

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
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


