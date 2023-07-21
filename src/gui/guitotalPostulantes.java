package gui;

import javax.swing.JDialog;



import arreglo.*;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;
public class guitotalPostulantes extends JDialog implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JButton btnMostrar;
	private JTable tblPostulantes;
	private DefaultTableModel model;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			guitotalPostulantes dialog = new guitotalPostulantes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public guitotalPostulantes() {
		setTitle("Total de postulantes");
		setBounds(100, 100, 561, 333);
		getContentPane().setLayout(null);
		setModal(true);
		setResizable(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 534, 225);
		getContentPane().add(scrollPane);
		
		tblPostulantes = new JTable();
		tblPostulantes.setBackground(Color.WHITE);
		scrollPane.setViewportView(tblPostulantes);
		model = new DefaultTableModel(); 
		model.addColumn("Codigo");
		model.addColumn("Nombre");
		model.addColumn("Apellido");
		model.addColumn("Celular");
		model.addColumn("DNI");
		tblPostulantes.setModel(model);
		
		btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(this);
		btnMostrar.setBounds(238, 261, 89, 23);
		getContentPane().add(btnMostrar);
	
	}
	

	
//  Metodos tipo void (con parametros)
	void imprimir(String s) {
		
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnMostrar) {
			actionPerformedBtnMostrar(arg0);
		}
	}
	protected void actionPerformedBtnMostrar(ActionEvent arg0) {
		arregloPostulante ap = new arregloPostulante();
		for (int i=0; i<ap.tamaño(); i++){
			{
			Object[] fila = { ap.obtener(i).getCodigoPostulante(),
					          ap.obtener(i).getNombres(),
					          ap.obtener(i).getApellidos(),
					          ap.obtener(i).getTelefono(),
					          ap.obtener(i).getDni()};
			model.addRow(fila);
		}
		}
		
	}
}
