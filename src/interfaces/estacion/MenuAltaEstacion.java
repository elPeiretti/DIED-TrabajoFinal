package interfaces.estacion;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

public class MenuAltaEstacion extends JPanel {
	private JTextField jtf_nombre;
	private JTextField jtf_horario_apertura;
	private JTextField jtf_horario_cierre;

	/**
	 * Create the panel.
	 */
	public MenuAltaEstacion() {
		setLayout(null);
		
		JButton jb_dar_de_alta = new JButton("Dar de Alta");
		jb_dar_de_alta.setBounds(351, 266, 89, 23);
		add(jb_dar_de_alta);
		
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

	}
}
