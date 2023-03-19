<style>
  div.table {
    overflow-x: scroll;
  }

</style>

# 03. Összekapcsolások
Lehetőségünk van táblák összekapcsolására.

Tekintsük a következő táblákat:

![](./table1.png)

## Fajtái:
### 1. Természetes összekapcsolás

Eredménye a két tábla, azon sorainak összevonása, melyekben az azonos nevű oszlopokban szereplő érétkek megegyeznek.
```sql
SELECT * FROM dolgozo NATURAL JOIN osztaly;
```
Az összekapcsolás az `OAZON` mező alaphán történik, az eredmény így a következő lesz:

<div class='table'>

| OAZON | DKOD | DNEV | FOGLALKOZAS | FONOKE | BELEPES | FIZETES | JUTALEK | ONEV | TELEPHELY |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 10 | 7782 | CLARK | MANAGER | 7839 | 1981-06-09 | 2450.00 | null | ACCOUNTING | NEW YORK |
...

</div>

Ha nincs azonos nevű oszlopoknak más a típusa hibával tér vissza.

### 2. Using
A `USING` záradékkal lehetőségünk van explicit megadni, mely oszlopok alapján történjen az összekapcsolás.

SELECT * FROM dolgozo d full outer join OSZTALY o on d.OAZON = o.OAZON;

SELECT DISTINCT telephely
FROM osztaly NATURAL JOIN dolgozo
WHERE dolgozo.foglalkozas = 'ANALYST';

SELECT telephely
FROM osztaly MINUS (SELECT DISTINCT telephely FROM OSZTALY NATURAL JOIN dolgozo
WHERE dolgozo.foglalkozas = 'ANALYST');

SELECT d.dnev
FROM DOLGOZO d join DOLGOZO f on d.FONOKE = f.dkod
WHERE f.dnev = 'KING';

SELECT d.dnev as "Dolgozó", f.dnev as "Főnök"
from dolgozo d join DOLGOZO f on d.fonoke = f.dkod
WHERE f.fizetes >= d.FIZETES * 2;

SELECT beosztott.dnev as "Dolgozó", fonok.dnev as "Főnök"
FROM DOLGOZO fonok cross join DOLGOZO beosztott
WHERE beosztott.FONOKE = fonok.DKOD AND (fonok.FIZETES > beosztott.FIZETES * 2);

CREATE table KDHPNI.GYAK3 AS
SELECT d.dnev as "Dolgozó", f.dnev as "Főnök"
from dolgozo d join DOLGOZO f on d.fonoke = f.dkod
WHERE f.fizetes >= d.FIZETES * 2;

SELECT * FROM KDHPNI.GYAK3;

<script>
  document.querySelectorAll('table').forEach(e => {
    e.style.background = 'red';
  })
</script>