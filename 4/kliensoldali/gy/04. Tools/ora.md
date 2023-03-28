# 04. Eszközök

## Node.js
JavaScript futtatókörnyezet.

REPL környezet indítása:
```shell
node
```

JavaScript fájl futtatása:
```shell
node <fájlnév>
```

## NPM
A Node.js környezet csomagkezelő rendszere.

Teljes parancslista és használati utasítás:
```shell
npm
```


Csomag telepítése:
```shell
npm install <csomagnév>
```

Az npm install első futtatásakor a következő fájlokat hozza létre:
- `node_molues` a telepített csomagok kódjait tartalmazó mappa. Tilos verzió kezelni!
- `package.json` a projekttel kapcsolatos metaadatokat tartalmaz pl. a telepített függőség listája.
- `package-lock.json` automatikusan generált fájl a `package.json` kibővített (teljes függőségi fa megjelenik) pontos
változata.

`package.json` példa:
```json
{
  "dependencies": {
    "cowsay": "^1.5.0"
  }
}
```

Lehetőségünk van saját csomagot létrehozni:
```shell
npm init
```

### Függőségek
Függőségeket megkülönböztetjük az alapján, hogy szükség van a rajtuk production-ben. Az, hogy egy csomag dev dependency
a telepítés során a `--save-dev` kapcsolóval adható meg.

Például:
```shell
npm install --save-dev @babel/core @babel/cli
```
`package.json`:
```json
{
  "dependencies": {
    "cowsay": "^1.5.0"
  },
  "devDependencies": {
    "@babel/preset-env": "^7.20.2"
  }
}
```

## Modul formátumok
### CommonJS
```js
// math.js
const add = (a, b) => a + b

module.exports = add // export one value
module.exports.add = add // export multiple values
module.exports = { add } // shorthand

// app.js
const { add } = require('./math')
console.log(add(3, 5));
```

### ES2015 module
```js
// math.js
export const add = (a, b) => a + b

// app.js
import { add } from "./math.js";
console.log(add(3, 5));
``` 
Az NPM csomagok alapból CommonJS formátumot használják, ez a átváltható ESM modulokra a `package.json` kibővítésével:
```json
{
  "type": "module",
  "dependencies": {
    "cowsay": "^1.5.0"
  },
  "devDependencies": {
    "@babel/preset-env": "^7.20.2"
  }
}
```

## Babel
Transpiler (kód átfordító eszköz), például újabb még böngésző által nem támogatott elemeket tartalmazó kódot tudunk
régebbi verzióra fordítani.

```shell
npm install --save-dev @babel/core @babel/cli
npm install @babel/preset-env --save-dev
```

Definiálni kell egy `babel.config.json` konfigurációs fájlt:
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
Probléma: sok fájlt betölteni költséges.

Megoldás: csomagoljuk össze a fájlokat egy naggyá. Ezt a folyamatot
(a fájl tömörítése, minifikálása és obfuszkálása mellett) végzik el az ún. **bundlerek**.
- ### [Webpack](https://webpack.js.org/)
- ### [Rollup](https://rollupjs.org/)
- ### [esbuild](https://esbuild.github.io/)