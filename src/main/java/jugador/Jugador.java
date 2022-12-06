package jugador;

import excepciones.ExcepcionMovimientoInvalido;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionTurnoEquivocado;
import ficha.Color;
import ficha.Ficha;
import tablero.Tablero;

public interface Jugador {
	
	public boolean armarEstrategia();
	
	public boolean moverFicha(int posXi, int posYi, int posXf, int posYf, Tablero tablero);
	
	public boolean perdio();
	
	public boolean armarEstrategiaJugador2(int posXi, int posYi, int posXf, int posYf, Tablero tablero) throws ExcepcionMovimientoInvalido, ExcepcionPosicionInvalida, ExcepcionTurnoEquivocado;
	
	public boolean armarEstrategiaJugador1(int posXi, int posYi, int posXf, int posYf, Tablero tablero) throws ExcepcionMovimientoInvalido, ExcepcionPosicionInvalida, ExcepcionTurnoEquivocado;
	
	public String obtenerNombre();
	
	public Color obtenerColor();
	
	public void perderFicha(Ficha ficha);
	
	
}
