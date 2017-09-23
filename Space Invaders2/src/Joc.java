import java.awt.*;
import java.io.*;
import java.util.*;
import javax.sound.sampled.*;

public class Joc extends Thread{
	
	Finestra f;
	Graphics g;
	
	CotxeConductor c1;
	Barrier b1; Barrier b2; Barrier b3; Barrier b4;
	Cotxe c3;
	SpecialInvader specialInvader;
	LargeInvader[] largeInvaders = new LargeInvader[22]; //bot
	NormalInvader[] normalInvaders = new NormalInvader[22]; //middle
	SmallInvader[] smallInvaders = new SmallInvader[22]; //top
	Linea lineaTop, lineaBot;
	
	Bullet bullet;
	Bullet specialInvaderBullet;  // just for the developer enjoyment
	Bullet siBullet;
	ArrayList<Bullet> specialInvaderBullets = new ArrayList<Bullet>(); // just for the developer enjoyment
	Bullet invaderBullet;
	ArrayList<Bullet> invaderBullets = new ArrayList<Bullet>();
	
	boolean menuDisplay = true;
	boolean gameStarted =false;
	boolean gameOver  =false;
	boolean gamePaused = false;
	boolean topHighScoreMenu = false;
	boolean instructionsMenu = false;
	boolean optionsMenu = false;
	boolean gameCredits = false;
	boolean soundOn = true;
	boolean estirat=true;
	boolean pause;
	
	int score, numVides,scoreSubstractedBonus,voltesSI,timeWaitedInPause,numberOfRounds=0, menuSelection=0, maxScore = 0;
	final int bonusScore=1500, alturaGameOver = 790*6/9;
	
	//Image image, backgroundImage, menuBackgroundImage, uabImage, creditsImage, monsterImage;
	
	Font font = null;
	
	Random random = new Random();
	
	Joc(Finestra f){
		this.f = f;
		g=f.g;
	}
	
