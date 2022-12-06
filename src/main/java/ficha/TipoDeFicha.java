package ficha;

public enum TipoDeFicha {
	
	BANDERA(0),
	ESPIA(1),
	DOS(2),
	TRES(3),
	CUATRO(4),
	CINCO(5), 
	SEIS(6),
	SIETE(7),
	OCHO(8),
	NUEVE(9),
	DIEZ(10),
	BOMBA(11),
	
	AGUA(-1);
	
	public int valor;
	
	TipoDeFicha (int valor){
	    this.valor = valor;
	    
	}
	
	

	
}