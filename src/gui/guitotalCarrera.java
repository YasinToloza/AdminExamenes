package gui;

import arreglo.*;

import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class guitotalCarrera extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JButton btnMostrar;
	private JTable tblCarrera;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			guitotalCarrera dialog = new guitotalCarrera();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public guitotalCarrera() {
		setTitle("Total de Carreras");
		setBounds(100, 100, 438, 300);
		getContentPane().setLayout(null);
		setModal(true);
		setResizable(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 205);
		getContentPane().add(scrollPane);
		
		tblCarrera = new JTable();
		scrollPane.setViewportView(tblCarrera);
		model = new DefaultTableModel();
		model.addColumn("Nombre Carrera");
		model.addColumn("Puntos Examen");
		tblCarrera.setModel(model);
		
		btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(this);
		btnMostrar.setBounds(173, 227, 89, 23);
		getContentPane().add(btnMostrar);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnMostrar) {
			actionPerformedBtnMostrar(arg0);
		}
	}
	protected void actionPerformedBtnMostrar(ActionEvent arg0) {
		arregloCarrera ac = new arregloCarrera();
		for(int i=0; i<ac.tamaño(); i++) {
			{
				Object[] fila = { ac.obtener(i).getNombreCarrera(),
								  ac.obtener(i).getPuntosExamen(),
								 };
							
				model.addRow(fila);
				
			}
			
		}
	}
}
