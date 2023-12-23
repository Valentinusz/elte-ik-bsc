# Lock


## Feladatok

A `synchronised` blokkokhoz, nagyon hasonló módszer ahol monitor egy direkt erre a feladatra megtervezett objektum, amit
`Lock`-nak nevezünk. Minden `Lock` a `java.util.Concurrent.Lock` interfész egy megvalósítása. Mely a következő
műveletek definiálását írja elő.

Mi a `ReentrantLock` implementációt fogjuk használni.

### LockedList

Lock segítségével készíts egy `LockedList<T>` osztályt. Az osztály konstruktorában kapjon egy `List<T>` típusú listát
argumentumként.

Vezesd ki az `add(T)` metódust, és biztosítsd egy lock segítségével, hogy amíg egy szál elemeket ad a listához, más ne
tehesse ezt meg. Írj egy programot, amin 3 szál véletlenszerű értékeket szúr be a `[0, 100)` intervallumról egy közös
listába `[200, 400)` ms-onként.

Vezesd ki a `lock()`, `unlock()` metódusokat, valamit az `iterator()` metódust. A programban legyen egy szál, ami 2000
ms-onként kírja a képernyőre (elemenként új sorba), a lista jelenlegi tartalmát. Minden elem kiírása között a szál
aludjon el 50 ms-umra.

Oldd meg, hogy a lock műveletek kiadása nélkül lehessen biztonságosan végigiterálni a listán.

Oldd meg, az előző feladatot úgy, hogy minden lock-ra várást meg lehessen szakítani.

### AppleGarden

`Lock` és `Condition` segítségével valósítsd meg a következő feladatot.

Készíts egy `AppleGarden` nevezetű osztályt. Minden szál ezen osztály egy példányával lép interakcióba. Az
`AppleGarden`-ben legyen egy `int basket` és egy `int truck` változó. Minden szál a végtelenségig fut.

Legyen egy almaszedőt megvalósító szál. Ez a szál 1 másodpercenként meghívja az `AppleGarden` `pickApple()` metódusát,
ami  1-el megnöveli a `basket` tartalmát, és kíírja `"Current apples in basket: <"+basket+">"`. Ha a kosárban már 10
alma van, akkor a metódus a `basketIsNotFullCondition`-re várakozzon.  

Legyen egy rakodómunkást megvalósító szál. A rakodómunkás 300 millisecundumonként meghívja az `AppleGarden`
`moveToTruck()` metódusát, mely a `basket` tartalmát 1-el csökkenti, a `truck` tartalmát 1-el növeli, majd kiírja 
`"1 apple moved: <"+basket+"> --> <"+truck+">"`. Ha a `basket` üres (`basket == 0`) vagy a `truck` tele van
(`truck >= 89`), akkor a `appleIsNotPackableCondition`-re várakozzon.

Legyen egy szállító szál. Ez a szál 100 millisecundumonként meghívja az `AppleGarden.deliverTruckOfApples()` metódust. 
Ha a `truck` nincs tele akkor a `truckIsNotFullCondition`-re várakozzon.