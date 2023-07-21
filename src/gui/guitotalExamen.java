package gui;



import arreglo.*;

import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class guitotalExamen extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JTable tblExamen;
	private JButton btnMostrar;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			guitotalExamen dialog = new guitotalExamen();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public guitotalExamen() {
		setTitle("Total Examenes");
		setBounds(100, 100, 543, 358);
		getContentPane().setLayout(null);
		setModal(true);
		setResizable(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 517, 254);
		getContentPane().add(scrollPane);
		
		tblExamen = new JTable();
		scrollPane.setViewportView(tblExamen);
		model = new DefaultTableModel();
		model.addColumn("Codigo Carrera");
		model.addColumn("Fecha Examen");
		model.addColumn("Hora Examen");
		tblExamen.setModel(model);
		
		btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(this);
		btnMostrar.setBounds(214, 285, 89, 23);
		getContentPane().add(btnMostrar);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMostrar) {
			actionPerformedBtnNewButton(e);
		}
	}
	protected void actionPerformedBtnNewButton(ActionEvent e) {
		arregloExamen ae = new arregloExamen();
		for (int i=0; i<ae.tamaño(); i++) {
			{
				Object[] fila = { ae.obtener(i).getCodigoCarrera(),
								  ae.obtener(i).getFechaExamen(),
								  ae.obtener(i).getHoraExamen()};
				
					model.addRow(fila);
				}
			}
		}
	}

