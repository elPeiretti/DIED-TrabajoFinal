package interfaces.lineastransporte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class MenuGestionarLineaDeTransporte extends JPanel {
	
	private JTextField jtf_nombre;
	private JTextField jtf_cierre;
	/**
	 * Create the panel.
	 */
	public MenuGestionarLineaDeTransporte() {
		JButton jb_buscar = new JButton("Buscar");
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
		
		JButton jb_alta = new JButton("Dar de Alta");
		jb_alta.setBounds(353, 81, 89, 23);
		jb_alta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton jb_regresar = new JButton("Regresar");
		jb_regresar.setBounds(31, 219, 77, 23);
		add(jb_regresar);
		add(jb_alta);
		
		JButton jb_modificar = new JButton("Modificar");
		jb_modificar.setBounds(365, 178, 77, 23);
		jb_modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton jb_eliminar = new JButton("Eliminar");
		jb_eliminar.setBounds(364, 127, 73, 23);
		add(jb_eliminar);
		
		jtf_cierre = new JTextField();
		jtf_cierre.setBounds(115, 99, 106, 20);
		add(jtf_cierre);
		jtf_cierre.setColumns(10);
		
		JComboBox jcb_estado = new JComboBox();
		jcb_estado.setModel(new DefaultComboBoxModel(new String[] {"Activa", "No Activa"}));
		jcb_estado.setMaximumRowCount(2);
		jcb_estado.setBounds(115, 126, 106, 24);
		add(jcb_estado);
		add(jb_modificar);
		
		JLabel lbl_color = new JLabel("Color:");
		lbl_color.setBounds(54, 102, 48, 14);
		add(lbl_color);
		
		JLabel lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setBounds(57, 71, 48, 14);
		add(lbl_nombre);
		
		JLabel lbl_estado = new JLabel("Estado:");
		lbl_estado.setBounds(54, 131, 48, 14);
		add(lbl_estado);
		
		JButton jb_registrar_recorrido = new JButton("Registrar recorrido");
		jb_registrar_recorrido.setBounds(319, 219, 123, 23);
		add(jb_registrar_recorrido);

	}
}
