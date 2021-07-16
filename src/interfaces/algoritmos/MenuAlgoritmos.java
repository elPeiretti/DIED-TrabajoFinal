package interfaces.algoritmos;

import javax.swing.JPanel;

import interfaces.VentanaPrincipal;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class MenuAlgoritmos extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7369278906871564582L;
	
	private JButton jb_prox_mantenimiento;
	private JButton jb_page_rank;
	private JButton jb_flujo_max;
	private JComboBox<String> jcb_estacion_destino;
	private JLabel lbl_estacion_destino;
	private JComboBox<String> jcb_estacion_origen;
	private JLabel lbl_estacion_origen;
	private JButton jb_regresar;
	private VentanaPrincipal ventana_contenedora;
	
	/**
	 * Create the panel.
	 */
	public MenuAlgoritmos(VentanaPrincipal contenedor) {
		setLayout(null);
		this.ventana_contenedora=contenedor;
		
		jb_flujo_max = new JButton("Calcular Flujo Maximo");
		jb_flujo_max.setBounds(2, 180, 135, 54);
		
		
		jb_page_rank = new JButton("Calcular Page Rank");
		jb_page_rank.setBounds(138, 180, 125, 54);
		
		
		jb_prox_mantenimiento = new JButton("Calcular Proximo Mantenimiento");
		jb_prox_mantenimiento.setBounds(263, 180, 185, 54);
		
		
		jcb_estacion_origen = new JComboBox<String>();
		jcb_estacion_origen.setBounds(10, 88, 115, 24);
		
		
		lbl_estacion_origen = new JLabel("Estacion de origen");
		lbl_estacion_origen.setBounds(23, 65, 99, 14);
		
		
		jcb_estacion_destino = new JComboBox<String>();
		jcb_estacion_destino.setBounds(10, 145, 115, 24);
		
		
		lbl_estacion_destino = new JLabel("Estacion de destino");
		lbl_estacion_destino.setBounds(23, 122, 99, 14);
		
		
		jb_regresar = new JButton("Regresar");
		jb_regresar.setBounds(10, 266, 89, 23);
		
		this.agregarActionListener();
		add(jb_flujo_max);
		add(jb_page_rank);
		add(jcb_estacion_origen);
		add(lbl_estacion_origen);
		add(jcb_estacion_destino);
		add(lbl_estacion_destino);
		add(jb_prox_mantenimiento);
		add(jb_regresar);

	}
	
	private void agregarActionListener() {
		jb_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_contenedora.cambiarPanel(VentanaPrincipal.MENU_PPAL);
			}
		});
	}
}
