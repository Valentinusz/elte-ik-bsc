//1.a
document.querySelector('#btn-new-content').remove();

window.addEventListener('scroll', _.throttle(onScroll, 1000))

function onScroll() {
    if ((window.innerHeight + window.scrollY) >= document.body.scrollHeight) {
        // 1.b
        console.log('End of page');

        // 1.c, 1.d
        document.body.appendChild(document.querySelector('#newRow').content.cloneNode(true));
    }
}