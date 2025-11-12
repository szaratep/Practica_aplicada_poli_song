package co.edu.poli.negocio;

import co.edu.poli.datos.*;
import co.edu.poli.model.*;

/**
 * Clase de lógica de negocio para la gestión de carritos.
 * 
 * Esta clase NO realiza consultas SQL.
 * Toda la comunicación con la base de datos se hace a través
 * de carritoDAO y carritoItemDAO.
 */
public class carritoManager {

    private final carritoDAO carritoDao;
    private final carritoItemDAO itemDao;

    /**
     * Constructor que inicializa los DAOs necesarios.
     */
    public carritoManager() {
        this.carritoDao = new carritoDAO();
        this.itemDao = new carritoItemDAO();
    }

    /**
     * Crea un carrito nuevo en la base de datos.
     */
    public void crearCarrito(int idCarrito, int idUsuario) {
        carrito c = new carrito(idCarrito, idUsuario, null);
        carritoDao.createCarrito(c);
        System.out.println("carritoManager -> crearCarrito: Carrito creado para el usuario con ID " + idUsuario);
    }

    /**
     * Agrega un producto al carrito.
     */
    public void agregarItem(int idItem, int idCarrito, String tipo, int idProducto, int cantidad) {
        carritoItem item = new carritoItem(idItem, idCarrito, tipo, idProducto, cantidad);
        itemDao.createItem(item);
        System.out.println("carritoManager -> agregarItem: Producto agregado (" + tipo + " ID: " + idProducto + ")");
    }

    /**
     * Lee un carrito de la base de datos.
     */
    public void verCarrito(int idCarrito) {
        carrito c = carritoDao.readCarrito(idCarrito);
        if (c != null) {
            System.out.println("carritoManager -> verCarrito: Carrito encontrado (ID Usuario: " + c.getId_usuario() + ")");
        } else {
            System.out.println("carritoManager -> verCarrito: No se encontró el carrito con ID " + idCarrito);
        }
    }

    /**
     * Lee un item específico del carrito.
     */
    public void verItem(int idItem) {
        carritoItem item = itemDao.readItem(idItem);
        if (item != null) {
            System.out.println("carritoManager -> verItem: Item encontrado (tipo: " + item.getTipo_producto() + ")");
        } else {
            System.out.println("carritoManager -> verItem: No existe el item con ID " + idItem);
        }
    }

    /**
     * Actualiza un item existente del carrito.
     */
    public void actualizarItem(int idItem, int idCarrito, String tipo, int idProducto, int cantidad) {
        carritoItem item = new carritoItem(idItem, idCarrito, tipo, idProducto, cantidad);
        itemDao.updateItem(item);
        System.out.println("carritoManager -> actualizarItem: Item actualizado (ID: " + idItem + ")");
    }

    /**
     * Elimina un item del carrito.
     */
    public void eliminarItem(int idItem) {
        itemDao.deleteItem(idItem);
        System.out.println("carritoManager -> eliminarItem: Item eliminado (ID: " + idItem + ")");
    }

    /**
     * Elimina un carrito completo de la base de datos.
     */
    public void eliminarCarrito(int idCarrito) {
        carritoDao.deleteCarrito(idCarrito);
        System.out.println("carritoManager -> eliminarCarrito: Carrito eliminado (ID: " + idCarrito + ")");
    }
}
