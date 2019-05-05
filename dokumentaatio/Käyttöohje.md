# Käyttöohje

## Sovelluksen tarkoitus

Sovellus on tarkoitettu reseptien tallentamiseen ja hakemiseen. Sovelluksen avulla tallennettuja reseptejä voidaan myös järjestellä halutulla tavalla esimerkiksi ajan tai kategorian (jäkiruoka, kasvisruoka jne.) mukaan. Lisäksi sovelluksen avulla voi löytää resetejä, jotka sopivat haluttuihin rajoitteisiin (esim. aika on sopiva tai resepti sisältää tiettyjä aineksia).

## Käyttäjät

Sovelluksella on tällä hetkellä vain yksi käyttäjärooli, eikä ohjelma erottele kuka ohjelmaa käyttää. Jatkokehittelynä on mahdollista muokata ohjelmaa niin, että yksi on ohjelman pääkäyttäjä, jolla on muokkausoikeudet. Muut voivat vain tarkastella ja etsiä reseptejä. Tai ohjelmaan voidaan asettaa myös eri käyttäjiä, joilla jokaisella on oma tietokantansa ja reseptinsä. 

## Ohejlman toiminta

Käynnistä ohjelma. Ohjelma avaa graafisen käyttöliittymän, jossa on viisi vaihtoehtoa. 

### Add recipe
![alt text](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Add.PNG)

Tämän avulla voit lisätä uuden reseptin tiedostoon. Syötä kaikki tarvittavat tiedot. Voit jättää minkä tahansa kohdan nimeä lukuun ottamatta tyhjäksi. Muista merkitä hours ja minutes kokonaisilla numeroilla. Älä lisää tekstiä. Paina lopuksi Add-nappia, jolloin resepti lisätään tietokantaan. 

### List all recipes
![alt text](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/See%20all.PNG)

Täällä näet kaikki tietokannassa olevat reseptit. Voit järjestää reseptejä ajan mukaan (Sort recipes by time) tai kategorioiden mukaan (Sort recipes by Category). Ohjelma näyttää kaikki reseptit halutussa järjestyksessä. Voit klikata reseptin nimeä, jolloin ohjelma näyttää reseptin kokonaisuudessaan.

### Find recipes
![alt text](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Find.PNG)

Täällä voit etsiä reseptejä tietyillä hakuehdoilla. Voit asettaa maksimiajan tai kirjoittaa listan ainesosia tai kategorioita, jotka pitää reseptistä löytyä. Ohjelma näyttää sopivat reseptit listana. Voit klikata reseptin nimeä, jolloin ohjelma näyttää reseptin nimen kokonaisuudessaan.

### Edit recipe
![alt text](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Edit.PNG)

Täällä voit muokata haluamaasi reseptiä. Tällä hetkellä muokata voi nimeä, aikaa ja ohjetta. Paina lopuksi save, jotta reseptiin tehdyt muutokset tallennetaan tietokantaan.

### Get full recipe
![alt text](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Get%20full.PNG)

Voit hakea haluamasi reseptin myös tätä kautta. Kirjoita reseptin nimi, niin ohjelma hakee sinulle kyseisen reseptin kokonaisuudessaan. 

## Jatkokehitysideoita

- Reseptien haku hakusanoilla. Jos hakusana löytyy nimestä, ohjeesta, ainesosista tai kategorioista, näyttää ohjelma kyseisen reseptin.
- Paluu nappi, jotta voi palata näkymään, jossa juuri oli.
- Reseptien muokkaaminen ainesosien ja kategorioiden perusteella.
- Reseptien rajaaminen useiden eri ehtojen kautta (esim. sekä aika että tietty kategoria)



