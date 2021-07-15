package interfaces.estacion;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MenuEdicionEstacion extends JPanel {
	
	private JTextField jtf_nombre;
	private JTextField jtf_horario_apertura;
	private JTextField jtf_horario_cierre;
	/**
	 * Create the panel.
	 */
	public MenuEdicionEstacion() {
		setLayout(null);
		
		JButton jb_guardar_cambios = new JButton("Guardar Cambios");
		jb_guardar_cambios.setBounds(325, 266, 115, 23);
		add(jb_guardar_cambios);
		
		JButton jb_regresar = new JButton("Regresar");
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
		
		jtf_horario_cierre = new JTextField();
		jtf_horario_cierre.setBounds(183, 155, 86, 20);
		add(jtf_horario_cierre);
		jtf_horario_cierre.setColumns(10);
		
		JLabel lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setBounds(70, 71, 46, 14);
		add(lbl_nombre);
		
		JLabel lbl_horario_apertura = new JLabel("Horario de apertura:");
		lbl_horario_apertura.setBounds(70, 113, 102, 14);
		add(lbl_horario_apertura);
		
		JLabel lbl_horario_cierre = new JLabel("Horario de cierre:");
		lbl_horario_cierre.setBounds(70, 158, 97, 14);
		add(lbl_horario_cierre);
		
		JLabel lbl_estado = new JLabel("Estado:");
		lbl_estado.setBounds(70, 198, 46, 14);
		add(lbl_estado);
		
		JComboBox jcb_estado = new JComboBox();
		jcb_estado.setModel(new DefaultComboBoxModel(new String[] {"Operativa", "En Mantenimiento"}));
		jcb_estado.setMaximumRowCount(2);
		jcb_estado.setBounds(183, 193, 86, 24);
		add(jcb_estado);
	}
}