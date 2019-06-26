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
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSelezionaBinarioLibero() {
		Hashtable configurazioneAttuale = Risolutore.inizializzaBinari(10,10);
		Binario primo = Risolutore.getPrimoBinario(configurazioneAttuale);
		
		Binario binario = Risolutore.selezionaBinarioLibero(configurazioneAttuale,primo);
		assertTrue(binario.libero == true);
	}

	@Test
	void testInizializzaBinariSize() {
		Hashtable configurazioneAttuale = Risolutore.inizializzaBinari( 10,10);
		assertTrue(configurazioneAttuale.size() == 20);
	}

	@Test
	void testInizializzaBinarigetlibero() {
		Hashtable configurazioneAttuale = Risolutore.inizializzaBinari(10,10);
		int max = 20;
		for (int i=1; i<=max;i++) {
			Binario binario = (Binario)(configurazioneAttuale.get(""+i));
			assertTrue(binario.libero == true);	
		}  
		
	}
	
	@Test
	void testGetLiberoByClass_Dritto() {
		Hashtable configurazioneAttuale = Risolutore.inizializzaBinari(2,4);
		
		Binario b = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioDritto.class);
		
		assert(b.getClass()==BinarioDritto.class); // TODO
	}

	@Test
	void testGetLiberoByClass_Curvo() {
		Hashtable configurazioneAttuale = Risolutore.inizializzaBinari(2,4);
		
		Binario b = Risolutore.getBinarioLibero(configurazioneAttuale,BinarioCurvoSemplice.class);
		
		assert(b.getClass()==BinarioCurvoSemplice.class); // TODO
	}
	
	@Test
	void testFine() {
		Hashtable configurazioneAttuale = Risolutore.inizializzaBinari(2,4);
		
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
		
		boolean finito = Risolutore.fine(configurazioneAttuale);
		assert(finito==true); 
	}
	
	//@Test
	void testCercaSoluzione() {
		fail("Not yet implemented"); // TODO
	}

}
