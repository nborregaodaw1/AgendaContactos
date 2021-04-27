package ut7.agenda.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
	 * Este método importa todos los contactos que se obtienen a través de<br>
	 * un fichero csv
	 * 
	 * @param agenda - Agenda donde se importaran los contactos
	 */
	public static int importar(AgendaContactos agenda, String ruta) {
		int contador = 0;

		try {
			Scanner sc = new Scanner(new File(ruta));
			while (sc.hasNextLine()) {
				agenda.añadirContacto(parsearLinea(sc.nextLine()));
			}
		} catch (FileNotFoundException fnf) {
			System.out.println("La ruta especificada no es valida");
		} catch (NumberFormatException nfe) {
			contador++;
		}

		return contador;

	}

	/**
	 *  Este metodo privado parsea la linea y devuelve un Contacto Personal o Profesional.
	 *  
	 *  @throws NumberFormatException si la fecha no esta bien parseada.
	 *  */
	private static Contacto parsearLinea(String linea) throws NumberFormatException{
		
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
