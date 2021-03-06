package tp.pr2.logica;

/**
 * Clase que almacena la informaci�n de un tablero.
 * 
 */
public class Tablero {
	private Ficha[][] tablero;
	private int alto; // numColumna
	private int ancho; // numFila

	/**
	 * Clase que almacena el estado de un tablero de Fichas
	 * @param tx representa el ancho del tablero
	 * @param ty representa el alto del tablero
	 */
	public Tablero(int tx, int ty) {
		if (tx <= 0 || ty <= 0) {
			ancho = 1;
			alto = 1;
			tablero = new Ficha[alto][ancho];
			reset();
		} else {
			ancho = tx;
			alto = ty;
			tablero = new Ficha[alto][ancho];
			reset();
		}
	}

	/**
	 * M�todo para obtener el alto del tablero.
	 * 
	 * @return Devuelve el alto del tablero
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * M�todo para obtener el ancho del tablero.
	 * 
	 * @return Devuelve el ancho del tablero
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * M�todo para reiniciar la partida
	 */
	public void reset() {
		for (int f = 0; f < this.alto; f++) {
			for (int c = 0; c < this.ancho; c++) {
				tablero[f][c] = Ficha.VACIA;
			}
		}
	}

	/**
	 * M�todo para acceder a la informaci�n de una casilla o celda concreta
	 * @param x N�mero de Columna
	 * @param y N�mero de fila
	 * @return Informaci�n de la casilla. Si la casilla no es v�lida, se
	 *         devuelve Ficha.VACIA
	 */
	public Ficha getCasilla(int x, int y) {
		if (hayEspacio(y - 1, x - 1)) {
			return tablero[y - 1][x - 1];
		} else {
			return Ficha.VACIA;
		}
	}

	/**
	 * M�todo que permite especificar el nuevo contenido de una casilla. Tambi�n
	 * puede utilizarse para quitar una ficha
	 * @param x N�mero de Columna
	 * @param y N�mero de fila
	 * @param color Color de la casilla. Si se indica Ficha.VACIA, la celda
	 *            	quedar� sin ficha.    
	 */
	public void setCasilla(int x, int y, Ficha color) {
		if (hayEspacio(y - 1, x - 1)) {
			tablero[y - 1][x - 1] = color;
		}
	}

