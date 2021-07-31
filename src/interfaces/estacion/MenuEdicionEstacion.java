package interfaces.estacion;

import javax.swing.*;
import interfaces.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dominio.EstadoEstacion;
import excepciones.DatosDeEstacionIncorrectosException;
import gestores.*;
import java.awt.Color;

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
	private JComboBox<EstadoEstacion> jcb_estado;
	private VentanaPrincipal ventana_contenedora;
	private JTextPane jtp_errores;
	/**
	 * Create the panel.
	 */
	public MenuEdicionEstacion(VentanaPrincipal contenedor) {
		this.ventana_contenedora = contenedor;
		setLayout(null);
		
		jb_guardar_cambios = new JButton("Guardar Cambios");
		jb_guardar_cambios.setBounds(460, 266, 150, 23);
		
		jb_regresar = new JButton("Regresar");
		jb_regresar.setBounds(10, 266, 89, 23);
				
		jtf_nombre = new JTextField();
		jtf_nombre.setBounds(250, 66, 200, 20);
		jtf_nombre.setColumns(10);
		
		jtf_horario_apertura = new JTextField();
		jtf_horario_apertura.setBounds(330, 108, 120, 20);
		jtf_horario_apertura.setColumns(10);
		
		jtf_horario_cierre = new JTextField();
		jtf_horario_cierre.setBounds(330, 153, 120, 20);
		jtf_horario_cierre.setColumns(10);
		
		lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setBounds(159, 69, 80, 14);
		
		lbl_horario_apertura = new JLabel("Horario de apertura:");
		lbl_horario_apertura.setBounds(159, 111, 150, 14);
		
		lbl_horario_cierre = new JLabel("Horario de cierre:");
		lbl_horario_cierre.setBounds(159, 156, 150, 14);
		
		lbl_estado = new JLabel("Estado:");
		lbl_estado.setBounds(159, 196, 46, 14);
	
		jcb_estado = new JComboBox<EstadoEstacion>();
		jcb_estado.setModel(new DefaultComboBoxModel<EstadoEstacion>(new EstadoEstacion[] {EstadoEstacion.OPERATIVA, EstadoEstacion.EN_MANTENIMIENTO}));
		jcb_estado.setMaximumRowCount(2);
		jcb_estado.setBounds(330, 191, 120, 24);
		
		jtp_errores = new JTextPane();
		jtp_errores.setEditable(false);
		jtp_errores.setForeground(Color.RED);
		jtp_errores.setBackground(UIManager.getColor("Button.background"));
		jtp_errores.setBounds(10, 301, 600, 88);
		
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
		add(jtp_errores);
	}
	
	private void agregarActionListener() {
		jb_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_contenedora.cambiarPanel(VentanaPrincipal.GEST_ESTACIONES);
			}
		});
		
		jb_guardar_cambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = jtf_nombre.getText();
				String apertura = jtf_horario_apertura.getText();
				String cierre = jtf_horario_cierre.getText();
				EstadoEstacion estado = (EstadoEstacion)jcb_estado.getSelectedItem();
				try {
					GestorValidaciones.validarEstacion(nombre,apertura,cierre);
					jtp_errores.setText("");
					
					Integer opcion = VentanaPrincipal.popupConfirmar("Esta seguro que desea guadar los cambios?", "Confirmar Cambios");
					
					if(opcion == JOptionPane.YES_OPTION) {
						EstadoEstacion estado_viejo = MenuGestionarEstaciones.estacion_seleccionada.getEstado();
						GestorEntidades.actualizarEstacion(MenuGestionarEstaciones.estacion_seleccionada,nombre,apertura,cierre,estado);
						GestorJDBC.actualizarEstacion(MenuGestionarEstaciones.estacion_seleccionada);
						GestorEntidades.gestionarNuevoEstadoEstacion(MenuGestionarEstaciones.estacion_seleccionada,estado_viejo, estado);
						VentanaPrincipal.popupInfo("Se guardaron los cambios exitosamente.", "Cambios Guardados");
						ventana_contenedora.cambiarPanel(VentanaPrincipal.GEST_ESTACIONES);	
					}
				}
				catch(DatosDeEstacionIncorrectosException exp) {
					jtp_errores.setText(exp.errores);
				}
			}
		});
	}
	
	public void llenarCampos() {
		jtf_nombre.setText(MenuGestionarEstaciones.estacion_seleccionada.getNombre());
		jcb_estado.setSelectedItem(MenuGestionarEstaciones.estacion_seleccionada.getEstado());
		jtf_horario_apertura.setText(MenuGestionarEstaciones.estacion_seleccionada.getHorario_apertura());
		jtf_horario_cierre.setText(MenuGestionarEstaciones.estacion_seleccionada.getHorario_cierre());
	}
}
