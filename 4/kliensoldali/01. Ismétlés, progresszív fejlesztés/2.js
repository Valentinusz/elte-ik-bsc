const menuBar = document.querySelector('nav#mainNav');

function scrollHandler() {
    if (window.scrollY > 200 && !menuBar.classList.contains('navbar-scrolled')) {
        menuBar.classList.toggle('navbar-scrolled');
    } else if (window.scrollY <= 200) {
        menuBar.classList.remove('navbar-scrolled');
    }
}

/**
 * @see https://lodash.com/docs/#throttle
 */
document.addEventListener('scroll', _.throttle(scrollHandler, 1000));
