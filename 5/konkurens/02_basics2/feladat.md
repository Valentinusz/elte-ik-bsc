# Konkurens programozás #2

## 1. Car
Készíts egy `Car` osztályt, ami implementálja a `Runnable` interfészt. Az osztálynak 2 adattagja van: `int traveled`
és `int fuel`. A fuel értékét konstruktorból lehessen beállítani. A Run metódusban az autó, mindaddig, amíg van még
benne legalább 5 liter benzin, haladjon 100 km-t, és írja ki: az aktuális szál nevét, és azt,
hogy  `"brumm-brumm"`, majd csökkentse az üzemanyag mennyiséget 5-tel. Minden 100 km megtétele után a szál aludjon 2
másodpercet. Ha már nem tud tovább menni, írja ki a szál nevét, és azt, hogy `"stopped after: " + traveled`. Ezután a
run érjen véget. 

Készíts 5 szálat, amik 1-1 autó objektumot kapnak. Az autók üzemanyag véletlenszerű szám legyen 20 és 40 között.


## 2. Checker
Készíts egy programot, ahol a main metódusban definiálsz egy 10 elemű tömböt. A tömb elemeit véletlenszerűen tölsd fel a
`[0, 10)` intervallumból. Készíts 10 szálat, ahol a szálak feladatát egy névtelen lambda függvény segítségével adod meg,
az `i`-edik szál ellenőrizze a tömb `i`-edik elemét, hogy annak az értéke éppen `i`-e. Ha igen, írja ki:
`"Heuréka a[<i>]==<i>"` Ellenkező esetben írd ki: `"Sajnos a[<i>] ==<a[i]>"`.


## 3. Read/Write
Készíts egy `Helper` nevű osztályt. Az osztálynak 1 `public static boolean B = false` adattagja van.

Készíts két osztályt (`Reader`, `Writer`), mindegyik valósítsa meg a `Runnable` interfészt. A `Writer` a `run`
metódusában egy ciklusban fusson addig, amíg még nem fut 5 másodperce (ne várakozzon), és a `Helper.b` értéke `false`,
aztán írja át az értéket `true`-ra, és írja ki, hogy `"changed"`.

A Reader a `run` metódusában fusson addig, amíg a `Helper.b` értéke `true`-ra nem vált
(a ciklus lépéseiben ne várakozzon). Majd írja ki `"about to stop"`, és hogy mennyi ideje futott. 

Hozz létre egy `Reader` és egy `Writer` objketumot, majd indíts el 2 szálat ezen objektumok segítségével.

## 4. Info
Készíts egy statikus metódust, ami kiírja a képernyőre az aktuális szál nevét, és az aktuális időpillanatot
(`Instant.now()`), majd alszik véletlenszerűen `[1200,1500)` milliszekundumot, és mégegyszer kiírja ezt az információt
a képernyőre.

Készíts egy programot, ami egy listába belerak 3 olyan szálat, aminek a konstruktorában a fenti függvényt adod át,
metódus referenciaként. Egy ciklus segítségével mind a 3 szálon hívd meg a `run()` metódust, majd egy másik ciklus
segítségével, mind a 3 szálon hívd meg a `start()` metódust. Mit tapasztalsz?