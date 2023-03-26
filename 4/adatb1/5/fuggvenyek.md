```sql
--Dolgozók
SELECT *
FROM DOLGOZO;

--Kik azok a dolgozók, akik 1982.01.01 után léptek be a céghez?
SELECT *
FROM DOLGOZO
WHERE BELEPES > TO_DATE('1982-01-01', 'YYYY-MM-DD');

--Adjuk meg azon dolgozókat, akik nevének második betűje 'A'.
SELECT *
FROM DOLGOZO
WHERE SUBSTR(DNEV, 2, 1) = 'A';

--Adjuk meg azon dolgozókat, akik nevében van legalább két 'L' betű.
SELECT *
FROM DOLGOZO
WHERE DNEV LIKE '%L%L%';

--Adjuk meg a dolgozók nevének utolsó három betűjét.
SELECT SUBSTR(DNEV, -3, 3)
FROM DOLGOZO;

--Adjuk meg a dolgozók fizetéseinek négyzetgyökét két tizedesre, és ennek egészrészét.
SELECT ROUND(SQRT(FIZETES), 2) AS Rounded, TRUNC(SQRT(FIZETES)) AS Truncated
FROM DOLGOZO;

--Adjuk meg, hogy hány napja dolgozik a cégnél ADAMS és milyen hónapban lépett be.
SELECT (TRUNC(SYSDATE) - BELEP) as NAPOK, TO_CHAR(BELEP, 'month') as HÓNAP
FROM (SELECT BELEPES AS BELEP
      FROM DOLGOZO
      WHERE DNEV = 'ADAMS'
      );

--Adjuk meg azokat a (név, főnök) párokat, ahol a két ember neve ugyanannyi betűből áll.
SELECT dolgozo.DNEV, fonok.DNEV
FROM DOLGOZO dolgozo JOIN DOLGOZO fonok ON dolgozo.FONOKE = fonok.DKOD
WHERE LENGTH(dolgozo.DNEV) = LENGTH(fonok.DNEV);

--Listázzuk ki a dolgozók nevét és fizetését, valamint jelenítsük meg a fizetést grafikusan
  --úgy, hogy a fizetést 1000 Ft-ra kerekítve, minden 1000 Ft-ot egy '#' jel jelöl.
SELECT DNEV, FIZETES, RPAD('#', ROUND(FIZETES, -3) / 1000, '#')
FROM DOLGOZO;

--Listázzuk ki azoknak a dolgozóknak a nevét, fizetését, jutalékát, és a jutalék/fizetés
  --arányát, akiknek a foglalkozása eladó (salesman). Az arányt két tizedesen jelenítsük meg.

SELECT DNEV, FIZETES, JUTALEK, ROUND(JUTALEK / FIZETES, 2)
FROM DOLGOZO
WHERE FOGLALKOZAS = 'SALESMAN'
```