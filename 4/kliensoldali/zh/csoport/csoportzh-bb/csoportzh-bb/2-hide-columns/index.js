const controlTemplate = document.querySelector('#columns-template');

class HideColumnTable {
    table;
    columns;
    inputs;

    constructor(table) {
        this.table = table;
        this.columns = [...this.table.querySelectorAll('th')].map(th => th.innerHTML);
        console.log(this.columns);

        this.table.prepend(controlTemplate.content.cloneNode(true));
        const controls = this.table.querySelector('details')

        this.inputs = [];
        for (const columnName of this.columns) {
            const input = document.createElement('input');
            input.type = 'checkbox';
            input.value = columnName;
            controls.append(input);
            controls.append(columnName)
            this.inputs.push(input);
        }

        controls.addEventListener('input', this.onInput);
    }

    onInput = () => {
        for (const inputIndex in this.inputs) {
            const cssIndex = Number.parseInt(inputIndex ,10) + 1;
            this.table.querySelectorAll(`tr > *:nth-child(${cssIndex})`).forEach(cell => {
                cell.classList.toggle('hide', this.inputs[inputIndex].checked);
            })
        }
    }
}

new HideColumnTable(document.querySelector('table'));