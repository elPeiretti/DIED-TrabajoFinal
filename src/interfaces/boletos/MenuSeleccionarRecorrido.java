package interfaces.boletos;

import java.awt.Color;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MenuSeleccionarRecorrido extends JPanel {
	private JTable jtable_recorridos;
	private JLabel lbl_estacion_origen;
	private JComboBox jcb_estacion_origen;
	private JComboBox jcb_estacion_destino;
	private JLabel lbl_estacion_destino;
	private JButton jb_siguiente;
	private JButton jb_cancelar;

	/**
	 * Create the panel.
	 */
	public MenuSeleccionarRecorrido() {
		setLayout(null);
		
		lbl_estacion_origen = new JLabel("Estacion origen:");
		lbl_estacion_origen.setBounds(20, 16, 92, 14);
		add(lbl_estacion_origen);
		
		jcb_estacion_origen = new JComboBox();
		jcb_estacion_origen.setBounds(109, 11, 99, 24);
		add(jcb_estacion_origen);
		
		jcb_estacion_destino = new JComboBox();
		jcb_estacion_destino.setBounds(329, 11, 99, 24);
		add(jcb_estacion_destino);
		
		lbl_estacion_destino = new JLabel("Estacion destino:");
		lbl_estacion_destino.setBounds(227, 16, 92, 14);
		add(lbl_estacion_destino);
		
		jb_siguiente = new JButton("Siguiente");
		jb_siguiente.setBounds(311, 254, 117, 23);
		add(jb_siguiente);
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(23, 254, 89, 23);
		add(jb_cancelar);
		
		jtable_recorridos = new JTable();
		jtable_recorridos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Duracion [minutos]", "Distancia [Km]", "Precio [$]"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Integer.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		jtable_recorridos.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtable_recorridos.getColumnModel().getColumn(1).setResizable(false);
		jtable_recorridos.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtable_recorridos.getColumnModel().getColumn(2).setResizable(false);
		jtable_recorridos.getColumnModel().getColumn(2).setPreferredWidth(70);
		jtable_recorridos.setBounds(30, 43, 400, 200);
		add(jtable_recorridos);

	}
}
