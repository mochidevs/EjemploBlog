package interfaz;

import control.Controladora;
import java.util.Map;
import java.util.Scanner;

public class EjemploBlog {
	
	private static Controladora controladora;	//logica
	private static Scanner scan = new Scanner(System.in);
	private static int blogActual = -1;	//codigo blog actual
	private static int publicacionActual = -1;  //codigo publicacion actual
	
	private static void datosIniciales() throws Exception {
		//Blog 1 de recetas
		controladora.crearBlog("Recetas Caseras", "Cocina sencilla para el día a día");
		int blog1 = 1;
		
		controladora.crearPublicacion(blog1, "Gallo Pinto Sueltito", "Sofríe ajo y cebolla, agrega frijoles negros cocidos con su caldo, "
                + "mezcla arroz blanco del día anterior y cocina a fuego medio. "
                + "Añade culantro picado y Salsa Lizano al gusto.", 
                "Doña Marta");
		
		controladora.crearPublicacion(blog1, "Arroz con Leche Rápido",
                "Cocina arroz en leche con canela en raja y azúcar. "
                        + "Cuando espese apaga y sirve frío. "
                        + "Espolvorea canela molida encima.",
                "Chef Rodrigo");
		//comentarios Blog 1, publicaciones 1 y 2
		 controladora.agregarComentario(blog1, 1,
	                "ana@mail.com", "192.168.1.10",
	                "¡Me quedó igualito que el de mi abuela! La Salsa Lizano hace la diferencia.");
	 
	        controladora.agregarComentario(blog1, 1,
	                "carlos@mail.com", "10.0.0.5",
	                "Yo le agrego chile dulce y queda aún mejor. Gracias por la receta.");
	 
	        controladora.agregarComentario(blog1, 2,
	                "sofia@mail.com", "172.16.0.3",
	                "Perfecto para el frío de diciembre. Lo hice en 20 minutos.");
	 
	        controladora.agregarComentario(blog1, 2,
	                "mario@mail.com", "192.168.2.20",
	                "¿Se puede hacer con leche de almendra? Soy intolerante a la lactosa.");
	        
	        //Blog 2 de postres
	        controladora.crearBlog("Postres y Antojos", "Dulces para cualquier ocasión");
	        int blog2 = 2;
	        
	        controladora.crearPublicacion(blog2,
	                "Tres Leches Clásico",
	                "Hornea un bizcocho esponjoso, mezcla leche condensada, evaporada y crema, "
	                        + "perfora el bizcocho caliente y bañalo con la mezcla. "
	                        + "Refrigera 4 horas mínimo y cubre con merengue.",
	                "Repostera Laura");
	 
	        controladora.crearPublicacion(blog2,
	                "Brownies de Chocolate Oscuro",
	                "Derrite 200g de chocolate 70% con mantequilla. "
	                        + "Bate 3 huevos con azúcar hasta que espumen, "
	                        + "une todo con harina tamizada y hornea 25 min a 180°C. "
	                        + "Deben quedar húmedos por dentro.",
	                "Chef Rodrigo");
		
	        //comentarios blog 2, publicaciones 3 y 4
	        controladora.agregarComentario(blog2, 3,
	                "lupe@mail.com", "10.10.0.1",
	                "Lo hice para el cumpleaños de mi mamá y todos quedaron felices. ¡Gracias!");
	 
	        controladora.agregarComentario(blog2, 3,
	                "andres@mail.com", "192.168.5.5",
	                "¿Cuánto tiempo aguanta en el refri? Lo hice hoy para mañana.");
	 
	        controladora.agregarComentario(blog2, 4,
	                "valentina@mail.com", "172.20.1.8",
	                "Yo le pongo nueces encima antes de hornear y quedan riquísimos.");
	 
	        controladora.agregarComentario(blog2, 4,
	                "jose@mail.com", "10.0.1.15",
	                "Primera vez que me quedan bien los brownies, las instrucciones están muy claras. Por fin sé cocinar.");
	        
	}
	
	
	
