package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Hashtable;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ferrovia.Area;
import ferrovia.Binario;
import ferrovia.BinarioCurvoSemplice;
import ferrovia.BinarioDritto;
import ferrovia.Risolutore;
import jdk.nashorn.internal.runtime.regexp.joni.Config;

class RisolutoreTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	//@Test
	void testAggancia() {
		fail("Not yet implemented"); // TODO  add test
	}

	@Test
	void testSelezionaBinarioLibero() {
		Hashtable configurazioneAttuale = Risolutore.inizializzaBinari(5,10,10,90);
		Binario primo = Risolutore.getPrimoBinario(configurazioneAttuale);
		
		Binario binario = Risolutore.selezionaBinarioLibero(configurazioneAttuale,primo);
		assertTrue(binario.libero == true);
	}

	@Test
	void testInizializzaBinariSize() {
		Hashtable configurazioneAttuale = Risolutore.inizializzaBinari( 5,10,10,90);
		assertTrue(configurazioneAttuale.size() == 25);
	}

	@Test
	void testInizializzaBinarigetlibero() {
		Hashtable configurazioneAttuale = Risolutore.inizializzaBinari(5,10,10,90);
		int max = 20;
		for (int i=1; i<=max;i++) {
			Binario binario = (Binario)(configurazioneAttuale.get(""+i));
			assertTrue(binario.libero == true);	
		}  
		
	}
	
	@Test
	void testGetLiberoByClass_Dritto() {
		Hashtable configurazioneAttuale = Risolutore.inizializzaBinari(4,2,4,90);
		Binario b = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioDritto.class);
		assertEquals(BinarioDritto.class, b.getClass());
	}

	@Test
	void testGetLiberoByClass_Curvo() {
		Hashtable configurazioneAttuale = Risolutore.inizializzaBinari(1,2,4,90);
		Binario b = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
		assertEquals(BinarioCurvoSemplice.class , b.getClass());
	}
	@Test
	void testAgganciaGiunto() {
		Hashtable configurazioneAttuale = Risolutore.inizializzaBinari(1,2,4,90);
		
		Binario d1 = Risolutore.getPrimoBinario(configurazioneAttuale,BinarioDritto.class);
		Binario c1 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
		c1.agganciaAl(d1);
		assertEquals(c1.giuntoFemmina.posX,d1.giuntoMaschio.posX);
		assertEquals(c1.giuntoFemmina.posY,d1.giuntoMaschio.posY);
	}
	
	@Test
	void testGetTesta() {
		Hashtable configurazioneAttuale = Risolutore.inizializzaBinari(2,4,90,90);
		
		Binario d1 = Risolutore.getPrimoBinario(configurazioneAttuale,BinarioDritto.class);
		Binario c1 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
		
		c1.agganciaAl(d1);
		Binario c2 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
	    c2.agganciaAl(c1);
	    Binario d2 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioDritto.class);
		d2.agganciaAl(c2);
		Binario c3 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
		c3.agganciaAl(d2);
		Binario c4 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
		c4.agganciaAl(c3);
		
		
		Binario ultimo = Risolutore.getTestaTracciato(configurazioneAttuale);

		assertEquals (c4 , ultimo); 
		
	}
	
	@Test
	void testTestaInizioPosX() {
		Hashtable configurazioneAttuale = Risolutore.inizializzaBinari2(2,4,90);
		
		Binario d1 = Risolutore.getPrimoBinario(configurazioneAttuale,BinarioDritto.class);
		Binario c1 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
		
		c1.agganciaAl(d1);
		Binario c2 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
	    c2.agganciaAl(c1);
	    Binario d2 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioDritto.class);
		d2.agganciaAl(c2);
		Binario c3 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
		c3.agganciaAl(d2);
		Binario c4 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
		c4.agganciaAl(c3);
		//d1.agganciaAl(c4);
		
		
		Binario testa = Risolutore.getTestaTracciato(configurazioneAttuale);
		Binario inizio = Risolutore.getPrimoBinario(configurazioneAttuale);
		assertEquals (Math.round(inizio.giuntoFemmina.posX) , Math.round(testa.giuntoMaschio.posX)); 
		
	}

	@Test
	void testFine() {
		Hashtable configurazioneAttuale = Risolutore.inizializzaBinari2(2,4,90);
		
		Binario d1 = Risolutore.getPrimoBinario(configurazioneAttuale,BinarioDritto.class);
		Binario c1 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
		
		c1.agganciaAl(d1);
		Binario c2 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
	    c2.agganciaAl(c1);
	    Binario d2 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioDritto.class);
		d2.agganciaAl(c2);
		Binario c3 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
		c3.agganciaAl(d2);
		Binario c4 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
		c4.agganciaAl(c3);
		//d1.agganciaAl(c4);
		
		boolean finito = Risolutore.fine(configurazioneAttuale);
		assertEquals(true,finito); 
	}
	
	@Test
	void testFineBig() {
		Hashtable configurazioneAttuale = Risolutore.inizializzaBinari2(4,8,45);
		
		Binario d1 = Risolutore.getPrimoBinario(configurazioneAttuale,BinarioDritto.class);
		Binario c1 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
		c1.agganciaAl(d1);
		Binario c2 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
	    c2.agganciaAl(c1);
	    
	    Binario d2 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioDritto.class);
		d2.agganciaAl(c2);
		Binario c3 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
		
		c3.agganciaAl(d2);
		Binario c4 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
	    c4.agganciaAl(c3);
	    
	    Binario d3 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioDritto.class);
		d3.agganciaAl(c4);
		
		Binario c5 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
		c5.agganciaAl(d3);
		Binario c6 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
		c6.agganciaAl(c5);
		
		
		Binario d4 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioDritto.class);
		d4.agganciaAl(c6);
		Binario c7 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
		
		c7.agganciaAl(d4);
		Binario c8 = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
	    c8.agganciaAl(c7);
	    
		
		boolean finito = Risolutore.fine(configurazioneAttuale);
		assertEquals(true,finito); 
	}
	
	
	//@Test
	void testCercaSoluzione() {
		fail("Not yet implemented"); // TODO  add test
	}

}
