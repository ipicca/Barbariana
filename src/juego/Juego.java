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
	boolean tocaBordeY=false;

	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Boss Rabbit Rabber - Grupo ... - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.barbarianna = new Barbarianna(30, 570);
		this.dinosaurio = new Dinosaurio (750, 20);
		
			
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
		
		
		if (dinosaurio != null && dinosaurio.getX() > 0+ dinosaurio.getAncho()/2 && tocaBordeY==false) 
			this.dinosaurio.moverIzquierda() ;
		
		if (dinosaurio.getX()==14)
			tocaBordeY=true;
		
		
		if (tocaBordeY==true)
			if (dinosaurio.getX() < entorno.ancho() - dinosaurio.getAncho()/2)
					this.dinosaurio.moverDerecha() ;
		
		if (dinosaurio.getX()==786) //cuando toca el extremo d
			tocaBordeY=false;											// MOVIMIENTO DE DINOS
		
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
				if (this.estaDentroPiso(this.pisos[1])==false) // si esta por debajo de los pisos
					barbarianna.saltar();						//hace un salto corto
				
				else
					barbarianna.saltarMasALto(); // salta mas alto dentro de los huecos
												 
			}
			
			
			if (barbarianna.enElSuelo()==false) {
				barbarianna.caida();				// CHEQUEAR NOMBRE "CAIDA"
			}
			
			if(this.entorno.sePresiono('u')) {
				if(this.estaDentroPiso(this.pisos[1]) || this.estaDentroPiso(this.pisos[2]) || this.estaDentroPiso(this.pisos[3]) || this.estaDentroPiso(this.pisos[4]))
						barbarianna.subirPiso();
			}
			if(dinosaurio.enElSuelo()==false) {
				dinosaurio.caida();
			}
													
			
		
		for (int i = 0; i < pisos.length; i++) {
			if(this.pisos[i] != null)
				this.pisos[i].dibujarPiso(entorno);
		}

	}
	
	
	public boolean estaDentroPiso (Piso piso) {// pregunta si barbariana esta dentro del hueco
											   // dependiendo del piso
												// Esto tien que servir para los dinosaurios
			
	
	if (barbarianna.getX() - barbarianna.getAncho()>= piso.getX()+piso.getAncho()/2
		&& barbarianna.getX()-barbarianna.getAncho() <= entorno.ancho() ||
		
		barbarianna.getX()+barbarianna.getAncho()>=0 && 
		barbarianna.getX()+barbarianna.getAncho()<= piso.getX() - piso.getAncho()/2)
	
		return true;
	
	
	return false;
	}
	
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
