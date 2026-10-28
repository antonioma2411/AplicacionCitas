/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ud1a2_antoniomanuel.modelo;

/**
 *
 * @author antonioma2411
 */
public class Cita {
    int idCita;
    String nombre;
    String apellido;
    String medico;
    String motivo;
    String fecha;
    String hora;
    // Constructor vacío
    public Cita() {
    }

    // Constructor con parámetros (opcional, muy útil para instanciar rápido)
    public Cita(String nombre, String apellido, String medico, String motivo, String fecha, String hora) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.medico = medico;
        this.motivo = motivo;
        this.fecha = fecha;
        this.hora = hora;
    }

    @Override
    public String toString() {
       return this.nombre + " " + this.apellido;
    }
    
    

    // --- GETTERS Y SETTERS ---

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }
}
