package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;

public class Computadora {
	private int x;
    private int y;
    private int ancho, alto;
    private Image imagen;
    
    public Computadora(int x, int y) {
        this.x = x;
        this.y = y;
        //this.imagen = Herramientas.cargarImagen("images/.png");
        this.ancho=40;
        this.alto=40;
        
    }

    public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void dibujarse(Entorno entorno) {
		//entorno.dibujarImagen(imagen, this.x, this.y, 0, 0.1); // 0.1= tamaño de la imagen
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.gray);
	}
	
}
