package com.example.Examen;

import java.time.LocalDate;

public class Vuelo {
    private int id;
    private String origen;
    private String destino;
    private LocalDate fecha;
    private int duracion;

    // Constructor vacío
    public Vuelo() {}

    // Constructor con campos
    public Vuelo(int id, String origen, String destino, LocalDate fecha, int duracion) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.duracion = duracion;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }
    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public int getDuracion() { return duracion; }
    public void setDuracion(int duracion) { this.duracion = duracion; }
}