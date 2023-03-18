document.querySelector('#btn-new-content').style.display = 'none';

window.addEventListener('scroll', _.throttle(onScroll, 1000))

function onScroll(event) {
    if ((window.innerHeight + window.scrollY) >= document.body.scrollHeight) {
        console.log('End of page');
        const clone = document.querySelector('#newRow').content.cloneNode(true);
        document.body.appendChild(clone);

        if (window.innerHeight > document.body.scrollHeight) {
            onScroll();
        }
    }
}

onScroll();