package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;

public class Laser {
	private int x;
    private int y;
    private Image imagen;
    private double diametro;

    public Laser(int x, int y) {
        this.x = x;
        this.y = y;
        //this.imagen = Herramientas.cargarImagen("images/.png");
        this.diametro = 15;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
   

    void moverIzquierda() {
        this.x = this.x - 6;
    }

    void moverDerecha() {
        this.x = this.x + 6;
    }


    public void dibujarse(Entorno entorno) {
       // entorno.dibujarImagen(imagen, this.x, this.y, 0, 0.07);
    	entorno.dibujarCirculo(this.x, this.y, this.diametro, Color.red);
}
}
