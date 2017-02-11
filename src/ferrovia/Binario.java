package ferrovia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;



public class Binario {
	static double fattrad=3.1415/180;
	public static int angolo_giro = 360;
	public int inclinazione =0;
	public int id =0;
	public String forma ="STANDARD";
	public int ultimoAgganciato =0;
	public static int newid =0;
	public Giunto[] maschi;
	public Giunto[] femmine;
	public boolean ribaltato = false;
	public String nome ="";//dritto, curvo, biforcatoIn, biforcatoOut
	Binario precedente = null;
	Binario successivo = null;
	@Override
	public String toString() {
		String sPrec;
		if (precedente==null) sPrec="nessuno";
		else sPrec=precedente.nome;
		return "------\nnome=" + nome+
		"\nincl:" + inclinazione +
		"\nincl M:" + maschi[0].inclinGiunto+
		"\n ribalT="+ribaltato+
		"\nprec:"+sPrec;
	}
	public boolean selezionato=false;//appartiene ad una soluzione
	public Hashtable listaOut=new Hashtable();;
	public Binario() {
		super();
		
		newid++;
		id=newid;
		// TODO Auto-generated constructor stub
	}
	public void stampa() {
		// TODO Auto-generated method stub
		
	}
	public void rovescia(){}
	public void ricalcolaPosizioneGiunti(){}
	public void disegna(Graphics g) {
		// SKELETON
		
	}
	public void svuotalistaOut(Hashtable listaOut2) {
		// TODO Auto-generated method stub
		
		Enumeration enu = listaOut2.keys();
		String chiave = "";
		Binario appo =null;
		while (enu.hasMoreElements()) {
			chiave = (String) enu.nextElement();
			appo = (Binario) listaOut2.get(chiave);
			appo.svuotalistaOut(appo.listaOut);
		}
		System.out.println("svuoto lista di "+this.nome);
		this.listaOut= new Hashtable();
		if (this.id == 5)
			System.out.println("svuoto lista di "+this.nome);
	}
	
}
