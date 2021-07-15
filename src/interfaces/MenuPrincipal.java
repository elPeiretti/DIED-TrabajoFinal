package interfaces;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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

	/**
	 * Create the panel.
	 */
	public MenuPrincipal() {
		
		jb_gestionar_estaciones = new JButton("Gestionar Estaciones");
		jb_gestionar_estaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		jb_gestionar_lineas = new JButton("Gestionar Lineas de Transporte");
		jb_gestionar_lineas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		jb_registrar_venta = new JButton("Registrar Venta");
		add(jb_registrar_venta);
		
		jb_algoritmos_estaciones = new JButton("Algoritmos Estaciones");
		jb_algoritmos_estaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(jb_algoritmos_estaciones);
		add(jb_gestionar_lineas);
		add(jb_gestionar_estaciones);

	}

}
