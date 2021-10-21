package juego;


import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Barbarianna barbarianna;
	private Piso[] pisos = new Piso[5]; // null 
	private Dinosaurio[] dinosaurios = new Dinosaurio[4];

	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Boss Rabbit Rabber - Grupo ... - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.barbarianna = new Barbarianna(30, 570);
		
			
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
		if (barbarianna !=null) {
			this.barbarianna.dibujarse(entorno);
			
			if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && barbarianna.getX() > 0+barbarianna.getAncho()/2) {
				barbarianna.moverIzquierda();
			}
			
			if(entorno.estaPresionada(entorno.TECLA_DERECHA) && barbarianna.getX()  < entorno.ancho() - barbarianna.getAncho()/2) {
				barbarianna.moverDerecha();
			}
			
			if (this.entorno.sePresiono(entorno.TECLA_ESPACIO)) {
				if (barbarianna.getRayo() == null)
					barbarianna.crearRayo(entorno);
			}
			
			if (barbarianna.getRayo() != null)
				this.barbarianna.efectuarRayo(entorno);
		     
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
