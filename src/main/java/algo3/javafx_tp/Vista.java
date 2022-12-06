package algo3.javafx_tp;

import ficha.TipoDeFicha;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.application.Application;
import java.util.concurrent.TimeUnit;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import partida. *;
import tablero. *;
import excepciones.ExcepcionMovimientoInvalido;
import excepciones.ExcepcionTurnoEquivocado;
import ficha. *;

//JavaFX App

//App es el controlador
//Partida el modelo
//Vista la vista

public class Vista{

	
	private Button botonJugador;
	private Button botonComputadora;
	private Button botonCargarPartidaContraJugador;
	private Button botonIngresarNombres;
	private Button botonCargarPartidaContraIA;
	private TextField campoNombre1 ;
	private TextField campoNombre2;

	
	//DIMENSIONES DE LA ESCENA
	private final int ALTO_ESCENA = 800;
	private final int ANCHO_ESCENA = 1200;
	
	//DIMENSIONES DEL TABLERO
	private final int TAM_CANVAS_TABLERO = 800;
	private final int CANT_CASILLAS = 10;
	private final int TAM_CASILLA = TAM_CANVAS_TABLERO / CANT_CASILLAS;
	
	//DIMENSIONES MENU INICIAL
	
	
	//DIMENSIONES Y UBICACION EN ESCENA DE: MENU DEL COSTADO
	private final int ANCHO_MENU_COSTADO = 400;
	private final int ALTO_MENU_COSTADO = ALTO_ESCENA;
	private final int UBICACION_X_MENU_COSTADO =  TAM_CANVAS_TABLERO;
	
	//DIMENSIONES Y UBICACION EN LA ESCENA DE: VENTANA DE AVISOS
	private final int ANCHO_CANVAS_AVISOS = ANCHO_MENU_COSTADO;
	private final int ALTO_CANVAS_AVISOS = 300;
	private final int UBICACION_X_AVISOS =  TAM_CANVAS_TABLERO; //+ (ANCHO_ESCENA - TAM_CANVAS_TABLERO) / 8;
	private final int UBICACION_Y_AVISOS = 200;
	
