class ImageCarousel extends HTMLDivElement {
    images;
    currentImage;

    constructor() {
        super();

        this.attachShadow({mode: 'open'});
        // létrehozzuk a shadow DOM-ot
        // open azt jelenti, hogy kívülről be tudunk nyúlni a shadow DOM-ba JS-el

        this.shadowRoot.append(this.firstElementChild);
        // áthelyezzük az <ul> -t a shadow DOM-ba

        const style = document.createElement('link');
        style.rel = 'stylesheet';
        style.href = './image-carousel.css';
        this.shadowRoot.append(style);
        // stílus csak a shadow DOM-ra hat

        this.currentImage = 0;
        this.images = this.shadowRoot.querySelectorAll('li img');
        this.images.forEach(image => this.hide(image));

        window.addEventListener('keydown', this.carouselMove);
    }

    hide(image) {
        image.style.display = 'none';
    }

    show(image) {
        image.style.display = '';
    }


    carouselMove = (e) => {
        this.hide(this.images[this.currentImage]);

        switch (e.key) {
            case 'Left': // IE/Edge specific value
            case 'ArrowLeft':
                this.currentImage--;
                break;
            case 'Right': // IE/Edge specific value
            case 'ArrowRight':
                this.currentImage++;
                break;
            default:
                return;
        }

        // gonosz kód
        this.currentImage = ((this.currentImage % this.images.length) + this.images.length) % this.images.length;
        this.show(this.images[this.currentImage]);

        e.preventDefault();
    };
}

customElements.define('image-carousel', ImageCarousel, {extends: 'div'});