package capacidadMovimiento;


import java.util.ArrayList;

import ficha. *;
import tablero. *; 

public class MovimientoDeUnaCasilla implements CapacidadMovimiento , java.io.Serializable{

	private static final long serialVersionUID = 1L;


	
	public boolean validarMovimiento(int posXi, int posYi, int posXf, int posYf, Tablero tablero) {

		Ficha aMover = tablero.obtenerFicha(posXi, posYi);
		Ficha aComer = tablero.obtenerFicha(posXf, posYf);
//		if (aMover.obtenerTipoDeFicha().equals(ficha.TipoDeFicha.AGUA) || aComer.obtenerTipoDeFicha().equals(ficha.TipoDeFicha.AGUA)) {
//			return false;
//			
//		}
		int movimientosEnX = java.lang.Math.abs(posXf - posXi);
		int movimientosEnY = java.lang.Math.abs(posYf - posYi);
		
		if (aComer != Tablero.VACIO && aComer.obtenerColor() == aMover.obtenerColor()) {
			return false;
			
		}
		if (!((movimientosEnX == 1 && movimientosEnY == 0) || (movimientosEnX == 0 && movimientosEnY == 1))) {
			return false;
			
		}
		
		return true;
	}

	
	public int[] movimientoPosible(int posXi, int posYi, Tablero tablero) {
		
		
		
		//System.out.print(String.format("entra la ficha %d para buscar mov posibles\n", tablero.obtenerFicha(posXi, posYi).obtenerValor()));
		ArrayList<int[]> movimientosPosibles = new ArrayList<int[]>();
		
		System.out.print(String.format("La posicion inicial de la pieza es %d %d \n", posXi,posYi));
		int[] pos0 = new int[2]; 
		pos0[0] = posXi + 1;
		pos0[1] = posYi;
		movimientosPosibles.add(pos0);
		
		int[] pos1 = new int[2];
		pos1[0] = posXi - 1;
		pos1[1] = posYi;
		movimientosPosibles.add(pos1);
		
		int[] pos2 = new int[2];
		pos2[0] = posXi;
		pos2[1] = posYi + 1;
		movimientosPosibles.add(pos2);
		
		int[] pos3 = new int[2];
		pos3[0] = posXi;
		pos3[1] = posYi - 1;
		movimientosPosibles.add(pos3);
		
		
		System.out.print(String.format("Un movimiento posible es %d %d\n", movimientosPosibles.get(0)[0],movimientosPosibles.get(0)[1]));
		System.out.print(String.format("Un movimiento posible es %d %d\n", movimientosPosibles.get(1)[0],movimientosPosibles.get(1)[1]));
		System.out.print(String.format("Un movimiento posible es %d %d\n", movimientosPosibles.get(2)[0],movimientosPosibles.get(2)[1]));
		System.out.print(String.format("Un movimiento posible es %d %d\n", movimientosPosibles.get(3)[0],movimientosPosibles.get(3)[1]));
		
		for (int i = 0; i < movimientosPosibles.size(); i++) {
			int[] pos = movimientosPosibles.get(i);
			int posXf = pos[0];
			int posYf = pos[1];

			if (tablero.validarPosicion(posXf, posYf) && validarMovimiento(posXi, posYi, posXf, posYf, tablero)) {
				System.out.print(String.format("Posiciones %d %d \n", posXf, posYf));
				return pos;
			}
		}
		
		return null;
	}
}