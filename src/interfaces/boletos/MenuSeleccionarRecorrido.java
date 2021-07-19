package interfaces.boletos;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dominio.Camino;
import dominio.Estacion;
import gestores.GestorEntidades;
import interfaces.VentanaPrincipal;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.awt.event.ActionEvent;

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

	/**
	 * Create the panel.
	 */
	public MenuSeleccionarRecorrido(VentanaPrincipal contenedor) {
		this.ventana_contenedora = contenedor;
		setLayout(null);
		
		lbl_estacion_origen = new JLabel("Estacion origen:");
		lbl_estacion_origen.setBounds(20, 16, 92, 14);
		
		
		jcb_estacion_origen = new JComboBox<Estacion>();
		jcb_estacion_origen.setBounds(109, 11, 99, 24);
		
		
		jcb_estacion_destino = new JComboBox<Estacion>();
		jcb_estacion_destino.setBounds(329, 11, 99, 24);
		jcb_estacion_destino.setEnabled(false);
		
		
		lbl_estacion_destino = new JLabel("Estacion destino:");
		lbl_estacion_destino.setBounds(227, 16, 92, 14);
		
		
		jb_siguiente = new JButton("Siguiente");
		jb_siguiente.setBounds(311, 254, 117, 23);
		
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(23, 254, 89, 23);

		jtable_recorridos_contenido = new DefaultTableModel();
		jtable_recorridos_contenido.addColumn("Duracion [minutos]");
		jtable_recorridos_contenido.addColumn("Distancia [Km]");
		jtable_recorridos_contenido.addColumn("Precio [$]");
		
		jtable_recorridos = new JTable();
		jtable_recorridos.setModel(jtable_recorridos_contenido);
		jtable_recorridos.setBounds(23, 79, 407, 164);
		
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
		add(jtable_recorridos);
		add(jb_buscar);

	}
	
	private void agregarActionListener() {
		jb_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_contenedora.cambiarPanel(VentanaPrincipal.MENU_PPAL);
			}
		});
		
		jb_siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jtable_recorridos.getSelectedRow()!=-1)
					ventana_contenedora.cambiarPanel(VentanaPrincipal.REG_COMPRADOR);
			}
		});
		
		jb_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estacion origen = (Estacion) jcb_estacion_origen.getSelectedItem();
				Estacion destino = (Estacion) jcb_estacion_destino.getSelectedItem();
				if(origen != null && destino != null)
					buscarCaminosYCompletarTabla(origen,destino);
			}
		});
		
		
	}
	
	private static void buscarCaminosYCompletarTabla(Estacion origen, Estacion destino) {
		//List<Camino> c = GestorEntidades.getCaminosDesdeHasta(origen,destino); TO DO
	}
	
	private void llenarComboBox() {
		/*for(Estacion e : GestorJDBC.buscarEstacion("","","","",-1)) {
			jcb_estacion_origen.addItem(e);
			jcb_estacion_destino.addItem(e);
		}*/
	}

}
