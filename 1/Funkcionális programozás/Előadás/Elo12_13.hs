-- <<<<< . és $ >>>>>

evenFrequency :: [Int] -> Int
evenFrequency ls = length (filter even ls)

-- . függvénykompozíció operátor (f . g) x = f (g x)
-- precedenciája a legkisebb ezért pl. length . filter even ls esetében először 
-- végrehajtódik a filter
-- jobb asszociatív

evenFrequency' :: [Int] -> Int
evenFrequency' ls = (length . filter even) ls

-- $ függvényalkalmazás operátor
-- precedenciája a legmagasabb, jól használható zárójelek elhagyására
-- jobb asszociatív

evenFrequency'' :: [Int] -> Int
evenFrequency'' ls = length $ filter even ls

-- ######

dropSpaces :: String -> String
dropSpaces "" = ""
dropSpaces str = dropWhile (== ' ') str

dropSpaces2a :: String -> String
dropSpaces2a "" = ""
dropSpaces2a str = reverse (dropSpaces (reverse (dropSpaces str)))

dropSpaces2b :: String -> String
dropSpaces2b "" = ""
dropSpaces2b str = reverse $ dropSpaces $ reverse $ dropSpaces str

dropSpaces2c :: String -> String
dropSpaces2c "" = ""
dropSpaces2c str = reverse . dropSpaces . reverse .dropSpaces $ str

-- ######

composition :: [a -> a] -> (a -> a)
composition [] e = e
composition (f:fs) e = f $ composition fs e

composition' :: [a -> a] -> (a -> a)
composition' fs = foldr (.) (\x -> x) fs

-- <<<<< ADAT TÍPUSOK >>>>>

--típusnevek mindig nagybetűvel kezdődnek

-- ##### típus szinoníma ######

--type kulcsszó

--type String = [Char]

type Hour   = Int
type Minute = Int
type Second = Int

type Time = (Hour,Minute,Second)

-- olvashatóság miatt hasznos

type PredicateOnInt = (Int -> Bool)
-- even :: PredicateOnInt

type Tuple a b = (a,b)
--fst :: (Tuple a b) -> a

-- a b típusváltozók, ha ezek léteznek, akkor a típust paraméteres típusnak nevezzük

type TupleStrict a = (a,a)
--fst :: (Tuple a) -> a

-- ha kell használható típusmegszorításra

-- ##### algebrai adattípusok #####

-- data kulcsszó

--data Bool = False | True

-- felsorolás típus (a konstruktorok maguk az értékek [konstans függvények])
data Day = Mon | Tue | Wed | Thu | Fri | Sat | Sun deriving (Show)

-- ez a típus ebben a formában nem tud semmit
-- nekünk kell "megtanítani", egyedül a mintaillesztés létezik
-- a deriving kulcsszóval kérhetjük bizonyos típusosztályok automatikus példányosítását

-- manuálisan:

instance Eq Day where
  (==) Mon Mon = True
  (==) Tue Tue = True --stb stb.
  
-- Únió típus  (konstruktor függvények P2 :: Int -> Int -> Point)
-- teljes új típusok
data Point = P2 Int Int | P3 Int Int Int deriving (Show)

xCord :: Point -> Int
xCord (P2 x _) = x
xCord (P3 x _ _) = x

data Shape = Circle Double Point| Rect Double Double| Mult [Point] deriving (Show)

area :: Shape -> Double
area (Rect x y) = x * y
area (Circle x _) = x*x*3.14

-- bizonyos esetekben szeretnénk kikényszeríteni hogy csak a számunkra megfelelő típus működjön
data D = D Int deriving Show
-- az egykonstruktoros történeteket célszerűbb inkább newtype-al írni

-- ##### newtype #####
newtype N = N Int deriving Show

testD :: D -> Bool
testD (D _) = True

testN :: N -> Bool
testN (N _) = True
-- nincs konstruktor, nem kell kiértékelni azt
-- testD undefined -> Hiba, testN undefined -> True


data Lazy = L Int deriving Show
data Strict = S !Int deriving Show

-- ! a mohó kiértékelést jelöli

testL :: Lazy -> Bool
testL (L _) = False
-- testL undefined -> False
-- lusta kiértékelés meg se próbálja kiértékelni a kifejezést
-- mert nincs szükség az értékre

testS :: Strict -> Bool
testS (S _) = False
-- testS undefined -> Hiba
-- mohó kiértékelés esetében viszont az S undefined kiértékelésre kerül
-- ami hibához vezet

-- ##### LISTA #####
-- kosntruktorok [] - üres lista, (:) - fejelem és többi

data List a = Nil | a :> (List a) --deriving Show
--             []    (:) a [a]
-- lista típus rekurzív
-- infix konstruktor megadása

infixr 5 :>
-- explicit megadjuk hogy infix jobbra asszociatív a művelet és erőssége 5

instance Show a => Show (List a) where
  show ls = "<" ++ showContent ls ++ ">" where
    showContent Nil = ""
    showContent (x :> Nil) = show x
    showContent (x :> xs) = show x ++ "," ++ showContent xs

toList :: [a] -> List a
toList [] = Nil
toList (x:xs) = x :> toList xs

-- ekkor viszont az összes függvényt definiálni kell a mi listánkra
-- mit tegyünk ha ehhez nincs kedvünk ?

-- ##### Foldable #####

instance Foldable List where
  foldr f e Nil = e
  foldr f e (x :> xs) = f x (foldr f e xs)
