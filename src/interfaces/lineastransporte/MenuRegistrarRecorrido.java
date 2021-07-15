package interfaces.lineastransporte;

import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import javax.swing.DropMode;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

public class MenuRegistrarRecorrido extends JPanel {
	private JTextField jtf_costo;
	private JLabel lbl_estacion_origen;
	private JComboBox jcb_estacion_origen;
	private JButton jb_agregar_trayecto;
	private JLabel lbl_estacion_destino;
	private JComboBox jcb_estacion_destino;
	private JSpinner jspin_distancia;
	private JLabel lbl_distancia;
	private JLabel lbl_duracion;
	private JSpinner jspin_duracion;
	private JLabel lbl_capacidad_maxima;
	private JSpinner jspin_capacidad_maxima;
	private JLabel lbl_costo;
	private JComboBox jcb_estado;
	private JLabel lbl_estado;
	private JList jlist_trayectos;
	private JButton jb_guardar_recorrido;
	private JButton jb_cancelar;

	/**
	 * Create the panel.
	 */
	public MenuRegistrarRecorrido() {
		setLayout(null);
		
		lbl_estacion_origen = new JLabel("Estacion origen:");
		lbl_estacion_origen.setBounds(20, 16, 92, 14);
		add(lbl_estacion_origen);
		
		jcb_estacion_origen = new JComboBox();
		jcb_estacion_origen.setBounds(109, 11, 99, 24);
		add(jcb_estacion_origen);
		
		jb_agregar_trayecto = new JButton("Agregar trayecto al recorrido");
		jb_agregar_trayecto.setBackground(new Color(60, 179, 113));
		jb_agregar_trayecto.setBounds(248, 109, 173, 23);
		add(jb_agregar_trayecto);
		
		jcb_estacion_destino = new JComboBox();
		jcb_estacion_destino.setBounds(329, 11, 99, 24);
		add(jcb_estacion_destino);
		
		lbl_estacion_destino = new JLabel("Estacion destino:");
		lbl_estacion_destino.setBounds(227, 16, 92, 14);
		add(lbl_estacion_destino);
		
		jspin_distancia = new JSpinner();
		jspin_distancia.setToolTipText("Distancia entre las estaciones en Km");
		jspin_distancia.setModel(new SpinnerNumberModel(1, 1, 50, 1));
		jspin_distancia.setBounds(159, 46, 49, 20);
		add(jspin_distancia);
		
		lbl_distancia = new JLabel("Distancia [Km] :");
		lbl_distancia.setBounds(20, 49, 82, 14);
		add(lbl_distancia);
		
		lbl_duracion = new JLabel("Duracion [minutos]:");
		lbl_duracion.setBounds(20, 80, 105, 14);
		add(lbl_duracion);
		
		jspin_duracion = new JSpinner();
		jspin_duracion.setModel(new SpinnerNumberModel(1, 1, 120, 1));
		jspin_duracion.setToolTipText("Distancia entre las estaciones en Km");
		jspin_duracion.setBounds(159, 77, 49, 20);
		add(jspin_duracion);
		
		lbl_capacidad_maxima = new JLabel("Capacidad maxima [pasajeros]:");
		lbl_capacidad_maxima.setBounds(227, 49, 151, 14);
		add(lbl_capacidad_maxima);
		
		jspin_capacidad_maxima = new JSpinner();
		jspin_capacidad_maxima.setModel(new SpinnerNumberModel(1, 1, 200, 1));
		jspin_capacidad_maxima.setToolTipText("Distancia entre las estaciones en Km");
		jspin_capacidad_maxima.setBounds(379, 46, 49, 20);
		add(jspin_capacidad_maxima);
		
		jtf_costo = new JTextField();
		jtf_costo.setBounds(329, 77, 99, 20);
		add(jtf_costo);
		jtf_costo.setColumns(10);
		
		lbl_costo = new JLabel("Costo [$]:");
		lbl_costo.setBounds(227, 80, 57, 14);
		add(lbl_costo);
		
		jcb_estado = new JComboBox();
		jcb_estado.setModel(new DefaultComboBoxModel(new String[] {"Activa", "Inactiva"}));
		jcb_estado.setMaximumRowCount(2);
		jcb_estado.setBounds(109, 108, 99, 24);
		add(jcb_estado);
		
		lbl_estado = new JLabel("Estado:");
		lbl_estado.setBounds(20, 113, 46, 14);
		add(lbl_estado);
		
		jlist_trayectos = new JList();
		jlist_trayectos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlist_trayectos.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		jlist_trayectos.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jlist_trayectos.setBounds(20, 143, 408, 100);
		add(jlist_trayectos);
		
		jb_guardar_recorrido = new JButton("Guardar recorrido");
		jb_guardar_recorrido.setBounds(311, 254, 117, 23);
		add(jb_guardar_recorrido);
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(23, 254, 89, 23);
		add(jb_cancelar);

	}
}
