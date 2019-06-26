package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ferrovia.Binario;
import ferrovia.BinarioCurvoSemplice;
import ferrovia.BinarioDritto;

class BinarioTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testAggencia() {
		
		Binario primo = new BinarioDritto(0);
		Binario secondo = new BinarioDritto(1);
		primo.agganciaAl(secondo);
		assertTrue(secondo.successivo == primo);
	}
	
	@Test
	void testAggenciaLink() {
		
		Binario primo = new BinarioDritto(0);
		Binario secondo = new BinarioDritto(1);
		primo.agganciaAl(secondo);
		assertTrue(secondo == primo.precedente);
	}

	@Test
	void testAggenciaGiuntiPosX() {
		
		Binario nuovo = new BinarioDritto(0);
		Binario vecchio = new BinarioDritto(1);
		nuovo.agganciaAl(vecchio);
		assertTrue(nuovo.giuntoFemmina.posX == vecchio.giuntoMaschio.posX);
	}
	
	@Test
	void testAggenciaGiuntiInclinazioneDritto() {
		
		Binario nuovo = new BinarioDritto(0);
		Binario vecchio = new BinarioDritto(1);
		vecchio.inclinazione=45;
		nuovo.agganciaAl(vecchio);
		assertTrue(nuovo.inclinazione == vecchio.inclinazione);
	}
	@Test
	void testAggenciaGiuntiInclinazioneCurvo() {
		
		Binario nuovo = new BinarioDritto(0);
		Binario vecchio = new BinarioCurvoSemplice(1);
		vecchio.inclinazione=90;
		nuovo.agganciaAl(vecchio);
		assertTrue(nuovo.inclinazione == (vecchio.inclinazione+270)%360);
	}
	
	@Test
	void testSgancia() {
		
		Binario nuovo = new BinarioDritto(0);
		Binario vecchio = new BinarioCurvoSemplice(1);
		vecchio.inclinazione=90;
		nuovo.agganciaAl(vecchio);
		
		nuovo.sgancia();
		assertTrue(nuovo.inclinazione == 0);
		assertTrue(nuovo.precedente == null);
		assertTrue(vecchio.successivo == null);
	}	
}