	public static void menuPrincipal() {
		int opcion = 0;
		
		do {
			System.out.println("\n\tMenú Principal\t");
			System.out.println("Administración de Blogs");
			System.out.println("1. Crear blog nuevo");
			System.out.println("2. Trabajar con blog");
            System.out.println("3. Borrar blog");
            System.out.println("4. Salir");
            System.out.print("Opción >> ");
            
            try {
				opcion = Integer.parseInt(scan.nextLine()); //lanza NumberFormatException, nextInt() no lee enter
			} catch (NumberFormatException error) {
				System.out.println("Ingrese un número válido: ");
				opcion = 0;
			}
            
            switch (opcion) {
            	case 1 -> crearBlog();
            	case 2 -> menuBlogs();
            	case 3 -> borrarBlog();
            	case 4 -> System.out.println("Saliendo...");
            	default -> System.out.println("");
            }
            
		} while (opcion != 4);
	}
	
	
	private static void crearBlog() {
		System.out.println("\n--- Crear Blog Nuevo ---");
		 
        System.out.print("Nombre del blog: ");
        String nombre = scan.nextLine().trim();	//para limpiar input
 
        System.out.print("Descripción: ");
        String descripcion = scan.nextLine().trim();
 
        // Valida que no esten vacíos
        if (nombre.isEmpty() || descripcion.isEmpty()) {
            System.out.println("El nombre y la descripción no pueden estar vacíos.");
            return;
        }
        controladora.crearBlog(nombre, descripcion);
        System.out.println("Blog creado correctamente.");
	}
	
