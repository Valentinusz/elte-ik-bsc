# Kliensoldali webprogramozás csoportzh -- Progresszív fejlesztés

[KERETPROGRAM](http://webprogramozas.inf.elte.hu/webprog-client/zh/2019-20-2/hgy-zh.zip)

## 1. Infinite scroll (5 pont)

Adott egy oldal sok tartalommal. JavaScript nélkül egy gomb szolgálna az új tartalom betöltésére az oldal alján. Progresszív fejlesztéssel szeretnénk elérni, hogy az oldal aljára érve automatikusan újabb tartalmat adjunk az oldalhoz. Az új tartalomnak a sablonja megtalálható az oldalon belül egy `newRow` azonosítójú `<template>` elemben.

- a. Tüntesd el a `btn-new-content` azonosítójú gombot! (1 pont)
- b. Érzékelve az oldal aljára érést, írd ki a konzolra: `End of page`! (2 pont)
- c. Ekkor szúrj be tetszőleges tartalmat a `body` elem gyerekei közé utolsónak (`appendChild`)! (1 pont)
- d. A beszúrt tartalom legyen a `<template>` elem tartalma! (1 pont)

_Megjegyzés_: az oldalon tetszőleges hosszú tartalmat a `div.row.old*10>div.item*3` emmet kód kiegészítésével kaphatsz VSCode-ban.

## 2. Markdown gallery (8 pont)

Adott egy (vagy akár több) textarea az oldalon `data-markdown` attribútummal. Ebben Markdown szöveget szerkeszthetünk. Markdown-ban képeket ekként szúrhatunk be: `![](url)`. Progresszív fejlesztéssel okosítsuk fel ezt a textareat úgy, hogy alatta megmutatjuk a beszúrt képeket!

- a. A textarea mögé szúrj be egy `ul` elemet. Ehhez legkényelmesebb az `insertAdjacentElement()` metódus használata: `textarea.insertAdjacentElement('afterend', ul)`. Fontos, hogy az `ul` közvetlenül a textarea után legyen! (1 pont)
- b. A textareaban való gépeléskor írd ki a konzolra a textarea szövegében található képek számát. A szövegből a képeket legegyszerűbben reguláris kifejezéssel tudod kinyerni (`/!\[\]\((.*?)\)/g`), és ehhez a legalkalmasabb a szöveg típus `matchAll` függvénye, ami visszaadja az illeszkedett elemeket csoportokkal együtt: `text.matchAll(regexp)`. (2 pont)
- c. A kinyert url-ekből készíts listaelemeket, amelyeket az `ul`-be szúrsz be. (2 pont)
    ```html
    <ul>
        <li><img src="url1"></li>
        <li><img src="url2"></li>
        <li><img src="url3"></li>
    </ul>
    ```
- d. Egyelőre gépelni kell, hogy a képek megjelenjenek. Oldd meg, hogy a felokosítás után rögtön megjelenjenek! (1 pont)
- e. Zárd egységbe a megoldást valamilyen módon: (2 pont)
    ```js
    // vagy így:
    new ImageTextarea(document.querySelector([textarea[data-markdown]]))
    ```
    ```html
    <!-- vagy így -->
    <image-textarea>
        <textarea>...</textarea>
    </image-textarea>
    ```

## 3. Lengthy input (7 pont)

Adott egy szöveges beviteli mező, aminek be van állítva, hogy legfeljebb mennyi karakter írható bele. Progresszíven fejlesszük fel ezt úgy, hogy a keretének színével jelezzük mint egy progress bar, hogy hol tartunk a szöveg hosszával a maximálishoz képest. A következőképpen szeretném az input mezőt jelezni:

```html
<input type="text" maxlength="20" is="lengthy-input" data-color="red">
```

- a. Készíts olyan komponenst az input mezőből, amelybe gépelve a konzolra kiírod a szöveg hosszát és a lehetséges maximális hosszt. (3 pont)
- b. A két érték arányának megfelelően állítsd be az input mező stílusának `border-image-source` tulajdonságát az alábbiaknak megfelelően (`százalék`): (2 pont)
```css
border-image-source: linear-gradient(to right, orange százalék%, hsla(0, 0%, 90%, 1) százalék% 100%);
```
- c. Ha meg van adva a `data-color` attribútum, akkor annak értékét használjuk fel a keret színezéséhez! A fenti kódrészletben az `orange` szót kell kicserélni az aktuális értékre. (1 pont)
- d. Tegyük lehetővé, hogy a színbeállítást JavaScript API-n is elérjük, de ez a `data-color` attribútumot is szinkronban tartsa (1 pont)
    ```js
    input.color="red"           // --> data-color="red"
    console.log(input.color)    // <-- data-color="red" --> "red"
    ```
