package interfaces.boletos;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import interfaces.VentanaPrincipal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuSeleccionarRecorrido extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4060984994480535340L;
	
	private JTable jtable_recorridos;
	private JLabel lbl_estacion_origen;
	private JComboBox<String> jcb_estacion_origen;
	private JComboBox<String> jcb_estacion_destino;
	private JLabel lbl_estacion_destino;
	private JButton jb_siguiente;
	private JButton jb_cancelar;
	private VentanaPrincipal ventana_contenedora;

	/**
	 * Create the panel.
	 */
	public MenuSeleccionarRecorrido(VentanaPrincipal contenedor) {
		this.ventana_contenedora = contenedor;
		setLayout(null);
		
		lbl_estacion_origen = new JLabel("Estacion origen:");
		lbl_estacion_origen.setBounds(20, 16, 92, 14);
		
		
		jcb_estacion_origen = new JComboBox<String>();
		jcb_estacion_origen.setBounds(109, 11, 99, 24);
		
		
		jcb_estacion_destino = new JComboBox<String>();
		jcb_estacion_destino.setBounds(329, 11, 99, 24);
		
		
		lbl_estacion_destino = new JLabel("Estacion destino:");
		lbl_estacion_destino.setBounds(227, 16, 92, 14);
		
		
		jb_siguiente = new JButton("Siguiente");
		jb_siguiente.setBounds(311, 254, 117, 23);
		
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(23, 254, 89, 23);
		
		
		jtable_recorridos = new JTable();
		jtable_recorridos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Duracion [minutos]", "Distancia [Km]", "Precio [$]"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true
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
		
		this.agregarActionListener();
		add(jcb_estacion_origen);
		add(jb_cancelar);
		add(jb_siguiente);
		add(lbl_estacion_destino);
		add(jcb_estacion_destino);
		add(lbl_estacion_origen);
		add(jtable_recorridos);

	}
	
	private void agregarActionListener() {
		jb_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_contenedora.cambiarPanel(VentanaPrincipal.MENU_PPAL);
			}
		});
		
		jb_siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_contenedora.cambiarPanel(VentanaPrincipal.REG_COMPRADOR);
			}
		});
	}
}
