package algo3.javafx_tp;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import partida. *;

import java.io.File;
import java.io.IOException;

import excepciones.ExcepcionMovimientoInvalido;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionTurnoEquivocado;


public class Controlador {
	private Vista vista;
	Media sound;
	private static Partida partida;
	private boolean esTurnoIA = false;
	boolean jugadorEstrategia1 = false;
	boolean jugadorEstrategia2 = false;
	File f = new File("src/main/java/partidaContraJugador.csv");
	File f1 = new File("src/main/java/partidaContraIA.csv");
	
	private static int[] casillaSelecionada = null;
	
	public Controlador(Vista vista) {
		this.vista = vista;
	}
	
	public void cargarPartidaEnControlador(Partida partida) {
		this.partida = partida;
	}
		
	public void seleccionarCasilla(int posX, int posY) {
		if (casillaSelecionada == null) {
			casillaSelecionada = new int[2];
			casillaSelecionada[0] = posX;
			casillaSelecionada[1] = posY;
		}
		else {
			if (casillaSelecionada.equals(partida.obtenerTablero().obtenerFicha(posX, posY))) {
				casillaSelecionada = null;
			} 
			else if (!jugadorEstrategia1){			
				try {
					partida.armarEstrategiaJugador1(casillaSelecionada[1], casillaSelecionada[0], posY, posX);
				} catch (ExcepcionPosicionInvalida e) {
//					vista.insertarSonidoInvalido();
					vista.avisarPosicionInvalida();
				} catch (ExcepcionTurnoEquivocado e) {
//					vista.insertarSonidoInvalido();
					vista.avisarTurnoEquivocado();
				} catch (ExcepcionMovimientoInvalido e) {
//					vista.insertarSonidoInvalido();
					vista.avisarMovimientoInvalido();
				}
				casillaSelecionada = null;
			}
			else if ((!jugadorEstrategia2  && !(partida instanceof PartidaContraComputadora))) {
				try {
					partida.armarEstrategiaJugador2(casillaSelecionada[1], casillaSelecionada[0], posY, posX);
				} catch (ExcepcionPosicionInvalida e) {
//					vista.insertarSonidoInvalido();
					vista.avisarPosicionInvalida();
				} catch (ExcepcionTurnoEquivocado e) {
//					vista.insertarSonidoInvalido();
					vista.avisarTurnoEquivocado();
				} catch (ExcepcionMovimientoInvalido e) {
//					vista.insertarSonidoInvalido();
					vista.avisarMovimientoInvalido();
				}
				casillaSelecionada = null;
			}
			else {
				try {					
					System.out.print(String.format("Entra a mover ficha con xi: %d, yi: %d, xf: %d, yf: %d\n", casillaSelecionada[1], casillaSelecionada[0], posY, posX));
					partida.moverFicha(casillaSelecionada[1], casillaSelecionada[0], posY, posX);
					esTurnoIA = true;
				} catch (ExcepcionPosicionInvalida e) {
//					vista.insertarSonidoInvalido();
					vista.avisarPosicionInvalida();
				} catch (ExcepcionTurnoEquivocado e) {
//					vista.insertarSonidoInvalido();
					vista.avisarTurnoEquivocado();
				} catch (ExcepcionMovimientoInvalido e) {
//					vista.insertarSonidoInvalido();
					vista.avisarMovimientoInvalido();
				}
				esTurnoIA = true;
				casillaSelecionada = null;
			}
		}
	}	
	
	
		private int obtenerIndiceTablero(double coordenadaPixel) {
			return (int) (coordenadaPixel / vista.obtenerTamañoDeCasilla());
		}
		

		public void setearArmarEstrategiaTrue() {
			jugadorEstrategia1 = true;
			jugadorEstrategia2 = true;
		}
    

