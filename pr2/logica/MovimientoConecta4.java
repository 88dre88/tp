package tp.pr2.logica;

/**
 * 
 * Clase que implementa el movimiento para el juego del Conecta 4. Se deben implementar los m�todos abstractos de la clase padre.
 *
 */
public class MovimientoConecta4 extends Movimiento {

	
	/**
	 * Constructor la clase.
	 * @param donde Columna en la que se colocar� la ficha
	 * @param color Color de la ficha que se pondr� (o jugador que pone).
	 */
	public MovimientoConecta4(int donde, Ficha color) {
		super(donde, color);
	}

	@Override
	
	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como par�metro. 
	 * Se puede dar por cierto que tablero recibido sigue las reglas del tipo de juego al que pertenece el movimiento.
	 * En caso contrario, el comportamiento es indeterminado.
	 */
	public boolean ejecutaMovimiento(Tablero tab) {
		if (tab.hayEspacio(donde) > 0) {
			int x = tab.hayEspacio(donde);
			tab.setCasilla(this.donde, x, this.color);
			fila = x;
			return true;
		} else
			return false;
	}

	@Override
	/**
	 * Deshace el movimiento en el tablero recibido como par�metro. 
	 * Se puede dar por cierto que el movimiento se ejecut� sobre ese tablero; en caso contrario, 
	 * el comportamiento es indeterminado. Por lo tanto, es de suponer que el m�todo siempre funcionar�
	 * correctamente.
	 */
	public void undo(Tablero tab) {
		int x = fila;
		//int x = tab.buscarCasilla(this.donde);
		int y = this.donde;
		tab.setCasilla(y, x, Ficha.VACIA);
	}
}
