import java.applet.Applet;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
//Ctrl+shift + O
import java.awt.event.WindowListener;

import javax.swing.JFrame;

//Control shift 0 (fer import // organize imports)// source -> organize imoprts

@SuppressWarnings("serial")
public class Finestra extends Frame implements KeyListener, WindowListener{ //JFrame extends Frame, and its better :)
	
	public static int AMPLE = 800, ALT = 790;
	//int AMPLE = 800, ALT = 500;
	Image im;
	Graphics g;
	Joc j;
	boolean movimentEsquerra=false, movimentDreta=false;
	
	
	public static void main(String[] args) {
		new Finestra();
	}
	
	public void paint(Graphics g){
		g.drawImage(im, 0,0, null);
	}
	
	public void update(Graphics g){
		paint(g);
	}
	
	/*public void init*/Finestra(){
		setSize(AMPLE,ALT);
		setVisible(true);
		//this.addKeyListener(this);
		this.addWindowListener( this); //why diferent from previous line?
		this.addKeyListener(this);
		
		
		//j.this.addMouseListener(this);
		im = this.createImage(AMPLE,ALT);
		g = im.getGraphics();
		
		j = new Joc(this);
		j.start();
		//j is an object of the class Thread. 
		//When you call j.start(), it starts a new thread and calls the run() method of j internally to execute it within that new thread.
		
		
	}

	public void keyTyped(KeyEvent e) {
		
		
	}



	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
	
		if(keyCode == KeyEvent.VK_UP){
        	System.out.print("UP\n");
        	if(j.menuSelection>0){
        		j.menuSelection-=1;
        	}
        	
			//j.c1.x-=15;
	}
		if(keyCode == KeyEvent.VK_DOWN){
        	System.out.print("DOWN\n");
        	if(j.menuSelection<4){
        		j.menuSelection+=1;
        	}
        	
			//j.c1.x-=15;
	}
		
		if(keyCode == KeyEvent.VK_LEFT){
	        	System.out.print("LEFT\n");
	        	movimentEsquerra = true;
				//j.c1.x-=15;
		}
		if(keyCode == KeyEvent.VK_RIGHT){
	        	System.out.print("RIGHT\n");
	        	movimentDreta = true;
	        	//j.c1.x+=15;
		}
		if(keyCode == KeyEvent.VK_SPACE){
			if(j.bullet.viu==false){
			j.bullet.x=j.c1.x+j.c1.ample/2;
        	j.bullet.y=j.c1.y - j.c1.alt/2;
        	j.bullet.viu= true;
        	j.playSound("shoot");
        	System.out.print("SPACE\n");
			}
			}
		
		if(keyCode == KeyEvent.VK_S){

			if(j.specialInvaderBullet.viu==false){
				j.specialInvaderBullet.x=j.specialInvader.x+j.specialInvader.ample/2;
	        	j.specialInvaderBullet.y=j.specialInvader.y - j.specialInvader.alt/2;
	        	j.specialInvaderBullet.viu= true;
        	j.playSound("ufo_shoot");
        	System.out.print("SPACE\n");
        	
			}

			
			}
		if(keyCode == KeyEvent.VK_D){
			 j.siBullet = new Bullet(j.specialInvader.x+j.specialInvader.ample/2,j.specialInvader.y - j.specialInvader.alt/2,1,4,2/*=pixel*/,true /*=viu*/,false/*=inDesctruction*/);
			 j.playSound("ufo_shoot");
			 j.specialInvaderBullets.add(j.siBullet);
			}
		
		if(keyCode == KeyEvent.VK_F){
			 j.invaderBullet = new Bullet(j.elInvaderQueDispara().x+j.elInvaderQueDispara().ample/2,j.elInvaderQueDispara().y - j.elInvaderQueDispara().alt/2,1,4,2/*=pixel*/,true /*=viu*/,false/*=inDesctruction*/);
			 j.playSound("ufo_shoot");
			 j.invaderBullets.add(j.invaderBullet);
			}
		if(keyCode == KeyEvent.VK_P){
			if(j.gameStarted==true){
				if(j.gamePaused==true){
					j.gamePaused =false;
				}
				else j.gamePaused = true;
				 System.out.println("pause"+j.gamePaused+"\n");
			}
		
			}
		
		if(keyCode == KeyEvent.VK_M){
			 if(j.soundOn==true){j.soundOn=false;}
			 else j.soundOn=true;
			}
		
