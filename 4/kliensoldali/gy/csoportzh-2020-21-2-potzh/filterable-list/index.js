class FilterableList {
    parentDiv;
    ul;
    input;

    constructor(ul) {
        this.ul = ul;

        this.parentDiv = document.createElement('div');
        this.parentDiv.classList.add('list-container');

        this.input = document.createElement('input');

        this.ul.parentNode.insertBefore(this.parentDiv, this.ul);

        this.parentDiv.append(this.input);
        this.parentDiv.append(this.ul);

        this.input.addEventListener('input', this.onInput);
    }

    onInput = () => {
        for (const li of this.ul.children) {
            li.hidden = !(li.innerText.includes(this.input.value));
        }
    }
}

new FilterableList(document.querySelector('ul'));