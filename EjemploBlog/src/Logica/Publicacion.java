package Logica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Publicacion {
	private static int consecutivo = 1;
	private int codigo;
	private String titulo;
	private String texto;
	private String autor;
	private LocalDateTime fechaPublicacion;
	private List<Comentario> comentarios;
	
	public Publicacion(String titulo, String texto, String autor) {
		codigo = consecutivo;
		consecutivo++;
		this.titulo = titulo;
		this.texto = texto;
		this.autor = autor;
		fechaPublicacion = LocalDateTime.now();
		comentarios = new ArrayList<Comentario>();
	}
	
	public int getCodigo() {
		return codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getTexto() {
		return texto;
	}

	public String getAutor() {
		return autor;
	}

	public LocalDateTime getFechaPublicacion() {
		return fechaPublicacion;
	}
	
	public String toString() {
		String resultado = titulo + "\n\n";
		resultado += "Creado por: " + autor + " - ";
		resultado += fechaPublicacion.toString() + "\n\n";
		resultado += texto + "\n\n";
		resultado += "Comentarios: \n";
		if (comentarios.isEmpty()) {
			resultado += "No hay comentarios.";
		} else {
			for (Comentario c : comentarios) {
				resultado += c.toString() + "\n\n";
			}
		}
		return resultado;
	}

	public void agregarComentario(String email, String direccionIp, String texto) {
		Comentario c = new Comentario(email, direccionIp, texto);
		comentarios.add(c);
	}
	
	public void borrarComentario(int posicion) throws Exception {
		if (posicion < 0 || posicion >= comentarios.size())
			throw new Exception("Comentario no válido.");
		comentarios.remove(posicion);
	}
}