  		public void iniciarPartida() {
		    
	    	
	    	vista.inicializarCanvas();
	    	vista.render();
	    	
	    	
	    	if ((!jugadorEstrategia1 || ((!(partida instanceof PartidaContraComputadora)) && (!jugadorEstrategia2)))) {
//	    		System.out.println("Está entrando al if");
//	    		if(!jugadorEstrategia1) {
//	    			System.out.println("El jugador 1 es false");
//	    		}
//	    		if(!jugadorEstrategia2) {
//	    			System.out.println("El jugador 2 es false");
//	    		}

	    		vista.agregarEventoMenuCostado(event->{
		    		double x = event.getX();
		            double y = event.getY();
		            int i = obtenerIndiceTablero(x);
		            int j = obtenerIndiceTablero(y);
		    	
		    		
		            if(i == 0 && j==2 ) {		      
		            	jugadorEstrategia1 = true;
		            	partida.actualizarTurno();
		            	vista.render();
		                vista.insertarSonidoEstrategia();
		                System.out.println("Se puso en true jugador 1");
	                }else if((i == 0 && j==6 )  || (partida instanceof PartidaContraComputadora)){
		            	jugadorEstrategia2 = true;
		            	partida.actualizarTurno();
		            	vista.render();
		            	vista.insertarSonidoEstrategia();
		            	System.out.println("Se puso en true jugador 2");
		            }
	                else if((i == 1 && j == 8) || (i == 0 && j == 8)) {

	            		try {
						partida.guardarPartida(f, partida);
						vista.insertarSonidoGuardar();
					} catch (IOException e) {
						e.printStackTrace();
						}	            		
	            	}	            
	    		});
	    		vista.agregarEventoDeMouse(event -> {
		    		double x = event.getX();
		        	double y = event.getY();

		            int i = obtenerIndiceTablero(x);
		            int j = obtenerIndiceTablero(y);
		                
		            seleccionarCasilla(i, j);
		            vista.render();
		                
		             
		            if(partida instanceof PartidaContraComputadora && esTurnoIA == true) {
		            	partida.moverFicha(-1, -1, -1, -1);
		            	vista.render();
		            	esTurnoIA = false;
//		            	try {
//		            		vista.generarRetraso();
//		            	} catch (InterruptedException e) {
//		            		e.printStackTrace();
//		            	}
		            }
		        });
	    		
	    	}
	    	

	    	else{
	    		vista.agregarEventoDeMouse(event -> {
		    		double x = event.getX();
		        	double y = event.getY();

		            int i = obtenerIndiceTablero(x);
		            int j = obtenerIndiceTablero(y);
		                
		            seleccionarCasilla(i, j);
		            vista.render();
		                
		             
		            if(partida instanceof PartidaContraComputadora && esTurnoIA == true) {
		            	partida.moverFicha(-1, -1, -1, -1);
		            	vista.render();
		            	esTurnoIA = false;
		            	try {
		            		vista.generarRetraso();
		            	} catch (InterruptedException e) {
		            		e.printStackTrace();
		            	}
		            }
		        });
	    		
			    vista.agregarEventoMenuCostado(event->{
				    	double x = event.getX();
			            double y = event.getY();
			            int i = obtenerIndiceTablero(x);
			            int j = obtenerIndiceTablero(y);
			            
			            if((i == 1 && j == 8) || (i == 0 && j == 8)) {

			            		try {
								partida.guardarPartida(f, partida);
								vista.insertarSonidoGuardar();
							} catch (IOException e) {
								e.printStackTrace();
								}	            		
			            	}
		
			                
				    	});

	    	}
  		}
}
		    

	    		
  			    		
