// javascript megoldás
document.querySelector('nav#mainNav').addEventListener('click', evt => {
    //alternatív: evt.target.matches()
    if (evt.target instanceof HTMLAnchorElement) {
        evt.preventDefault();
        // URL API használatával könnyen lekérhetőek egy link különböző részei
        const url = new URL(evt.target.href);

        document.querySelector(url.hash).scrollIntoView({behavior: 'smooth'})
    }
})