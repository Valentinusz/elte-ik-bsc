# Dataframe feladatok


```python
from pyspark.sql import  *
from pyspark.sql.functions import *

spark = SparkSession.builder.getOrCreate()
```


```python
menu_df = spark.read.option('header', True).option('inferschema', True).csv('menu.csv')
menu_df.show(5)
```

    +--------+--------------------+------------+--------+-----------------+---------+-------------------------+-------------+-----------------------------+---------+-----------+---------------------------+------+----------------------+-------------+-----------------------------+-------------+-----------------------------+------+-------+-------------------------+-------------------------+-----------------------+--------------------+
    |Category|                Item|Serving Size|Calories|Calories from Fat|Total Fat|Total Fat (% Daily Value)|Saturated Fat|Saturated Fat (% Daily Value)|Trans Fat|Cholesterol|Cholesterol (% Daily Value)|Sodium|Sodium (% Daily Value)|Carbohydrates|Carbohydrates (% Daily Value)|Dietary Fiber|Dietary Fiber (% Daily Value)|Sugars|Protein|Vitamin A (% Daily Value)|Vitamin C (% Daily Value)|Calcium (% Daily Value)|Iron (% Daily Value)|
    +--------+--------------------+------------+--------+-----------------+---------+-------------------------+-------------+-----------------------------+---------+-----------+---------------------------+------+----------------------+-------------+-----------------------------+-------------+-----------------------------+------+-------+-------------------------+-------------------------+-----------------------+--------------------+
    |       1|        Egg McMuffin|         136|     300|              120|     13.0|                       20|          5.0|                           25|      0.0|        260|                         87|   750|                    31|           31|                           10|            4|                           17|     3|     17|                       10|                        0|                     25|                  15|
    |       1|   Egg White Delight|         135|     250|               70|      8.0|                       12|          3.0|                           15|      0.0|         25|                          8|   770|                    32|           30|                           10|            4|                           17|     3|     18|                        6|                        0|                     25|                   8|
    |       1|    Sausage McMuffin|         111|     370|              200|     23.0|                       35|          8.0|                           42|      0.0|         45|                         15|   780|                    33|           29|                           10|            4|                           17|     2|     14|                        8|                        0|                     25|                  10|
    |       1|Sausage McMuffin ...|         161|     450|              250|     28.0|                       43|         10.0|                           52|      0.0|        285|                         95|   860|                    36|           30|                           10|            4|                           17|     2|     21|                       15|                        0|                     30|                  15|
    |       1|Sausage McMuffin ...|         161|     400|              210|     23.0|                       35|          8.0|                           42|      0.0|         50|                         16|   880|                    37|           30|                           10|            4|                           17|     2|     21|                        6|                        0|                     25|                  10|
    +--------+--------------------+------------+--------+-----------------+---------+-------------------------+-------------+-----------------------------+---------+-----------+---------------------------+------+----------------------+-------------+-----------------------------+-------------+-----------------------------+------+-------+-------------------------+-------------------------+-----------------------+--------------------+
    


```python
menu_df.schema
```




    StructType([StructField('Category', IntegerType(), True), StructField('Item', StringType(), True), StructField('Serving Size', StringType(), True), StructField('Calories', IntegerType(), True), StructField('Calories from Fat', IntegerType(), True), StructField('Total Fat', DoubleType(), True), StructField('Total Fat (% Daily Value)', IntegerType(), True), StructField('Saturated Fat', DoubleType(), True), StructField('Saturated Fat (% Daily Value)', IntegerType(), True), StructField('Trans Fat', DoubleType(), True), StructField('Cholesterol', IntegerType(), True), StructField('Cholesterol (% Daily Value)', IntegerType(), True), StructField('Sodium', IntegerType(), True), StructField('Sodium (% Daily Value)', IntegerType(), True), StructField('Carbohydrates', IntegerType(), True), StructField('Carbohydrates (% Daily Value)', IntegerType(), True), StructField('Dietary Fiber', IntegerType(), True), StructField('Dietary Fiber (% Daily Value)', IntegerType(), True), StructField('Sugars', IntegerType(), True), StructField('Protein', IntegerType(), True), StructField('Vitamin A (% Daily Value)', IntegerType(), True), StructField('Vitamin C (% Daily Value)', IntegerType(), True), StructField('Calcium (% Daily Value)', IntegerType(), True), StructField('Iron (% Daily Value)', IntegerType(), True)])




```python
menu_df.createTempView('menu')
```

# 3. feladat
Melyek azok az ételek, amelyek meghaladják az ajánlott napi zsír bevitelt? (`Total Fat (% Daily Value)`)

Elvárt oszlopok: `[Item]`


```python
d3 = menu_df\
    .where(menu_df['Total Fat (% Daily Value)'] > 100)\
    .select(menu_df['Item'])
      
d3.show(5)
```

    +--------------------+
    |                Item|
    +--------------------+
    |Chicken McNuggets...|
    +--------------------+
    


