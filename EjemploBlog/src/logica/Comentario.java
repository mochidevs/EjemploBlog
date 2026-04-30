package logica;

import java.time.LocalDateTime;

public class Comentario {
	private LocalDateTime fechaCreacion;
	private String email;
	private String direccionIp;
	private String texto;
	
	public Comentario(String email, String direccionIp, String texto) {
		this.email = email;
		this.direccionIp = direccionIp;
		this.texto = texto;
		fechaCreacion = LocalDateTime.now();
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public String getEmail() {
		return email;
	}

	public String getDireccionIp() {
		return direccionIp;
	}

	public String getTexto() {
		return texto;
	}
	
	public String toString() {
		String resultado = email + " - " + direccionIp + " - " + fechaCreacion.toString() + "\n";
		resultado += texto + "\n";
		return resultado;
	}

}
