import java.awt.Graphics;

public class Barrier extends Box {

	static final int SFample = 4*Bloc.SFample, SFalt = 3*Bloc.SFalt;
	Bloc[] blocs = new Bloc[20];
	
	Barrier(int x, int y) {
		super(x, y);
		this.ample = 4*Bloc.SFample;
		this.alt = 3*Bloc.SFalt;
		this.x=x; this.y=y;  
		for(int i=0; i<4; i++){
			for(int j=0; j<=1;j++){	
				blocs[i+4*j] = new Bloc(x+i*Bloc.SFample,y+j*Bloc.SFalt,Barrier.setBlocType(i+4*j));
			}
		}
		blocs[8]=new Bloc(x, y+2*Bloc.SFalt,0);
		blocs[9]=new Bloc(x+3*Bloc.SFample, y+2*Bloc.SFalt,0);
	}	

void dibuixa(Graphics g){
	
	for(int i=0; i<10; i++){
		blocs[i].dibuixa(g,blocs[i].type);
	}

}

static int setBlocType(int k){
	if(k==0){
		return 1;
	}
	else if(k==3){
		return 2;
	}
	else if (k==5){
		return 3;
	}
	else if(k==6){
		return 4;
	}
	return 0;
}

}

