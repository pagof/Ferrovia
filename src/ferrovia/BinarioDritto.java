package ferrovia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BinarioDritto extends Binario {
	double lunghezzaX=31;
	Giunto f1 = null;
	Giunto m1 = null;
	//
	//   f1 --- --- --- m1
	//
	
	public void disegna(Graphics g) {
		if(libero )return;
		
		Graphics2D g2 = (Graphics2D)g;
	    //ricalcolaPosizioneGiunti();
	    g.setColor(Color.blue);
	    
		g2.drawLine((int)Math.round(giuntoFemmina.posX),(int)Math.round(giuntoFemmina.posY),
				(int)Math.round(giuntoMaschio.posX),(int)Math.round(giuntoMaschio.posY));
		g.setColor(Color.red);
		g.setFont(new Font("Arial", Font.PLAIN, 9));
		//g.drawString(""+id, (int)Math.round(giuntoFemmina.posX)+1,(int)Math.round(giuntoFemmina.posY)-1);
		
		//g.drawString("Fx."+(int)Math.round(giuntoFemmina.posX)+" Fy:"+ (int)Math.round(giuntoFemmina.posY), 10,id*30);
		//g.drawString("Mx."+(int)Math.round(+giuntoMaschio.posX)+" My:"+ (int)Math.round(giuntoMaschio.posY), 10,id*30+10);
	}
	
	
public BinarioDritto(int id) {
		super(id);
	forma ="DRITTO";

	nome = "DRITTO"+id;
	

	f1 = new Giunto();
	m1 = new Giunto();
	m1.inclinGiunto = 0;
	this.inclinazione =Math.random()*360.0;
	

	m1.posX = f1.posX+ Math.round(Math.cos(fattrad*this.inclinazione)*lunghezzaX);
	m1.posY = f1.posY+ Math.round(Math.sin(fattrad*this.inclinazione)*lunghezzaX);
	
	giuntoMaschio = m1;
	giuntoFemmina = f1;
}



public void ricalcolaPosizioneGiunti(){
	m1.posX=f1.posX + Math.round(Math.cos(fattrad*this.inclinazione%angolo_giro)*lunghezzaX);
	m1.posY=f1.posY + Math.round(Math.sin(fattrad*this.inclinazione%angolo_giro)*lunghezzaX);
	
}
}//fine classe


