package interfaces.boletos;

import javax.swing.*;
import dominio.Camino;
import dominio.Cliente;
import excepciones.DatosDeClienteIncorrectosException;
import gestores.*;
import interfaces.VentanaPrincipal;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

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
		jtf_nombre_cliente.setBounds(280, 77, 190, 20);
		jtf_nombre_cliente.setColumns(10);
		
		lbl_nombre_cliente = new JLabel("Nombre del cliente:");
		lbl_nombre_cliente.setBounds(151, 80, 119, 14);
		
		jtf_email_cliente = new JTextField();
		jtf_email_cliente.setBounds(280, 108, 190, 20);
		jtf_email_cliente.setColumns(10);
		
		lbl_email_cliente = new JLabel("E-Mail del cliente:");
		lbl_email_cliente.setBounds(151, 111, 119, 14);
		
		jb_regresar = new JButton("Regresar");
		jb_regresar.setBounds(10, 266, 89, 23);
		
		jb_registrar_venta = new JButton("Registrar Venta");
		jb_registrar_venta.setBounds(470, 266, 140, 23);
		
		lbl_origen_destino = new JLabel("Viaje desde:");
		lbl_origen_destino.setBounds(151, 150, 319, 14);
		
		lbl_costo_boleto = new JLabel("Costo del boleto: $");
		lbl_costo_boleto.setBounds(151, 175, 319, 14);
		
		lbl_longitud_viaje = new JLabel("Longitud del viaje [km]: ");
		lbl_longitud_viaje.setBounds(151, 225, 319, 14);
		
		lbl_duracion_viaje = new JLabel("Duracion del viaje [min]: ");
		lbl_duracion_viaje.setBounds(151, 200, 319, 14);
		
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
				camino_seleccionado=null;
				ventana_contenedora.cambiarPanel(VentanaPrincipal.SELEC_RECORRIDO);
			}
		});
		
		jb_registrar_venta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre = jtf_nombre_cliente.getText();
				String email = jtf_email_cliente.getText();
				
				try {
					GestorValidaciones.validarCliente(nombre,email);
					Cliente c = GestorJDBC.buscarCliente(nombre,email);
					
					if(c==null) { // no hay registro en la BD
						c = GestorEntidades.crearCliente(nombre,email);
						GestorJDBC.agregarCliente(c);
					}
					jtp_errores.setText("");
					
					camino_seleccionado.asignarUltimoId();
					GestorJDBC.agregarCamino(camino_seleccionado);
					GestorJDBC.agregarBoleto(GestorEntidades.crearBoleto(c, camino_seleccionado.getOrigen(), camino_seleccionado.getDestino(), camino_seleccionado));
					
					VentanaPrincipal.popupInfo("Venta realizada exitosamente, boleto registrado en el sistema.", "Venta existosa");
					ventana_contenedora.cambiarPanel(VentanaPrincipal.MENU_PPAL);
				}
				catch(DatosDeClienteIncorrectosException exc) {
					jtp_errores.setText(exc.errores);
				}
				
			}
		});
	}
}
