package ut7.agenda.io;

import ut7.agenda.modelo.AgendaContactos;
import ut7.agenda.modelo.Contacto;
import ut7.agenda.modelo.Personal;
import ut7.agenda.modelo.Profesional;
import ut7.agenda.modelo.Relacion;

/**
 * Utilidades para cargar la agenda
 * 
 * @author Iker Iparraguirre, Jon García, Naiara Borrega
 */
public class AgendaIO {

	/**
	 * Este método importa todos los contactos que se obtienen del metodo<br>
	 * obtenerLineasDatos().
	 * 
	 * @param agenda - Agenda donde se importaran los contactos
	 */
	public static void importar(AgendaContactos agenda) {

		for (int i = 0; i < obtenerLineasDatos().length; i++) {
			agenda.añadirContacto(parsearLinea(obtenerLineasDatos()[i]));
		}
	}

	private static Contacto parsearLinea(String linea) throws NumberFormatException {

		// NumberFormatException es la excepcion que debe de saltar..

		String[] split = linea.split(",");
		for (int i = 0; i < split.length; i++) {
			split[i] = split[i].trim();
		}

		if (split[0].equals("1")) {
			Profesional p = new Profesional(split[1], split[2], split[3], split[4], split[5]);
			return p;
		} else {
			Personal p = new Personal(split[1], split[2], split[3], split[4], split[5],
					Relacion.valueOf(split[6].toUpperCase()));
			return p;
		}

	}

}
