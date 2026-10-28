package com.mycompany.ud1a2_antoniomanuel.modelo.persistencia.MariaDB;

import com.mycompany.ud1a2_antoniomanuel.modelo.Cita;
import com.mycompany.ud1a2_antoniomanuel.modelo.persistencia.CitaDAO;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CitaDAOImpl implements CitaDAO {

    // Datos de conexión (3306 es el puerto por defecto de MariaDB)
    private static final String URL = "jdbc:mariadb://localhost:3306/aplicacioncitas";
    private static final String USER = "Antonio";
    private static final String PASS = "";
    private static final Logger LOGGER = Logger.getLogger(Cita.class.getName());

    // Abre una conexión nueva; lanza SQLException si falla
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    @Override
    public void create(Cita cita) {
        String sql = "INSERT INTO cita (nombre, apellido, medico, motivo, fecha, hora) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cita.getNombre());
            ps.setString(2, cita.getApellido());
            ps.setString(3, cita.getMedico());
            ps.setString(4, cita.getMotivo());
            ps.setString(5, cita.getFecha());
            ps.setString(6, cita.getHora());

            ps.executeUpdate();
            LOGGER.info("La fila se ha creado correctamente");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al crear la cita en la base de datos", e);
            JOptionPane.showMessageDialog(null,
                    "Error al conectar o guardar en la base de datos:\n" + e.getMessage(),
                    "Error Base de Datos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void update(Cita cita) {
        String sql = "UPDATE cita SET nombre=?, apellido=?, medico=?, motivo=?, fecha=?, hora=? WHERE idCita=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cita.getNombre());
            ps.setString(2, cita.getApellido());
            ps.setString(3, cita.getMedico());
            ps.setString(4, cita.getMotivo());
            ps.setString(5, cita.getFecha());
            ps.setString(6, cita.getHora());
            ps.setInt(7, cita.getIdCita());

            ps.executeUpdate();
            LOGGER.info("La fila se ha modificado correctamente");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al modificar la cita en la base de datos", e);
            JOptionPane.showMessageDialog(null,
                    "Error al conectar o actualizar en la base de datos:\n" + e.getMessage(),
                    "Error Base de Datos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void delete(int idCita) {
        String sql = "DELETE FROM cita WHERE idCita=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCita);
            ps.executeUpdate();
            LOGGER.info("La fila se ha eliminado correctamente");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar la cita en la base de datos", e);
            JOptionPane.showMessageDialog(null,
                    "Error al conectar o eliminar en la base de datos:\n" + e.getMessage(),
                    "Error Base de Datos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public List<Cita> readAll() {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM cita ORDER BY STR_TO_DATE(fecha, '%d/%m/%Y'), hora";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cita cita = new Cita();
                cita.setIdCita(rs.getInt("idCita"));
                cita.setNombre(rs.getString("nombre"));
                cita.setApellido(rs.getString("apellido"));
                cita.setMedico(rs.getString("medico"));
                cita.setMotivo(rs.getString("motivo"));
                cita.setFecha(rs.getString("fecha"));
                cita.setHora(rs.getString("hora"));
                lista.add(cita);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al consultar las citas en la base de datos", e);
            JOptionPane.showMessageDialog(null,
                    "Error al conectar o consultar en la base de datos:\n" + e.getMessage(),
                    "Error Base de Datos",
                    JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    @Override
    public Cita readByPatient(int idCita) {
        String sql = "SELECT * FROM cita WHERE idCita = ?";
        Cita cita = null;

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCita);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cita = new Cita();
                    cita.setIdCita(rs.getInt("idCita"));
                    cita.setNombre(rs.getString("nombre"));
                    cita.setApellido(rs.getString("apellido"));
                    cita.setMedico(rs.getString("medico"));
                    cita.setMotivo(rs.getString("motivo"));
                    cita.setFecha(rs.getString("fecha"));
                    cita.setHora(rs.getString("hora"));
                }
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al buscar la cita en la base de datos", e);
            JOptionPane.showMessageDialog(null,
                    "Error al conectar o buscar en la base de datos:\n" + e.getMessage(),
                    "Error Base de Datos",
                    JOptionPane.ERROR_MESSAGE);
        }

        return cita;
    }
}
