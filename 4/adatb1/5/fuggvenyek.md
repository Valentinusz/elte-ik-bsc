--Dolgozók
SELECT *
FROM DOLGOZO;

--Kik azok a dolgozók, akik 1982.01.01 után léptek be a céghez?
SELECT *
FROM DOLGOZO
WHERE BELEPES > TO_DATE('1982.01.01', 'YYYY.MM.DD');
-- szöveg dátummá konvertálása megadott formátum alapján

--Adjuk meg azon dolgozókat, akik nevének második betűje 'A'.
SELECT *
FROM DOLGOZO
WHERE SUBSTR(DNEV, 2, 1) = 'A';
-- részstring (string, kezdet, substring-hossz)

--Adjuk meg azon dolgozókat, akik nevének második betűje 'A'.
SELECT *
FROM DOLGOZO
WHERE DNEV LIKE '_A%';

--Adjuk meg azon dolgozókat, akik nevében van legalább két 'L' betű.
SELECT *
FROM DOLGOZO
WHERE (LENGTH(DNEV) - LENGTH(REPLACE(DNEV, 'L'))) = 2;
-- length szöveg hossza
-- replace a megadott szövegben a második paramétekrént megadott substring előfordulásait a harmadik paraméterre cseréli (ha nincs megadva üres stringre cseréli)

--Adjuk meg azon dolgozókat, akik nevében van legalább két 'L' betű.
SELECT *
FROM DOLGOZO
WHERE INSTR(DNEV, 'L', 1, 2) > 0;
-- INSTR megadja, hogy az adott string benne van-e a másikban harmadik paraméter hogy milyen karaktertől kezdve vizsgálja negyedik paraméter hogy hányadik előfordulással térjen vissza. Egy számot ad vissza, ami ha nagyobb mint akkor az vizsgált string (4. paraméter mennyiségben) benne van a stringben.

--Adjuk meg azon dolgozókat, akik nevében van legalább két 'L' betű.
SELECT *
FROM DOLGOZO
WHERE DNEV LIKE '%L%L%';

--Adjuk meg a dolgozók nevének utolsó három betűjét.
SELECT SUBSTR(DNEV, -3, 3)
FROM DOLGOZO;
-- SUBSTR tud negatív értékeket is kezelni

--Adjuk meg a dolgozók fizetéseinek négyzetgyökét két tizedesre, és ennek egészrészét.
SELECT ROUND(SQRT(FIZETES), 2) AS Rounded, TRUNC(SQRT(FIZETES)) AS Truncated
FROM DOLGOZO;
-- ROUND matematikai kerekítést végez második paramétere, hogy hány tizedesjegyre történjen ez (lehet negatív is pl. -3 ezresre kerekít)
-- TRUNC egészrész
-- CEIL felső egészrész
-- FLOOR alsó egészrész

--Adjuk meg, hogy hány napja dolgozik a cégnél ADAMS és milyen hónapban lépett be.
SELECT (TRUNC(SYSDATE) - BELEP) as Napok, TO_CHAR(BELEP, 'month') as Hónap
FROM (SELECT BELEPES AS BELEP
      FROM DOLGOZO
      WHERE DNEV = 'ADAMS'
);
-- TO_CHAR dátumot konvertál a megadott formátumú szöveggé

--Adjuk meg azokat a (név, főnök) párokat, ahol a két ember neve ugyanannyi betűből áll.
SELECT dolgozo.DNEV, fonok.DNEV
FROM DOLGOZO dolgozo JOIN DOLGOZO fonok ON dolgozo.FONOKE = fonok.DKOD
WHERE LENGTH(dolgozo.DNEV) = LENGTH(fonok.DNEV);

--Listázzuk ki a dolgozók nevét és fizetését, valamint jelenítsük meg a fizetést grafikusan
  --úgy, hogy a fizetést 1000 Ft-ra kerekítve, minden 1000 Ft-ot egy '#' jel jelöl.
SELECT DNEV, FIZETES, RPAD(' ', (ROUND(FIZETES, -3)) / 1000 + 1, '#')
FROM DOLGOZO;
-- RPAD az első paraméterként kapott stringet a második paraméterként kapott hosszra egészíti ki, úgy, hogy a jobb oldalára a harmadik parméterként megadott szöveget pakolja

--Listázzuk ki azoknak a dolgozóknak a nevét, fizetését, jutalékát, és a jutalék/fizetés
  --arányát, akiknek a foglalkozása eladó (salesman). Az arányt két tizedesen jelenítsük meg.
SELECT DNEV, FIZETES, JUTALEK, ROUND(NVL(JUTALEK, 0) / FIZETES, 2)
FROM DOLGOZO
WHERE FOGLALKOZAS = 'SALESMAN';
-- NVL megvizsgálja hogy az első paramétere null-e, ha igen visszaadja a második paraméter, ha nem akkor az elsőt

-- Adjuk meg a maximális fizetésű dolgozót
SELECT max(fizetes)
FROM DOLGOZO;
-- max visszaadja a megadott oszlop legnagyobb értékét
