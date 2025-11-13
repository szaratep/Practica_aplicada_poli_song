package co.edu.poli.negocio;

import co.edu.poli.datos.*;
import co.edu.poli.model.*;

/**
 * Lógica de negocio para la gestión de pedidos y sus detalles.
 * Esta clase utiliza los DAOs para comunicarse con la base de datos.
 */
public class pedidoManager {

    private final pedidoDAO pedidoDao;
    private final pedidoDetalleDAO detalleDao;

    /**
     * Constructor: inicializa los DAOs.
     */
    public pedidoManager() {
        this.pedidoDao = new pedidoDAO();
        this.detalleDao = new pedidoDetalleDAO();
    }

    /**
     * Crea un nuevo pedido en la base de datos.
     */
    public void crearPedido(int idPedido, int idUsuario, String estado) {
        pedido p = new pedido(idPedido, idUsuario, new java.sql.Date(System.currentTimeMillis()), estado);
        pedidoDao.createPedido(p);
        System.out.println("pedidoManager -> crearPedido: Pedido creado correctamente (ID Usuario: " + idUsuario + ")");
    }

    /**
     * Muestra un pedido y sus detalles por ID de usuario.
     */
    public void verPedidosUsuario(int idUsuario) {
        System.out.println("pedidoManager -> verPedidosUsuario: Buscando pedidos del usuario ID " + idUsuario);

        // No tienes un método que lea todos los pedidos por usuario en pedidoDAO,
        // así que se haría leyendo manualmente desde SQL o agregando uno.
        // Por ahora, suponemos que se consulta 1 pedido a la vez (por ID).
        System.out.println("⚠️ Este método requiere un readAllPedidosByUsuario() en pedidoDAO para listar todos.");
    }

    /**
     * Muestra todos los detalles de un pedido específico.
     */
    public void verDetallesPedido(int idPedido) {
        pedido ped = pedidoDao.readPedido(idPedido);

        if (ped == null) {
            System.out.println("pedidoManager -> verDetallesPedido: No se encontró el pedido con ID " + idPedido);
            return;
        }

        System.out.println("---- PEDIDO ID " + ped.getId_pedido() + " ----");
        System.out.println("Usuario ID: " + ped.getId_usuario());
        System.out.println("Fecha: " + ped.getFecha());
        System.out.println("Estado: " + ped.getEstado());
        System.out.println("Detalles:");

        // Si tienes el método readDetallesByPedido(), lo usamos:
        try (java.sql.Connection conn = DBConnection.getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM pedido_detalle WHERE id_pedido = ?")) {

            stmt.setInt(1, idPedido);
            java.sql.ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println(" • Detalle ID: " + rs.getInt("id_detalle")
                        + " | Tipo: " + rs.getString("tipo_Producto")
                        + " | Producto ID: " + rs.getInt("id_producto")
                        + " | Cantidad: " + rs.getInt("cantidad")
                        + " | Precio: $" + rs.getDouble("precio_unitario"));
            }

        } catch (Exception e) {
            System.out.println("pedidoManager -> verDetallesPedido: Error al leer detalles");
            System.out.println("Detalles: " + e.getMessage());
        }
    }

    /**
     * Confirma el envío del pedido (actualiza estado a 'Enviado').
     */
    public void confirmarEnvioPedido(int idPedido) {
        pedido p = pedidoDao.readPedido(idPedido);
        if (p == null) {
            System.out.println("pedidoManager -> confirmarEnvioPedido: No se encontró el pedido con ID " + idPedido);
            return;
        }

        p.setEstado("Enviado");
        pedidoDao.updatePedido(p);
        System.out.println("pedidoManager -> confirmarEnvioPedido: Pedido ID " + idPedido + " marcado como 'Enviado'");
    }

    /**
     * Confirma la recepción del pedido (actualiza estado a 'Recibido').
     */
    public void confirmarRecepcionPedido(int idPedido) {
        pedido p = pedidoDao.readPedido(idPedido);
        if (p == null) {
            System.out.println("pedidoManager -> confirmarRecepcionPedido: No se encontró el pedido con ID " + idPedido);
            return;
        }

        p.setEstado("Recibido");
        pedidoDao.updatePedido(p);
        System.out.println("pedidoManager -> confirmarRecepcionPedido: Pedido ID " + idPedido + " marcado como 'Recibido'");
    }

    /**
     * Elimina un pedido existente.
     */
    public void eliminarPedido(int idPedido) {
        pedido p = pedidoDao.readPedido(idPedido);
        if (p == null) {
            System.out.println("pedidoManager -> eliminarPedido: No existe el pedido con ID " + idPedido);
            return;
        }

        pedidoDao.deletePedido(idPedido);
        System.out.println("pedidoManager -> eliminarPedido: Pedido eliminado correctamente");
    
    }
}
