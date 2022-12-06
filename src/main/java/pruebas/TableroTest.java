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
//import tablero.Tablero;
//
//public class TableroTest {
//		
//			
//		InterfaceUsuario interfaceUsuario ;
//		Partida partida;
//		Jugador jugador ;
//		ArrayList<Ficha> piezas;
//		Jugador jugador1 ;
//		Jugador jugador2 ;
//		
//		ArrayList<Ficha> piezas1;
//		ArrayList<Ficha> piezas2;
//		
//		
//	
//		@Before
//		public void init(){
//			InterfaceUsuario interfaceUsuario = new InterfaceUsuario();		
//			Partida partida = new PartidaContraJugador(interfaceUsuario);
//			
//			Jugador jugador = new JugadorHumano();
//			piezas = partida.inicializarFichas(Color.ROJA, jugador);
//			Jugador jugador1 = new JugadorHumano();
//			Jugador jugador2 = new JugadorHumano();
//			piezas1 = partida.inicializarFichas(Color.ROJA, jugador1);
//			piezas2 = partida.inicializarFichas(Color.AZUL, jugador2);
//
//			
//		}
//		@Test
//		public void testTableroInicializadoConFichasChequeaRojaLaPrimera() {
//
//			Tablero tablero = new Tablero(piezas1,piezas2);
//			Ficha ficha = tablero.obtenerFicha(0, 0);
//			assertEquals(ficha.obtenerColor(),Color.ROJA);
//		}
//		
//		@Test
//		public void testTableroInicializadoConFichasChequeaAzulLaUltima() {
//
//			Tablero tablero = new Tablero(piezas1,piezas2);
//			Ficha ficha = tablero.obtenerFicha(9, 9);
//			assertEquals(ficha.obtenerColor(),Color.AZUL);
//		}
//		
//		@Test
//		public void testMoverFichaMovimientoValidoJugador1() {
//			InterfaceUsuario interfaceUsuario = new InterfaceUsuario();		
//			Partida partida = new PartidaContraJugador(interfaceUsuario);
//
//			Jugador jugador1 = new JugadorHumano();
//			Jugador jugador2 = new JugadorHumano();
//			
//			ArrayList<Ficha> piezas1 = partida.inicializarFichas(Color.ROJA, jugador1);
//			ArrayList<Ficha> piezas2 = partida.inicializarFichas(Color.AZUL, jugador2);
//			Tablero tablero = new Tablero(piezas1,piezas2);
//			assertTrue(tablero.moverFicha(3, 0, 4, 0, jugador1));
//		}
//		
//		@Test
//		public void testMoverFichaMovimientoValidoJugador2() {
//			InterfaceUsuario interfaceUsuario = new InterfaceUsuario();		
//			Partida partida = new PartidaContraJugador(interfaceUsuario);
//
//			Jugador jugador1 = new JugadorHumano();
//			Jugador jugador2 = new JugadorHumano();
//			
//			ArrayList<Ficha> piezas1 = partida.inicializarFichas(Color.ROJA, jugador1);
//			ArrayList<Ficha> piezas2 = partida.inicializarFichas(Color.AZUL, jugador2);
//			Tablero tablero = new Tablero(piezas1,piezas2);
//			assertTrue(tablero.moverFicha(6, 0, 5, 0, jugador2));
//		}
//		
//		@Test
//		public void testMoverFichaMovimientoValidoHaciaAtras() {
//			InterfaceUsuario interfaceUsuario = new InterfaceUsuario();		
//			Partida partida = new PartidaContraJugador(interfaceUsuario);
//			Jugador jugador1 = new JugadorHumano();
//			Jugador jugador2 = new JugadorHumano();
//			
//			ArrayList<Ficha> piezas1 = partida.inicializarFichas(Color.ROJA, jugador1);
//			ArrayList<Ficha> piezas2 = partida.inicializarFichas(Color.AZUL, jugador2);
//			Tablero tablero = new Tablero(piezas1,piezas2);
//			tablero.moverFicha(3, 0, 4, 0, jugador1);
//			assertTrue(tablero.moverFicha(4, 0, 3, 0, jugador1));
//		}
//		@Test
//		public void testMoverFichaMovimientoValidoHaciaLaDerecha() {
//			InterfaceUsuario interfaceUsuario = new InterfaceUsuario();		
//			Partida partida = new PartidaContraJugador(interfaceUsuario);
//			Jugador jugador1 = new JugadorHumano();
//			Jugador jugador2 = new JugadorHumano();
//			
//			ArrayList<Ficha> piezas1 = partida.inicializarFichas(Color.ROJA, jugador1);
//			ArrayList<Ficha> piezas2 = partida.inicializarFichas(Color.AZUL, jugador2);
//			Tablero tablero = new Tablero(piezas1,piezas2);
//			tablero.moverFicha(3, 0, 4, 0, jugador1);
//			assertTrue(tablero.moverFicha(4, 0, 4, 1, jugador1));
//		}
//		
//		@Test
//		public void testMoverFichaMovimientoValidoHaciaLaIzquierda() {
//			InterfaceUsuario interfaceUsuario = new InterfaceUsuario();		
//			Partida partida = new PartidaContraJugador(interfaceUsuario);
//
//			Jugador jugador1 = new JugadorHumano();
//			Jugador jugador2 = new JugadorHumano();
//			
//			ArrayList<Ficha> piezas1 = partida.inicializarFichas(Color.ROJA, jugador1);
//			ArrayList<Ficha> piezas2 = partida.inicializarFichas(Color.AZUL, jugador2);
//			Tablero tablero = new Tablero(piezas1,piezas2);
//			tablero.moverFicha(3, 0, 4, 0, jugador1);
//			tablero.moverFicha(4, 0, 4, 1, jugador1);
//			assertTrue(tablero.moverFicha(4, 1, 4, 0, jugador1));
//		}
//		
//		@Test (expected = excepciones.ExcepcionMovimientoInvalido.class)
//		public void testMoverFichaMovimientoInvalidoHayFichaDeMismoColor() {
//			InterfaceUsuario interfaceUsuario = new InterfaceUsuario();		
//			Partida partida = new PartidaContraJugador(interfaceUsuario);
//
//			Jugador jugador1 = new JugadorHumano();
//			Jugador jugador2 = new JugadorHumano();
//			
//			ArrayList<Ficha> piezas1 = partida.inicializarFichas(Color.ROJA, jugador1);
//			ArrayList<Ficha> piezas2 = partida.inicializarFichas(Color.AZUL, jugador2);
//			Tablero tablero = new Tablero(piezas1,piezas2);
//			
//			assertFalse(tablero.moverFicha(0, 0, 1, 0, jugador1));
//		}
//		
//		@Test
//		public void testComerFichaOponente() {
//			InterfaceUsuario interfaceUsuario = new InterfaceUsuario();		
//			Partida partida = new PartidaContraJugador(interfaceUsuario);
//
//			Jugador jugador1 = new JugadorHumano();
//			Jugador jugador2 = new JugadorHumano();
//			
//			ArrayList<Ficha> piezas1 = partida.inicializarFichas(Color.ROJA, jugador1);
//			ArrayList<Ficha> piezas2 = partida.inicializarFichas(Color.AZUL, jugador2);
//			Tablero tablero = new Tablero(piezas1,piezas2);
//			tablero.moverFicha(3, 0, 4, 0, jugador1);
//			tablero.moverFicha(4, 0, 5, 0, jugador1);
//			assertTrue(tablero.moverFicha(5, 0, 6, 0, jugador1));
//			
//		}
//		@Test (expected = excepciones.ExcepcionMovimientoInvalido.class)
//		public void testMovimientoInvalido() {
//			InterfaceUsuario interfaceUsuario = new InterfaceUsuario();		
//			Partida partida = new PartidaContraJugador(interfaceUsuario);
//			Jugador jugador1 = new JugadorHumano();
//			Jugador jugador2 = new JugadorHumano();
//			
//			ArrayList<Ficha> piezas1 = partida.inicializarFichas(Color.ROJA, jugador1);
//			ArrayList<Ficha> piezas2 = partida.inicializarFichas(Color.AZUL, jugador2);
//			Tablero tablero = new Tablero(piezas1,piezas2);
//			assertNull(tablero.moverFicha(3, 0, 4, 1, jugador1));
//			
//			
//		}
//		
//		@Test (expected = excepciones.ExcepcionTurnoEquivocado.class)
//		public void testMoverFichaDelOtroJugador() {
//			InterfaceUsuario interfaceUsuario = new InterfaceUsuario();		
//			Partida partida = new PartidaContraJugador(interfaceUsuario);
//
//			Jugador jugador1 = new JugadorHumano();
//			Jugador jugador2 = new JugadorHumano();
//			
//			ArrayList<Ficha> piezas1 = partida.inicializarFichas(Color.ROJA, jugador1);
//			ArrayList<Ficha> piezas2 = partida.inicializarFichas(Color.AZUL, jugador2);
//			Tablero tablero = new Tablero(piezas1,piezas2);
//			
//			
//			assertFalse(tablero.moverFicha(3, 0, 4, 0, jugador2));
//			
//			
//		}
//		
//		
//
//
//}
