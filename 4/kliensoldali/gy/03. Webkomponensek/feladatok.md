### 1. Carousel
Adott képek listája. Alakítsd át ezt úgy, hogy filmszalag-szerűen egymás mellé rendezed a képeket, de csak egynek hagysz
helyet, hogy látszódjon. A jobbra-balra billentyűkkel csúsztasd arrébb a filmszalagot úgy, hogy a következő kép
látszódjék! Sablon: `carousel.html`

### 2. Hozzávalók szerkesztése
Adott egy űrlap, ahol receptet lehet megadni. Ezen belül van egy többsoros szöveges beviteli mező, amelyben a
hozzávalókat soroljuk soronként. Fejleszd fel ezt az oldalt úgy, hogy minden sorhoz két input mezőt rendelsz: elsőben a
mennyiség, másodikban a hozzávaló. Legyen lehetőség sorokat törölni, és új sort hozzáadni. Az űrlap elküldésekor az
eredeti formátumban kell az adatokat küldeni! Sablon: `new_recipe.html`

```
<textarea>
    1csipet Cordon
    3csipet Bleu
    1tasak szárított burgonyapüré
</textarea>
```
### 3. Fotóalbum
Készíts egy fotóalbumot, ami úgy néz ki, mintha az asztalra kiszórtak volna sok képet. A képek egy felsorolásban
legyenek. A képeknek ne csak a pozíciója, hanem elforgatása is változzon. Lehessen a képeket megtekinteni rájuk
kattintva, vagy a jobbra-balra billentyűvel navigálva. Sablon: `photo_album.html`

### 4. Színes mező
Készíts egy olyan input mezőt, ami 0-tól 360-ig fogad el értékeket, és a beállított értéknek megfelelően állítja be az
input mező háttérszínét: hsl(érték, 50%, 50%)!

### 5. Textarea hosszal
Készíts olyan textarea mezőt, amely alatt fel van tüntetve, hogy eddig hány karaktert írtunk be.

### 6. Olvasható jelszó
Készíts olyan jelszó mezőt, amely mellett megjelenik egy gomb is, amelyre kattintva a jelszó olvasható formában
megjelenik.

### 7. GYIK
Adott egy GYIK-gyűjtemény, ahol a kérdéseket különböző kategóriákba sorolták. Írj olyan szkriptet, amely kigyűjti a
kategóriákat, azokat linkekként a gyűjtemény fölött megjeleníti, és ezekre kattintva csak az adott kategóriájú elemeket
jeleníti meg!

```
<section>
  <h2>GYIK</h2>
  <details>
    <summary>Kérdés1 <small>Kategória1</small></summary>
    <p>Válasz1</p>
  </details>
  <details>
    <summary>Kérdés2 <small>Kategória2</small></summary>
    <p>Válasz2</p>
  </details>
</section>
```