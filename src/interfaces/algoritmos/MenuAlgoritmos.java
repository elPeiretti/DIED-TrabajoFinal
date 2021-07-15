package interfaces.algoritmos;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class MenuAlgoritmos extends JPanel {
	private JButton jb_prox_mantenimiento;
	private JButton jb_page_rank;
	private JButton jb_flujo_max;
	private JComboBox jcb_estacion_destino;
	private JLabel lbl_estacion_destino;
	private JComboBox jcb_estacion_origen;
	private JLabel lbl_estacion_origen;

	/**
	 * Create the panel.
	 */
	public MenuAlgoritmos() {
		setLayout(null);
		
		jb_flujo_max = new JButton("Calcular Flujo Maximo");
		jb_flujo_max.setBounds(2, 180, 135, 54);
		add(jb_flujo_max);
		
		jb_page_rank = new JButton("Calcular Page Rank");
		jb_page_rank.setBounds(138, 180, 125, 54);
		add(jb_page_rank);
		
		jb_prox_mantenimiento = new JButton("Calcular Proximo Mantenimiento");
		jb_prox_mantenimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		jb_prox_mantenimiento.setBounds(263, 180, 185, 54);
		add(jb_prox_mantenimiento);
		
		jcb_estacion_origen = new JComboBox();
		jcb_estacion_origen.setBounds(10, 88, 115, 24);
		add(jcb_estacion_origen);
		
		lbl_estacion_origen = new JLabel("Estacion de origen");
		lbl_estacion_origen.setBounds(23, 65, 99, 14);
		add(lbl_estacion_origen);
		
		jcb_estacion_destino = new JComboBox();
		jcb_estacion_destino.setBounds(10, 145, 115, 24);
		add(jcb_estacion_destino);
		
		lbl_estacion_destino = new JLabel("Estacion de destino");
		lbl_estacion_destino.setBounds(23, 122, 99, 14);
		add(lbl_estacion_destino);

	}
}