//	        	vista.agregarEventoDeMouse(event -> {
//	        		
//	        		
//	            	double x = event.getX();
//	            	double y = event.getY();
//	            	
//	                int i = obtenerIndiceTablero(x);
//	                int j = obtenerIndiceTablero(y);
//	                
//		            
//	                seleccionarCasilla(i, j);
//	                vista.render();
//	        	});
	    	    	
	        	
	        	
	        	
	        	
	    	
//	    	else if(jugadorEstrategia1 == true && jugadorEstrategia2 == true) {
//	    		vista.agregarEventoDeMouse(event -> {
//	            	double x = event.getX();
//	            	double y = event.getY();
//
//	                int i = obtenerIndiceTablero(x);
//	                int j = obtenerIndiceTablero(y);
//	                
//	                seleccionarCasilla(i, j);
//	                vista.render();
//	                
//	             
//	              if(partida instanceof PartidaContraComputadora && esTurnoIA == true) {
//	            	partida.moverFicha(-1, -1, -1, -1);
//	            	vista.render();
//	            	esTurnoIA = false;
//	            	try {
//	            		vista.generarRetraso();
//	            	} catch (InterruptedException e) {
//	            		e.printStackTrace();
//	            	}
//	            }
//	        });
//	    		
//		    	vista.agregarEventoMenuCostado(event->{
//			    	double x = event.getX();
//		            double y = event.getY();
//		            int i = obtenerIndiceTablero(x);
//		            int j = obtenerIndiceTablero(y);
//		            
//		            if((i == 1 && j == 8) || (i == 0 && j == 8)) {
//
//		            		try {
//							partida.guardarPartida(f, partida);
//							vista.insertarSonidoGuardar();
//						} catch (IOException e) {
//							e.printStackTrace();
//							}	            		
//		            	}
//		            
//		           
//		            System.out.print(String.format("Coordenada i: %d y j: %d\n",i,j));
//		            if(partida instanceof PartidaContraJugador) {
//		                	 if(i == 0 && j==2 ) {
//		                     	jugadorEstrategia1 = true;
//		                    	vista.insertarSonidoEstrategia();
//		                     }
//		                	 else if(i == 0 && j==6 ) {
//		                     	jugadorEstrategia2 = true;
//		                     	vista.insertarSonidoEstrategia();
//		                     }
//		                }
//		            else {
//		            	if(i == 0 && j==2 ) {
//	                     	jugadorEstrategia1 = true;
//	                     	vista.insertarSonidoEstrategia();
//	                     }
//		            }
//		            
//		            
//		                
//			    	});
//	    }
	    	
	    	

//	    	else {
//	    	vista.agregarEventoMenuCostado(event->{
//		    	double x = event.getX();
//	            double y = event.getY();
//	            int i = obtenerIndiceTablero(x);
//	            int j = obtenerIndiceTablero(y);
//	            
//	            if((i == 1 && j == 8) || (i == 0 && j == 8)) {
//
//	            		try {
//						partida.guardarPartida(f, partida);
//						vista.insertarSonidoGuardar();
//					} catch (IOException e) {
//						e.printStackTrace();
//						}	            		
//	            	}
//	            
//	           
//	            System.out.print(String.format("Coordenada i: %d y j: %d\n",i,j));
//	            if(partida instanceof PartidaContraJugador) {
//	                	 if(i == 0 && j==2 ) {
//	                     	jugadorEstrategia1 = true;
//	                    	vista.insertarSonidoEstrategia();
//	                     }
//	                	 else if(i == 0 && j==6 ) {
//	                     	jugadorEstrategia2 = true;
//	                     	vista.insertarSonidoEstrategia();
//	                     }
//	                }
//	            else {
//	            	if(i == 0 && j==2 ) {
//                     	jugadorEstrategia1 = true;
//                     	vista.insertarSonidoEstrategia();
//                     }
//	            }
//	            
//	            
//	                
//		    	});
//	    	}
//	    	if ((!jugadorEstrategia1 || ((!(partida instanceof PartidaContraComputadora)) && (!jugadorEstrategia2)))) {
//
//	    	
//	    		
//	        	vista.agregarEventoDeMouse(event -> {
//	        		
//	        		
//	            	double x = event.getX();
//	            	double y = event.getY();
//	            	
//	                int i = obtenerIndiceTablero(x);
//	                int j = obtenerIndiceTablero(y);
//	                
//		            
//	                seleccionarCasilla(i, j);
//	                vista.render();
//	        	});
//	        	
//	        	
//	        	
//	        	
//	        	
//	    	} 
  		

