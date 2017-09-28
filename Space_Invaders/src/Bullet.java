import java.awt.Color;
import java.awt.Graphics; //ctrl + shift + o //fer improts

public class Bullet extends Cotxe {
	Bullet(int x, int y, int ample, int alt,int pixel, boolean viu, boolean inDestruction){
		super(x,y,ample,alt,pixel,viu,inDestruction);
		this.x=x; this.y=y; this.pixel=pixel; this.ample=ample; this.alt=alt; this.viu=viu; this.inDestruction=inDestruction;
		
	}
	
	void dibuixa(Graphics g){
		g.setColor(Color.white);
		
		g.fillRect(x,y+4*pixel,pixel,4*pixel);
	}
	
	void moviment(int shotdirection){
		y-=20*shotdirection;
	}
	
}
