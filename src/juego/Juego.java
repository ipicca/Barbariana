package juego;

import java.awt.Color;
import java.awt.Font;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Barbarianna barbarianna;
	private Piso[] pisos = new Piso[5]; // null 
	private Dinosaurio [] dinosaurios= new Dinosaurio[4];
	//private Computadora computadora;
	
	private boolean[] tocaBordeYDinos = { false, false, false, false };
	private int[] contSegundos = {0,0,0,0};
	private int puntos=0;
	private int vidas=3;
	private int contDisparos=0;
	
	
	

	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Boss Rabbit Rabber - Grupo ... - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		int xDinos=750;
		int yDinos=20;
		
		this.barbarianna = new Barbarianna(30, 570);
		
	
		for (int i=0;i<this.dinosaurios.length;i++) {
			this.dinosaurios[i]= new Dinosaurio (xDinos,yDinos);
			xDinos=xDinos-150;
		}
		
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
		
		
		//kills Dinosuarios
		entorno.cambiarFont(Font.SANS_SERIF, 25, Color.RED);
		entorno.escribirTexto("Kills: " + this.puntos, 7, 23);
		
		//vidas de Barbarianna
		entorno.cambiarFont(Font.SANS_SERIF, 25, Color.pink);
		entorno.escribirTexto("♥ " + this.vidas, 100, 23);
		// ...
		
		//CREAMOS A LOS INTERVINIENTES
		
		// DIBUJAMOS los pisos
		for (int i = 0; i < pisos.length; i++) {
			if(this.pisos[i] != null)
				this.pisos[i].dibujarPiso(entorno);
		}
		
		if (barbarianna !=null) {// If principal de Barbariana
			
			
			this.barbarianna.dibujarse(entorno);
			
			// MOVIMIENTO BARBARIANNA
			if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && barbarianna.getX() > 0+barbarianna.getAncho()/2) {
				barbarianna.moverIzquierda();
			}
			
			if(entorno.estaPresionada(entorno.TECLA_DERECHA) && 
			   barbarianna.getX()  < entorno.ancho() - barbarianna.getAncho()/2) {
				barbarianna.moverDerecha();
			}
							
																	
			if (barbarianna.getRayo() != null) {// DISPARO BARBARIANNA
				this.barbarianna.efectuarRayo(entorno);	     
			}
			
			if (this.entorno.sePresiono(entorno.TECLA_ESPACIO)) {		
				if (barbarianna.getRayo() == null)
					barbarianna.crearRayo(entorno);
			}
			
			
			//VER FUNCIONALIDADES
			
			if (entorno.estaPresionada(entorno.TECLA_ABAJO)) {
				barbarianna.agacharse();
				

			} else {
				barbarianna.pararse();
				
			}

			if (this.entorno.sePresiono(entorno.TECLA_ARRIBA))
					barbarianna.saltar();//hace un salto corto				
			
			
			if (barbarianna.enElSuelo()==false ) {
				barbarianna.caida();// CHEQUEAR NOMBRE "CAIDA"
			}
			
		
			
		} // FIN del if principal Barbarianna 
		
		 
			for (int i=0;i<this.dinosaurios.length;i++) {// INICIO ciclo de DINOSAURIOS
				
				if (dinosaurios[i]!=null) { // INICIO if principal de DINOS
					
					this.dinosaurios[i].dibujarse(entorno);
					

					// INICIO MOVIMIENTOS DE LOS DINOSAURIOS
					
					if (this.dinosaurios[i].getX() > 0+ this.dinosaurios[i].getAncho()/2 && tocaBordeYDinos[i]==false) 
						this.dinosaurios[i].moverIzquierda() ;
					
					
					if (this.dinosaurios[i].getX()==14)//Verifica toca el extremo izq del eje y
						tocaBordeYDinos[i]=true;
					
					
					if (tocaBordeYDinos[i]==true) {
						
						if (this.dinosaurios[i].getX() < entorno.ancho() - this.dinosaurios[i].getAncho()/2)
							this.dinosaurios[i].moverDerecha() ;
						
							}
					
					if (this.dinosaurios[i].getX()==786) //Verifica toca el extremo derecho del eje y
						tocaBordeYDinos[i]=false;
					
					// FIN MOVIMIENTOS DE LOS DINOSUARIOS
					
					
					if (dinosaurios[i]!=null) {
						
						if (barbarianna != null && this.dinosaurios[i].choqueRayoDino(barbarianna.getRayo())) {
							//VERIFICA SI EL DISPARO DEL RAYO DE BARBARIANNA COLOSIONA CONTRA UN DINISARIOS Y LO ELIMINA 
							
							barbarianna.setRayo(null);
							this.dinosaurios[i] = null;
							this.puntos++; // cuenta los dinos muertos
						
							}
					
						}
				
			
					  if (dinosaurios[i]!=null) {
						  
						  
						  	//
						
							if(this.dinosaurios[i].enElSuelo()==false) {
								this.dinosaurios[i].caida();
							}
							
				
							if (this.dinosaurios[i].choqueBarbariannaDino(barbarianna)) {
								//BARBARINA TOCA UN DINO O LA TOCAN A ELLA, PIERDE UNA VIDA
									
								barbarianna=null;
								vidas--;
								}
					
										
							// INICIO DISPARO DINOS
							
								
							if (i==1) {// solo dispara un dinosuario
									
								// INICIO DISPARO DINOS												
								if (this.dinosaurios[i].getLaser() == null && this.dinosaurios[i].enElSuelo())  								
									this.dinosaurios[i].crearLaser(entorno);
										
								if (this.dinosaurios[i].getLaser()!=null )
									this.dinosaurios[i].efectuarLaser(entorno);
							
								}// FIN disparo dinos
							
					  		}
											
												
							
				}//FIN if principal de DINOS
				
				else {//INICIO del ELSE principal de DINOS
					
					
					contSegundos[i] = contSegundos[i] + 1;
					
					if (contSegundos[i]> 600){ 
						
					//REAPAREN LOS DINOSUARIOS ELMINADOS CADA 600 TICK
						
						int xDinos=780;
						int yDinos=20;
						
						dinosaurios[i]= new Dinosaurio (xDinos,yDinos);
						contSegundos[i] = 0; //SETEO EN 0 PARA EL PROXIMO DINOSUARIO
						
					}
					
					
				}//FIN del ELSE principal de DINOS
				
			}// FIN ciclo de DINOSAURIOS
										
			
		
	}//FINAL DEL TICK
	
	
	public boolean estaDentroPiso (Piso piso) {// pregunta si barbariana esta dentro del hueco dependiendo del piso
												// Esto tien que servir para los dinosaurios
	
	
		if (barbarianna.getX() - barbarianna.getAncho()>= piso.getX()+piso.getAncho()/2
		&& barbarianna.getX()-barbarianna.getAncho() <= entorno.ancho() ||
		
		barbarianna.getX()+barbarianna.getAncho()>=0 && 
		barbarianna.getX()+barbarianna.getAncho()<= piso.getX() - piso.getAncho()/2)
		
	
			
	
			return true;
	
	
	return false;
	}
	
	public boolean choqueRayoLaser(Laser laser, Rayo rayo) {//colision entre reyo de barbariana y los  laser dinos
		 if (laser == null) 
	    		return false;
		 if (rayo==null)
			 return false;
	    	
	    	if(Math.abs(rayo.getX()-laser.getX())<=10 && Math.abs(rayo.getY()-laser.getY())<=27){
		        return true;
		    }
	    	return false;
		
	 }


	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
