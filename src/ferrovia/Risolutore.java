package ferrovia;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Component;
import java.awt.Toolkit;
import java.util.Hashtable;

public class Risolutore {
	private static int _numCurvi = 8;
	private static int _numDritti = 2;

	static double fattrad = 3.1415 / 180;
	
	static Binario ultimoSelezionato = null;
	
	private static Binario primoBinario = null;
	private static Hashtable configurazione;
	private static Area areaDisegno;

	//private static ;// area disegno

	public static void main(String[] args) {
		Hashtable configurazioneAttuale  = init();
		start(configurazioneAttuale);
	}

	public static Hashtable init() {
		
		configurazione = inizializzaBinari(10,20,20,90);
		areaDisegno = Area.getIstanza();
		return configurazione;
	}

	


	private static void start(Hashtable configurazioneAttuale) {

		while(true)
		attaccaNextBinario(configurazioneAttuale);
		
	}

	private static void attaccaNextBinario(Hashtable configurazioneAttuale) {
		 
		areaDisegno.repaint();
		
		Binario testa = getTestaTracciato(configurazioneAttuale);
		Binario candidato  = selezionaBinarioLibero(configurazioneAttuale,testa);
		if(candidato == null) backTrack(configurazioneAttuale, testa);
		else {
			candidato.agganciaAl(testa);
			if (soluzioneBuona(configurazioneAttuale)) {
				//attaccaNextBinario(configurazioneAttuale);
			}
			else {
				candidato.sgancia();
				testa.addNonBuoni(candidato);
				//attaccaNextBinario(configurazioneAttuale);
			}
		}	
		
	}


	public static boolean fine(Hashtable configurazioneAttuale) {
		Binario primo = getPrimoBinario(configurazioneAttuale);
		Binario ultimo = getTestaTracciato(configurazioneAttuale);
		if (Math.round(primo.giuntoFemmina.posX) == Math.round(ultimo.giuntoMaschio.posX) &&
				Math.round(primo.giuntoFemmina.posY) == Math.round(ultimo.giuntoMaschio.posY)) {
			if (Math.round(ultimo.giuntoMaschio.inclinGiunto - primo.inclinazione) == 0){
				return true;	
			} else {
				System.out.println("ang primo:"+primo.inclinazione);
				System.out.println("ang ultimo:"+ultimo.giuntoMaschio.inclinGiunto);
			}
		}
			
		return false;
		
	}		

	private static boolean soluzioneBuona(Hashtable configurazioneAttuale) {
		if(numeroLiberi(configurazioneAttuale)>10) return true;
		
		if (selezionaBinarioLibero(configurazioneAttuale, primoBinario) == null) {
			if( fine(configurazioneAttuale)) {
				//System.exit(0);
				for (int i = 0; i < 10; i++) {
					i=i-1;
				} 
			}
			else return false;
		}
		
		
		int lunghezzaMedia = 20;
		if(distanzaTestaPrimo(configurazioneAttuale)>numeroLiberi(configurazioneAttuale)*lunghezzaMedia) {
			return false;
		}
		
		return true;
	}		

	private static int numeroLiberi(Hashtable configurazioneAttuale) {
		int liberi=0;
		int size = configurazioneAttuale.size();
		for(int i=1 ; i<=size;i++) {
			Binario bi = (Binario)configurazioneAttuale.get(""+i);
			if(bi.libero) liberi++;
		}
		
		return liberi;
	}

	private static int distanzaTestaPrimo(Hashtable configurazioneAttuale) {
		Binario testa = getTestaTracciato(configurazioneAttuale);
		Binario primo = getPrimoBinario(configurazioneAttuale);
		
		int a2 = (int) Math.pow( (testa.giuntoMaschio.posX-primo.giuntoFemmina.posX),2);
		int b2 = (int) Math.pow( (testa.giuntoMaschio.posY-primo.giuntoFemmina.posY),2);
		
		int rispo=  (int)(Math.sqrt(a2+b2));
		
		//System.out.println(rispo);
		return rispo;
	}

	private static void backTrack(Hashtable configurazioneAttuale, Binario testa) {
		testa.sgancia();
		//attaccaNextBinario(configurazioneAttuale); 

	}			

	public static Binario getTestaTracciato(Hashtable configurazioneAttuale) {
		Binario testa = getPrimoBinario(configurazioneAttuale);
		while (testa.successivo != null) {
			testa = testa.successivo;
		}
		//System.out.println("testa="+testa.id);
		return testa;
	}			


