package tp.pr2.logica;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Representa una pila de movimientos. Tiene la peculiaridad de que cuando la
 * pila est� llena y se a�ade un nuevo elemento, se elmina el elemento m�s
 * antiguo para hacer sitio al nuevo.
 * 
 */
public class Pila {
	/**
	 * N�mero de elementos actuales de la pila
	 */
	private int num;
	/**
	 * Array de movimientos
	 */
	private ArrayList<Movimiento> lista;

	/**
	 * Constructor de la clase. Crea una nueva pila de movimientos.
	 */
	public Pila() {
		num = 0;
		lista = new ArrayList<Movimiento>();
	}

	/**
	 * Apila un nuevo elemento. Si la pila est� llena, elimina el elemento m�s
	 * antiguo para hacer sitio al nuevo elemento.
	 * 
	 * @param mov Nuevo elemento a apilar
	 */
	public void apilar(Movimiento mov) {
		if (num == 10) {
			Collections.rotate(lista, num - 1);
			lista.set(num - 1, mov);
		} else {
			lista.add(num, mov);
			num = num + 1;
		}
	}

	/**
	 * Desapila el �ltimo elemento movimiento de la pila.
	 * 
	 * @param tab Tablero sobre el cual se desapila el movimiento
	 * 
	 * @return booleano indicando si se ha realizado correctamente la acci�n de desapilar.
	 */
	public boolean desApilar(Tablero tab) {
		if (num <= 0) {
			return false;
		} else {
			Movimiento x = lista.get(num - 1);
			x.undo(tab);
			num = num - 1;
			return true;
		}
	}

	/**
	 * M�todo para indicar si la pila esta vacia
	 * 
	 * @return boolean Indicando si la pila esta vacia
	 */
	public boolean estaVacia() {
		return (num == 0);
	}
}
