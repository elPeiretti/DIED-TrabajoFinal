package interfaces.algoritmos;

import javax.swing.JPanel;

import dominio.Estacion;
import excepciones.DatosDeRecorridoIncorrectosException;
import excepciones.NoHayDatosDeEstacionesException;
import gestores.GestorAlgoritmos;
import gestores.GestorJDBC;
import gestores.GestorValidaciones;
import interfaces.VentanaPrincipal;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		jb_flujo_max.setBounds(10, 220, 163, 54);
				
		jb_page_rank = new JButton("Calcular Page Rank");
		jb_page_rank.setBounds(438, 220, 150, 54);
				
		jb_prox_mantenimiento = new JButton("Calcular Proximo Mantenimiento");
		jb_prox_mantenimiento.setBounds(183, 220, 245, 54);
				
		jcb_estacion_origen = new JComboBox<Estacion>();
		jcb_estacion_origen.setBounds(10, 90, 163, 24);
		
		lbl_estacion_origen = new JLabel("Estacion de origen:");
		lbl_estacion_origen.setBounds(10, 65, 150, 14);
				
		jcb_estacion_destino = new JComboBox<Estacion>();
		jcb_estacion_destino.setBounds(10, 169, 163, 24);
		
		lbl_estacion_destino = new JLabel("Estacion de destino:");
		lbl_estacion_destino.setBounds(10, 144, 150, 14);
		
		jb_regresar = new JButton("Regresar");
		jb_regresar.setBounds(10, 380, 89, 23);
		
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
					Integer flujo = GestorAlgoritmos.calcularFlujoMaximo(origen,destino);
					
					VentanaPrincipal.popupInfo("La cantidad maxima de pasajeros que se pueden transportar desde: "+
												origen.getNombre()+", hasta: "+destino.getNombre()+
												"\nEs de "+ flujo.toString() +" pasajeros","Flujo maximo");
				} 
				catch (DatosDeRecorridoIncorrectosException exc) {
					jtp_errores.setText(exc.errores);
				}
			}
		});
		
		jb_page_rank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Estacion> ranking;
				try {
					ranking = GestorAlgoritmos.calcularPageRank();
					VentanaPrincipal.popupInfo("El ranking de las estaciones es el siguiente (más a la izquerda significa mayor prioridad):\n"+
							ranking.toString(), "Page Rank");
				} catch (NoHayDatosDeEstacionesException e1) {
					JOptionPane.showMessageDialog(null,"No se puede realizar este algoritmo sin tener estaciones cargadas.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		jb_prox_mantenimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//mostrar monticulo entero o solo la estacion correspondiente? TODO
				//para no ocupar mas memoria, se usan los objetos que estan en el combobox
				List<Estacion> estaciones = new ArrayList<Estacion>();
				for(int i=0; i<jcb_estacion_origen.getItemCount(); i++) {
					estaciones.add(jcb_estacion_origen.getItemAt(i));
				}
								
				try {
					PriorityQueue<Estacion> ests = GestorAlgoritmos.calcularPrioridadMantenimiento(estaciones);
					VentanaPrincipal.popupInfo("La estacion a la cual hay que realizarle el proximo mantenimiento es:\n"+
								"ID: "+ests.peek().getId_estacion()+ " NOMBRE: "+ests.peek().getNombre()
								+ "\nMonticulo: \n" + ests, "Proximo mantenimiento");
				} 
				catch (NoHayDatosDeEstacionesException e1) {
					JOptionPane.showMessageDialog(null,"No se puede realizar este algoritmo sin tener estaciones cargadas.", "Error", JOptionPane.ERROR_MESSAGE);
				}				
			}
		});
	}
	
	public void llenarComboBox() {
		for(Estacion e : GestorJDBC.buscarEstacionConUltimoMantenimiento("","","","",null)) {
			jcb_estacion_origen.addItem(e);
			jcb_estacion_destino.addItem(e);
		}
	}
	
	private void limpiarComboBox() {
		jcb_estacion_origen.removeAllItems();
		jcb_estacion_destino.removeAllItems();
	}
}
