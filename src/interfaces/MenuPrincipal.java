package interfaces;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Color;

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
	private JLabel lbl_titulo;
	private JLabel lbl_integrantes;
	private JLabel lbl_juan;
	private JLabel lbl_tomas;

	/**
	 * Create the panel.
	 */
	public MenuPrincipal(VentanaPrincipal contenedor) {
		this.ventana_contenedora = contenedor;
		
		jb_gestionar_estaciones = new JButton("Gestionar Estaciones");
		jb_gestionar_estaciones.setBounds(206, 192, 220, 40);
		
		jb_gestionar_lineas = new JButton("Gestionar Lineas de Transporte");
		jb_gestionar_lineas.setBounds(206, 243, 220, 40);
		
		jb_registrar_venta = new JButton("Registrar Venta");
		jb_registrar_venta.setBounds(206, 86, 220, 40);
		
		jb_algoritmos_estaciones = new JButton("Algoritmos Estaciones");
		jb_algoritmos_estaciones.setBounds(206, 137, 220, 40);
		
		lbl_titulo = new JLabel("DIED 2021 - TP FINAL");
		lbl_titulo.setBackground(UIManager.getColor("Button.shadow"));
		lbl_titulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titulo.setFont(new Font("Microsoft YaHei UI", Font.BOLD | Font.ITALIC, 30));
		lbl_titulo.setBounds(100, 11, 425, 64);
		
		lbl_integrantes = new JLabel("Integrantes:");
		lbl_integrantes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lbl_integrantes.setForeground(Color.DARK_GRAY);
		lbl_integrantes.setBounds(10, 350, 100, 20);
		
		lbl_juan = new JLabel("- Albani Juan Ignacio");
		lbl_juan.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lbl_juan.setForeground(Color.DARK_GRAY);
		lbl_juan.setBounds(20, 373, 150, 14);
		
		lbl_tomas = new JLabel("- Peiretti Tomas");
		lbl_tomas.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lbl_tomas.setForeground(Color.DARK_GRAY);
		lbl_tomas.setBounds(20, 390, 150, 14);
		
		setLayout(null);
		
		this.agregarActionListener();
		add(jb_registrar_venta);
		add(jb_algoritmos_estaciones);
		add(jb_gestionar_lineas);
		add(jb_gestionar_estaciones);
		add(lbl_titulo);		
		add(lbl_integrantes);
		add(lbl_juan);
		add(lbl_tomas);

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
