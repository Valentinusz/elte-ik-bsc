class ToastMessage extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});

        this.shadowRoot.appendChild(document.querySelector('template#toast-message-template').cloneNode());
    }
}

customElements.define('toast-message', ToastMessage);