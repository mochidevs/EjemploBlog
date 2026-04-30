package control;

import java.util.Map;
import java.util.TreeMap;
import logica.Blog;

public class Controladora {
	private Map<Integer, Blog> blogs;
	
	public Controladora() {
		blogs = new TreeMap<Integer, Blog>();
	}
	public void crearBlog(String nombre, String descripcion) {
		Blog b = new Blog(nombre, descripcion);
		blogs.put(b.getCodigo(), b);	//añade código con su blog
	}
	public Map<Integer, String> obtenerBlogs() {
		Map<Integer, String> resultado = new TreeMap<Integer, String>();
		for (Blog b : blogs.values()) {
			resultado.put(b.getCodigo(), b.getNombre());
		}
		return resultado;
	}
}