	/**
	 * M�todo para comprobar si hay espacio para colocar una ficha en la casilla
	 * indicada
	 * 
	 * @param c N�mero de Columna
	 *           
	 * @param f N�mero de fila
	 *            
	 * @return Booleano indicando si hay o no espacio disponible en el tablero
	 */
	private boolean hayEspacio(int c, int f) {
		if (columnaValida(f) && filaValida(c)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Comprueba que la columna indicada sea correcta
	 * 
	 * @param c N�mero de columna
	 *            
	 * @return Booleano True si la columna es correcta, false en caso contrario
	 */
	private boolean columnaValida(int c) {
		return (c >= 0) && (c < this.ancho);
	}

	/**
	 * Comprueba que la fila indicada sea correcta
	 * 
	 * @param f N�mero de fila
	 *            
	 * @return Booleano True si la columna es correcta, false en caso
	 *         contrario
	 */
	private boolean filaValida(int f) {
		return (f >= 0) && (f < this.alto);
	}

	/**
	 * M�todo para visualizar el contenido del tablero
	 */
	public String toString() {
		for (int i = 0; i < this.getAlto(); i++) {
			System.out.print("|");
			for (int j = 0; j < this.getAncho(); j++) {
				if (tablero[i][j] == Ficha.VACIA) {
					System.out.print(" ");
				}
				if (tablero[i][j] == Ficha.NEGRA) {
					System.out.print("X");
				}
				if (tablero[i][j] == Ficha.BLANCA) {
					System.out.print("O");
				}
			}
			System.out.print("|");
			System.out.println("");
		}
		System.out.println(getLineas());
		System.out.println(getNumeros());
		System.out.println("");
		return "";
	}

	/**
	 * M�todo auxiliar para completar la visializai�n del tablero.
	 * 
	 * @return Devuelve las l�neas inferiores para visualizarlas en el toString
	 */
	private String getLineas() {
		String s = "+";
		for (int i = 0; i < this.getAncho(); i++) {
			for (int j = 0; j < 1; j++) {
				s = s + "-";
			}
		}
		return s + "+";
	}

	/**
	 * M�todo auxiliar que para completar la visiualizaci�n del tablero.
	 * 
	 * @return Devulave los n�meros de colmnas visibles en el toString
	 */
	private String getNumeros() {
		String s = " ";
		for (int i = 0; i < this.getAncho(); i++) {
			for (int j = 0; j < 1; j++) {
				s = s + (i + 1);
			}
		}
		return s;
	}

	/**
	 * M�todo para comprobar si hay espacio para colocar una ficha en la casilla
	 * indicada
	 * 
	 * @param c N�mero de columna
	 *            
	 * @return Devuelve la fila disponible en la columna indicada
	 */
	public int hayEspacio(int c) {
		int i = -1;
		for (int x = 0; x < this.alto; x++) {
			if (columnaValida(c - 1) && this.tablero[x][c - 1] == Ficha.VACIA) {
				i = i + 1;
			}
		}
		return i + 1;
	}

	/**
	 * M�todo para comprobar si hay espacio para colocar una ficha en la casilla
	 * @param c Columna en la que se comprueba si hay espacio
	 * @return Devuelve el n�mero de la fila donde que est� libre. En caso de que este ocupada devuelve la �ltima posici�n de la columna
	 */
	public int hayEspacioDesplazarColumna(int c) {
		int i = -1;
		if (columnaValida(c - 1)) {
			for (int x = 0; x < alto; x++) {
				if (columnaValida(c - 1) && tablero[x][c - 1] == Ficha.VACIA) {
					i = i + 1;
				}
			}
			if (i == -1) {
				return 0;
			} else
				return i + 1;
		} else
			return i;

	}



	/**
	 * M�todo que comprueba si el tablero est� ocupado por fichas horizontales,
	 * verticales o en tablas
	 * 
	 * @param f N�mero de fila
	 *            
	 * @param c N�mero de columna
	 *            
	 * @return Devuelve 1 si la partida ha terminado
	 */
	public int ganador(int f, int c) {
		if (!columnaValida(c))
			return -1;
		if (!filaValida(f)) {
			return -1;
		}
		Ficha aBuscar = this.tablero[f][c];

		int fIni = f + 1;
		int enLinea = 1;
		while ((filaValida(fIni)) && (this.tablero[fIni][c] == aBuscar)) {
			enLinea++;
			fIni++;
		}
		fIni = f - 1;
		while ((filaValida(fIni)) && (this.tablero[fIni][c] == aBuscar)) {
			enLinea++;
			fIni--;
		}

		if (enLinea >= 4) {
			return 1;
		}

		int cIni = c + 1;
		enLinea = 1;

		while ((columnaValida(cIni)) && (this.tablero[f][cIni] == aBuscar)) {
			enLinea++;
			cIni++;
		}
		cIni = c - 1;
		while ((columnaValida(cIni)) && (this.tablero[f][cIni] == aBuscar)) {
			enLinea++;
			cIni--;
		}

		if (enLinea >= 4) {
			return 1;
		}

		cIni = c + 1;
		fIni = f + 1;
		enLinea = 1;
		while ((filaValida(fIni)) && (columnaValida(cIni))
				&& (this.tablero[fIni][cIni] == aBuscar)) {
			enLinea++;
			cIni++;
			fIni++;
		}
		fIni = f - 1;
		cIni = c - 1;
		while ((filaValida(fIni)) && (columnaValida(cIni))
				&& (this.tablero[fIni][cIni] == aBuscar)) {
			enLinea++;
			cIni--;
			fIni--;
		}

		if (enLinea >= 4) {
			return 1;
		}

		cIni = c + 1;
		fIni = f - 1;
		enLinea = 1;
		while ((filaValida(fIni)) && (columnaValida(cIni))
				&& (this.tablero[fIni][cIni] == aBuscar)) {
			enLinea++;
			cIni++;
			fIni--;
		}
		fIni = f + 1;
		cIni = c - 1;
		while ((filaValida(fIni)) && (columnaValida(cIni))
				&& (this.tablero[fIni][cIni] == aBuscar)) {
			enLinea++;
			cIni--;
			fIni++;
		}

		if (enLinea >= 4) {
			return 1;
		}
		return 0;
	}
	/**
	 * Metodo para comprobar si el tablero esta lleno
	 * 
	 * @return true si el tablero tiene todas las casillas ocupadas. False en
	 *         caso contrario.
	 */
	public boolean tableroLleno() {
		int lleno = 0;
			for (int c = 0; c < ancho; c++) {
				if (tablero[0][c] != Ficha.VACIA) {
					lleno = lleno + 1;
				}
		}
		if (lleno == ancho) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * M�todo para desplazar los valores del tablero hacia abajo.
	 * 
	 * @param c Columna del tablero que de la que se quiere desplazar los valores
	 */
	public void desplazarTableroAbajo(int c) {
		Ficha aux = Ficha.VACIA;
		for (int x = alto - 1; x >= 1; x--) {
			aux = tablero[x - 1][c - 1];
			tablero[x][c - 1] = aux;
		}
	}

	/**
	 * M�todo para desplazar los valores del tablero hacia abajo
	 * 
	 * @param c Columna del tablero que de la que se quiere desplazar los valores
	 */
	public void desplazarTableroArriba(int c) {
		Ficha aux = Ficha.VACIA;
		for (int i = 1; i < alto; i++) {
			aux = tablero[i][c - 1];
			tablero[i - 1][c - 1] = aux;
		}
	}
	/**
	 * M�todo para comprobar si hay un ganador en el tablero
	 * 
	 * @return Devuelve la ficha ganadora
	 */
	public Ficha calcularFichaGanadora(){
		Ficha ficha = Ficha.VACIA;
		for (int f = alto-1; f >= 0; f--) {
			for (int c = ancho-1; c >= 0; c--) {
				if(tablero[f][c] != Ficha.VACIA){
					if(ganador(f, c) > 0){
						ficha = tablero[f][c];
					}
				}
			}
		}
		return ficha;
	}
	/**
	 * Metodo que comprueba si el tablero esta ocupado por la ficha del color indicado como par�metro
	 * @param color Color de la ficha que se quiere comprobar en el tablero
	 * @return Booleano indicado si el tablero esta ocupado por la ficha indicada
	 */
	public boolean ocupadoFicha(Ficha color){
		boolean resultado = false;
		for (int f = alto-1; f >= 0; f--) {
			for (int c = ancho-1; c >= 0; c--) {
				if(tablero[f][c] == color){
					if(ganador(f, c) > 0){
						resultado= true;
					}
				}
			}
		}
		return resultado;
	}
}
