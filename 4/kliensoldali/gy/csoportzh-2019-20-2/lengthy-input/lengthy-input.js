class LengthyInput extends HTMLInputElement {
    _color;

    constructor() {
        super();
        this._color = this.dataset.color ?? 'orange';
        this.addEventListener('input', this.updateBorder);
        this.dispatchEvent(new Event('input'));
    }

    updateBorder = () => {
        const current = this.value.length;
        console.log(`Jelenlegi hossz: ${current}, Maximális hossz: ${this.maxLength}`);
        const percentage = (current / this.maxLength) * 100;
        console.log(`linear-gradient(to right, ${this._color} ${percentage}%, hsla(0, 0%, 90%, 1) ${percentage}% 100%);`);
        this.style.borderImageSource = `linear-gradient(to right, ${this._color} ${percentage}%, hsla(0, 0%, 90%, 1) ${percentage}% 100%)`
    }

    get color() {
        return this._color;
    }

    set color(newColor) {
        this._color = newColor;
        this.dataset.color = this._color;
        this.updateBorder();
    }
}

customElements.define('lengthy-input', LengthyInput, {extends: 'input'});

const input = document.querySelector('input[is="lengthy-input"]');
input.color = 'blue';
console.log(input.color);

setTimeout(() => {
    input.color = 'goldenrod'
}, 10000)