package arreglo;

import clase.Postulante;
import java.io.*;
import java.util.ArrayList;

public class arregloPostulante {
	//  Atributo privado
	private ArrayList <Postulante> postulante;
	//  Constructor
	public arregloPostulante() {
		postulante = new ArrayList <Postulante> ();
		cargarPostulante();
	}
	//  Operaciones publicas
	public void adicionar(Postulante x) {
		postulante.add(x);
		grabarPostulante();
	}
	public int tamaño() {
		return postulante.size();
	}
	public Postulante obtener(int i) {
		return postulante.get(i);
	}
	public Postulante buscar(int codigoPostulante) {
		for (int i=0; i<tamaño(); i++)
			if (obtener(i).getCodigoPostulante() == codigoPostulante)
				return obtener(i);
		return null;
	}
	public void eliminar(Postulante x) {
		postulante.remove(x);
		grabarPostulante();
	}
	public void actualizarArchivo() {
		grabarPostulante();
	}
	
	//  Metodos de guardar y cargar Postulante
	private void grabarPostulante() {
		try {
			PrintWriter pw;
			String linea;
			Postulante x;
			pw = new PrintWriter(new FileWriter("postulantes.txt"));
			for (int i=0; i<tamaño(); i++) {
				x = obtener(i);
				linea = x.getCodigoPostulante() + ";" +
						x.getNombres() + ";" +
						x.getApellidos() + ";" +
						x.getTelefono() + ";" +
						x.getDni();
				pw.println(linea);
			}
			pw.close();	
		}
		catch (Exception e) {
		}
	}	
	private void cargarPostulante() {
		try {
			BufferedReader br;
			String linea;
			String[] s;
			int codigoPostulante;
			String nombres, apellidos, telefono, dni;
			br = new BufferedReader(new FileReader("postulantes.txt"));
			while ((linea = br.readLine()) != null) {
				s = linea.split(";");
				codigoPostulante = Integer.parseInt(s[0].trim());
				nombres = s[1].trim();
				apellidos = s[2].trim();
				telefono = s[3].trim();
				dni = s[4].trim();
				adicionar(new Postulante(codigoPostulante, nombres, apellidos, telefono, dni));
			}
			br.close();
		}
		catch (Exception e) {
		}
	}

	//  Metodo auto-incrementado
	public int codigoCorrelativo() {
		if (tamaño() == 0)
			return 2021001;
		else
			return obtener(tamaño()-1).getCodigoPostulante() + 1;
	}
}
