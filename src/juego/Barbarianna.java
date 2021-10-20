package juego;
import entorno.Entorno;

import java.awt.Color;
import java.awt.Image;

import entorno.Herramientas;

public class Barbarianna {
	private int x;
	private int y;
	private Image imagen;
	private String direccion;
	private Rayo poder;
	private int ancho, alto;
	
	
	public Barbarianna(int x, int y) {
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
	
	public Rayo getRayo() {
		return poder;
	}

	void setRayo(Rayo rayo) {
		this.poder = rayo;
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
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.pink);
	}
	
	public void crearRayo(Entorno entorno) {
			
			if (this.direccion == null) return;
			
			this.poder = new Rayo(this.x, this.y,this.direccion);
		}
	
	public boolean noEstaPresionando(Entorno entorno, char tecla) {
		if (entorno.estaPresionada(tecla)) {
			return false;
		}
		return true;
	}

	public void efectuarRayo(Entorno entorno) {
		
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
	
	
}
