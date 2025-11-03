package co.edu.poli.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import co.edu.poli.model.correo;

/**
 * DAO para la gestión de correos electrónicos en la base de datos.
 */
public class correoDAO {

    /**
     * Constructor por defecto
     */
    public correoDAO() {}

    /**
     * Crear correo
     * @param obj Correo a registrar
     */
    public void createCorreo(correo obj) {
        String sql = "INSERT INTO correo (correo) VALUES (?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, obj.getCorreo());
            ps.executeUpdate();

            System.out.println("[200 OK] correoDAO -> createCorreo(): Correo registrado ✅");

        } catch (SQLException e) {
            System.out.println("[500 ERROR] correoDAO -> createCorreo(): " + e.getMessage());
        }
    }

    /**
     * Leer un correo
     * @param correo Correo a buscar
     * @return Objeto correo si existe, null si no
     */
    public correo readCorreo(String correo) {

        String sql = "SELECT correo FROM correo WHERE correo = ?";
        correo resultado = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                resultado = new correo(rs.getString("correo"));
                System.out.println("[200 OK] correoDAO -> readCorreo(): Correo encontrado ✅");
            } else {
                System.out.println("[404 NOT FOUND] correoDAO -> readCorreo(): Correo no existe ❌");
            }

        } catch (SQLException e) {
            System.out.println("[500 ERROR] correoDAO -> readCorreo(): " + e.getMessage());
        }

        return resultado;
    }

    /**
     * Eliminar un correo
     * @param correo Correo a eliminar
     */
    public void deleteCorreo(String correo) {
        String sql = "DELETE FROM correo WHERE correo = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.executeUpdate();

            System.out.println("[200 OK] correoDAO -> deleteCorreo(): Correo eliminado ✅");

        } catch (SQLException e) {
            System.out.println("[500 ERROR] correoDAO -> deleteCorreo(): " + e.getMessage());
        }
    }
}
