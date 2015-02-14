package tp.pr2.control;

import java.util.Scanner;

import tp.pr2.logica.Ficha;
import tp.pr2.logica.Movimiento;
import tp.pr2.logica.MovimientoConecta4;
import tp.pr2.logica.ReglasConecta4;
import tp.pr2.logica.ReglasJuego;

public class FactoriaConecta4 implements FactoriaTipoJuego {
	
	@Override
	public ReglasJuego creaReglas() {
		 ReglasConecta4 reglas = new ReglasConecta4();
		 return reglas;
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		MovimientoConecta4 mov = new MovimientoConecta4(col, color);
		return mov;
	}

	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		// TODO Auto-generated method stub
		return null;
	}

}
