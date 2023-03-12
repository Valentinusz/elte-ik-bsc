{
    const element = document.querySelector('table');
    console.log(element);

    const rows = [...element.querySelectorAll('tr:not(:first-child)')];

    element.addEventListener('click', evt => {
        if (evt.target.matches('th')) {
            rows.forEach(e => {
                e.remove();
            });

            rows.sort((row1, row2) => {
                return row1.children[evt.target.cellIndex].innerText.localeCompare(row2.children[evt.target.cellIndex].innerText);
            });

            rows.forEach(e => element.appendChild(e));
        }
    })
}