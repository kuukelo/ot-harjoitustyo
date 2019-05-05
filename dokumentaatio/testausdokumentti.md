# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduilla testeillä, että manuaalisesti ohjelmaa käyttämällä. Ohjelma toimii moitteettomasti.


## Yksikkö- ja integraatiotestaus

Automatisoidut testit keskittyvät databaseEditoriin, jossa suurin osa testauksen kannalta keskeisistä metodeista on. 
Editorin kautta tulee testatuksi myös dao luokat, joista editori hakee tietoa.

Graafisen käyttöliittymä on jätetty testien ulkopuolelle, eikä sen metodeja siis ole testattu, muuten kuin manuaalisesti.

### Testauskattavuus
![alt text](https://github.com/kuukelo/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/testikattavuus.PNG)

Testikattavuus on tällä hetkellä riittävä. Kaikkia gettereitä ja settereitä ei tarkisteta. 
Kattavuudesta uupuu myös rivit, jotka käsittelevät virhetilanteita, jos tietokantaa ei esimerkiksi löydy. 

## Järjestelmätestaus

Ohjelman toimintaa on testattu useita kertoja manuaalisesti. Kaikki käyttöohjeissa olevat toiminnallisuudet toimviat ohjelmassa moitteetta. 


## Sovellukseen jääneet laatuongelmat

Ohjelma ei ota huomioon kaikkia tilanteita, joissa käyttäjä syöttää väärää tai epäsopivaa tietoa. Ohjelma toimii sen jälkeen yhä, mutta haluttu toiminnallisuus ei välttämättä ole toiminut
Esimerkiksi reseptin lisääminen ei onnistu, jos aikaan on asetettu väärän muotoista tietoa, eikä tästä ilmoiteta käyttäjälle. 
