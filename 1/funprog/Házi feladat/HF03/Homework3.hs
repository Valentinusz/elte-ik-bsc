--1 Pont egy tengelyen
onAxis :: Real a => (a, a) -> Bool
onAxis (_, 0) = True
onAxis (0, _) = True
onAxis (x, y) = False

--2 Törtek
add :: Integral a => (a, a) -> (a, a) -> (a, a)
add (s1, n1) (s2, n2) = (s1*n2+s2*n1, n1*n2)

--3 Időeltolás
shift :: Integral a => (a, a) -> a -> (a, a)
shift (x, y) z = (((x+(y+z) `div` 60) `mod` 24),(y+z) `mod` 60)

--4 Hegy
mountain :: Integral a => a -> [a]
mountain n = [1..n] ++ [n-1,n-2..1]