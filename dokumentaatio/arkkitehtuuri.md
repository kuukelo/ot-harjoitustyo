# Arkkitehtuurikuvaus

## Rakenne

Ohjelma noudattaa seuraavaa pakkausrakennetta. Aluksi siirrytään luokkaan ui, josta ohjelma käynnistää graafisen käyttöliittymän. ui pakkauksessa oleva RecipeDatabase applikaatio käyttää hyväkseen editor pakkauksessa olevan DatabaseEditorin tarjoamia metodeja. Editori puolestaan käyttää dao pakkauksen luokkia, jotka pitävät huolta tiedon hakemisesta ja tallentamisesta tietokantaan. Kaikki kolme pakkausta käyttävät lisäksi pakkauksen domain luokkia. 

![alt text](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Rakenne.png)

## Käyttöliittymä

Käyttöliittymä on graafinen. Perusnäkymä pysyy koko ajan samana (eli napit ikkunan alareunassa). Kuitenkin muu sisältö vaihtuu. Ohjelmassa on viisi erilaista näkymää, joiden alla näkymä vielä hieman vaihtelee riippuen käyttäjän tekemistä napinpainalluksista. Kaikki napinpainallukset muuttavat näkymää jollain lailla. Käyttöliittymä ei ole suoraan yhteydessä tietokantaan, eikä se varsinaisesti käsittele tietoa. Ainoastaan ottaa tietoa vastaan ja muokkaa sen sopivaan muotoon oikeanlaisen graafisen näkymän aikaansaamiseksi.

## Sovelluslogiikka

Resepti luokka pitää kirjaa resepteistä, niihin on tallennettu lista ainesosista, kategorioista. Näille on myös omat luokkansa ohjelmassa. Lisäksi reseptiin tallennetaan tieto reseptin nimestä, valmistusajasta sekä valmistusohjeista. Ingredient ja Category luokat tarjoavat vain perus setterit ja getterit. Recipe -luokka tarjoaa kuitenkin myös muutaman toString() tyyppisen metodin. 

## Tietokantakaavio

![alt text](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/tietokantakaavio.png)

Ohessa on kuvaus tietokannasta ja tietokantataulujen suhteista toisiinsa.  

## Ohjelman toiminnallisuudet

Ohjelman perustoiminnallisuuksia ovat 
- reseptin lisääminen
- kaikkien reseptien hakeminen ja niiden lajittelu ajan ja kategorioiden perusteella
- reseptin muokkaaminen (aika, nimi tai ohjeet)
- sopivan reseptin etsintä ajan, aineseosien tai kategorioiden perusteella
- koko reseptin hakeminen

RecipeDatabase vastaa graafisesta käsittelystä, DatabaseEditor tiedon käsittelystä, Daot tiedon hakemisest ja tallentamisesta tietokantaan ja domain-luokat olioiden luomisesta. 

Ohjelma on niin monimutkainen, että sekvenssikaavion tekeminen ei ole järkevää. Siitä tulisi aivan liian pitkä. Tässä kuitenkin tekstimuotoinen kuvaus eräästä toiminnallisuudesta ohjelmassa:

Täyden reseptin hakeminen nimellä Pizza. Käyttäjä on kirjoittanut tekstikenttään "Pizza" ja klikannut nappia "Get full recipe".
- RecipeDatabase kutsuu RecipeEditorin metodia getRecipe("Pizza");
- RecipeEditor kutsuu RecipeDaon metodia list()
- RecipeDao hakee tietokannasta tiedon kaikista resepteistä
- RecipeDao luo uusia reseptiolioita ja hakee samalla tiedon ainesosista IngredientRecipeDaosta (listByRecipe) sekä kategorioista CategoryDaosta (listByRecipe). Nämä puolestaan hakevat tietokannasta listat ainesosista ja kategorioista, jotka vastaavat haettuja reseptejä (vertailemalla id:ta).
- RecipeDao asettaa luodut Recipe-oliot listalle ja palauttaa ne DatabaseEditorille. 
- DatabaseEditor käy reseptilistaa läpi ja vertailee reseptien nimiä haettuun nimeen "Pizza". Jos nimi on sama, palauttaa editori kyseisen reseptiolion. Jos yksikään resepti ei ole samanniminen, palauttaa metodi null.
- RecipeDatabase ottaa reseptin, tarkistaa, ettei se ole null ja ottaa sen tiedot tekstimuodossa Recipe-luokalta ja lisää sitten sen graafiseen näkymään ja näyttää käyttäjälle. 


## Tietojen pysyväistallennus

Tiedot tallennetaan h2-tietokantaan. Tietokannassa on viisi taulukkoa, johon tietoa tallennetaan ja haetaan. Ylempänä on tietokantakaavio, jossa taulujen suhteet näkyvät. Dao luokat ovat ainoita, jotka hakevat ja tallentavat tietoa tietokantaan. Tietokannan alustus löytyy kuitenkin editori luokasta, mutta ohjelma ei käytä sitä aktiivisesti. Metodi on jätetty ohjelmaan siltä varalta, että joku käyttäjä haluaa alustaa tietokannan uudestaan. Näin ohjelman jatkokehittely on helpompaa.

Testit käyttävät samaa tietokantaa, mutta ennen testausta, ne tallettavat tietokannassa olevan tiedon listalle. Testien lopuksi tieotkantaan tehdyt muutokset pyyhitään ja samat tiedot palautetaan sinne takaisin. 

## Ohjelman rakenteeseen jääneet heikkoudet

Ohjelmaa olisi voinut jakaa vielä enemmän eri luokkiin. Tällä hetkellä ui ja editor sisältävät vain yhdet jättiluokat. Lisäksi metodeja olisi voinut vielä selkiyttää, nyt on vielä muutama spesifiin tarkoitukseen tarkoitettu metodi joita eivät muut metodit voi välttämättä hyödyntää. Tietokannasta haetaan myös vähän väliä tarpeetonta tietoa. Kun haetaan yhtä tiettyä reseptiä, on turhaa hakea kaikkien reseptien ainesosia ja kategorioita, ennen kuin tiedetään, että kyseinen resepti todella halutaan. rDaoon voisi luoda luokan, joka hakee suoraan nimen perusteella reseptiä, sillä nimet ovat myös uniikkeja. 
