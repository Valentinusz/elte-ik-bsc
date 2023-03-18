const button = document.querySelector('button');
button.disabled = true;

const container = document.querySelector('#container');
container.addEventListener('scroll', _.throttle(onScroll, 1000))

function onScroll() {
    if ((container.clientHeight + container.scrollTop) >= container.scrollHeight) {
        button.disabled = false;
        container.removeEventListener('scroll', onScroll)
    }
}

container.dispatchEvent(new Event('scroll'));