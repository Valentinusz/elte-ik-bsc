const messageTemplate = document.querySelector('template#toast-message-template');

class ToastMessage extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        this.shadowRoot.appendChild(messageTemplate.content.cloneNode(true));
        this.initializeClose();
        this.initializeTimeout();
    }

    initializeClose() {
        if ('closable' in this.attributes) {
            this.shadowRoot.querySelector('button.close').addEventListener('click', () => {
                this.remove();
            })

        } else {
            this.shadowRoot.querySelector('button.close').remove();
        }
    }

    initializeTimeout() {
        if ('timeout' in this.attributes) {
            const progress = this.shadowRoot.querySelector('progress');

            progress.value = 0;
            progress.setAttribute('max', this.getAttribute('timeout'));

            const interval = setInterval(() => {
                if (progress.value >= progress.max) {
                    this.remove();
                    clearInterval(interval)
                }
                progress.value++;
            }, 1000);
        } else {
            this.shadowRoot.querySelector('progress').remove();
        }
    }
}

customElements.define('toast-message', ToastMessage);