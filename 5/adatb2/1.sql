SELECT * FROM DBA_OBJECTS;

-- Kinek a tulajdonában van a DBA_TABLES nevu nézet (illetve a DUAL nevu tábla)?
SELECT OWNER
FROM DBA_OBJECTS
WHERE OBJECT_NAME = 'DBA_TABLES' AND OBJECT_TYPE = 'VIEW';

-- Specifikusabb rendszerkatalógus:
SELECT OWNER
FROM DBA_VIEWS
WHERE VIEW_NAME = 'DBA_TABLES';

SELECT OWNER
FROM DBA_TABLES
WHERE TABLE_NAME = 'DUAL';

-- Milyen típusú objektumai vannak az orauser nevu felhasználónak az adatbázisban?
SELECT DISTINCT OBJECT_TYPE
FROM DBA_OBJECTS
WHERE OWNER = 'ORAUSER';

-- Hány különböző típusú objektum van nyilvántartva az adatbázisban?
SELECT COUNT(DISTINCT OBJECT_TYPE)
FROM DBA_OBJECTS;

-- Melyek ezek a típusok?
SELECT DISTINCT OBJECT_TYPE
FROM DBA_OBJECTS;

-- Kik azok a felhasználók, akiknek több mint 10 féle objektumuk van?
SELECT OWNER
FROM DBA_OBJECTS
GROUP BY OWNER
HAVING COUNT(DISTINCT OBJECT_TYPE) > 10;

-- Kik azok a felhasználók, akiknek van triggere és nézete is?
SELECT OWNER
FROM (SELECT OWNER FROM DBA_VIEWS) INTERSECT (SELECT OWNER FROM DBA_TRIGGERS);

-- Kik azok a felhasználók, akiknek van nézete, de nincs triggere?
SELECT * FROM (SELECT OWNER FROM DBA_VIEWS) MINUS (SELECT OWNER FROM DBA_TRIGGERS)

-- Kik azok a felhasználók, akiknek több mint 40 táblájuk, de maximum 37 indexük van?
-- korrelált alkérdés
SELECT DISTINCT OWNER
FROM DBA_OBJECTS outer
WHERE (SELECT COUNT(*) FROM DBA_TABLES WHERE OWNER = outer.OWNER GROUP BY OWNER) > 40 AND
      (SELECT COUNT(*) FROM DBA_INDEXES WHERE OWNER = outer.OWNER GROUP BY OWNER) <= 37

-- Kinek a tulajdonában van a DBA_TABLES nevu nézet?
SELECT OWNER FROM DBA_VIEWS WHERE VIEW_NAME = 'DBA_TABLES';