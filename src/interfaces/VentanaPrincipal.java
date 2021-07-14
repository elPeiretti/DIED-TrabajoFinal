package interfaces;

import javax.swing.*;

public class VentanaPrincipal extends JFrame {

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
		
		panel.add(hola);
		panel.add(salir);
	}
	private static void agregarLabel(JPanel panel) {
		panel.add(new JLabel("hola muy buenas tardes lector"));
	}
	
}
