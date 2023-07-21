package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;


import arreglo.arregloCarrera;
import arreglo.arregloExamen;
import arreglo.arregloPostulante;
import clase.Carrera;
import clase.Examen;
import clase.Postulante;
import libreria.Fecha;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class guiExamen extends JDialog implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCodigoExamen;
	private JTextField txtCodigoPostulante;
	private JTextField txtCodigoCarrera;
	private JTable tblExamen;
	private JButton btnAceptar;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private DefaultTableModel modelo;
	private JComboBox<String> cboCodigoPostulante;
	private JComboBox<String> cboCodigoCarrera;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			guiExamen dialog = new guiExamen();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public guiExamen() {
		setTitle("Inscripci\u00F3n a examen");
		setBounds(100, 100, 550, 400);
		getContentPane().setLayout(null);
		setModal(true);
		setResizable(false);
		
		JLabel lblCodigoExamen = new JLabel("C\u00F3digo");
		lblCodigoExamen.setBounds(10, 15, 46, 14);
		getContentPane().add(lblCodigoExamen);
		
		JLabel lblPostulante = new JLabel("Postulante");
		lblPostulante.setBounds(10, 62, 72, 14);
		getContentPane().add(lblPostulante);
		
		JLabel lblCarrera = new JLabel("Carrera");
		lblCarrera.setBounds(10, 108, 59, 14);
		getContentPane().add(lblCarrera);
		
		txtCodigoExamen = new JTextField();
		txtCodigoExamen.setBounds(79, 8, 98, 29);
		getContentPane().add(txtCodigoExamen);
		txtCodigoExamen.setColumns(10);
		
		txtCodigoPostulante = new JTextField();
		txtCodigoPostulante.setColumns(10);
		txtCodigoPostulante.setBounds(79, 55, 98, 29);
		getContentPane().add(txtCodigoPostulante);
		
		txtCodigoCarrera = new JTextField();
		txtCodigoCarrera.setBounds(79, 101, 98, 29);
		getContentPane().add(txtCodigoCarrera);
		txtCodigoCarrera.setColumns(10);
		
		cboCodigoPostulante = new JComboBox<String>();
		cboCodigoPostulante.addActionListener(this);
		cboCodigoPostulante.setBounds(197, 55, 89, 29);
		getContentPane().add(cboCodigoPostulante);
		colocarCodigosPostulantes();
		
		cboCodigoCarrera = new JComboBox<String>();
		cboCodigoCarrera.addActionListener(this);
		cboCodigoCarrera.setBounds(197, 101, 89, 29);
		getContentPane().add(cboCodigoCarrera);
		colocarCodigosCarreras();
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setBounds(197, 11, 89, 23);
		getContentPane().add(btnAceptar);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(422, 11, 89, 23);
		getContentPane().add(btnAdicionar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(422, 58, 89, 23);
		getContentPane().add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(422, 104, 89, 23);
		getContentPane().add(btnEliminar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 146, 501, 204);
		getContentPane().add(scrollPane);
		
		tblExamen = new JTable();
		tblExamen.setFillsViewportHeight(true);
		tblExamen.addMouseListener(this);
		scrollPane.setViewportView(tblExamen);
		modelo = new DefaultTableModel();
		modelo.addColumn("C�digo");
		modelo.addColumn("Postulante");
		modelo.addColumn("Carrera");
		modelo.addColumn("Fecha de inscripci�n");
		modelo.addColumn("Hora de inscripci�n");
		
		tblExamen.setModel(modelo);
		
		txtCodigoExamen.setEditable(false);
		txtCodigoPostulante.setEditable(false);
		txtCodigoCarrera.setEditable(false);
		habilitarTexto(false);
		listar();
		obtenerValores();

	}
	
//  Declaracion global
	arregloExamen ae = new arregloExamen();

	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnAceptar) {
			actionPerformedBtnAceptar(arg0);
		}
		if (arg0.getSource() == cboCodigoPostulante) {
			actionPerformedCboCodigoPostulante(arg0);
		}
		if (arg0.getSource() == cboCodigoCarrera) {
			actionPerformedCboCodigoCarrera(arg0);
		}
		if (arg0.getSource() == btnAdicionar) {
			actionPerformedBtnAdicionar(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificar(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
			
		}}
	//BOTONES
			protected void actionPerformedBtnAceptar(ActionEvent arg0) {
				int codigoExamen = leerCodigoExamen();
				int codigoPostulante = leerCodigoPostulante();
				String codigoCarrera = leerCodigoCarrera();
				if (btnAdicionar.isEnabled() == false) {
					Examen nuevo = new Examen(codigoExamen, codigoPostulante, codigoCarrera, fechaActual(),horaActual());
					ae.adicionar(nuevo);
					btnAdicionar.setEnabled(true);
					cboCodigoPostulante.removeItem(cboCodigoPostulante.getSelectedItem());
				}
				if (btnModificar.isEnabled() == false) {
					Examen c = ae.buscar(codigoExamen);
					c.setCodigoCarrera(codigoCarrera);
					c.setCodigoPostulante(codigoPostulante);
	
					btnModificar.setEnabled(true);
				}
				listar();
				habilitarTexto(false);
				obtenerValores();
			};
			protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
				if (cboCodigoPostulante.getSelectedIndex() < 0)
					mensaje("No existen postulantes");
				else {
					btnAdicionar.setEnabled(false);
					btnModificar.setEnabled(true);
					btnAceptar.setEnabled(true);
					limpieza();
					habilitarTexto(true);
					txtCodigoCarrera.setText("" + cboCodigoCarrera.getSelectedItem());
					txtCodigoPostulante.setText("" + cboCodigoPostulante.getSelectedItem());
				}
			}
			protected void actionPerformedBtnModificar(ActionEvent arg0) {
				btnAdicionar.setEnabled(true);
				if (ae.tamaño() == 0) {
					btnAceptar.setEnabled(false);
					habilitarTexto(false);
					getToolkit().beep();
					mensaje("No existen inscripciones previas");	
				}
				else {
					obtenerValores();
					btnModificar.setEnabled(false);
					btnAceptar.setEnabled(true);
					habilitarTexto(true);
					
				}
			}
			protected void actionPerformedBtnEliminar(ActionEvent arg0) {
				btnAdicionar.setEnabled(true);
				btnModificar.setEnabled(true);
				btnAceptar.setEnabled(false);
				if (ae.tamaño() == 0){
					getToolkit().beep();
					mensaje("No existen carreras");
				}else {
					obtenerValores();
					habilitarTexto(false);
					int ok = confirmar("�Desea eliminar el registro?");
					if (ok == 0) {
						ae.eliminar(ae.buscar(leerCodigoExamen()));
						listar();
						obtenerValores();
					}
				}
			}
			protected void actionPerformedCboCodigoPostulante(ActionEvent arg0) {
				txtCodigoPostulante.setText("" + cboCodigoPostulante.getSelectedItem());
			}
			protected void actionPerformedCboCodigoCarrera(ActionEvent arg0) {
				txtCodigoCarrera.setText("" + cboCodigoCarrera.getSelectedItem());
			}
			
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getSource() == tblExamen) {
					mouseClickedTblExamen(arg0);
				}
			}
			protected void mouseClickedTblExamen(MouseEvent arg0) {
				habilitarTexto(false);
				habilitarBotones(true);
				obtenerValores();
			}
			
			
			
			
		void habilitarTexto(boolean yes) {
			btnAceptar.setEnabled(yes);;
		}
		
		
		void habilitarBotones(boolean yes) {
			btnAdicionar.setEnabled(yes);
			btnModificar.setEnabled(yes);
		}
		
		
		void obtenerValores() {
			if (ae.tamaño() == 0){
					txtCodigoCarrera.setText(""+ae.codigoCorrelativo());
			}else {
				Examen p = ae.obtener(tblExamen.getSelectedRow());
				txtCodigoCarrera.setText("" + p.getCodigoCarrera());
				txtCodigoExamen.setText(""+p.getCodigoExamen());
				txtCodigoPostulante.setText(""+p.getCodigoPostulante());
			}
		}
		
		void limpieza() {
			txtCodigoExamen.setText("" + ae.codigoCorrelativo());
		}
		
			void listar() {
			int posFila = 0;
			if (modelo.getRowCount() > 0)
				posFila = tblExamen.getSelectedRow();
			if (modelo.getRowCount() == ae.tamaño() - 1)
				posFila = ae.tamaño() - 1;
			if (posFila == ae.tamaño())
				posFila --;
			modelo.setRowCount(0);
			Examen e;
			for (int i=0; i<ae.tamaño(); i++) {
				e = ae.obtener(i);
				Object[] fila = { e.getCodigoExamen(),
								  e.getCodigoPostulante(),
								  e.getCodigoCarrera(),
								  Fecha.enTextoFecha(e.getFechaExamen()),
								  e.getHoraExamen()};
				modelo.addRow(fila);
			}
			if (ae.tamaño() > 0)
				tblExamen.getSelectionModel().setSelectionInterval(posFila, posFila);
		}
			//  M�todos que retornan valor (sin parametros)
			int leerCodigoExamen() {
				return Integer.parseInt(txtCodigoExamen.getText().trim());
			}
			String leerCodigoCarrera() {
				return cboCodigoCarrera.getSelectedItem().toString();
			}
			int leerCodigoPostulante() {
				return Integer.parseInt(cboCodigoPostulante.getSelectedItem().toString());
			}
			String fechaActual() {
				int dd, mm, aa;
				Calendar c = new GregorianCalendar();
				dd = c.get(Calendar.DAY_OF_MONTH);
				mm = c.get(Calendar.MONTH) + 1;
				aa = c.get(Calendar.YEAR);
				return Fecha.ajustar(dd) + "/" + Fecha.ajustar(mm) + "/" + aa;
			}
			String horaActual() {
				int hh, mm, ss;
				Calendar c = new GregorianCalendar();
				hh = c.get(Calendar.HOUR_OF_DAY);
				mm = c.get(Calendar.MINUTE);
				ss = c.get(Calendar.SECOND);
				return Fecha.ajustar(hh) + ":" + Fecha.ajustar(mm) + ":" + Fecha.ajustar(ss);
			}
			
			void mensaje(String s) {
				JOptionPane.showMessageDialog(this, s, "Informaci�n", 0);
			}
			void error(String s, JTextField txt) {
				mensaje(s);
				txt.setText("");
				txt.requestFocus();
			}

			void colocarCodigosPostulantes() {
				arregloPostulante ap = new arregloPostulante();
				Postulante p;
				for (int i=0; i<ap.tamaño(); i++) {
					p = ap.obtener(i);
						cboCodigoPostulante.addItem("" + p.getCodigoPostulante());
				}
			}
			
			void colocarCodigosCarreras() {
				arregloCarrera ac = new arregloCarrera();
				Carrera c;
				for (int i=0; i<ac.tamaño(); i++) {
					c = ac.obtener(i);
						cboCodigoCarrera.addItem("" + c.getNombreCarrera());
				}
			}
			
			
			//  Metodos que retornan valor (con parametros)
			int confirmar(String s) {
				return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
			}
			
			//Otros motodos

			public void mouseEntered(java.awt.event.MouseEvent arg0) {
			}

			public void mouseExited(java.awt.event.MouseEvent arg0) {
			}

			public void mousePressed(java.awt.event.MouseEvent arg0) {
			}

			public void mouseReleased(java.awt.event.MouseEvent arg0) {
			}
			

}
