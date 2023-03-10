const statusBar = document.querySelector('hr#status-bar');
statusBar.style.width = calculateStatusBarWidth();

function calculateStatusBarWidth() {
    return ((window.scrollY + window.innerHeight) / document.body.scrollHeight) * 100 + '%'
}

function scrollHandler() {
    statusBar.style.width = calculateStatusBarWidth();
}

/** @see https://lodash.com/docs/#throttle */
document.addEventListener('scroll', _.throttle(scrollHandler, 1000));

