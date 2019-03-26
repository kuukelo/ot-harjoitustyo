package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    @Test
    public void kortinSaldoAlussaOikein2() {
        assertEquals(1000, kortti.saldo());
    }
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 20.0", kortti.toString());
    }
    @Test
    public void otaRahaaToimiiJosTarpeeksiRahaa() {
        kortti.otaRahaa(500);
        assertEquals("saldo: 5.0", kortti.toString());
    }
    @Test
    public void saldoEiMuutuJosRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(2000);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    @Test
    public void otaRahaaPalauttaaTrue() {
        assertEquals(true, kortti.otaRahaa(500));
    }
    @Test
    public void otaRahaaPalauttaaFalse() {
        assertEquals(false, kortti.otaRahaa(2000));
    }
}
