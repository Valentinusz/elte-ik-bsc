class CascadeSelect {
    categoryInput;
    optionInput;
    categories;
    options;

    constructor(select) {
        this.categoryInput = document.createElement('select');
        this.optionInput = select;
        this.categories = new Map();

        this.options = [];

        // children egy élő gyűjtemény ezért tömbbé kell alakítani
        for (const optionGroup of [...select.children]) {
            const options = [];

            for (const option of optionGroup.children) {
                option.hidden = true;
                options.push(option);
                this.options.push(option);
            }

            this.categories.set(optionGroup.label, options);

            optionGroup.remove();

            const categoryOption = document.createElement('option');
            categoryOption.innerText = optionGroup.label;
            this.categoryInput.appendChild(categoryOption);
        }

        for (const option of this.options) {
            this.optionInput.appendChild(option);
        }

        select.closest('label').insertBefore(this.categoryInput, select);

        this.categoryInput.addEventListener('change', () => {
            this.showSelectedOptions();
        })

        this.showSelectedOptions();
    }

    showSelectedOptions() {
        const valuesToDisplay = this.categories.get(this.categoryInput.value);

        this.options.map(option => option.hidden = !valuesToDisplay.includes(option));
    }
}

new CascadeSelect(document.querySelector('select[name="pets"]'));