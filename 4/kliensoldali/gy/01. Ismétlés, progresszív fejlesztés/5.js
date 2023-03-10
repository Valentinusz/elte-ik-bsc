const navBar = document.querySelector('nav#mainNav');

const navObserver = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
        const link = document.querySelector(`a[href="#${entry.target.id}"]`);

        // toggle-nek megadható opecionális paraméterként egy logikai érték
        // ha true akkor a toggle csak adni ha false akkor csak elvenni tud
        // ez itt nem jó mert előfordulna, hogy feleslegesen válogatnánk ki elemeket a dokumentumból
        link.classList.toggle('active', entry.isIntersecting);
    })
}, {threshold: 0.8})

document.querySelectorAll('nav#mainNav a').forEach(e => {
    const url = new URL(e.href);
    console.log(document.querySelector(url.hash));
    navObserver.observe(document.querySelector(url.hash))
})