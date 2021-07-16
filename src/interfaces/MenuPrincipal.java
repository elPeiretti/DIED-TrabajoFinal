package interfaces;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6659076339767881277L;
	
	private JButton jb_registrar_venta;
	private JButton jb_algoritmos_estaciones;
	private JButton jb_gestionar_lineas;
	private JButton jb_gestionar_estaciones;
	private VentanaPrincipal ventana_contenedora;

	/**
	 * Create the panel.
	 */
	public MenuPrincipal(VentanaPrincipal contenedor) {
		this.ventana_contenedora = contenedor;
		
		jb_gestionar_estaciones = new JButton("Gestionar Estaciones");
		jb_gestionar_lineas = new JButton("Gestionar Lineas de Transporte");
		jb_registrar_venta = new JButton("Registrar Venta");
		jb_algoritmos_estaciones = new JButton("Algoritmos Estaciones");
		this.agregarActionListener();
		
		add(jb_registrar_venta);
		add(jb_algoritmos_estaciones);
		add(jb_gestionar_lineas);
		add(jb_gestionar_estaciones);

	}
	
	private void agregarActionListener() {
		jb_gestionar_estaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_contenedora.cambiarPanel(VentanaPrincipal.GEST_ESTACIONES);
			}
		});
		
		jb_gestionar_lineas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_contenedora.cambiarPanel(VentanaPrincipal.GEST_LINEA);
			}
		});
		
		jb_registrar_venta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_contenedora.cambiarPanel(VentanaPrincipal.SELEC_RECORRIDO);
			}
		});
		
		jb_algoritmos_estaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_contenedora.cambiarPanel(VentanaPrincipal.ALGORITMOS);
			}
		});
		
		
	}

}
