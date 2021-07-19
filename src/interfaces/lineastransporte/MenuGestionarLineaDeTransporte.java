package interfaces.lineastransporte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import dominio.EstadoLinea;
import dominio.LineaDeTransporte;
import excepciones.DatosDeLineaDeTransporteIncorrectosException;
import gestores.GestorEntidades;
import gestores.GestorValidaciones;
import interfaces.VentanaPrincipal;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class MenuGestionarLineaDeTransporte extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6814597321105357697L;
	
	private JTextField jtf_nombre;
	private JTextField jtf_color;
	private JButton jb_buscar;
	private JButton jb_regresar;
	private JButton jb_alta;
	private JButton jb_eliminar;
	private JComboBox<EstadoLinea> jcb_estado;
	private JButton jb_modificar;
	private JLabel lbl_color;
	private JLabel lbl_nombre;
	private JLabel lbl_estado;
	private JButton jb_registrar_recorrido;
	private JList<LineaDeTransporte> jlist_lineas;
	private DefaultListModel<LineaDeTransporte> jlist_lineas_contenido;
	private VentanaPrincipal ventana_contenedora;
	private JTextPane jtp_errores;
	/**
	 * Create the panel.
	 */
	public MenuGestionarLineaDeTransporte(VentanaPrincipal contenedor) {
		this.ventana_contenedora = contenedor;
		setLayout(null);
		
		jb_buscar = new JButton("Buscar");
		jb_buscar.setBounds(348, 61, 67, 23);
		
		jtf_nombre = new JTextField();
		jtf_nombre.setBounds(72, 13, 89, 20);
		jtf_nombre.setColumns(10);
		
		jb_alta = new JButton("Dar de Alta");
		jb_alta.setBounds(249, 61, 89, 23);
		
		jb_regresar = new JButton("Regresar");
		jb_regresar.setBounds(10, 266, 77, 23);
		
		jb_modificar = new JButton("Modificar");
		jb_modificar.setBounds(232, 266, 77, 23);
		
		jb_eliminar = new JButton("Eliminar");
		jb_eliminar.setBounds(154, 266, 73, 23);
		
		jtf_color = new JTextField();
		jtf_color.setBounds(205, 13, 89, 20);
		jtf_color.setColumns(10);
		
		jcb_estado = new JComboBox<EstadoLinea>();
		jcb_estado.setModel(new DefaultComboBoxModel<EstadoLinea>(new EstadoLinea[] {EstadoLinea.ACTIVA,EstadoLinea.NO_ACTIVA}));
		jcb_estado.setMaximumRowCount(2);
		jcb_estado.setBounds(348, 11, 67, 24);
		
		lbl_color = new JLabel("Color:");
		lbl_color.setBounds(171, 16, 48, 14);
		
		lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setBounds(26, 16, 48, 14);
		
		lbl_estado = new JLabel("Estado:");
		lbl_estado.setBounds(304, 16, 48, 14);
		
		jb_registrar_recorrido = new JButton("Registrar recorrido");
		jb_registrar_recorrido.setBounds(314, 266, 123, 23);
		
		
		jlist_lineas = new JList<LineaDeTransporte>();
		jlist_lineas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlist_lineas.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jlist_lineas.setBounds(26, 95, 389, 150);
		
		jlist_lineas_contenido = new DefaultListModel<LineaDeTransporte>();
		
		jlist_lineas.setModel(jlist_lineas_contenido);
		
		
		this.agregarActionListener();
		add(jb_registrar_recorrido);
		add(lbl_estado);
		add(lbl_nombre);
		add(lbl_color);
		add(jcb_estado);
		add(jb_modificar);
		add(jtf_color);
		add(jb_eliminar);
		add(jb_regresar);
		add(jb_alta);
		add(jb_buscar);
		add(jtf_nombre);
		add(jlist_lineas);
		
		jtp_errores = new JTextPane();
		jtp_errores.setEditable(false);
		jtp_errores.setBackground(UIManager.getColor("Button.background"));
		jtp_errores.setForeground(Color.RED);
		jtp_errores.setBounds(10, 300, 427, 89);
		add(jtp_errores);

	}
	
	private void agregarActionListener() {
		
		jb_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String estado = jcb_estado.getSelectedItem() == null? "" : jcb_estado.getSelectedItem().toString();

				//buscar datos en la BD
				/*ArrayList<LineaDeTransporte> lineas = GestorJDBC.buscarLineasDeTransporte(jtf_nombre.getText(),jtf_color.getText(),estado);
				
				//llenar tabla
				for(LineaDeTransporte linea : lineas) {
					jlist_lineas_contenido.addElement(linea);
				}*/
				
			}
		});
		
		jb_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_contenedora.cambiarPanel(VentanaPrincipal.MENU_PPAL);
			}
		});
		
		jb_modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jlist_lineas.getSelectedIndex()!=-1) {
					ventana_contenedora.cambiarPanel(VentanaPrincipal.EDIT_LINEA);
					MenuRegistrarRecorrido.linea_seleccionada=jlist_lineas.getSelectedValue();
				}
			}
		});
		
		jb_registrar_recorrido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jlist_lineas.getSelectedIndex()!=-1){
					ventana_contenedora.cambiarPanel(VentanaPrincipal.REG_RECORRIDO);
					MenuRegistrarRecorrido.linea_seleccionada=jlist_lineas.getSelectedValue();
				}
			}
		});
		
		jb_eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jlist_lineas.getSelectedIndex()!=-1)
					//// eliminar de la BD TO DO
					System.out.println("BORRAR");
			}
		});
		
		jb_alta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nombre = jtf_nombre.getText();
					String color = jtf_color.getText();
					EstadoLinea estado = (EstadoLinea) jcb_estado.getSelectedItem();
					GestorValidaciones.validarLineaDeTransporte(nombre,color,estado);
					jtp_errores.setText("");
					
					LineaDeTransporte lt = GestorEntidades.crearLineaDeTransporte(nombre,color,estado);
					
					//GestorJDBC.agregarLineaDeTransporte(lt) TO DO
				}
				catch(DatosDeLineaDeTransporteIncorrectosException exc) {
					jtp_errores.setText(exc.errores);
				}
			}
		});
		
	}
}
