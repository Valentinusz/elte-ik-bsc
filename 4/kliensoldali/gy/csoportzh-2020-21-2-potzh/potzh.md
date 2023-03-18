# Kliensoldali webprogramozás pót csoportzh -- Progresszív fejlesztés

[KERETPROGRAM]()

## Tudnivalók

A feladat beküldésével az alább leírtakat megértettnek és elfogadottnak tekintjük annak a nevében, aki a megoldást beküldte.

```txt
<Hallgató neve>
<Neptun kódja>
Kliensoldali webprogramozás - Progresszív fejlesztés csoport pótZH
Ezt a megoldást a fent írt hallgató küldte be és készítette 
a Webprogramozás kurzus JavaScript csoport ZH-jához.
Kijelentem, hogy ez a megoldás a saját munkám. Nem másoltam vagy 
használtam harmadik féltől származó megoldásokat. Nem továbbítottam 
megoldást hallgatótársaimnak, és nem is tettem közzé. Az Eötvös Loránd 
Tudományegyetem Hallgatói Követelményrendszere (ELTE szervezeti és 
működési szabályzata, II. Kötet, 74/C. §) kimondja, hogy mindaddig, 
amíg egy hallgató egy másik hallgató munkáját - vagy legalábbis annak 
jelentős részét - saját munkájaként mutatja be, az fegyelmi vétségnek számít. 
A fegyelmi vétség legsúlyosabb következménye a hallgató elbocsátása az egyetemről.
```

## 1. Hozzájárulási nyilatkozat (10 pont)

Szeretnénk egy gombot csak akkor elérhetővé tenni, ha a felhasználó végigolvasta, vagyis végiggörgette az elolvasandó szöveget.

- a. (1 pont) Az oldal betöltésekor tedd a gombot elérhetetlenné (disabled).
- b. (6 pont) Ha a `container` azonosítójú elem aljára görgetünk, akkor a gomb legyen újra elérhető.
- c. (3 pont) Legyen a megoldásod hatékony, azaz ha lehet, minél kevesebbszer meghívódó eseménnyel operáljon!


## 2. Szűrhető lista (10 pont)

Fejleszd fel az oldalon található listát úgy, hogy szűrhető legyen. Ehhez egy szöveges beviteli mezőt kell dinamikusan beszúrni a lista elé, és e kettőt egy div-be csomagolni. A felfejlesztett HTML szerkezet így néz ki.

```html
<div class="list-container">
    <input type="text">
    <ul>
        <!-- ... -->
    </ul>
</div>
```

A mezőben gépelve pedig csak a megfelelő listaelemek jelennek meg.

- a. (2 pont) Szúrj be egy szöveges beviteli mezőt az oldalra!
- b. (1 pont) A szöveges beviteli mező a lista elé kerüljön!
- c. (2 pont) A szöveges beviteli mezőt és a listát rakd bele egy `list-container` azonosítójú, dinamikusan beszúrt div-be!
- d. (5 pont) A szöveges beviteli mezőben gépelve a listában csak azokat a listaelemeket jelenítsd meg, amelyek belső szövege (`innerText`) tartalmazza a szűrőmező értékét! A megjelenítéshez, eltüntetéshez használhatod a `hidden` tulajdonságát a DOM elemnek.


## 3. Üzenetek (10 pont)

Készíts egy `toast-message` nevű komponenst, amelyet elsősorban felugró üzenetek megjelenítésre használhatunk. A keretprogramban elő van készítve pár ilyen üzenet, amelyek azonnal megjelennek (azaz a feladat nem ezek dinamikus létrehozása, hanem egy ilyen létrejött komponens működtetése). Egy ilyen üzenet három dologra képes:
- kívülről kapott tartalom megjelenítésére;
- ha adott a `closable` attribútuma, akkor egy gomb is megjelenik, amelyen keresztül el lehet tüntetni;
- ha adott a `timeout` attribútum egy másodperc értékkel, akkor egy progress bar jelenik meg, másodpercenként növekedve. Az adott másodperc múlva az üzenet eltűnik.

Feladatok:

- a. (1 pont) Regisztráld be a `toast-message` komponenst!
- b. (1 pont) Rendelj hozzá shadow DOM-ot!
- c. (2 pont) Add a `toast-message-template` azonosítójú sablonban lévő tartalmat a shadow DOM-hoz! (Nem feltétlenül kell shadow DOM-ot használni, de pl a sablon ehhez van előkészítve, ld. slot-ok és CSS használata.)
- d. (1 pont) Ha nincs megadva a `closable` attribútum, akkor tüntesd el a gombot a beszúrt tartalomból!
- e. (1 pont) Ha meg van adva a `closable` attribútum, akkor a gombra kattintva vedd ki az egész `toast-message` komponenst (`this`) a DOM-ból.
- f. (1 pont) Ha nincs megadva a `timeout` attribútum, akkor tüntesd el a progress elemet a beszúrt tartalomból!
- g. (1 pont) Ha meg van adva a `timeout` attribútum, akkor az értékének megfelelő idő múlva magától tűnjön el az elem a DOM-ból!
- h. (2 pont) Ha meg van adva a `timeout` attribútum, akkor állítsuk be a progress elem `max` tulajdonságát a timeout értékére, és másodpercenként növeljük a progress `value`-ját, amíg el nem éri a `max` értékét!

*Technikai segítség az attribútumkezeléshez*: a komponensen belül `this.hasAttribute()` és `this.getAttribute()` metódusokkal lehet lekérdezni őket, [ahogy az előadásban is volt erről szó](http://webprogramozas.inf.elte.hu/webprog-client/lectures/03/#/attrib%C3%BAtumok-%C3%A9s-tulajdons%C3%A1gok-szinkronban-tart%C3%A1sa).
