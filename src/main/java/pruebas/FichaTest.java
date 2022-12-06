//package pruebas;
//
//import static org.junit.Assert.*;
//
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
//
//public class FichaTest {
//	
//	InterfaceUsuario interfaceUsuario;
//	Partida partida;
//	Jugador jugador ;
//	ArrayList<Ficha> piezas ;
//	
//	
//	
//	@Before
//	public void init() {
//		interfaceUsuario = new InterfaceUsuario();		
//		partida = new PartidaContraJugador();
//
//		jugador = new JugadorHumano(Color.ROJA);
//		piezas = partida.inicializarFichas(Color.ROJA, jugador);
//	}
//
//	@Test
//	public void testPrimerPiezaValor0() {
//
//		Ficha ficha = piezas.get(0);
//		int valor = ficha.obtenerValor();
//		assertEquals(valor,0);
//	}
//	
//	@Test
//	public void testSegundaPiezaValor1() {
//
//		Ficha ficha = piezas.get(1);
//		int valor = ficha.obtenerValor();
//		assertEquals(valor,1);
//	}
//	
//	@Test
//	public void testPrimerPiezaValor2() {
//
//		Ficha ficha = piezas.get(2);
//		int valor = ficha.obtenerValor();
//		assertEquals(valor,2);
//	}
//	
//	@Test
//	public void testPrimerPiezaValor3() {
//
//		Ficha ficha = piezas.get(10);
//		int valor = ficha.obtenerValor();
//		assertEquals(valor,3);
//	}
//	
//	@Test
//	public void testPrimerPiezaValor4() {
//
//		Ficha ficha = piezas.get(16);
//
//		int valor = ficha.obtenerValor();
//		assertEquals(valor,4);
//	}
//	
//	@Test
//	public void testPrimerPiezaValor5() {
//
//		Ficha ficha = piezas.get(20);
//		int valor = ficha.obtenerValor();
//		assertEquals(valor,5);
//	}
//	
//	@Test
//	public void testPrimerPiezaValor6() {
//
//		Ficha ficha = piezas.get(24);
//		int valor = ficha.obtenerValor();
//		assertEquals(valor,6);
//	}
//	
//	@Test
//	public void testPrimerPiezaValor7() {
//
//		Ficha ficha = piezas.get(27);
//		int valor = ficha.obtenerValor();
//		assertEquals(valor,7);
//	}
//	
//	@Test
//	public void testPrimerPiezaValor8() {
//
//		Ficha ficha = piezas.get(30);
//		int valor = ficha.obtenerValor();
//		assertEquals(valor,8);
//	}
//	
//	@Test
//	public void testPrimerPiezaValor9() {
//
//		Ficha ficha = piezas.get(32);
//		int valor = ficha.obtenerValor();
//		assertEquals(valor,9);
//	}
//	
//	@Test
//	public void testPrimerPiezaValor10() {
//
//		Ficha ficha = piezas.get(33);
//		int valor = ficha.obtenerValor();
//		assertEquals(valor,10);
//	}
//	
//	@Test
//	public void testPrimerPiezaValor11() {
//
//		Ficha ficha = piezas.get(34);
//		int valor = ficha.obtenerValor();
//		assertEquals(valor,11);
//	}
//	
//	
//	@Test
//	public void testPiezaColorRojo() {
//		
//
//		Ficha ficha = piezas.get(0);
//		assertEquals(ficha.obtenerColor(),Color.ROJA);
//	}
//	
//	@Test
//	public void testPiezaColorAzul() {
//
//		ArrayList<Ficha> piezas = partida.inicializarFichas(Color.AZUL, jugador);
//
//		Ficha ficha = piezas.get(0);
//		assertEquals(ficha.obtenerColor(),Color.AZUL);
//	}
//	
//
//
//}
