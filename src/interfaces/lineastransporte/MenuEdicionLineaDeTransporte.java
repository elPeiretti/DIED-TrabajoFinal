package interfaces.lineastransporte;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuEdicionLineaDeTransporte extends JPanel {

	private JTextField jtf_nombre;
	private JTextField jtf_horario_apertura;
	private JButton jb_guardar_cambios;
	private JButton jb_regresar;
	private JLabel lbl_nombre;
	private JLabel lbl_color;
	private JLabel lbl_estado;
	private JComboBox jcb_estado;
	/**
	 * Create the panel.
	 */
	public MenuEdicionLineaDeTransporte() {
		setLayout(null);
		
		jb_guardar_cambios = new JButton("Guardar Cambios");
		jb_guardar_cambios.setBounds(325, 266, 115, 23);
		add(jb_guardar_cambios);
		
		jb_regresar = new JButton("Regresar");
		jb_regresar.setBounds(27, 266, 89, 23);
		add(jb_regresar);
		
		jtf_nombre = new JTextField();
		jtf_nombre.setBounds(183, 68, 86, 20);
		add(jtf_nombre);
		jtf_nombre.setColumns(10);
		
		jtf_horario_apertura = new JTextField();
		jtf_horario_apertura.setBounds(183, 110, 86, 20);
		add(jtf_horario_apertura);
		jtf_horario_apertura.setColumns(10);
		
		lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setBounds(70, 71, 46, 14);
		add(lbl_nombre);
		
		lbl_color = new JLabel("Color:");
		lbl_color.setBounds(70, 113, 102, 14);
		add(lbl_color);
		
		lbl_estado = new JLabel("Estado:");
		lbl_estado.setBounds(70, 150, 46, 14);
		add(lbl_estado);
		
		jcb_estado = new JComboBox();
		jcb_estado.setModel(new DefaultComboBoxModel(new String[] {"Activa", "No Activa"}));
		jcb_estado.setMaximumRowCount(2);
		jcb_estado.setBounds(183, 145, 86, 24);
		add(jcb_estado);
	}

}
