const observer = new IntersectionObserver((entries, observer) => {
    entries.forEach(entry => {
        const image = document.querySelector('img');

        if (image != null) {
            if (entry.isIntersecting) {
                let ratio = entry.intersectionRatio * 90;

                if (image.nextSibling == null) {
                    ratio *= -1;
                }

                image.style.transform = `rotateY(${ratio}deg)`
            } else {
                image.style.transform = 'none';
            }
        }
    })
}, {threshold: [0.2, 0.4, 0.6, 0.8, 1.0]})


document.querySelectorAll('section div').forEach(div => {
    observer.observe(div);
});