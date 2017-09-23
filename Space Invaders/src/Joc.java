import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;

import java.awt.Graphics;

import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;

//botons
//http://stackoverflow.com/questions/3616761/addmouselistener-or-addactionlistener-or-jbutton


public class Joc extends Thread{
	//int AMPLE, ALT;
	int x1=100, y1=100, x2=400, y2=100;
	
	Finestra f;
	Graphics g;
	
	CotxeConductor c1;
	Barrier b1;Barrier b2;Barrier b3;Barrier b4;
	Cotxe c3;
	SpecialInvader specialInvader;
	LargeInvader[] largeInvaders = new LargeInvader[22]; //bot
	NormalInvader[] normalInvaders = new NormalInvader[22]; //middle
	SmallInvader[] smallInvaders = new SmallInvader[22]; //top
	
	Bullet bullet;
	Bullet specialInvaderBullet;
	Bullet siBullet;
	ArrayList<Bullet> specialInvaderBullets = new ArrayList<Bullet>();
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
	
	int menuSelection=0;
	
	
	
	Image image = ResourceLoader.getImage("title.png");
	Image backgroundImage = ResourceLoader.getImage("background.png");
	Image menuBackgroundImage = ResourceLoader.getImage("creditsBackground.png");
	Image uabImage = ResourceLoader.getImage("uab.png");
	Image creditsImage = ResourceLoader.getImage("background.png");
	Image monsterImage = ResourceLoader.getImage("creditsBackground.png");
	
//	Image image = new ImageIcon(this.getClass().getResource("img/title.png")).getImage();
//	Image backgroundImage = new ImageIcon(this.getClass().getResource("img/background.png")).getImage();
//	Image menuBackgroundImage = new ImageIcon(this.getClass().getResource("img/creditsBackground.png")).getImage();
//	Image uabImage = new ImageIcon(this.getClass().getResource("img/uab.png")).getImage();
//	Image creditsImage = new ImageIcon(this.getClass().getResource("img/background.png")).getImage();
//	Image monsterImage = new ImageIcon(this.getClass().getResource("img/creditsBackground.png")).getImage();
//	
	int score;
	final int bonusScore=1500;
	int scoreSubstractedBonus;
	int numVides;
	Font font = null;
	int voltesSI;
	
	Random random = new Random();
	
