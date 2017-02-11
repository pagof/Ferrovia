package ferrovia;

import java.awt.Toolkit;
import java.util.Hashtable;

public class Risolutore {
	private static final int _numCurvi = 10;
	private static final int _numDritti = 8;
	private static final int _numBinari = _numDritti+_numCurvi;
	static double fattrad = 3.1415 / 180;
	static Hashtable configurazioneAttuale;
	static Binario ultimoSelezionato = null;

	private static Area areaDisegno;// area disegno

	public static void main(String[] args) {
		// esempio();
		areaDisegno = Area.getIstanza();

		configurazioneAttuale = inizializzaBinari();
		Binario binarioIniziale = selezionaBinario(configurazioneAttuale, null);
		boolean trovato = false;
		Binario prossimo = binarioIniziale;
		while(prossimo != null){
			prossimo = agganciaBinario(prossimo);	
		}
		
		

	}

	private static void esempio() {
		// TODO Auto-generated method stub
		int Fx = 10;
		int Fy = 20;
		int Mx = Fx + (int) (Math.cos(fattrad * 270) * 30);
		int My = Fy + (int) (Math.sin(fattrad * 270) * 30);

		System.out.println("" + Mx);
		System.out.println("" + My);
		System.out.println("" + (int) (Math.cos(fattrad * 270) * 30));

	}

	
	
	public static Binario agganciaBinario(Binario attuale) {

		areaDisegno.repaint();
		Binario prossimo = null;
		for(int k = 1;k<=_numBinari;k++){
			int x=k;
			
			
			
			prossimo = (Binario)(configurazioneAttuale.get(""+x));
			if(attuale.listaOut.containsKey(""+prossimo.forma)) {
				prossimo =null;
				continue;		
			}
			
			if(attuale.listaOut.containsKey(""+prossimo.forma)) {
				prossimo =null;
				continue;		
			}
			
		if(prossimo == null || prossimo.selezionato)  
				prossimo = null;
					
		else{
				attuale.listaOut.put(""+prossimo.forma,prossimo);
				prossimo.selezionato=true;
				break;
			}
		}//fine ciclo
		

		if (prossimo != null) {
				//precedente.ultimoAgganciato=prossimo.id;
				prossimo.precedente = attuale;
				prossimo.femmine[0].posX = attuale.maschi[0].posX;
				prossimo.femmine[0].posY = attuale.maschi[0].posY;
				prossimo.inclinazione = (attuale.inclinazione + attuale.maschi[0].inclinGiunto) % Binario.angolo_giro;
	
	
				prossimo.ricalcolaPosizioneGiunti();	
				
				Risolutore.soluzione(prossimo);
				return (prossimo);
		}	
	
		//soluzione(attuale);
		
		if (prossimo == null) {
			Binario ripartenza = smontaBinario(configurazioneAttuale,
					attuale);
			System.out.println("back-track:" + attuale.nome);
			//agganciaBinario(ripartenza);
			
		}
		return (attuale.precedente);
	}

	private static boolean soluzione(Binario b) {

		Binario primo = (Binario)(configurazioneAttuale.get("1"));
		int cont=0;
		for(int k = 1;k<=_numBinari;k++){
			Binario prossimo = (Binario)(configurazioneAttuale.get(""+k));
			
			if(prossimo.selezionato) {
				if(verificaIncrocio(prossimo)) 
					cont=0;
				cont++;
			}
		}	
		
		
		if (cont<_numBinari) 
			return false;
		
		if (Math.abs(b.maschi[0].posX-primo.femmine[0].posX)<=20)
			if (Math.abs(b.maschi[0].posY-primo.femmine[0].posY)<=20)
				if ((b.maschi[0].inclinGiunto+b.inclinazione)%Binario.angolo_giro ==primo.inclinazione)
				{
				Toolkit.getDefaultToolkit().beep();
				Toolkit.getDefaultToolkit().beep();
				Toolkit.getDefaultToolkit().beep();
				//areaDisegno.repaint();
				return true;
				}
		
		return false;
	}

	
	private static boolean verificaIncrocio(Binario b) {

		Binario primo = (Binario)(configurazioneAttuale.get("1"));
		primo.ricalcolaPosizioneGiunti();
		int cont=0;
		for(int k = 1;k<=_numBinari;k++){
			Binario prossimo = (Binario)(configurazioneAttuale.get(""+k));
			if(prossimo.selezionato && prossimo!=b) {
				if (Math.abs(b.maschi[0].posY-prossimo.maschi[0].posY)<=20)
				if (Math.abs(b.maschi[0].posX-prossimo.maschi[0].posX)<=20)
				
								return true;
				
			}
		}	
		return (false);
	}

