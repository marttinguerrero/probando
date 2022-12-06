package fabricaDeFichas;

import capacidadEnfrentamiento. *;
import capacidadMovimiento. *;
import ficha. *;
import jugador.Jugador;

public class FabricaDeFichas {
	
	public Ficha crearFicha(Color color, TipoDeFicha tipoDeFicha, Jugador jugador) {
		
		CapacidadMovimiento capacidadDeMovimiento = null;
		CapacidadEnfrentamiento capacidadDeEnfrentamiento = null;
		
		if (tipoDeFicha == TipoDeFicha.BOMBA) {
			capacidadDeMovimiento = new IncapacidadMovimiento();
		}
		else if (tipoDeFicha == TipoDeFicha.DOS) {
			capacidadDeMovimiento = new MovimientoLineaRecta();
		}
		else {
			capacidadDeMovimiento = new MovimientoDeUnaCasilla();
		}
		
		if (tipoDeFicha == TipoDeFicha.ESPIA) {
			capacidadDeEnfrentamiento = new CapacidadGanarMenoresYDiez();
		}
		else if (tipoDeFicha == TipoDeFicha.TRES) {
			capacidadDeEnfrentamiento = new CapacidadGanarMenoresYBomba();
		}
		else {
			capacidadDeEnfrentamiento = new CapacidadGanarMenores();
		}
		return new Ficha(color, tipoDeFicha, capacidadDeMovimiento, capacidadDeEnfrentamiento, jugador);
	}
}