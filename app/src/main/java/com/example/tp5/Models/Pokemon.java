package com.example.tp5.Models;


public class Pokemon {
    private int id;
    private String nombre;
    private String tipo;
    private String tipo2;
    private int ataque;
    private int defensa;
    private int resistencia;
    private String foto;

    public Pokemon(int id, String nombre, String tipo, String tipo2, int ataque, int defensa, int resistencia, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.tipo2 = tipo2;
        this.ataque = ataque;
        this.defensa = defensa;
        this.resistencia = resistencia;
        this.foto = foto;
    }

    public Pokemon(){

    }

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo2() {
        return tipo2;
    }

    public void setTipo2(String tipo2) {
        this.tipo2 = tipo2;
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

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
