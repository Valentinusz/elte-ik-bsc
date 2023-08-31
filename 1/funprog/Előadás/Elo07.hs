queens :: Int -> [[Int]]
queens 0 = [[]]
queens n = [ q:b | b <- queens (n-1), q <- [0..7], safe q b] where
  safe q b = and [ not (checks q b i) | i <- [0..length b-1]]
  checks q b i = q == b !! i || abs (q - b!!i) == i + 1
  -- !! index operátor [1..10] !! 0 = 1
  -- and megvizsgálja hogy egy listában csak igaz érték van-e
  
--indexelés elhagyása
queens' :: Int -> [[Int]]
queens' 0 = [[]]
queens' n = [ q:b | b <- queens' (n-1), q <- [0..7], safe' q b] where
  safe' q b = and [ not (checks' q e i) | (i,e) <- zip [0..] b] where
    checks' q e i = q == e || abs (q - e) == i + 1

--rekurzív megoldás
queens'' :: Int -> [[Int]]
queens'' 0 = [[]]
queens'' n = [ q:b | b <- queens'' (n-1), q <- [0..7], safe'' q b] where
  safe'' q b = safeH q b 0 where
    safeH q (x:xs) i = q /= x && abs (q-x) /= i + 1 && safeH q xs (i+1)
    safeH q [] i = True