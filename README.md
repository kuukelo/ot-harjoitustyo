
# Harjoitustyö - Reseptitietokanta

Sovellus on tarkoitettu reseptien tallentamiseen ja hakemiseen. Sovelluksen avulla tallennettuja reseptejä voidaan myös järjestellä halutulla tavalla esimerkiksi ajan tai kategorian (jäkiruoka, kasvisruoka jne.) mukaan. Lisäksi sovelluksen avulla voi löytää resetejä, jotka sopivat haluttuihin rajoitteisiin (esim. aika on sopiva tai resepti sisältää tiettyjä aineksia).


## Dokumentaatio

[Käyttöohje](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/K%C3%A4ytt%C3%B6ohje.md)

[Vaatimusmäärittely](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/m%C3%A4%C3%A4rittelydokumentti.md)

[Arkkitehtuurikuvaus](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/testausdokumentti.md)

[Työaikakirjanpito](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/ty%C3%B6aikakirjanpito.md)


## Releaset

[Viikko5](https://github.com/kuukelo/ot-harjoitustyo/releases/tag/viikko5)

[Viikko6](https://github.com/kuukelo/ot-harjoitustyo/releases/tag/viikko6)

[Lopullinen työ, viikko7](https://github.com/kuukelo/ot-harjoitustyo/releases/tag/lopullinen)

## Komentorivitoiminnot

### Testaus
Testit suoritetaan komennolla **mvn test**

Testikattavuusraportti luodaan komennolla **mvn jacoco:report**
  - Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html

### Suoritettavan jarin generointi
Jar luodaan komennolla **mvn package**
  - Komento generoi hakemistoon target suoritettavan jar-tiedoston RecipeDatabase-1.0-SNAPSHOT.jar

### JavaDoc

JavaDoc generoidaan komennolla **mvn javadoc:javadoc**
  - JavaDocia voi tarkastella avaamalla selaimella tiedosto target/site/apidocs/index.html

### Checkstyle

Checkstyle tarkistukset suoritetaan komennolla **mvn jxr:jxr checkstyle:checkstyle**
  - Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html

