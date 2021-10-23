package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Barbarianna barbarianna;
	private Piso[] pisos = new Piso[5]; // null 
	private Dinosaurio dinosaurio;
	private Computadora computadora;

	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Boss Rabbit Rabber - Grupo ... - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.barbarianna = new Barbarianna(30, 570);
		this.dinosaurio = new Dinosaurio (750, 55);
		
			
				this.pisos[1] = new Piso(300,450);
				this.pisos[2] = new Piso(500,330);
				this.pisos[3] = new Piso(300,210);
				this.pisos[4] = new Piso(500,90);
				
	
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		
		
		// ...
		
		//CREAMOS A LOS INTERVINIENTES
		
		if (barbarianna !=null) {					// CREAMOS A BARBARIANA
			this.barbarianna.dibujarse(entorno);
		}
		
		if (dinosaurio.cantDinos()<=6) {					// CREAMOS UN DINO SI Y SOLO SI HAY >= 6
			this.dinosaurio.dibujarse(entorno);
		}
		
		
		if (dinosaurio != null && dinosaurio.getX() > 0+ dinosaurio.getAncho()/2 && dinosaurio.getX() < entorno.ancho() - dinosaurio.getAncho()/2) {
			this.dinosaurio.moverIzquierda() ;
			}											// MOVIMIENTO DE DINOS
		
		//this.dinosaurio.moverIzquierda();
		//this.dinosaurio.caminar();

														// MOVIMIENTO BARBARIANNA
			if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && barbarianna.getX() > 0+barbarianna.getAncho()/2) {
				barbarianna.moverIzquierda();
			}
			
			if(entorno.estaPresionada(entorno.TECLA_DERECHA) && barbarianna.getX()  < entorno.ancho() - barbarianna.getAncho()/2) {
				barbarianna.moverDerecha();
			}
							
																	
			if (barbarianna.getRayo() != null) {								// DISPARO BARBARIANNA
				this.barbarianna.efectuarRayo(entorno);	     
			}
			
			if (this.entorno.sePresiono(entorno.TECLA_ESPACIO)) {		
				if (barbarianna.getRayo() == null)
					barbarianna.crearRayo(entorno);
			}
			
			if (dinosaurio.getLaser() != null) {								// DISPAROS DINOS
				this.dinosaurio.crearLaser(entorno);	     
			}
			
						//VER FUNCIONALIDADES
			
			if (entorno.estaPresionada(entorno.TECLA_ABAJO)) {
				barbarianna.agacharse();
			} else {
				barbarianna.pararse();
			}

			if (this.entorno.sePresiono(entorno.TECLA_ARRIBA)) {
				barbarianna.saltar();
			}
			
			if (barbarianna.enElSuelo()==false) {
				barbarianna.caida();				// CHEQUEAR NOMBRE "CAIDA"
			}
													
			
		
		for (int i = 0; i < pisos.length; i++) {
			if(this.pisos[i] != null)
				this.pisos[i].dibujarPiso(entorno);
		}

	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
