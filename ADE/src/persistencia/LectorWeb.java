package persistencia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JOptionPane;

import modelo.ConstantesTexto;

/**
 * Permite leer una pagina Web.
 *
 * @author Alexander Castro
 */
public class LectorWeb {

	public static final String ERROR_URL = "Error al leer la URL del articulo.";
	private static final String ERROR_CARGAR = "Error al cargar el articulo.";
	private static final String ERROR_LECTURA = "Error al leer el texto del articulo.";
	private static final String ERROR_CERRAR = "Error al cerrar el canal de datos.";

	/**
	 * A partir de un URL retorna el contenido HTML de un pagina
	 *
	 * @param urlArticulo
	 * @return String con el HTML de la pagina
	 */
	public static String leerArticulo(String urlArticulo){
		URL url;
		try {
			url = new URL(urlArticulo);
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(null, ERROR_URL, ConstantesTexto.TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
			return ERROR_URL;
		}
		BufferedReader entrada;
		try {
			entrada = new BufferedReader(new InputStreamReader(url.openStream()));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ERROR_CARGAR, ConstantesTexto.TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
			return ERROR_CARGAR;
		}
		String linea;
		StringBuilder texto = new StringBuilder();
		try {
			while ((linea = entrada.readLine()) != null)
				texto.append(linea);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ERROR_LECTURA, ConstantesTexto.TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
			return ERROR_LECTURA;
		}
		try {
			entrada.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ERROR_CERRAR, ConstantesTexto.TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
			return ERROR_CERRAR;
		}
		return remplazarCaracteresHTML(texto.toString());
	}

	/**
	 * Resuelve problemas de internazionalización cambiando los caracteres
	 * especiales de HTML a sus correspondientes valor para porder analizarlos
	 * en JAVA.
	 *
	 * @param texto
	 *            con caracteres especiales de HTML
	 * @return texto libre de caracteres especiales de HTML
	 */
	private static String remplazarCaracteresHTML(String texto) {
		return texto = texto.replaceAll("&aacute;", "á").replace("&eacute;", "é").replace("&iacute;", "í")
				.replace("&oacute;", "ó").replace("&uacute;", "ú").replace("&Aacute;", "Á").replace("&Eacute;", "É")
				.replace("&Iacute;", "Í").replace("&Oacute;", "Ó").replace("&Uacute;", "Ú").replace("&ntilde;", "ñ")
				.replace("&Ntilde;", "Ñ").replace("&amp;", "&").replace("&nbsp;", " ").replace("&#91;", "[")
				.replace("&#93;", "]");
	}
}