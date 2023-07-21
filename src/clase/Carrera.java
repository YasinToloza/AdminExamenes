package clase;

public class Carrera {
//	Atributos privados
	private int codigoCarrera;
	private String puntosExamen, nombreCarrera;
	
	public Carrera(int codigoCarrera, String nombreCarrera, String puntosExamen) {
		this.codigoCarrera = codigoCarrera;
		this.puntosExamen = puntosExamen;
		this.nombreCarrera = nombreCarrera;
	}

	public int getCodigoCarrera() {
		return codigoCarrera;
	}

	public void setCodigoCarrera(int codigoCarrera) {
		this.codigoCarrera = codigoCarrera;
	}

	public String getPuntosExamen() {
		return puntosExamen;
	}

	public void setPuntosExamen(String puntosExamen) {
		this.puntosExamen = puntosExamen;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}
	
}
