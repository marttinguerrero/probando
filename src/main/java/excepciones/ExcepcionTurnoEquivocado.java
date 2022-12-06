package excepciones;

public class ExcepcionTurnoEquivocado extends RuntimeException implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	public ExcepcionTurnoEquivocado(){
        super("Es turno del equipo contrario");
    }
}
