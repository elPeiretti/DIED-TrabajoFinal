package interfaces.lineastransporte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interfaces.VentanaPrincipal;

public class MenuEdicionLineaDeTransporte extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 82309887738866888L;
	
	private JTextField jtf_nombre;
	private JTextField jtf_horario_apertura;
	private JButton jb_guardar_cambios;
	private JButton jb_cancelar;
	private JLabel lbl_nombre;
	private JLabel lbl_color;
	private JLabel lbl_estado;
	private JComboBox<String> jcb_estado;
	private VentanaPrincipal ventana_contenedora;
	/**
	 * Create the panel.
	 */
	public MenuEdicionLineaDeTransporte(VentanaPrincipal contenedor) {
		this.ventana_contenedora = contenedor;
		setLayout(null);
		
		jb_guardar_cambios = new JButton("Guardar Cambios");
		jb_guardar_cambios.setBounds(325, 266, 115, 23);
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(27, 266, 89, 23);
		
		jtf_nombre = new JTextField();
		jtf_nombre.setBounds(183, 68, 86, 20);
		jtf_nombre.setColumns(10);
		
		jtf_horario_apertura = new JTextField();
		jtf_horario_apertura.setBounds(183, 110, 86, 20);
		jtf_horario_apertura.setColumns(10);
		
		lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setBounds(70, 71, 46, 14);
		
		lbl_color = new JLabel("Color:");
		lbl_color.setBounds(70, 113, 102, 14);
		
		lbl_estado = new JLabel("Estado:");
		lbl_estado.setBounds(70, 150, 46, 14);
		
		jcb_estado = new JComboBox<String>();
		jcb_estado.setModel(new DefaultComboBoxModel<String>(new String[] {"Activa", "No Activa"}));
		jcb_estado.setMaximumRowCount(2);
		jcb_estado.setBounds(183, 145, 86, 24);
		
		this.agregarActionListener();
		add(lbl_estado);
		add(lbl_color);
		add(lbl_nombre);
		add(jtf_horario_apertura);
		add(jtf_nombre);
		add(jb_cancelar);
		add(jb_guardar_cambios);
		add(jcb_estado);
	}
	
	private void agregarActionListener() {
		jb_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_contenedora.cambiarPanel(VentanaPrincipal.GEST_LINEA);
			}
		});
	}

}
