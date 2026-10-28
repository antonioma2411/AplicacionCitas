/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.ud1a2_antoniomanuel.modelo.persistencia;

import com.mycompany.ud1a2_antoniomanuel.modelo.Cita;
import java.util.List; 

/**
 *
 * @author antonioma2411
 */
public interface CitaDAO {
    //Create
    void create (Cita cita);
    void update (Cita cita);
    void delete (int idCita);
    List<Cita> readAll();
    Cita readByPatient(int idCita);
    
}
