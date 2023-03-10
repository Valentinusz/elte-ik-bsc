const numberObserver = new IntersectionObserver((entries) => {
    entries.forEach(e => {
        if (e.isIntersecting) {
            const numberDisplay = e.target.children[0];

            if (numberDisplay.dataset.amountCurrent !== numberDisplay.dataset.amountEnd) {
                let i = parseInt(numberDisplay.dataset.amountStart, 10);
                let timer = setInterval(() => {
                    i++;
                    numberDisplay.dataset.amountCurrent = i.toString();

                    if (i === parseInt(numberDisplay.dataset.amountEnd, 10)) {
                        clearInterval(timer);
                    }
                }, 1)
            }
        }
    })
}, {threshold: 0.5})

document.querySelectorAll('.number-counter').forEach(e => numberObserver.observe(e));