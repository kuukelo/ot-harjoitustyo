# Arkkitehtuurikuvaus

## Rakenne

Ohjelma noudattaa seuraavaa pakkausrakennetta. Aluksi siirrytään luokkaan ui, josta ohjelma käynnistetään. Suurin osa metodeista löytyy userinterfacen alta, joka käyttää hyödykseen luokkia domainista. 

![alt text](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/Rakenne.png)

## Käyttöliittymä

Ohjelma on toistaiseksi tekstikäyttöliittymään perustuva ohjelma. Ohjelma kysyy käyttäjältä, mitä hän haluaa tehdä ja lukee käyttäjältä syötteitä. Sen jälkeen ohjelma ohjaa käyttäjää eteenpäin ja kysyy uudet ohjeet, mitä käyttäjä haluaa tehdä, lukee jälleen syötettä ja toimii sen mukaisesti. Käyttäjän tekemien toimintojen mukaisesti tietoa haetaan ja tallennetaan tiedostoon recipes.txt.

## Sovelluslogiikka

Respeti luokka pitää kirjaa resepteistä, niihin on tallennettu lista ainesosista, kategorioista ja ohjeista. Näille on myös omat luokkansa ohjelmassa. Lisäksi reseptistä tallennetaan myös siihen käytetty aika. Tietojen käsittely tapahtuu userinterface luokassa.


### Luokkakaavio

![alt text](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/Luokkakaavio.png)

### Sekvenssikaavio

#### Uuden reseptin lisääminen ja reseptien hakeminen ajan perusteella

Sekvenssikaaviosta puuttuu reseptin ohjeiden (method) lisääminen. Sillä se on lisätty ohjelmaan viime metreillä.

![alt text](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/Sekvenssikaavio.jpg)


## Tietojen pysyväistallennus

Tarkoituksena on ensi viikolla muokata ohjelma ottamaan ja tallentamaan tiedot tietokantaan. Tällä hetkellä tiedot haetaan ja tallennetaan kuitenkin tiedostoon recipes.txt. Tiedot tallennetaan niin, että tiedosto on myös helposti luettava avatessa ilman ohjelmaa. Ensin on otsikko, sen alla aika, sitten listana ainesosat, otikko Categories, sen alla listana kategoriat, sitten otsikko Method ja sen alla ohjeet listattuna. Tiedosto tallentaa reseptiä tallentaessa jokaisen uuden ohjerivin eteen juoksevan numeron. Näin ohjeet on selkeät, kun ne tulostetaan käyttäjälle.

