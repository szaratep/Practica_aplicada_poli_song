package co.edu.poli.datos;

import co.edu.poli.model.administrador;
import java.sql.*;

public class administradorDAO {

    public administradorDAO() {}

    /**
     * Crear administrador con FK correo_id
     */
    public void createAdmin(administrador admin) {

        String findCorreoSQL = "SELECT id_correo FROM correo WHERE correo = ?";
        String insertAdminSQL = "INSERT INTO administrador (id_admin, nombre, correo_id, contrasena) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {

            // Buscar id_correo
            PreparedStatement findStmt = conn.prepareStatement(findCorreoSQL);
            findStmt.setString(1, admin.getCorreo());
            ResultSet rs = findStmt.executeQuery();

            Integer idCorreo = null;
            if (rs.next()) {
                idCorreo = rs.getInt("id_correo");
            } else {
                System.out.println("[404] Correo NO existe, créalo antes ⚠");
                return;
            }

            // Insertar administrador
            PreparedStatement insertStmt = conn.prepareStatement(insertAdminSQL);
            insertStmt.setInt(1, admin.getId_admin());
            insertStmt.setString(2, admin.getNombre());
            insertStmt.setInt(3, idCorreo);
            insertStmt.setString(4, admin.getContrasena());

            insertStmt.executeUpdate();
            System.out.println("[201 CREATED] Administrador creado ✅");

        } catch (SQLException e) {
            System.out.println("[500 ERROR] administradorDAO -> createAdmin(): ❌");
            System.out.println("Detalles: " + e.getMessage());
        }
    }

    /**
     * Leer administrador con JOIN al correo
     */
    public administrador readAdmin(int id) {

        String sql = """
            SELECT a.id_admin, a.nombre, a.contrasena, c.correo
            FROM administrador a
            LEFT JOIN correo c ON a.correo_id = c.id_correo
            WHERE a.id_admin = ?
        """;

        administrador admin = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                admin = new administrador(
                        rs.getInt("id_admin"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("contrasena")
                );
                System.out.println("[200 OK] Administrador encontrado ✅");
            } else {
                System.out.println("[404 NOT FOUND] Administrador no existe ⚠");
            }

        } catch (SQLException e) {
            System.out.println("[500 ERROR] administradorDAO -> readAdmin(): ❌");
        }

        return admin;
    }

    /**
     * Actualizar administrador
     */
    public void updateAdmin(administrador admin) {

        String findCorreoSQL = "SELECT id_correo FROM correo WHERE correo = ?";
        String updateSQL = "UPDATE administrador SET nombre = ?, correo_id = ?, contrasena = ? WHERE id_admin = ?";

        try (Connection conn = DBConnection.getConnection()) {

            // Buscar id_correo
            PreparedStatement findStmt = conn.prepareStatement(findCorreoSQL);
            findStmt.setString(1, admin.getCorreo());
            ResultSet rs = findStmt.executeQuery();

            Integer idCorreo = null;
            if (rs.next()) {
                idCorreo = rs.getInt("id_correo");
            } else {
                System.out.println("[404] Correo NO existe, no se puede actualizar ⚠");
                return;
            }

            // Actualizar admin
            PreparedStatement stmt = conn.prepareStatement(updateSQL);
            stmt.setString(1, admin.getNombre());
            stmt.setInt(2, idCorreo);
            stmt.setString(3, admin.getContrasena());
            stmt.setInt(4, admin.getId_admin());

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("[200 OK] Administrador actualizado ✅");
            } else {
                System.out.println("[404 NOT FOUND] No existe administrador ⚠");
            }

        } catch (SQLException e) {
            System.out.println("[500 ERROR] administradorDAO -> updateAdmin(): ❌");
            System.out.println("Detalles: " + e.getMessage());
        }
    }

    /**
     * Eliminar administrador
     */
    public void deleteAdmin(int id) {
        String sql = "DELETE FROM administrador WHERE id_admin = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("[200 OK] Administrador eliminado ✅");
            } else {
                System.out.println("[404 NOT FOUND] No existe administrador ⚠");
            }

        } catch (SQLException e) {
            System.out.println("[500 ERROR] administradorDAO -> deleteAdmin(): ❌");
            System.out.println("Detalles: " + e.getMessage());
        }
    }
}
