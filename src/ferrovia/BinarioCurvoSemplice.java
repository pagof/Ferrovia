package ferrovia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BinarioCurvoSemplice extends Binario {
	public double lunghezzaSezA=20;//pezzo Dritto
	public double lunghezzaSezB=20;//pezzo Curvo
	Giunto f1 = null;
	Giunto m1 = null;
	
	//posizione punto angolo intermedio
	double cX;
	double cY;
	//
	//   f1 --- --- --- m1
	//
	
	

	@Override
	public void ricalcolaInclinazioneGiunti(double inclinazione) {
		this.giuntoMaschio.inclinGiunto = (this.inclinazioneGiunto +inclinazione)%360 ;	
	}
	
	
	public void disegna(Graphics g) {
		if(libero )return;
		Graphics2D g2 = (Graphics2D)g;
	    //ricalcolaPosizioneGiunti();
		
		g.setColor(Color.red);
		g2.drawLine((int)Math.round(giuntoFemmina.posX),
					(int)Math.round(giuntoFemmina.posY),
					(int)Math.round(cX), 
					(int)Math.round(cY));
		g.setColor(Color.red);
		g2.drawLine((int)Math.round(cX),
					(int)Math.round(cY),
					(int)Math.round(giuntoMaschio.posX),
					(int)Math.round(giuntoMaschio.posY));
		g.setColor(Color.red);
		g.setFont(new Font("Arial", Font.PLAIN, 9));
		//g.drawString(""+id, (int)Math.round(giuntoFemmina.posX)+1,(int)Math.round(giuntoFemmina.posY)-1);
		//g.drawString("Fx."+giuntoFemmina.posX+" Fy:"+ giuntoFemmina.posY, 10,id*30);
		//g.drawString("Mx."+giuntoMaschio.posX+" My:"+ giuntoMaschio.posY, 10,id*30+10);
	}	
	



	
	
public BinarioCurvoSemplice(int id, double _inclinazioneGiunto ) {
	super(id);
	forma ="CURVA-SU";
	nome = "CURVO"+id;
	inclinazioneGiunto = _inclinazioneGiunto;
	
	f1 = new Giunto();
	m1 = new Giunto();

	giuntoMaschio = m1;
	giuntoFemmina = f1;
	
	this.setInclinazione(Math.random()*360.0);

}



public void ricalcolaPosizioneGiunti(){

	cX=f1.posX+(Math.cos(getRadianti(this.inclinazione) )*lunghezzaSezA);
	cY=f1.posY+(Math.sin(getRadianti(this.inclinazione) )*lunghezzaSezA);

{
		m1.posX=cX+(Math.cos(getRadianti(giuntoMaschio.inclinGiunto))*lunghezzaSezB);
		m1.posY=cY+(Math.sin(getRadianti(giuntoMaschio.inclinGiunto))*lunghezzaSezB);
	}

	
	

}

}//fine classe


