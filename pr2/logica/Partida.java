package tp.pr2.logica;

/**
 * Clase para representar la información de una partida. Se encarga de almacenar
 * el estado del tablero, a quién le toca, si ya hay un ganador, etc., así como
 * la lista de movimientos que se han ido realizando para poder deshacerlos. La
 * partida guarda al menos los 10 últimos movimientos.
 */
public class Partida {
	private Tablero tablero;
	private Ficha ficha;
	private boolean terminada;
	private Ficha ganador;
	private Pila pila;
	private ReglasJuego reglas;

	public Partida() {
		terminada = false;
		ganador = Ficha.VACIA;
		ficha = Ficha.BLANCA;
		tablero =  new Tablero(7, 6); 
		pila = new Pila();
	} 

	/**
	 * Construye una partida nueva
	 * 
	 * @param reglas Reglas del juego que se utilizarán durante la partida (al
	 *            menos hasta que se ejecute reset)
	 *            
	 */
	public Partida(ReglasJuego reglas) {
		terminada = false;
		tablero = reglas.iniciaTablero();
		ficha = reglas.jugadorInicial();
		pila = new Pila();
		ganador = Ficha.VACIA;
		this.reglas = reglas;
	}

	/**
	 * Método para conprobar el estado de la partida(Terminada True or False)
	 * 
	 * @return booleano indicando si la partida ha terminado.
	 */
	public boolean isTerminada() {
		return terminada;
	}

	/**
	 * Ejecuta el movimiento indicado
	 * 
	 * @param mov Movimiento a ejecutar. Se asume que el movimiento es
	 *            compatible con las reglas de la partida que se está jugando
	 *            (si se está jugando a Conecta 4, el tipo de movimiento es
	 *            Conecta 4, etc.). En caso contrario, el comportamiento es
	 *            indeterminado.
	 *           
	 * @return true si se puede efectuar el movimiento. Es un error intentar
	 *         colocar una ficha del jugador que no tiene el turno, cuando la
	 *         partida está terminada, columna llena,
	 */
	public boolean ejecutaMovimiento(Movimiento mov) {
		if (mov.ejecutaMovimiento(tablero) && ficha == mov.color
				&& !isTerminada()) {
			pila.apilar(mov);
			
			if (reglas.hayGanador(mov, tablero) != Ficha.VACIA) {
				ganador = mov.color;
				terminada = true;

			} else if (reglas.tablas(mov.color, tablero)) {
				ganador = Ficha.VACIA;
				terminada = true;
			}
			if (reglas.multiplesGanadores(tablero)) {
				ganador = Ficha.VACIA;
				terminada = false;
			}

			ficha = reglas.siguienteTurno(mov.color, tablero);

			return true;
		} else
			return false;
	}

	public void setReglas(ReglasJuego reglas) {
		this.reglas = reglas;
	}

	/**
	 * Reinicia la partida en curso. Esta operación no puede deshacerse. Gracias
	 * al parámetro, permite cambiar el tipo de juego al que se juega
	 * 
	 * @param reglas
	 *            Las reglas del juego a utilizar a partir de ahora.
	 */
	public void reset(ReglasJuego reglas) {
		terminada = false;
		tablero = reglas.iniciaTablero();
		ficha = reglas.jugadorInicial();
		pila = new Pila();
	}

	/**
	 * Deshace el último movimiento ejecutado.
	 * 
	 * @return true si se pudo deshacer.
	 */
	public boolean undo() {
		if (!pila.desApilar(tablero)) {
			return false;
		} else {
			ficha = reglas.siguienteTurno(ficha, tablero);
			return true;
		}
	}

	/**
	 * Devuelve el color del ganador. Sólo válido si la partida ya ha terminado
	 * (isTerminada() == true).
	 * 
	 * @return Color del ganador. Si la partida terminó en tablas, Ficha.VACIA.
	 *         Si la partida no ha terminado aún, el resultado es indeterminado.
	 */
	public Ficha getGanador() {
		return ganador;
	}

	/**
	 * Método de acceso al tablero. Dependiendo de cómo se haga la
	 * implementación del resto de clases (principalmente de la clase
	 * Controlador), es posible que no se utilice para nada en la práctica. Sin
	 * embargo, es necesario implementarlo para poder ejecutar los test de
	 * unidad.
	 * 
	 * @return Estado del tablero actua
	 */
	public Tablero getTablero() {
		return this.tablero;
	}

	/**
	 * Devuelve el color del jugador al que le toca poner.
	 * 
	 * @return Color del jugador, o Ficha.VACIA si la partida ha terminado.
	 */
	public Ficha getTurno() {
		return ficha;
	}

}
