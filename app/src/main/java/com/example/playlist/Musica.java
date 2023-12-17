package com.example.playlist;

public class Musica {

    int id;
    String titulo;
    String autor;
    int image;
    int audio;
    int posicionActual;

    public Musica(int id, String titulo, String autor, int image, int audio) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.image = image;
        this.audio = audio;
        this.posicionActual = 0;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public int getImage() { return image; }
    public void setImage(int image) { this.image = image; }
    public int getAudio() { return audio; }
    public void setAudio(int audio) { this.audio = audio; }

    public int getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(int posicionActual) {
        this.posicionActual = posicionActual;
    }
}
