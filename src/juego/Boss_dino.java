package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Boss_dino {
	
	private int x;
	private int y;
	private Image imagen;
	private String direccion;
	private Laser poder;
	private int ancho, alto;
	
	public Boss_dino(int x, int y) {
		this.x = x;
		this.y = y;
		this.ancho=50;
		this.alto=80;
		this.poder = null;
		this.imagen = Herramientas.cargarImagen("images/DinoBoss_izq.png");
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Laser getLaser() {
		return poder;
	}

	void setLaser(Laser laser) {
		this.poder = laser;
	}

	void moverIzquierda() {
		this.x = this.x - 2;
		this.imagen = Herramientas.cargarImagen("images/DinoBoss_izq.png");
		this.direccion = "izquierda";
		
	}

	void moverDerecha() {
		this.x = this.x + 2;
		this.imagen = Herramientas.cargarImagen("images/DinoBoss_der.png");
		this.direccion = "derecha";
		
	}
	
	void caminar() {
		if (this.direccion == "izquierda") {
			moverIzquierda();
		}if (this.direccion == "derecha")
			moverDerecha();
		}

	
	public boolean enElSuelo() { // Chequea si barbarianna esta tocando alguno de los niveles (VER EJE DE Y) En el eje x sólo toma las partes amarillas.
		if ((this.y == 120 - this.getAlto()/2 && this.getX() + this.getAncho()/2>200)|| 
				(this.y == 240 - this.getAlto()/2 && this.getX() -this.getAncho()/2<600) || 
				(this.y == 360 - this.getAlto()/2 && this.getX() +this.getAncho()/2>200)||
				(this.y == 480 - this.getAlto()/2 && this.getX() -this.getAncho()/2<600)|| 
				this.y == 570)
			return true;
		return false;
	}

	void caida() { // Si el objeto esta en el suelo.
		if (enElSuelo() == false)
		this.y = getY()+ 2;
	}
	
	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}
	
	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(imagen, this.x, this.y, 0, 0.3);
	}
	
	public void crearLaser(Entorno entorno) {
			
			if (this.direccion == null) return;
			this.poder = new Laser(this.x, this.y, direccion);
		}
	
	public void efectuarLaser(Entorno entorno) {
			
			this.poder.dibujarse(entorno);
			
			switch (this.poder.getDireccion()) {
			
			case "derecha":
				this.poder.moverDerecha();
				break;
				
			case "izquierda":
				this.poder.moverIzquierda();
				break;
			}
			
			// Cuando el rayo toque algun borde vuelvo el objeto rayo a null 
		
			if (this.poder.getX() <= 0 || this.poder.getX() >= entorno.ancho()  ) {	
				this.poder = null;
			}
		}
	
		//1
		 public boolean choqueRayoBoss_dino(Rayo rayo) {//colision entre reyo de barbariana y Boss_dino		    	
			if (rayo == null) 
		    		return false;
		    	
		    if(Math.abs(this.x-rayo.getX())<=15 && Math.abs(this.y-rayo.getY())<=27){
			        return true;
			    }
		    
		    return false;
		    }	
		 
		//2
		 public boolean choqueBarbariannaBoss_dino(Barbarianna barb) {//colision entre los personajes 
			if (barb != null) 
		    	if(Math.abs(this.x-barb.getX())<=25 && Math.abs(this.y-barb.getY())<=27){
			        return true;
			    }
		    return false;
		    
		    }
		 
		//3
		public boolean choqueLaserBoss_dinoBarba(Barbarianna barb) {//colision entre laser de boss dino y barbarianna 
			 if (this.poder == null) 
		    		return false;
		    	
		     if(Math.abs(this.poder.getX() -barb.getX())<=15 && Math.abs(this.poder.getY() -barb.getY())<=27){
			        return true;
			    }
		     return false;
		    }

		
		}
		 
		 //4
		
		
		
		/*
		void movimientoBoss() {
			
			boolean tocaBordeYBoss = false;
			
			if (getX() > 0+ getAncho()/2 && tocaBordeYBoss == false) 
					moverIzquierda() ;	
	    	
			if (getX()==14)//Verifica toca el extremo izq del eje y
				tocaBordeYBoss = true;	
			
			if (tocaBordeYBoss==true) {	    			
				if (getX() < getAncho() - getAncho()/2)
					moverDerecha() ;	    			
					}	    	

			if (getX()==786) //Verifica toca el extremo derecho del eje y
				tocaBordeYBoss=false;
			
			if(enElSuelo()==false) {
				caida();
				}
		}
		*/
		 

	

