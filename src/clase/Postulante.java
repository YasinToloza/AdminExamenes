package clase;

public class Postulante {
//	Atributos privados
	private int codigoPostulante;
	private String nombres, apellidos, telefono, dni;
	
	public Postulante(int codigoPostulante, String nombres, String apellidos,String telefono, String dni) {
		this.codigoPostulante = codigoPostulante;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.dni = dni;
	}

	public int getCodigoPostulante() {
		return codigoPostulante;
	}

	public void setCodigoPostulante(int codigoPostulante) {
		this.codigoPostulante = codigoPostulante;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	
}
