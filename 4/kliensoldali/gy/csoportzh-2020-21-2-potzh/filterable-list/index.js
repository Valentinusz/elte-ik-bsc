class FilterableList {
    list;
    filterInput;

    constructor(list) {
        this.list = list;
        this.filterInput = document.createElement('input');

        this.list.parentNode.insertBefore(this.filterInput, this.list);
        this.filterInput.addEventListener('input', this.onInput);
    }

    onInput = () => {
        for (const li of this.list.children) {
            li.style.display = li.innerHTML.includes(this.filterInput.value) ? '' : 'none';
        }
    }
}

new FilterableList(document.querySelector('ul:first-of-type'));
