import java.awt.Color;
import java.awt.Graphics; //ctrl + shift + o //fer improts

public class Bloc extends Box {
	
	int hitsTaken;
	int type;
	static final int SFample = 6*pixel, SFalt = 6*pixel;
	
	
	Bloc(int x, int y, int type){
		super(x,y);
		this.x=x; this.y=y; this.type = type;
		this.alt=6*pixel; this.ample = 6*pixel;
		hitsTaken = 0;
	}
	
	void dibuixa(Graphics g, int tipus){
		g.setColor(Color.green);
		
		if(tipus==1){
			if(hitsTaken==0){
				
				g.fillRect(x,y,ample,alt);
				g.setColor(Color.black);
				g.fillRect(x,y,3*pixel,pixel);
				g.fillRect(x,y+pixel,2*pixel,pixel);
				g.fillRect(x,y+2*pixel,1*pixel,pixel);
			}
			if(hitsTaken==1){

				g.fillRect(x+4*pixel,y,pixel,pixel);
				g.fillRect(x+2*pixel,y+pixel,2*pixel,pixel);
				g.fillRect(x+5*pixel,y+pixel,pixel,pixel);
				g.fillRect(x+3*pixel,y+2*pixel,3*pixel,pixel);
				g.fillRect(x+pixel,y+3*pixel,2*pixel,pixel);
				g.fillRect(x+4*pixel,y+3*pixel,2*pixel,pixel);
				g.fillRect(x+pixel,y+4*pixel,5*pixel,pixel);
				g.fillRect(x,y+5*pixel,6*pixel,pixel);
			}
			if(hitsTaken==2){
				g.fillRect(x+3*pixel,y+pixel,pixel,pixel);
				g.fillRect(x+5*pixel,y+pixel,pixel,pixel);
				
				g.fillRect(x+3*pixel,y+2*pixel,2*pixel,pixel);
				
				g.fillRect(x+pixel,y+3*pixel,2*pixel,pixel);
				g.fillRect(x+4*pixel,y+3*pixel,2*pixel,pixel);
				
				g.fillRect(x+2*pixel,y+4*pixel,pixel,pixel);
				g.fillRect(x+5*pixel,y+4*pixel,pixel,pixel);
				
				g.fillRect(x+pixel,y+5*pixel,5*pixel,pixel);
				
				
			}
			if(hitsTaken==3){
				g.fillRect(x+3*pixel,y+2*pixel,2*pixel,pixel);
				g.fillRect(x+4*pixel,y+3*pixel,pixel,pixel);
				g.fillRect(x+2*pixel,y+4*pixel,pixel,pixel);
				g.fillRect(x+5*pixel,y+4*pixel,pixel,pixel);
				g.fillRect(x+pixel,y+5*pixel,2*pixel,pixel);
				g.fillRect(x+4*pixel,y+5*pixel,2*pixel,pixel);
				
			}
		
			
		}
		else if(tipus==2){
			
			if(hitsTaken==0){
				g.setColor(Color.green);
				g.fillRect(x,y,ample,alt);
				g.setColor(Color.black);
				g.fillRect(x+3*pixel,y,3*pixel,pixel);
				g.fillRect(x+4*pixel,y+pixel,2*pixel,pixel);
				g.fillRect(x+5*pixel,y+2*pixel,1*pixel,pixel);
			}
			
			if(hitsTaken==1){

				g.fillRect(x+pixel,y,pixel,pixel);
				g.fillRect(x,y+pixel,pixel,pixel);
				g.fillRect(x+2*pixel,y+pixel,2*pixel,pixel);
				g.fillRect(x,y+2*pixel,3*pixel,pixel);
				g.fillRect(x+3*pixel,y+3*pixel,2*pixel,pixel);
				g.fillRect(x,y+3*pixel,2*pixel,pixel);
				g.fillRect(x,y+4*pixel,5*pixel,pixel);
				g.fillRect(x,y+5*pixel,6*pixel,pixel);
			}
			if(hitsTaken==2){
				g.fillRect(x+pixel,y+pixel,pixel,pixel);
				g.fillRect(x+2*pixel,y+pixel,pixel,pixel);
				
				g.fillRect(x+pixel,y+2*pixel,2*pixel,pixel);
				
				g.fillRect(x,y+3*pixel,2*pixel,pixel);
				g.fillRect(x+3*pixel,y+3*pixel,2*pixel,pixel);
				
				g.fillRect(x,y+4*pixel,pixel,pixel);
				g.fillRect(x+3*pixel,y+4*pixel,pixel,pixel);
				
				g.fillRect(x,y+5*pixel,5*pixel,pixel);
				
				
			}
			if(hitsTaken==3){
				g.fillRect(x+pixel,y+2*pixel,2*pixel,pixel);
				g.fillRect(x+pixel,y+3*pixel,pixel,pixel);
				g.fillRect(x+3*pixel,y+4*pixel,pixel,pixel);
				g.fillRect(x,y+4*pixel,pixel,pixel);
				g.fillRect(x+3*pixel,y+5*pixel,2*pixel,pixel);
				g.fillRect(x,y+5*pixel,2*pixel,pixel);
				
			}
		}
		else if(tipus==3){
			if(hitsTaken==0){
				g.setColor(Color.green);
				g.fillRect(x,y,ample,alt);
				g.setColor(Color.black);
				g.fillRect(x+4*pixel,y+2*pixel,2*pixel,pixel);
				g.fillRect(x+3*pixel,y+3*pixel,3*pixel,pixel);
				g.fillRect(x+2*pixel,y+4*pixel,4*pixel,pixel);
				g.fillRect(x+1*pixel,y+5*pixel,5*pixel,pixel);
			}
			
			if(hitsTaken==1){
				g.fillRect(x,y,6*pixel, pixel);
				g.fillRect(x+pixel,y+pixel,5*pixel, pixel);
				g.fillRect(x,y+2*pixel,2*pixel, pixel);
				g.fillRect(x+4*pixel,y+2*pixel,pixel, pixel);
				g.fillRect(x,y+3*pixel,4*pixel, pixel);
				g.fillRect(x,y+4*pixel,pixel, pixel);
				g.fillRect(x,y+5*pixel,2*pixel, pixel);
			}
			if(hitsTaken==2){
				g.fillRect(x,y,4*pixel, pixel);
				g.fillRect(x+5*pixel,y,pixel, pixel);
				
				g.fillRect(x+2*pixel,y+pixel,pixel, pixel);
				g.fillRect(x+4*pixel,y+pixel,pixel*2, pixel);
				
				g.fillRect(x+pixel,y+2*pixel,2*pixel, pixel);
				g.fillRect(x+4*pixel,y+2*pixel,pixel, pixel);
				
				g.fillRect(x+3*pixel,y+2*pixel,pixel, pixel);
				g.fillRect(x,y+3*pixel,pixel, pixel);
				
				g.fillRect(x,y+4*pixel,pixel, pixel);
			}
			if(hitsTaken==3){
				g.fillRect(x+pixel,y+pixel,pixel, pixel);
				g.fillRect(x+4*pixel,y+pixel,pixel, pixel);
				g.fillRect(x+3*pixel,y+2*pixel,pixel, pixel);
				g.fillRect(x,y+3*pixel,pixel, pixel);
			}
		}
		else if(tipus==4){
			if(hitsTaken==0){
				g.setColor(Color.green);
				g.fillRect(x,y,ample,alt);
				g.setColor(Color.black);
				g.fillRect(x,y+2*pixel,2*pixel,pixel);
				g.fillRect(x,y+3*pixel,3*pixel,pixel);
				g.fillRect(x,y+4*pixel,4*pixel,pixel);
				g.fillRect(x,y+5*pixel,5*pixel,pixel);
			}
			if(hitsTaken==1){
				g.fillRect(x,y,6*pixel,pixel);
				g.fillRect(x,y+pixel,5*pixel,pixel);
				g.fillRect(x+3*pixel,y+2*pixel,2*pixel,pixel);
				g.fillRect(x+pixel,y+2*pixel,pixel,pixel);
				g.fillRect(x+2*pixel,y+3*pixel,4*pixel,pixel);
				g.fillRect(x+5*pixel,y+4*pixel,pixel,pixel);
				g.fillRect(x+4*pixel,y+5*pixel,2*pixel,pixel);
			}
			if(hitsTaken==2){
				g.fillRect(x+2*pixel,y,4*pixel, pixel);
				g.fillRect(x,y,pixel, pixel);
				
				g.fillRect(x+3*pixel,y+pixel,pixel, pixel);
				g.fillRect(x,y+pixel,pixel*2, pixel);
				
				g.fillRect(x+pixel,y+2*pixel,pixel, pixel);
				g.fillRect(x+3*pixel,y+2*pixel,2*pixel, pixel);
				
				g.fillRect(x+2*pixel,y+2*pixel,pixel, pixel);
				g.fillRect(x+5*pixel,y+3*pixel,pixel, pixel);
				
				g.fillRect(x+5*pixel,y+4*pixel,pixel, pixel);
			}
			if(hitsTaken==3){
				g.fillRect(x+pixel,y+pixel,pixel, pixel);
				g.fillRect(x+4*pixel,y+pixel,pixel, pixel);
				g.fillRect(x+2*pixel,y+2*pixel,pixel, pixel);
				g.fillRect(x+5*pixel,y+3*pixel,pixel, pixel);
			}
			
			
			
		}
		else{
			
			if(hitsTaken==0){
				
				g.fillRect(x,y,ample,alt);
			}
			if(hitsTaken==1){
				g.setColor(Color.green);	
				g.fillRect(x,y,ample,alt);
				g.setColor(Color.black);
				g.fillRect(x+pixel,y,2*pixel,pixel);
				g.fillRect(x+5*pixel,y+pixel,pixel,pixel);
				g.fillRect(x,y+2*pixel,2*pixel,pixel);
				g.fillRect(x+3*pixel,y+2*pixel,pixel,pixel);
				g.fillRect(x,y+3*pixel,pixel,pixel);
				g.fillRect(x+2*pixel,y+4*pixel,2*pixel,pixel);
				g.fillRect(x+4*pixel,y+5*pixel,pixel,pixel);
				g.setColor(Color.green);
			}
			if(hitsTaken ==2){
				g.fillRect(x+3*pixel,y,pixel,pixel);
				g.fillRect(x+5*pixel,y,pixel,pixel);
				
				g.fillRect(x+pixel,y+pixel,pixel,pixel);
				g.fillRect(x+3*pixel,y+pixel,2*pixel,pixel);
				
				g.fillRect(x+2*pixel,y+2*pixel,pixel,pixel);
				
				g.fillRect(x+pixel,y+3*pixel,5*pixel,pixel);
				
				g.fillRect(x,y+4*pixel,pixel,pixel);
				g.fillRect(x+4*pixel,y+4*pixel,pixel,pixel);
				
				g.fillRect(x,y+5*pixel,pixel,pixel);
				g.fillRect(x+3*pixel,y+5*pixel,pixel,pixel);
				g.fillRect(x+5*pixel,y+5*pixel,pixel,pixel);
				
			}
			else if(hitsTaken==3){
				g.fillRect(x+pixel,y+pixel,pixel,pixel);
				g.fillRect(x+4*pixel,y+pixel,pixel,pixel);
				g.fillRect(x+2*pixel,y+2*pixel,pixel,pixel);
				g.fillRect(x+2*pixel,y+3*pixel,pixel,pixel);
				g.fillRect(x+5*pixel,y+3*pixel,pixel,pixel);
				g.fillRect(x,y+4*pixel,pixel,pixel);
				g.fillRect(x+4*pixel,y+4*pixel,pixel,pixel);
			}
			
		}
		
	}
	
}
