package interfaces.lineastransporte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import interfaces.VentanaPrincipal;

public class MenuGestionarLineaDeTransporte extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6814597321105357697L;
	
	private JTextField jtf_nombre;
	private JTextField jtf_cierre;
	private JButton jb_buscar;
	private JButton jb_regresar;
	private JButton jb_alta;
	private JButton jb_eliminar;
	private JComboBox<String> jcb_estado;
	private JButton jb_modificar;
	private JLabel lbl_color;
	private JLabel lbl_nombre;
	private JLabel lbl_estado;
	private JButton jb_registrar_recorrido;
	private JTable table;
	private VentanaPrincipal ventana_contenedora;
	/**
	 * Create the panel.
	 */
	public MenuGestionarLineaDeTransporte(VentanaPrincipal contenedor) {
		this.ventana_contenedora = contenedor;
		setLayout(null);
		
		jb_buscar = new JButton("Buscar");
		jb_buscar.setBounds(348, 61, 67, 23);
		
		jtf_nombre = new JTextField();
		jtf_nombre.setBounds(72, 13, 89, 20);
		jtf_nombre.setColumns(10);
		
		jb_alta = new JButton("Dar de Alta");
		jb_alta.setBounds(249, 61, 89, 23);
		
		jb_regresar = new JButton("Regresar");
		jb_regresar.setBounds(10, 266, 77, 23);
		
		jb_modificar = new JButton("Modificar");
		jb_modificar.setBounds(232, 266, 77, 23);
		
		jb_eliminar = new JButton("Eliminar");
		jb_eliminar.setBounds(154, 266, 73, 23);
		
		jtf_cierre = new JTextField();
		jtf_cierre.setBounds(205, 13, 89, 20);
		jtf_cierre.setColumns(10);
		
		jcb_estado = new JComboBox<String>();
		jcb_estado.setModel(new DefaultComboBoxModel<String>(new String[] {"Activa", "No Activa"}));
		jcb_estado.setMaximumRowCount(2);
		jcb_estado.setBounds(348, 11, 67, 24);
		
		lbl_color = new JLabel("Color:");
		lbl_color.setBounds(171, 16, 48, 14);
		
		lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setBounds(26, 16, 48, 14);
		
		lbl_estado = new JLabel("Estado:");
		lbl_estado.setBounds(304, 16, 48, 14);
		
		jb_registrar_recorrido = new JButton("Registrar recorrido");
		jb_registrar_recorrido.setBounds(314, 266, 123, 23);
		
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Color", "Estado"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		table.setBounds(26, 95, 389, 150);
		
		this.agregarActionListener();
		add(jb_registrar_recorrido);
		add(lbl_estado);
		add(lbl_nombre);
		add(lbl_color);
		add(jcb_estado);
		add(jb_modificar);
		add(jtf_cierre);
		add(jb_eliminar);
		add(jb_regresar);
		add(jb_alta);
		add(jb_buscar);
		add(jtf_nombre);
		add(table);

	}
	
	private void agregarActionListener() {
		jb_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_contenedora.cambiarPanel(VentanaPrincipal.MENU_PPAL);
			}
		});
	}
}
