package co.edu.poli.view;

import co.edu.poli.negocio.*;

public class Main {
    public static void main(String[] args) {
    	carritoManager manager = new carritoManager();

        int idCarrito = 1;
        int idUsuario = 1;

        System.out.println("===== PRUEBA CARRITO MANAGER =====");

        // Crear carrito
        manager.crearCarrito(idCarrito, idUsuario);

        // Agregar productos
        manager.agregarItem(1, idCarrito, "cancion", 1, 2);
        manager.agregarItem(2, idCarrito, "vinilo", 1, 1);

        // Ver carrito e items
        manager.verCarrito(idCarrito);
        manager.verItem(1);
        manager.verItem(2);

        // Actualizar item
        manager.actualizarItem(1, idCarrito, "cancion", 1, 3);

        // Eliminar item
        manager.eliminarItem(2);

        // Eliminar carrito completo
        manager.eliminarCarrito(idCarrito);

        System.out.println("===== FIN PRUEBA CARRITO MANAGER =====");
    }
}

