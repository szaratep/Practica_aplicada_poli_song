package co.edu.poli.view;

import co.edu.poli.datos.*;
import co.edu.poli.model.*;

public class Main {
	public static void main(String[] args) {
		// 1️⃣ Crear correo (si no existe)
        correoDAO cdao = new correoDAO();
        cdao.createCorreo(new correo("nuevoadmin@poli.edu")); // para probar cambio de correo
        
        administradorDAO adao = new administradorDAO();
        // 3️⃣ Actualizar datos del admin (cambiar nombre y correo)
        administrador adminUpdate = new administrador(1, "Sebastian Actualizado", "nuevoadmin@poli.edu", "abcd1234");
        adao.updateAdmin(adminUpdate);

        // 4️⃣ Leer para verificar cambios
        administrador adminResult = adao.readAdmin(1);
        System.out.println("📌 Datos del admin actualizado:");
        System.out.println("ID: " + adminResult.getId_admin());
        System.out.println("Nombre: " + adminResult.getNombre());
        System.out.println("Correo: " + adminResult.getCorreo());
        System.out.println("Contraseña: " + adminResult.getContrasena());
		 
	}
}
