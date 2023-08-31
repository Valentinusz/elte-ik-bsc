import Data.Maybe

data Time = T Int Int deriving Show

--1
showTime :: Time -> String
showTime (T x y) = show x ++ "." ++ show y

--2
eqTime :: Time -> Time -> Bool
eqTime (T a b) (T x y) = a==x && b==y

--3
isEarlier :: Time -> Time -> Bool
isEarlier (T x y) (T a b)
  | x <  a          = True
  | x <= a && y < b = True
  | otherwise       = False

--4
isBetween :: Time -> Time -> Time -> Bool
isBetween (T a b) (T x y) (T c d)
  | isEarlier (T x y) (T a b) && isEarlier (T x y) (T c d)                 = False
  | (not $ isEarlier (T x y) (T a b)) && (not $ isEarlier (T x y) (T c d)) = False
  | otherwise                                                              = True

--5
latest :: [Time] -> Maybe Time
latest [] = Nothing
latest (x:xs)
  | showTime x == getMax (x:xs) = Just x
  | otherwise                   = latest xs

getMax :: [Time] -> String
getMax xs = maximum $ map (showTime) xs

--1

data USTime = AM Int Int | PM Int Int deriving (Eq, Show)

--2

showUSTime :: USTime -> String
showUSTime (AM x y) = showTime (T x y) ++ " am"
showUSTime (PM x y) = showTime (T x y) ++ " pm"

--3

usTimeToTime :: USTime -> Time
usTimeToTime (AM 12 y) = (T 00 y)
usTimeToTime (PM 12 y) = (T 12 y)
usTimeToTime (AM x y) = (T x y)
usTimeToTime (PM x y) = (T (x+12) y)

--4

timeToUSTime :: Time -> USTime
timeToUSTime (T 12 y) = (PM 12 y)
timeToUSTime (T 0 y) = (AM 12 y)
timeToUSTime (T 24 00) = (AM 12 00)
timeToUSTime (T x y)
  | x > 12 = (PM (x-12) y)
  | otherwise = (AM x y)