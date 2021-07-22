package interfaces;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestores.GestorJDBC;

import java.awt.CardLayout;
import interfaces.algoritmos.*;
import interfaces.boletos.*;
import interfaces.estacion.*;
import interfaces.lineastransporte.*;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2455145160154899220L;
	public final static String GEST_ESTACIONES = "Gestionar Estaciones";
	public final static String GEST_LINEA = "Gestionar Linea de Transporte";
	public final static String REG_RECORRIDO = "Registrar recorrido";
	public final static String ALGORITMOS = "Algoritmos para ejecutar";
	public final static String SELEC_RECORRIDO = "Seleccion de Recorrido";
	public final static String REG_COMPRADOR = "Registrar comprador del boleto";
	public final static String EDIT_LINEA = "Modificar Linea de Transporte";
	public final static String EDIT_ESTACION = "Modificar Estacion";
	public final static String MENU_PPAL = "Menu Principal";
	
	private MenuAlgoritmos m_alg;
	private MenuRegistrarComprador m_reg_compr;
	private MenuSeleccionarRecorrido m_selec_rec;
	private MenuEdicionEstacion m_edit_estac;
	private MenuGestionarEstaciones m_gest_estac;
	private MenuEdicionLineaDeTransporte m_edit_linea;
	private MenuGestionarLineaDeTransporte m_gest_linea;
	private MenuRegistrarRecorrido m_reg_rec;
	private MenuPrincipal m_ppal;
	
	
	
	/**
	 * 
	 */
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestorJDBC.updateUltimosId();
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		//inicializar menues
		m_alg = new MenuAlgoritmos(this);
		m_reg_compr = new MenuRegistrarComprador(this);
		m_selec_rec = new MenuSeleccionarRecorrido(this);
		m_edit_estac = new MenuEdicionEstacion(this);
		m_gest_estac = new MenuGestionarEstaciones(this);
		m_edit_linea = new MenuEdicionLineaDeTransporte(this);
		m_gest_linea = new MenuGestionarLineaDeTransporte(this);
		m_reg_rec = new MenuRegistrarRecorrido(this);
		m_ppal = new MenuPrincipal(this);
		
		//inicializar contentPane principal
		this.agregarPaneles();
		
		//Primer menu que se muestra es el principal
		this.cambiarPanel(MENU_PPAL);
		
	}
	
	public void agregarPaneles() {
		contentPane.add(m_reg_compr,REG_COMPRADOR);
		contentPane.add(m_reg_rec,REG_RECORRIDO);
		contentPane.add(m_alg,ALGORITMOS);
		contentPane.add(m_selec_rec,SELEC_RECORRIDO);
		contentPane.add(m_edit_estac,EDIT_ESTACION);
		contentPane.add(m_gest_estac,GEST_ESTACIONES);
		contentPane.add(m_edit_linea,EDIT_LINEA);
		contentPane.add(m_gest_linea,GEST_LINEA);
		contentPane.add(m_ppal,MENU_PPAL);
	}
	
	public void cambiarPanel(String panel) {
		CardLayout c = (CardLayout) contentPane.getLayout();
		if(panel.equals(REG_COMPRADOR))
			m_reg_compr.inicializarLabels();
		if(panel.equals(SELEC_RECORRIDO))
			m_selec_rec.llenarComboBox();
		if(panel.equals(EDIT_ESTACION))
			m_edit_estac.llenarCampos();
		if(panel.equals(GEST_ESTACIONES)) {
			m_gest_estac.limpiarTabla();
			m_gest_estac.limpiarCampos();
		}
		c.show(contentPane,panel);
	}
	
	public static void popupInfo (String mensaje, String titulo) {
		
		JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
		
	}
	
public static Integer popupConfirmar (String mensaje, String titulo) {
		
		return JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.YES_NO_OPTION);
		
	}
	
	
}
