---1---
areTriangleSides :: Real a => a -> a -> a -> Bool
areTriangleSides a b c = a + b > c && a + c > b && b + c > a
  
---2---  
fillCount :: Int
fillCount = ceiling (50*0.25/1.8)

---3---
f1 :: Integral a => a
f1 = 2

f2 :: Num a => a -> Bool -> a
f2 n m = n

f3 :: a -> a
f3 n = n  