	private static void borrarBlog() {
		System.out.println("\n--- Borrar Blog ---");
        mostrarListaBlogs();
 
        System.out.print("Código del blog para borrar: ");
        try {
            int codigo = Integer.parseInt(scan.nextLine());
            controladora.borrarBlog(codigo);
            
            if (blogActual == codigo) {		//limpia el seleccionado
                blogActual = -1;
                publicacionActual = -1;
            }
            System.out.println("Blog borrado.");
     
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
	}
	
	
	public static void menuBlogs() {
		//lista para seleccionar uno
		 mostrarListaBlogs();
		 
	     Map<Integer, String> blogs = controladora.obtenerBlogs();
	     if (blogs.isEmpty()) {
	    	 System.out.println("No hay blogs disponibles, cree uno.");
	         return;
	     }
	 
	     System.out.print("Seleccione el código del blog con el que va a trabajar: ");
	     
	     try {
            blogActual = Integer.parseInt(scan.nextLine());
            // Validar que el código existe
            if (!blogs.containsKey(blogActual)) {
                System.out.println("Código de blog no válido.");
                blogActual = -1;
                return;
            }
         } catch (NumberFormatException e) {
            System.out.println("Ingrese un número válido.");
            return;
         }
	     
	     //menú del blog elegido
	     int opcion = 0;
	     do {
            System.out.println("\n--- Blog: " + blogs.get(blogActual) + " ---");
            System.out.println("1. Crear publicación");
            System.out.println("2. Ver publicación y comentarios");
            System.out.println("3. Regresar");
            System.out.print("Opción >> ");
 
            try {
                opcion = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
                opcion = 0;
            }
            
            switch (opcion) {
            	case 1 -> crearPublicacion();
            	case 2 -> menuPublicaciones();
            	case 3 -> System.out.println("Regresa al menú principal...");
            	default -> System.out.println("Opción inválida.");
            }
            
	     } while (opcion != 3);
}		
	
	private static void crearPublicacion() {
		System.out.println("\n--- Crear Publicación ---");
		 
        System.out.print("Título: ");
        String titulo = scan.nextLine().trim();
 
        System.out.print("Autor: ");
        String autor = scan.nextLine().trim();
 
        System.out.print("Texto de la publicación: ");
        String texto = scan.nextLine().trim();
 
        if (titulo.isEmpty() || autor.isEmpty() || texto.isEmpty()) {
            System.out.println("Todos los campos deben de llenarse.");
            return;
        }
        
        try {
            controladora.crearPublicacion(blogActual, titulo, texto, autor);
            System.out.println("Publicación creada correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
	}
	
	
	//elige una publicacion actual
	public static void menuPublicaciones() {
		//lista publicaciones de blog actual
		Map<Integer, String> publicaciones;
        try {
            publicaciones = controladora.obtenerPublicaciones(blogActual);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
 
        if (publicaciones.isEmpty()) {
            System.out.println("El blog no tiene publicaciones todavía.");
            return;
        }
        
        //imprime uno a uno
        System.out.println("\n--- Publicaciones disponibles ---");
        for (Map.Entry<Integer, String> entrada : publicaciones.entrySet()) {
            System.out.println(entrada.getKey() + ". " + entrada.getValue());
        }
		
        System.out.print("Seleccione el número de la publicación: ");
        try {
            publicacionActual = Integer.parseInt(scan.nextLine());
            if (!publicaciones.containsKey(publicacionActual)) {
                System.out.println("Número de publicación no válido.");
                publicacionActual = -1;
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Ingrese un número válido.");
            return;
        }
        
        //menú de la publicación que elige
        int opcion = 0;
        do {
        	//publicación con comentarios
        	try {
        		String contenido = controladora.obtenerPublicacion(blogActual, publicacionActual);
                System.out.println("\n----------------------------------------");
                System.out.println(contenido);
                System.out.println("----------------------------------------");
        		
        	} catch (Exception e) {
        		System.out.println("Error: " + e.getMessage());
        		return;
        	}
        	
        	//opciones
        	System.out.println("Opciones:");
            System.out.println("1. Crear comentario");
            System.out.println("2. Borrar comentario");
            System.out.println("3. Regresar");
            System.out.print("Opción >> ");
        	
        } while (opcion != 3);
        
	}
	
	private static void agregarComentario() {
		System.out.println("\n--- Nuevo Comentario ---");
		 
        System.out.print("Email: ");
        String email = scan.nextLine().trim();
 
        System.out.print("Dirección IP: ");
        String ip = scan.nextLine().trim();
 
        System.out.print("Comentario: ");
        String texto = scan.nextLine().trim();
 
        if (email.isEmpty() || ip.isEmpty() || texto.isEmpty()) {
            System.out.println("Todos los campos se deben rellenar.");
            return;
        }
        
        try {
            controladora.agregarComentario(blogActual, publicacionActual, email, ip, texto);
            System.out.println("Comentario agregado.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
	}
	
	private static void borrarComentario() {
		System.out.println("\n--- Borrar Comentario ---");
        System.out.println("Ingrese la posición del comentario (la primera es 0): ");
        System.out.print("Posición >> ");
 
        try {
            int posicion = Integer.parseInt(scan.nextLine());
            controladora.borrarComentario(blogActual, publicacionActual, posicion);
            System.out.println("Comentario borrado.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
	}
	
	//lista con código y nombre
	private static void mostrarListaBlogs() {
		Map<Integer, String> blogs = controladora.obtenerBlogs();
		if (blogs.isEmpty()) {
            System.out.println("No hay blogs registrados.");
        } else {
            System.out.println("\n--- Blogs disponibles ---");
            for (Map.Entry<Integer, String> entrada : blogs.entrySet()) {
                System.out.println(entrada.getKey() + ". " + entrada.getValue());
            }
        }
	}
	
	public static void main(String[] args) {
		
		controladora = new Controladora();
		
		try {
			datosIniciales();
		} catch (Exception e) {
            System.out.println("Error cargando datos iniciales: " + e.getMessage());
		}	
		
		menuPrincipal();
	}
}
