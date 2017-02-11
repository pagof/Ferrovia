package ferrovia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BinarioCurvoSemplice extends Binario {
	int lunghezzaD=31;//pezzo Dritto
	int lunghezzaC=20;//pezzo Curvo
	Giunto f1 = null;
	Giunto m1 = null;
	
	//posizione punto angolo intermedio
	int cX;
	int cY;
	//
	//   f1 --- --- --- m1
	//
	

	public void disegna(Graphics g) {
		if(!selezionato )return;
		Graphics2D g2 = (Graphics2D)g;
	    //ricalcolaPosizioneGiunti();
		
		g.setColor(Color.red);
		g2.drawLine(femmine[0].posX,femmine[0].posY,cX, cY);
		g.setColor(Color.red);
		g2.drawLine(cX,cY,  maschi[0].posX, maschi[0].posY);
		g.setColor(Color.red);
		g.setFont(new Font("Arial", Font.PLAIN, 9));
		g.drawString(""+id, femmine[0].posX+1,femmine[0].posY-1);
		
		g.drawString("Fx."+femmine[0].posX+" Fy:"+ femmine[0].posY, 10,id*30);
		g.drawString("Mx."+maschi[0].posX+" My:"+ maschi[0].posY, 10,id*30+10);
	}	
	
public BinarioCurvoSemplice(int id) {
		super();
		forma ="CURVA-SU";
	inclinazione=0;
	ribaltato = false;
	selezionato = false;
	nome = "CURVO"+id;
	
	f1 = new Giunto();
	m1 = new Giunto();
	m1.inclinGiunto=270;
	
	
	
	f1.posX =(int)(Math.random()*100)+150;
	f1.posY =(int)(Math.random()*100)+150;

	cX=(int)f1.posX+(int)(Math.cos(fattrad*this.inclinazione)*lunghezzaD);
	cY=(int)f1.posY+(int)(Math.sin(fattrad*this.inclinazione)*lunghezzaD);

	
	m1.posX=(int)cX+(int)(Math.cos((fattrad*((this.inclinazione+270)%angolo_giro)))*lunghezzaC);
	m1.posY=(int)cY+(int)(Math.sin((fattrad*((this.inclinazione+270)%angolo_giro)))*lunghezzaC);
	
	maschi = new Giunto[1];
	maschi[0]=m1;
	
	femmine = new Giunto[1]; 
	femmine[0] = f1;
}


public void rovescia(){
	if(ribaltato)		forma ="CURVA-SU";
	else 				forma ="CURVA-GIU";

	
	if(!ribaltato) m1.inclinGiunto=(m1.inclinGiunto+360+90)%angolo_giro;
	if( ribaltato) m1.inclinGiunto=(m1.inclinGiunto+360-90)%angolo_giro;
	
	ribaltato = !ribaltato;
}

public void ricalcolaPosizioneGiunti(){
	cX=(int)f1.posX+(int)(Math.cos(fattrad*this.inclinazione)*lunghezzaD);
	cY=(int)f1.posY+(int)(Math.sin(fattrad*this.inclinazione)*lunghezzaD);

	
	if(ribaltato){
		m1.posX=(int)cX+(int)(Math.cos(fattrad*((this.inclinazione-270)%angolo_giro))*lunghezzaC);
		m1.posY=(int)cY+(int)(Math.sin(fattrad*((this.inclinazione-270)%angolo_giro))*lunghezzaC);
	}
	else{
		m1.posX=(int)cX+(int)(Math.cos(fattrad*((this.inclinazione+270)%angolo_giro))*lunghezzaC);
		m1.posY=(int)cY+(int)(Math.sin(fattrad*((this.inclinazione+270)%angolo_giro))*lunghezzaC);
	}

	
	

}
}//fine classe


