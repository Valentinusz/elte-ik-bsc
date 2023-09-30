SELECT * FROM DBA_FREE_SPACE;
SELECT * FROM DBA_TEMP_FILES;
SELECT * FROM DBA_DATA_FILES;
SELECT * FROM DBA_EXTENTS;
SELECT * FROM DBA_SEGMENTS;
SELECT * FROM DBA_TABLESPACES;

-- 0. Hol vannak a NIKOVITS felhasználó SZALLIT táblájának az adatai tárolva?
SELECT * FROM NIKOVITS.SZALLIT;

SELECT * FROM DBA_TABLES WHERE OWNER = 'NIKOVITS' AND TABLE_NAME = 'SZALLIT'

SELECT * FROM DBA_SEGMENTS WHERE OWNER = 'NIKOVITS' AND SEGMENT_NAME = 'SZALLIT';

SELECT * FROM DBA_EXTENTS WHERE OWNER = 'NIKOVITS' AND SEGMENT_NAME = 'SZALLIT';

SELECT * FROM DBA_DATA_FILES WHERE FILE_ID = 2

-- 1. Adjuk meg az adatbázishoz tartozó adatfile-ok (és temporális fájlok) nevét és méretét méret szerint csökkenő sorrendben.
(SELECT FILE_NAME, BYTES FROM DBA_DATA_FILES UNION SELECT FILE_NAME, BYTES FROM DBA_TEMP_FILES) ORDER BY BYTES DESC;

-- 2. Adjuk meg, hogy milyen tablaterek vannak letrehozva az adatbazisban, az egyes tablaterek hany adatfajlbol allnak,
-- es mekkora az osszmeretuk. (tablater_nev, fajlok_szama, osszmeret) !!! Vigyázat, van temporális táblatér is.
SELECT TABLESPACE_NAME, COUNT(BYTES), SUM(BYTES)
FROM (SELECT TABLESPACE_NAME, BYTES FROM DBA_DATA_FILES UNION SELECT TABLESPACE_NAME, BYTES FROM DBA_TEMP_FILES)
GROUP BY TABLESPACE_NAME;

-- 3. Mekkora az adatblokkok merete a USERS táblatéren?
SELECT BLOCK_SIZE
FROM DBA_TABLESPACES
WHERE TABLESPACE_NAME = 'USERS'

-- 4. Van-e olyan táblatér, amelynek nincs DBA_DATA_FILES-beli adatfájlja? Ennek adatai hol tárolódnak? -> DBA_TEMP_FILES
SELECT TABLESPACE_NAME
FROM DBA_TABLESPACES MINUS SELECT DISTINCT TABLESPACE_NAME FROM DBA_DATA_FILES;

-- 5. Melyik a legnagyobb méretű tábla szegmens az adatbázisban (a tulajdonost is adjuk meg) és hány extensből áll?
-- (A particionált táblákat most ne vegyük figyelembe.)
SELECT SEGMENT_NAME, EXTENTS, OWNER
FROM DBA_SEGMENTS
WHERE (PARTITION_NAME IS NULL) AND SEGMENT_TYPE = 'TABLE'
ORDER BY BYTES DESC
FETCH FIRST 1 ROWS ONLY;

-- 6. Melyik a legnagyobb meretű index szegmens az adatbázisban és hány blokkból áll?
-- (A particionalt indexeket most ne vegyuk figyelembe.)
SELECT SEGMENT_NAME, BLOCKS, OWNER
FROM DBA_SEGMENTS
WHERE SEGMENT_TYPE = 'INDEX'
ORDER BY BYTES DESC
FETCH FIRST 1 ROWS ONLY;

-- 7. Adjuk meg adatfájlonkent, hogy az egyes adatfájlokban mennyi a foglalt hely összesen
-- (írassuk ki a fájlok méretét is).
SELECT FILE_NAME, totalspace, BYTES
FROM (SELECT FILE_ID, SUM(BYTES) totalspace FROM DBA_EXTENTS GROUP BY FILE_ID) NATURAL JOIN DBA_DATA_FILES;

-- 8. Melyik ket felhasznalo objektumai foglalnak osszesen a legtobb helyet az adatbazisban? Vagyis ki foglal a legtöbb
-- helyet, és ki a második legtöbbet?
SELECT OWNER, SUM(BYTES) claimed
FROM DBA_SEGMENTS
GROUP BY OWNER
ORDER BY claimed DESC
FETCH FIRST 2 ROWS ONLY;

-- 9. Hány extens van a 'users01.dbf' adatfájlban? Mekkora ezek összmérete?
SELECT COUNT(*), SUM(BYTES)
FROM DBA_EXTENTS WHERE FILE_ID = (SELECT FILE_ID FROM DBA_DATA_FILES WHERE FILE_NAME LIKE '%users01.dbf')