	public static Binario selezionaBinarioLibero(Hashtable configurazioneAttuale, Binario t) {
		int max = configurazioneAttuale.size();
		for (int i=1; i<=max;i++) {
			Binario binario = (Binario)(configurazioneAttuale.get(""+i));
			if (t.listaNonBuoni.containsKey(""+binario.getClass()+binario.inclinazioneGiunto)) continue;
			if (binario.libero) return binario;	
		}  
		return null;
	}


	public static Binario getBinarioLibero(Hashtable configurazioneAttuale, Class tipo) {
		int max = configurazioneAttuale.size();
		for (int i=1; i<=max;i++) {
			Binario binario = (Binario)(configurazioneAttuale.get(""+i));
			if (binario.getClass() != tipo) continue;
			if (binario.libero) return binario;	
		}  
		return null;
	}



	public static Hashtable inizializzaBinari2(int numDritti, int numCurvi, double angolo) {
		Hashtable configurazione = new Hashtable();
		_numDritti = numDritti;
		_numCurvi = numCurvi;
		int _numBinari = _numDritti+_numCurvi;
		int d=0;
		while(d<_numDritti){
			int rand=1+(int)(Math.random()*_numBinari);
			if(!configurazione.containsKey(""+rand)){
				Binario b = new BinarioDritto(rand);
		
				configurazione.put("" + rand, b);
				d++;
			}
		}
		d=0;
		while(d<_numCurvi){
			
			int rand=1+(int)(Math.random()*_numBinari);

			if(!configurazione.containsKey(""+rand)){
				Binario b = new BinarioCurvoSemplice(rand,angolo);
				configurazione.put("" + rand, b);
				d++;
			}
		}
		

		
		
		return configurazione;
	}
	
	public static Hashtable inizializzaBinari(int numDritti, int numCurviD,int numCurviS, double angolo) {
		Hashtable configurazione = new Hashtable();
		_numDritti = numDritti;
		_numCurvi = numCurviS+numCurviD;
		int _numBinari = _numDritti+_numCurvi;
		int d=0;
		while(d<_numDritti){
			int rand=1+(int)(Math.random()*_numBinari);
			if(!configurazione.containsKey(""+rand)){
				Binario b = new BinarioDritto(rand);
		
				configurazione.put("" + rand, b);
				d++;
			}
		}
		d=0;
		while(d<numCurviS){
			
			int rand=1+(int)(Math.random()*_numBinari);

			if(!configurazione.containsKey(""+rand)){
				Binario b = new BinarioCurvoSemplice(rand,angolo);
				configurazione.put("" + rand, b);
				d++;
			}
		}
		
		d=0;
		while(d<numCurviD){
			
			int rand=1+(int)(Math.random()*_numBinari);

			if(!configurazione.containsKey(""+rand)){
				Binario b = new BinarioCurvoSemplice(rand,360-angolo);
				
				configurazione.put("" + rand, b);
				d++;
			}
		}
		
		
		return configurazione;
	}

	/**
	 * @param configurazione
	 * @return
	 */
	public static Binario getPrimoBinario(Hashtable configurazioneAttuale) {
		if (primoBinario!= null) return primoBinario;
		
		reset(configurazioneAttuale);
		primoBinario = (Binario)configurazioneAttuale.get("1");
		primoBinario.libero=false;
		return primoBinario;
	}
	
	/**
	 * @param configurazione
	 * @return
	 */
	public static Binario getPrimoBinario(Hashtable configurazioneAttuale, Class tipo) {
		if (primoBinario!= null) return primoBinario;
		
		reset(configurazioneAttuale);
		primoBinario = getBinarioLibero(configurazioneAttuale, tipo);
		primoBinario.libero=false;
		
		//System.out.println("primo="+primoBinario.id);
		return primoBinario;
	}


	private static void reset(Hashtable configurazioneAttuale) {
		int numBinari  = configurazioneAttuale.size();
		for(int k = 1;k<=numBinari;k++){
			int x=k;
			
			Binario prossimo = (Binario)(configurazioneAttuale.get(""+x));
			prossimo.libero=true;
			prossimo.successivo=null;
			prossimo.precedente=null;
			
		}
	}

	public static Hashtable getConfigurazione() {
		if (configurazione == null)
			
		configurazione = init();
		return configurazione;
	}
}
