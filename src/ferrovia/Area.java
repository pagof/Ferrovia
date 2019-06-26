package ferrovia;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Area extends Canvas {
	
	private static Area area =null;
	
	public void paint(Graphics g) {
		g.setColor(Color.red);
		int size=Risolutore.getConfigurazione().size();
	    for (int ii=1; ii<=size; ii++) {
	    	//g.setColor(Color.blue);
		    Binario bin=(Binario)(Risolutore.getConfigurazione().get(""+ii));
	        if (!bin.libero ) bin.disegna(g);

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

}