package clase;

public class Examen {
//	Atributos privados
	private int codigoExamen,codigoPostulante;
	private String fechaExamen, horaExamen,codigoCarrera;

	public Examen(int codigoExamen, int codigoPostulante, String codigoCarrera, String fechaExamen, String horaExamen) {
	
		this.codigoExamen = codigoExamen;
		this.codigoPostulante = codigoPostulante;
		this.fechaExamen = fechaExamen;
		this.horaExamen = horaExamen;
		this.codigoCarrera = codigoCarrera;
	}

	public int getCodigoExamen() {
		return codigoExamen;
	}

	public void setCodigoExamen(int codigoExamen) {
		this.codigoExamen = codigoExamen;
	}

	public int getCodigoPostulante() {
		return codigoPostulante;
	}

	public void setCodigoPostulante(int codigoPostulante) {
		this.codigoPostulante = codigoPostulante;
	}

	public String getFechaExamen() {
		return fechaExamen;
	}

	public void setFechaExamen(String fechaExamen) {
		this.fechaExamen = fechaExamen;
	}

	public String getHoraExamen() {
		return horaExamen;
	}

	public void setHoraExamen(String horaExamen) {
		this.horaExamen = horaExamen;
	}

	public String getCodigoCarrera() {
		return codigoCarrera;
	}

	public void setCodigoCarrera(String codigoCarrera) {
		this.codigoCarrera = codigoCarrera;
	}
	
}