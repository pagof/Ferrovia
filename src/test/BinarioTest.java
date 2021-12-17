package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ferrovia.Binario;
import ferrovia.BinarioCurvoSemplice;
import ferrovia.BinarioDritto;
import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

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
		vecchio.setInclinazione(45);
		nuovo.agganciaAl(vecchio);
		assertTrue(nuovo.getInclinazione() == vecchio.getInclinazione());
	}
	@Test
	void testAggenciaGiuntiInclinazioneCurvo() {
		
		Binario nuovo = new BinarioDritto(0);
		
		Binario vecchio = new BinarioCurvoSemplice(1,90);
		vecchio.setInclinazione(13);
		
		nuovo.agganciaAl(vecchio);
		assertEquals(nuovo.getInclinazione() , (vecchio.getInclinazione() +vecchio.giuntoMaschio.inclinGiunto)%360);
	}
	
	@Test
	void verificaDrittoPosizioneStartGiuntoMaschioinclinatoZeroX() {
		
		BinarioDritto nuovo = new BinarioDritto(0);
		nuovo.setInclinazione(0);
		assertEquals(nuovo.giuntoMaschio.posX, nuovo.giuntoFemmina.posX + nuovo.lunghezzaX);
	}

	@Test
	void verificaCurvoSetInclinazioneGiunto() {
		
		BinarioCurvoSemplice nuovo = new BinarioCurvoSemplice(1,90);
		nuovo.setInclinazione(10);
		assertEquals(nuovo.giuntoMaschio.inclinGiunto, (nuovo.getInclinazione() + nuovo.inclinazioneGiunto )%360	);
	}
	
	@Test
	void verificaCurvoSetInclinazioneGiunto279() {
		
		BinarioCurvoSemplice nuovo = new BinarioCurvoSemplice(1,90);
		nuovo.setInclinazione(279);
		assertEquals(nuovo.giuntoMaschio.inclinGiunto, (nuovo.getInclinazione() + nuovo.inclinazioneGiunto )%360	);
	}
	
	@Test
	void verificaCurvoPosizioneStartGiuntoMaschioinclinatoZeroX() {
		
		BinarioCurvoSemplice nuovo = new BinarioCurvoSemplice(1,90);
		nuovo.setInclinazione(0);
		assertEquals(nuovo.giuntoMaschio.posX,  nuovo.giuntoFemmina.posX + 
												nuovo.lunghezzaSezA*Math.cos(nuovo.getRadianti(nuovo.getInclinazione() ))+
												nuovo.lunghezzaSezB*Math.cos(nuovo.getRadianti(nuovo.giuntoMaschio.inclinGiunto)));
	}

	@Test
	void verificaCurvoPosizioneStartGiuntoMaschioinclinatoZ90X() {
		
		BinarioCurvoSemplice nuovo = new BinarioCurvoSemplice(1,90);
		nuovo.setInclinazione(90);
		assertEquals(nuovo.giuntoMaschio.posX,  nuovo.giuntoFemmina.posX + 
												nuovo.lunghezzaSezA*Math.cos(nuovo.getRadianti(nuovo.getInclinazione() ))+
												nuovo.lunghezzaSezB*Math.cos(nuovo.getRadianti(nuovo.giuntoMaschio.inclinGiunto)));
	}
	@Test
	void verificaCurvoPosizioneStartGiuntoMaschioinclinato180X() {
		
		BinarioCurvoSemplice nuovo = new BinarioCurvoSemplice(1,90);
		nuovo.setInclinazione(180);
		assertEquals(nuovo.giuntoMaschio.posX,  nuovo.giuntoFemmina.posX + 
												nuovo.lunghezzaSezA*Math.cos(nuovo.getRadianti(nuovo.getInclinazione() ))+
												nuovo.lunghezzaSezB*Math.cos(nuovo.getRadianti(nuovo.giuntoMaschio.inclinGiunto)));
	}
	
	@Test
	void verificaCurvoPosizioneStartGiuntoMaschioinclinato360() {
		
		BinarioCurvoSemplice nuovo = new BinarioCurvoSemplice(1,90);
		nuovo.setInclinazione(360);
		assertEquals(nuovo.giuntoMaschio.posX,  nuovo.giuntoFemmina.posX + 
												nuovo.lunghezzaSezA*Math.cos(nuovo.getRadianti(nuovo.getInclinazione() ))+
												nuovo.lunghezzaSezB*Math.cos(nuovo.getRadianti(nuovo.giuntoMaschio.inclinGiunto)));
	}
	
	@Test
	void verificaDrittoPosizioneStartGiuntoMaschioinclinatoZeroY() {
		
		BinarioDritto nuovo = new BinarioDritto(0);
		nuovo.setInclinazione(0);
		assertEquals(nuovo.giuntoMaschio.posY, nuovo.giuntoFemmina.posY );
	}

	@Test
	void verificaPosizioneStartGiuntoMaschioinclinato45() {
		
		BinarioDritto nuovo = new BinarioDritto(0);
		nuovo.setInclinazione(55);
		
		assertEquals(nuovo.giuntoMaschio.posX, Math.round( nuovo.giuntoFemmina.posX + nuovo.lunghezzaX*Math.cos(nuovo.getRadianti(nuovo.getInclinazione())) ));
	}

	@Test
	void verificaPosizioneStartGiuntoMaschioNuovaInvlinazione() {
		
		BinarioDritto nuovo = new BinarioDritto(0);
		nuovo.setInclinazione(45);

		assertEquals(nuovo.giuntoMaschio.posX, Math.round( nuovo.giuntoFemmina.posX + nuovo.lunghezzaX*Math.cos(nuovo.getRadianti(nuovo.getInclinazione())) ));
	}
	
	@Test
	void testSgancia() {
		
		Binario nuovo = new BinarioDritto(0);
		Binario vecchio = new BinarioCurvoSemplice(1,90);
		
		nuovo.agganciaAl(vecchio);
		
		nuovo.sgancia();
		assertEquals(nuovo.getInclinazione(), 0);
		assertNull(nuovo.precedente);
		assertNull(vecchio.successivo);
	}	
}
