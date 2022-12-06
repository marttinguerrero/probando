
package partida;
import ficha.Color;
import jugador. *;



public class PartidaContraComputadora extends Partida{
	
	private static final long serialVersionUID = 1L;

	public PartidaContraComputadora() {
		super(new JugadorHumano(Color.AZUL), new JugadorRobot(Color.ROJA));
	}
}
