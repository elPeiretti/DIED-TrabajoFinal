package interfaces;

import java.awt.BorderLayout;
import java.awt.event.*;

import javax.swing.*;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2017141174165126162L;

	public VentanaPrincipal() {
		super("Ventana Principal");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Ventanota");
		
		JPanel panelDeContenido = new JPanel();
		agregarContenido(panelDeContenido);
		
		
		this.setContentPane(panelDeContenido);
		this.pack();
		this.setSize(400,400);
		this.setVisible(true);
	}
	
	private static void agregarContenido(JPanel panel) {
		agregarBotones(panel);
		agregarLabel(panel);
		
	}
	
	private static void agregarBotones(JPanel panel) {
		JButton salir = new JButton("Salir");
		JButton hola = new JButton("Hola");
		
		salir.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(ABORT);
			}
		});
		
		hola.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("hola desde la consola");
			}
		});
		
		
		panel.add(hola);
		panel.add(salir);

	}
	
	private static void agregarLabel(JPanel panel) {
		JLabel txt = new JLabel("haceme click");
		txt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JDialog dialog = new JDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.add(new JLabel("VENTANA EMERGENTE"),BorderLayout.CENTER);
				dialog.pack();
				dialog.setSize(200,400);
				dialog.setLocation(200,200);
				dialog.setVisible(true);
			}
		});
		panel.add(txt);
	}
	
	
}
