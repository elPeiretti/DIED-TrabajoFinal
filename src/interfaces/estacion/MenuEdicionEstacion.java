package interfaces.estacion;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interfaces.VentanaPrincipal;

import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;

public class MenuEdicionEstacion extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8724189951605684229L;
	
	private JTextField jtf_nombre;
	private JTextField jtf_horario_apertura;
	private JTextField jtf_horario_cierre;
	private JButton jb_guardar_cambios;
	private JButton jb_regresar;
	private JLabel lbl_nombre;
	private JLabel lbl_horario_apertura;
	private JLabel lbl_horario_cierre;
	private JLabel lbl_estado;
	private JComboBox<String> jcb_estado;
	private VentanaPrincipal ventana_contenedora;
	/**
	 * Create the panel.
	 */
	public MenuEdicionEstacion(VentanaPrincipal contenedor) {
		this.ventana_contenedora = contenedor;
		setLayout(null);
		
		jb_guardar_cambios = new JButton("Guardar Cambios");
		jb_guardar_cambios.setBounds(325, 266, 115, 23);
		
		jb_regresar = new JButton("Regresar");
		jb_regresar.setBounds(27, 266, 89, 23);
				
		jtf_nombre = new JTextField();
		jtf_nombre.setBounds(183, 68, 86, 20);
		jtf_nombre.setColumns(10);
		
		jtf_horario_apertura = new JTextField();
		jtf_horario_apertura.setBounds(183, 110, 86, 20);
		jtf_horario_apertura.setColumns(10);
		
		jtf_horario_cierre = new JTextField();
		jtf_horario_cierre.setBounds(183, 155, 86, 20);
		jtf_horario_cierre.setColumns(10);
		
		lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setBounds(70, 71, 46, 14);
		
		lbl_horario_apertura = new JLabel("Horario de apertura:");
		lbl_horario_apertura.setBounds(70, 113, 102, 14);
		
		lbl_horario_cierre = new JLabel("Horario de cierre:");
		lbl_horario_cierre.setBounds(70, 158, 97, 14);
		
		lbl_estado = new JLabel("Estado:");
		lbl_estado.setBounds(70, 198, 46, 14);
	
		jcb_estado = new JComboBox<String>();
		jcb_estado.setModel(new DefaultComboBoxModel<String>(new String[] {"Operativa", "En Mantenimiento"}));
		jcb_estado.setMaximumRowCount(2);
		jcb_estado.setBounds(183, 193, 86, 24);
		
		this.agregarActionListener();
		add(jb_guardar_cambios);
		add(jb_regresar);
		add(jtf_nombre);
		add(jtf_horario_apertura);
		add(jtf_horario_cierre);
		add(lbl_nombre);
		add(lbl_horario_apertura);
		add(lbl_horario_cierre);
		add(lbl_estado);
		add(jcb_estado);
	}
	
	private void agregarActionListener() {
		jb_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//falta logica para recuperar el estado anterior
				ventana_contenedora.cambiarPanel(VentanaPrincipal.GEST_ESTACIONES);
			}
		});
	}
}
