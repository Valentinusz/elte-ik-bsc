import Data.Maybe

data Time = T Int Int

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