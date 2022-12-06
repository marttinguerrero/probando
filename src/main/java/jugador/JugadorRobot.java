package jugador;

import java.util.ArrayList;

import excepciones.ExcepcionMovimientoInvalido;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionTurnoEquivocado;
import ficha.Color;
import ficha.Ficha;
import ficha.TipoDeFicha;
import tablero. *;

public class JugadorRobot implements java.io.Serializable , Jugador{
	private static final long serialVersionUID = 1L;
	private int fichasVivas;
	private String nombre = "Soy la computadora";

	private Color colorFichas;
	private int fichaBombaVivas;
	private boolean banderaViva;
	
	public JugadorRobot(Color color) {
		this.fichasVivas = 40;
		this.fichaBombaVivas = 6;
		this.banderaViva = true;
		this.colorFichas = color;
	}
	
public static double obtenerNumeroAlAzar(double min, double max){
	    double x = (int)(Math.random()*((max-min)+1))+min;
	    return x;
}
public Color obtenerColor() {
	return colorFichas;
}




public String obtenerNombre() {
	return nombre;
}


public boolean armarEstrategiaJugador1(int posXi, int posYi, int posXf, int posYf, Tablero tablero) throws ExcepcionMovimientoInvalido, ExcepcionPosicionInvalida, ExcepcionTurnoEquivocado {
	System.out.print(String.format("Entra a armar la estrategia el jugador 1 con xi = %d, yi = %d, xf= %d, yf= %d \n", posXi, posYi, posXf, posYf));
	if (!(tablero.validarPosicionCampoJugador1(posXi, posYi) && tablero.validarPosicionCampoJugador1(posXf, posYf))) {
		throw new excepciones.ExcepcionPosicionInvalida();
	}
	return tablero.rotarFicha(posXi, posYi, posXf, posYf, this);
}


public boolean armarEstrategiaJugador2(int posXi, int posYi, int posXf, int posYf, Tablero tablero) throws ExcepcionMovimientoInvalido, ExcepcionPosicionInvalida, ExcepcionTurnoEquivocado {
	System.out.print(String.format("Entra a armar la estrategia el jugador 1 con xi = %d, yi = %d, xf= %d, yf= %d \n", posXi, posYi, posXf, posYf));
	if (!(tablero.validarPosicionCampoJugador2(posXi, posYi) && tablero.validarPosicionCampoJugador2(posXf, posYf))) {
		throw new excepciones.ExcepcionPosicionInvalida();
	}
	return tablero.rotarFicha(posXi, posYi, posXf, posYf, this);
}

public boolean moverFicha(int posXi, int posYi, int posXf, int posYf, Tablero tablero) {
		
		ArrayList<int[]> posFichas = tablero.obtenerFichasDeUnJugador(this);
		java.util.Random random = new java.util.Random();
		int posicionRandom = random.nextInt(posFichas.size());
		int[] posFicha = posFichas.get(posicionRandom);
		
		posFichas.remove(posicionRandom);
		int posXicompu = posFicha[0];
		int posYicompu = posFicha[1];
	
		int[] movimientoPosible = null;
		movimientoPosible = tablero.obtenerFicha(posXicompu, posYicompu).movimientoPosible(posXicompu, posYicompu, tablero);
		while (posFichas.size() != 0) {
			
			while(movimientoPosible == null) {
				posicionRandom = random.nextInt(posFichas.size());
				posFicha = posFichas.get(posicionRandom);
				posFichas.remove(posicionRandom);
				posXicompu = posFicha[0];
				posYicompu = posFicha[1];
				System.out.print(String.format("Se obtuvo la pieza %d %d \n", posXicompu, posYicompu));
				movimientoPosible = tablero.obtenerFicha(posXicompu, posYicompu).movimientoPosible(posXicompu, posYicompu, tablero);
				
			}
			int posXfcompu = movimientoPosible[0];
			int posYfcompu = movimientoPosible[1];


//			System.out.print(String.format("Se obtuvo la ficha %d %d y se va a mover hacia %d %d\n", posXicompu,posYicompu,posXfcompu,posYfcompu ));
			if(tablero.moverFicha(posXicompu, posYicompu, posXfcompu, posYfcompu, this)) {
				return true;
			}
		}
		
		return false;
		
			
	}
	
	public void perderFicha(Ficha ficha) {
		if (ficha.obtenerValor() == TipoDeFicha.BOMBA.valor) {
			fichaBombaVivas = fichaBombaVivas - 1;
			
		}
		if (ficha.obtenerValor() == TipoDeFicha.BANDERA.valor) {
			banderaViva = false;
			
		}
		fichasVivas = fichasVivas - 1;	
	}
	
	public boolean armarEstrategia() {
		return true;
	}
	
	public boolean perdio() {
		return (fichasVivas == 0 || fichaBombaVivas == fichasVivas || !banderaViva);
	}
}
