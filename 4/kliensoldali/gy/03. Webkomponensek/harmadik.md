# Webkomponensek
1. Custom HTML elemek
   A HTML szabvány lehetővé teszi hogy saját elemeket definiáljunk, ez a megközelítés viszont szembe megy a progresszív
   fejlesztés eszméjével. Mivel JS hiányában ezek csak sima divkéént működnek.
2. Shadow DOM
   egy komponenshez egy saját a külső világtól leválasztottt domot adhatunk. Ha egy elemehez hozzá van csatolba egy shadow DOM akkor csak azt jeleníti meg a a további HTML-t (ún. light DOM) figyelmen kívül hagyja. Shadow dom nagyon erős nem enged be külső css-t és még a custom elementen kívüliek a sahdow domban lévő űrlap mezőket sem látják. slot 