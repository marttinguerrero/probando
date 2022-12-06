package tablero;

import ficha. *;
import jugador. *;
import excepciones.*; 

import java.util.ArrayList;

import capacidadEnfrentamiento. *;

public class Tablero implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	public int TAM_TABLERO = 10;
	public static final Ficha VACIO = null;
	//public static final Ficha PROHIBIDO = null;
	public static final Ficha AGUA = new Ficha(null, TipoDeFicha.AGUA, null, null, null);
	private Ficha[][] tablero;
	
	

	
	public Tablero(ArrayList<Ficha> fichasRojas, ArrayList<Ficha> fichasAzules) {
		
		this.tablero = new Ficha[TAM_TABLERO][TAM_TABLERO];
		for (int i = 0; i < TAM_TABLERO; i++) {
			for (int j = 0; j < TAM_TABLERO; j++) {
				
				if (i < 4) {
					tablero[i][j] = fichasRojas.get(10*i + j);
				}
				else if (i > 5) {
//					System.out.println("Se estan creando las fichas azules");
					tablero[i][j] = fichasAzules.get(10*(i-6) + j);
				} else{
					
					if ((j == 2 || j == 3 || j == 6 || j == 7)) {
						tablero[i][j] = AGUA;
					}else {
						tablero[i][j] = VACIO;
					}
				}
			}
		}
	}
	

	
	@SuppressWarnings("unlikely-arg-type")
	public ArrayList<int[]> obtenerFichasDeUnJugador(Jugador jugador) {
		ArrayList<int[]> posFichasDelJugador = new ArrayList<int[]>();

//		int l = 0;
		for (int i = 0; i < TAM_TABLERO; i++) {
			for (int j = 0; j < TAM_TABLERO; j++) {
//				System.out.println(tablero[i][j].obtenerValor());
//				System.out.println(l);
//				l++;
				if (tablero[i][j] != VACIO && !tablero[i][j].equals(ficha.TipoDeFicha.AGUA) && tablero[i][j].obtenerJugador() instanceof JugadorRobot) {
					int[] pos = new int[2];
					pos[0] = i;
					pos[1] = j;
					posFichasDelJugador.add(pos);
					
				}
			}
		}
		
		return posFichasDelJugador;
	}

	public boolean rotarFicha(int posXi, int posYi, int posXf, int posYf, Jugador jugador) throws ExcepcionTurnoEquivocado {
//		System.out.print(String.format("Entra a rotar ficha con xi = %d, yi = %d, xf= %d, yf= %d \n", posXi, posYi, posXf, posYf));
		Ficha aMover = tablero[posXi][posYi];
		Ficha aComer = tablero[posXf][posYf];
		
		if (!validarAMoverPertenescaAJugadorEnTurno(aMover, jugador)) {
			throw new excepciones.ExcepcionTurnoEquivocado();
		}
		
		tablero[posXf][posYf] = aMover;
		tablero[posXi][posYi] = aComer;
		return true;
	}
	
	public boolean validarPosicionCampoJugador1(int posX, int posY) {
		if (posX < 0 || posX >= 4 || posY < 0 || posY >= 10) {
			return false;
		}
		return true;
	}

	public boolean validarPosicionCampoJugador2(int posX, int posY) {
		if (posX < 6 || posX >= 10 || posY < 0 || posY >= 10) {
			return false;
		}
		return true;
	}
	
	public Ficha obtenerFicha(int posX, int posY) {
		return tablero[posX][posY];
	}
	
	
	public boolean validarPosicion(int posX, int posY) {
		if ((posY == 2 || posY == 3 || posY == 6 || posY == 7) && (posX == 4 || posX == 5)) {
			return false;	
		}
		if (posX < 0 || posX >= 10 || posY < 0 || posY >= 10) {
			return false;
		}
		return true;
	}
	
	private boolean validarAMoverPertenescaAJugadorEnTurno(Ficha ficha, Jugador jugador) {
		if (ficha.obtenerJugador().equals(jugador)) {
			return true;
		}
		return false;
	}
	

	public boolean moverFicha(int posXi, int posYi, int posXf, int posYf, Jugador jugador) throws ExcepcionMovimientoInvalido, ExcepcionPosicionInvalida, ExcepcionTurnoEquivocado{
		Ficha aMover = tablero[posXi][posYi];
		Ficha aComer = tablero[posXf][posYf];

		if (!validarPosicion(posXi,posYi) || !validarPosicion(posXf,posYf)) {
			throw new excepciones.ExcepcionPosicionInvalida();
		}
		
		if (!validarAMoverPertenescaAJugadorEnTurno(aMover, jugador)) {
			throw new excepciones.ExcepcionTurnoEquivocado();
		}
		
		if (!aMover.validarMovimiento(posXi, posYi, posXf, posYf, this)) {
			throw new excepciones.ExcepcionMovimientoInvalido("movimiento invalido");
		}
		
		if (aComer == VACIO) {			
			tablero[posXf][posYf] = aMover;
			tablero[posXi][posYi] = VACIO;
			return true;
		}
		
		ResultadoEnfrentamiento resultado = aMover.enfrentar(aComer);
		if (resultado == ResultadoEnfrentamiento.GANO) {
			tablero[posXf][posYf] = aMover;
			tablero[posXi][posYi] = VACIO;
			System.out.print(String.format("La pieza %d se come a la %d\n", aMover.obtenerValor(), aComer.obtenerValor()));
		}
		if (resultado == ResultadoEnfrentamiento.PERDIO) {
			tablero[posXi][posYi] = VACIO;
			System.out.print(String.format("La pieza %d se come a la %d\n", aComer.obtenerValor(), aMover.obtenerValor()));
		}
		if (resultado == ResultadoEnfrentamiento.EMPATO) {
			tablero[posXi][posYi] = VACIO;
			tablero[posXf][posYf] = VACIO;
			System.out.print(String.format("Empate\n"));
		}
//		System.out.print(String.format("La pieza %d se come a la %d\n", aMover.obtenerValor(), aComer.obtenerValor()));
		return true;
	}
	
	public String obtenerResultado(String aviso) {
		return aviso;
	}
