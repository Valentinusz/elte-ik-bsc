class HideColumns {
    table;
    columns;
    checkboxes = [];

    constructor(table) {
        this.table = table;
        this.columns = [...this.table.querySelectorAll('thead tr th')];
        console.log(this.columns);

        this.table.prepend(document.querySelector('template#columns-template').content.cloneNode(true));
        const columnFilter = this.table.querySelector('details summary');

        for (const th of this.columns) {
            const label = document.createElement('label');

            const input = document.createElement('input');

            input.type = 'checkbox';
            input.value = th.innerText;
            label.append(input);

            label.innerHTML += th.innerText;
            this.checkboxes.push(label.firstChild);
            
            columnFilter.appendChild(label);
        }

        console.log(this.checkboxes);
        columnFilter.addEventListener('input', this.onInput);
    }

    onInput = () => {
        console.log(this.checkboxes);
        for (const index in this.checkboxes) {
            let i = Number.parseInt(index,10) +1;
            this.table.querySelectorAll(`tr > *:nth-child(${i})`).forEach(e => {

                // nem tudom ez miért false
                if (this.checkboxes[index].checked) {
                    e.classList.add('hide');
                } else {
                    e.classList.remove('hide');
                }
            })
        }
    }
}

new HideColumns(document.querySelector('table'));