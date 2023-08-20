-- take :: Int -> [a] -> [a]

-- -> jobbra köt (ilyen még logikai hatványozás stb.)

-- take :: Int -> ([a] -> [a])
-- Egyparaméter Int visszaad egy függvény ami [a]-hoz [a]-t rendel

take3 :: [a] -> [a]
take3 l = take 3 l

take3' :: [a] -> [a]
take3' = take 3