//	    	else {
//	    		System.out.println("Empezo la partida");
//	    		vista.agregarEventoDeMouse(event -> {
//	            	double x = event.getX();
//	            	double y = event.getY();
//
//	                int i = obtenerIndiceTablero(x);
//	                int j = obtenerIndiceTablero(y);
//	                
//	                seleccionarCasilla(i, j);
//	                vista.render();
//	                
//	             
//	              if(partida instanceof PartidaContraComputadora && esTurnoIA == true) {
//	            	partida.moverFicha(-1, -1, -1, -1);
//	            	vista.render();
//	            	esTurnoIA = false;
//	            	try {
//	            		vista.generarRetraso();
//	            	} catch (InterruptedException e) {
//	            		e.printStackTrace();
//	            	}
//	            	}
//	        	});
//	    	}
	    	
//	    }

			
		
	

//		public void iniciarPartidaEmpezada() {
//		    
//	    	
//			
//	    	vista.inicializarCanvas();
//	    	vista.render();
//	    	vista.agregarEventoMenuCostado(event->{
//	    		jugadorEstrategia1 = true;
//				jugadorEstrategia2 = true;
//		    	double x = event.getX();
//	            double y = event.getY();
//	            int i = obtenerIndiceTablero(x);
//	            int j = obtenerIndiceTablero(y);
//	            
//	            if((i == 1 && j == 8) || (i == 0 && j == 8)) {
////	            	if(partida instanceof PartidaContraJugador) {
//	            		try {
//							partida.guardarPartida(f, partida);
////							sound = new Media(getClass().getResource("res/sonidoGuardar.wav").toExternalForm());
////	                    	new MediaPlayer(sound).play();
//							vista.insertarSonidoGuardar();
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//	            	}
////	            	else {
////	            		try {
////						partida.guardarPartida(f1, partida);
////						sound = new Media(getClass().getResource("res/sonidoGuardar.wav").toExternalForm());
////	                	new MediaPlayer(sound).play();
////					} catch (IOException e) {
////						e.printStackTrace();
////					}
////	            		
////	            	}
//	            	
////	            }
//	            System.out.print(String.format("Coordenada i: %d y j: %d\n",i,j));
//	            if(partida instanceof PartidaContraJugador) {
//	                	 if(i == 0 && j==2 ) {
//	                		 vista.insertarSonidoInvalido();
//	                     }
//	                	 else if(i == 0 && j==6 ) {
//	                		 vista.insertarSonidoInvalido();
//	                     	System.out.print(String.format("Se está haciendo click en el boton de temrinar estrategia del jugador 2\n"));
//	                     }
//	                }
//	            else {
//	            	if(i == 0 && j==2 ) {
////	                 	sound = new Media(getClass().getResource("res/invalid.wav").toExternalForm());
////	                	new MediaPlayer(sound).play();
//	            		vista.insertarSonidoInvalido();
//	                 	System.out.print(String.format("Se está haciendo click en el boton de temrinar estrategia del jugador 1\n"));
//	                 }
//	            }
//	            
//	            
//	                
//		    	});
//	    	
//	    		vista.agregarEventoDeMouse(event -> {
//	    			jugadorEstrategia1 = true;
//	    			jugadorEstrategia2 = true;
//	            	double x = event.getX();
//	            	double y = event.getY();
//	
//	                int i = obtenerIndiceTablero(x);
//	                int j = obtenerIndiceTablero(y);
//	                
//	               
//	                System.out.print(String.format("Entra a evento de mouse con la coordenada i: %d y la coordenada j; %d\n", i, j));
//	                seleccionarCasilla(i, j);
//	                vista.render();
//	                
//	                
//	                
//	             
//	              if(partida instanceof PartidaContraComputadora) {
//	            	partida.moverFicha(-1, -1, -1, -1);
//	            	vista.render();
//	            	esTurnoIA = false;
//	            	try {
//	            		vista.generarRetraso();
//	            	} catch (InterruptedException e) {
//	            		e.printStackTrace();
//	            	}
//	            	}
//	        	});
//	    	}
//    	
//    
//    
//    }
    

    	

    

