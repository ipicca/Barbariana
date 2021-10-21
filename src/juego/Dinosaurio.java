package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Dinosaurio {
	private int x;
	private int y;
	private Image imagen;
	private String direccion;
	private Laser poder;
	private int ancho, alto;
	
	
	public Dinosaurio(int x, int y) {
		this.x = x;
		this.y = y;
		this.ancho=30;
		this.alto=80;
		this.poder = null;
		this.imagen = Herramientas.cargarImagen("images/barbariana.png");
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

	void setRayo(Laser laser) {
		this.poder = laser;
	}

	void moverIzquierda() {
		this.x = this.x - 5;
		this.direccion = "izquierda";
		
	}

	void moverDerecha() {
		this.x = this.x + 5;
		this.direccion = "derecha";
		
	}
	
	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}
	

	public void dibujarse(Entorno entorno) {
		//entorno.dibujarImagen(imagen, this.x, this.y, 0, 0.1); // 0.1= tamaño de la imagen
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.GREEN);
	}
	
	public void crearLaser(Entorno entorno) {
			
			if (this.direccion == null) return;
			
			this.poder = new Laser(this.x, this.y);
		}
	
}
