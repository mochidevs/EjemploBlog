package control;

import java.util.Map;
import java.util.TreeMap;
import logica.Blog;

public class Controladora {
	private Map<Integer, Blog> blogs;
	
	public Controladora() {
		blogs = new TreeMap<Integer, Blog>();
	}
	
	//BLOG
	
	public void crearBlog(String nombre, String descripcion) {
		Blog b = new Blog(nombre, descripcion);
		blogs.put(b.getCodigo(), b);	//añade código con su blog
	}
	
	public void borrarBlog(int codigoBlog) throws Exception {
		revisarBlogExistente(codigoBlog);
		blogs.remove(codigoBlog);
	}
	
	//lista con código y nombre de blogs
	public Map<Integer, String> obtenerBlogs() {
		Map<Integer, String> resultado = new TreeMap<Integer, String>();
		for (Blog b : blogs.values()) {
			resultado.put(b.getCodigo(), b.getNombre());
		}
		return resultado;
	}
	
	//PUBLICACIÓN
	
	public void crearPublicacion(int codigoBlog, String titulo, String texto, String autor) throws Exception {
		
		revisarBlogExistente(codigoBlog);
		Blog b = blogs.get(codigoBlog);
		b.crearPublicacion(titulo, texto, autor);
	}
	
	// mapa con código y titulo de cada publicacion
	public Map<Integer,String> obtenerPublicaciones(int codigoBlog) throws Exception {
		revisarBlogExistente(codigoBlog);
		Blog b = blogs.get(codigoBlog);
		return b.obtenerTitulosPublicaciones();
	}
	
	//texto de publicacion
	public String obtenerPublicacion(int codigoBlog, int codigoPublicacion) throws Exception {
		revisarBlogExistente(codigoBlog);
		Blog b = blogs.get(codigoBlog);
		return b.obtenerPublicacion(codigoPublicacion);
	}
	
	//COMENTARIO
	
	
	
	
	//verificar el código con método parecido al de publicacion
	private void revisarBlogExistente(int codigoBlog) throws Exception {
		if (!blogs.containsKey(codigoBlog))
			throw new Exception("El código de blog no es válido.");
	}
}
