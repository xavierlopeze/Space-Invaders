import java.awt.Color;
import java.awt.Graphics;


public class CotxeConductor extends Cotxe {
	
	CotxeConductor(int x, int y, int ample, int alt, int pixel,boolean viu, boolean inDestruction) {
		super(x, y, ample, alt,pixel,viu, inDestruction);
		
		this.x=x; this.y=y; this.pixel = pixel; this.ample=13*pixel; this.alt=7*pixel; this.inDestruction=inDestruction;
		
	}
	
	void dibuixa(Graphics g){
		g.setColor(Color.green);
		g.fillRect(x+6*pixel,y,pixel,pixel);
		g.fillRect(x+5*pixel,y+1*pixel,3*pixel,2*pixel);
		g.fillRect(x+pixel,y+3*pixel,11*pixel,pixel);
		g.fillRect(x,y+4*pixel,13*pixel,4*pixel);
		//g.fillRect(x+1,y+3,pixel+12,pixel);
		//g.fillRect(x,y+4,pixel+4,pixel+14);
	}
	
	void dibuixaExplosio(Graphics g, int i){
		
		if(i%5==0 ){
			g.setColor(Color.green);
			g.fillRect(x,y,pixel,pixel);
			g.fillRect(x+6*pixel,y,pixel,pixel);
			g.fillRect(x+12*pixel,y,pixel,pixel);
			
			g.fillRect(x+3*pixel,y+pixel,pixel,pixel);
			
			g.fillRect(x+pixel,y+2*pixel,pixel,pixel);
			g.fillRect(x+6*pixel,y+2*pixel,pixel,pixel);
			g.fillRect(x+12*pixel,y+2*pixel,pixel,pixel);
			
			g.fillRect(x+5*pixel,y+3*pixel,pixel,pixel);
			g.fillRect(x+10*pixel,y+3*pixel,pixel,pixel);
			g.fillRect(x+14*pixel,y+3*pixel,pixel,pixel);
			
			g.fillRect(x,y+4*pixel,pixel,pixel);
			g.fillRect(x+4*pixel,y+4*pixel,2*pixel,pixel);
			g.fillRect(x+8*pixel,y+4*pixel,pixel,pixel);
			
			g.fillRect(x+3*pixel,y+5*pixel,7*pixel,pixel);
			g.fillRect(x+12*pixel,y+5*pixel,pixel,pixel);
			
			g.fillRect(x+2*pixel,y+6*pixel,9*pixel,pixel);
			g.fillRect(x,y+6*pixel,13*pixel,pixel);
		}
		else{
			g.setColor(Color.green);
			g.fillRect(x+4*pixel,y-pixel,pixel,pixel);
			
			g.fillRect(x+2*pixel,y+pixel,pixel,pixel);
			g.fillRect(x+7*pixel,y+pixel,pixel,pixel);
			g.fillRect(x+10*pixel,y+pixel,pixel,pixel);
			
			g.fillRect(x+4*pixel,y+2*pixel,pixel,pixel);
			g.fillRect(x+6*pixel,y+2*pixel,pixel,pixel);
			g.fillRect(x+9*pixel,y+2*pixel,pixel,pixel);
			g.fillRect(x+11*pixel,y+2*pixel,pixel,pixel);
			
			g.fillRect(x+2*pixel,y+3*pixel,pixel,pixel);
			g.fillRect(x+12*pixel,y+3*pixel,pixel,pixel);
			
			g.fillRect(x+5*pixel,y+4*pixel,pixel,pixel);
			g.fillRect(x+7*pixel,y+4*pixel,2*pixel,pixel);
			
			g.fillRect(x,y+5*pixel,pixel,pixel);
			g.fillRect(x+3*pixel,y+5*pixel,8*pixel,pixel);
			
			g.fillRect(x+2*pixel,y+6*pixel,10*pixel,pixel);
			
			g.fillRect(x,y+7*pixel,13*pixel,pixel);
			
		}
		
		
	}
	
	void dibuixaCano(Graphics g){
		//g.drawRect(x+ample/2-ample/16, y-alt/2, ample/8, alt/2);
	}

}
