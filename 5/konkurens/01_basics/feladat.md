# Konkurens Programozás #1 

1. Készíts 2 szálat (`A` és `B`). `A` írja ki, hogy `"A"`, aludjon másodpercet, majd írja ki, hogy `"A kilép"`. `B`
írja ki, hogy `"B"`, várja meg, míg `A` leáll, majd írja ki `"B végzett A után"`.
2. Készíts 1000 szálat. A nevük legyen `"sz-<n>"`, ahol n a szál sorszáma. Mindegyik szál írja ki a  nevét, és hogy hány
darab élő szál van még az 1000 szál közül.
3. Készíts egy szálat, ami 1 000 000 -szor egy (eredetileg üres) `String`-hez karakterenként hozzáfűzi, hogy
`"I am very slow, but at least you can see how I preform, when I do a slow task on a different thread"`. Az első lépés
előtt a szál írja ki: `"Munka elkezdve"`. Egy másik szál 1.5 másodperc után szakíts meg az első szál működését, majd az
első szál írja ki, hány teljes hozzáfűzés történt, hány karakteres a `String`, és mi az utolsó 500 karaktere.
   1. minden karakter befűzése előtt megszakítható legyen a művelet
   2. csak a teljes sor beszúrása után legyen megszakítható a művelet
   3. csak a 2. megszakítást vegye figyelembe (az jöjjön 0.5 másodperc után), elsőre csak írja ki.
   `"I am not listening."`
4. Készíts 2 szálat. Közösen kezeljenek egy 1 elemű tömböt. Az első szál nézze meg, hogy az elem `null`-e; ha igen,
aludjon 1 másodpercet, majd ezt addig ismételje, amíg az elem nem null-ra változik. Ha az elem null, írja ki
`"Nincs itt semmi"`, ha nem `null`, akkor írja ki `"Az érték: <érték>"`. Ezután végezhet. Egy másik szál véletlenszerűen
várjon 2-5 másodpercet, majd írjon a tömbbe egy értéket (ezután kiléphet).
5. Indíts 5 szálat. A 0., 2., 4. szálak legyen démon szálak, az 1., 3. nem démon szálak. Mindegyik szál írja ki a sorszámát, hogy elindult és az időt, amennyit aludni fog (véletlenszerűen 1-4 másodperc), az alvás végén írja ki, a számát és hogy felébredt. 