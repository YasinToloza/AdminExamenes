package arreglo;

import clase.Carrera;
import java.io.*;
import java.util.ArrayList;

public class arregloCarrera {
	//  Atributo privado
	private ArrayList <Carrera> carrera;
	//  Constructor
	public arregloCarrera() {
		carrera = new ArrayList <Carrera> ();
		cargarCarrera();
	}
	//  Operaciones publicas
	public void adicionar(Carrera x) {
		carrera.add(x);
		grabarCarrera();
	}
	public int tamaño() {
		return carrera.size();
	}
	public Carrera obtener(int i) {
		return carrera.get(i);
	}
	public Carrera buscar(int codigoCarrera) {
		for (int i=0; i<tamaño(); i++)
			if (obtener(i).getCodigoCarrera() == codigoCarrera)
				return obtener(i);
		return null;
	}
	public void eliminar(Carrera x) {
		carrera.remove(x);
		grabarCarrera();
	}
	public void actualizarArchivo() {
		grabarCarrera();
	}
	
	//   Metodos de guardar y cargar carrera
	private void grabarCarrera() {
		try {
			PrintWriter pw;
			String linea;
			Carrera x;
			pw = new PrintWriter(new FileWriter("carreras.txt"));
			for (int i=0; i<tamaño(); i++) {
				x = obtener(i);
				linea = x.getCodigoCarrera() + ";" +
						x.getNombreCarrera() + ";" +
						x.getPuntosExamen();
				pw.println(linea);
			}
			pw.close();	
		}
		catch (Exception e) {
		}
	}
	private void cargarCarrera() {
		try {
			BufferedReader br;
			String linea;
			String[] s;
			int codigoCarrera;
			String nombreCarrera, puntosExamen;
			br = new BufferedReader(new FileReader("carreras.txt"));
			while ((linea = br.readLine()) != null) {
				s = linea.split(";");
				codigoCarrera = Integer.parseInt(s[0].trim());
				nombreCarrera = s[1].trim();
				puntosExamen = s[2].trim();
				adicionar(new Carrera(codigoCarrera, nombreCarrera,puntosExamen));
			}
			br.close();
		}
		catch (Exception e) {
		}
	}
	
	//  Metodo auto-incrementado
	public int codigoCorrelativo() {
		if (tamaño() == 0)
			return 100001;
		else
			return obtener(tamaño()-1).getCodigoCarrera() + 1;
	}
	
}
