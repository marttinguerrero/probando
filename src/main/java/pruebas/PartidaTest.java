//package pruebas;
//
//import static org.junit.Assert.*;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import ficha.Color;
//import ficha.Ficha;
//import interfaceUsuario.InterfaceUsuario;
//import jugador.Jugador;
//import jugador.JugadorHumano;
//import partida.Partida;
//import partida.PartidaContraJugador;
//
//public class PartidaTest {
//
//	InterfaceUsuario interfaceUsuario;
//	Partida partida;
//	
//	@Before
//	public void init() {
//		interfaceUsuario = new InterfaceUsuario();		
//		partida = new PartidaContraJugador(interfaceUsuario);
//
//	}
//
//	
//	@Test
//	public void testInicializarPartida() {
//
//		assertTrue(partida.inicializarPartida());
//		
//	}
//	
//	@Test 
//	public void testValidarCantidadDePiezas() {
//		
//		Jugador jugador = new JugadorHumano();
//		ArrayList<Ficha> piezas = partida.inicializarFichas(Color.ROJA, jugador);
//		int x = 40;
//		assertEquals(x,piezas.size());
//		
//	}
//	
//	@Test
//	public void testGuardarPartida() throws IOException {
//		String archivo = "/home/martin/Algoritmos 3/Tp/src/main/java/partida5.csv"; //Ruta para crear archivo
//
//		
//		assertTrue(partida.guardarPartida(archivo, partida));
//		
//	}
//	
//	@Test
//	public void testCargarPartida() throws IOException, ClassNotFoundException {
//		String archivo = "/home/martin/Algoritmos 3/Tp/src/main/java/partida5.csv"; //Ruta del archivo ya creado
//		
//		
//		assertTrue(partida.cargarPartida(archivo, partida));
//		
//	}
//	
//
//}
