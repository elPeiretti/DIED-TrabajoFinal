package interfaces.boletos;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dominio.Camino;
import dominio.Estacion;
import excepciones.DatosDeRecorridoIncorrectosException;
import gestores.GestorEntidades;
import gestores.GestorJDBC;
import gestores.GestorValidaciones;
import interfaces.VentanaPrincipal;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Color;

public class MenuSeleccionarRecorrido extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4060984994480535340L;
	
	private JTable jtable_recorridos;
	private JLabel lbl_estacion_origen;
	private JComboBox<Estacion> jcb_estacion_origen;
	private JComboBox<Estacion> jcb_estacion_destino;
	private JLabel lbl_estacion_destino;
	private JButton jb_siguiente;
	private JButton jb_cancelar;
	private VentanaPrincipal ventana_contenedora;
	private DefaultTableModel jtable_recorridos_contenido;
	private JButton jb_buscar;
	private List<Camino> caminos_en_tabla;
	private JScrollPane jspane_recorridos;
	private JTextPane jtp_errores;

	/**
	 * Create the panel.
	 */
	public MenuSeleccionarRecorrido(VentanaPrincipal contenedor) {
		this.ventana_contenedora = contenedor;
		setLayout(null);
		
		caminos_en_tabla = new ArrayList<Camino>();
		
		lbl_estacion_origen = new JLabel("Estacion origen:");
		lbl_estacion_origen.setBounds(20, 16, 92, 14);
		
		jcb_estacion_origen = new JComboBox<Estacion>();
		jcb_estacion_origen.setBounds(109, 11, 99, 24);
	
		jcb_estacion_destino = new JComboBox<Estacion>();
		jcb_estacion_destino.setBounds(329, 11, 99, 24);
		jcb_estacion_destino.setEnabled(true);
		
		lbl_estacion_destino = new JLabel("Estacion destino:");
		lbl_estacion_destino.setBounds(227, 16, 92, 14);
		
		jb_siguiente = new JButton("Siguiente");
		jb_siguiente.setBounds(311, 254, 117, 23);
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(23, 254, 89, 23);

		jtable_recorridos_contenido = new DefaultTableModel();
		jtable_recorridos_contenido.addColumn("");
		jtable_recorridos_contenido.addColumn("Duracion [minutos]");
		jtable_recorridos_contenido.addColumn("Distancia [Km]");
		jtable_recorridos_contenido.addColumn("Precio [$]");
		
		jtable_recorridos = new JTable();
		jtable_recorridos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtable_recorridos.setModel(jtable_recorridos_contenido);
		jtable_recorridos.setAutoCreateRowSorter(true);
		
		jspane_recorridos = new JScrollPane(jtable_recorridos);
		jspane_recorridos.setSize(430, 140);
		jspane_recorridos.setLocation(10, 100);
		
		jb_buscar = new JButton("Buscar");
		jb_buscar.setBounds(329, 46, 99, 23);
		
		this.agregarActionListener();
		this.llenarComboBox();
		add(jcb_estacion_origen);
		add(jb_cancelar);
		add(jb_siguiente);
		add(lbl_estacion_destino);
		add(jcb_estacion_destino);
		add(lbl_estacion_origen);
		add(jspane_recorridos);
		add(jb_buscar);
		
		jtp_errores = new JTextPane();
		jtp_errores.setForeground(Color.RED);
		jtp_errores.setEditable(false);
		jtp_errores.setBackground(UIManager.getColor("Button.background"));
		jtp_errores.setBounds(10, 299, 430, 74);
		add(jtp_errores);

	}
	
	private void agregarActionListener() {
		jb_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
				ventana_contenedora.cambiarPanel(VentanaPrincipal.MENU_PPAL);
			}
		});
		
		jb_siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer i = jtable_recorridos.getSelectedRow();
				if(i!=-1) {
					MenuRegistrarComprador.camino_seleccionado = caminos_en_tabla.get(i);
					ventana_contenedora.cambiarPanel(VentanaPrincipal.REG_COMPRADOR);
				}
			}
		});
		
		jb_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estacion origen = (Estacion) jcb_estacion_origen.getSelectedItem();
				Estacion destino = (Estacion) jcb_estacion_destino.getSelectedItem();
				try {
					GestorValidaciones.validarEstaciones(origen, destino);
					jtp_errores.setText("");
					limpiarTabla();
					
					buscarCaminosYCompletarTabla(origen,destino);
				} 
				catch (DatosDeRecorridoIncorrectosException exc) {
					jtp_errores.setText(exc.errores);
				}
			}
		});		

	}
	
	private void buscarCaminosYCompletarTabla(Estacion origen, Estacion destino) {
		List<Camino> recorridos = GestorEntidades.getRecorridosDesdeHasta(origen,destino);
		Integer i=1;
		for(Camino c : recorridos) {
			Vector<String> data = new Vector<String>();
			data.add((i++).toString());
			data.addAll(GestorEntidades.getVectorDeDatosDeCamino(c));
			jtable_recorridos_contenido.addRow(data);
			caminos_en_tabla.add(c);
		}
	}
	
	public void llenarComboBox() {
		for(Estacion e : GestorJDBC.buscarEstacion("","","","",null)) {
			jcb_estacion_origen.addItem(e);
			jcb_estacion_destino.addItem(e);
		}
	}
	
	public void limpiarTabla() {
		jtable_recorridos_contenido.setRowCount(0);
		caminos_en_tabla.clear();
	}
}
