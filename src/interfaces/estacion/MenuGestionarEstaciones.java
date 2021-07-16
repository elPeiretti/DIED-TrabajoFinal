package interfaces.estacion;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import interfaces.VentanaPrincipal;

public class MenuGestionarEstaciones extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4997700988942456105L;
	
	private JTextField jtf_nombre;
	private JTextField jtf_apertura;
	private JTextField jtf_cierre;
	private JButton jb_buscar;
	private JButton jb_alta;
	private JButton jb_regresar;
	private JButton jb_eliminar;
	private JComboBox<String> jcb_estado;
	private JButton jb_modificar;
	private JLabel lbl_horario_cierre;
	private JLabel lbl_nombre;
	private JLabel lbl_horario_apertura;
	private JLabel lbl_estado;
	private JTable jtable_estaciones;
	private VentanaPrincipal ventana_contenedora;

	/**
	 * Create the panel.
	 */
	public MenuGestionarEstaciones(VentanaPrincipal contenedor) {
		this.ventana_contenedora = contenedor;
		setLayout(null);
		
		jb_buscar = new JButton("Buscar");
		jb_buscar.setBounds(359, 71, 67, 23);

		
		jtf_nombre = new JTextField();
		jtf_nombre.setBounds(115, 8, 89, 20);
		jtf_nombre.setColumns(10);
		
		
		jb_alta = new JButton("Dar de Alta");
		jb_alta.setBounds(260, 71, 89, 23);
		
		jb_regresar = new JButton("Regresar");
		jb_regresar.setBounds(10, 266, 77, 23);
		
		jb_modificar = new JButton("Modificar");
		jb_modificar.setBounds(360, 266, 77, 23);
		
		jb_eliminar = new JButton("Eliminar");
		jb_eliminar.setBounds(277, 266, 73, 23);
		
		jtf_cierre = new JTextField();
		jtf_cierre.setBounds(331, 39, 95, 20);
		jtf_cierre.setColumns(10);
		
		jtf_apertura = new JTextField();
		jtf_apertura.setBounds(115, 39, 89, 20);
		jtf_apertura.setColumns(10);
		
		jcb_estado = new JComboBox<String>();
		jcb_estado.setModel(new DefaultComboBoxModel<String>(new String[] {"Operativa", "En Mantenimiento"}));
		jcb_estado.setMaximumRowCount(2);
		jcb_estado.setBounds(331, 6, 95, 24);
		
		lbl_horario_cierre = new JLabel("Horario de Cierre:");
		lbl_horario_cierre.setBounds(214, 42, 106, 14);
		
		lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setBounds(10, 11, 48, 14);
		
		lbl_horario_apertura = new JLabel("Horario de Apertura:");
		lbl_horario_apertura.setBounds(10, 42, 106, 14);
		
		lbl_estado = new JLabel("Estado:");
		lbl_estado.setBounds(214, 11, 48, 14);
		
		
		jtable_estaciones = new JTable();
		jtable_estaciones.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Estado", "Horario de apertura", "Horario de cierre"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		jtable_estaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtable_estaciones.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jtable_estaciones.setFillsViewportHeight(true);
		jtable_estaciones.setBounds(26, 105, 400, 150);
		
		this.agregarActionListener();
		add(lbl_estado);
		add(lbl_horario_apertura);
		add(lbl_nombre);
		add(lbl_horario_cierre);
		add(jcb_estado);
		add(jb_modificar);
		add(jtf_apertura);
		add(jtf_cierre);
		add(jb_eliminar);
		add(jb_regresar);
		add(jb_alta);
		add(jb_buscar);
		add(jtf_nombre);
		add(jtable_estaciones);

	}
	
	private void agregarActionListener() {
		jb_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_contenedora.cambiarPanel(VentanaPrincipal.MENU_PPAL);
			}
		});
	}
}
