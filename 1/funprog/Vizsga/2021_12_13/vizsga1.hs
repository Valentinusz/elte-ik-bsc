import Data.List
import Data.Maybe
import Data.Char

--
f5 :: Integral a => a -> a
f5 n = n `mod` 5


--
matchingArgs :: Eq a => a -> a -> a -> Bool
matchingArgs a b c
  | a == b || b == c || a == c = True
  | otherwise = False


--
division :: Integral a => (a, a, a) -> Maybe a
division (_,_,0) = Nothing
division (a,b,c)
  | b `mod` c /= 0 = Just $ a `div` (b `mod` c)
  | otherwise = Nothing


--
elemOnEvenIdx :: Eq a => a -> [a] -> Bool
elemOnEvenIdx e ls = findElem e ls 1 where
  findElem :: Eq a => a -> [a] -> Int -> Bool
  findElem _ [] _ = False
  findElem e (x:xs) n
    | (n `mod` 2 == 0) && x == e = True
    | otherwise = findElem e xs (n+1)


--
dropEveryNth :: Int -> [a] -> [a]
dropEveryNth _ [] = []
dropEveryNth n ls = [x | (x,i) <- zip ls [1..], i `mod` n /= 0]


--
simDiff :: Eq a => [a] -> [a] -> [a]
simDiff [] [] = []
simDiff [] ls = ls
simDiff ls [] = ls
simDiff xs ys = (xs \\ ys) ++ (ys \\ xs)

  
--
parseNum :: String -> Maybe Integer
parseNum "+" = Nothing
parseNum "-" = Nothing
parseNum (x:xs)
  | x == '+' && onlyDigits xs = Just $ (read xs :: Integer)
  | x == '-' && onlyDigits xs = Just $ (-1) * (read xs :: Integer)
  | onlyDigits (x:xs) = Just $ (read (x:xs) :: Integer)
  | otherwise = Nothing where
    onlyDigits :: String -> Bool
    onlyDigits "" = True
    onlyDigits (x:xs)
      | isDigit x = onlyDigits xs
      | otherwise = False


--
elevate :: Eq a => a -> [a] -> [a]
elevate _ [] = []
elevate e ls
  | e `elem` ls = e : (delete e ls)
  | otherwise = ls


--
apply :: Ord b => [(a -> b)] -> a -> [b]
apply [] _ = []
apply (f:fs) a = f a : apply fs a

findLocalMax :: Ord b => [b] -> b
findLocalMax [x] = x
findLocalMax (x:y:xs)
  | x > y = x
  | otherwise = findLocalMax (y:xs)

localMax :: Ord b => [(a -> b)]{- nem üres -} -> a -> b
localMax fs e = findLocalMax (apply fs e)


--
pairMap :: (a -> b) -> [(a,a)] -> [(b,b)]
pairMap _ [] = []
pairMap f ((a,b):xs) = (f a, f b) : pairMap f xs


--
applyIfReduces :: Ord a => (a -> a) -> [a] -> [a]
applyIfReduces _ [] = []
applyIfReduces f (x:xs)
  | f x < x   = f x : applyIfReduces f xs
  | otherwise =   x : applyIfReduces f xs

  
--
data Plant = Flower String Int | Tree String Int deriving (Show,Eq)


--
isFlower :: Plant -> Bool
isFlower (Flower _ _) = True
isFlower _            = False

surviveCheck :: Int -> Plant -> Bool
surviveCheck r (Flower _ w) = w <= r

getName :: Plant -> String
getName (Flower name _) = name

survive :: [Plant] -> Int -> [String]
survive ls w = map (getName) $ filter (surviveCheck w) $ filter (isFlower) ls


--
getWater :: Plant -> Int
getWater (Tree _ w) = w

avgTreeWater :: [Plant] -> Maybe Double
avgTreeWater ls = avgTreeWater2 (filter (not . isFlower) ls) where
  avgTreeWater2 :: [Plant] -> Maybe Double
  avgTreeWater2 [] = Nothing
  avgTreeWater2 ls = Just $ (fromIntegral $ sum $ map (getWater) ls) / (fromIntegral (length ls))


--
reverseWordsInside :: String -> String
reverseWordsInside "" = ""
reverseWordsInside str = unwords [reversedS i (length $ words str) word | (word,i) <- zip (words str) [1..]] where
  reversedS :: Int -> Int -> String -> String
  reversedS i last word
    | i == 1 || i == last = word
    | otherwise = reverse word


--
strangePow :: [Int] -> [Int]
strangePow [] = []
strangePow [a] = [a]
strangePow [a,b] = [a,b]
strangePow (x:y:z:xs) = x^z : strangePow (y:z:xs)