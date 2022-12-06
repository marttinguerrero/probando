
package capacidadMovimiento;

import tablero. *; 

public interface CapacidadMovimiento {
	
	public boolean validarMovimiento(int posXi, int posYi, int posXf, int posYf, Tablero tablero);
	
	public int[] movimientoPosible(int posXi, int posYi, Tablero tablero);
}