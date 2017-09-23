import java.awt.Graphics;

public class Dot extends Box{
	int pixelSize;
	boolean viu;
	Dot(int x, int y, int pixelSize) {
		super(x, y);
		this.x=x;
		this.y=y;
		this.pixelSize = pixelSize;
		this.viu=true;
		this.ample = pixelSize;
		this.alt=pixelSize;
	}
	void dibuixa(Graphics g){
		if(this.viu){
			//g.setColor(Color.red);
			g.fillRect(this.x, this.y, this.pixelSize, this.pixelSize);
		}
	}

}