	public void run() {
		//g.setColor(Color.black); g.fillRect(0,0,Finestra.AMPLE,Finestra.ALT); // black background
		getImages();
		
		f.setLayout( new FlowLayout ());		
		f.setVisible(true);
		f.repaint();

		while(true){
	
			if(gameStarted==false) { // si el joc no ha començat
				
				if(gameCredits == true){
					dibuixaCredits();
					while(gameCredits==true){} // do nothing, just wait here
				}
				else if(topHighScoreMenu==true){
					dibuixatopHighScoreMenu();
				}
				else if(instructionsMenu==true){
					dibuixaInstructionsMenu();
				}
				else if(optionsMenu==true){
					dibuixaOptionsMenu();
				}
				else{ // if menuDisplay 
					dibuixaMenu();// dibuixa el menu principal
				}
			}
			
			else if(gameOver == false){ // si el joc ha començat i no s'ha acabat
				inicialitzacio();
				int windDirection=0, specialInvaderWindDirection=0;
				boolean inBorder = false;
				
				int maxTempsEspera = 67, minTempsEspera = 1;
				int tempsEsperat = 0;
				int iterat=-1;
				
				int maxTimePause = 100; // temps pausa explosió c1
				
				Cotxe invaderDreta;
				Cotxe invaderEsquerra;
				
				while(c1.viu) {
					
					int i;
					if(pause == false){

						if(maxTempsEspera == tempsEsperat ){
							tempsEsperat = 0;
							c1.inDestruction=false;
							for(int j=0; j<22;j++){
								normalInvaders[j].explotat = false;
								smallInvaders[j].explotat = false;
								largeInvaders[j].explotat = false;
							}
							playSound("invadermove");
							iterat+=1;
							if(iterat % 2 == 0){estirat = false;}
							else{estirat = true;}
							
							
							if(!allInvadersDead()){// si hi ha invaders vius 
								
								maxTempsEspera = determinaTempsEspera(numInvadersAlive());	
								invaderEsquerra = smallInvaders[0];//java asks to initalize variable (no really need)
								for(i=0; i<11; i++){
									if(smallInvaders[i].viu){
										invaderEsquerra = smallInvaders[i];
										break;
										}
									else if(smallInvaders[i+11].viu){
										invaderEsquerra = smallInvaders[i+11];
										break;
									}
								}
								
								if(invaderEsquerra.x <=0){
											windDirection = 0;
											inBorder=true;
											if(maxTempsEspera>minTempsEspera){
												maxTempsEspera*=0.75;
											}
											
											}
								if(specialInvader.x <=0){
									specialInvaderWindDirection = 0;
									voltesSI+=1;
									if(voltesSI%2==0){
										specialInvader.viu=true;
										specialInvader.inDestruction=false;
										playSound("ufo");
									}
									}
								else if(specialInvader.x+specialInvader.ample>=Finestra.AMPLE){
									specialInvaderWindDirection = 1;
								}
								if(voltesSI%2==0){
									specialInvader.visibilitat=true;
								}
								else {
									specialInvader.visibilitat=false;
								}
								
								
								
								
								invaderDreta = smallInvaders[0];// java asks to initialize variable (not really needed)
								for(i=10; i>=0; i--){
									if(smallInvaders[i].viu){
										invaderDreta = smallInvaders[i];
										break;
										}
									else if(smallInvaders[i+11].viu){
										invaderDreta = smallInvaders[i+11];
										break;
									}
								}
								
								
								if(invaderDreta.x + invaderDreta.ample + 20 >= Finestra.AMPLE ){
											windDirection = 1;
											inBorder=true;
											if(maxTempsEspera>minTempsEspera){
												maxTempsEspera*=0.75;
											}
											}
								
								
								if(random.nextDouble()<0.125){// amb probabilitat 12.5% disparen quan es mouen
									 invaderBullet = new Bullet(elInvaderQueDispara().x+elInvaderQueDispara().ample/2,elInvaderQueDispara().y - elInvaderQueDispara().alt/2,pixel.size,4*pixel.size,pixel.size,true /*=viu*/,false/*=inDesctruction*/);

									 playSound("ufo_shoot");
									 invaderBullets.add(invaderBullet);
									 System.out.print("random number"+random.nextDouble()+"\n");
								}
								else{
									cleanAllInvaderBulletsIfDead();
								}
								
							checkIfInvadersInvaded();
								
								
							}
							
							else{
								numberOfRounds++;
								inicialitzaInvaders(numberOfRounds);
								System.out.println("El nombre de rondes és " + numberOfRounds);
							}
							
							
							
							
						}
						else {
							tempsEsperat+=1;
						}
						
						calculaMoviments(windDirection,specialInvaderWindDirection,maxTempsEspera,tempsEsperat,inBorder);
						
						inBorder = false;
						
						colisionsLineas();
						colisionsSpecialInvader();
						colisionsInvaders();
						colisionsBlocs();
				
					}
					else{ // pause == true // c1 està mort
						cleanAllInvaderBullets();
						if(timeWaitedInPause==maxTimePause){
							pause = false;
							numVides -=1;
							
							if(numVides <=0){
								c1.viu=false;
								gameOver = true;
							}
						}
						else{
							timeWaitedInPause +=1;
							System.out.println("time Waited in Destruction pause = " + timeWaitedInPause+"\n");
							if(timeWaitedInPause==1 && c1.inDestruction==true){
								playSound("explosion");
							}
						}
						
					}
					
					
					
					dibuixa(estirat,timeWaitedInPause,score,gameOver);
					
					f.repaint();	
					try {
						sleep(30);//perdem 50ms
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					
				}
			}
			else{
				dibuixa(estirat,timeWaitedInPause,score,gameOver);
				f.repaint();
				if(score>maxScore) maxScore = score;
				while(gameOver==true){} //stuck in here, press enter to leave
			}
		}
		
	}
	
	void inicialitzacio(){
		pause = false;
		estirat=true;
		timeWaitedInPause=0;
		timeWaitedInPause = 0;
		numberOfRounds = 0;
		score = 0;
		numVides=3;//3
		voltesSI=-1;
		c1 = new CotxeConductor(0,Finestra.ALT*8/9-14*pixel.size,50,15,pixel.size,true,false);
		b1 = new Barrier((Finestra.AMPLE-6*4*4*pixel.size)/5,Finestra.ALT*11/16);
		b2 = new Barrier((Finestra.AMPLE-6*4*4*pixel.size)*2/5+6*4*pixel.size,Finestra.ALT*11/16);
		b3 = new Barrier((Finestra.AMPLE-6*4*4*pixel.size)*3/5+2*6*4*pixel.size,Finestra.ALT*11/16);
		b4 = new Barrier((Finestra.AMPLE-6*4*4*pixel.size)*4/5+3*6*4*pixel.size,Finestra.ALT*11/16);
		
		lineaTop = new Linea(Finestra.ALT*3/18);
		lineaBot = new Linea(Finestra.ALT*8/9);
		
		bullet = new Bullet(-100,-100,1,4,pixel.size,false,false);
		specialInvaderBullet = new Bullet(-100,-100,1,4,pixel.size,false,false);
		
		specialInvader = new SpecialInvader(400,Finestra.ALT*7/36,50,15,pixel.size,true,false); 
		inicialitzaInvaders(0);
	}
	
	void inicialitzaInvaders(int nRounds){
		int i;
		int altura = 8*pixel.size*(nRounds%11); // nRounds mod 10

		int marge = 5*pixel.size;
		
		largeInvaders[5] = new LargeInvader(Finestra.AMPLE/2,260+altura,20,20,pixel.size,true,false,false,alturaGameOver);
		normalInvaders[5] = new NormalInvader(Finestra.AMPLE/2,340+altura,20,20,pixel.size,true,false,false,alturaGameOver);
		smallInvaders[5] = new SmallInvader(Finestra.AMPLE/2,220+altura,20,20,pixel.size,true,false,false,alturaGameOver);
		
		largeInvaders[5+11] = new LargeInvader(Finestra.AMPLE/2,300+altura,20,20,pixel.size,true,false,false,alturaGameOver);
		normalInvaders[5+11] = new NormalInvader(Finestra.AMPLE/2,380+altura,20,20,pixel.size,true,false,false,alturaGameOver);
		smallInvaders[5+11] = new SmallInvader(Finestra.AMPLE/2,180+altura,20,20,pixel.size,true,false,false,alturaGameOver);
		
		
		for(i=1; i<=5; i++){
			largeInvaders[5+i] = new LargeInvader(Finestra.AMPLE/2+i*largeInvaders[5].ample+i*marge,260+altura,20,20,pixel.size,true,false,false,alturaGameOver);
			largeInvaders[5-i] = new LargeInvader(Finestra.AMPLE/2-i*largeInvaders[5].ample-i*marge,260+altura,20,20,pixel.size,true,false,false,alturaGameOver);
			
			largeInvaders[11+5+i] = new LargeInvader(Finestra.AMPLE/2+i*largeInvaders[5].ample+i*marge,300+altura,20,20,pixel.size,true,false,false,alturaGameOver);
			largeInvaders[11+5-i] = new LargeInvader(Finestra.AMPLE/2-i*largeInvaders[5].ample-i*marge,300+altura,20,20,pixel.size,true,false,false,alturaGameOver);
			
			normalInvaders[5+i] = new NormalInvader(Finestra.AMPLE/2+i*largeInvaders[5].ample+i*marge ,340+altura,20,20,pixel.size,true,false,false,alturaGameOver);
			normalInvaders[5+i+11] = new NormalInvader(Finestra.AMPLE/2+i*largeInvaders[5].ample+i*marge,380+altura,20,20,pixel.size,true,false,false,alturaGameOver);
			normalInvaders[5-i] = new NormalInvader(Finestra.AMPLE/2-i*largeInvaders[5].ample-i*marge,340+altura,20,20,pixel.size,true,false,false,alturaGameOver);
			normalInvaders[5-i+11] = new NormalInvader(Finestra.AMPLE/2-i*largeInvaders[5].ample-i*marge,380+altura,20,20,pixel.size,true,false,false,alturaGameOver);
			
			
			smallInvaders[5+i] = new SmallInvader(Finestra.AMPLE/2+i*largeInvaders[5].ample+i*marge,220+altura,20,20,pixel.size,true,false,false,alturaGameOver);
			smallInvaders[5+i+11] = new SmallInvader(Finestra.AMPLE/2+i*largeInvaders[5].ample+i*marge,180+altura,20,20,pixel.size,true,false,false,alturaGameOver);
			smallInvaders[5-i] = new SmallInvader(Finestra.AMPLE/2-i*largeInvaders[5].ample-i*marge,220+altura,20,20,pixel.size,true,false,false,alturaGameOver);
			smallInvaders[11+5-i] = new SmallInvader(Finestra.AMPLE/2-i*largeInvaders[5].ample-i*marge,180+altura,20,20,pixel.size,true,false,false,alturaGameOver);
		
		}
	}
	
	void calculaMoviments(int windDirection,int specialInvaderWindDriection, int maxTempsEspera, int tempsEsperat, boolean inBorder){
		//c1.moviment();
		
		if(f.movimentDreta==true && c1.x+20+c1.ample<Finestra.AMPLE){
			c1.x+=12;
			//j.c1.x+=15;
		}
		if(f.movimentEsquerra==true && c1.x-15>0){
			c1.x-=12;
		}
		if(inBorder){
			for(int i=0; i<22;i++){
				smallInvaders[i].y +=( smallInvaders[i].alt  ); 
				normalInvaders[i].y +=( normalInvaders[i].alt  ); 
				largeInvaders[i].y += ( largeInvaders[i].alt  );
			}
		}
		
		if(maxTempsEspera==tempsEsperat){
			int i;
			for(i=0; i<22; i++){
				smallInvaders[i].moviment(windDirection);
				normalInvaders[i].moviment(windDirection);
				largeInvaders[i].moviment(windDirection);
			}
		}
		
		if(specialInvader.inDestruction==false){
			specialInvader.moviment(specialInvaderWindDriection);
		}
		
		if(bullet.viu){
			bullet.moviment(1);
		}
		else{
			bullet.x=-100;
			bullet.y=-100;
		}
		if(bullet.y<10){
			bullet.viu=false;
		}
		
		if(specialInvaderBullet.viu){
			specialInvaderBullet.moviment(-1);
		}
		else{
			specialInvaderBullet.x=-100;
			specialInvaderBullet.y=-100;
		}
		if(specialInvaderBullet.y>Finestra.ALT-100){
			specialInvaderBullet.viu=false;
		}
		
		for(int i=0; i<specialInvaderBullets.size(); i++){
			if(specialInvaderBullets.get(i).viu){
				specialInvaderBullets.get(i).moviment(-1);
			}
			else{
				specialInvaderBullets.get(i).x=-100;
				specialInvaderBullets.get(i).y=-100;
			}
			if(specialInvaderBullets.get(i).y>Finestra.ALT-100){
				specialInvaderBullets.get(i).viu=false;
			}
		}
		
		for(int i=0; i<invaderBullets.size(); i++){
			if(invaderBullets.get(i).viu){
				invaderBullets.get(i).moviment(-1);
			}
			else{
				invaderBullets.get(i).x=-100;
				invaderBullets.get(i).y=-100;
			}
			if(invaderBullets.get(i).y>Finestra.ALT-10){
				invaderBullets.get(i).viu=false;
				//invaderBullets.remove(i);
			}
		}
		
		
	}
	
	
	
	void dibuixa( boolean estirat,int timeWaitedInPause, int score, boolean gameOver){
		g.setColor(Color.black);
		g.fillRect(0,0,Finestra.AMPLE,Finestra.ALT);
		//g.drawImage(backgroundImage,0,0,null);
		g.setColor(Color.red);
		lineaTop.dibuixa(g);
		g.setColor(Color.cyan);
		lineaBot.dibuixa(g);
		//g.fillRect(0,Finestra.ALT*8/9,Finestra.AMPLE,pixel.size);
//		g.setColor(Color.cyan);
	//	g.fillRect(0,Finestra.ALT*6/9,Finestra.AMPLE,pixel.size);
		
		  
 		
	
		
		g.setColor(Color.white);
		
		font = font.deriveFont(Font.BOLD, 20f);
		g.setFont(font);
		g.drawString("SCORE", 2*20, Finestra.ALT/8);
		g.drawString("ROUND", Finestra.AMPLE/2 - 3*20, Finestra.ALT/8);
		g.drawString("LIVES",Finestra.AMPLE-20*(2+5),Finestra.ALT/8);
		
		g.setColor(Color.cyan);
		g.drawString(""+score, 20*(2+5), Finestra.ALT/8);
		g.drawString(""+numberOfRounds, Finestra.AMPLE/2+2*20, Finestra.ALT/8);
		g.drawString(""+numVides, Finestra.AMPLE-20*(2), Finestra.ALT/8);
		
		b1.dibuixa(g);
		b2.dibuixa(g);
		b3.dibuixa(g);
		b4.dibuixa(g);
		
		
		if(c1.inDestruction==false){
			c1.dibuixa(g);
		}
		else{
			c1.dibuixaExplosio(g, timeWaitedInPause);
		}
		
		
		if(gameOver){
			dibuixaGameOver();
		}
	
	
		if(gameOver==false){
			
			//g.drawLine(x1, y1, x2, y2);
			
			if(specialInvader.visibilitat && specialInvader.viu){
				if(!specialInvader.inDestruction){
					specialInvader.dibuixa(g);
				}
				else{
					font = font.deriveFont(Font.BOLD, specialInvader.alt);
					g.setFont(font);
					specialInvader.dibuixaExplosio(g);
				}
				
			}
			
		
			int i;
			for(i=0; i<22; i++){
				
				if(normalInvaders[i].viu){
					normalInvaders[i].dibuixa(g,estirat);
				}
				else{
					if(normalInvaders[i].explotat){
						normalInvaders[i].dibiuxaExplosio(g, estirat);
					}
				}
			
				if(smallInvaders[i].viu){
					smallInvaders[i].dibuixa(g,estirat);
				}
				else{
					if(smallInvaders[i].explotat){
						smallInvaders[i].dibiuxaExplosio(g, estirat);
					}
				}
				
				if(largeInvaders[i].viu){
					largeInvaders[i].dibuixa(g,estirat);
				}
				else{
					if(largeInvaders[i].explotat){
						largeInvaders[i].dibiuxaExplosio(g, estirat);
					}
				}
				
				//smallInvaders[i].dibuixa(g,numVoltes,estirat);
				//largeInvaders[i].dibuixa(g,numVoltes,estirat);
			}
			
			
			
			if(bullet.viu){	
				bullet.dibuixa(g);
				}
			if(specialInvaderBullet.viu){
				specialInvaderBullet.dibuixa(g);
			}
			for(i=0; i<specialInvaderBullets.size(); i++){
				if(specialInvaderBullets.get(i).viu){
					specialInvaderBullets.get(i).dibuixa(g);
				}
				
			}
			
			for(i=0; i<invaderBullets.size(); i++){
				if(invaderBullets.get(i).viu){
					invaderBullets.get(i).dibuixa(g);
				}
				
			}
			
			
		}
		
		
		
	}
	boolean colisio(Box c1, Box c2){
		Rectangle r1 = new Rectangle(c1.x,c1.y,c1.ample,c1.alt);
		Rectangle r2 = new Rectangle(c2.x,c2.y,c2.ample,c2.alt);
		return r1.intersects(r2);
	}
	
	void colisionsInvaders(){
		for(int i=0; i<22; i++){
			if( colisio(normalInvaders[i],bullet) && normalInvaders[i].viu){
				System.out.println("alien " + i + " coalisió\n");
				bullet.viu=false;
				normalInvaders[i].viu = false;
				normalInvaders[i].explotat = true;
				//tempsEsperat = 0;
				score += 20;
				scoreSubstractedBonus +=20;
				playSound("invaderkilled");
		}
			
			if( colisio(smallInvaders[i],bullet) && smallInvaders[i].viu){
				System.out.println("alien " + i + " coalisió\n");
				bullet.viu=false;
				smallInvaders[i].viu = false;
				smallInvaders[i].explotat = true;
				//tempsEsperat = 0;
				score += 40;
				scoreSubstractedBonus +=40;
				playSound("invaderkilled");
				
		}
			if( colisio(largeInvaders[i],bullet) && largeInvaders[i].viu){
				System.out.println("alien " + i + " coalisió\n");
				bullet.viu=false;
				largeInvaders[i].viu = false;
				largeInvaders[i].explotat = true;
				//tempsEsperat = 0;
				score += 10;
				scoreSubstractedBonus +=10;
				playSound("invaderkilled");
		}
			if(scoreSubstractedBonus>bonusScore){
				numVides+=1;
				scoreSubstractedBonus-=bonusScore;
			}

			if(gamePaused==true){
				dibuixaGamePaused();
				System.out.println("Entrem a pause\n\n");
			}
			
			if(colisio(specialInvaderBullet,c1)){
				c1.inDestruction=true;
				//timeWaitedInPause=0;
				pause = true;
				specialInvaderBullet.x=-100;
				playSound("explosion");
			}
			
			for(int k=0; k<specialInvaderBullets.size(); k++ ){
				if(colisio(c1,specialInvaderBullets.get(k))){
					c1.inDestruction=true;
					timeWaitedInPause=0;
					pause = true;
					//specialInvaderBullet.x=-100;
					//playSound("explosion");
					specialInvaderBullets.get(k).viu=false;
					break;
				}
			}
			
			for(int k=0; k<invaderBullets.size(); k++ ){
				if(colisio(c1,invaderBullets.get(k))){
					c1.inDestruction=true;
					timeWaitedInPause=0;
					pause = true;
					//specialInvaderBullet.x=-100;
					//playSound("explosion");
					invaderBullets.get(k).viu=false;
					break;
				}
			}
			
		}
	}
	
	void colisionsLineas(){
		int i;
		for(i=0; i<lineaTop.nDots; i++){
			if(colisio(bullet,lineaTop.dots[i])) {
				if(lineaTop.dots[i].viu){
					bullet.viu=false;
				}
				lineaTop.dots[i].viu=false;
				
			}
		}
		
		for(int k=0; k<invaderBullets.size(); k++ ){
			for(i=0; i<lineaBot.nDots; i++){
				if(colisio(lineaBot.dots[i],invaderBullets.get(k))){
					invaderBullets.get(k).viu=false;
					lineaBot.dots[i].viu=false;
				}
			}
			
		}
		
	}
	
	void colisionsSpecialInvader(){
		if(colisio(specialInvader,bullet)){
			//specialInvader.viu=false;
			int tmpScore = specialInvader.setScore();
			score += tmpScore;
			scoreSubstractedBonus +=tmpScore;
			
			specialInvader.inDestruction=true;
			specialInvader.timer=0;
			playSound("ufo");
		}
		if(specialInvader.inDestruction==true){
			specialInvader.timer+=1;
			System.out.println(specialInvader.timer+"\n");
			if(specialInvader.timer>=50){
				specialInvader.viu=false;
				specialInvader.inDestruction=false;
			}
			
		}
	}
	
	void colisionsBlocs(){
		for(int i=0; i<10; i++){
			if(colisio(b1.blocs[i],bullet) && b1.blocs[i].hitsTaken<4){
				System.out.println("colisió bloc"+i+"\n");
				bullet.viu=false;
				b1.blocs[i].hitsTaken+=1;
			}
			if(colisio(b2.blocs[i],bullet) && b2.blocs[i].hitsTaken<4){
				System.out.println("colisió bloc"+i+"\n");
				bullet.viu=false;
				b2.blocs[i].hitsTaken+=1;
			}
			if(colisio(b3.blocs[i],bullet) && b3.blocs[i].hitsTaken<4){
				System.out.println("colisió bloc"+i+"\n");
				bullet.viu=false;
				b3.blocs[i].hitsTaken+=1;
			}
			if(colisio(b4.blocs[i],bullet) && b4.blocs[i].hitsTaken<4){
				System.out.println("colisió bloc 0\n");
				bullet.viu=false;
				b4.blocs[i].hitsTaken+=1;
			}
			for(int k=0; k<invaderBullets.size(); k++ ){
				if(colisio(b1.blocs[i],invaderBullets.get(k))&& invaderBullets.get(k).viu && b1.blocs[i].hitsTaken<4){
					b1.blocs[i].hitsTaken+=1;
					invaderBullets.get(k).viu=false;
				}
				if(colisio(b2.blocs[i],invaderBullets.get(k))&& invaderBullets.get(k).viu && b2.blocs[i].hitsTaken<4){
					b2.blocs[i].hitsTaken+=1;
					invaderBullets.get(k).viu=false;
				}
				if(colisio(b3.blocs[i],invaderBullets.get(k))&& invaderBullets.get(k).viu && b3.blocs[i].hitsTaken<4){
					b3.blocs[i].hitsTaken+=1;
					invaderBullets.get(k).viu=false;
				}
				if(colisio(b4.blocs[i],invaderBullets.get(k))&& invaderBullets.get(k).viu && b4.blocs[i].hitsTaken<4){
					b4.blocs[i].hitsTaken+=1;
					invaderBullets.get(k).viu=false;
				}
			}
		}
	}
	
	void playSound(String nameSoundFile) {
		
	}
	
	Cotxe elInvaderQueDispara(){
		ArrayList<Cotxe> invadersVius = new ArrayList<Cotxe>();
		for(int i=0; i<22; i++){
			if(smallInvaders[i].viu){
				invadersVius.add(smallInvaders[i]);
			}
			if(normalInvaders[i].viu){
				invadersVius.add(normalInvaders[i]);
			}
			if(largeInvaders[i].viu){
				invadersVius.add(largeInvaders[i]);
			}
		}
		
		
		Random random = new Random();
		
		

		return invadersVius.get(random.nextInt(invadersVius.size())); 
	}
	
	boolean allInvadersDead(){
		for(int i=0; i<22; i++){
			if(smallInvaders[i].viu 
			|| normalInvaders[i].viu
			|| largeInvaders[i].viu){
				return false;
			}
		}
		return true;
	}
	
	int numInvadersAlive(){
		int invadersAlive = 0;
		for(int i=0; i<22; i++){
			if(smallInvaders[i].viu){
				invadersAlive ++;
			}
			if(normalInvaders[i].viu){
				invadersAlive++;
			}
			if(largeInvaders[i].viu){
				invadersAlive++;
			}
		}
		return invadersAlive;
	}
	
	int determinaTempsEspera(int n){
		if(n==1) return 0;
		else if(n<5) return 2;
		else if(n<10) return 3;
		else if (n<15) return 5;
		else if (n<20) return 7;
		else if (n<25) return 10;
		else if (n<35) return 14;
		else if (n<46) return 16;
		else if (n<56) return 18;
		return 20;
		
	}
	
	void cleanAllInvaderBullets(){
		for(int i=0; i<this.invaderBullets.size(); i++){
		//	this.invaderBullets.get(i).viu =false;
			this.invaderBullets.removeAll(invaderBullets);
		}
	}
	void cleanAllInvaderBulletsIfDead(){
		boolean allInvaderBulletsAreDead = true;
		for(int i=0; i<this.invaderBullets.size(); i++){
			if(this.invaderBullets.get(i).viu) allInvaderBulletsAreDead = false;
		}
		if(allInvaderBulletsAreDead) 
			{cleanAllInvaderBullets();
			System.out.println("Im cleaning bullets");
			}
	}
	
	void checkIfInvadersInvaded(){
		int alturaGameOver = 340 + 8*pixel.size*13;
		for(int i=0; i<22; i++){
			if(normalInvaders[i].viu && normalInvaders[i].y + normalInvaders[i].alt>=alturaGameOver
			|| smallInvaders[i].viu && smallInvaders[i].y + smallInvaders[i].alt>=alturaGameOver
			|| largeInvaders[i].viu && largeInvaders[i].y + largeInvaders[i].alt>=alturaGameOver){
				gameOver=true;  c1.viu=false; System.out.println("Massa baix!!");}	
		}
	}
	
	void getImages(){
		//http://www.picresize.com/
//		this.image = ResourceLoader.getImage("title.png");
//		this.backgroundImage = ResourceLoader.getImage("background.png");
//		this.menuBackgroundImage = ResourceLoader.getImage("creditsBackground.png");
//		this.uabImage = ResourceLoader.getImage("uab.png");
//		this.creditsImage = ResourceLoader.getImage("background.png");
//		this.monsterImage = ResourceLoader.getImage("creditsbackground.png");
		
//		Image image = new ImageIcon(this.getClass().getResource("img/title.png")).getImage();
//		Image backgroundImage = new ImageIcon(this.getClass().getResource("img/background.png")).getImage();
//		Image menuBackgroundImage = new ImageIcon(this.getClass().getResource("img/creditsBackground.png")).getImage();
//		Image uabImage = new ImageIcon(this.getClass().getResource("img/uab.png")).getImage();
//		Image creditsImage = new ImageIcon(this.getClass().getResource("img/background.png")).getImage();
//		Image monsterImage = new ImageIcon(this.getClass().getResource("img/creditsBackground.png")).getImage();
	}
	
	void dibuixaGamePaused(){
		while(gamePaused==true){
			g.setColor(Color.white);
			font = font.deriveFont(Font.BOLD, 20f);
			g.setFont(font);
			g.drawString("GAME  PAUSED", Finestra.AMPLE/2-9*10, 67*Finestra.ALT/72);
			font = font.deriveFont(Font.BOLD, 10f);
			g.setFont(font);
			g.drawString("Press 'P' to unpause", Finestra.AMPLE/2-8*10, 70*Finestra.ALT/72);
			f.repaint();
			System.out.println("im in a loop\n");
			
		}
		
	}
	void dibuixaGameOver(){
		g.setColor(Color.white);
		font = font.deriveFont(Font.BOLD, 20f);
		g.setFont(font);
		g.drawString("GAME OVER", Finestra.AMPLE/2-9*10, 67*Finestra.ALT/72);
		font = font.deriveFont(Font.BOLD, 10f);
		g.setFont(font);
		g.drawString("Press 'ENTER' to continue", Finestra.AMPLE/2-12*10, 70*Finestra.ALT/72);
		f.repaint();
		System.out.println("im in a loop\n");
	}
	
	void dibuixaCredits(){

		
		//g.drawImage(sideImage,0,0,null);
		
		font = font.deriveFont(Font.BOLD, 30f);
		g.setFont(font);
//		g.drawString("Credits", Finestra.AMPLE/2-9*10, Finestra.ALT/2);
		font = font.deriveFont(Font.BOLD, 10f);
		g.setFont(font);
//		f.repaint();	
//		try {
//			sleep(1000);//perdem 50ms
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		
		f.repaint();
		for(int i=0; i<Finestra.ALT+100+29*15+110; i++){
			try {
				sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int alturaChange = Finestra.ALT-i;
			
			g.setColor(Color.black);
			g.fillRect(0,0,Finestra.AMPLE,Finestra.ALT);
			g.setColor(Color.white);
			//g.drawImage(creditsImage,0,0,null);
			g.setColor(Color.black);
			g.fillRect(0,0,Finestra.AMPLE,Finestra.ALT);
			font = font.deriveFont(Font.BOLD, 15f); g.setFont(font);
			g.drawString("This game has been developed by:", Finestra.AMPLE/2-11*15, 90+alturaChange);
			font = font.deriveFont(Font.BOLD, 20f); g.setFont(font);
			g.setColor(Color.orange);
			g.drawString("XAVIER LOPEZ", Finestra.AMPLE/2-4*20, 100+2*15+alturaChange);
			g.setColor(Color.white);
			font = font.deriveFont(Font.BOLD, 12f); g.setFont(font);
			g.drawString("As an assigment from the Advanced Programming Course", Finestra.AMPLE/2-20*12, 100+5*15+alturaChange);
			//font = font.deriveFont(Font.BOLD, 15f); g.setFont(font);
			g.drawString("Mentored by:", Finestra.AMPLE/2-5*12, 100+7*15+alturaChange);
			font = font.deriveFont(Font.BOLD, 17f); g.setFont(font);
			g.setColor(Color.orange);
			g.drawString("Vicente Soler", Finestra.AMPLE/2-6*15, 100+9*15+alturaChange);
			g.setColor(Color.white);
			font = font.deriveFont(Font.BOLD, 12f); g.setFont(font);
			g.drawString("During the first semester of 2016/2017 in:", Finestra.AMPLE/2-12*15, 100+12*15+alturaChange);
			font = font.deriveFont(Font.BOLD, 15f); g.setFont(font); g.setColor(Color.orange);
			g.drawString("UAB Degree on Mathematics", Finestra.AMPLE/2-12*12, 100+14*15+alturaChange);
			g.setColor(Color.white);
			font = font.deriveFont(Font.BOLD, 12f); g.setFont(font); 
			g.drawString("Inspired on the original arcade game:" , Finestra.AMPLE/2-12*14, 100+17*15+alturaChange);
			g.setColor(Color.orange); font = font.deriveFont(Font.BOLD, 15f); g.setFont(font); 
			g.drawString("SPACE INVADERS" , Finestra.AMPLE/2-15*5, 100+19*15+alturaChange); g.setColor(Color.white);
			font = font.deriveFont(Font.BOLD, 12f); g.setFont(font); 
			g.drawString("Created by:" , Finestra.AMPLE/2-12*5, 100+22*15+alturaChange);
			font = font.deriveFont(Font.BOLD, 15f); g.setFont(font);  g.setColor(Color.orange);
			g.drawString("Toshihiro Nishikado" , Finestra.AMPLE/2-10*10, 100+24*15+alturaChange);
			g.setColor(Color.white);
			font = font.deriveFont(Font.BOLD, 12f); g.setFont(font); 
			g.drawString("and originaly released to the market in 1978 by " , Finestra.AMPLE/2-12*18, 100+27*15+alturaChange);g.setColor(Color.orange);
			font = font.deriveFont(Font.BOLD, 15f); g.setFont(font); 
			g.drawString("Taito Corporation " , Finestra.AMPLE/2-12*8, 100+29*15+alturaChange);
			
			//g.drawImage(uabImage,Finestra.AMPLE/2-12*8, 100+32*15+alturaChange,null);
			
			if(gameCredits == false ){break;}
			f.repaint();
		}
		menuDisplay = true;
		gameCredits = false;
		System.out.println("im in a loop\n");
	}
	
void dibuixatopHighScoreMenu(){
		g.setColor(Color.black);
		g.fillRect(0,0,Finestra.AMPLE,Finestra.ALT);
		//g.drawImage(monsterImage,0,200,null);
		
		font = font.deriveFont(Font.BOLD, 30f); g.setFont(font);
		g.setColor(Color.white);
		g.drawString("YOUR HIGHEST SOCRE IS:", 170, Finestra.ALT/8);
		g.drawString(""+maxScore, Finestra.AMPLE/2-50, Finestra.ALT/8+50);
		f.repaint();
		while(true){
			// do nothing
			if(topHighScoreMenu == false ){break;}
		}
		menuDisplay = true;
	}
	
void dibuixaOptionsMenu(){

	
	g.setColor(Color.black);
	g.fillRect(0,0,Finestra.AMPLE,Finestra.ALT);
	//g.drawImage(monsterImage,0,200,null);
	
	font = font.deriveFont(Font.BOLD, 30f); g.setFont(font);
	g.setColor(Color.white);
	g.drawString("OPTIONS", Finestra.AMPLE/2-50, Finestra.ALT/8);
	g.drawString("999999", Finestra.AMPLE/2-50, Finestra.ALT/8+50);
	f.repaint();
	while(true){
		// do nothing
		if(optionsMenu == false ){break;}
	}
	menuDisplay = true;
	optionsMenu = false;
}

void dibuixaInstructionsMenu(){

	
	g.setColor(Color.black);
	g.fillRect(0,0,Finestra.AMPLE,Finestra.ALT);
	//g.drawImage(backgroundImage,0,100,null);
	//g.drawImage(instructionsImage,175,50,null);
	font = font.deriveFont(Font.BOLD, 40f); g.setFont(font);
	g.setColor(Color.white);
	g.drawString("INSTRUCTIONS", 200, Finestra.ALT/8);
	g.setColor(Color.green);
	font = font.deriveFont(Font.BOLD, 15f); g.setFont(font);
	g.drawString("- To move 'laser base' left or right operate the arrows.", 50, 200);
	g.drawString("- To fire laser, press the space bar.", 50, 200+50);
	g.drawString("- Ufo hit gives player mistery score.", 50, 200+50*2);
	g.drawString("- Extended play for 1500 points.", 50, 200+50*3);
	g.drawString("- Game over when player's laser base is hit 3 times by invader missiles", 50, 200+50*4);
	g.drawString("or when invaders overrun the base.", 50, 200+50*4+25);
	g.drawString("-In game, press 'P' to pause the game and 'M' to mute/unmute sound.", 50, 200+50*5+25);
	g.drawString("-In game, press 'ESC' to surrender", 50, 200+50*6+25);
	g.setColor(Color.white);
	g.drawString("Press Enter to return to the main menu", 200, 700);
	f.repaint();
	while(true){
		// do nothing
		if(instructionsMenu == false ){break;}
	}
	menuDisplay = true;
	instructionsMenu = false;
}

	void dibuixaMenu(){
		g.setColor(Color.black);
		g.fillRect(0,0,Finestra.AMPLE,Finestra.ALT);
		//g.drawImage(menuBackgroundImage,0,100,null);
//		try {
//			font = Font.createFont(Font.TRUETYPE_FONT,new FileInputStream("res/font/space_invaders.ttf"));
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (FontFormatException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
		//InputStream is = getClass().getResourceAsStream("font/space_invaders.ttf");
//		try {
			//font = Font.createFont(Font.TRUETYPE_FONT, is);
			
			int style = Font.BOLD | Font.ITALIC;
			font = new Font ("Garamond", style , 11);
//		}
//		catch (FontFormatException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		font = font.deriveFont(Font.BOLD, 75f);
		 //font = new Font("Jokerman", Font.PLAIN, 35);
		g.setFont(font);
		
		
		g.setColor(Color.black);
		g.fillRect(0,0,Finestra.AMPLE,Finestra.ALT);
		//g.drawImage(image,Finestra.AMPLE/2-250,Finestra.ALT/2-300,null);
		
		g.setColor(Color.WHITE);
		//g.drawString("SPACE", Finestra.AMPLE/3, Finestra.ALT/4);
		//g.drawString("INVADERS", Finestra.AMPLE/4, 3*Finestra.ALT/8);
		
		font = font.deriveFont(Font.BOLD, 15f);
		g.setFont(font);
		
		g.setColor(Color.white);
		
		LargeInvader iniciLargeInvader = new LargeInvader(9*Finestra.AMPLE/20, Finestra.ALT/2-20,20,20,pixel.size,true,false,false,alturaGameOver);
		iniciLargeInvader.dibuixa(g, false);
		NormalInvader iniciNormalInvader = new NormalInvader(9*Finestra.AMPLE/20, Finestra.ALT/2-20+40,20,20,pixel.size,true,false,false,alturaGameOver);
		iniciNormalInvader.dibuixa(g, false);
		SmallInvader iniciSmallInvader = new SmallInvader(9*Finestra.AMPLE/20+2*pixel.size, Finestra.ALT/2-20+40*2,20,20,pixel.size,true,false,false,alturaGameOver);
		iniciSmallInvader.dibuixa(g, false);
		
		SpecialInvader iniciSpecialInvader = new SpecialInvader(9*Finestra.AMPLE/20-3*pixel.size, Finestra.ALT/2-20+40*3,50,15,pixel.size,true,false); 
		iniciSpecialInvader.dibuixa(g);
		g.setColor(Color.WHITE);
		g.drawString("PLAY SPACE INVADERS", 8*Finestra.AMPLE/20, 3*Finestra.ALT/4);
		g.drawString(" = 10PTS", 9*Finestra.AMPLE/20+40, Finestra.ALT/2);
		
		g.drawString(" = 20PTS", 9*Finestra.AMPLE/20+40, Finestra.ALT/2+40);
		g.drawString(" = 40PTS", 9*Finestra.AMPLE/20+40, Finestra.ALT/2+40*2);
		g.drawString(" = ?PTS", 9*Finestra.AMPLE/20+40, Finestra.ALT/2+40*3);
		
		g.drawString("HIGH SCORES", 9*Finestra.AMPLE/20, 3*Finestra.ALT/4+30);
		g.drawString("INSTRUCTIONS", 9*Finestra.AMPLE/20, 3*Finestra.ALT/4+2*30);
		g.drawString("OPTIONS", 9*Finestra.AMPLE/20, 3*Finestra.ALT/4+3*30);
		g.drawString("CREDITS", 9*Finestra.AMPLE/20, 3*Finestra.ALT/4+4*30);
		
		g.setColor(Color.cyan);
		if(menuSelection==0){
			g.drawString("PLAY SPACE INVADERS", 8*Finestra.AMPLE/20, 3*Finestra.ALT/4);
		}
		else if(menuSelection==1){
			g.drawString("HIGH SCORES", 9*Finestra.AMPLE/20, 3*Finestra.ALT/4+30);
		}
		else if(menuSelection==2){
			g.drawString("INSTRUCTIONS", 9*Finestra.AMPLE/20, 3*Finestra.ALT/4+2*30);
		}
		else if(menuSelection==3){
			g.drawString("OPTIONS", 9*Finestra.AMPLE/20, 3*Finestra.ALT/4+3*30);
		}
		else{
			g.drawString("CREDITS", 9*Finestra.AMPLE/20, 3*Finestra.ALT/4+4*30);
		}
		
		f.repaint();
		try {
			sleep(10);//perdem 50ms
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}




//source -> override implement methods -> run
//source ...
//interfaces


//TODO:
//Bullet
//random enemy alien
//fix keylistener
// colision , hide/show