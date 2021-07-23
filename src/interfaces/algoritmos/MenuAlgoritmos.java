package interfaces.algoritmos;

import javax.swing.JPanel;

import dominio.Estacion;
import excepciones.DatosDeRecorridoIncorrectosException;
import gestores.GestorJDBC;
import gestores.GestorValidaciones;
import interfaces.VentanaPrincipal;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Color;

public class MenuAlgoritmos extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7369278906871564582L;
	
	private JButton jb_prox_mantenimiento;
	private JButton jb_page_rank;
	private JButton jb_flujo_max;
	private JComboBox<Estacion> jcb_estacion_destino;
	private JLabel lbl_estacion_destino;
	private JComboBox<Estacion> jcb_estacion_origen;
	private JLabel lbl_estacion_origen;
	private JButton jb_regresar;
	private VentanaPrincipal ventana_contenedora;
	private JTextPane jtp_errores;
	
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
				
		jcb_estacion_origen = new JComboBox<Estacion>();
		jcb_estacion_origen.setBounds(10, 88, 115, 24);
		
		lbl_estacion_origen = new JLabel("Estacion de origen");
		lbl_estacion_origen.setBounds(23, 65, 99, 14);
				
		jcb_estacion_destino = new JComboBox<Estacion>();
		jcb_estacion_destino.setBounds(10, 145, 115, 24);
		
		lbl_estacion_destino = new JLabel("Estacion de destino");
		lbl_estacion_destino.setBounds(23, 122, 99, 14);
		
		jb_regresar = new JButton("Regresar");
		jb_regresar.setBounds(10, 266, 89, 23);
		
		jtp_errores = new JTextPane();
		jtp_errores.setEditable(false);
		jtp_errores.setForeground(Color.RED);
		jtp_errores.setBackground(UIManager.getColor("Button.background"));
		jtp_errores.setBounds(10, 300, 430, 80);
		
		this.agregarActionListener();
		add(jb_flujo_max);
		add(jb_page_rank);
		add(jcb_estacion_origen);
		add(lbl_estacion_origen);
		add(jcb_estacion_destino);
		add(lbl_estacion_destino);
		add(jb_prox_mantenimiento);
		add(jb_regresar);
		add(jtp_errores);

	}
	
	private void agregarActionListener() {
		jb_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarComboBox();
				ventana_contenedora.cambiarPanel(VentanaPrincipal.MENU_PPAL);
			}
		});
		
		jb_flujo_max.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Estacion origen = (Estacion)jcb_estacion_origen.getSelectedItem();
				Estacion destino = (Estacion) jcb_estacion_destino.getSelectedItem();
				
				try {
					GestorValidaciones.validarEstaciones(origen,destino);
					jtp_errores.setText("");
				} 
				catch (DatosDeRecorridoIncorrectosException exc) {
					jtp_errores.setText(exc.errores);
				}
			}
		});
	}
	
	public void llenarComboBox() {
		for(Estacion e : GestorJDBC.buscarEstacion("","","","",null)) {
			jcb_estacion_origen.addItem(e);
			jcb_estacion_destino.addItem(e);
		}
	}
	
	private void limpiarComboBox() {
		jcb_estacion_origen.removeAllItems();
		jcb_estacion_destino.removeAllItems();
	}
}
