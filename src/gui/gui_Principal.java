package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import libreria.Fecha;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class gui_Principal extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem mntmPostulantes;
	private JMenuItem mntmCarrera;
	private JMenuItem mntmExamen;
	private JMenu mnArchivo;
	private JMenuItem mntmSalir;
	private JLabel lblFondo;
	private JMenuItem mntmTotalPostulantes;
	private JMenuItem mntmTotalCarreras;
	private JMenuItem mntmTotalDeExamenes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui_Principal frame = new gui_Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public gui_Principal() {
		int ANCHO = 480, ALTO =320,
				DX =16, DY = 62;

		setResizable(false);
		setTitle("Programa de Inscripcion "+ Fecha.fecha());
		setIconImage(new ImageIcon("imagen/Examen.jpg").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(ANCHO + DX, ALTO + DY);
		this.setLocationRelativeTo(null);
		setBounds(100, 100, 480, 320);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		lblFondo = new JLabel(new ImageIcon("imagen/Examen.jpg"));
		lblFondo.setBounds(0, 0, ANCHO, ALTO);
		getContentPane().add(lblFondo);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(this);
		mnArchivo.add(mntmSalir);
		
		JMenu mnMantenimiento = new JMenu("Mantenimiento");
		menuBar.add(mnMantenimiento);
		
		mntmPostulantes = new JMenuItem("Postulantes");
		mntmPostulantes.addActionListener(this);
		mnMantenimiento.add(mntmPostulantes);
		
		mntmCarrera = new JMenuItem("Carrera");
		mntmCarrera.addActionListener(this);
		mnMantenimiento.add(mntmCarrera);
		
		JMenu mnRegistro = new JMenu("Registro");
		menuBar.add(mnRegistro);
		
		mntmExamen = new JMenuItem("Examen");
		mntmExamen.addActionListener(this);
		mnRegistro.add(mntmExamen);
		
		JMenu mnConsultas = new JMenu("Reporte");
		menuBar.add(mnConsultas);
		
		mntmTotalPostulantes = new JMenuItem("Total de Postulantes");
		mntmTotalPostulantes.addActionListener(this);
		mnConsultas.add(mntmTotalPostulantes);
		
		mntmTotalCarreras = new JMenuItem("Total de Carreras");
		mntmTotalCarreras.addActionListener(this);
		mnConsultas.add(mntmTotalCarreras);
		
		mntmTotalDeExamenes = new JMenuItem("Total de Examenes");
		mntmTotalDeExamenes.addActionListener(this);
		mnConsultas.add(mntmTotalDeExamenes);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmTotalDeExamenes) {
			actionPerformedMntmTotalDeExamenes(e);
		}
		if (e.getSource() == mntmTotalCarreras) {
			actionPerformedMntmTotalCarreras(e);
		}
		if (e.getSource() == mntmTotalPostulantes) {
			actionPerformedMntmTotalPostulantes(e);
		}
		if (e.getSource() == mntmSalir) {
			actionPerformedMntmSalir(e);
		}
		if (e.getSource() == mntmExamen) {
			actionPerformedMntmExamen(e);
		}
		if (e.getSource() == mntmCarrera) {
			actionPerformedMntmCarrera(e);
		}
		if (e.getSource() == mntmPostulantes) {
			actionPerformedMntmPostulantes(e);
		}
	}
	protected void actionPerformedMntmPostulantes(ActionEvent e) {
		guiPostulante p = new guiPostulante();
		p.setLocationRelativeTo(this);
		p.setVisible(true);
	}
	
	
	protected void actionPerformedMntmCarrera(ActionEvent e) {
		guiCarrera c = new guiCarrera();
		c.setLocationRelativeTo(this);
		c.setVisible(true);
	}
	protected void actionPerformedMntmExamen(ActionEvent e) {
		guiExamen x = new guiExamen();
		x.setLocationRelativeTo(this);
		x.setVisible(true);
	}
	protected void actionPerformedMntmSalir(ActionEvent e) {
		dispose();
	}
	protected void actionPerformedMntmTotalPostulantes(ActionEvent e) {
		guitotalPostulantes gt = new guitotalPostulantes();
		gt.setLocationRelativeTo(this);
		gt.setVisible(true);
	}
	protected void actionPerformedMntmTotalCarreras(ActionEvent e) {
		guitotalCarrera gc = new guitotalCarrera();
		gc.setLocationRelativeTo(this);
		gc.setVisible(true);
	}
	
	protected void actionPerformedMntmTotalDeExamenes(ActionEvent e) {
		guitotalExamen ge = new guitotalExamen();
		ge.setLocationRelativeTo(this);
		ge.setVisible(true);
	}
}
