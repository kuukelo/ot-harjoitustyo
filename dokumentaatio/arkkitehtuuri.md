# Arkkitehtuurikuvaus

## Rakenne

Ohjelma noudattaa seuraavaa pakkausrakennetta. Aluksi siirrytään luokkaan ui, josta ohjelma käynnistetään. Suurin osa metodeista löytyy userinterfacen alta, joka käyttää hyödykseen luokkia domainista. 

![alt text](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/Rakenne.png)

## Käyttöliittymä

Ohjelma on toistaiseksi tekstikäyttöliittymään perustuva ohjelma. Ohjelma kysyy käyttäjältä, mitä hän haluaa tehdä ja lukee käyttäjältä syötteitä. Sen jälkeen ohjelma ohjaa käyttäjää eteenpäin ja kysyy uudet ohjeet, mitä käyttäjä haluaa tehdä, lukee jälleen syötettä ja toimii sen mukaisesti. Käyttäjän tekemien toimintojen mukaisesti tietoa haetaan ja tallennetaan tiedostoon recipes.txt.

## Sovelluslogiikka



## Luokkakaavio

![alt text](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/Luokkakaavio%20Recipedatabase.jpg)

## Sekvenssikaavio

### Uuden reseptin lisääminen ja reseptien hakeminen ajan perusteella

![alt text](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/Sekvenssikaavio.jpg)
