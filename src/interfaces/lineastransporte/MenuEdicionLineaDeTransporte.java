package interfaces.lineastransporte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import excepciones.DatosDeLineaDeTransporteIncorrectosException;
import gestores.GestorEntidades;
import gestores.GestorValidaciones;
import interfaces.VentanaPrincipal;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import dominio.EstadoLinea;
import dominio.LineaDeTransporte;

import java.awt.Color;

public class MenuEdicionLineaDeTransporte extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 82309887738866888L;
	
	private JTextField jtf_nombre;
	private JTextField jtf_color;
	private JButton jb_guardar_cambios;
	private JButton jb_cancelar;
	private JLabel lbl_nombre;
	private JLabel lbl_color;
	private JLabel lbl_estado;
	private JComboBox<EstadoLinea> jcb_estado;
	private VentanaPrincipal ventana_contenedora;
	private JTextPane jtp_errores;
	protected static LineaDeTransporte linea_seleccionada;
	/**
	 * Create the panel.
	 */
	public MenuEdicionLineaDeTransporte(VentanaPrincipal contenedor) {
		this.ventana_contenedora = contenedor;
		setLayout(null);
		
		jb_guardar_cambios = new JButton("Guardar Cambios");
		jb_guardar_cambios.setBounds(450, 266, 140, 23);
		
		jb_cancelar = new JButton("Cancelar");
		jb_cancelar.setBounds(27, 266, 89, 23);
		
		jtf_nombre = new JTextField();
		jtf_nombre.setBounds(262, 84, 200, 20);
		jtf_nombre.setColumns(10);
		
		jtf_color = new JTextField();
		jtf_color.setBounds(262, 115, 200, 20);
		jtf_color.setColumns(10);
		
		lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setBounds(149, 87, 82, 14);
		
		lbl_color = new JLabel("Color:");
		lbl_color.setBounds(149, 118, 102, 14);
		
		lbl_estado = new JLabel("Estado:");
		lbl_estado.setBounds(149, 151, 82, 14);
		
		jcb_estado = new JComboBox<EstadoLinea>();
		jcb_estado.setModel(new DefaultComboBoxModel<EstadoLinea>(new EstadoLinea[] {EstadoLinea.ACTIVA,EstadoLinea.NO_ACTIVA}));
		jcb_estado.setMaximumRowCount(2);
		jcb_estado.setBounds(322, 146, 140, 24);
		
		jtp_errores = new JTextPane();
		jtp_errores.setEditable(false);
		jtp_errores.setForeground(Color.RED);
		jtp_errores.setBackground(UIManager.getColor("Button.background"));
		jtp_errores.setBounds(10, 303, 430, 86);
		
		this.agregarActionListener();
		add(lbl_estado);
		add(lbl_color);
		add(lbl_nombre);
		add(jtf_color);
		add(jtf_nombre);
		add(jb_cancelar);
		add(jb_guardar_cambios);
		add(jcb_estado);
		add(jtp_errores);
		
		
	}
	
	private void agregarActionListener() {
		jb_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_contenedora.cambiarPanel(VentanaPrincipal.GEST_LINEA);
			}
		});
		
		jb_guardar_cambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nombre = jtf_nombre.getText();
					String color = jtf_color.getText();
					EstadoLinea estado = (EstadoLinea) jcb_estado.getSelectedItem();
					GestorValidaciones.validarLineaDeTransporte(nombre,color,estado);
					jtp_errores.setText("");
					
					Integer opcion = VentanaPrincipal.popupConfirmar("Esta seguro que desea guardar los cambios?", "Guardar cambios");
					if(opcion == JOptionPane.YES_OPTION) {
						GestorEntidades.actualizarLinea(linea_seleccionada,nombre,color,estado);
						//GestorJDBC.actualizarLineaDeTransporte(linea_seleccionada) TODO
						VentanaPrincipal.popupInfo("Camios realizados exitosamente.", "Cambio exitoso");
						ventana_contenedora.cambiarPanel(VentanaPrincipal.GEST_LINEA);
					}
				}
				catch(DatosDeLineaDeTransporteIncorrectosException exc) {
					jtp_errores.setText(exc.errores);
				}
			}
		});
	}

	public void llenarCampos() {
		jtf_nombre.setText(linea_seleccionada.getNombre());
		jtf_color.setText(linea_seleccionada.getColor());
		jcb_estado.setSelectedItem(linea_seleccionada.getEstado());
	}
}
