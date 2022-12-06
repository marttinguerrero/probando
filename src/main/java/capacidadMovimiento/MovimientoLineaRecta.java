package capacidadMovimiento;


import java.util.ArrayList;

import ficha.Ficha;

import tablero. *;

public class MovimientoLineaRecta implements CapacidadMovimiento , java.io.Serializable{
	
	private static final long serialVersionUID = 1L;


	
	public boolean validarMovimiento(int posXi, int posYi, int posXf, int posYf, Tablero tablero) {
		Ficha aMover = tablero.obtenerFicha(posXi, posYi);
		Ficha aComer = tablero.obtenerFicha(posXf, posYf);
		
		int movimientosEnX = java.lang.Math.abs(posXf - posXi);
		int movimientosEnY = java.lang.Math.abs(posYf - posYi);
		
		if (aComer != Tablero.VACIO && aComer.obtenerColor() == aMover.obtenerColor()) {
			return false;
		}
		
		if (!((movimientosEnX == 0 && movimientosEnY != 0) ||(movimientosEnX != 0 && movimientosEnY == 0))) {
//			System.out.print(String.format("Entra en false porque aprieta la misma casilla \n",null));
			return false;
		}
		
		if (movimientosEnY == 0) {
			System.out.println("Entra a movimiento en x");
			for (int i = 0; i < (movimientosEnX - 1); i++) {
				if (tablero.obtenerFicha(posXi + (((posXf - posXi) /java.lang.Math.abs(posXf - posXi)) + i*((posXf - posXi) /java.lang.Math.abs(posXf - posXi))), posYi) != Tablero.VACIO) {
					//System.out.print(String.format("Entra en false porque se quiso mover en X y habia una ficha en medio \n",null));
					return false;
				}
			}
		}
		
		if (movimientosEnX == 0) {
			System.out.println("Entra a movimiento en y");
			for (int i = 0; i < (movimientosEnY - 1); i++) {
				if (tablero.obtenerFicha(posXi , posYi + ((posYf - posYi) /java.lang.Math.abs(posYf - posYi)) + i) != Tablero.VACIO) {
//					System.out.print(String.format("Entra en false porque se quiso mover en Y y habia una ficha en medio \n",null));
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	public int[] movimientoPosible(int posXi, int posYi, Tablero tablero) {
		
		
		ArrayList<int[]> movimientosPosibles = new ArrayList<int[]>();
		
		for (int i = 1; i < 10; i++) {
			
			int[] pos0 = new int[2];
			pos0[0] = posXi + i;
			pos0[1] = 0;
			movimientosPosibles.add(pos0);
			
			int[] pos1 = new int[2];
			pos1[0] = posXi - i;
			pos1[1] = 0;
			movimientosPosibles.add(pos1);
			
			int[] pos2 = new int[2];
			pos2[0] = 0;
			pos2[1] = posYi + i;
			movimientosPosibles.add(pos2);
			
			int[] pos3 = new int[2];
			pos3[0] = 0;
			pos3[1] = posYi - i;
			movimientosPosibles.add(pos3);
		}
		
		for (int i = 0; i < movimientosPosibles.size(); i++) {
			int[] pos = movimientosPosibles.get(i);
			int posXf = pos[0];
			int posYf = pos[1];
			if (tablero.validarPosicion(posXf, posYf) && validarMovimiento(posXi, posYi, posXf, posYf, tablero)) {
				return pos;
			}
		}
		
		return null;
	}
}