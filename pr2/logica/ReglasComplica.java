package tp.pr2.logica;

/**
 * 
 * Clase que implementa de las reglas del juego. Se han implementado todos los
 * métodos del interfaz, además del constructor. Esta clase contiene la lógica del juego Complica
 */
public class ReglasComplica implements ReglasJuego {

	private Tablero tablero;
	private Ficha ficha;

	/**
	 * Constructor de la clase, sin parámetros.
	 */
	public ReglasComplica() {
	}

	@Override
	/**
	 * Permite averiguar si en la partida ya tenemos un ganador o no. Devuelve
	 * el color del ganador (si lo hay).
	 * 
	 * @param ultimoMovimiento
	 *            Ultimo movimiento realizado. Las distintas implementaciones
	 *            pueden utilizar este parámetro para optimizar la búsqueda del
	 *            ganador.
	 * @param t Estado del tablero
	 *            
	 * @return color del ganador, si lo hay. Si no lo hay, devuelve Ficha.VACIA
	 *         (eso NO significa necesariamente que la partida haya terminado en
	 *         tablas).
	 */
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		if (t.ganador(ultimoMovimiento.fila - 1, ultimoMovimiento.donde - 1) > 0) {
			return ultimoMovimiento.color;
		} else if (t.calcularFichaGanadora() != Ficha.VACIA) {
			return  t.calcularFichaGanadora();
		} else
			return Ficha.VACIA;
	}

	@Override
	/**
	 * Construye el tablero que hay que utilizar para la partida, según las
	 * reglas del juego.
	 * 
	 * @return Tablero a utilizar. El estado del tablero será el de inicio de la
	 *         partida.
	 */
	public Tablero iniciaTablero() {
		tablero = new Tablero(4, 7);
		return tablero;
	}

	@Override
	/**
	 * Devuelve el color del jugador que comienza la partida
	 * 
	 * @return Color del primer jugador en colocar ficha.
	 */
	public Ficha jugadorInicial() {
		ficha = Ficha.BLANCA;
		return this.ficha;
	}

	@Override
	/**
	 * Devuelve el color del jugador al que le toca poner.
	 * 
	 * @param ultimoEnPoner
	 *            Último jugador en poner ficha
	 * @param t Estado del tablero.
	 *            
	 * @return Siguiente jugador que debe poner ficha.
	 */
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		if (ultimoEnPoner == Ficha.BLANCA) {
			ficha = Ficha.NEGRA;
			return Ficha.NEGRA;
		} else {
			ficha = Ficha.BLANCA;
			return Ficha.BLANCA;
		}
	}

	@Override
	/**
	 * Devuelve true si, con el estado del tablero dado, la partida ha terminado
	 * en tablas.
	 * 
	 * @param ultimoEnPoner
	 *            Jugador que acaba de poner ficha
	 * @param t  Estado del tablero
	 *           
	 * @return true si la partida ha terminado sin ganador
	 */
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		if (t.tableroLleno()) {
			return false;
		} else
			return false;
	}

	@Override
	/**
	 * Devuelve true si, con el estado del tablero dado, si el tablero 
	 * esta ocupado en 4 en linea por fichas blancas y negras
	 * 
	 * @param t Estado del tablero
	 * @return True si en el tablero hay 4 en linea de blancas y negras
	 */
	public boolean multiplesGanadores(Tablero t) {
		if(t.ocupadoFicha(Ficha.BLANCA) && t.ocupadoFicha(Ficha.NEGRA)){
			return true;
		}else
			return false;
		}

}
