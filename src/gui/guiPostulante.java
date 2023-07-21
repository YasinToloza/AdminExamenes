package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import clase.Postulante;
import arreglo.arregloPostulante;


public class guiPostulante extends JDialog implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCodigoPostulante;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtDNI;
	private JTextField txtTelefono;
	private JTable tblPostulante;
	private DefaultTableModel modelo;
	private JButton btnAceptar;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			guiPostulante dialog = new guiPostulante();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public guiPostulante() {
		setTitle("Inscripci\u00F3n de alumno al sistema");
		setBounds(100, 100, 669, 458);
		getContentPane().setLayout(null);
		setModal(true);
		setResizable(false);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(10, 11, 46, 14);
		getContentPane().add(lblCodigo);
		
		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(10, 46, 56, 14);
		getContentPane().add(lblNombres);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(10, 84, 56, 14);
		getContentPane().add(lblApellidos);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setBounds(10, 122, 56, 14);
		getContentPane().add(lblTelefono);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(10, 160, 46, 14);
		getContentPane().add(lblDni);
		
		txtCodigoPostulante = new JTextField();
		txtCodigoPostulante.setBounds(76, 6, 110, 24);
		getContentPane().add(txtCodigoPostulante);
		txtCodigoPostulante.setColumns(10);
		
		txtNombres = new JTextField();
		txtNombres.setBounds(76, 40, 219, 27);
		getContentPane().add(txtNombres);
		txtNombres.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setBounds(76, 79, 219, 24);
		getContentPane().add(txtApellidos);
		txtApellidos.setColumns(10);
		
		
		txtDNI = new JTextField();
		txtDNI.setBounds(76, 156, 110, 23);
		getContentPane().add(txtDNI);
		txtDNI.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(76, 116, 110, 27);
		getContentPane().add(txtTelefono);
		txtTelefono.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setBounds(206, 7, 89, 23);
		getContentPane().add(btnAceptar);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(539, 11, 89, 23);
		getContentPane().add(btnAdicionar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(539, 42, 89, 23);
		getContentPane().add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(539, 76, 89, 23);
		getContentPane().add(btnEliminar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 194, 618, 214);
		getContentPane().add(scrollPane);
		
		tblPostulante = new JTable();
		tblPostulante.addMouseListener(this);
		tblPostulante.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblPostulante);
		modelo = new DefaultTableModel();
		modelo.addColumn("C�digo");
		modelo.addColumn("Nombres");
		modelo.addColumn("Apellidos");
		modelo.addColumn("Tel�fono");
		modelo.addColumn("DNI");
		tblPostulante.setModel(modelo);
		
		
		txtCodigoPostulante.setEditable(false);
		
		habilitarTexto(false);
		listar();
		obtenerValores();
	}	
		//  Declaracion global
	arregloPostulante ap = new arregloPostulante();
		
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
		}
			
	}
	
	//BOTONES	
	protected void actionPerformedBtnAceptar(ActionEvent arg0) {
		int codigoPostulante = leerCodigoPostulante();
		String nombres = leerNombres();
		if(nombres.length()>0){
			String apellidos = leerApellidos();
			if(apellidos.length()>0){
					String telefono = leerTelefono();
					if(telefono.length()>0){
						String dni = leerDni();
						if(dni.length()>0){
							if (btnAdicionar.isEnabled() == false) {
								Postulante nuevo = new Postulante(codigoPostulante,nombres, apellidos, telefono, dni);
								ap.adicionar(nuevo);
								btnAdicionar.setEnabled(true);
							}
							if (btnModificar.isEnabled() == false) {
								Postulante p = ap.buscar(codigoPostulante);
								p.setNombres(nombres);
								p.setApellidos(apellidos);
								p.setTelefono(telefono);
								p.setDni(dni);
								ap.actualizarArchivo();
								btnModificar.setEnabled(true);
							}
							listar();
							habilitarTexto(false);
						}
						else{
							getToolkit().beep();
							error("Ingrese un DNI correcto",txtDNI);
						}
					}
					else{
						getToolkit().beep();
						error("Ingrese un Tel�fono correcto",txtTelefono);
					}
			}
			else{
				getToolkit().beep();
				error("Ingrese apellidos correctos",txtApellidos);
			}
		}
		else{
			getToolkit().beep();
			error("Ingrese nombres correctos",txtNombres);
		}
	}
	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		btnAdicionar.setEnabled(false);
		btnModificar.setEnabled(true);
		btnAceptar.setEnabled(true);
		limpieza();
		habilitarTexto(true);
		txtNombres.requestFocus();

	}
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		btnAdicionar.setEnabled(true);
		btnModificar.setEnabled(false);
		if (ap.tamaño() == 0) {
			btnAceptar.setEnabled(false);
			habilitarTexto(false);
			getToolkit().beep();
			mensaje("No existen postulantes");	
		}
		else {
			obtenerValores();
			btnAceptar.setEnabled(true);
			habilitarTexto(true);
			txtNombres.requestFocus();
			
		}
	}
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		btnAdicionar.setEnabled(true);
		btnModificar.setEnabled(true);
		btnAceptar.setEnabled(false);
		if (ap.tamaño() == 0)
			mensaje("No existen postulantes");
		else {
			obtenerValores();
			habilitarTexto(false);
			int ok = confirmar("�Desea eliminar el registro?");
			if (ok == 0) {
				ap.eliminar(ap.buscar(leerCodigoPostulante()));
				listar();
				obtenerValores();
			}
		}
	}
	
	
	
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == tblPostulante) {
			mouseClickedTblPostulante(arg0);
		}
	}
	protected void mouseClickedTblPostulante(MouseEvent arg0) {
		habilitarTexto(false);
		habilitarBotones(true);
		obtenerValores();
	}
	void limpieza() {
		txtCodigoPostulante.setText("" + ap.codigoCorrelativo());
		txtDNI.setText("");
		txtNombres.setText("");
		txtApellidos.setText("");
		txtTelefono.setText("");
		txtNombres.requestFocus();
	}
	void listar() {
		int posFila = 0;
		if (modelo.getRowCount() > 0)
			posFila = tblPostulante.getSelectedRow();
		modelo.setRowCount(0);
		Postulante p;
		for (int i=0; i<ap.tamaño(); i++) {
			p = ap.obtener(i);
			Object[] fila = { p.getCodigoPostulante(),
					          p.getNombres(),
					          p.getApellidos(),
					          p.getTelefono(),
					          p.getDni() };
			modelo.addRow(fila);
		}
		if (ap.tamaño() > 0)
			tblPostulante.getSelectionModel().setSelectionInterval(posFila, posFila);
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
		txtNombres.setEditable(yes);
		txtApellidos.setEditable(yes);
		txtTelefono.setEditable(yes);
		txtDNI.setEditable(yes);
	}
	
	
	void habilitarBotones(boolean yes) {
		btnAdicionar.setEnabled(yes);
		btnModificar.setEnabled(yes);
	}
	
	
	void obtenerValores() {
		if (ap.tamaño() == 0){
			limpieza();
			getToolkit().beep();
			error("No existe ningun registro",txtNombres);}
		else {
			Postulante p = ap.obtener(tblPostulante.getSelectedRow());
			txtCodigoPostulante.setText("" + p.getCodigoPostulante());
			txtNombres.setText(p.getNombres());
			txtApellidos.setText(p.getApellidos());
			txtTelefono.setText(p.getTelefono());
			txtDNI.setText(p.getDni());
		}
	}
	
	
	//  M�todos que retornan valor (sin parametros)
	int leerCodigoPostulante() {
		return Integer.parseInt(txtCodigoPostulante.getText().trim());
	}
	String leerNombres() {
		return txtNombres.getText().trim();
	}
	String leerApellidos() {
		return txtApellidos.getText().trim();
	}
	String leerTelefono() {
		return txtTelefono.getText().trim();
	}
	String leerDni() {
		return txtDNI.getText().trim();
	}
	
	
	//  M�todos que retornan valor (con parametros)
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



