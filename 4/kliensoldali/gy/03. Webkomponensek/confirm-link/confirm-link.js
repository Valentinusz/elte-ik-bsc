/** Mivel az <a> elem funkcionalitását terjesztjük ki ezért az osztálynak abból kell öröklődnie. */
class ConfirmLink extends HTMLAnchorElement {
    constructor() {
        super();
    }

    // DOM-ba csatoláskor hívódik meg
    connectedCallback() {
        this.addEventListener('click', this.onClick);
    }

    // DOM-ból való törléskor hívódik meg
    disconnectedCallback() {
        this.removeEventListener('click', this.onClick);
    }

    onClick = e => {
        if (!confirm('Biztos meg akarod nyitni a hivatkozást?')) {
            e.preventDefault();
        }
    };
}

// meg kell adni itt is mit terjeszt ki a custom elem
customElements.define('confirm-link', ConfirmLink, {extends: 'a'});

const a = document.createElement('a', {is: 'confirm-link'});
a.innerHTML = 'elte';
a.href = 'https://www.elte.hu';
document.body.append(a);