package interfaces;

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

public class MenuGestionarEstaciones extends JPanel {
	private JTextField jtf_nombre;
	private JTextField jtf_apertura;
	private JTextField jtf_cierre;

	/**
	 * Create the panel.
	 */
	public MenuGestionarEstaciones() {
		
		JButton jb_buscar = new JButton("Buscar");
		jb_buscar.setBounds(454, 59, 67, 23);
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
		
		JButton jb_alta = new JButton("Dar de Alta");
		jb_alta.setBounds(438, 171, 89, 23);
		jb_alta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton jb_regresar = new JButton("Regresar");
		jb_regresar.setBounds(25, 25, 77, 23);
		add(jb_regresar);
		add(jb_alta);
		
		JButton jb_modificar = new JButton("Modificar");
		jb_modificar.setBounds(438, 239, 77, 23);
		jb_modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton jb_eliminar = new JButton("Eliminar");
		jb_eliminar.setBounds(438, 205, 73, 23);
		add(jb_eliminar);
		
		jtf_cierre = new JTextField();
		jtf_cierre.setBounds(115, 99, 106, 20);
		add(jtf_cierre);
		jtf_cierre.setColumns(10);
		
		jtf_apertura = new JTextField();
		jtf_apertura.setBounds(115, 130, 106, 20);
		add(jtf_apertura);
		jtf_apertura.setColumns(10);
		
		JComboBox cb_estado = new JComboBox();
		cb_estado.setBounds(115, 161, 106, 24);
		add(cb_estado);
		add(jb_modificar);
		
		JLabel lblNewLabel = new JLabel("Cierre:");
		lblNewLabel.setBounds(54, 102, 48, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(57, 71, 48, 14);
		add(lblNewLabel_1);
		
		JLabel lblApertura = new JLabel("Apertura:");
		lblApertura.setBounds(54, 133, 48, 14);
		add(lblApertura);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(54, 166, 48, 14);
		add(lblEstado);

	}
}
