package juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Barbarianna barbarianna;
	private Piso[] pisos = new Piso[4]; // null 
	private Dinosaurio [] dinosaurios= new Dinosaurio[4];
	private Computadora computadora;
	
	private boolean[] tocaBordeYDinos = { false, false, false, false };
	private int dinosMuertos=0;
	private int puntos=0;
	private int vidas=3;
	private int aparicionDinos=0;
	private Image imagen;

	

	// Variables y mÃ©todos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Boss Rabbit Rabber - Grupo ... - v1", 800, 600);
	
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.pisos[0] = new Piso(300,480);
		this.pisos[1] = new Piso(500,360);
		this.pisos[2] = new Piso(300,240);
		this.pisos[3] = new Piso(500,120);
		
		this.computadora= new Computadora (500,93);
		
		this.barbarianna = new Barbarianna(30, 570);
		
		int xDinos=750;
		int yDinos=60;
		
		for (int i=0;i<this.dinosaurios.length;i++) {
	
				this.dinosaurios[i]= new Dinosaurio (xDinos,yDinos);
				xDinos=xDinos-150;
				
		}


	
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el mÃ©todo tick() serÃ¡ ejecutado en cada instante y 
	 * por lo tanto es el mÃ©todo mÃ¡s importante de esta clase. AquÃ­ se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{//INICIO DEL TICK
		// Procesamiento de un instante de tiempo
		
	
		
		
		//CREAMOS A LOS INTERVINIENTES
		this.tableroGame();
		
		this.inicializarPisos();
		this.inicializarComputadora();
		this.inicializarBarbarianna();
		this.inicializarDinosaurios();
		
		if (this.colisionComputadora(barbarianna, computadora)) {
			
			this.entorno.removeAll();
	  		this.Ganador();
	  			
	  		}
		
	}//FINAL DEL TICK
	
	
	
	public void tableroGame () {
		//fondo del juego
		this.entorno.dibujarImagen(Herramientas.cargarImagen("images/fondo5.png"), 400, 300, 0);
		
		//kills Dinosuarios
		entorno.cambiarFont(Font.SANS_SERIF, 20, Color.RED);
		entorno.escribirTexto(" " + this.dinosMuertos, 40, 28);
		
		//vidas de Barbarianna
		entorno.cambiarFont(Font.SANS_SERIF, 20, Color.pink);
		entorno.escribirTexto( " " + this.vidas, 147, 28);
		// ...
		
		//puntos del juego
		entorno.cambiarFont(Font.SANS_SERIF, 20, Color.magenta);
		entorno.escribirTexto( " " + this.puntos, 270, 28);
		// ...
		
		this.entorno.dibujarImagen(Herramientas.cargarImagen("images/dinosmuertos.png"), 24, 20, 0,0.1);
		this.entorno.dibujarImagen(Herramientas.cargarImagen("images/vida.png"), 117, 20, 0, 0.06);
		this.entorno.dibujarImagen(Herramientas.cargarImagen("images/puntos.png"), 223, 22, 0, 0.15);
		
	}
	
	
	public boolean estaDentroPiso (Piso piso) {// pregunta si barbariana esta dentro del hueco dependiendo del piso
												// Esto tien que servir para los dinosaurios
	
		if (((barbarianna.getX() - barbarianna.getAncho()>= piso.getX()+piso.getAncho()/2
				&& barbarianna.getX()-barbarianna.getAncho() <= entorno.ancho() ) ||
				barbarianna.getX()+barbarianna.getAncho()>=0 &&	barbarianna.getX()+barbarianna.getAncho()<= piso.getX() - piso.getAncho()/2)
				&& ((barbarianna.getY()-barbarianna.getAlto()/2>=piso.getY() + piso.getAlto()/2)))
	
			return true;
	
	return false;
	}
	
	void inicializarComputadora () {
		
		
		if (this.computadora!=null)
			this.computadora.dibujarse(entorno);
		
		this.computadora.saltar();
		
		if (this.computadora.estaEnPosY()==false)
			this.computadora.caida();
	
	}

	
	void inicializarBarbarianna () {
		
		
		if (barbarianna !=null) {// If principal de Barbariana
				
				this.barbarianna.dibujarse(entorno);
				
				// MOVIMIENTO BARBARIANNA
				if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && barbarianna.getX() > 0+barbarianna.getAncho()/2) {
					barbarianna.moverIzquierda();
				}
				
				if (entorno.estaPresionada(entorno.TECLA_DERECHA) && 
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
				/*
				if (entorno.estaPresionada(entorno.TECLA_ABAJO)) {
						barbarianna.agacharse();
				
				} else {
					barbarianna.pararse();
					
				}*/
	
				if (this.entorno.sePresiono(entorno.TECLA_ARRIBA))
						barbarianna.saltar();//hace un salto corto
				
				
				if(this.entorno.sePresiono('u')&&
						(estaDentroPiso(pisos[0]) ||
						estaDentroPiso(pisos[1]) ||
						estaDentroPiso(pisos[2])||
						estaDentroPiso(pisos[3])))
						barbarianna.subirPiso();
				
				
				if (barbarianna.enElSuelo()==false ) {
					barbarianna.caida();// CHEQUEAR NOMBRE "CAIDA"
					
					
				
					
				}
				
			
				
			} // FIN del if principal Barbarianna 
		
	}
	
	void inicializarPisos() {
		// DIBUJAMOS los pisos
				for (int i = 0; i < pisos.length; i++) {
					if(this.pisos[i] != null)
						
						this.pisos[i].dibujarPiso(entorno);
				}
				
	}
	
	
	
	 public boolean choqueLaserBarbarianna (Barbarianna barb, Laser laser) {//CUANDO EL LASER DE LOS DINOS TOCA A BARBARIANNA PIE
	    	
		 if (laser == null) 
	    	return false;
	    	
	     if(barb!= null && Math.abs(barb.getX()-laser.getX())<=15 && Math.abs(barb.getY()-laser.getY())<=27){
		        return true;
		    }
	    	return false;
	    }
	
	 
	 public boolean colisionComputadora(Barbarianna barbarianna, Computadora computadora) {
		 	if (computadora ==null)
		 		return false;
		 
	    	if(barbarianna != null &&Math.abs(barbarianna.getX()-computadora.getX())<=15 && Math.abs(barbarianna.getY()-computadora.getY())<=20){
		        return true;
		    }
	    	
	    return false;
	    
	    }
	 

	 
	void inicializarDinosaurios () {
	
	
		for (int i=0;i<this.dinosaurios.length;i++) {// INICIO ciclo de DINOSAURIOS
				
				if (dinosaurios[i]!=null) { // INICIO if principal de DINOS
					
					
					this.dinosaurios[i].dibujarse(entorno);
					
					this.movimientoDeLosDinos(i);
					this.disparoDinos(i);
					this.colisiones(i);
					  
					if (barbarianna!= null && this.dinosaurios[i]!= null && this.vidas==0) {
							
						this.entorno.removeAll();
				  		this.GameOver();
				  			
				  		}
							
				}//FIN if principal de DINOS
			
			else {//INICIO del ELSE principal de DINOS
				
					this.reaPareserDinos(i);
	
				
			}//FIN del ELSE principal de DINOS
				
			
			
		}// FIN ciclo de DINOSAURIOS
	
	}
	
	
	void GameOver() {
		
		this.entorno.dibujarImagen(Herramientas.cargarImagen("images/black.jpg"), 400, 300, 0);
		this.entorno.dibujarImagen(Herramientas.cargarImagen("images/GO.png"), 400, 200, 0,0.3);
		
		//kills Dinosuarios
		entorno.cambiarFont(Font.SANS_SERIF, 50, Color.RED);
		entorno.escribirTexto(" " + this.dinosMuertos, 330, 432);
		
	
		//puntos del juego
		entorno.cambiarFont(Font.SANS_SERIF, 50, Color.magenta);
		entorno.escribirTexto( " " + this.puntos, 410, 498);
		// ...
		
		this.entorno.dibujarImagen(Herramientas.cargarImagen("images/dinosmuertos.png"), 270, 400, 0,0.3);
		this.entorno.dibujarImagen(Herramientas.cargarImagen("images/puntos.png"), 310, 480, 0, 0.33);
		

	}
	
	
	void Ganador () {
		this.entorno.dibujarImagen(Herramientas.cargarImagen("images/black.jpg"), 400, 300, 0);
		this.entorno.dibujarImagen(Herramientas.cargarImagen("images/ganaste.png"), 400, 200, 0,0.5);
		
		//kills Dinosuarios
		entorno.cambiarFont(Font.SANS_SERIF, 50, Color.RED);
		entorno.escribirTexto(" " + this.dinosMuertos, 330, 432);
		this.entorno.dibujarImagen(Herramientas.cargarImagen("images/dinosmuertos.png"), 270, 400, 0,0.3);
	
		//puntos del juego
		entorno.cambiarFont(Font.SANS_SERIF, 50, Color.magenta);
		entorno.escribirTexto( " " + this.puntos, 410, 498);
		this.entorno.dibujarImagen(Herramientas.cargarImagen("images/puntos.png"), 310, 480, 0, 0.33);
		// ...
		
	}
	
	
	void movimientoDeLosDinos(int i) {
		// INICIO MOVIMIENTOS DE LOS DINOSAURIOS
		
		if (this.dinosaurios[i].getX() > 0+ this.dinosaurios[i].getAncho()/2 && tocaBordeYDinos[i]==false) 
			this.dinosaurios[i].moverIzquierda() ;
		
		if (this.dinosaurios[i].getX()<=15)//Verifica toca el extremo izq del eje y
				tocaBordeYDinos[i]=true;
			
		if (this.dinosaurios[i].getX() < entorno.ancho() - this.dinosaurios[i].getAncho()/2 && tocaBordeYDinos[i]==true)
				this.dinosaurios[i].moverDerecha() ;
				
	
		if (this.dinosaurios[i].getX()>=780) //Verifica toca el extremo derecho del eje y
				tocaBordeYDinos[i]=false;

		if(this.dinosaurios[i].enElSuelo()==false) {
			this.dinosaurios[i].caida();
			}
		
		
		}
	
	
		void disparoDinos(int i) {
			if (i==3) {// solo dispara un dinosuario
				
				// INICIO DISPARO DINOS	
				
				if (this.dinosaurios[i].getLaser() == null && this.dinosaurios[i].enElSuelo())  								
					
					this.dinosaurios[i].crearLaser(entorno);
				
	
				if (this.dinosaurios[i].getLaser()!=null )
					
					this.dinosaurios[i].efectuarLaser(entorno);
				
				}// FIN disparo dinos
			
				
				/*
				if (i==1) {// solo dispara un dinosuario
				
				// INICIO DISPARO DINOS	
				
				if (this.dinosaurios[i].getLaser() == null && this.dinosaurios[i].enElSuelo())  								
					
					this.dinosaurios[i].crearLaser(entorno);
				
	
				if (this.dinosaurios[i].getLaser()!=null )
					
					this.dinosaurios[i].efectuarLaser(entorno);
				
				}// FIN disparo dinos
			
				 */
		}
		
	void colisiones(int i) {
			
			//COLISION ENTRE LASER DE LOS DINOS Y BARBARIANNA
			
				if ( this.dinosaurios[i]!=null && this.choqueLaserBarbarianna(this.barbarianna, this.dinosaurios[i].getLaser())) {
					  this.dinosaurios[i].setLaser(null);
					  this.vidas--;
					  this.barbarianna = new Barbarianna(30, 570);
				  }
				
				// COLISION ENTRE PERSONAJES
				if (this.dinosaurios[i]!=null && this.dinosaurios[i].choqueBarbariannaDino(barbarianna)) {
				//BARBARINA TOCA UN DINO O LA TOCAN A ELLA, PIERDE UNA VIDA
						
					this.vidas--;
					this.barbarianna = new Barbarianna(30, 570);
					
				}
				
				//VERIFICA SI EL DISPARO DEL RAYO DE BARBARIANNA COLOSIONA CONTRA UN DINISARIOS Y LO ELIMINA 
				if (barbarianna != null && this.dinosaurios[i].choqueRayoDino(barbarianna.getRayo())) {
					
					
					barbarianna.setRayo(null);
					this.dinosaurios[i] = null;
					this.dinosMuertos++;
					this.puntos+=5; // cuenta los dinos muertos
				
					}
				
				//VEREIFICA SI EL DINOSAURIO TOCA EXTREMO IZQUIERDO DEL PRIMER PISO 
				if( this.dinosaurios[i]!=null && this.dinosaurios[i].getX()<=21 && this.dinosaurios[i].getY()>=550)
					this.dinosaurios[i]=null;
				
			
				
		}
	
	

	
	void reaPareserDinos(int i) {
		
		aparicionDinos++;
	
		if (aparicionDinos> 400){ 
			
		//REAPAREN LOS DINOSUARIOS ELMINADOS CADA 600 TICK
			
			int xDino=770;
			int yDino=50;
			
			dinosaurios[i]= new Dinosaurio (xDino,yDino);	
			aparicionDinos=0;
			
		}
		
	}
	
	



	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}