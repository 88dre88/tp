package tp.pr2.logica;

/**
 * 
 * Clase que implementa el movimiento para el juego del Complica. Se deben
 * implementar los métodos abstractos de la clase padre.
 * 
 */
public class MovimientoComplica extends Movimiento {

	// Esta clase tiene atributos adicionales para
	// guardar la ficha que se desplaza

	/**
	 * Constructor del objeto.
	 * 
	 * @param donde Columna en la que se colocará la ficha
	 *            
	 * @param color  Color de la ficha que se pondrá (o jugador que pone).
	 *           
	 */
	public MovimientoComplica(int donde, Ficha color) {
		super(donde, color);
	}

	@Override
	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como parámetro. 
	 * Se puede dar por cierto que tablero recibido sigue las reglas del tipo de juego al que pertenece el movimiento.
	 * En caso contrario, el comportamiento es indeterminado.
	 */
	public boolean ejecutaMovimiento(Tablero tab) {
		if (tab.hayEspacioDesplazarColumna(donde) >= 1) {
			int x = tab.hayEspacioDesplazarColumna(donde);
			tab.setCasilla(donde, x, color);
			fila = x;
			colorAnterior = Ficha.VACIA;
			return true;
		} else if (tab.hayEspacioDesplazarColumna(donde) == 0) {
			colorAnterior = tab.getCasilla(donde, tab.getAlto());
			filaAnterior = tab.getAlto();
			colorNoDesplaza = tab.getCasilla(donde, 1);
			fila = 1;

			tab.desplazarTableroAbajo(donde);
			tab.setCasilla(donde, 1, color);

			return true;
		} else
			return false;
	}

	@Override
	/**
	 * Deshace el movimiento en el tablero recibido como parámetro. 
	 * Se puede dar por cierto que el movimiento se ejecutó sobre ese tablero; en caso contrario, 
	 * el comportamiento es indeterminado. Por lo tanto, es de suponer que el método siempre funcionará
	 * correctamente.
	 */
	public void undo(Tablero tab) {
		// undo si ha habido desplazamiento
		if (filaAnterior != 0) {
			tab.desplazarTableroArriba(donde);
			tab.setCasilla(donde, filaAnterior, colorAnterior);
			tab.setCasilla(donde, fila, colorNoDesplaza);
		} else
			tab.setCasilla(donde, fila, colorAnterior);
	}

}
