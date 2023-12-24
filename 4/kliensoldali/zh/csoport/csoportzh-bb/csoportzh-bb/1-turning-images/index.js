const observer = new IntersectionObserver((entries, observer) => {
    entries.forEach(entry => {
        if (entry.target.matches('div')) {
            const image = entry.target.querySelector('img');
            console.log(entry.target.firstChild);
            const rotationDegreeMultiplier = image === entry.target.children[0] ? -1 : 1 ;
            if (image != null) {
                if (entry.isIntersecting) {
                    const degree = rotationDegreeMultiplier * (1 - entry.intersectionRatio) * 90
                    image.style.transform = `rotateY(${degree}deg)`;
                } else {
                    image.style.transform = 'none';
                }
            }
        }
    })
}, {threshold: [0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0]})

document.querySelectorAll('div').forEach(div => {
    observer.observe(div);
})