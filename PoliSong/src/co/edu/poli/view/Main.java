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

    	// Agregar productos válidos según tu BD
    	manager.agregarCancion(1, idCarrito, 5, 2); // Canción con ID 5
    	manager.agregarVinilo(2, idCarrito, 1, 1);  // Vinilo con ID 1
    	manager.agregarMP3(3, idCarrito, 1, 1);     // MP3 con ID 1

    	// Ver carrito e ítems
    	manager.verCarrito(idCarrito);
    	manager.verItem(1);
    	manager.verItem(2);
    	manager.verItem(3);

    	// Actualizar item (ejemplo)
    	manager.actualizarItem(1, idCarrito, "cancion", 5, null, null, 3);

    	// Eliminar item y carrito
    	manager.eliminarItem(2);
    	manager.eliminarCarrito(idCarrito);

    	System.out.println("===== FIN PRUEBA CARRITO MANAGER =====");

    }
}