	Joc(Finestra f){
		//this.AMPLE = ample;
		//this.ALT=alt;
		this.f = f;
		g=f.g;
	}
	int alturaPerillosa = 790*6/9;//f.ALT
	
	
	public void run() {
	
		f.setLayout( new FlowLayout ());		
		f.setVisible(true);
		g.setColor(Color.black);
		g.fillRect(0,0,f.AMPLE,f.ALT);
		f.repaint();
		
		//Image image = new ImageIcon(this.getClass().getResource("/resources/images/front.png")).getImage();
		//http://www.picresize.com/
		//g.drawImage(image,0,0,null);

		while(true){
			boolean estirat=true;
			int timeWaitedInPause = 0;
			
			// Si el joc no ha començat, dibuixa el menu principal
			if(gameStarted==false) {
				if(menuDisplay){
					dibuixaMenu();
				}
				
				else if(gameCredits == true){
					dibuixaCredits();
					while(gameCredits==true){
						// do nothing, just wait here
					}
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
				
			}
			
			// si el joc ha començat i no s'ha acabat
			else if(gameOver == false){
				inicialitzacio();
				int windDirection=0;
				int specialInvaderWindDirection=0;
				boolean inBorder = false;
				
				int maxTempsEspera = 15;
				int minTempsEspera = 1;
				int tempsEsperat = 0;
				int iterat=-1;
				
				boolean pause = false;
				estirat=true;
				timeWaitedInPause=0;
				int maxTimePause = 100;
				
				Cotxe invaderDreta;
				Cotxe invaderEsquerra;
				
				while(c1.viu) {
					
					int i;
					if(pause == false){

						if(maxTempsEspera == tempsEsperat){
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
							
							
							/*Aliens disparen*/
							if(!allInvadersDead()){ 
								
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
								else if(specialInvader.x+specialInvader.ample>=f.AMPLE){
									specialInvaderWindDirection = 1;
								}
								if(voltesSI%2==0){
									specialInvader.visibilitat=true;
								}
								else {
									specialInvader.visibilitat=false;
								}
								
								//smallInvaders[0],smallInvaders[11]
								//smallInvaders[1], smallInvaders[12]    
								
								
								
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
								
								
								if(invaderDreta.x + invaderDreta.ample + 20 >= f.AMPLE ){
											windDirection = 1;
											inBorder=true;
											if(maxTempsEspera>minTempsEspera){
												maxTempsEspera*=0.75;
											}
											}
								
								
								if(random.nextDouble()<0.125){// amb probabilitat 50% disparen quan es mouen
									 invaderBullet = new Bullet(elInvaderQueDispara().x+elInvaderQueDispara().ample/2,elInvaderQueDispara().y - elInvaderQueDispara().alt/2,1,4,2/*=pixel*/,true /*=viu*/,false/*=inDesctruction*/);
									 playSound("ufo_shoot");
									 invaderBullets.add(invaderBullet);
									 System.out.print("random number"+random.nextDouble()+"\n");
								}
							}
							
							else{
								inicialitzaInvadersNewRound();
							}
							
							
							
						}
						else {
							tempsEsperat+=1;
						}
						
						calculaMoviments(windDirection,specialInvaderWindDirection,maxTempsEspera,tempsEsperat,inBorder);
						
						inBorder = false;
					//	if(bullet.viu=true){
							
					//	}
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
						
						
						for(i=0; i<22; i++){
							if( colisio(normalInvaders[i],bullet) && normalInvaders[i].viu){
								System.out.println("alien " + i + " coalisió\n");
								bullet.viu=false;
								normalInvaders[i].viu = false;
								normalInvaders[i].explotat = true;
								tempsEsperat = 0;
								score += 20;
								scoreSubstractedBonus +=20;
								playSound("invaderkilled");
						}
							
							if( colisio(smallInvaders[i],bullet) && smallInvaders[i].viu){
								System.out.println("alien " + i + " coalisió\n");
								bullet.viu=false;
								smallInvaders[i].viu = false;
								smallInvaders[i].explotat = true;
								tempsEsperat = 0;
								score += 40;
								scoreSubstractedBonus +=40;
								playSound("invaderkilled");
								
						}
							if( colisio(largeInvaders[i],bullet) && largeInvaders[i].viu){
								System.out.println("alien " + i + " coalisió\n");
								bullet.viu=false;
								largeInvaders[i].viu = false;
								largeInvaders[i].explotat = true;
								tempsEsperat = 0;
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
								timeWaitedInPause=0;
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
						
						for(i=0; i<10; i++){
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
					else{
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
							System.out.println("time Waited in pause = " + timeWaitedInPause+"\n");
							
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
				while(gameOver==true){
					//stuck in here, press enter
					
				}
				

				
			}
		}
		
		

		
	}
	
	void inicialitzacio(){
		score = 0;
		numVides=3;//3
		voltesSI=-1;
		c1 = new CotxeConductor(0,f.ALT*8/9-14*pixel.size,50,15,pixel.size,true,false);
		b1 = new Barrier((f.AMPLE-6*4*4*pixel.size)/5,f.ALT*11/16);
		b2 = new Barrier((f.AMPLE-6*4*4*pixel.size)*2/5+6*4*pixel.size,f.ALT*11/16);
		b3 = new Barrier((f.AMPLE-6*4*4*pixel.size)*3/5+2*6*4*pixel.size,f.ALT*11/16);
		b4 = new Barrier((f.AMPLE-6*4*4*pixel.size)*4/5+3*6*4*pixel.size,f.ALT*11/16);
		
		bullet = new Bullet(-100,-100,1,4,pixel.size,false,false);
		specialInvaderBullet = new Bullet(-100,-100,1,4,pixel.size,false,false);
		
		specialInvader = new SpecialInvader(400,f.ALT*7/36,50,15,pixel.size,true,false); 
		
		int i;

		int marge = 3*pixel.size;
		
		largeInvaders[5] = new LargeInvader(f.AMPLE/2,260,20,20,pixel.size,true,false,false,alturaPerillosa);
		normalInvaders[5] = new NormalInvader(f.AMPLE/2,320,20,20,pixel.size,true,false,false,alturaPerillosa);
		smallInvaders[5] = new SmallInvader(f.AMPLE/2,230,20,20,pixel.size,true,false,false,alturaPerillosa);
		
		largeInvaders[5+11] = new LargeInvader(f.AMPLE/2,290,20,20,pixel.size,true,false,false,alturaPerillosa);
		normalInvaders[5+11] = new NormalInvader(f.AMPLE/2,350,20,20,pixel.size,true,false,false,alturaPerillosa);
		smallInvaders[5+11] = new SmallInvader(f.AMPLE/2,200,20,20,pixel.size,true,false,false,alturaPerillosa);
		
		
		for(i=1; i<=5; i++){
			largeInvaders[5+i] = new LargeInvader(f.AMPLE/2+i*largeInvaders[5].ample+i*marge,260,20,20,pixel.size,true,false,false,alturaPerillosa);
			largeInvaders[5-i] = new LargeInvader(f.AMPLE/2-i*largeInvaders[5].ample-i*marge,260,20,20,pixel.size,true,false,false,alturaPerillosa);
			
			largeInvaders[11+5+i] = new LargeInvader(f.AMPLE/2+i*largeInvaders[5].ample+i*marge,290,20,20,pixel.size,true,false,false,alturaPerillosa);
			largeInvaders[11+5-i] = new LargeInvader(f.AMPLE/2-i*largeInvaders[5].ample-i*marge,290,20,20,pixel.size,true,false,false,alturaPerillosa);
			
			normalInvaders[5+i] = new NormalInvader(f.AMPLE/2+i*largeInvaders[5].ample+i*marge ,320,20,20,pixel.size,true,false,false,alturaPerillosa);
			normalInvaders[5+i+11] = new NormalInvader(f.AMPLE/2+i*largeInvaders[5].ample+i*marge,350,20,20,pixel.size,true,false,false,alturaPerillosa);
			normalInvaders[5-i] = new NormalInvader(f.AMPLE/2-i*largeInvaders[5].ample-i*marge,320,20,20,pixel.size,true,false,false,alturaPerillosa);
			normalInvaders[5-i+11] = new NormalInvader(f.AMPLE/2-i*largeInvaders[5].ample-i*marge,350,20,20,pixel.size,true,false,false,alturaPerillosa);
			
			
			smallInvaders[5+i] = new SmallInvader(f.AMPLE/2+i*largeInvaders[5].ample+i*marge,230,20,20,pixel.size,true,false,false,alturaPerillosa);
			smallInvaders[5+i+11] = new SmallInvader(f.AMPLE/2+i*largeInvaders[5].ample+i*marge,200,20,20,pixel.size,true,false,false,alturaPerillosa);
			smallInvaders[5-i] = new SmallInvader(f.AMPLE/2-i*largeInvaders[5].ample-i*marge,230,20,20,pixel.size,true,false,false,alturaPerillosa);
			smallInvaders[11+5-i] = new SmallInvader(f.AMPLE/2-i*largeInvaders[5].ample-i*marge,200,20,20,pixel.size,true,false,false,alturaPerillosa);
		
		}
	}
	
	void calculaMoviments(int windDirection,int specialInvaderWindDriection, int maxTempsEspera, int tempsEsperat, boolean inBorder){
		//c1.moviment();
		
		if(f.movimentDreta==true && c1.x+20+c1.ample<f.AMPLE){
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
		if(specialInvaderBullet.y>f.ALT-100){
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
			if(specialInvaderBullets.get(i).y>f.ALT-100){
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
			if(invaderBullets.get(i).y>f.ALT-100){
				invaderBullets.get(i).viu=false;
			}
		}
		
		
	}
	
	
	void calculaNovaPosicio(){
		//y2 = y2 +10;
		x2+=200;
	}
	
	void dibuixa( boolean estirat,int timeWaitedInPause, int score, boolean gameOver){
		g.setColor(Color.black);
		g.fillRect(0,0,f.AMPLE,f.ALT);
		g.drawImage(backgroundImage,0,0,null);
		
		g.setColor(Color.cyan);
		g.fillRect(0,f.ALT*8/9,f.AMPLE,pixel.size);
		g.setColor(Color.cyan);
		g.fillRect(0,f.ALT*6/9,f.AMPLE,pixel.size);
		
		  
 		g.setColor(Color.red);
		g.fillRect(0,f.ALT*3/18,f.AMPLE,pixel.size);
	
		
		g.setColor(Color.white);
		
		font = font.deriveFont(Font.BOLD, 20f);
		g.setFont(font);
		g.drawString("SCORE", 2*20, f.ALT/8);
		g.drawString("LIVES",f.AMPLE-20*(2+5),f.ALT/8);
		
		g.setColor(Color.cyan);
		g.drawString(""+score, 20*(2+5), f.ALT/8);
		g.drawString(""+numVides, f.AMPLE-20*(2), f.ALT/8);
		
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
	
	void playSound(String nameSoundFile) {
		AudioInputStream audioIn = null;
		try {
			audioIn = AudioSystem.getAudioInputStream(Joc.class.getResource("sound/"+nameSoundFile+".wav"));
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Clip clip = null;
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clip.open(audioIn);
		} catch (LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clip.start();
//		 try
//		  {
//			// get the sound file as a resource out of my jar file;
//		    // the sound file must be in the same directory as this class file.
//		    InputStream inputStream = getClass().getResourceAsStream("sound/"+nameSoundFile+".wav");  
//		    AudioStream audioStream = new AudioStream(inputStream);
//		    AudioPlayer.player.start(audioStream);
//		  }
//		  catch (Exception e)
//		  {
//		    // a special way i'm handling logging in this application
//		    //if (debugFileWriter!=null) e.printStackTrace(debugFileWriter);
//		  }
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
	
	void inicialitzaInvadersNewRound(){
		
		largeInvaders[5] = new LargeInvader(f.AMPLE/2,260,20,20,pixel.size,true,false,false,alturaPerillosa);
		normalInvaders[5] = new NormalInvader(f.AMPLE/2,320,20,20,pixel.size,true,false,false,alturaPerillosa);
		smallInvaders[5] = new SmallInvader(f.AMPLE/2,230,20,20,pixel.size,true,false,false,alturaPerillosa);
		
		largeInvaders[5+11] = new LargeInvader(f.AMPLE/2,290,20,20,pixel.size,true,false,false,alturaPerillosa);
		normalInvaders[5+11] = new NormalInvader(f.AMPLE/2,350,20,20,pixel.size,true,false,false,alturaPerillosa);
		smallInvaders[5+11] = new SmallInvader(f.AMPLE/2,200,20,20,pixel.size,true,false,false,alturaPerillosa);
		
		int marge = 3*pixel.size;
		
		for(int i=1; i<=5; i++){
			largeInvaders[5+i] = new LargeInvader(f.AMPLE/2+i*largeInvaders[5].ample+i*marge,260,20,20,pixel.size,true,false,false,alturaPerillosa);
			largeInvaders[5-i] = new LargeInvader(f.AMPLE/2-i*largeInvaders[5].ample-i*marge,260,20,20,pixel.size,true,false,false,alturaPerillosa);
			
			largeInvaders[11+5+i] = new LargeInvader(f.AMPLE/2+i*largeInvaders[5].ample+i*marge,290,20,20,pixel.size,true,false,false,alturaPerillosa);
			largeInvaders[11+5-i] = new LargeInvader(f.AMPLE/2-i*largeInvaders[5].ample-i*marge,290,20,20,pixel.size,true,false,false,alturaPerillosa);
			
			normalInvaders[5+i] = new NormalInvader(f.AMPLE/2+i*largeInvaders[5].ample+i*marge ,320,20,20,pixel.size,true,false,false,alturaPerillosa);
			normalInvaders[5+i+11] = new NormalInvader(f.AMPLE/2+i*largeInvaders[5].ample+i*marge,350,20,20,pixel.size,true,false,false,alturaPerillosa);
			normalInvaders[5-i] = new NormalInvader(f.AMPLE/2-i*largeInvaders[5].ample-i*marge,320,20,20,pixel.size,true,false,false,alturaPerillosa);
			normalInvaders[5-i+11] = new NormalInvader(f.AMPLE/2-i*largeInvaders[5].ample-i*marge,350,20,20,pixel.size,true,false,false,alturaPerillosa);
			
			
			smallInvaders[5+i] = new SmallInvader(f.AMPLE/2+i*largeInvaders[5].ample+i*marge,230,20,20,pixel.size,true,false,false,alturaPerillosa);
			smallInvaders[5+i+11] = new SmallInvader(f.AMPLE/2+i*largeInvaders[5].ample+i*marge,200,20,20,pixel.size,true,false,false,alturaPerillosa);
			smallInvaders[5-i] = new SmallInvader(f.AMPLE/2-i*largeInvaders[5].ample-i*marge,230,20,20,pixel.size,true,false,false,alturaPerillosa);
			smallInvaders[11+5-i] = new SmallInvader(f.AMPLE/2-i*largeInvaders[5].ample-i*marge,200,20,20,pixel.size,true,false,false,alturaPerillosa);
		
		}
		
	}
	void dibuixaGamePaused(){
		while(gamePaused==true){
			g.setColor(Color.white);
			font = font.deriveFont(Font.BOLD, 20f);
			g.setFont(font);
			g.drawString("GAME  PAUSED", f.AMPLE/2-9*10, 67*f.ALT/72);
			font = font.deriveFont(Font.BOLD, 10f);
			g.setFont(font);
			g.drawString("Press 'P' to unpause", f.AMPLE/2-8*10, 70*f.ALT/72);
			f.repaint();
			System.out.println("im in a loop\n");
			
		}
		
	}
	void dibuixaGameOver(){
		g.setColor(Color.white);
		font = font.deriveFont(Font.BOLD, 20f);
		g.setFont(font);
		g.drawString("GAME OVER", f.AMPLE/2-9*10, 67*f.ALT/72);
		font = font.deriveFont(Font.BOLD, 10f);
		g.setFont(font);
		g.drawString("Press 'ENTER' to continue", f.AMPLE/2-12*10, 70*f.ALT/72);
		f.repaint();
		System.out.println("im in a loop\n");
	}
	
	void dibuixaCredits(){

		
		//g.drawImage(sideImage,0,0,null);
		
		font = font.deriveFont(Font.BOLD, 30f);
		g.setFont(font);
		g.drawString("Credits", f.AMPLE/2-9*10, f.ALT/2);
		font = font.deriveFont(Font.BOLD, 10f);
		g.setFont(font);
		f.repaint();	
		try {
			sleep(1000);//perdem 50ms
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		f.repaint();
		for(int i=0; i<f.ALT+100+29*15+110; i++){
			try {
				sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int alturaChange = f.ALT-i;
			
			g.setColor(Color.black);
			g.fillRect(0,0,f.AMPLE,f.ALT);
			g.setColor(Color.white);
			g.drawImage(creditsImage,0,0,null);
			font = font.deriveFont(Font.BOLD, 15f); g.setFont(font);
			g.drawString("This game has been developed by:", f.AMPLE/2-11*15, 90+alturaChange);
			font = font.deriveFont(Font.BOLD, 20f); g.setFont(font);
			g.setColor(Color.orange);
			g.drawString("XAVIER LOPEZ", f.AMPLE/2-4*20, 100+2*15+alturaChange);
			g.setColor(Color.white);
			font = font.deriveFont(Font.BOLD, 12f); g.setFont(font);
			g.drawString("As an assigment from the Advanced Programming Course", f.AMPLE/2-20*12, 100+5*15+alturaChange);
			//font = font.deriveFont(Font.BOLD, 15f); g.setFont(font);
			g.drawString("Mentored by:", f.AMPLE/2-5*12, 100+7*15+alturaChange);
			font = font.deriveFont(Font.BOLD, 17f); g.setFont(font);
			g.setColor(Color.orange);
			g.drawString("Vicente Soler", f.AMPLE/2-6*15, 100+9*15+alturaChange);
			g.setColor(Color.white);
			font = font.deriveFont(Font.BOLD, 12f); g.setFont(font);
			g.drawString("During the first semester of 2016/2017 in:", f.AMPLE/2-12*15, 100+12*15+alturaChange);
			font = font.deriveFont(Font.BOLD, 15f); g.setFont(font); g.setColor(Color.orange);
			g.drawString("UAB Degree on Mathematics", f.AMPLE/2-12*12, 100+14*15+alturaChange);
			g.setColor(Color.white);
			font = font.deriveFont(Font.BOLD, 12f); g.setFont(font); 
			g.drawString("Inspired on the original arcade game:" , f.AMPLE/2-12*14, 100+17*15+alturaChange);
			g.setColor(Color.orange); font = font.deriveFont(Font.BOLD, 15f); g.setFont(font); 
			g.drawString("SPACE INVADERS" , f.AMPLE/2-15*5, 100+19*15+alturaChange); g.setColor(Color.white);
			font = font.deriveFont(Font.BOLD, 12f); g.setFont(font); 
			g.drawString("Created by:" , f.AMPLE/2-12*5, 100+22*15+alturaChange);
			font = font.deriveFont(Font.BOLD, 15f); g.setFont(font);  g.setColor(Color.orange);
			g.drawString("Toshihiro Nishikado" , f.AMPLE/2-10*10, 100+24*15+alturaChange);
			g.setColor(Color.white);
			font = font.deriveFont(Font.BOLD, 12f); g.setFont(font); 
			g.drawString("and originaly released to the market in 1978 by " , f.AMPLE/2-12*18, 100+27*15+alturaChange);g.setColor(Color.orange);
			font = font.deriveFont(Font.BOLD, 15f); g.setFont(font); 
			g.drawString("Taito Corporation " , f.AMPLE/2-12*8, 100+29*15+alturaChange);
			
			g.drawImage(uabImage,f.AMPLE/2-12*8, 100+32*15+alturaChange,null);
			
			if(gameCredits == false ){break;}
			f.repaint();
		}
		menuDisplay = true;
		gameCredits = false;
		System.out.println("im in a loop\n");
	}
	
void dibuixatopHighScoreMenu(){

		
		g.setColor(Color.black);
		g.fillRect(0,0,f.AMPLE,f.ALT);
		g.drawImage(monsterImage,0,200,null);
		
		font = font.deriveFont(Font.BOLD, 30f); g.setFont(font);
		g.setColor(Color.white);
		g.drawString("YOUR HIGHEST SOCRE IS:", 170, f.ALT/8);
		g.drawString("999999", f.AMPLE/2-50, f.ALT/8+50);
		f.repaint();
		while(true){
			// do nothing
			if(topHighScoreMenu == false ){break;}
		}
		menuDisplay = true;
		topHighScoreMenu = false;
	}
	
void dibuixaOptionsMenu(){

	
	g.setColor(Color.black);
	g.fillRect(0,0,f.AMPLE,f.ALT);
	g.drawImage(monsterImage,0,200,null);
	
	font = font.deriveFont(Font.BOLD, 30f); g.setFont(font);
	g.setColor(Color.white);
	g.drawString("OPTIONS", f.AMPLE/2-50, f.ALT/8);
	g.drawString("999999", f.AMPLE/2-50, f.ALT/8+50);
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
	g.fillRect(0,0,f.AMPLE,f.ALT);
	g.drawImage(backgroundImage,0,100,null);
	//g.drawImage(instructionsImage,175,50,null);
	font = font.deriveFont(Font.BOLD, 40f); g.setFont(font);
	g.setColor(Color.white);
	g.drawString("INSTRUCTIONS", 200, f.ALT/8);
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
		g.fillRect(0,0,f.AMPLE,f.ALT);
		g.drawImage(menuBackgroundImage,0,100,null);
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
		InputStream is = getClass().getResourceAsStream("font/space_invaders.ttf");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		font = font.deriveFont(Font.BOLD, 75f);
		 //font = new Font("Jokerman", Font.PLAIN, 35);
		g.setFont(font);
		
		
		
		g.drawImage(image,f.AMPLE/2-250,f.ALT/2-300,null);
		
		g.setColor(Color.WHITE);
		//g.drawString("SPACE", f.AMPLE/3, f.ALT/4);
		//g.drawString("INVADERS", f.AMPLE/4, 3*f.ALT/8);
		
		font = font.deriveFont(Font.BOLD, 15f);
		g.setFont(font);
		
		g.setColor(Color.white);
		
		LargeInvader iniciLargeInvader = new LargeInvader(9*f.AMPLE/20, f.ALT/2-20,20,20,pixel.size,true,false,false,alturaPerillosa);
		iniciLargeInvader.dibuixa(g, false);
		NormalInvader iniciNormalInvader = new NormalInvader(9*f.AMPLE/20, f.ALT/2-20+40,20,20,pixel.size,true,false,false,alturaPerillosa);
		iniciNormalInvader.dibuixa(g, false);
		SmallInvader iniciSmallInvader = new SmallInvader(9*f.AMPLE/20+2*pixel.size, f.ALT/2-20+40*2,20,20,pixel.size,true,false,false,alturaPerillosa);
		iniciSmallInvader.dibuixa(g, false);
		
		SpecialInvader iniciSpecialInvader = new SpecialInvader(9*f.AMPLE/20-3*pixel.size, f.ALT/2-20+40*3,50,15,pixel.size,true,false); 
		iniciSpecialInvader.dibuixa(g);
		g.setColor(Color.WHITE);
		g.drawString("PLAY SPACE INVADERS", 8*f.AMPLE/20, 3*f.ALT/4);
		g.drawString(" = 10PTS", 9*f.AMPLE/20+40, f.ALT/2);
		
		g.drawString(" = 20PTS", 9*f.AMPLE/20+40, f.ALT/2+40);
		g.drawString(" = 40PTS", 9*f.AMPLE/20+40, f.ALT/2+40*2);
		g.drawString(" = ?PTS", 9*f.AMPLE/20+40, f.ALT/2+40*3);
		
		g.drawString("HIGH SCORES", 9*f.AMPLE/20, 3*f.ALT/4+30);
		g.drawString("INSTRUCTIONS", 9*f.AMPLE/20, 3*f.ALT/4+2*30);
		g.drawString("OPTIONS", 9*f.AMPLE/20, 3*f.ALT/4+3*30);
		g.drawString("CREDITS", 9*f.AMPLE/20, 3*f.ALT/4+4*30);
		
		g.setColor(Color.cyan);
		if(menuSelection==0){
			g.drawString("PLAY SPACE INVADERS", 8*f.AMPLE/20, 3*f.ALT/4);
		}
		else if(menuSelection==1){
			g.drawString("HIGH SCORES", 9*f.AMPLE/20, 3*f.ALT/4+30);
		}
		else if(menuSelection==2){
			g.drawString("INSTRUCTIONS", 9*f.AMPLE/20, 3*f.ALT/4+2*30);
		}
		else if(menuSelection==3){
			g.drawString("OPTIONS", 9*f.AMPLE/20, 3*f.ALT/4+3*30);
		}
		else{
			g.drawString("CREDITS", 9*f.AMPLE/20, 3*f.ALT/4+4*30);
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