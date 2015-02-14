package tp.pr2.logica;

public class MovimientoGravity extends Movimiento {

	
	
	protected MovimientoGravity(int donde, Ficha color) {
		super(donde, color);
		// TODO Auto-generated constructor stub
	}
	
	protected MovimientoGravity(int columna, int fila, Ficha color){
		super(columna, color);
	}

	@Override
	public boolean ejecutaMovimiento(Tablero tab) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void undo(Tablero tab) {
		// TODO Auto-generated method stub

	}

}
