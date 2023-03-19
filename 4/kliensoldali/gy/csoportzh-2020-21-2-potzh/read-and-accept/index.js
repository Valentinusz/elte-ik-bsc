const button = document.querySelector('button');
const container = document.querySelector('#container');

// 1.1
button.disabled = true;

// 1.2
function onScroll() {
    if (container.clientHeight + container.scrollTop >= container.scrollHeight) {
        container.removeEventListener('scroll', throttledOnScroll);
        button.disabled = false;
    }
}

// 1.3
const throttledOnScroll = _.throttle(onScroll, 1000);

container.addEventListener('scroll', throttledOnScroll);