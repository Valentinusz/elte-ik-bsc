# 1. Composer

A Composer egy csomagkezelő rendszer PHP-hoz.  
[egyéni telepítő](https://getcomposer.org/download/),  
[php és composer telepítő](https://github.com/totadavid95/PhpComposerInstaller)

A ` composer -V` paranncsal ellenőrizhető a telepítés sikeressége. 

A Composer a [Packagist](https://packagist.org/) repositoryjából dolgozik, itt böngészhetünk külső könyvtárakat.

A `composer init` segítségével adható hozzá a composer egy projekthez. A paranccsal egy tool nyílik meg
mely egy alap Composer konfigurációt hoz létre.

Akkor létehozásra kerülnek a következő fájlok és könyvtárak:
1. `src` az általánuk írt forráskód mappája
2. `vendor` a külső könyvtárak mappája (tilos belenyúlni)
3. `composer.json` a Composer konfigurációját tartalmazó fájl
4. `composer.lock` automatikusan generált konfigáriós fájl (tilos belenyúlni)  

Külső könyvtárakat a 
```
composer require <csomagnév>
```
paranccsal adhatunk hozzá a projekthez. Például `composer require fakerphp/faker`. Ez a parancs automatikusan letölti
és hozzáadja a függűségekhez a megadott csomagot.

A projekt függőségeit megadhatjuk manuálisan a `composer.json` fájlban is ekkor a `composer update` paranccsal
telepíthetőek, illetve frissíthetőek.

A `composer require ...` parancs kiadásakor a megadott csomag mint függőség bekerül a `composer.json` fájlba, mely alapján automatikusan újragenerálásra kerül a `composer.lock` fájl.

A `composer.lock` fájl tartalmazza a projekt függőségeinek listáját azok pontos verziószámával. A függőségek a `composer install` paranccsal telepíhetőek.

A Composer által biztosított csomagok használatához a `require 'vendor/autoload.php';` utasítást kell megadnunk a
`.php` fájljainkban. Ez egy `autoloader` hoz létre, mely a függőségek betöltéséért lesz felelős.

Ha verziókezelést használunk érdemes létrehozni a `.gitignore` fájlt és megadni, hogy a `vendor` mappa ne legyen
követve.

## Faker
A [Faker](https://github.com/FakerPHP/Faker) egy random adat generátor PHP-hoz. Egy minimális demó megtalálható az
`index.php` fájlban.