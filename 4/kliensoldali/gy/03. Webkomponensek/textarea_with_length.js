class TextareaWithLength extends HTMLElement {
    textArea;
    lengthDisplay;

    constructor() {
        super();
        this.textArea = this.querySelector('textarea');
        this.lengthDisplay = document.createElement('span');
        this.textArea.addEventListener('input', this.onInput);
        this.textArea.dispatchEvent(new Event('input'));
        this.append(this.lengthDisplay);

    }

    onInput = (e) => {
        console.log("a");
        this.lengthDisplay.innerHTML = this.textArea.value.length.toString();
    }
}

customElements.define('textarea-with-length', TextareaWithLength)