		if(keyCode == KeyEvent.VK_R){
			j.numberOfRounds++;
			j.inicialitzaInvaders(j.numberOfRounds);
			System.out.println("Num ronda = " + j.numberOfRounds);
		
			}
		
		if(keyCode == KeyEvent.VK_ENTER){
			if(j.gameStarted==false && j.menuDisplay){
				if(j.menuSelection==0){
					j.gameStarted = true;
				}
				else if(j.menuSelection==4){
					j.gameCredits=true;
					j.menuDisplay=false;
				}
				else if(j.menuSelection==1){
					j.topHighScoreMenu=true;
					j.menuDisplay=false;
				}
				
				else if(j.menuSelection==2){
					j.instructionsMenu=true;
					j.menuDisplay=false;
				}
				
				else if(j.menuSelection==3){
					j.optionsMenu=true;
					j.menuDisplay=false;
				}
				
			}
			else if(j.menuDisplay==false){
				if(j.gameCredits==true){
					j.menuDisplay = true;
					j.gameCredits = false;
				}
				if(j.topHighScoreMenu==true){
					j.menuDisplay=true;
					j.topHighScoreMenu=false;
				}
				if(j.instructionsMenu==true){
					j.menuDisplay=true;
					j.instructionsMenu=false;
				}
				if(j.optionsMenu==true){
					j.menuDisplay=true;
					j.optionsMenu=false;
				}
				
			}
			
			else{
				j.gameStarted=false;
			}
			
			
			j.gameOver=false;
			j.playSound("invaderkilled");
		}
		
