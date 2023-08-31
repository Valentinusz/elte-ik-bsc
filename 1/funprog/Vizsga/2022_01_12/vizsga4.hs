import Data.Char
import Data.Maybe
import Data.List

--
threeDivs :: Integral a => (a,a) -> (a,a) -> (a,a) -> Maybe a
threeDivs (_,0) _ _ = Nothing
threeDivs _ (_,0) _ = Nothing
threeDivs _ _ (_,0) = Nothing
threeDivs (a,b) (c,d) (e,f) = Just $ (a `div` b) + (c `div` d) + (e `div` f)

--
howManyDifferences :: Eq a => [(a,a)] -> Int
howManyDifferences [] = 0
howManyDifferences ((a,b):xs)
  | a /= b = 1 + howManyDifferences xs
  | otherwise = howManyDifferences xs

--
getDigitsFromCode :: String -> String
getDigitsFromCode "" = ""
getDigitsFromCode code = takeWhile (isDigit) code

--
isTriangularNumber :: Integral a => a -> Bool
isTriangularNumber num = findNumber num 1 where
  findNumber :: Integral a => a -> a -> Bool
  findNumber num acc
    | acc == num = True
    | acc > num = False
    | otherwise = findNumber (num-acc) (acc + 1)

--	
smallestInSize :: [a] -> [b] -> [c] -> Maybe Int
smallestInSize [] [] [] = Nothing
smallestInSize xs [] [] = Nothing
smallestInSize [] ys [] = Nothing
smallestInSize [] [] zs = Nothing
smallestInSize [] ys zs = Just 1
smallestInSize xs [] zs = Just 2
smallestInSize xs ys [] = Just 3
smallestInSize (_:xs) (_:ys) (_:zs) = smallestInSize xs ys zs

--
reverseWords :: Integral a => String -> [a] -> String
reverseWords str ls = unwords (reverseWords2 (words str) ls 1) where
  reverseWords2 :: Integral a => [String] -> [a] -> Int -> [String]
  reverseWords2 [] _ _ = []
  reverseWords2 ls [] _ = ls
  reverseWords2 (x:xs) (y:ys) i
    | (fromIntegral y) == i = (reverse x) : reverseWords2 xs ys (i+1)
    | otherwise = x : reverseWords2 xs (y:ys) (i+1)

--
camelToSnake :: String -> String
camelToSnake "" = ""
camelToSnake (x:xs)
  | isUpper x = '_' : toLower x : camelToSnake xs
  | otherwise = x : camelToSnake xs

--
sumMaybe :: Num a => [Maybe a] -> a  
sumMaybe ls = sum $ map (abs . fromJust) $ filter (not . null) ls

--
applyIfIncreases :: Ord a => (a -> a) -> [a] -> [a]
applyIfIncreases _ [] = []
applyIfIncreases f (x:xs)
  | f x > x = f x : applyIfIncreases f xs
  | otherwise = x : applyIfIncreases f xs

--
elemFreqByFirstOcc :: Eq a => [a] -> [(a, Int)]
elemFreqByFirstOcc [] = []
elemFreqByFirstOcc (x:xs) = (x,(count x (x:xs))) : elemFreqByFirstOcc (remove x xs)

count :: Eq a => a-> [a] -> Int
count a [] = 0
count a (x:xs)
   | a == x = 1 + count a xs
   | otherwise = count a xs

remove :: Eq a => a -> [a] -> [a]
remove a [] = []
remove a (x:xs)
   | a == x = remove a xs
   | otherwise = x : (remove a xs)
   
-- 
type RegNum = String -- rendszám
type Level = Int -- emelet
type SpotNum = Int -- parklóhely sorszáma

data Status = Free | Occupied RegNum deriving (Show, Eq)

data ParkingSpace = PS Level SpotNum Status deriving (Show, Eq)

type ParkingLot = [ParkingSpace]

--
freeSpaces :: ParkingLot -> Int -> Int
freeSpaces [] _ = 0
freeSpaces ((PS level _ status):xs) n
  | level == n && status == Free = 1 + freeSpaces xs n
  | otherwise = freeSpaces xs n

--
getRegNum :: Status -> RegNum
getRegNum (Occupied rn) = rn

findCar :: ParkingLot -> RegNum -> Maybe (Level, SpotNum)
findCar [] _ = Nothing
findCar ((PS level spot status):xs) rn
  | status /= Free && getRegNum status == rn = Just $ (level,spot)
  | otherwise = findCar xs rn

--
eval :: String -> Integer
eval str = sum (map (read) $ words $ map (\x -> if x == '+' then ' ' else x) str :: [Integer])

--
dropOrInsert :: Eq a => [a] -> [a] -> [a]
dropOrInsert [] [] = []
dropOrInsert ls [] = ls
dropOrInsert ls (x:xs)
  | x `elem` ls = dropOrInsert (delete x ls) xs
  | otherwise = dropOrInsert (ls ++ [x]) xs

-- 
movingAvg :: Int -> [Double] -> [Maybe Double]
movingAvg _ [] = []
movingAvg m ls
  | m <= 0 = []
  | (length $ take m ls) == m = (Just $ (sum $ take m ls) / (fromIntegral m)) : movingAvg m (drop 1 ls) 
  | otherwise = Nothing : movingAvg m (drop 1 ls) 