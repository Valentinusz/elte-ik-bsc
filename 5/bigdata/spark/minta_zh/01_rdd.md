# RDD feladatok


```python
from pyspark import SparkConf, SparkContext

conf = SparkConf()
sc = SparkContext(conf=conf)
```

## 1. feladat

A vasarlasok.txt egy kiskereskedelmi áruházban megvásárolt termékeket tartalmazzák. Minden sor
egy vásárló kosarában lévő termékeket sorolja fel, vesszővel elválasztva. Határozd meg azt a három
terméket, amelyekből a legtöbbet vásárolták és add meg hányat. Figyelj oda arra, hogy az
adathalmazban kis és nagy betűk is előfordulhatnak. Azaz előfordulhat a "rizs" és a "Rizs" termék is.
Ezeket azonos termékeknek kell tekintened.

Egy lehetséges kimenet: ('rizs', 120), ('narancs', 34), ('hal', 32)


```python
products_rdd = sc.textFile('vasarlasok.txt')
products_rdd.take(5)
```




    ['tej,Kenyer,csoki',
     'csoki,tonhal,rizs,turo',
     'teszta,kenyer,vaj',
     'cukor,kenyer,rizs,teszta,parizsi',
     'hagyma,uborka,teszta']




```python
from operator import add
```


```python
products_rdd\
    .flatMap(lambda line: line.lower().split(','))\
    .map(lambda product: (product, 1))\
    .reduceByKey(add)\
    .sortBy(lambda entry: entry[1], ascending=False)\
    .take(3)
```




    [('kenyer', 108), ('cukor', 68), ('tej', 64)]



## 2. feladat
Az online_retail_data.csv egy webáruház eladásait tartalmazza. Határozd meg, hogy melyik terméket
melyik termékkel vásárolják gyakran együtt Franciaországban (`France`). Azaz melyek a leggyakrabban
előforduló termékpárok. Add meg a párok előfordulásának a számát és a 3 legtöbbször előfordulót ad
vissza.

Egy lehetséges kimenet: [(('POSTAGE', 'RABBIT NIGHT LIGHT'), 130), (('POSTAGE', 'RED TOADSTOOL LED NIGHT LIGHT'), 124), (('PLASTERS IN TIN CIRCUS PARADE ', 'POSTAGE'), 116)]


```python
retail_rdd = sc.textFile('online_retail_data.csv')
retail_rdd.take(5)
```




    ['InvoiceNo,StockCode,Description,Quantity,InvoiceDate,UnitPrice,CustomerID,Country',
     '536365,85123A,WHITE HANGING HEART T-LIGHT HOLDER,6,01/12/2010 8:26,2.55,17850,United Kingdom',
     '536365,71053,WHITE METAL LANTERN,6,01/12/2010 8:26,3.39,17850,United Kingdom',
     '536365,84406B,CREAM CUPID HEARTS COAT HANGER,8,01/12/2010 8:26,2.75,17850,United Kingdom',
     '536365,84029G,KNITTED UNION FLAG HOT WATER BOTTLE,6,01/12/2010 8:26,3.39,17850,United Kingdom']




```python
# sortból kiszedjük a rendelés számát (0), a terméket(2) és az országot (-1)
def split_line(line):
    split = line.split(',')
    return split[0], split[2], split[-1]

# termékek egy listájából előállítjuk az összes lehetséges kombinációt ahol 2 különböző termék van
def make_pairs(entry):
    products = set(entry[1])
    return [(tuple(sorted((a, b))), 1) for a in products for b in products if a != b]
    
retail_rdd\
    .map(split_line)\
    .filter(lambda entry: entry[0] != 'InvoiceNo' and entry[-1] == 'France')\
    .map(lambda entry: (entry[0], entry[1]))\
    .groupByKey()\
    .flatMap(make_pairs)\
    .reduceByKey(add)\
    .takeOrdered(3, key=lambda entry: -entry[1])
```




    [(('POSTAGE', 'RABBIT NIGHT LIGHT'), 130),
     (('POSTAGE', 'RED TOADSTOOL LED NIGHT LIGHT'), 124),
     (('POSTAGE', 'ROUND SNACK BOXES SET OF4 WOODLAND '), 116)]


