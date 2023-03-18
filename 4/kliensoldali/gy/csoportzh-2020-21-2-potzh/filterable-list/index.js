class FilterableList {
    parent;
    ul;
    input;

    constructor(ul) {
        this.ul = ul;

        this.parent = document.createElement('div');
        this.parent.classList.add('list-container')

        this.input = document.createElement('input');

        this.ul.parentNode.insertBefore(this.parent, this.ul);

        this.parent.append(this.input);
        this.parent.append(this.ul)

        this.input.addEventListener('input', this.onInput);
    }

    onInput = () => {
        for (const li of this.ul.children) {
            li.hidden = !(li.innerText.includes(this.input.value));
        }
    }
}

new FilterableList(document.querySelector('ul'));