package interfaces;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JPanel {

	/**
	 * Create the panel.
	 */
	public MenuPrincipal() {
		
		JButton jb_gestionar_estaciones = new JButton("Gestionar Estaciones");
		jb_gestionar_estaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton jb_gestionar_lineas = new JButton("Gestionar Lineas de Transporte");
		jb_gestionar_lineas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton jb_registrar_venta = new JButton("Registrar Venta");
		add(jb_registrar_venta);
		
		JButton jb_algoritmos_estaciones = new JButton("Algoritmos Estaciones");
		jb_algoritmos_estaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(jb_algoritmos_estaciones);
		add(jb_gestionar_lineas);
		add(jb_gestionar_estaciones);

	}

}
