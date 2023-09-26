-- DBA_TAB_COLS táblák oszlopairól tárol információt
-- Hány oszlopa van a nikovits.emp táblának?
SELECT COUNT(*)
FROM DBA_TAB_COLS
WHERE OWNER='NIKOVITS' and TABLE_NAME='EMP';

-- Milyen típusú a nikovits.emp tábla 6. oszlopa?
SELECT DATA_TYPE
FROM DBA_TAB_COLS
WHERE OWNER='NIKOVITS' AND TABLE_NAME = 'EMP' AND COLUMN_ID = 6;

-- Adjuk meg azoknak a tábláknak a nevét, amelyeknek legalább 8 darab dátum tipusú oszlopa van.
SELECT TABLE_NAME
FROM DBA_TAB_COLS
WHERE DATA_TYPE = 'DATE'
GROUP BY OWNER, TABLE_NAME
HAVING COUNT(DATA_TYPE) >= 8;

-- Adjuk meg azoknak a tábláknak a nevét, amelyeknek 1. es 4. oszlopa is VARCHAR2 tipusú.
SELECT OWNER, TABLE_NAME
FROM DBA_TAB_COLS
WHERE DATA_TYPE='VARCHAR2' AND COLUMN_ID = 1
INTERSECT
SELECT OWNER, TABLE_NAME
FROM DBA_TAB_COLS
WHERE DATA_TYPE='VARCHAR2' AND COLUMN_ID = 4

-- Adatbázis kapcsolat
CREATE DATABASE LINK ullman
    CONNECT TO felhasznalo
    IDENTIFIED BY jelszo
    USING 'ullman.inf.elte.hu:1521/ullman'

DROP DATABASE LINK ULLMAN;

SELECT * FROM VDANI.DOLGOZO@ULLMAN;

-- Miyen objektum típus van az egyik adatbázisban de nincs a másikban?
(SELECT OBJECT_TYPE FROM DBA_OBJECTS MINUS (SELECT OBJECT_TYPE FROM DBA_OBJECTS@ULLMAN))
UNION
(SELECT OBJECT_TYPE FROM DBA_OBJECTS@ULLMAN MINUS (SELECT OBJECT_TYPE FROM DBA_OBJECTS));



-- Derítsük ki, hogy kinek melyik tábláját kérdeztük le.
SELECT * FROM SZ1;

SELECT * FROM DBA_OBJECTS WHERE OBJECT_NAME = 'SZ1';
-- sz1 szinonima

SELECT * FROM DBA_SYNONYMS WHERE SYNONYM_NAME = 'SZ1';
-- tulajdonos: NIKOVITS
-- becsapós oszlopnév TABLE_OWNER és TABLE_NAME szerepel, de a szinoníma nem feltétlen tábla

SELECT * FROM DBA_OBJECTS WHERE OBJECT_NAME = 'V1' AND OWNER = 'NIKOVITS';
-- V1 egy view

SELECT TEXT FROM DBA_VIEWS WHERE VIEW_NAME = 'V1' AND OWNER = 'NIKOVITS';
-- eredmény
-- select first_name, last_name, salary, department_name
-- from nikovits.employees e natural join nikovits.departments
-- where employee_id between 110 and 120
-- megoldás NIKOVITS, employees és departments táblák