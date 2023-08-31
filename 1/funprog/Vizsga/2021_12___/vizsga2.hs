import Data.List
import Data.Char

--
byDunaOrTisza :: String -> Bool
byDunaOrTisza ('D':'u':'n':'a':_) = True
byDunaOrTisza ('T':'i':'s':'z':'a':_) = True
byDunaOrTisza _ = False


--
howManyDoubles :: Eq a => [[a]] -> Int
howManyDoubles [] = 0
howManyDoubles ([a,b]:xs)
  | a == b    = 1 + howManyDoubles xs
  | otherwise = howManyDoubles xs
  

--  
blackJackPoints :: Integral a => [a] -> Maybe a
blackJackPoints ls
  | sum ls <= 21 && (and $ map (`elem` [1..11]) ls) = Just $ sum ls
  | otherwise = Nothing


--
notDivisibleByThree :: Integral a => [a] -> Maybe Int
notDivisibleByThree ls = notDivisibleByThreeIndex ls 1 where
  notDivisibleByThreeIndex :: Integral a => [a] -> Int -> Maybe Int
  notDivisibleByThreeIndex [] _ = Nothing
  notDivisibleByThreeIndex (x:xs) n
    | x `mod` 3 /= 0 = Just n
    | otherwise = notDivisibleByThreeIndex xs (n+1)


--
crowd :: Int -> String
crowd 0 = ""
crowd n
  | odd n     = concat $ (replicate ((n-1) `div` 2) "(-_") ++ "(-_-)" : (replicate ((n-1) `div` 2) "_-)")
  | otherwise = concat $ (replicate ((n-2) `div` 2) "(-_") ++ "(-_-)(-_-)" : (replicate ((n-2) `div` 2) "_-)")


--
atLeastNFrom :: Eq a => Int -> a -> [a] -> Bool
atLeastNFrom n e ls = atLeastNFromAcc n e ls 0 where
  atLeastNFromAcc :: Eq a => Int -> a -> [a] -> Int -> Bool
  atLeastNFromAcc n _ [] acc = n <= acc
  atLeastNFromAcc n e (x:xs) acc
    | n == acc  = True
    | x == e    = atLeastNFromAcc n e xs (acc+1)
    | otherwise = atLeastNFromAcc n e xs acc


--
mapEither :: (a -> Bool) -> (a -> b) -> (a -> b) -> [a] -> [b]
mapEither _ _  _    []   = []
mapEither p f1 f2 (x:xs)
  | p x       = f1 x : mapEither p f1 f2 xs
  | otherwise = f2 x : mapEither p f1 f2 xs


--  
numberOfFails :: Integral a => [[a]] -> Int
numberOfFails [] = 0
numberOfFails (x:xs)
  | (not . null) x && (fromIntegral $ sum x) / (fromIntegral $ length x) < 2.0 = 1 + numberOfFails xs
  | otherwise = numberOfFails xs


--
encode :: String -> String
encode "" = ""
encode str = concat $ encodeGroup (group str) where
  encodeGroup :: [String] -> [String]
  encodeGroup [] = []
  encodeGroup (x:xs)
    | (length x) == 1 = x : encodeGroup xs
    | otherwise = ((head x) : show (length x)) : encodeGroup xs


--
mergedOf :: Eq a => [a] -> [a] -> [a] -> Bool
mergedOf [] [] [] = True
mergedOf xs ys zs = (getMerged xs ys) == zs where
  getMerged :: Eq a => [a] -> [a] -> [a]
  getMerged [] [] = []
  getMerged xs [] = xs
  getMerged [] ys = ys
  getMerged (x:xs) (y:ys) = x : y : getMerged xs ys


--
data Weather = Sunny | Cloudy | Rainy deriving (Show, Eq)
data Forecast = Prediction Weather Int deriving (Show, Eq)


--
getWeather :: Forecast -> Weather
getWeather (Prediction w _) = w

getContinous :: [Weather] -> [[Weather]]
getContinous = filter (not . null) . concat . map inits . tails

getLongestLength :: [[Weather]] -> Int
getLongestLength ls = maximum $ map (length) ls

noRain :: [Weather] -> Bool
noRain [] = True
noRain (x:xs)
  | x == Rainy = False
  | otherwise = noRain xs

getLongest :: [[Weather]] -> Int -> [Weather]
getLongest (x:xs) longest
  | length x == longest = x
  | otherwise = getLongest xs longest

ls1 = [Prediction Rainy 30, Prediction Sunny 50,Prediction Cloudy 65]

summerVacation :: [Forecast] -> [Weather]
summerVacation ls = getLongest (filter (noRain) $ getContinous $ map (getWeather) ls) (getLongestLength $ filter (noRain) $ getContinous $ map (getWeather) ls)


--
nthSmallest :: (Integral a, Eq b) => a -> [b] -> Maybe b
nthSmallest _ [] = Nothing
nthSmallest n (x:xs) = nthSmallestI n xs x 1 where
  nthSmallestI :: (Integral a, Eq b) => a -> [b] -> b -> a -> Maybe b
  nthSmallestI _ [] _ _ = Nothing
  nthSmallestI n (x:xs) last current
    | current == n = Just last
    | x /= last = nthSmallestI n xs x (current+1)
    | otherwise = nthSmallestI n xs last current

--
--highlight :: String -> String -> String
{-
highlight "" ""  = ""
highlight  _ ""  = ""
highlight "" str = str
highlight to (x:xs)
  | isPrefixOf to (x:xs) = map (toUpper) to ++ highlight to (drop (length to) (x:xs))
  | otherwise            = x : highlight to xs
-}


--
isPrime :: Int -> Bool
isPrime 1 = False
isPrime n = (length [x | x <- [2..n], n `mod` x == 0]) == 1

getFactors :: Int -> [Int]
getFactors n = [x | x <- [1..n], n `mod` x == 0]

filterPrime :: [Int] -> [Int]
filterPrime ls = filter (isPrime) ls

roughNumber :: Int -> Int -> Bool
roughNumber k n =  and $ map (>=k) $ filterPrime $ getFactors n