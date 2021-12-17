package ferrovia;


import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import javax.swing.JFrame;

public class Area extends java.awt.Canvas{
	
	private static Area area =null;
	private int size;
	
	public void paint(Graphics g) {
		g.setColor(Color.red);
	
	    for (int ii=1; ii<=size; ii++) {
	    	//g.setColor(Color.blue);
		    Binario bin=(Binario)(Risolutore.getConfigurazione().get(""+ii));
	        if (!bin.libero ) bin.disegna(g);

	    }
	}
	
	public static Area getIstanza() {
		if (area != null) return area;
		
		
		JFrame f = new JFrame("Disegno");
		area = new Area();
		area.size=Risolutore.getConfigurazione().size();
		f.setSize(700, 700);
		f.setLocation(10, 10);
		f.add(area);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		return area;
	}

}