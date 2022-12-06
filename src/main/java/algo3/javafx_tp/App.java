package algo3.javafx_tp;

import ficha.TipoDeFicha;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import partida. *;
import tablero. *;

import java.io.File;
import java.io.IOException;

import ficha. *;


public class App extends Application {

	
	Media sound;
	File f = new File("src/main/java/partidaContraJugador.csv");
//	File f1 = new File("src/main/java/partidaContraIA.csv");

    @SuppressWarnings("null")
	@Override
    public void start(Stage stage) {
//    	sound = new Media(getClass().getResource("res/sonidoFondo.wav").toExternalForm());
//    	new MediaPlayer(sound).setAutoPlay(true);
//    	new MediaPlayer(sound).play();
    	Vista vista = new Vista(stage);
//    	vista.insertarSonido();
    	Controlador controlador = new Controlador(vista);
    	vista.dibujarMenu(stage);
    	

    	vista.apretarCargarPartida(event->{
    		try {
    			Partida partida = new Partida();
				partida = partida.cargarPartida(f, partida);
				controlador.cargarPartidaEnControlador(partida);
	        	vista.cargarPartidaEnVista(partida);  
	        	controlador.setearArmarEstrategiaTrue();
	        	controlador.iniciarPartida();
	        					
			} catch (ClassNotFoundException | IOException e1) {				
				e1.printStackTrace();
			}
    	});
    	
//    	vista.apretarCargarPartidaContraIA(event->{
//    		try {
//    			
//    			Partida partida = new PartidaContraComputadora();
//				partida = partida.cargarPartida(f1, partida);
//				controlador.cargarPartidaEnControlador(partida);
//	        	vista.cargarPartidaEnVista(partida);  
//	        	controlador.iniciarPartidaEmpezada();
//	        	
//				
//			} catch (ClassNotFoundException | IOException e1) {
//				e1.printStackTrace();
//			}
//    			
//
//    		
//    	});
    	
    	vista.empezarPartidaContraJugador(event->{
    		Partida partida = new PartidaContraJugador();
        	controlador.cargarPartidaEnControlador(partida);
        	vista.cargarPartidaEnVista(partida);  
        	controlador.iniciarPartida();
        	
        	
    	});

    	
    	vista.empezarPartidaContraIA(event->{
    		Partida partida = new PartidaContraComputadora();
    		vista.cargarPartidaEnVista(partida);
    		controlador.cargarPartidaEnControlador(partida);
    		controlador.iniciarPartida();
    		
    		
    	});
    	
    	
//    	vista.apretarIngresarNombres(event->{
//    		
//    	});
    	
    	
    	
    	
    	
    	
    	
//    	Partida partida = new PartidaContraJugador();
    	
    	/*int tipoDePartida = vista.obtenerTipoDePartida();
    	
    	if (tipoDePartida == 0) {
    		partida = new PartidaContraJugador();
    	} else {
    		partida = new PartidaContraComputadora();
    	}*/
//    	vista.cargarPartidaEnVista(partida);
    	
    	
//    	Controlador controlador = new Controlador(vista);
    	
//    	controlador.cargarPartidaEnControlador(partida);
//    	vista.dibujarMenu(stage);
//    	controlador.iniciar();
    }
    
    public static void main(String[] args) {
    	launch(args);
    }
}
