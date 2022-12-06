package excepciones;

public class ExcepcionPosicionInvalida extends RuntimeException implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	public ExcepcionPosicionInvalida(){
	       super("Posicion invalida");
	   }
}
