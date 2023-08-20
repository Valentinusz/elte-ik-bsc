--1
intExpr1 :: Int
intExpr1 = 4

intExpr2 :: Int
intExpr2 = 9

intExpr3 :: Int
intExpr3 = 19

--2
inc :: Int -> Int
inc n = n + 1

double :: Int -> Int
double n = n*2

zero :: Int
zero = 0

seven1 :: Int
seven1 = inc(inc(inc(double(double(inc zero)))))

seven2 :: Int
seven2 = inc(double(inc(inc(inc zero))))

seven3 :: Int
seven3 = inc(double(inc(double(inc zero))))

--3
foo :: Int -> Int -> Int
foo a b = a + b