import java.awt.Color;
import java.awt.Graphics;


public class LargeInvader extends Cotxe {
	boolean explotat;
	int alturaPerillosa;
	LargeInvader(int x, int y, int ample, int alt, int pixel, boolean viu, boolean explotat, boolean inDestruction, int alturaPerillosa) {
		super(x, y, ample, alt,pixel,viu,inDestruction);
		this.x=x; this.y=y; this.pixel = pixel; this.ample=12*pixel; this.alt=8*pixel; this.explotat = explotat; this.inDestruction=inDestruction; this.alturaPerillosa=alturaPerillosa;
	}
void dibuixa(Graphics g, boolean estirat){
	if(y+this.alt>=Finestra.ALT*8/9-70){
		g.setColor(Color.red);
	}
	else if(y+this.alt-10<=alturaPerillosa){
		g.setColor(Color.white);
	}
		else{
			g.setColor(Color.green);
		}
			
			if(estirat==true){
				g.fillRect(x+2*pixel,y,pixel,pixel);
				g.fillRect(x+9*pixel,y,pixel,pixel);
				
				g.fillRect(x+3*pixel,y+pixel,pixel,pixel);
				g.fillRect(x+8*pixel,y+pixel,pixel,pixel);
				g.fillRect(x,y+pixel,pixel,pixel);
				g.fillRect(x+11*pixel,y+pixel,pixel,pixel);
				
				
				g.fillRect(x+2*pixel,y+2*pixel,8*pixel,pixel);
				g.fillRect(x,y+2*pixel,pixel,pixel);
				g.fillRect(x+11*pixel,y+2*pixel,pixel,pixel);
				
				g.fillRect(x,y+3*pixel,3*pixel,pixel);
				g.fillRect(x+4*pixel,y+3*pixel,4*pixel,pixel);
				g.fillRect(x+9*pixel,y+3*pixel,3*pixel,pixel);
				
				
				g.fillRect(x,y+4*pixel,12*pixel,pixel);
				
				g.fillRect(x+pixel,y+5*pixel,10*pixel,pixel);
				
				g.fillRect(x+2*pixel,y+6*pixel,pixel,pixel);
				g.fillRect(x+9*pixel,y+6*pixel,pixel,pixel);
				
				g.fillRect(x+1*pixel,y+7*pixel,pixel,pixel);
				g.fillRect(x+10*pixel,y+7*pixel,pixel,pixel);
				
				
			}
			else{
				g.fillRect(x+2*pixel,y,pixel,pixel);
				g.fillRect(x+9*pixel,y,pixel,pixel);
				
				g.fillRect(x+3*pixel,y+pixel,pixel,pixel);
				g.fillRect(x+8*pixel,y+pixel,pixel,pixel);
				
				g.fillRect(x+2*pixel,y+2*pixel,8*pixel,pixel);
			
				g.fillRect(x+1*pixel,y+3*pixel,2*pixel,pixel);
				g.fillRect(x+4*pixel,y+3*pixel,4*pixel,pixel);
				g.fillRect(x+9*pixel,y+3*pixel,2*pixel,pixel);
				
				g.fillRect(x,y+4*pixel,12*pixel,pixel);
				
				g.fillRect(x,y+5*pixel,1*pixel,pixel);
				g.fillRect(x+2*pixel,y+5*pixel,8*pixel,pixel);
				g.fillRect(x+11*pixel,y+5*pixel,1*pixel,pixel);
				
				g.fillRect(x,y+6*pixel,pixel,pixel);
				g.fillRect(x+2*pixel,y+6*pixel,pixel,pixel);
				g.fillRect(x+9*pixel,y+6*pixel,pixel,pixel);
				g.fillRect(x+9*pixel,y+6*pixel,pixel,pixel);
				
				g.fillRect(x+3*pixel,y+7*pixel,2*pixel,pixel);
				g.fillRect(x+7*pixel,y+7*pixel,2*pixel,pixel);
				
				
			}
		}

}