		if(keyCode == KeyEvent.VK_ESCAPE){
			j.gameOver = true;
			j.gameStarted = true;
			j.numVides=0;
			j.c1.viu=false;
		}
		//System.out.println("Game credits = " + j.gameCredits + "\n");
		//System.out.println("High score menu = " + j.topHighScoreMenu+ "\n");
	} 


	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_LEFT){
	        	System.out.print("LEFT\n");
				movimentEsquerra = false;
		}
		if(keyCode == KeyEvent.VK_RIGHT){
	        	System.out.print("RIGHT\n");
	        	movimentDreta = false;
		}
	}

	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosed(WindowEvent arg0) {
		System.exit(0); //Finalitza execucio
		
	}

	public void windowClosing(WindowEvent arg0) {
		dispose(); // tanca finestra, no finalitza execucio
		
	}

	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		j.gameStarted = true;
		System.out.printf("botÛ click");
	}
	
	

}
 // wubdiw -> showview -> conole // poder parar aplicaci√≥
 
 /*
 import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
//Ctrl+shift + O
import java.awt.event.WindowListener;

import javax.swing.JFrame;

//Control shift 0 (fer import // organize imports)// source -> organize imoprts

public class Finestra extends Frame implements KeyListener, WindowListener{ //JFrame extends Frame, and its better :)
	
	int AMPLE = 800, ALT = 790;
	//int AMPLE = 800, ALT = 500;
	Image im;
	Graphics g;
	Joc j;
	boolean movimentEsquerra=false, movimentDreta=false;
	
	
	public static void main(String[] args) {
		new Finestra();
	}
	
	public void paint(Graphics g){
		g.drawImage(im, 0,0, null);
	}
	
	public void update(Graphics g){
		paint(g);
	}
	
	Finestra(){
		setSize(AMPLE,ALT);
		setVisible(true);
		//this.addKeyListener(this);
		this.addWindowListener( this); //why diferent from previous line?
		this.addKeyListener(this);
		
		
		//j.this.addMouseListener(this);
		im = this.createImage(AMPLE,ALT);
		g = im.getGraphics();
		
		j = new Joc(this);
		j.start();
		//j is an object of the class Thread. 
		//When you call j.start(), it starts a new thread and calls the run() method of j internally to execute it within that new thread.
		
		
	}

	public void keyTyped(KeyEvent e) {
		
		
	}



	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
	
		if(keyCode == KeyEvent.VK_UP){
        	System.out.print("UP\n");
        	if(j.menuSelection>0){
        		j.menuSelection-=1;
        	}
        	
			//j.c1.x-=15;
	}
		if(keyCode == KeyEvent.VK_DOWN){
        	System.out.print("DOWN\n");
        	if(j.menuSelection<4){
        		j.menuSelection+=1;
        	}
        	
			//j.c1.x-=15;
	}
		
		if(keyCode == KeyEvent.VK_LEFT){
	        	System.out.print("LEFT\n");
	        	movimentEsquerra = true;
				//j.c1.x-=15;
		}
		if(keyCode == KeyEvent.VK_RIGHT){
	        	System.out.print("RIGHT\n");
	        	movimentDreta = true;
	        	//j.c1.x+=15;
		}
		if(keyCode == KeyEvent.VK_SPACE){
			if(j.bullet.viu==false){
			j.bullet.x=j.c1.x+j.c1.ample/2;
        	j.bullet.y=j.c1.y - j.c1.alt/2;
        	j.bullet.viu= true;
        	j.playSound("shoot");
        	System.out.print("SPACE\n");
			}
			}
		
		if(keyCode == KeyEvent.VK_S){

			if(j.specialInvaderBullet.viu==false){
				j.specialInvaderBullet.x=j.specialInvader.x+j.specialInvader.ample/2;
	        	j.specialInvaderBullet.y=j.specialInvader.y - j.specialInvader.alt/2;
	        	j.specialInvaderBullet.viu= true;
        	j.playSound("ufo_shoot");
        	System.out.print("SPACE\n");
        	
			}

			
			}
		if(keyCode == KeyEvent.VK_D){
			 j.siBullet = new Bullet(j.specialInvader.x+j.specialInvader.ample/2,j.specialInvader.y - j.specialInvader.alt/2,1,4,2);
			 j.playSound("ufo_shoot");
			 j.specialInvaderBullets.add(j.siBullet);
			}
		
		if(keyCode == KeyEvent.VK_F){
			 j.invaderBullet = new Bullet(j.elInvaderQueDispara().x+j.elInvaderQueDispara().ample/2,j.elInvaderQueDispara().y - j.elInvaderQueDispara().alt/2,1,4,2,true,false);
			 j.playSound("ufo_shoot");
			 j.invaderBullets.add(j.invaderBullet);
			}
		if(keyCode == KeyEvent.VK_P){
			if(j.gameStarted==true){
				if(j.gamePaused==true){
					j.gamePaused =false;
				}
				else j.gamePaused = true;
				 System.out.println("pause"+j.gamePaused+"\n");
			}
		
			}
		
		if(keyCode == KeyEvent.VK_ENTER){
			if(j.gameStarted==false && j.menuDisplay){
				if(j.menuSelection==0){
					j.gameStarted = true;
				}
				else if(j.menuSelection==4){
					j.gameCredits=true;
					j.menuDisplay=false;
				}
				else if(j.menuSelection==1){
					j.topHighScoreMenu=true;
					j.menuDisplay=false;
				}
				
				else if(j.menuSelection==2){
					j.instructionsMenu=true;
					j.menuDisplay=false;
				}
				
				else if(j.menuSelection==3){
					j.optionsMenu=true;
					j.menuDisplay=false;
				}
				
			}
			else if(j.menuDisplay==false){
				if(j.gameCredits==true){
					j.menuDisplay = true;
					j.gameCredits = false;
				}
				if(j.topHighScoreMenu==true){
					j.menuDisplay=true;
					j.topHighScoreMenu=false;
				}
				if(j.instructionsMenu==true){
					j.menuDisplay=true;
					j.instructionsMenu=false;
				}
				if(j.optionsMenu==true){
					j.menuDisplay=true;
					j.optionsMenu=false;
				}
				
			}
			
			else{
				j.gameStarted=false;
			}
			
			
			j.gameOver=false;
			j.playSound("invaderkilled");
		}
		if(keyCode == KeyEvent.VK_ESCAPE){
			j.gameOver = true;
			j.gameStarted = true;
			j.numVides=0;
			j.c1.viu=false;
		}
		System.out.println("Game credits = " + j.gameCredits + "\n");
	} 


	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_LEFT){
	        	System.out.print("LEFT\n");
				movimentEsquerra = false;
		}
		if(keyCode == KeyEvent.VK_RIGHT){
	        	System.out.print("RIGHT\n");
	        	movimentDreta = false;
		}
	}

	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosed(WindowEvent arg0) {
		System.exit(0); //Finalitza execucio
		
	}

	public void windowClosing(WindowEvent arg0) {
		dispose(); // tanca finestra, no finalitza execucio
		
	}

	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		j.gameStarted = true;
		System.out.printf("botÛ click");
	}
	
	

}
 // wubdiw -> showview -> conole // poder parar aplicaci√≥
 */
