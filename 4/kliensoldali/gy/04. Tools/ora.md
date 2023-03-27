# 05. Tooling

## Node.js
Parancssori JavaScript

### NPM
Csomagkezelő rendszer. `package.json` tartalmazza a külső függvények listáját.

A függőségek a következő paranccsal telepíthetőek:
```shell
npm install
```

`package.json` létrehozása:
```shell
npm init
```

Általában akkor használjuk ha mi akarunk csomagot létrehozni.
Preferált mód:

```shell
npm install <csomagnév>
```

A `--save-dev` kapcsoló megadásával adható meg, hogy egy csomag fejlesztési környezteben függőség csak.

## Babel
Transpiler (kód átfordító eszköz), ES verziók közt tudunk átfordítani

```shell
npm install --save-dev @babel/core @babel/cli
npm install @babel/preset-env --save-dev
```

Definiálni kell egy konfigurációs fájlt
```json
{
  "presets": ["@babel/preset-env"]
}
```


Fájl átfordítása:
```shell
npx babel script.js
```

## Csomagoló eszközök
Probléma: sok fájlt betölteni költséges. Megoldás: csomagoljuk össze a fájlokat egy naggyá. Ezt a folyamatot
(a fájl tömörítése, minifikálása és obfuszkálása mellett) végzik el az ún. **bundlerek**.
### [Webpack](https://webpack.js.org/)
### [Rollup](https://rollupjs.org/)
### [esbuild](https://esbuild.github.io/)

