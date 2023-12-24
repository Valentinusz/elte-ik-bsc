//3.a
class LengthyInput extends HTMLInputElement {
    _color;

    constructor() {
        super();
        this._color = this.dataset.color ?? 'orange'; //3.c
        this.addEventListener('input', this.updateBorder);
        this.dispatchEvent(new Event('input'));
    }

    updateBorder = () => {
        const current = this.value.length;
        console.log(`Jelenlegi hossz: ${current}, Maximális hossz: ${this.maxLength}`);

        // 3.b
        const percentage = (current / this.maxLength) * 100;
        this.style.borderImageSource = `linear-gradient(to right, ${this._color} ${percentage}%, hsla(0, 0%, 90%, 1) ${percentage}% 100%)`
    }

    //3.d
    get color() {
        return this._color;
    }

    set color(newColor) {
        this._color = newColor;
        this.dataset.color = this._color;
        this.updateBorder();
    }
}

//3.a
customElements.define('lengthy-input', LengthyInput, {extends: 'input'});

// színváltozás teszt
const input = document.querySelector('input[is="lengthy-input"]');
input.color = 'blue';


setTimeout(() => {
    input.color = 'goldenrod'
}, 10000)