import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class SpecialInvader extends Cotxe {
	boolean visibilitat;
	int timer;
	static int score;
	SpecialInvader(int x, int y, int ample, int alt,int pixel,boolean viu,boolean inDestruction) {
		super(x, y, ample, alt,pixel,viu,inDestruction);
		this.x=x; this.y=y; this.pixel = pixel; this.ample=ample=16*pixel; this.alt=7*pixel; this.inDestruction=inDestruction;
		visibilitat = false;
		timer = 0;
	}
	void dibuixa(Graphics g){
			g.setColor(Color.red);
			
			g.fillRect(x+5*pixel,y,6*pixel,pixel);
			g.fillRect(x+3*pixel,y+pixel,10*pixel,pixel);
			g.fillRect(x+2*pixel,y+2*pixel,12*pixel,pixel);
			
			g.fillRect(x+1*pixel,y+3*pixel,2*pixel,pixel);
			g.fillRect(x+4*pixel,y+3*pixel,2*pixel,pixel);
			g.fillRect(x+7*pixel,y+3*pixel,2*pixel,pixel);
			g.fillRect(x+10*pixel,y+3*pixel,2*pixel,pixel);
			g.fillRect(x+13*pixel,y+3*pixel,2*pixel,pixel);
			
			g.fillRect(x,y+4*pixel,16*pixel,pixel);
			
			g.fillRect(x+2*pixel,y+5*pixel,3*pixel,pixel);
			g.fillRect(x+7*pixel,y+5*pixel,2*pixel,pixel);
			g.fillRect(x+11*pixel,y+5*pixel,3*pixel,pixel);

			g.fillRect(x+3*pixel,y+6*pixel,pixel,pixel);
			g.fillRect(x+12*pixel,y+6*pixel,pixel,pixel);
	}
	void moviment(int specialInvaderWindDirection){
		if (specialInvaderWindDirection==0) x+=4;
		else x-=4;
	}
	void dibuixaExplosio(Graphics g){
		
		
		if(timer%2==0){
			g.setColor(Color.red);
			g.drawString(""+score, x+ample/2, y+alt/2);
		}
		
		
	}
	int setScore(){
		Random random = new Random();
		if(random.nextFloat()<0.25) {
			score = 50;
			return 50;
		}
		else if(random.nextInt()<0.5){
			score = 100;
			return 100;
		} 
		else if(random.nextInt()<0.75){
			score = 200;
			return 200;
		} 
		else {
			score=300;
			return 300;
			
		}
	}

}
