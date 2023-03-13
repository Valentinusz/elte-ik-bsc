# Webkomponensek
A HTML szabvány lehetőséget biztosít saját elemek definiálására. Pl.
```
<image-carousel>...</image-carousel>
```
**A saját elemeknek nevének mindig tartalmaznia kell egy kötőjelet és kebab-case-ben kell írni.**
(Különben ismeretlen elem lesz.) A custom elemek használatával könnyen megsérthetjük a progresszív fejlesztés eszméjét,
ezért extra figyelmet kell fordítanunk afelé, hogy JS nélkül is használható maradjon a weblapunk.

Saját HTML elemeket alapvetően két megközelítés alapján tudunk készíteni:
1. Autonóm elemek, melyek nem örökölnek semmilyen funkcionalitást meglévő HTML elemektől. Példa: `textarea-width-length`
   könyvtár. 
2. Személyre szabott alap HTML elemek kiterjesztése. Példa: `confirm-link` könyvtár.

## Shadow DOM
A komponensalapú fejlesztésnek fontos aspektusa az enkapszuláció, a struktúra, a stílus és a logika szétválasztása.
A `Shadow DOM API` egy lehetséges megoldás erre a problémára. Az API lehetővé teszi, hogy a sima DOM-tól elkülönülő
Shadow DOM-okat definiáljunk.
![](shadowdom.svg)
A Shadow DOM-okat a sima DOM-al azonos módon manipulálhatjuk, azonban a Shadow DOM-ban szereplő kód semmilyen módon nem
tud hatni a sima DOM-ra. 

Bővebben: [mdn web docs](https://developer.mozilla.org/en-US/docs/Web/Web_Components/Using_shadow_DOM)

Példa: `image-carousel` könyvtár

**Fontos:** Ha egy formon belül egy komponensben Shadow DOM-ot használunk, akkor a form beküldésekor a komponens
inputjaiban lévő értékek nem fognak elküldésre kerülni!

## TODO
1. `<slot>`
2. `<template>`