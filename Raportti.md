# Sovellus #
JavaEE-sivusto johon rekisteröityneet käyttäjät lisäävät haluamansa merkkipäivät (esimerkiksi Kavereidens syntymäpäivät) joista lähetetään tapahtuman lähestyessä muistutus lisättyyn sähköposti-osoitteeseen.

## Näkymät ##
  * login
  * mainpage (actionit, lisätyt merkkipäivät, muokkaa aiempaa)
  * lisää merkkipäivä (henkilön nimi, päivämäärä)
  * muokkaa merkkipäivää

## Database ##
  * MySQL
  * Taulut:
    1. Userbase (id, userid, salasana, nimi, maili, syntaika, ?) (userid yksilöi)
    1. Merkkipäivät (id, userid, merkkipäivän-nimi, pvm, julkinen(?)) (userid yksilöinti merkkipäivän kanssa)

## Versionhallinta ##
  * URL:  http://code.google.com/p/merkkipaeivae/
  * Linux CLI checkout:
    * svn checkout https://merkkipaeivae.googlecode.com/svn/trunk/ merkkipaeivae --username your@email.com