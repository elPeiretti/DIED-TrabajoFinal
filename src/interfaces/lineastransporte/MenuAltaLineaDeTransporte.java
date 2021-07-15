package interfaces.lineastransporte;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuAltaLineaDeTransporte extends JPanel {

	private JTextField jtf_nombre;
	private JTextField jtf_horario_apertura;

	/**
	 * Create the panel.
	 */
	public MenuAltaLineaDeTransporte() {
		setLayout(null);
		
		JButton jb_dar_de_alta = new JButton("Dar de Alta");
		jb_dar_de_alta.setBounds(351, 266, 89, 23);
		add(jb_dar_de_alta);
		
		JButton jb_regresar = new JButton("Regresar");
		jb_regresar.setBounds(27, 266, 89, 23);
		add(jb_regresar);
		
		jtf_nombre = new JTextField();
		jtf_nombre.setBounds(261, 105, 86, 20);
		add(jtf_nombre);
		jtf_nombre.setColumns(10);
		
		jtf_horario_apertura = new JTextField();
		jtf_horario_apertura.setBounds(261, 136, 86, 20);
		add(jtf_horario_apertura);
		jtf_horario_apertura.setColumns(10);
		
		JLabel lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setBounds(142, 108, 46, 14);
		add(lbl_nombre);
		
		JLabel lbl_color = new JLabel("Color:");
		lbl_color.setBounds(142, 139, 102, 14);
		add(lbl_color);

	}

}
