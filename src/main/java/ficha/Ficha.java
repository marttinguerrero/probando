package ficha;



import capacidadEnfrentamiento. *;
import capacidadMovimiento. *;
import tablero. *;
import jugador. *;

public class Ficha implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private TipoDeFicha tipoDeFicha;
	private Color color;
	private CapacidadMovimiento capacidadMovimiento;
	private CapacidadEnfrentamiento capacidadEnfrentamiento;
	private Jugador jugador;
	
	public Ficha(Color color, TipoDeFicha tipoDeFicha, CapacidadMovimiento capacidadMovimiento, CapacidadEnfrentamiento capacidadEnfrentamiento, Jugador jugador) {
		
		this.color = color;
		this.tipoDeFicha = tipoDeFicha;
		this.capacidadMovimiento = capacidadMovimiento;
		this.capacidadEnfrentamiento = capacidadEnfrentamiento;
		this.jugador = jugador;
//		System.out.print(String.format("se crea ficha con jugador: %s\n", jugador.obtenerNombre()));
	}
	
//	public void imprimirFicha() {
//		System.out.print(tipoDeFicha.valor);
//	}
	
	public Jugador obtenerJugador() {
		return jugador;
	}
	
	public void imprimirFicha() {
		System.out.print(String.format("El valor de la ficha es: %d\n", tipoDeFicha.valor));
		System.out.print(String.format("El jugador al que le pertenece es: %s\n", jugador.obtenerNombre()));
	}
	
	public boolean validarMovimiento(int posXi, int posYi, int posXf, int posYf, Tablero tablero) {
		return capacidadMovimiento.validarMovimiento(posXi, posYi, posXf, posYf, tablero);
	}
	
	public ResultadoEnfrentamiento enfrentar(Ficha ficha) {
		return capacidadEnfrentamiento.enfrentar(this, ficha);
	}
	
	public int[] movimientoPosible(int posXi, int posYi, Tablero tablero) {
		return capacidadMovimiento.movimientoPosible(posXi, posYi, tablero);
	}
	
	public int obtenerValor() {
		return tipoDeFicha.valor;
	}
	
	public TipoDeFicha obtenerTipoDeFicha() {
		return tipoDeFicha;
	}
	
	public Color obtenerColor() {
		return color;
		
	}
}