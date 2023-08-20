isEven :: Int -> Bool
isEven n = mod n 2 == 0

isOdd :: Int -> Bool
isOdd n = mod n 2 /= 0

isOddBetter :: Int -> Bool
isOddBetter n = not (isEven n)

add :: Int -> Int -> Int
add a b = a + b

seven1 :: Int -> Int
seven1 = inc(inc(double(double(inc 1))))

isDivisibleBy :: Int -> Int -> Bool
isDivisibleBy n k = n `mod` k == 0

isDivBy4 :: Int -> Bool
isDivBy4 n = isDivisibleBy n 4 

isDivBy100 :: Int -> Bool
isDivBy100 n = isDivisibleBy n 100

isDivBy400 :: Int -> Bool
isDivBy400 n = isDivisibleBy n 400

isLeapYear :: Int -> Bool
isLeapYear n = isDivBy400(n) || isDivBy4(n) && not(isDivBy100(n))

-- és magasabb szintű operátor

sumSqrt :: Integral a => a -> a -> Double
sumSqrt a b = sqrt (fromIntegral (a + b))

-----------------
--Polimorfizmus--
-----------------

--egy kifejezés típusa lekérdezhető GHCi-ben a :t <kifejezés> paranccsal
--Például :t (+) (+) :: Num a => a -> a -> a 
--a típusparaméter
--Num típusosztály (a szám jellegű típusok összefoglaló neve)
--Minden ami ebbe az típusosztályba tartozik rendelkezik (+,-,*)
--Numon belül altípusosztály az Integral (Int, Integer)
--Itt már működik a (mod, div)
--További altípusosztály a numon belül a Fractional (Double, Float)
--Itt működik a /

f1 :: Num a => a -> a
f1 x = 2*x+1

f2 :: Integral p1 => p1 -> Bool
f2 x = 3*x `div` 3 == 0

f3 :: Fractional a => a -> a 
f3 x = (x+100) / 7

--Eq(==) típusosztály egyenlőségvizsgálható típusok
--Ord(<) típusosztály sorbarendezhető típusok (altípusosztálya Eq-nak)

decreasing :: Ord a => a -> a -> a -> Bool
decreasing x y z = x >= y && y >= z

equal3 :: Eq a => a -> a -> a -> Bool
equal3 x y z = x == z && y == z

spike :: Eq a => a -> a -> a -> bool
spike x y z == z && y > x

--még általáosabb függvény

firstParam :: p1 -> p2 -> p1
firstParam a b = a