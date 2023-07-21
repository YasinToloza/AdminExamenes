package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import clase.Carrera;
import arreglo.arregloCarrera;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class guiCarrera extends JDialog implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCodigo;
	private JTextField txtPuntos;
	private JTextField txtCarrera;
	private JTable tblCarrera;
	private DefaultTableModel modelo;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnAdicionar;
	private JButton btnAceptar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			guiCarrera dialog = new guiCarrera();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public guiCarrera() {
		setTitle("Carrera");
		setBounds(100, 100, 455, 320);
		getContentPane().setLayout(null);
		setModal(true);
		setResizable(false);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(10, 11, 46, 14);
		getContentPane().add(lblCdigo);
		
		JLabel lblNombreDeCarrera = new JLabel("Nombre de Carrera");
		lblNombreDeCarrera.setBounds(10, 70, 119, 14);
		getContentPane().add(lblNombreDeCarrera);
		
		JLabel lblPuntos = new JLabel("Puntos");
		lblPuntos.setBounds(10, 42, 46, 14);
		getContentPane().add(lblPuntos);
	
		txtCodigo = new JTextField();
		txtCodigo.setBounds(66, 8, 98, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtPuntos = new JTextField();
		txtPuntos.setColumns(10);
		txtPuntos.setBounds(66, 39, 98, 20);
		getContentPane().add(txtPuntos);
		modelo = new DefaultTableModel();
		modelo.addColumn("C�digo");
		modelo.addColumn("Nombre de la Carrera");
		modelo.addColumn("Puntos m�nimos");
		
		txtCarrera = new JTextField();
		txtCarrera.setBounds(126, 67, 134, 21);
		getContentPane().add(txtCarrera);
		txtCarrera.setColumns(10);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(343, 70, 89, 23);
		getContentPane().add(btnEliminar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(343, 38, 89, 23);
		getContentPane().add(btnModificar);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(343, 7, 89, 23);
		getContentPane().add(btnAdicionar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(174, 7, 89, 23);
		btnAceptar.addActionListener(this);
		getContentPane().add(btnAceptar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 104, 417, 164);
		getContentPane().add(scrollPane);
		getContentPane().add(scrollPane);
		
		tblCarrera = new JTable();
		tblCarrera.setFillsViewportHeight(true);
		tblCarrera.addMouseListener(this);
		scrollPane.setViewportView(tblCarrera);
		tblCarrera.setModel(modelo);
		
		txtCodigo.setEditable(false);
		habilitarTexto(false);
		listar();
		obtenerValores();
		}
		//  Declaracion global
		arregloCarrera ac = new arregloCarrera();
	
		public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnAceptar) {
			actionPerformedBtnAceptar(arg0);
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
			int codigoCarrera = leerCodigoCarrera();
			String nombreCarrera = leerNombreCarrera();
			if(nombreCarrera.length()>0){
				String puntosExamen = leerPuntosExamen();
				if(puntosExamen.length()>0){
					if (btnAdicionar.isEnabled() == false) {
						Carrera nuevo = new Carrera(codigoCarrera, nombreCarrera, puntosExamen);
						ac.adicionar(nuevo);
						btnAdicionar.setEnabled(true);
					}
					if (btnModificar.isEnabled() == false) {
						Carrera c = ac.buscar(codigoCarrera);
						c.setNombreCarrera(nombreCarrera);
						c.setPuntosExamen(puntosExamen);
						ac.actualizarArchivo();
						btnModificar.setEnabled(true);
					}
					listar();
					habilitarTexto(false);
				}
				else{
					getToolkit().beep();
					error("Ingrese valores correctos",txtPuntos);
				}
			}
			else{
				getToolkit().beep();
				error("Ingrese una carrera v�lida",txtCarrera);
			}
		}
		protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
			btnAdicionar.setEnabled(false);
			btnModificar.setEnabled(true);
			btnAceptar.setEnabled(true);
			limpieza();
			habilitarTexto(true);
			txtCarrera.requestFocus();
		}
		protected void actionPerformedBtnModificar(ActionEvent arg0) {
			btnAdicionar.setEnabled(true);
			btnModificar.setEnabled(false);
			if (ac.tamaño() == 0) {
				btnAceptar.setEnabled(false);
				btnModificar.setEnabled(true);
				habilitarTexto(false);
				getToolkit().beep();
				mensaje("No existen carreras");	
			}
			else {
				obtenerValores();
				btnAceptar.setEnabled(true);
				habilitarTexto(true);
				txtCarrera.requestFocus();
				
			}
		}
		protected void actionPerformedBtnEliminar(ActionEvent arg0) {
			btnAdicionar.setEnabled(true);
			btnModificar.setEnabled(true);
			btnAceptar.setEnabled(false);
			if (ac.tamaño() == 0)
				mensaje("No existen carreras");
			else {
				obtenerValores();
				habilitarTexto(false);
				int ok = confirmar("¿Desea eliminar el registro?");
				if (ok == 0) {
					ac.eliminar(ac.buscar(leerCodigoCarrera()));
					listar();
					obtenerValores();
				}
			}
		}
		
		
		public void mouseClicked(MouseEvent arg0) {
			if (arg0.getSource() == tblCarrera) {
				mouseClickedTblCarrera(arg0);
			}
		}
		protected void mouseClickedTblCarrera(MouseEvent arg0) {
			habilitarTexto(false);
			habilitarBotones(true);
			obtenerValores();
		}
		
		
		
		void limpieza() {
			txtCodigo.setText("" + ac.codigoCorrelativo());
			txtCarrera.setText("");
			txtPuntos.setText("");
			txtCarrera.requestFocus();
		}
		void listar() {
			int posFila = 0;
			if (modelo.getRowCount() > 0)
				posFila = tblCarrera.getSelectedRow();
			modelo.setRowCount(0);
			Carrera p;
			for (int i=0; i<ac.tamaño(); i++) {
				p = ac.obtener(i);
				Object[] fila = { p.getCodigoCarrera(),
						          p.getNombreCarrera(),
						          p.getPuntosExamen()};
				modelo.addRow(fila);
			}
			if (ac.tamaño() > 0)
				tblCarrera.getSelectionModel().setSelectionInterval(posFila, posFila);
		}
		
		void mensaje(String s) {
			JOptionPane.showMessageDialog(this, s, "Informaci�n", 0);
		}
		void error(String s, JTextField txt) {
			mensaje(s);
			txt.setText("");
			txt.requestFocus();
		}
		
		
		void habilitarTexto(boolean yes) {
			btnAceptar.setEnabled(yes);
			txtCarrera.setEditable(yes);
			txtPuntos.setEditable(yes);
		}
		
		
		void habilitarBotones(boolean yes) {
			btnAdicionar.setEnabled(yes);
			btnModificar.setEnabled(yes);
		}
		
		
		void obtenerValores() {
			if (ac.tamaño() == 0){
				limpieza();
				getToolkit().beep();
				error("No existe ningun registro",txtCarrera);}
			else {
				Carrera p = ac.obtener(tblCarrera.getSelectedRow());
				txtCodigo.setText("" + p.getCodigoCarrera());
				txtCarrera.setText(p.getNombreCarrera());
				txtPuntos.setText(p.getPuntosExamen());
			}
		}
		
		int leerCodigoCarrera() {
			return Integer.parseInt(txtCodigo.getText().trim());
		}
		String leerNombreCarrera() {
			return txtCarrera.getText().trim();
		}
		String leerPuntosExamen() {
			return txtPuntos.getText().trim();
		}

	//  Metodos que retornan valor (con parametros)
		int confirmar(String s) {
			return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
		}

		// Metodos para que no de error
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
		public void mousePressed(MouseEvent e) {	
		}
		public void mouseReleased(MouseEvent e) {
		}

}



