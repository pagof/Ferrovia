package ferrovia;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;

class Area extends Canvas {
	
	private static Area area =null;
	
	
	Binario[] binario_list = {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null};
	int binario_num;
	
	
	public void paint(Graphics g) {
		g.setColor(Color.red);
	/*or (int i = 1; i <= 5; i++) {
			g.drawLine(50, 10 * i, 200, 20 + 10 * i);
		}
		g.setColor(Color.blue);
		g.setFont(new Font("Dialog", Font.PLAIN, 18));
		g.drawString("LINEE PARALLELE", 50, 100);
	*/  
	    
	    for (int ii=1; ii<=Binario.newid; ii++) {
	    	//g.setColor(Color.blue);
		    Binario bin=binario_list[ii];
	        if (bin != null) bin.disegna(g);

	    }
	}
	
	public static Area getIstanza() {
		if (area != null) return area;
		
		Frame f = new Frame("Disegno");
		area = new Area();
		f.setSize(700, 700);
		f.setLocation(10, 10);
		f.add(area);
		f.setVisible(true);
		return area;
	}
	  /* inserisce nuovo cerchio alla posizione indicata e ridisegna */
	  public void insertBinario(Binario bin)
	  {	//bin.ricalcolaPosizioneGiunti();
		  binario_list[bin.id]=bin;
		    
	  }
}