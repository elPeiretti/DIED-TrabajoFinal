package interfaces.estacion;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MenuGestionarEstaciones extends JPanel {
	private JTextField jtf_nombre;
	private JTextField jtf_apertura;
	private JTextField jtf_cierre;
	private JButton jb_buscar;
	private JButton jb_alta;
	private JButton jb_regresar;
	private JButton jb_eliminar;
	private JComboBox jcb_estado;
	private JButton jb_modificar;
	private JLabel lbl_cierre;
	private JLabel lbl_nombre;
	private JLabel lbl_apertura;
	private JLabel lbl_estado;

	/**
	 * Create the panel.
	 */
	public MenuGestionarEstaciones() {
		
		jb_buscar = new JButton("Buscar");
		jb_buscar.setBounds(118, 219, 67, 23);
		jb_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		setLayout(null);
		
		jtf_nombre = new JTextField();
		jtf_nombre.setBounds(115, 68, 106, 20);
		add(jtf_nombre);
		jtf_nombre.setColumns(10);
		add(jb_buscar);
		
		jb_alta = new JButton("Dar de Alta");
		jb_alta.setBounds(353, 81, 89, 23);
		jb_alta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		jb_regresar = new JButton("Regresar");
		jb_regresar.setBounds(25, 25, 77, 23);
		add(jb_regresar);
		add(jb_alta);
		
		jb_modificar = new JButton("Modificar");
		jb_modificar.setBounds(365, 178, 77, 23);
		jb_modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		jb_eliminar = new JButton("Eliminar");
		jb_eliminar.setBounds(364, 127, 73, 23);
		add(jb_eliminar);
		
		jtf_cierre = new JTextField();
		jtf_cierre.setBounds(115, 99, 106, 20);
		add(jtf_cierre);
		jtf_cierre.setColumns(10);
		
		jtf_apertura = new JTextField();
		jtf_apertura.setBounds(115, 130, 106, 20);
		add(jtf_apertura);
		jtf_apertura.setColumns(10);
		
		jcb_estado = new JComboBox();
		jcb_estado.setModel(new DefaultComboBoxModel(new String[] {"Operativa", "En Mantenimiento"}));
		jcb_estado.setMaximumRowCount(2);
		jcb_estado.setBounds(115, 161, 106, 24);
		add(jcb_estado);
		add(jb_modificar);
		
		lbl_cierre = new JLabel("Cierre:");
		lbl_cierre.setBounds(54, 102, 48, 14);
		add(lbl_cierre);
		
		lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setBounds(57, 71, 48, 14);
		add(lbl_nombre);
		
		lbl_apertura = new JLabel("Apertura:");
		lbl_apertura.setBounds(54, 133, 48, 14);
		add(lbl_apertura);
		
		lbl_estado = new JLabel("Estado:");
		lbl_estado.setBounds(54, 166, 48, 14);
		add(lbl_estado);

	}
}
