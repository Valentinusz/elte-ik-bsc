# Haladó Java
## 1. A felsorolási típus
1. Készítsd el a City felsorolási típust.
   1. a felsorolási típus elemei legyenek a városok nevei (csupa nagybetűvel)
   2. a típusnak legyen zipCode mezője (irányítószám), egy int 
   3. lehessen az elemeket egy int-tel paraméterezni, amit vegyen át a mezőbe
   4. lehessen egy másik várossal paraméterezni az elemeket, ekkor az aktuális elem irányítószáma a paraméterből jöjjön
   5. számít-e a sorrend?
   6. készíts saját toString metódust hozzá
   7. Írd ki a City elemeit sorban.
2. Készítsd el a WeekDay felsorolási típust.
   1. Az elemek legyenek a napok nevei rövidítve.
   2. Készítsd el a nextDay metódust, ami kiadja a következő napot.
      1.  Legyen ebből túlterhelt változat, ami kapja meg, hány napot lépjünk előre.
        Negatív szám esetén is működjön, értelemszerűen visszafelé lépésekkel.
   3. Az elemek paraméterei legyenek a napok teljes nevei különböző nyelveken. Tetszőleges sok paramétert kaphasson az elem. 
   4. Egy static mező írja le a támogatott nyelvek szöveges kódjait egy tömbben. 
      1. Legyen egy get(String lang) metódus, amely adja vissza a nap nevét a megadott nyelven, illetve a "?" szöveget, ha a nyelv nem ismert, vagy a paraméterezésben nincsen jelen.
      2. Lehessen úgy is megadni a neveket, hogy azok tetszőleges sorrendben legyenek leírhatók az adott naphoz, és ne feltétlenül egy előre meghatározott sorrendben kelljen őket megadni. Akár az is lehetséges így, hogy két különböző napnak nincs is közös nyelven neve.
## Összefoglaló
- Felsorolási típus
  - lehetnek adattagjai, konstruktorai, metódusai
  - `Enum.values()` megadja a felsorolási típus lehetséges érétkeit egy tömbben
  - `Enum.ordinal()` megadja az adott konstans `Enum` deklarációbeli indexét
- vararg pl. `String...` váltakozó számú paraméter
- Összociatív adatszerkezet: `Map` típusok pl. `HashMap`
  - `get(Object key)` elem lekérése
  - `put(K key, V value)` elem beállítása