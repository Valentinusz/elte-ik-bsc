function enhanceInterval() {
    const element = document.querySelector('form');

    const addButton = document.createElement('button');
    addButton.innerText = "+";
    addButton.type = 'button'
    element.appendChild(addButton);

    addButton.addEventListener('click', e => {
        const div = document.createElement('div');
        div.classList.add('idopoint');

        const input1 = document.createElement('input');
        input1.type = 'date';
        input1.name = 'datum[]';
        div.appendChild(input1);

        const input2 = document.createElement('input');
        input2.type = 'time';
        input2.name = 'tol[]';
        div.appendChild(input2);

        const input3 = document.createElement('input');
        input3.type = 'time';
        input3.name = 'ig[]';
        div.appendChild(input3);

        element.insertBefore(div, addButton);
    })

    element.addEventListener('input', evt => {
        if (evt.target.matches('input[type="time"]')) {
            /** @see https://developer.mozilla.org/en-US/docs/Web/API/Element/closest */
            let div = evt.target.closest('div');

            const greater = div.children[1].value > div.children[2].value && div.children[1].value !== '' && div.children[2].value !== '';

            for (let e of div.children) {
                if (e.matches('input[type="time"]')) {
                    e.classList.toggle('wrong', greater)
                }
            }
        }
    })
}

enhanceInterval();

// function expressions
const fun1 = function () {

}

// "arrow function"
const fun2 = () => {

}

// immidiatly invoked function expression (iife)
// jó arra hogy elkerüljük a globális névtér szennyezését
(function ()  {
    console.log("a");
})();

(() => {
    console.log("b");
})();

/** @see https://developer.mozilla.org/en-US/docs/Glossary/IIFE */