```python
s3 = spark.sql(
    """
    SELECT Item
    FROM menu
    WHERE `Total Fat (% Daily Value)` > 100
    """
)
      
s3.show(5)
```

    +--------------------+
    |                Item|
    +--------------------+
    |Chicken McNuggets...|
    +--------------------+
    


```python
d3.sameSemantics(s3)
```




    True



# 4. feladat
Melyik ételnek van a maximális `Sugars` értéke?
Elvárt oszlopok: `[Item, Sugars]`


```python
d4 = menu_df\
    .select(['Item', 'Sugars'])\
    .orderBy(desc('Sugars'))\
    .limit(1)

d4.show()
```

    +--------------------+------+
    |                Item|Sugars|
    +--------------------+------+
    |McFlurry with M&M...|   128|
    +--------------------+------+
    


```python
s4 = spark.sql(
    """
    SELECT Item, Sugars
    FROM menu
    ORDER BY Sugars
    DESC LIMIT 1
    """
)

s4.show()
```

    +--------------------+------+
    |                Item|Sugars|
    +--------------------+------+
    |McFlurry with M&M...|   128|
    +--------------------+------+
    


```python
s4.sameSemantics(d4)
```




    True



## 5. feladat
Hány elem van kategóriánként? Rendezzük csökkenő sorrendbe és adjuk meg a kategóriák nevét is.

Elvárt oszlopok: `[Name, Cnt]`


```python
category_df = spark.read\
    .option('header', True)\
    .option('inferschema', True)\
    .csv('menuCategory.csv')

category_df.schema
```




    StructType([StructField('Id', IntegerType(), True), StructField('Name', StringType(), True)])




```python
category_df.createTempView('category')
```


```python
s5 = spark.sql(
    """
    SELECT Name, count
    FROM ((SELECT Category, COUNT(*) as count FROM menu GROUP BY Category) JOIN category ON Category = Id)
    """
)

s5.show()
```

    +------------------+-----+
    |              Name|count|
    +------------------+-----+
    |         Breakfast|   42|
    |          Desserts|    7|
    |    Chicken & Fish|   27|
    |    Snacks & Sides|   13|
    |Smoothies & Shakes|   28|
    |            Salads|    6|
    |      Coffee & Tea|   95|
    |         Beverages|   27|
    |       Beef & Pork|   15|
    +------------------+-----+
    


```python
d5 = menu_df\
    .groupBy('Category')\
    .count()\
    .join(category_df, col('Category') == category_df['Id'])\
    .select(['Name', 'count'])

d5.show()
```

    +------------------+-----+
    |              Name|count|
    +------------------+-----+
    |         Breakfast|   42|
    |          Desserts|    7|
    |    Chicken & Fish|   27|
    |    Snacks & Sides|   13|
    |Smoothies & Shakes|   28|
    |            Salads|    6|
    |      Coffee & Tea|   95|
    |         Beverages|   27|
    |       Beef & Pork|   15|
    +------------------+-----+
    


```python
d5.sameSemantics(s5)
```




    True



## 6. feladat
Átlagosan mennyi kalóriát tartalmaznak az egyes kategóriák? Adjuk meg a kategória nevét és rendezzük átlag alapján csökkenő sorrendbe. A `Coffee and Tea` és a 
`Beverages` kategóriákat ne vegyük figyelembe.

Elvárt oszlopok: `[Name, AvgCal]`


```python
s6 = spark.sql(
    """
    SELECT NAME, avg FROM
    (
    SELECT Category, AVG(Calories) as avg
    FROM menu
    GROUP BY CATEGORY
    )
    JOIN
    (
    SELECT id, name
    FROM CATEGORY
    WHERE (name != 'Coffee & Tea' AND name != 'Beverages')
    )
    ON category = id
    """
)

s6.show()
```

    +------------------+------------------+
    |              NAME|               avg|
    +------------------+------------------+
    |         Breakfast| 526.6666666666666|
    |          Desserts|222.14285714285714|
    |    Chicken & Fish| 552.9629629629629|
    |    Snacks & Sides|245.76923076923077|
    |Smoothies & Shakes| 531.4285714285714|
    |            Salads|             270.0|
    |       Beef & Pork|             494.0|
    +------------------+------------------+
    


```python
d6 = menu_df.groupBy('Category')\
    .agg(avg('Calories').alias('avg'))\
    .join(category_df.where((col('Name') != 'Beverages') & (col('Name') != 'Coffee & Tea')), col('Category') == col('Id')).select('Name', 'avg')

d6.show()
```

    +------------------+------------------+
    |              Name|               avg|
    +------------------+------------------+
    |         Breakfast| 526.6666666666666|
    |          Desserts|222.14285714285714|
    |    Chicken & Fish| 552.9629629629629|
    |    Snacks & Sides|245.76923076923077|
    |Smoothies & Shakes| 531.4285714285714|
    |            Salads|             270.0|
    |       Beef & Pork|             494.0|
    +------------------+------------------+
    


```python
d6.sameSemantics(s6) # ezt meg se próbálom inkább azonos fizikai tervre kihozni
```




    False


