import java.awt.Graphics;

public class Linea  {
	int nDots;
	Dot[] dots;
	Linea(int y){
		nDots = Finestra.AMPLE/pixel.size;
		dots = new Dot[nDots];
		
		for(int i=0; i<nDots; i++){
			dots[i]= new Dot(i*pixel.size,y,pixel.size);
		}
	}
	void dibuixa(Graphics g){
		//g.setColor(Color.red);
		for(int i=0; i<this.nDots; i++){
			this.dots[i].dibuixa(g);
		}
		
//		g.fillRect(0,Finestra.ALT*3/18,Finestra.AMPLE,pixel.size);
	}

}