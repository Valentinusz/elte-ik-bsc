// komponens definiálás
class ImageCarousel extends HTMLElement {
    images;
    currentImage;

    constructor() {
        super();

        const style = document.createElement('style');

        style.textContent = `
            #contact_wrapper {
              border: 2px solid darkblue;
              background: lightblue;
              width: 800px;
              padding: 10px;
            }
            
            #contact_wrapper ul {
              float: left;
            }
            
            #a_contact {
              width: 400px;
              margin-left: 200px;
              border: 1px solid black;
              background: #cccccc;
              padding: 20px;
            }
            
            #a_contact dt {
              font-weight: bold;
              float: left;
              width: 120px;
            }
            
            carousel {
              position: relative;
              overflow: hidden;
              margin: 0;
              padding: 0px;
              border: 20px solid gray;
              width: 500px;
              height: 333px;
            }
            
            ul {
              position: relative;
              overflow: hidden;
              margin: 0;
              padding: 0;
              top: 0;
              left: 0;
              list-style: none outside none;
            }
            
            li {
              margin: 0;
              padding: 0;
              list-style: none outside none;
              float: left;
            }
            
            controls {
              text-align: center;
              padding: 0;
              margin: 0;
              border: 1px solid gray;
              width: 538px;
            }
        `;

        this.attachShadow({mode: "open"});
        // this light DOM
        // this shadowDOM
        this.shadowRoot.append(this.firstElementChild);
        this.shadowRoot.append(style);

        this.currentImage = 0;
        this.images = this.querySelectorAll('li img');

        console.log(this);
    }
}

customElements.define('image-carousel', ImageCarousel);