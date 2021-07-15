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
		
		JButton btnNewButton = new JButton("Gestionar Estaciones");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton_1 = new JButton("Gestionar Lineas de Transporte");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton_2 = new JButton("Registrar Venta");
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Algoritmos Estaciones");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnNewButton_3);
		add(btnNewButton_1);
		add(btnNewButton);

	}

}
