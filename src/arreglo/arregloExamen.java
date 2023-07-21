package arreglo;

import clase.Examen;

import java.io.*;
import java.util.ArrayList;

public class arregloExamen {
	//  Atributo privado
	private ArrayList <Examen> examen;
	//  Constructor
	public arregloExamen() {
		examen = new ArrayList <Examen> ();
		cargarExamen();
	}
	//  Operaciones publicas
	public void adicionar(Examen x) {
		examen.add(x);
		grabarExamen();
	}
	public int tamaño() {
		return examen.size();
	}
	public Examen obtener(int i) {
		return examen.get(i);
	}
	public Examen buscar(int codigoExamen) {
		for (int i=0; i<tamaño(); i++)
			if (obtener(i).getCodigoExamen() == codigoExamen)
				return obtener(i);
		return null;
	}
	public void eliminar(Examen x) {
		examen.remove(x);
		grabarExamen();
	}
	public void actualizarArchivo() {
		grabarExamen();
	}
	private void grabarExamen() {
		try {
			PrintWriter pw;
			String linea;
			Examen x;
			pw = new PrintWriter(new FileWriter("examenes.txt"));
			for (int i=0; i<tamaño(); i++) {
				x = obtener(i);
				linea = x.getCodigoExamen() + ";" +
						x.getCodigoPostulante() + ";" +
						x.getCodigoCarrera() + ";" +
						x.getFechaExamen() + ";" +
						x.getHoraExamen();
				pw.println(linea);
			}
			pw.close();	
		}
		catch (Exception e) {
		}
	}
	
	//  Metodos de guardar y cargar Examen
	private void cargarExamen() {
		try {
			BufferedReader br;
			String linea;
			String[] s;
			int codigoExamen, codigoPostulante;
			String fechaExamen, horaExamen, codigoCarrera;
			br = new BufferedReader(new FileReader("examenes.txt"));
			while ((linea = br.readLine()) != null) {
				s = linea.split(";");
				codigoExamen = Integer.parseInt(s[0].trim());
				codigoPostulante = Integer.parseInt(s[1].trim());
				codigoCarrera = s[2].trim();
				fechaExamen = s[3].trim();
				horaExamen = s[4].trim();
				adicionar(new Examen( codigoExamen, codigoPostulante, codigoCarrera, fechaExamen, horaExamen));
			}
			br.close();
		}
		catch (Exception e) {
		}
	}
	
	//  Metodo auto-incrementado
	public int codigoCorrelativo() {
		if (tamaño() == 0)
			return 790001;
		else
			return obtener(tamaño()-1).getCodigoExamen() + 1;
	}
}