	public static void agganciaBinarioOLD(Binario precedente) {
		int ultimo=precedente.ultimoAgganciato;
		Binario prossimo = selezionaBinario(configurazioneAttuale, precedente);
		
		
		if (prossimo == null) {
			Binario ripartenza = smontaBinario(configurazioneAttuale,
					precedente);
			System.out.println("backtrack:" + precedente.nome);
			agganciaBinario(ripartenza);
			return;
		}
		precedente.ultimoAgganciato=prossimo.id;
		prossimo.precedente = precedente;
		prossimo.femmine[0].posX = precedente.maschi[0].posX;
		prossimo.femmine[0].posY = precedente.maschi[0].posY;

		prossimo.inclinazione = (precedente.inclinazione + precedente.maschi[0].inclinGiunto) % Binario.angolo_giro;
		
		// System.out.println(precedente);
		// System.out.println(prossimo);
		for (long w = 0; w < 10000000; w++) {
			long x = w;
		}
		areaDisegno.repaint();
		
		agganciaBinario(prossimo);
	}

	private static Binario smontaBinario(Hashtable configurazioneAttuale2,
			Binario ultimo) {
		// oltre a rimuoversi
		// rimuove tutti i successori
		// e return predecessore
		ultimo.rovescia();
		ultimo.listaOut=new Hashtable();
		ultimo.femmine[0].posX = 10;
		ultimo.femmine[0].posY = 50;
		ultimo.inclinazione = 0;
		ultimo.selezionato = false;
		ultimoSelezionato = ultimo;
		//areaDisegno.repaint();
		return ultimo.precedente;
	}

	public static Binario selezionaBinario(Hashtable configurazione,
			Binario precedente) {

		Binario bin = null;
		if (precedente != null) {
			Giunto[] insiemeGiuntiM = precedente.maschi;

			for (int k = 1; k <= _numBinari; k++) {
				bin = (Binario) configurazioneAttuale.get("" + k);

				if (bin.selezionato == false
						&& !precedente.listaOut.contains(bin)) {
					if (bin == ultimoSelezionato)
						if (bin.ribaltato) {// l'ho provato in entrambi i versi
							precedente.listaOut.put(bin.nome,bin);
							bin.rovescia();// lo raddrizza per il futuro
							return null;
						} else {
							bin.rovescia();
							bin.svuotalistaOut(bin.listaOut);

						}
					break;
				}

			}

		} else {
			bin = (Binario) configurazione.get("1"); // prende il primo
		}
		
		if (bin.selezionato == true)
			return null;
		if (precedente != null && precedente.listaOut.contains(bin))
			return null;
		bin.selezionato = true;
		bin.precedente = precedente;
		// System.out.println("selezionato:"+bin);
		// stampaConfig();
		//areaDisegno.repaint();
		return bin;

	}

	private static void stampaConfig() {
		System.out.println("configurazioneAttuale");
		Binario bin = null;
		for (int k = 1; k <= _numBinari; k++) {
			bin = (Binario) configurazioneAttuale.get("" + k);
			if (bin.selezionato)
				bin.stampa();
		}
	}

	public static Hashtable inizializzaBinari() {
		Hashtable configurazione = new Hashtable();
		int d=0;
		while(d<_numDritti){
			int rand=1+(int)(Math.random()*_numBinari);
			if(!configurazione.containsKey(""+rand)){
				Binario b = new BinarioDritto(rand);
				
				configurazione.put("" + rand, b);
				areaDisegno.insertBinario(b);
				d++;
			}
		}
		d=0;
		while(d<_numCurvi){
			int rand=1+(int)(Math.random()*_numBinari);
			if(!configurazione.containsKey(""+rand)){
				Binario b = new BinarioCurvoSemplice(rand);
				int rov=1+(int)(Math.random()*2);
				configurazione.put("" + rand, b);
				if(rov==1) b.rovescia();
				areaDisegno.insertBinario(b);
				d++;
			}
		}
		
		/*
		for (int i = 1; i <= _numDritti ; i++) {
			Binario b = new BinarioDritto(i);
			configurazione.put("" + i, b);
			areaDisegno.insertBinario(b);
		}
		for (int j = 1; j <= _numCurvi ; j++) {
			Binario b = new BinarioCurvoSemplice(j+_numDritti );
			configurazione.put("" + (j+_numDritti) , b);
			areaDisegno.insertBinario(b);
		}
		*/

		return configurazione;
	}
	
	public static void cercaSoluzione(){
		Hashtable stato = inizializzaBinari();
		
		
		
		
		
	}
}
