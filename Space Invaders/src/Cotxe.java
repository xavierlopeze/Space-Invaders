import java.awt.Color;
import java.awt.Graphics; //ctrl + shift + o //fer improts

public class Cotxe extends Box {
	int pixel;
	boolean viu;
	boolean inDestruction;
	Cotxe(int x, int y, int ample, int alt, int pixel, boolean viu, boolean inDestruction){
		super(x, y);
		this.x=x; this.y=y; this.ample=ample; this.alt=alt; this.pixel = pixel; this.viu=viu; this.inDestruction=inDestruction;
	}
	
	//void dibuixa(Graphics g){
	//	g.setColor(Color.yellow);
	//	g.drawRect(x, y, ample, alt);
	//	g.drawLine(x+(int)(ample*0.5), y, x+(int)(ample*0.5),y+alt);
	//}
	
	void moviment(int windDirection){
		if (windDirection==0) x+=2*pixel;
		else x-=2*pixel;
	}
	
	void dibiuxaExplosio(Graphics g,boolean estirat){
		g.setColor(Color.white);
		
		g.fillRect(x+4*pixel,y,pixel,pixel);
		g.fillRect(x+8*pixel,y,pixel,pixel);
		
		g.fillRect(x+1*pixel,y+pixel,pixel,pixel);
		g.fillRect(x+5*pixel,y+pixel,pixel,pixel);
		g.fillRect(x+7*pixel,y+pixel,pixel,pixel);
		g.fillRect(x+11*pixel,y+pixel,pixel,pixel);
		
		g.fillRect(x+2*pixel,y+2*pixel,pixel,pixel);
		g.fillRect(x+10*pixel,y+2*pixel,pixel,pixel);
		
		g.fillRect(x+3*pixel,y+3*pixel,pixel,pixel);
		g.fillRect(x+9*pixel,y+3*pixel,pixel,pixel);
		
		g.fillRect(x,y+4*pixel,2*pixel,pixel);
		g.fillRect(x+11*pixel,y+4*pixel,2*pixel,pixel);
		
		g.fillRect(x+3*pixel,y+5*pixel,pixel,pixel);
		g.fillRect(x+9*pixel,y+5*pixel,pixel,pixel);
		
		g.fillRect(x+2*pixel,y+6*pixel,pixel,pixel);
		g.fillRect(x+10*pixel,y+6*pixel,pixel,pixel);
		
		g.fillRect(x+1*pixel,y+7*pixel,pixel,pixel);
		g.fillRect(x+5*pixel,y+7*pixel,pixel,pixel);
		g.fillRect(x+7*pixel,y+7*pixel,pixel,pixel);
		g.fillRect(x+11*pixel,y+7*pixel,pixel,pixel);
		
		g.fillRect(x+4*pixel,y+8*pixel,pixel,pixel);
		g.fillRect(x+8*pixel,y+8*pixel,pixel,pixel);
	}
	
}
