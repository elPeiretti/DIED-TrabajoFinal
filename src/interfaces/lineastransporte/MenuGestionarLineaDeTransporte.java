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
	/**
	 * Create the panel.
	 */
	public MenuGestionarLineaDeTransporte() {
		jb_buscar = new JButton("Buscar");
		jb_buscar.setBounds(348, 61, 67, 23);
		jb_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		setLayout(null);
		
		jtf_nombre = new JTextField();
		jtf_nombre.setBounds(72, 13, 89, 20);
		add(jtf_nombre);
		jtf_nombre.setColumns(10);
		add(jb_buscar);
		
		jb_alta = new JButton("Dar de Alta");
		jb_alta.setBounds(249, 61, 89, 23);
		jb_alta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		jb_regresar = new JButton("Regresar");
		jb_regresar.setBounds(10, 266, 77, 23);
		add(jb_regresar);
		add(jb_alta);
		
		jb_modificar = new JButton("Modificar");
		jb_modificar.setBounds(232, 266, 77, 23);
		jb_modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		jb_eliminar = new JButton("Eliminar");
		jb_eliminar.setBounds(154, 266, 73, 23);
		add(jb_eliminar);
		
		jtf_cierre = new JTextField();
		jtf_cierre.setBounds(205, 13, 89, 20);
		add(jtf_cierre);
		jtf_cierre.setColumns(10);
		
		jcb_estado = new JComboBox<String>();
		jcb_estado.setModel(new DefaultComboBoxModel<String>(new String[] {"Activa", "No Activa"}));
		jcb_estado.setMaximumRowCount(2);
		jcb_estado.setBounds(348, 11, 67, 24);
		add(jcb_estado);
		add(jb_modificar);
		
		lbl_color = new JLabel("Color:");
		lbl_color.setBounds(171, 16, 48, 14);
		add(lbl_color);
		
		lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setBounds(26, 16, 48, 14);
		add(lbl_nombre);
		
		lbl_estado = new JLabel("Estado:");
		lbl_estado.setBounds(304, 16, 48, 14);
		add(lbl_estado);
		
		jb_registrar_recorrido = new JButton("Registrar recorrido");
		jb_registrar_recorrido.setBounds(314, 266, 123, 23);
		add(jb_registrar_recorrido);
		
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
		add(table);

	}
}
