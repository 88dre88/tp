/**
 * Paquete principal de la práctica.
 */
package tp.pr2;

import java.util.Scanner;

import tp.pr2.control.Controlador;
import tp.pr2.logica.Partida;

/**
 * Clase que contiene el punto de entrada a la aplicación.
 * 
 */
public class Main {
	/**
	 * Método principal de la aplicación.
	 * @param args Argumentos pasados a la aplicación. Actualmente no se utilizan.
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Partida p1 = new Partida();
		Controlador c1 = new Controlador(p1, in);
		c1.run();
	}
}
