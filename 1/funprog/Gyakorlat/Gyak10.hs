import Data.List (findIndex, find, break)
import Data.Maybe (fromJust, isJust, isNothing)

--Feladatsor/óravázlat a 10. gyakorlathoz

-------------------
--Típusszinonímák--
-------------------

--Példa:

data Point = Point (Float, Float) -- Új típus
    deriving Show

origo :: Point -- =! (Float, Float) 
origo = Point (0, 0)   

type IntPoint = (Int, Int) -- Nem új típus!
-- csak szinoníma egy másik típusra pl. string szinoníma karaktertömbre

intOrigo :: IntPoint -- == (Int, Int) 
intOrigo = (0, 0)

newtype Point2 = P (Int, Int) deriving Show
-- egyetlen adatkonstruktoros és adatmezős data

origo2 :: Point2
origo2 = P (0, 0)

----------

type Neptun = String
type Name = String
type Student = (Neptun, Name)

students :: [Student]
students = [("DEF456", "Toth Tibor"), ("ABC123", "Kovacs Kata"), ("GHI789", "Nagy Elemer")]

type AssignmentResult = (Student, Int) --((String, String), Int) átláthatóbb
results :: [AssignmentResult]
results = zip students [30, 40 ..]

grade :: AssignmentResult -> (Student, Int)
grade (student, score) 
    | score >= 50 = (student, 5)
    | score >= 40 = (student, 4)
    | score >= 30 = (student, 3)
    | score >= 20 = (student, 2)
    | otherwise  = (student, 1)

----------    
type Matrix = [[Float]]

zeroMat :: Int -> Matrix    
zeroMat n = [[0 | i <- [1..n]] | j <- [1..n]]

            
----------------  
-- Maybe típus--
----------------

-- data Maybe a = Nothing | Just a
--     deriving (Show, Eq)          

safeDiv :: Float -> Float -> Maybe Float 
safeDiv _ 0 = Nothing
safeDiv a b = Just $ a / b

--Definiáljuk a biztonságos fejelem függvényt!
safeHead :: [a] -> Maybe a
safeHead [] = Nothing
safeHead (x:xs) = Just x

--Definiáljuk a biztonságos tail függvényt!
safeTail :: [a] -> Maybe [a]
safeTail [] = Nothing
safeTail [x] = Just [x]
safeTail (_:xs) = safeTail xs

-- find, lookup a Data.List-ből

-- Adott két `Maybe a` érték. Adjuk össze őket, ha mindkettő tartalmaz értéket, egyébként legyen az eredmény `Nothing`!
addMaybe :: Num a => Maybe a -> Maybe a -> Maybe a
addMaybe (Just x) (Just y) = Just $ x + y 
addMaybe _ _ = Nothing

--------------
-- Feladatok--
--------------

--Adott egy `Maybe a` értékeket tartalmazó lista. Számold meg, hány `Nothing` szerepel a listában!

-- countNothings [] == 0
-- countNothings [Just 3, Just 5] == 0
-- countNothings [Just "Haskell", Just "Clean"] == 0
-- countNothings [Just "Haskell", Just "Clean", Nothing] == 1
-- countNothings [Nothing, Just "Haskell", Just "Clean", Nothing] == 2
-- countNothings [Nothing, Just "Haskell", Nothing, Just "Clean", Nothing] == 3
-- countNothings [Nothing, Just Nothing, Nothing, Just Nothing, Nothing] == 3

countNothings :: [Maybe a] -> Int
countNothings [] = 0
countNothings (x:xs) = isNothing x + countNothings xs where
  isNothing (Nothing) = 1
  isNothing _ = 0
  
countNothings' :: [Maybe a] -> Int
countNothings' xs = sum([1 | Nothing <-xs])

-- Egy `Maybe`-be csomagolt elemet fűzz egy lista elejére! Ha az elem `Nothing`, az eredmény az eredeti lista legyen!

-- Pl.:
-- addBefore (Just 1) [2,3,4] == [1,2,3,4]
-- addBefore (Just 'E') "LTE" == "ELTE"
-- addBefore Nothing [True, False] == [True, False]

addBefore :: Maybe a -> [a] -> [a]
addBefore (Nothing) xs = xs
addBefore (Just x) xs = x : xs

-- Add meg azt a függvényt, amely alkalmaz egy adott függvényt `Maybe`-kben lévő értékekre! Ha `Nothing`-ban kellene alkamazni a függvényt, akkor térjen vissza `Nothing`-gal!

--pl.:
--ap2Maybe (*) (Just 3) (Just 2)             == Just 6
--ap2Maybe (++) (Just [0..4]) (Just [5..10]) == Just [0..10]
--ap2Maybe div (Just 6) Nothing              == Nothing
ap2Maybe :: (a -> b -> c) -> Maybe a -> Maybe b -> Maybe c
ap2Maybe _ _ Nothing = Nothing
ap2Maybe _ Nothing _ = Nothing 
ap2Maybe f (Just x) (Just y) = Just $ f x y


--Definiálj egy `SignedInt` nevű adattípust előjeles egész számok reprezentálására! Az adattípusnak három konstruktora legyen: `Minus`, `Zero` és `Plus`. A `deriving` záradékban szerepeljenek legalább az `Eq` és a `Show` típusosztályok!
-- Néhány példa:

-- Minus 5 :: SignedInt -- jelentése: (-5)
-- Zero    :: SignedInt -- jelentése: 0
-- Plus 4  :: SignedInt -- jelentése: 4

data SignedInt = Minus Int | Zero | Plus Int deriving (Eq, Show)


--Valósítsuk meg az összeadást a fent definiált `SignedInt` adattípuson! (Segítségként érdemes definiálni először a fromSigned és toSigned függvényeket)

fromSigned :: SignedInt -> Int
fromSigned (Minus x) = negate $ abs x
fromSigned (Zero) = 0
fromSigned (Plus x) = abs x

toSigned :: Int -> SignedInt
toSigned x
  | x < 0 = Minus $ abs x
  | x == 0 = Zero
  | otherwise = Plus x

plus :: SignedInt -> SignedInt -> SignedInt
plus x y = toSigned $ (fromSigned x) + (fromSigned y)

--Minus 6 `plus` Plus 6 == Zero
--Plus 7 `plus` Minus 2 == Plus 5
--Minus 5 `plus` Zero == Minus 5
--Minus 8 `plus` Plus 4 == Minus 4


--Sorolj fel `SignedInt` típusú értékeket egy megadott (zárt) intervallumban!

interval :: (SignedInt, SignedInt) -> [SignedInt]
interval (x,y) = [toSigned z | z <- [(fromSigned x)..(fromSigned y)]]


--interval (Plus 2, Plus 5) == [Plus 2, Plus 3, Plus 4, Plus 5]   
--interval (Minus 2, Plus 2) == [Minus 2, Minus 1, Zero, Plus 1, Plus 2]
--interval (Plus 2, Plus 2) == [Plus 2]
--interval (Plus 2, Minus 1) == []

