**Probléma**: nagy fájlok esetén a változónevek ütközhetnek és a JS nem biztosít lehetőséget névterek definiálására.

**Megoldás**: scope-t kell adnunk a kódnak

1. `{let a = ...}` kódblokk
2. `function asd() {}` függvény
3.  Objektum 
    ```
    class Asd { a = ... }
    ```