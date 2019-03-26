
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate kp;
    
    public KassapaateTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.kp = new Kassapaate();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void alussaOikeaRahamaara() {
        assertEquals(100000, kp.kassassaRahaa());
    }
    
    @Test
    public void alussaOikeaMaaraEdullisia() {
        assertEquals(0, kp.edullisiaLounaitaMyyty());
    }
    @Test
    public void alussaOikeaMaaraMaukkaasti() {
        assertEquals(0, kp.maukkaitaLounaitaMyyty());
    }
    @Test
    public void kateisOstoMaksuRiittavaEdullisesti() {
        kp.syoEdullisesti(300);
        assertEquals(100240, kp.kassassaRahaa());
    }
    @Test
    public void kateisOstoMaksuRiittavaMaukkaasti() {
        kp.syoMaukkaasti(500);
        assertEquals(100400, kp.kassassaRahaa());
    }
    @Test
    public void kateisOstoMaksuRiittavaVaihtorahaEdullisesti() {
        assertEquals(60, kp.syoEdullisesti(300));
    }
    @Test
    public void kateisOstoMaksuRiittavaVaihtorahaMaukkaasti() {
        assertEquals(100, kp.syoMaukkaasti(500));
    }
    @Test
    public void kateisOstoMaksuRiittavaLounasmaaraKasvaaEdullisesti() {
        kp.syoEdullisesti(300);
        assertEquals(1, kp.edullisiaLounaitaMyyty());
    }
    @Test
    public void kateisOstoMaksuRiittavaLounasmaaraKasvaaMaukkaasti() {
        kp.syoMaukkaasti(500);
        assertEquals(1, kp.maukkaitaLounaitaMyyty());
    }
    
    
    @Test
    public void kateisOstoMaksuEiRiittavaEdullisesti() {
        kp.syoEdullisesti(200);
        assertEquals(100000, kp.kassassaRahaa());
    }
    @Test
    public void kateisOstoMaksuEiRiittavaMaukkaasti() {
        kp.syoMaukkaasti(300);
        assertEquals(100000, kp.kassassaRahaa());
    }
    @Test
    public void kateisOstoMaksuEiRiittavaVaihtorahaEdullisesti() {
        assertEquals(200, kp.syoEdullisesti(200));
    }
    @Test
    public void kateisOstoMaksuEiRiittavaVaihtorahaMaukkaasti() {
        assertEquals(300, kp.syoMaukkaasti(300));
    }
    @Test
    public void kateisOstoMaksuEiRiittavaLounasmaaraKasvaaEdullisesti() {
        kp.syoEdullisesti(200);
        assertEquals(0, kp.edullisiaLounaitaMyyty());
    }
    @Test
    public void kateisOstoMaksuEiRiittavaLounasmaaraKasvaaMaukkaasti() {
        kp.syoMaukkaasti(300);
        assertEquals(0, kp.maukkaitaLounaitaMyyty());
    }


    @Test
    public void korttiOstoMaksuRiittavaEdullisesti() {
        Maksukortti kortti = new Maksukortti(1000);
        assertEquals(true, kp.syoEdullisesti(kortti));
    }
    @Test
    public void korttiOstoMaksuRiittavaMaukkaasti() {
        Maksukortti kortti = new Maksukortti(1000);
        assertEquals(true, kp.syoMaukkaasti(kortti));
    }
    @Test
    public void korttiOstoMaksuRiittavaKortinSaldoMuuttuuEdullisesti() {
        Maksukortti kortti = new Maksukortti(1000);
        kp.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }
    @Test
    public void korttiOstoMaksuRiittavaKortinSaldoMuuttuuMaukkaasti() {
        Maksukortti kortti = new Maksukortti(1000);
        kp.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }
    @Test
    public void korttiOstoMaksuRiittavaLounasmaaraKasvaaEdullisesti() {
        Maksukortti kortti = new Maksukortti(1000);
        kp.syoEdullisesti(kortti);
        assertEquals(1, kp.edullisiaLounaitaMyyty());
    }
    @Test
    public void korttiOstoMaksuRiittavaLounasmaaraKasvaaMaukkaasti() {
        Maksukortti kortti = new Maksukortti(1000);
        kp.syoMaukkaasti(kortti);
        assertEquals(1, kp.maukkaitaLounaitaMyyty());
    }
    @Test
    public void korttiOstoMaksuRiittavaKassaEiMuutuEdullisesti() {
        Maksukortti kortti = new Maksukortti(1000);
        kp.syoEdullisesti(kortti);
        assertEquals(100000, kp.kassassaRahaa());
    }
    @Test
    public void korttiOstoMaksuRiittavaKassaEiMuutuMaukkaasti() {
        Maksukortti kortti = new Maksukortti(1000);
        kp.syoMaukkaasti(kortti);
        assertEquals(100000, kp.kassassaRahaa());
    }
    
    
    @Test
    public void korttiOstoMaksuEiRiittavaEdullisesti() {
        Maksukortti kortti = new Maksukortti(200);
        assertEquals(false, kp.syoEdullisesti(kortti));
    }
    @Test
    public void korttiOstoMaksuEiRiittavaMaukkaasti() {
        Maksukortti kortti = new Maksukortti(200);
        assertEquals(false, kp.syoMaukkaasti(kortti));
    }
    @Test
    public void korttiOstoMaksuEiRiittavaKortinSaldoMuuttuuEdullisesti() {
        Maksukortti kortti = new Maksukortti(200);
        kp.syoEdullisesti(kortti);
        assertEquals(200, kortti.saldo());
    }
    @Test
    public void korttiOstoMaksuEiRiittavaKortinSaldoMuuttuuMaukkaasti() {
        Maksukortti kortti = new Maksukortti(200);
        kp.syoMaukkaasti(kortti);
        assertEquals(200, kortti.saldo());
    }
    @Test
    public void korttiOstoMaksuEiRiittavaLounasmaaraKasvaaEdullisesti() {
        Maksukortti kortti = new Maksukortti(200);
        kp.syoEdullisesti(kortti);
        assertEquals(0, kp.edullisiaLounaitaMyyty());
    }
    @Test
    public void korttiOstoMaksuEiRiittavaLounasmaaraKasvaaMaukkaasti() {
        Maksukortti kortti = new Maksukortti(200);
        kp.syoMaukkaasti(kortti);
        assertEquals(0, kp.maukkaitaLounaitaMyyty());
    }
    @Test
    public void korttiOstoMaksuEiRiittavaKassaEiMuutuEdullisesti() {
        Maksukortti kortti = new Maksukortti(200);
        kp.syoEdullisesti(kortti);
        assertEquals(100000, kp.kassassaRahaa());
    }
    @Test
    public void korttiOstoMaksuEiRiittavaKassaEiMuutuMaukkaasti() {
        Maksukortti kortti = new Maksukortti(200);
        kp.syoMaukkaasti(kortti);
        assertEquals(100000, kp.kassassaRahaa());
    }
    
    @Test
    public void rahaaLadatessaKortinSaldoMuuttuu() {
        Maksukortti kortti = new Maksukortti(200);
        kp.lataaRahaaKortille(kortti, 200);
        assertEquals(400, kortti.saldo());  
    }
    @Test
    public void rahaaLadatessaNegatiivisestiKortinSaldoEiMuutu() {
        Maksukortti kortti = new Maksukortti(200);
        kp.lataaRahaaKortille(kortti, -200);
        assertEquals(200, kortti.saldo());  
    }
    @Test
    public void rahaaLadatessaLiikaaKassaKasvaa() {
        Maksukortti kortti = new Maksukortti(200);
        kp.lataaRahaaKortille(kortti, 200);
        assertEquals(100200, kp.kassassaRahaa());  
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
