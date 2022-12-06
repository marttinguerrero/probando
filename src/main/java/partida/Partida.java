package partida;

import jugador. *;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import excepciones.ExcepcionMovimientoInvalido;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionTurnoEquivocado;
import fabricaDeFichas.FabricaDeFichas;
import ficha. *;
import tablero. *;

public class Partida implements Serializable{
	private static final long serialVersionUID = 1L;
	private Jugador jugador1;
	private Jugador jugador2;
	private Tablero tablero;
	private Jugador jugadorEnTurno;
	//private Ficha casillaSelecionada;
	
	public Partida(Jugador jugador1, Jugador jugador2) {
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		ArrayList<Ficha> fichasRojas = inicializarFichas(Color.ROJA, jugador1);
		ArrayList<Ficha> fichasAzules = inicializarFichas(Color.AZUL, jugador2);
		this.tablero = new Tablero(fichasRojas, fichasAzules);
		this.jugadorEnTurno = jugador1;
	}
	
	public Partida(){	
	}
	
	public int obtenerJugador() {
		if(jugadorEnTurno == jugador1) {
			return 1;
		}
		else if(jugadorEnTurno == jugador2) {
			return 2;
		}
		return 5;
		
	}
	
	public boolean guardarPartida(File nombreArchivo, Partida partida) throws IOException {
		
		try (FileOutputStream fileOutputStream = new FileOutputStream(nombreArchivo)){
			
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(partida);
		objectOutputStream.close();
		return true;
		}
		
		catch (IOException e) {
			return false;
		}
		
		
	}
	
	public Partida cargarPartida(File nombreArchivo, Partida partida) throws IOException, ClassNotFoundException {
		try(FileInputStream fileInputStream = new FileInputStream(nombreArchivo)){
		
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		partida = (Partida) objectInputStream.readObject();
		objectInputStream.close();
		return partida;
		}
		catch (IOException e) {
			return null;
		}	
		
	}
	
	public Tablero obtenerTablero() {
		return tablero;
	}
	
	public boolean armarEstrategiaJugador1(int posXi, int posYi, int posXf, int posYf) throws ExcepcionMovimientoInvalido, ExcepcionPosicionInvalida, ExcepcionTurnoEquivocado{
		//System.out.print(String.format("Entra a armar la estrategia el jugador 1 con xi = %d, yi = %d, xf= %d, yf= %d \n", posXi, posYi, posXf, posYf));
		jugador1.armarEstrategiaJugador1(posXi, posYi, posXf, posYf, tablero);
		return true;
	}
	
	public boolean armarEstrategiaJugador2(int posXi, int posYi, int posXf, int posYf) throws ExcepcionMovimientoInvalido, ExcepcionPosicionInvalida, ExcepcionTurnoEquivocado{
		jugador2.armarEstrategiaJugador2(posXi, posYi, posXf, posYf, tablero);
		return true;
	}
	
	
	public ArrayList<Ficha> inicializarFichas(Color color, Jugador jugador) {
		ArrayList<Ficha> fichas =  new ArrayList<Ficha>();
		FabricaDeFichas fabricaDeFichas = new FabricaDeFichas();

		fichas.add(fabricaDeFichas.crearFicha(color, TipoDeFicha.BANDERA, jugador));
		fichas.add(fabricaDeFichas.crearFicha(color, TipoDeFicha.ESPIA, jugador));
		for (int i = 0; i<8; i++) {
			fichas.add(fabricaDeFichas.crearFicha(color, TipoDeFicha.DOS, jugador));
		}
		for (int i = 0; i<5; i++) {
			fichas.add(fabricaDeFichas.crearFicha(color, TipoDeFicha.TRES, jugador));
		}
		for (int i = 0; i<4; i++) {
			fichas.add(fabricaDeFichas.crearFicha(color, TipoDeFicha.CUATRO, jugador));
		}
		for (int i = 0; i<4; i++) {
			fichas.add(fabricaDeFichas.crearFicha(color, TipoDeFicha.CINCO, jugador));
		}
		for (int i = 0; i<4; i++) {
			fichas.add(fabricaDeFichas.crearFicha(color, TipoDeFicha.SEIS, jugador));
		}
		for (int i = 0; i<3; i++) {
			fichas.add(fabricaDeFichas.crearFicha(color, TipoDeFicha.SIETE, jugador));
		}
		fichas.add(fabricaDeFichas.crearFicha(color, TipoDeFicha.OCHO, jugador));
		fichas.add(fabricaDeFichas.crearFicha(color, TipoDeFicha.OCHO, jugador));
		fichas.add(fabricaDeFichas.crearFicha(color, TipoDeFicha.NUEVE, jugador));
		fichas.add(fabricaDeFichas.crearFicha(color, TipoDeFicha.DIEZ, jugador));
		for (int i = 0; i<6; i++) {
			fichas.add(fabricaDeFichas.crearFicha(color, TipoDeFicha.BOMBA, jugador));
		}
		Collections.shuffle(fichas);
		return fichas;
		
	}
	
	public boolean moverFicha(int posXi, int posYi, int posXf, int posYf) throws ExcepcionMovimientoInvalido, ExcepcionPosicionInvalida, ExcepcionTurnoEquivocado {
		if (jugadorEnTurno.moverFicha(posXi, posYi, posXf, posYf, tablero)) {
			actualizarTurno();
			return true;
		}
		return false;

	}
	
	/*public void comenzarPartida() {
		while(!jugador1.perdio() && !jugador2.perdio()) {
			while (!jugadorEnTurno.moverFicha(posXi, posYi, posXf, posYf, tablero)) {
				}
			}
			actualizarTurno();
		}*/

	public boolean terminar() {
		if(jugador1.perdio() || jugador2.perdio()) {
			return true;
		}
		return false;
	}
	
	public boolean esTurnoAzules(){
		if (jugadorEnTurno.obtenerColor() == Color.AZUL )	{
			return true;
		}
		return false;
	}
	
	

	public boolean inicializarPartida() {
		return jugador1.armarEstrategia() && jugador2.armarEstrategia();
	}
	
	public void actualizarTurno() {
		if (jugadorEnTurno == jugador1) {
			jugadorEnTurno = jugador2;
		} else {
			jugadorEnTurno = jugador1;
		}
	}
	
}