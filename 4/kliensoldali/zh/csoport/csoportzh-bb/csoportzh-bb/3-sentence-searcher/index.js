// Utility function for processing the inner HTML of paragraphs
function filterSentences(content, val) {
    return content
        .replace(/[.?!]”?(?=[\n <]+)/g, "$& <br>")
        .split("<br>")
        .filter(sen => sen.includes(val))
        .map(sen => sen.replaceAll(val, "<mark>$&</mark>"))
        .join(' <span class="separator">...</span> ');
}

class SentenceSearcher extends HTMLDivElement {
    searchInput;
    originalContent;

    constructor() {
        super();


        console.log(this);

        this.searchInput = document.createElement('input');
        this.searchInput.classList.add('searchable-text-input');
        this.searchInput.addEventListener('input', this.onInput)
        this.originalContent = this.innerHTML;

        this.insertAdjacentElement('beforebegin', this.searchInput);
    }

    onInput = () => {
        this.innerHTML = this.originalContent;

        if (this.searchInput.value !== '') {
            this.querySelectorAll('p').forEach(p => {
                p.innerHTML = filterSentences(p.innerHTML, this.searchInput.value);
            })
        }
    }
}

customElements.define('sentence-searcher', SentenceSearcher, {extends: 'div'});

