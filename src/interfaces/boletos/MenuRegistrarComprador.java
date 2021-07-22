package interfaces.boletos;

import javax.swing.JPanel;
import javax.swing.JTextField;

import dominio.Camino;
import dominio.Cliente;
import excepciones.DatosDeClienteIncorrectosException;
import gestores.GestorEntidades;
import gestores.GestorJDBC;
import gestores.GestorValidaciones;
import interfaces.VentanaPrincipal;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.UIManager;

public class MenuRegistrarComprador extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 457210643685330418L;
	
	private JTextField jtf_nombre_cliente;
	private JTextField jtf_email_cliente;
	private JLabel lbl_nombre_cliente;
	private JLabel lbl_email_cliente;
	private JButton jb_regresar;
	private JButton jb_registrar_venta;
	private JLabel lbl_origen_destino;
	private JLabel lbl_costo_boleto;
	private VentanaPrincipal ventana_contenedora;
	protected static Camino camino_seleccionado;
	private JLabel lbl_longitud_viaje;
	private JLabel lbl_duracion_viaje;
	private JTextPane jtp_errores;

	/**
	 * Create the panel.
	 */
	public MenuRegistrarComprador(VentanaPrincipal contenedor) {
		this.ventana_contenedora=contenedor;
		setLayout(null);
		
		jtf_nombre_cliente = new JTextField();
		jtf_nombre_cliente.setBounds(248, 77, 86, 20);
		jtf_nombre_cliente.setColumns(10);
		
		lbl_nombre_cliente = new JLabel("Nombre del cliente:");
		lbl_nombre_cliente.setBounds(119, 80, 99, 14);
		
		jtf_email_cliente = new JTextField();
		jtf_email_cliente.setBounds(248, 108, 86, 20);
		jtf_email_cliente.setColumns(10);
		
		lbl_email_cliente = new JLabel("E-Mail del cliente:");
		lbl_email_cliente.setBounds(119, 105, 99, 14);
		
		jb_regresar = new JButton("Regresar");
		jb_regresar.setBounds(10, 266, 89, 23);
		
		jb_registrar_venta = new JButton("Registrar Venta");
		jb_registrar_venta.setBounds(333, 266, 107, 23);
		
		lbl_origen_destino = new JLabel("Viaje desde:");
		lbl_origen_destino.setBounds(79, 166, 292, 14);
		
		lbl_costo_boleto = new JLabel("Costo del boleto: $");
		lbl_costo_boleto.setBounds(79, 191, 292, 14);
		
		lbl_longitud_viaje = new JLabel("Longitud del viaje [km]: ");
		lbl_longitud_viaje.setBounds(79, 241, 292, 14);
		
		lbl_duracion_viaje = new JLabel("Duracion del viaje [min]: ");
		lbl_duracion_viaje.setBounds(79, 216, 292, 14);
		
		jtp_errores = new JTextPane();
		jtp_errores.setEditable(false);
		jtp_errores.setBackground(UIManager.getColor("Button.background"));
		jtp_errores.setForeground(Color.RED);
		jtp_errores.setBounds(20, 300, 400, 100);
		
		this.agregarActionListener();
		add(jtf_nombre_cliente);
		add(lbl_nombre_cliente);
		add(jtf_email_cliente);
		add(lbl_origen_destino);
		add(jb_registrar_venta);
		add(jb_regresar);
		add(lbl_email_cliente);
		add(lbl_costo_boleto);		
		add(lbl_duracion_viaje);
		add(lbl_longitud_viaje);
		add(jtp_errores);
		
	}
	
	public void inicializarLabels() {
		lbl_origen_destino.setText("Viaje desde: "+camino_seleccionado.getOrgien().getNombre()+" - hasta: "+camino_seleccionado.getDestino().getNombre());
		lbl_costo_boleto.setText("Costo del boleto: $"+camino_seleccionado.getCosto().toString());
		lbl_duracion_viaje.setText("Duracion del viaje: "+camino_seleccionado.getDuracion().toString()+" minutos");
		lbl_longitud_viaje.setText("Longitud del viaje: "+camino_seleccionado.getDistancia().toString()+" km");
	}
	
	private void agregarActionListener() {
		jb_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_contenedora.cambiarPanel(VentanaPrincipal.SELEC_RECORRIDO);
			}
		});
		
		jb_registrar_venta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre = jtf_nombre_cliente.getText();
				String email = jtf_email_cliente.getText();
				Cliente c = null;//GestorJDBC.buscarCliente(nombre,email); TODO
				try {
					if(c==null) {
						GestorValidaciones.validarCliente(nombre,email);
						c = GestorEntidades.crearCliente(nombre,email);
					}
					jtp_errores.setText("");
					
					//GestorJDBC.agregarCliente(c);
					//GestorJDBC.agregarCamino(camino_seleccionado)
					//GestorJDBC.agregarBoleto(c, camino_seleccionado.getOrigen, camino_seleccionado.getDestino(), camino_seleccionado)
					
				}
				catch(DatosDeClienteIncorrectosException exc) {
					jtp_errores.setText(exc.errores);
				}
				
				ventana_contenedora.cambiarPanel(VentanaPrincipal.MENU_PPAL);
			}
		});
	}
}
