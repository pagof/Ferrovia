package ferrovia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BinarioDritto extends Binario {
	int lunghezzaX=31;
	Giunto f1 = null;
	Giunto m1 = null;
	//
	//   f1 --- --- --- m1
	//
	
	
	public void disegna(Graphics g) {
		if(!selezionato )return;
		
		Graphics2D g2 = (Graphics2D)g;
	    //ricalcolaPosizioneGiunti();
	    g.setColor(Color.green);
		g2.drawLine(femmine[0].posX,femmine[0].posY,
				    maschi[0].posX, maschi[0].posY);
		g.setColor(Color.red);
		g.setFont(new Font("Arial", Font.PLAIN, 9));
		g.drawString(""+id, femmine[0].posX+1,femmine[0].posY-1);
		
		g.drawString("Fx."+femmine[0].posX+" Fy:"+ femmine[0].posY, 10,id*30);
		g.drawString("Mx."+maschi[0].posX+" My:"+ maschi[0].posY, 10,id*30+10);
	}	

	
	
public BinarioDritto(int id) {
		super();
	forma ="DRITTO";
	inclinazione=0;
	ribaltato = false;
	selezionato = false;
	nome = "DRITTO"+id;
	

	f1 = new Giunto();
	m1 = new Giunto();
	m1.inclinGiunto=0;
	
	f1.posX =(int)(Math.random()*100)+150;
	f1.posY =(int)(Math.random()*100)+150;
	m1.posX=(int)f1.posX+(int)(Math.cos(fattrad*this.inclinazione)*lunghezzaX);
	m1.posY=(int)f1.posY+(int)(Math.sin(fattrad*this.inclinazione)*lunghezzaX);
	
	maschi = new Giunto[1];
	maschi[0]=m1;
	
	femmine = new Giunto[1]; 
	femmine[0] = f1;
}


public void rovescia(){
	ribaltato=!ribaltato;
}

public void ricalcolaPosizioneGiunti(){
	m1.posX=(int)f1.posX+(int)(Math.cos(fattrad*(this.inclinazione%angolo_giro))*lunghezzaX);
	m1.posY=(int)f1.posY+(int)(Math.sin(fattrad*(this.inclinazione%angolo_giro))*lunghezzaX);
	
}
}//fine classe


