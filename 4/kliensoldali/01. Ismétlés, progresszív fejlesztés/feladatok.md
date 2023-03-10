# Landing page

Az alábbi feladatokat a **landing_page.html** oldalon oldd meg! A feladatokat natívan is meg lehet oldani, illetve lehet 
találni rá valamilyen kész megoldást és azt alkalmazni. A külső könyvtárakat CDN-ről húzzátok be, ajánlom a jsdelvr-t 
használatra. Opcionálisan jQuery is használható!

1. belső linkek – A landing page oldalon a navigációs fejlécben lévő belső linkekre kattintva az oldal gördülve menjen az adott helyre. 
2. navigáció rögzítése – Ha elgördült az oldal 200px-nyit, akkor alkalmazzuk a navbar-scrolled stílusosztályt a nav elemen. Ügyelj arra, hogy a scroll esemény nagyon sokszor hívódik meg!
3. animáció megjelenéskor – Ha egy elem gördítés közben a viewportba ér, akkor valamilyen animáció segítségével jelenjen meg! Az elemeket deklaratívan jelöljük meg HTML5 data attribútumokat használva, pl. data-scroll. Az animáció nevét is eltárolhatod data attribútumban, pl. data-scroll-animation="fadeInUp". Animációhoz használhatod az animate.css könyvtárat. Ügyelj arra, hogy a scroll esemény nagyon sokszor hívódik meg!
4. folyamatsáv – Helyezz el egy olvasási folyamatsávot az oldal tetején. A gördítés mértéke szerint változzon 0 és 100% között a szélessége!
5. aktív menüpont jelzése – Az oldal gördítése közben jelezd a navigációs sorban, hogy melyik menüpontnál tartunk éppen. Az adott menüpont linkjének stílusosztályai közé adjuk az active stílusosztályt. 
6. számláló pörgetése – Tedd lehetővé, hogy egy számot tartalmazó elem 0-tól felpörögjön az aktuális értékére! Az elemet deklaratívan paraméterezzük fel data attribútumokon keresztül! Eleinte az elemre kattintva történjen meg a számlálás, később a viewportba érve induljon el!
7. képnagyítás – Tedd lehetővé, hogy egy olyan elem fölé víve az egeret, amely háttérképet tartalmaz, a kép nagyobban jelenjen meg ugyanakkora helyen, és az egeret mozgatva az elem fölött lehessen a kép minden részletét megtekinteni.
