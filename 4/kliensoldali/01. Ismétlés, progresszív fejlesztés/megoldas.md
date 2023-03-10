# Landing page – megoldások

1. A feladat több módon is megoldható:
   1. animáció (bonyolult)
   2. `Element.scrollIntoView()`  
   3. CSS
        ```
        * { scroll-behavior: smooth; }
        ```
2. A feladathoz megoldásához egy `scroll` eseménykezelőt fogunk hozzáadni a dokumentumhoz. Ez nagyon sokszor elsül
   ezért valahogy gondoskodnunk kell arról, hogy ritkábbá tegyük.
   1. natív megoldás `setTimeout()`
   2. [lodash-throttle](https://www.jsdelivr.com/package/npm/lodash.throttle)
3. Ötlet: minden görgetéskor elenőrizzük minden meganimálandó elem esetén, hogy a viewportban van-e. [Animate.css](https://animate.style/)  
Probléma: throttle-t kell használni, matekozni kell.  
Megoldás: `Intersection Observer API`
4. A feladat megoldásához hozzáadunk egy horizontális vonalat a HTML-hez `<hr id='status-bar'>`. A második feladathoz
   hasonlóan az görgtéshez rendelünk egy throttle-ölt eseménykezelőt, mely a
   `((window.scrollY + window.innerHeight) / document.body.scrollHeight) * 100 + '%'` képlet alapján frissíti az elem
   szélességét.
5. Kigyűjtjük a navbarban szereplő linkekhez tartozó elemeket és mindegyikre egy `Intersection Observer`-t állítunk.
   Minden scroll esetén frissítjük az összes menüpont aktívságát.
6. Egy `Intersection Observert` állítunk a számot tartalmazó elemekre. A konkrét szám értéket egy `<span>` tagba
   jelenítjük meg. Az adott pillaantban megjelenő számot a `<span>` `data-current` attribútumában tároljuk
   el és CSS-el jelenítjük meg.
7. A `img-fluid` osztályú képekre oldjuk meg a feladatot. Első lépésként a képek tárójára (`portfolio-box` osztályú 
   elemek) alkalmazzuk a következő szabályt: `overflow: hidden`, hogy a kép a tárolón belül maradjon. A képek stílusa
   pedig a következő lesz:
   ```
   .portfolio-box:hover .img-fluid {
      transform: scale(1.2);
      transform-origin: center;
      overflow: hidden;
      transition: 1s;
    }
   ```