//	public static double obtenerNumeroAlAzar(double min, double max){
//	    double x = (int)(Math.random()*((max-min)+1))+min;
//	    return x;
//}

//	public boolean moverFichaIA(int posXi, int posYi, int posXf, int posYf, JugadorRobot jugador) throws ExcepcionMovimientoInvalido, ExcepcionPosicionInvalida, ExcepcionTurnoEquivocado{
//		System.out.println("Estoy en moverFicha del jugadorRobot en el tablero");
//		posXi = (int) obtenerNumeroAlAzar(0, 9);
//		posYi = (int) obtenerNumeroAlAzar(0, 9);
//		Ficha aMover = obtenerFicha(posXi, posYi);
//		while (aMover == null || aMover.obtenerJugador() != jugador) {
//			posXi = (int) obtenerNumeroAlAzar(0, 9);
//			posYi = (int) obtenerNumeroAlAzar(0, 9);
//			aMover = obtenerFicha(posXi, posYi);
//		}
//		posXf = (int) obtenerNumeroAlAzar(0, 9);
//		posYf = (int) obtenerNumeroAlAzar(0, 9);
//		while(moverFicha(posXi, posYi, posXf, posYf, jugador) != true) {
//			posXf = (int) obtenerNumeroAlAzar(0, 9);
//			posYf = (int) obtenerNumeroAlAzar(0, 9);
//			moverFicha(posXi, posYi, posXf, posYf, jugador);
//			
//		}
//		return false;
//	}
//	
	
	public boolean posicionDelTableroVacia(int pos_i, int pos_j) {
		return tablero[pos_i][pos_j] == VACIO;
	}
	

	
}