package jugador;

import tablero. *;
import excepciones.ExcepcionMovimientoInvalido;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionTurnoEquivocado;
import ficha. *; 

public class JugadorHumano implements Jugador , java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int fichasVivas;	
	private int fichaBombaVivas;
	private boolean banderaViva;
	private String nombre = "pepito";
	private Color colorFichas;
	
	public JugadorHumano(Color color) {
		this.fichasVivas = 40;
		this.fichaBombaVivas = 6;
		this.banderaViva = true;
		this.colorFichas = color;
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
	public Color obtenerColor() {
		return colorFichas;
	}
	
	public boolean moverFicha(int posXi, int posYi, int posXf, int posYf, Tablero tablero) {
		
		return tablero.moverFicha(posXi, posYi, posXf, posYf, this);
	}
	
	public boolean armarEstrategiaJugador1(int posXi, int posYi, int posXf, int posYf, Tablero tablero) throws ExcepcionMovimientoInvalido, ExcepcionPosicionInvalida, ExcepcionTurnoEquivocado {
//		System.out.print(String.format("Entra a armar la estrategia el jugador 1 con xi = %d, yi = %d, xf= %d, yf= %d \n", posXi, posYi, posXf, posYf));
		if (!(tablero.validarPosicionCampoJugador1(posXi, posYi) && tablero.validarPosicionCampoJugador1(posXf, posYf))) {
			throw new excepciones.ExcepcionPosicionInvalida();
		}
		return tablero.rotarFicha(posXi, posYi, posXf, posYf, this);
	}
	
	public boolean armarEstrategiaJugador2(int posXi, int posYi, int posXf, int posYf, Tablero tablero) throws ExcepcionMovimientoInvalido, ExcepcionPosicionInvalida, ExcepcionTurnoEquivocado {
//		System.out.print(String.format("Entra a armar la estrategia el jugador 1 con xi = %d, yi = %d, xf= %d, yf= %d \n", posXi, posYi, posXf, posYf));
		if (!(tablero.validarPosicionCampoJugador2(posXi, posYi) && tablero.validarPosicionCampoJugador2(posXf, posYf))) {
			throw new excepciones.ExcepcionPosicionInvalida();
		}
		return tablero.rotarFicha(posXi, posYi, posXf, posYf, this);
	}
	
	public boolean armarEstrategia() {
		return true;
	}
	
	public boolean perdio() {
		
		if (fichasVivas == 0 || fichaBombaVivas == fichasVivas || !banderaViva) {
			System.out.println("Bandera muerta");
			return true;
		}
		return false;
		
	}

	@Override
	public String obtenerNombre() {
		
		return nombre;
	}
}
