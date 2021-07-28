package interfaces.boletos;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dominio.Camino;
import dominio.Estacion;
import excepciones.DatosDeRecorridoIncorrectosException;
import gestores.GestorAlgoritmos;
import gestores.GestorEntidades;
import gestores.GestorJDBC;
import gestores.GestorValidaciones;
import interfaces.VentanaPrincipal;
import interfaces.grafo.PanelDeGrafo;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
	private JButton jb_visualizar_grafo;

	/**
	 * Create the panel.
	 */
	public MenuSeleccionarRecorrido(VentanaPrincipal contenedor) {
		this.ventana_contenedora = contenedor;
		setLayout(null);
		
		caminos_en_tabla = new ArrayList<Camino>();
		
		lbl_estacion_origen = new JLabel("Estacion origen:");
		lbl_estacion_origen.setBounds(10, 16, 125, 14);
		
		jcb_estacion_origen = new JComboBox<Estacion>();
		jcb_estacion_origen.setBounds(115, 11, 171, 24);
	
		jcb_estacion_destino = new JComboBox<Estacion>();
		jcb_estacion_destino.setBounds(439, 11, 171, 24);
		
		lbl_estacion_destino = new JLabel("Estacion destino:");
		lbl_estacion_destino.setBounds(324, 16, 125, 14);
		
		jb_siguiente = new JButton("Siguiente");
		jb_siguiente.setBounds(493, 254, 117, 23);
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(10, 254, 89, 23);

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
		jspane_recorridos.setSize(600, 140);
		jspane_recorridos.setLocation(10, 100);
		
		jb_buscar = new JButton("Buscar");
		jb_buscar.setBounds(511, 53, 99, 23);
		
		jb_visualizar_grafo = new JButton("Visualizar Grafo");
		jb_visualizar_grafo.setBounds(334, 254, 149, 23);
		
		jtp_errores = new JTextPane();
		jtp_errores.setForeground(Color.RED);
		jtp_errores.setEditable(false);
		jtp_errores.setBackground(UIManager.getColor("Button.background"));
		jtp_errores.setBounds(10, 299, 600, 74);
		
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
		add(jtp_errores);
		add(jb_visualizar_grafo);

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
				inicializarBotones(false);
				Estacion origen = (Estacion) jcb_estacion_origen.getSelectedItem();
				Estacion destino = (Estacion) jcb_estacion_destino.getSelectedItem();
				limpiarTabla();
				try {
					GestorValidaciones.validarEstaciones(origen, destino);
					jtp_errores.setText("");
					
					buscarCaminosYCompletarTabla(origen,destino);
					if(jtable_recorridos.getRowCount()>0)
						jb_visualizar_grafo.setEnabled(true);
				} 
				catch (DatosDeRecorridoIncorrectosException exc) {
					jtp_errores.setText(exc.errores);
				}
			}
		});		
		
		jtable_recorridos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if(jtable_recorridos.getSelectedRow() != -1){
					inicializarBotones(true);
				}
			}
		});
		
		jb_visualizar_grafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog();
				PanelDeGrafo grafo = new PanelDeGrafo(GestorJDBC.buscarColoresTrayectos(caminos_en_tabla.stream()
																										.map(c -> c.getCombinacion())
																										.flatMap(c -> c.stream())
																										.collect(Collectors.toList())));
				dialog.add(grafo);
				dialog.setSize(1280,720);
				dialog.setTitle("Recorridos Desde: '"+((Estacion)jcb_estacion_origen.getSelectedItem()).getNombre()
								+ "', Hasta: '"+((Estacion)jcb_estacion_destino.getSelectedItem()).getNombre()+"'");
				dialog.setVisible(true);
			}
		});
	}
	
	private void buscarCaminosYCompletarTabla(Estacion origen, Estacion destino) {
		List<Camino> recorridos = GestorAlgoritmos.getRecorridosDesdeHasta(origen,destino);
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
	
	public void inicializarBotones(boolean estado) {
		jb_siguiente.setEnabled(estado);
		jb_visualizar_grafo.setEnabled(estado);
	}
}
