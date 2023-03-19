const messageTemplate = document.querySelector('template#toast-message-template');

class ToastMessage extends HTMLElement {
    constructor() {
        super();

        //3.b
        this.attachShadow({mode: 'open'});

        //3.c
        this.shadowRoot.appendChild(messageTemplate.content.cloneNode(true));
        this.initializeCloseButton();
        this.initializeTimeout();
    }

    initializeCloseButton() {

        if ('closable' in this.attributes) {
            // 3.e
            this.shadowRoot.querySelector('button.close').addEventListener('click', () => {
                this.remove();
            })

        // 3.d
        } else {
            this.shadowRoot.querySelector('button.close').remove();
        }
    }

    initializeTimeout() {
        if ('timeout' in this.attributes) {
            // 3.g, 3.h
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
            // 3.f
            this.shadowRoot.querySelector('progress').remove();
        }
    }
}

// 3.a
customElements.define('toast-message', ToastMessage);