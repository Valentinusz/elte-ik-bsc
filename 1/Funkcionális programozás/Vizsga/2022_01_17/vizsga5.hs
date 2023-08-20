import Data.Char
import Data.Maybe

-- 1
concatTripleString :: ([Char], [Char], [Char]) -> [Char]
concatTripleString (a,b,c) = a ++ b ++ c


-- 2
mods :: Integral a => a -> a -> Maybe (a, a)
mods 0 _ = Nothing
mods _ 0 = Nothing
mods a b = Just $ (a `mod` b, b `mod` a)


-- 3
dropEmpties :: Eq a => [[a]] -> [[a]]
dropEmpties [] = []
dropEmpties (x:xs)
  | null x    =     dropEmpties xs
  | otherwise = x : dropEmpties xs


-- 4
createChain :: Integer -> String
createChain n
  | n <= 0    = ""
  | otherwise = concat $ [("(" ++ show i ++ ")") | i <- [1..n]]


-- 5
aLtErNaTiNgCaPs :: String -> String
aLtErNaTiNgCaPs ""       = ""
aLtErNaTiNgCaPs [a]      = [toLower a]
aLtErNaTiNgCaPs (x:y:xs) = toLower x : toUpper y : aLtErNaTiNgCaPs xs


-- 6
result :: [Maybe Bool] -> Int -> Bool
result _ 0  = True
result [] _ = False
result (x:xs) n
  | (not . null) x && fromJust x       = result xs (n-1)
  | (not . null) x && not (fromJust x) = result xs (n+1)
  | otherwise                          = result xs n


-- 7
maximumIF :: Ord a => (a -> Bool) -> [a] -> Maybe a
maximumIF p ls
  | null $ filter p ls = Nothing
  | otherwise          = Just $ maximum $ filter p ls


-- 8
fillBlanks :: String -> String -> String
fillBlanks ""  "" = ""
fillBlanks ""  _  = ""
fillBlanks str "" = str 
fillBlanks (x:xs) (y:ys)
  | x == '_'  = y : fillBlanks xs ys
  | otherwise = x : fillBlanks xs (y:ys)


-- 9
riffleShuffle :: [a] -> [a]
riffleShuffle ls = getShuffle (take ((length ls) `div` 2) ls) (drop ((length ls) `div` 2) ls) where
  getShuffle :: [a] -> [a] -> [a]
  getShuffle [] []  = []
  getShuffle [] [a] = [a]
  getShuffle (x:xs) (y:ys) = x : y : getShuffle xs ys


-- 10
getPositions :: Eq a => a -> [a] -> Maybe [Int]
getPositions e ls
  | e `elem` ls = Just $ [i | (x,i) <- zip ls [1..], x == e]
  | otherwise   = Nothing


-- 11
applies :: [a -> b] -> [a] -> [b]
applies   []   _  = []
applies   _    [] = []
applies (f:fs) ls = (map f ls) ++ applies fs ls


-- 12
data FiniteList = Empty | NonEmpty Int [Integer] deriving (Show, Eq)


-- 13
toFinite :: Int -> [Integer] -> FiniteList
toFinite _ [] = Empty
toFinite n ls
  | n <= 0    = Empty
  | otherwise = NonEmpty (length $ take n ls) (take n ls)



-- 14
concatTwo :: FiniteList -> FiniteList -> FiniteList
concatTwo Empty Empty = Empty
concatTwo  ls1  Empty = ls1
concatTwo Empty  ls2  = ls2
concatTwo (NonEmpty a ls1) (NonEmpty b ls2) = (NonEmpty (a+b) (ls1 ++ ls2))

concatFL :: [FiniteList] -> FiniteList
concatFL []       = Empty
concatFL [a]      = a
concatFL (x:y:xs) = concatFL ((concatTwo x y):xs)


-- 15
difference :: String -> String -> Maybe String
difference "" "" = Nothing
difference str1 "" = Just str1
difference "" str2 = Nothing
difference str1 str2
  | str1 == str2             = Nothing
  | null $ getDiff str1 str2 = Nothing
  | otherwise                = Just $ getDiff str1 str2 where
  getDiff :: String -> String -> String
  getDiff ""   _  = ""
  getDiff str1 "" = str1
  getDiff (x:xs) (y:ys)
    | x /=y     = x : getDiff xs ys
    | otherwise =     getDiff xs ys


-- 16
countTrue :: [(a -> Bool)] -> a -> Int
countTrue [] _ = 0
countTrue (p:ps) e
  | p e       = 1 + countTrue ps e
  | otherwise =     countTrue ps e


-- 17
filterByMajority :: [(a -> Bool)] -> [a] -> [a]
filterByMajority [] [] = []
filterByMajority [] ls = ls
filterByMajority _  [] = []
filterByMajority ps (x:xs)
  | (countTrue ps x) > ((length ps) `div` 2) = x : filterByMajority ps xs
  | otherwise                                =     filterByMajority ps xs