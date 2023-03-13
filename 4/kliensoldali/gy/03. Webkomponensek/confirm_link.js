class ConfirmLink extends HTMLAnchorElement {
    constructor() {
        super();
    }

    // DOM-ba csatoláskor hívódik meg
    connectedCallback() {
        this.addEventListener('click', this.onClick);
    }

    // DOM-ból törléskor hívódik meg
    disconnectedCallback() {
        this.removeEventListener('click', this.onClick);
    }

    onClick = (e) => {
        if (confirm('Biztos meg akarod nyitni a hivatkozást?')) {
            this.click();
        } else {
            e.preventDefault();
        }
    }
}

customElements.define('confirm-link', ConfirmLink, {extends: 'a'});

const a = document.createElement('a', { is: 'confirm-link'});
document.body.append(a);