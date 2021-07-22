package interfaces.lineastransporte;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dominio.Camino;
import dominio.Estacion;
import dominio.EstadoEstacion;
import dominio.LineaDeTransporte;
import dominio.Trayecto;
import excepciones.DatosDeTrayectoIncorrectosException;
import gestores.GestorEntidades;
import gestores.GestorJDBC;
import gestores.GestorValidaciones;
import interfaces.VentanaPrincipal;

import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

public class MenuRegistrarRecorrido extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8877632157246809361L;
	
	private JTextField jtf_costo;
	private JLabel lbl_estacion_origen;
	private JComboBox<Estacion> jcb_estacion_origen;
	private JButton jb_agregar_trayecto;
	private JLabel lbl_estacion_destino;
	private JComboBox<Estacion> jcb_estacion_destino;
	private JSpinner jspin_distancia;
	private JLabel lbl_distancia;
	private JLabel lbl_duracion;
	private JSpinner jspin_duracion;
	private JLabel lbl_capacidad_maxima;
	private JSpinner jspin_capacidad_maxima;
	private JLabel lbl_costo;
	private JComboBox<EstadoEstacion> jcb_estado;
	private JLabel lbl_estado;
	private JTable jtable_trayectos;
	private DefaultTableModel jtable_trayectos_contenido;
	private JButton jb_guardar_recorrido;
	private JButton jb_cancelar;
	private VentanaPrincipal ventana_contenedora;
	private JTextPane jtp_errores;
	private DefaultComboBoxModel<Estacion> jcb_estacion_origen_contenido;
	private DefaultComboBoxModel<Estacion> jcb_estacion_destino_contenido;
	protected static LineaDeTransporte linea_seleccionada;
	private JButton jb_eliminar_ultimo;
	private List<Trayecto> objetos_en_tabla;
	private JScrollPane jspane_trayectos;

	/**
	 * Create the panel.
	 */
	public MenuRegistrarRecorrido(VentanaPrincipal contenedor) {
		this.ventana_contenedora = contenedor;
		setLayout(null);
		
		lbl_estacion_origen = new JLabel("Estacion origen:");
		lbl_estacion_origen.setBounds(20, 16, 92, 14);
		
		jcb_estacion_origen = new JComboBox<Estacion>();
		jcb_estacion_origen.setBounds(109, 11, 99, 24);
		
		jcb_estacion_origen_contenido = new DefaultComboBoxModel<Estacion>();
		jcb_estacion_origen.setModel(jcb_estacion_origen_contenido);
		
		jb_agregar_trayecto = new JButton("Agregar trayecto al recorrido");
		jb_agregar_trayecto.setBackground(new Color(60, 179, 113));
		jb_agregar_trayecto.setBounds(248, 109, 173, 23);
		jb_agregar_trayecto.setEnabled(false);
		
		jcb_estacion_destino = new JComboBox<Estacion>();
		jcb_estacion_destino.setBounds(329, 11, 99, 24);
		
		jcb_estacion_destino_contenido = new DefaultComboBoxModel<Estacion>();
		jcb_estacion_destino.setModel(jcb_estacion_destino_contenido);
		
		lbl_estacion_destino = new JLabel("Estacion destino:");
		lbl_estacion_destino.setBounds(227, 16, 92, 14);
		
		jspin_distancia = new JSpinner();
		jspin_distancia.setToolTipText("Distancia entre las estaciones en Km");
		jspin_distancia.setModel(new SpinnerNumberModel(1, 1, 50, 1));
		jspin_distancia.setBounds(159, 46, 49, 20);
		
		lbl_distancia = new JLabel("Distancia [Km] :");
		lbl_distancia.setBounds(20, 49, 82, 14);
		
		lbl_duracion = new JLabel("Duracion [minutos]:");
		lbl_duracion.setBounds(20, 80, 105, 14);
		
		jspin_duracion = new JSpinner();
		jspin_duracion.setModel(new SpinnerNumberModel(1, 1, 120, 1));
		jspin_duracion.setToolTipText("Distancia entre las estaciones en Km");
		jspin_duracion.setBounds(159, 77, 49, 20);
		
		lbl_capacidad_maxima = new JLabel("Capacidad maxima [pasajeros]:");
		lbl_capacidad_maxima.setBounds(227, 49, 151, 14);
		
		jspin_capacidad_maxima = new JSpinner();
		jspin_capacidad_maxima.setModel(new SpinnerNumberModel(1, 1, 200, 1));
		jspin_capacidad_maxima.setToolTipText("Distancia entre las estaciones en Km");
		jspin_capacidad_maxima.setBounds(379, 46, 49, 20);
		
		jtf_costo = new JTextField();
		jtf_costo.setBounds(329, 77, 99, 20);
		jtf_costo.setColumns(10);
		
		lbl_costo = new JLabel("Costo [$]:");
		lbl_costo.setBounds(227, 80, 57, 14);
		
		jcb_estado = new JComboBox<EstadoEstacion>();
		jcb_estado.setModel(new DefaultComboBoxModel<EstadoEstacion>(new EstadoEstacion[] {EstadoEstacion.OPERATIVA, EstadoEstacion.EN_MANTENIMIENTO}));
		jcb_estado.setMaximumRowCount(3);
		jcb_estado.setBounds(109, 108, 99, 24);
		
		lbl_estado = new JLabel("Estado:");
		lbl_estado.setBounds(20, 113, 46, 14);
		
		jtable_trayectos = new JTable();
		jtable_trayectos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		jtable_trayectos_contenido = new DefaultTableModel();
		jtable_trayectos_contenido.addColumn("Origen");
		jtable_trayectos_contenido.addColumn("Destino");
		jtable_trayectos_contenido.addColumn("Costo [$]");
		jtable_trayectos_contenido.addColumn("Distancia [km]");
		jtable_trayectos_contenido.addColumn("Duracion [min]");
		jtable_trayectos_contenido.addColumn("Capacidad [personas]");
		
		objetos_en_tabla = new ArrayList<Trayecto>();

		jtable_trayectos.setModel(jtable_trayectos_contenido);
		jtable_trayectos.setBorder(null);
		
		jspane_trayectos = new JScrollPane(jtable_trayectos);
		jspane_trayectos.setSize(430, 100);
		jspane_trayectos.setLocation(10, 150);
		
		jb_guardar_recorrido = new JButton("Guardar recorrido");
		jb_guardar_recorrido.setBounds(311, 254, 117, 23);
		jb_guardar_recorrido.setEnabled(false);
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(23, 254, 89, 23);
		
		jtp_errores = new JTextPane();
		jtp_errores.setForeground(Color.RED);
		jtp_errores.setEditable(false);
		jtp_errores.setBackground(UIManager.getColor("Button.background"));
		jtp_errores.setBounds(10, 286, 418, 103);
		
		jb_eliminar_ultimo = new JButton("Eliminar ultimo trayecto");
		jb_eliminar_ultimo.setBounds(129, 254, 173, 23);
		jb_eliminar_ultimo.setEnabled(false);
		
		this.llenarComboBoxEstaciones();
		this.agregarActionListener();
		add(jb_guardar_recorrido);
		add(jspane_trayectos);
		add(lbl_estado);
		add(jcb_estado);
		add(lbl_costo);
		add(jtf_costo);
		add(jspin_capacidad_maxima);
		add(lbl_capacidad_maxima);
		add(jspin_duracion);
		add(lbl_duracion);
		add(lbl_distancia);
		add(jspin_distancia);
		add(lbl_estacion_destino);
		add(jcb_estacion_destino);
		add(jb_agregar_trayecto);
		add(jcb_estacion_origen);
		add(lbl_estacion_origen);
		add(jb_cancelar);
		add(jtp_errores);
		add(jb_eliminar_ultimo);

	}
	
	private void llenarComboBoxEstaciones() {
		
		for(Estacion e : GestorJDBC.buscarEstacion("","","","",null)) {
			jcb_estacion_origen_contenido.addElement(e);
			jcb_estacion_destino_contenido.addElement(e);
		}
		
		//// for debugging purposes
		/*for(char x='A';x<'F';x++) {
			Estacion e = GestorEntidades.crearEstacion(""+x,"13:00","20:00",EstadoEstacion.OPERATIVA);
			jcb_estacion_origen_contenido.addElement(e);
			jcb_estacion_destino_contenido.addElement(e);
		}*/
	}
	
	private void agregarActionListener() {
		
		jb_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jcb_estacion_origen.setEnabled(true);
				jb_eliminar_ultimo.setEnabled(false);
				limpiarTabla();
				ventana_contenedora.cambiarPanel(VentanaPrincipal.GEST_LINEA);
			}
		});
		
		jcb_estacion_origen.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(jcb_estacion_destino.getSelectedIndex() != -1)
					jb_agregar_trayecto.setEnabled(true);
			}
		});
		
		jcb_estacion_destino.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(jcb_estacion_origen.getSelectedIndex() != -1)
					jb_agregar_trayecto.setEnabled(true);
			}
		});
		
		jb_agregar_trayecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estacion origen = (Estacion)jcb_estacion_origen.getSelectedItem();
				Estacion destino = (Estacion)jcb_estacion_destino.getSelectedItem();
				
				
				try {
					GestorValidaciones.validarTrayecto(origen,destino,jtf_costo.getText());
					jtp_errores.setText("");
					
					Trayecto t = GestorEntidades.crearTrayecto(origen,destino,(Integer)jspin_duracion.getValue(),(Integer)jspin_capacidad_maxima.getValue(),(Integer)jspin_duracion.getValue(),jtf_costo.getText());
					jtable_trayectos_contenido.addRow(t.asVector());
					objetos_en_tabla.add(t);
					
					// fijar el combobox jcb_origen al destino seleccionado
					jcb_estacion_origen_contenido.removeAllElements();
					jcb_estacion_origen_contenido.addElement(destino);
					jcb_estacion_origen.setEnabled(false);
					jb_eliminar_ultimo.setEnabled(true);
					jb_guardar_recorrido.setEnabled(true);
					
				}
				catch(DatosDeTrayectoIncorrectosException exc){
					jtp_errores.setText(exc.errores);
				}
			}
		});
		
		jb_eliminar_ultimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer ultimo_indice = jtable_trayectos_contenido.getRowCount();
				jtable_trayectos_contenido.removeRow(ultimo_indice-1);
				objetos_en_tabla.remove(ultimo_indice-1);
				
				if(jtable_trayectos_contenido.getRowCount()==0) {
					jb_eliminar_ultimo.setEnabled(false);
					jb_guardar_recorrido.setEnabled(false);
					jcb_estacion_origen.setEnabled(true);
				}
			}
		});
		
		jb_guardar_recorrido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer opcion = VentanaPrincipal.popupConfirmar("Esta seguro que desea registrar el recorrido?", "Guardar Recorrido");
				if(opcion == JOptionPane.YES_OPTION) {
					GestorJDBC.agregarRecorrido(linea_seleccionada, (Trayecto[]) objetos_en_tabla.toArray());
					VentanaPrincipal.popupInfo("Nuevo recorrido registrado exitosamente.","Registro exitoso");
				}
			}
		});
	}
	
	public void limpiarTabla() {
		jtable_trayectos_contenido.setRowCount(0);
		objetos_en_tabla.clear();
	}
}
