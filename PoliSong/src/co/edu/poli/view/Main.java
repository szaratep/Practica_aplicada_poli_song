package co.edu.poli.view;

import co.edu.poli.datos.*;
import co.edu.poli.model.*;
import co.edu.poli.negocio.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== PRUEBA USUARIO MANAGER =====");

        usuarioManager manager = new usuarioManager();

        // 1️⃣ Registrar un nuevo usuario (crea el correo si no existe)
        System.out.println("\n--- REGISTRAR USUARIO ---");
        manager.registrarUsuario(10, "Juan Pérez", "juanperez@example.com", "1234");

        // 2️⃣ Consultar usuario por ID
        System.out.println("\n--- VER USUARIO ---");
        manager.verUsuario(10);

        // 3️⃣ Actualizar datos del usuario (crea nuevo correo si no existe)
        System.out.println("\n--- ACTUALIZAR USUARIO ---");
        manager.actualizarUsuario(10, "Juan Pérez Actualizado", "juanpereznuevo@example.com", "abcd");

        // 4️⃣ Listar todos los usuarios
        System.out.println("\n--- LISTAR USUARIOS ---");
        manager.listarUsuarios();

        // 5️⃣ Eliminar usuario
        System.out.println("\n--- ELIMINAR USUARIO ---");
        manager.eliminarUsuario(10);

        System.out.println("\n===== FIN PRUEBA USUARIO MANAGER =====");
    }
}

