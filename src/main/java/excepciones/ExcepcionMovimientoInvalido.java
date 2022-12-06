package excepciones;

public class ExcepcionMovimientoInvalido extends RuntimeException implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	public ExcepcionMovimientoInvalido(String mensaje){
	       super(mensaje);
	   }
}
