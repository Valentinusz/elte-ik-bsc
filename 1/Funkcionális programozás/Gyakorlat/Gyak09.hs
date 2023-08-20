--Feladatsor/óravázlat a 9. gyakorlathoz

---------------------
--Saját adattípusok--
---------------------


--Új típus bevezetése:
--data Típuskonstruktor = Adatkonstruktor1 | Adatkonstruktor2 | ...

--pl.:
--data Bool = False | True 


-- Új felsorolási típus:
data SimpleColor = Red | Green | Blue


-- Mintaillesztés
favoriteColor :: SimpleColor -> Bool
favoriteColor Blue = True
favoriteColor _ = False


-- Típus paraméterrel
data Point = Point Float Float deriving Show
-- Típuskonstruktor != Adatkonstruktor
-- ... de mivel más névtérben vannak, hívhatjuk ugyanúgy

distance :: Point -> Point -> Float
distance (Point x1 y1) (Point x2 y2) = sqrt $ (x1 - x2)^2 + (y1 - y2)^2


-- Írjuk meg a getX és a getY függvényeket, amelyek visszaadják egy pont (az előbb definiált Point adattípus) x illetve y koordinátáját!
getX :: Point -> Float
getX (Point x _) = x

getY :: Point -> Float
getY (Point _ y) = y


-- Írjuk újra a distance függvényt, hogy a Point adatkonstruktorára való mintailleszés helyett a getX és getY függvényeket használja!
distance' :: Point -> Point -> Float 
distance' (point1) (point2) = sqrt $ (getX point1 - getX point2)^2 + (getY point1 - getY point2)^2


-- Az alakzat adattípusnak két adatkonstruktora is van: a Circle (kör) és Rect (téglalap)
-- paraméterek:       x     y   sugár          x1    y1    x2    y2
data Shape = Circle Float Float Float | Rect Float Float Float Float deriving Show
--                                            bal felső    jobb alsó

area :: Shape -> Float
area (Circle _ _ r) = r^2 * pi
area (Rect x1 y1 x2 y2) = (abs $ x2 - x1) * (abs $ y2 - y1)


-- Írjuk át a Shape adattípust típust, hogy a Circle adakonstruktor egy Point-ként kapja meg a kör középpontját és a Rect egy-egy Pointként kapja meg a téglalap bal felső illetve jobb alsó sarkát!
-- Alakítsuk át hozzá a területet kiszámoló area függvényt is!
data Shape' = Circle' Point Float | Rect' Point Point
area' :: Shape' -> Float
area' (Circle' _ r) = r^2 * pi
area' (Rect' p1 p2) = (abs $ (getX p2) - (getX p1)) * (abs $ (getY p2) - (getY p1))


-- Írjunk függvényt, amely eltol egy pontot a síkon! 
displace :: Point -> Point -> Point
displace (Point x1 y1) (Point x2 y2) = Point (x1+x2) (y1+y2)

displace' :: Point -> Point -> Point
displace' (p1) (p2) = Point ((getX p1) + (getX p2)) ((getY p1)+(getY p2))


-- Írjunk függvényt, amely eltol egy alakzatot (paraméterként egy pontot vár).
displaceShape :: Shape -> Point -> Shape
displaceShape (Circle u v r) (Point x y) = Circle (u+x) (v+y) r
displaceShape (Rect x1 y1 x2 y2) (Point a b) = Rect (x1+a) (y1+b) (x2+a) (y2+b)

displaceShape' :: Shape' -> Point -> Shape'
displaceShape' (Circle' p r) (v) = Circle' (displace' p v) r
displaceShape' (Rect' p1 p2) (v) = Rect' (displace' p1 v) (displace' p2 v)


-- (korábbi feladat)
-- Definiálj függvényt, ami egy mértékegység-hőmérséklet párból eldönti, hogy kellemes idő van-e! (Celsius: 20-30, Kelvin: 293-303, Farenheit: 68-86)
--niceWeather :: (Ord a, Num a) => ([Char], a) -> Bool
--niceWeather ("Celsius", n)
--    | n >= 20 && n<= 30 = True
--niceWeather ("Kelvin", n)
--    | n >= 293 && n<= 303 = True
--niceWeather ("Farenheit", n)
--    | n >= 68 && n<= 86 = True
--niceWeather _ = False   

-- Oldjuk meg az előző feladatot úgy, hogy a hőmérsékletet egy saját adattípussal reprezentáljuk!

data Temperature = Celsius Float | Fahrenheit Float | Kelvin Float
niceWeather :: Temperature -> Bool
niceWeather (Fahrenheit t) = (t >= 68 && t<= 86)
niceWeather (Kelvin t) = (t >= 293 && t<= 303)
niceWeather (Celsius t) = (t >= 20 && t<= 30)