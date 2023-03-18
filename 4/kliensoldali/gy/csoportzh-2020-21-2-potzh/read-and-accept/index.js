const button = document.querySelector('button');
const container = document.querySelector('#container');

// 1.1
button.disabled = true;

// 1.2
function onScroll() {
    if (container.clientHeight + container.scrollTop >= container.scrollHeight) {
        container.removeEventListener('scroll', onScroll);
        button.disabled = false;
    }
}

// 1.3
container.addEventListener('scroll', _.throttle(onScroll, 1000));