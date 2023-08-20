import Data.Char

--Feladatsor/óravázlat a 6. gyakorlathoz

------------
--Ismétlés--
------------

--Definiálj egy `countSpaces` függvényt, amely megszámlálja, hogy egy tetszőleges paraméterül kapott String hány szóközt tartalmaz!
countSpaces :: String -> Int
--countSpaces s = sum([1 | ' ' <- s])
countSpaces [] = 0
countSpaces (' ':xs) = 1 + countSpaces xs
countSpaces (_:xs) = countSpaces xs

--Definiálj egy `firstTwo` függvényt, amely eldönti, hogy a lista első 2 eleme közül pontosan 1 `True`.
firstTwo :: [Bool] -> Bool
firstTwo (False:True:_) = True
firstTwo (True:False:_) = True
firstTwo _ = False

--Írjunk egy `and'` függvényt, ami eldönti egy logikai elemeket tartalmazó listáról, hogy minden eleme igaz-e. (Üres listára az eredmény igaz.)
and' :: [Bool] -> Bool
and' [] = True
and' (False:_) = False
and' (_:xs) = and' xs


--Készítsük el a listába egy tetszőleges helyre egy elemet beszúró `insert` függvényt az első eleme legyen a beszúrás indexe, a második a beszúrt elem a harmadik pedig a lista, amibe az elemet beszúrjuk. Rossz indexeléssel ne foglalkozzunk.
insert :: Int -> a -> [a] -> [a]
insert 0 e xs = e:xs
insert i e (x:xs) = x : insert (i-1) e xs

--Definiáljunk egy `everySecond` függvényt, amely kiválogat minden második elemet egy listából!
everySecond :: [a] -> [a]
everySecond [] = []
everySecond [x] = []
everySecond (x:y:xs) = y : everySecond xs


--Definiáljunk egy `everyNth` függvényt, amely kiválogat minden n. elemet egy listából!


--Készítsünk egy `sublist` függvényt, ami kivág egy listából egy részt! Az első paraméter az index, ahonnan a részlista kezdődik és a második paraméter a vágás hossza.
sublist :: Int -> Int -> [b] -> [b]
sublist _ _ [] = []
sublist _ 0 _ = []
sublist kezd hossz ls = take hossz (drop (kezd-1) ls)


----------------
--Őrfeltételek--
----------------

--  foo ...
--      | őrfeltétel = kifejezés
--      | otherwise = kifejezés

--pl.:
min' :: Ord a => a -> a -> a
min' x y 
    | x < y = x
    | otherwise = y
    
--Definiálj függvényt, ami egy mértékegység-hőmérséklet párból eldönti, hogy kellemes idő van-e! (Celsius: 20-30, Kelvin: 293-303, Farenheit: 68-86)
niceWeather :: (Ord a, Num a) => ([Char], a) -> Bool
niceWeather (x,y)
  | x == "Celsius" = y >= 20 && y <= 30
  | x == "Kelvin" = y >= 293 && y <= 303
  | otherwise = y >= 68 && y <= 86
{-
niceWeather ("Celsius", n)
  | n>=20 && n<=30 = True
niceWeather ("Kelvin", n)
  | n>=293 && n<=303 = True
niceWeather ("Fahrenheit", n)
  | n>=68 && n<=86 = True
niceWeather _ = False
   -}
--Definiálj egy `filterEven` függvényt, amely egy tetszőleges paraméterül kapott egészeket tartalmazó listából kiválogatja a páros számokat! A megoldáshoz használj rekurziót!
filterEven :: [Int] -> [Int]
filterEven [] = []
filterEven (x:xs)
  | x `mod` 2 == 0 = x : filterEven xs
  | otherwise = filterEven xs

----Definiálj egy függvényt, amely egy tetszőleges paraméterül kapott Stringből eltávolítja az 'e' betűket! A megoldáshoz használj rekurziót!
--pl.: removeLetterE "feketeribizli" == "fktribizli"

    
--Szedjük ki egy listából egy adott elem első előfordulását (`remove` függvény). A további előfordulásokat hagyjuk meg. Az eltávolítandó elem az első paraméter a lista a második.
remove :: Eq a => a -> [a] -> [a]
remove _ [] = []
remove e (x:xs)
  | e == x = xs
  | otherwise = x : remove e xs
    
--Definiáljunk az upperLower függvényt, amely kisbetűből nagybetűt csinál, és fordítva, az egyéb karaktereket pedig változatlanul hagyja!


--Definiáljunk az upperLowerString függvényt, amely egy String-ben a kisbetűből nagybetűt csinál, és fordítva, az egyéb karaktereket pedig változatlanul hagyja!


--Írjuk meg a lookup' függvényt, ami kulcs-érték párok listájából kikeresi azt az értéket, ami egy adott kulcshoz tartozik. (A kulcs érték párokat egyszerű tuple-ökkel definiáljuk.)
--lookup 2 [(3,"xy"),(2,"abc"),(4,"qwe")] == "abc"

    
--Legyen toBin az a függvény, amely visszaadja egy nemnegatív egész szám kettes számrendszerbeli számjegyeit fordított sorrendben!

