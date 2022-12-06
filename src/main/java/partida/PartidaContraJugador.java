
package partida;
import ficha.Color;
import jugador. *;



public class PartidaContraJugador extends Partida{
	
	private static final long serialVersionUID = 1L;

	public PartidaContraJugador() {
		super(new JugadorHumano(Color.AZUL), new JugadorHumano(Color.ROJA));
	}
}