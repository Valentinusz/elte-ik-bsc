toysOnSale :: (Fractional a, Ord a, Integral b) => [(String, a ,b)] -> [(String, a)]
toysOnSale n = [(x,y*0.8) | (x,y,z) <- n, y>500.0 && z<10]

areAmicableNumbers :: Integral a => a -> a -> Bool
areAmicableNumbers a b = sum[n | n <- [1..a-1], a `mod` n == 0] == b && sum[n | n <- [1..b-1], b `mod` n == 0] == a

relation :: Integral a => [(a,a)]
relation = [(n,n^5-5*n^4+2*n^3-3*n+4) | n <- [0..100]] 