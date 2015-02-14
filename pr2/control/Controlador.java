package tp.pr2.control;

import tp.pr2.logica.*;

import java.util.Scanner;

/**
 * Clase que controla la ejecución de la partida, pidiendo al usuario qué quiere
 * ir haciendo, hasta que la partida termina.
 * 
 */
public class Controlador {
	private Partida partida;
	private static Scanner in;
	private Ficha jugadr;

	/**
	 * Constructor de la clase.
	 * 
	 * @param p Partida a la que se jugará, configurada con el juego al que
	 *            hay que jugar en la invocación a run().
	 *            
	 * @param in Scanner que hay que se utiliza para introducir la información
	 *            al sistema
	 */
	public Controlador(Partida p, java.util.Scanner in){
		partida = p;
		Controlador.in = in;
		jugadr = Ficha.BLANCA;
	}
	
	public Controlador(FactoriaTipoJuego f, Partida p, java.util.Scanner in){
		partida = p;
		this.in = in;
	}

	/**
	 * Ejecuta la partida hasta que ésta termina. Se supone que se hará una
	 * única invocación a este método; tras ella, si se vuelve a llamar a run()
	 * el comportamiento será indeterminado.
	 */
	public void run() {
		ReglasJuego reglas = new ReglasConecta4();
		partida.setReglas(reglas);
		int tipoJuego = 1;
		while (!partida.isTerminada()) {
			partida.getTablero().toString();
			System.out.println("Juegan " + getTextoFicha());
			System.out.print("Qué quieres hacer? ");
			String comando = in.nextLine().toLowerCase();
			switch (comando) {
			case "jugar co":
				reglas = new ReglasComplica();
				jugadr = reglas.jugadorInicial();
				partida.setReglas(reglas);
				partida = new Partida(reglas);
				System.out.println("Partida reiniciada.");
				tipoJuego = 2;
				break;
			case "jugar c4":
				reglas = new ReglasConecta4();
				jugadr = reglas.jugadorInicial();
				partida.setReglas(reglas);
				partida = new Partida(reglas);
				System.out.println("Partida reiniciada.");
				tipoJuego = 1;
				break;

			case "poner":
				System.out.print("Introduce la columna: ");
				int num = 0;
				num = in.nextInt();
				Movimiento mov;
				if (tipoJuego == 1) {
					mov = new MovimientoConecta4(num, jugadr);
				} else {
					mov = new MovimientoComplica(num, jugadr);
				}

				if (partida.ejecutaMovimiento(mov)) {
					if (partida.isTerminada()
							&& reglas.hayGanador(mov, partida.getTablero()) != Ficha.VACIA) {
						partida.getTablero().toString();
						System.out.println("Ganan las " + getTextoFicha());
					}

					if (reglas.tablas(jugadr, partida.getTablero())) {
						partida.getTablero().toString();
						System.out.println("Partida terminada en tablas.");
					}

					jugadr = partida.getTurno();
				} else {
					System.err.println("Movimiento incorrecto");
				}
				in.nextLine();
				break;

			case "salir":
				salir();
				break;

			case "reiniciar":
				partida.reset(reglas);
				System.out.println("Partida reiniciada.");
				break;

			case "deshacer":
				if (partida.undo()) {
					jugadr = partida.getTurno();
				} else
					System.err.println("Imposible deshacer.");

				break;
			default:
				System.out.println("");
				System.err.println("No te entiendo.");
				break;
			}
		}
	}

	/**
	 * Método privado auxiliar que calcula el mensaje del jugador actual.
	 * 
	 * @return String con el texto del jugador que tiene el turno en la partida.
	 */
	private String getTextoFicha() {
		if (jugadr == Ficha.BLANCA) {
			return "blancas";
		} else
			return "negras";
	}

	/**
	 * Método auxiliar para finalizar la partida
	 */
	public void salir() {
		System.exit(0);
	}
}
