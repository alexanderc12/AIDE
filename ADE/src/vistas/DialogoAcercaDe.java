package vistas;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class DialogoAcercaDe extends JDialog {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public DialogoAcercaDe(VentanaPrincipal ventanaPrincipal) {
		super(ventanaPrincipal);
		setTitle(ConstantesGUI.T_TITULO_DIALOGO_CARGANDO);
		setSize(ConstantesGUI.DIALOGO_CARGANDO_ANCHO, ConstantesGUI.DIALOGO_CARGANDO_ALTO);
		setLocationRelativeTo(null);
		JLabel progressBar = new JLabel(createImageIcon("/images/about.png"));
		add(progressBar);
	}

	protected ImageIcon createImageIcon(String path) {
		URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, "");
		} else {
			return null;
		}
	}
}