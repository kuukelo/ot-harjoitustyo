
# Harjoitustyö - Reseptitietokanta

## Dokumentaatio

[Määrittelydokumentti](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/m%C3%A4%C3%A4rittelydokumentti.md)

[Työaikakirjanpito](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/ty%C3%B6aikakirjanpito.md)

[Arkkitehtuuri](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Komentorivitoiminnot

### Testaus
Testit suoritetaan komennolla **mvn test**

Testikattavuusraportti luodaan komennolla **mvn jacoco:report**
  - Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html

Checkstyle tarkistukset suoritetaan komennolla **mvn jxr:jxr checkstyle:checkstyle**
  - Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html
