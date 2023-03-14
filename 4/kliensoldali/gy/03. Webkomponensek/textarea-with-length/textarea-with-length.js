/**
 * Minden HTML elemnek megfelel egy DOM osztály, amit saját komponens definiálásánál ki tudunk terjeszteni.
 * Autonóm elemeknél mivel nem kívánunk semmilyen megörökölni ezért csak a HTMLElement osztályt terjesztjük ki.
 */
class TextareaWithLength extends HTMLElement {
    textArea;
    lengthDisplay;

    constructor() {
        // JS-ben nincs implicit super hívás
        super();
        this.textArea = this.querySelector('textarea');
        this.lengthDisplay = document.createElement('span');
        this.textArea.addEventListener('input', this.onInput);

        // vagy ha onInput egy sima metódus (függvényliterál) helyett, akkor
        // this.textArea.addEventListener('input', this.onInput.bind(this));

        // mesterséges Event generálás
        this.textArea.dispatchEvent(new Event('input'));
        this.append(this.lengthDisplay);
    }

    /*
    anonim függvény nem változtatja meg a this mire köt (ha csak egy sima metódus lenne ez akkor a 14. sorban lévő
    eseménykezelő megváltoztatná a this-t az esemény célpontjára
     */
    onInput = () => {
        this.lengthDisplay.innerHTML = this.textArea.value.length.toString();
    }
}

//
customElements.define('textarea-with-length', TextareaWithLength)