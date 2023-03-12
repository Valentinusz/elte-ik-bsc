# Kliensoldali webprogramozás 2.
## Progresszív fejlesztés II.
### 1. Rendezhető táblázat
Adott egy táblázat az oldalon. JavaScript segítségével tedd lehetővé, hogy az oszlopok fejlécére kattintva a táblázat az adott oszlop szerint rendezve jelenjen meg!
```
<table>
    <tr>
        <th>Gyümölcs</th>
        <th>Megye</th>
    </tr>
    <tr>
        <td>Alma</td>
        <td>Békés</td>
    </tr>
    <tr>
        <td>Szilva</td>
        <td>Hajdú-Dorog</td>
    </tr>
    <tr>
        <td>Gesztenye</td>
        <td>Vas</td>
    </tr>
</table>
```

### 2. Időintervallumok
Adott egy űrlap, ahol több időpontot is fel lehet venni. Minden időpontnál meg kell adni a dátumot és egy tól-ig
intervallumot. Ellenőrizzük le JavaScript segítségével, hogy a tól mindig kisebb legyen az ig-nél. Ha rossz, akkor mind
a két mező legyen piros keretes. Legyen lehetőség új időpont-blokkot felvenni.
```
<form action="">
    <div class="idopont">
        <input type="date" name="datum[]">
        <input type="time" name="tol[]">
        <input type="time" name="ig[]">
    </div>
    <div class="idopont">
        <input type="date" name="datum[]">
        <input type="time" name="tol[]">
        <input type="time" name="ig[]">
    </div>
</form>
```

### 3. Kaszkád legördülők
Adott egy legördülő menü, benne az opciók csoportosítva. Alakítsd át ezt úgy, hogy két legördülő legyen: az elsőben a
csoportok neve, majd abból választva a másodikban a csoporton belüli opciók jelenjenek meg!
```
<label>Please choose one or more pets:
<select name="pets">
    <optgroup label="4-legged pets">
        <option value="dog">Dog</option>
        <option value="cat">Cat</option>
        <option value="hamster">Hamster</option>
    </optgroup>
    <optgroup label="Flying pets">
        <option value="parrot">Parrot</option>
        <option value="macaw">Macaw</option>
        <option value="albatross">Albatross</option>
    </optgroup>
</select>
</label>
```