	//TEXTURAS TABLERO
	//private final Image PASTO = new Image(getClass().getResourceAsStream("res/texturaPasto2.jpg"));
	private final Image AGUA = new Image(getClass().getResourceAsStream("res/texturaAgua1.jpeg"));
	private final Image MOSAICO = new Image(getClass().getResourceAsStream("res/mosaico1.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	
	
	//FICHAS AZULES
	
	private final Image EN_BLANCO_AZUL = new Image(getClass().getResourceAsStream("res/enBlancoAzul.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image BANDERA_AZUL = new Image(getClass().getResourceAsStream("res/BanderaAzul.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image ESPIA_AZUL = new Image(getClass().getResourceAsStream("res/UnoAzul.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image DOS_AZUL = new Image(getClass().getResourceAsStream("res/DosAzul.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image TRES_AZUL = new Image(getClass().getResourceAsStream("res/TresAzul.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image CUATRO_AZUL = new Image(getClass().getResourceAsStream("res/CuatroAzul.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image CINCO_AZUL = new Image(getClass().getResourceAsStream("res/CincoAzul.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image SEIS_AZUL = new Image(getClass().getResourceAsStream("res/SeisAzul.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image SIETE_AZUL = new Image(getClass().getResourceAsStream("res/SieteAzul.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image OCHO_AZUL = new Image(getClass().getResourceAsStream("res/OchoAzul.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image NUEVE_AZUL = new Image(getClass().getResourceAsStream("res/NueveAzul.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image DIEZ_AZUL = new Image(getClass().getResourceAsStream("res/DiezAzul.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image BOMBA_AZUL = new Image(getClass().getResourceAsStream("res/BombaAzul.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	
	//FICHAS VERDES
	private final Image EN_BLANCO_VERDE = new Image(getClass().getResourceAsStream("res/enBlancoVerde.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image BANDERA_VERDE = new Image(getClass().getResourceAsStream("res/BanderaVerde.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image ESPIA_VERDE = new Image(getClass().getResourceAsStream("res/UnoVerde.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image DOS_VERDE = new Image(getClass().getResourceAsStream("res/DosVerde.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image TRES_VERDE = new Image(getClass().getResourceAsStream("res/TresVerde.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image CUATRO_VERDE = new Image(getClass().getResourceAsStream("res/CuatroVerde.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image CINCO_VERDE = new Image(getClass().getResourceAsStream("res/CincoVerde.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image SEIS_VERDE = new Image(getClass().getResourceAsStream("res/SeisVerde.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image SIETE_VERDE = new Image(getClass().getResourceAsStream("res/SieteVerde.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image OCHO_VERDE = new Image(getClass().getResourceAsStream("res/OchoVerde.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image NUEVE_VERDE = new Image(getClass().getResourceAsStream("res/NueveVerde.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image DIEZ_VERDE = new Image(getClass().getResourceAsStream("res/DiezVerde.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	private final Image BOMBA_VERDE = new Image(getClass().getResourceAsStream("res/BombaVerde.png"), TAM_CASILLA, TAM_CASILLA, false, false);
	
	//TITULO
	private final Image TITULO = new Image(getClass().getResourceAsStream("res/Titulo1.png"), 100, 400, false, false);
	
	//atributos
	Partida partida;
	Stage stage;
	Scene scene;
	
	GraphicsContext contextoCanvasTablero;
	Canvas canvasTablero;
	
	GraphicsContext contextoCanvasMenuCostado;
	Canvas canvasMenuCostado;
	
	Canvas canvasAvisos;
	
	GraphicsContext contextoCanvasAvisos;
	String aviso;
	private Group group;
	
	
	public Vista(Stage stage) {
		this.stage = stage;
	}
	
	public void cargarPartidaEnVista(Partida partida) {
		this.partida = partida;
	}
	
	
	//si hago mvc no se si deberia ser 
	private Image obtenerImagenDeFicha(TipoDeFicha tipoDeFicha, ficha.Color color) {
		
		if (color.equals(ficha.Color.AZUL)) {
			if (partida.esTurnoAzules()) {
				return EN_BLANCO_AZUL;
			}
			switch (tipoDeFicha) {
				case BANDERA:
					return BANDERA_AZUL;
				case ESPIA:
					return ESPIA_AZUL;
				case DOS:
					return DOS_AZUL;
				case TRES:
					return TRES_AZUL;
				case CUATRO:
					return CUATRO_AZUL;
				case CINCO:
					return CINCO_AZUL;
				case SEIS:
					return SEIS_AZUL;	
				case SIETE:
					return SIETE_AZUL;	
				case OCHO:
					return OCHO_AZUL;
				case NUEVE:
					return NUEVE_AZUL;	
				case DIEZ:
					return DIEZ_AZUL;	
				case BOMBA:
					return BOMBA_AZUL;	
			}
		}
		
		
		
		if (color.equals(ficha.Color.ROJA)){
			if (!partida.esTurnoAzules()) {
				return EN_BLANCO_VERDE;
			}
			switch (tipoDeFicha) {
				case BANDERA:
					return BANDERA_VERDE;
				case ESPIA:
					return ESPIA_VERDE;
				case DOS:
					return DOS_VERDE;
				case TRES:
					return TRES_VERDE;
				case CUATRO:
					return CUATRO_VERDE;
				case CINCO:
					return CINCO_VERDE;
				case SEIS:
					return SEIS_VERDE;	
				case SIETE:
					return SIETE_VERDE;	
				case OCHO:
					return OCHO_VERDE;
				case NUEVE:
					return NUEVE_VERDE;	
				case DIEZ:
					return DIEZ_VERDE;	
				case BOMBA:
					return BOMBA_VERDE;	
			}
		}
		
		return null;
	}
	
	public boolean terminarPartida() {
		if(partida.terminar()) {
			return true;
		}
		return false;
	}
	
	public int obtenerTamañoDeCasilla() {
		return TAM_CASILLA;
	}
	
	public int obtenerTipoDePartida() {
		return 0;
	}
	
	
	public void avisarPosicionInvalida(){
		
//		aviso.
		this.aviso = "Posición inválida";
		Media sound = new Media(getClass().getResource("res/invalid.wav").toExternalForm());
    	new MediaPlayer(sound).play();
	}
	
	public void avisarTurnoEquivocado() {
		this.aviso = "Turno equivocado ";
		Media sound = new Media(getClass().getResource("res/invalid.wav").toExternalForm());
    	new MediaPlayer(sound).play();
	}
	
	public void avisarMovimientoInvalido() {
		this.aviso = "Movimiento inválido ";
		Media sound = new Media(getClass().getResource("res/invalid.wav").toExternalForm());
    	new MediaPlayer(sound).play();
	}
	
	
	private void dibujarTablero(Tablero tablero, GraphicsContext contextoCanvas) {
		  
       	for (int i = 0; i < 10; i++) {
    		for (int j = 0; j < 10; j++) {
    			
    			Ficha fichaAMostrar = tablero.obtenerFicha(i, j);
    			
    			contextoCanvas.drawImage(MOSAICO, 0, 0, TAM_CASILLA, TAM_CASILLA, j * TAM_CASILLA, i * TAM_CASILLA, TAM_CASILLA, TAM_CASILLA);
    			
    			if (fichaAMostrar != null && fichaAMostrar.obtenerTipoDeFicha().equals(ficha.TipoDeFicha.AGUA)) {
    				
    				contextoCanvas.drawImage(AGUA, 0, 0, TAM_CASILLA, TAM_CASILLA, j * TAM_CASILLA, i * TAM_CASILLA, TAM_CASILLA, TAM_CASILLA);
    				
    			} else if (fichaAMostrar != null){
    				
            		Image imagenFicha = obtenerImagenDeFicha(fichaAMostrar.obtenerTipoDeFicha(), fichaAMostrar.obtenerColor());
            	    double altoImagenFicha = imagenFicha.getHeight();
            	    double anchoImagenFicha = imagenFicha.getWidth();
            	    	
            	    contextoCanvas.drawImage(imagenFicha, 0, 0, altoImagenFicha, anchoImagenFicha, j * TAM_CASILLA, i * TAM_CASILLA, TAM_CASILLA, TAM_CASILLA);
    			}
    			
    			contextoCanvas.strokeRect(j * TAM_CASILLA, i * TAM_CASILLA ,TAM_CASILLA,TAM_CASILLA);
    			
    		}
    	}

	}
	
	public void dibujarMenuCostado(Canvas canvas, GraphicsContext contextoCanvas) {
		Image MOSAICO2 = new Image(getClass().getResourceAsStream("res/mosaico1.png"), 500, 20, false, false);
		Image MOSAICO3 = new Image(getClass().getResourceAsStream("res/mosaico1.png"), 20, 900, false, false);
		Image MOSAICO4 = new Image(getClass().getResourceAsStream("res/mosaico1.png"), 400, 100, false, false);
		Image TerminarEstrategia1 = new Image(getClass().getResourceAsStream("res/botonTerminarEstrategia.png"), 400, 100, false, false);
		Image TerminarEstrategia2 = new Image(getClass().getResourceAsStream("res/botonTerminarEstrategia.png"), 400, 100, false, false);
		Image Fondo1 = new Image(getClass().getResourceAsStream("res/botonTerimnar.png"), 400, 100, false, false);
		Image Fondo2 = new Image(getClass().getResourceAsStream("res/botonTerimnar.png"), 400, 100, false, false);
		Image Fondo3 = new Image(getClass().getResourceAsStream("res/BotonGuardar.png"), 400, 100, false, false);
		contextoCanvas.setFill(Color.LIGHTGRAY);
		contextoCanvas.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		contextoCanvas.drawImage(MOSAICO2, 50, 0, 400, 20, 0, 0, 400, 20);
		contextoCanvas.drawImage(MOSAICO2, 50, 0, 400, 20, 0, 780, 400, 20);
		contextoCanvas.drawImage(MOSAICO3, 0, 50, 20, 800, 0, 0, 20, 800);
		contextoCanvas.drawImage(MOSAICO3, 0, 50, 20, 800, 380, 0, 20, 800);
		contextoCanvas.drawImage(MOSAICO4, 20, 0, 360, 100, 20, 20, 360, 100);

		contextoCanvas.drawImage(Fondo1, 20, 0, 360, 100, 20, 150, 50, 50);
		contextoCanvas.drawImage(TerminarEstrategia2, 20, 0, 360, 100, 80, 150, 150, 40);
		
		contextoCanvas.drawImage(Fondo2, 20, 0, 360, 100, 20, 500, 50, 50);
		contextoCanvas.drawImage(TerminarEstrategia1, 20, 0, 360, 100, 80, 500, 150, 40);
		
		
		contextoCanvas.drawImage(Fondo3, 20, 0, 360, 100, 20, 690, 100, 40);
		contextoCanvas.setFill(Color.BLACK);
//		contextoCanvas.drawImage(Fondo, 20, 0, 360, 100, 20, 20, 360, 100);

		
		
		contextoCanvas.setFont(Font.font("Verdana", FontWeight.LIGHT, FontPosture.ITALIC, 60.0));
//		contextoCanvas.setFill(Color.BLACK);
		contextoCanvas.fillText("Stratego", 60.0, 80.0);

	}
	
	public void dibujarAviso(String aviso, GraphicsContext contextoCanvas) {
		contextoCanvas.setFill(Color.RED);
//		contextoCanvas.scale(10, 10);
		contextoCanvas.setFont(Font.font("Verdana", FontWeight.LIGHT, FontPosture.REGULAR, 20.0));
		contextoCanvas.fillText(aviso, 25, 150);
		
		this.aviso = "";
	}
	
	public void insertarSonido() {
		Media sound = new Media(getClass().getResource("res/sonidoFondo.wav").toExternalForm());
		new MediaPlayer(sound).setAutoPlay(true);
    	new MediaPlayer(sound).play();
	}
	
//	public void insertarSonidoInvalido() {
//		Media sound = new Media(getClass().getResource("res/invalid.wav").toExternalForm());
//    	new MediaPlayer(sound).play();
//	}
	
	public void insertarSonidoGuardar() {
		
		Media sound = new Media(getClass().getResource("res/sonidoGuardar.wav").toExternalForm());
    	new MediaPlayer(sound).play();
	}
	public void insertarSonidoEstrategia() {
		Media sound = new Media(getClass().getResource("res/done.wav").toExternalForm());
    	new MediaPlayer(sound).play();
	}
	
	
	
	public void dibujarMenu(Stage escenario)     {
		
		Image Fondo2 = new Image(getClass().getResourceAsStream("res/Fondo.png"), 800, 1200, true, true);
		ImageView Fondo = new ImageView(Fondo2);
		Label label = new Label("El Estratego");
		botonJugador = new Button("Partida contra jugador");
		botonComputadora = new Button("Partida contra IA");
		botonCargarPartidaContraJugador = new Button("Cargar partida");
//		botonCargarPartidaContraIA = new Button("Cargar partida contra IA");
		StackPane root = new StackPane();
		root.getChildren().addAll(Fondo);
		root.getChildren().add(label);
		root.getChildren().add(botonJugador);
//		root.getChildren().add(botonCargarPartidaContraIA);

		root.getChildren().add(botonComputadora);
		root.getChildren().add(botonCargarPartidaContraJugador);

		label.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 60.0));
		label.setTextFill(Color.WHITE);
		label.setTranslateY(-200);
		botonJugador.setTranslateY(-100);
		botonComputadora.setTranslateY(-50);
		botonCargarPartidaContraJugador.setTranslateY(0);
//		botonCargarPartidaContraIA.setTranslateY(50);
		Scene scene = new Scene(root);
		escenario.setTitle("Menu principal");
		escenario.setResizable(true);
		escenario.setScene(scene);
		escenario.show();		


    }

	public void empezarPartidaContraJugador(EventHandler<MouseEvent> eventHandler) {
		botonJugador.setOnMouseClicked(eventHandler);
	}
	
	public void empezarPartidaContraIA(EventHandler<MouseEvent> eventHandler) {
		botonComputadora.setOnMouseClicked(eventHandler);
	}
//	public void cargarPartidaContraJugador(EventHandler<MouseEvent>eventHandler) {
//		botonCargarPartidaContraJugador.setOnMouseClicked(eventHandler);
//	}

	public void dibujarMenuNombres(Stage escenario) {
		
		Label label1 = new Label("Nombre Jugador2");
		Label label2 = new Label("Nombre Jugador1");
		campoNombre1 = new TextField();
		campoNombre2 = new TextField();
		botonIngresarNombres = new Button("Ingresar");
		StackPane root = new StackPane();
		
		root.getChildren().add(botonIngresarNombres);
		root.getChildren().add(campoNombre1);
		root.getChildren().add(campoNombre2);
		root.getChildren().add(label1);
		root.getChildren().add(label2);
		
		label1.setTranslateX(25);
		label1.setTranslateY(30);
		label2.setTranslateX(-90);
		label2.setTranslateY(-10);
		
		campoNombre1.setTranslateX(90);
		campoNombre1.setTranslateY(50);
		campoNombre2.setTranslateX(-82);
		campoNombre2.setTranslateY(10);
		
		botonIngresarNombres.setTranslateY(100);
		botonIngresarNombres.setTranslateX(200);
		
		Scene scene = new Scene(root);
		escenario.setWidth(500);
		escenario.setHeight(250);
		escenario.setTitle("Ingrese los nombres");
		escenario.setScene(scene);
		escenario.show();
	}
	

	public String obtenerNombre1() {
		return campoNombre1.getText();
	}
	
	public String obtenerNombre2() {
		return campoNombre2.getText();
	}
	public void apretarIngresarNombres(EventHandler<MouseEvent> eventHandler) {
		botonIngresarNombres.setOnMouseClicked(eventHandler);
	}
	
	public void apretarCargarPartida(EventHandler<MouseEvent> eventHandler) {
		botonCargarPartidaContraJugador.setOnMouseClicked(eventHandler);
	}
//	public void apretarCargarPartidaContraIA(EventHandler<MouseEvent> eventHandler) {
//		botonCargarPartidaContraIA.setOnMouseClicked(eventHandler);
//	}
	
	//ponele inicializar escena de nombre
	public void inicializarCanvas() {
		
		//CANVAS TITULO
		Canvas canvasMenuCostado = new Canvas(400, 800);	
    	GraphicsContext contextoCanvasMenuCostado = canvasMenuCostado.getGraphicsContext2D();
		
    	this.canvasMenuCostado = canvasMenuCostado;
    	this.contextoCanvasMenuCostado = contextoCanvasMenuCostado;
    	
    	canvasMenuCostado.setTranslateX(TAM_CANVAS_TABLERO);
    	
    	dibujarMenuCostado(canvasMenuCostado, contextoCanvasMenuCostado);
    	
    		
		//CANVAS TABLERO
		Canvas canvasTablero = new Canvas(TAM_CANVAS_TABLERO, TAM_CANVAS_TABLERO);	
    	GraphicsContext contextoCanvasTablero = canvasTablero.getGraphicsContext2D();
    	
    	this.canvasTablero = canvasTablero;
    	this.contextoCanvasTablero = contextoCanvasTablero;
    	
    	canvasTablero.setTranslateY(ALTO_ESCENA - TAM_CANVAS_TABLERO);
    	
    	
    	//CANVAS AVISOS
    	Canvas canvasAvisos = new Canvas(ANCHO_CANVAS_AVISOS , ALTO_CANVAS_AVISOS);	
    	GraphicsContext contextoCanvasAvisos = canvasAvisos.getGraphicsContext2D();
    	
    	this.canvasAvisos = canvasAvisos;
    	this.contextoCanvasAvisos  = contextoCanvasAvisos ;
    	this.aviso = null;
    	
    	canvasAvisos.setTranslateX(UBICACION_X_AVISOS);
    	canvasAvisos.setTranslateY(UBICACION_Y_AVISOS);
    	Group group = new Group(); 
    	group.getChildren().add(canvasMenuCostado);
    	group.getChildren().add(canvasTablero);
    	group.getChildren().add(canvasAvisos);
    	
    	this.group = group;
    	this.scene = new Scene(group, ANCHO_ESCENA, ALTO_ESCENA);
	}

	public void mostrarMenu() {
//		this.stage = stage;
		dibujarMenuNombres(stage);
	}
	

    public void render() {
//    	System.out.print(String.format("Entra a render\n"));
    	contextoCanvasTablero.clearRect(0, 0, canvasTablero.getWidth(), canvasTablero.getHeight());
    	contextoCanvasAvisos.clearRect(0, 0, canvasAvisos.getWidth(), canvasAvisos.getHeight());
    	
//    	dibujarMenuDeArriba(stage);
    	dibujarTablero(partida.obtenerTablero(), contextoCanvasTablero);
    	dibujarAviso(aviso, contextoCanvasAvisos);
    	
        stage.setScene(scene);
        stage.show();
        
    }
	
	public void agregarEventoDeMouse(EventHandler<MouseEvent> eventHandler) {
		canvasTablero.setOnMouseClicked(eventHandler);
	}
	
	public void agregarEventoMenuCostado(EventHandler<MouseEvent> eventHandler) {
		canvasMenuCostado.setOnMouseClicked(eventHandler);
	}

	public void generarRetraso() throws InterruptedException {
		System.out.print(String.format("Entra a generar retraso\n"));
		TimeUnit.SECONDS.sleep(5);
				
	}
	
	
	public boolean chequeaJugador1() {
		return true;
	}
	
	public boolean jugador1TerminoEstrategia() {
		
		return false;
	}
	
	public boolean jugador2TerminoEstrategia() {
		return false;
	}

}

/*canvasTablero.setOnMouseClicked(new EventHandler<MouseEvent>() {
@Override
public void handle(MouseEvent event) {
	double x = event.getX();
	double y = event.getY();
    System.out.println(String.format("x: %f , y: %f", x, y));
    int i = obtenerIndiceTablero(x);
    int j = obtenerIndiceTablero(y);
    System.out.println(String.format("i: %d , j: %d", i, j));
    
    partida.realizarMovimiento(i, j);
}
});*/