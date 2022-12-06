package interfaceUsuario;

public class InterfaceUsuario implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	public static final java.util.Scanner teclado = new java.util.Scanner(System.in);
	public static final java.io.PrintStream pantalla = new java.io.PrintStream(System.out);
	
	
	public String pedirTipoPartida() {
		pantalla.print("que tipo de partida quiere jugar contra jugador o contra compu: ");
		String tipoPartida = teclado.nextLine();
		return tipoPartida;
	}
	
	public int perdirPosXi(){
		pantalla.print("posicion x de la ficha que se quiere mover (Xi): ");
		int pos = teclado.nextInt();
		return pos;
	}
	
	public int perdirPosYi(){
		pantalla.print("posicion y de la ficha que se quiere mover (Yi): ");
		int pos = teclado.nextInt();
		return pos;
	}
	
	public int perdirPosXf(){
		pantalla.print("posicion x hacia donde quiere mover la ficha (Xf): ");
		int pos = teclado.nextInt();
		return pos;
	}
	
	public int perdirPosYf(){
		pantalla.print("posicion y hacia donde quiere mover la ficha (Yf): ");
		int pos = teclado.nextInt();
		return pos;
	}
	
	public boolean perdirSiQuiereSeguirJugando() {
		pantalla.print("quiere seguir jugando true/false: ");
		boolean pos = teclado.nextBoolean();
		return pos;
	}
	
}
