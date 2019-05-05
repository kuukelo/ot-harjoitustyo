# Arkkitehtuurikuvaus

## Rakenne

Ohjelma noudattaa seuraavaa pakkausrakennetta. Aluksi siirrytään luokkaan ui, josta ohjelma käynnistää graafisen käyttöliittymän. ui pakkauksessa oleva RecipeDatabase applikaatio käyttää hyväkseen editor pakkauksessa olevan DatabaseEditorin tarjoamia metodeja. Editori puolestaan käyttää dao pakkauksen luokkia, jotka pitävät huolta tiedon hakemisesta ja tallentamisesta tietokantaan. Kaikki kolme pakkausta käyttävät lisäksi pakkauksen domain luokkia. 

![alt text](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Rakenne.png)

## Käyttöliittymä

Käyttöliittymä on graafinen. Perusnäkymä pysyy koko ajan samana (eli napit ikkunan alareunassa). Kuitenkin muu sisältö vaihtuu. Ohjelmassa on viisi erilaista näkymää, joiden alla näkymä vielä hieman vaihtelee riippuen käyttäjän tekemistä napinpainalluksista. Kaikki napinpainallukset muuttavat näkymää jollain lailla. Käyttöliittymä ei ole suoraan yhteydessä tietokantaan, eikä se varsinaisesti käsittele tietoa. Ainoastaan ottaa tietoa vastaan ja muokkaa sen sopivaan muotoon oikeanlaisen graafisen näkymän aikaansaamiseksi.

## Sovelluslogiikka

Resepti luokka pitää kirjaa resepteistä, niihin on tallennettu lista ainesosista, kategorioista. Näille on myös omat luokkansa ohjelmassa. Lisäksi reseptiin tallennetaan tieto reseptin nimestä, valmistusajasta sekä valmistusohjeista. Ingredient ja Category luokat tarjoavat vain perus setterit ja getterit. Recipe -luokka tarjoaa kuitenkin myös muutaman toString() tyyppisen metodin. 

### Tietokantakaavio

![alt text](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/tietokantakaavio.png)

### Sekvenssikaavio

#### Uuden reseptin lisääminen ja reseptien hakeminen ajan perusteella

Sekvenssikaaviosta puuttuu reseptin ohjeiden (method) lisääminen. Sillä se on lisätty ohjelmaan viime metreillä.

![alt text](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Sekvenssikaavio.jpg)


## Tietojen pysyväistallennus

Tarkoituksena on ensi viikolla muokata ohjelma ottamaan ja tallentamaan tiedot tietokantaan. Tällä hetkellä tiedot haetaan ja tallennetaan kuitenkin tiedostoon recipes.txt. Tiedot tallennetaan niin, että tiedosto on myös helposti luettava avatessa ilman ohjelmaa. Ensin on otsikko, sen alla aika, sitten listana ainesosat, otikko Categories, sen alla listana kategoriat, sitten otsikko Method ja sen alla ohjeet listattuna. Tiedosto tallentaa reseptiä tallentaessa jokaisen uuden ohjerivin eteen juoksevan numeron. Näin ohjeet on selkeät, kun ne tulostetaan käyttäjälle.

