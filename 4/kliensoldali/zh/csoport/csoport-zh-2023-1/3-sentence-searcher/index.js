
// Utility function for processing the inner HTML of paragraphs
function filterSentences(content, val) {
  return content
    .replace(/[.?!]”?(?=[\n <]+)/g, "$& <br>")
    .split("<br>")
    .filter(sen => sen.includes(val))
    .map(sen => sen.replaceAll(val, "<mark>$&</mark>"))
    .join(' <span class="separator">...</span> ');
}

class SentenceSearcher extends HTMLElement {
  innerHTMLBackup;

  constructor() {
    super();
    const input = document.createElement('input');
    input.classList.add('searchable-text-input');
    this.insertAdjacentElement('beforebegin', input)

    input.addEventListener('input', this.onInput);

    this.innerHTMLBackup = this.innerHTML;


  }

  onInput = (e) => {
    this.innerHTML = this.innerHTMLBackup;

    this.querySelectorAll('p').forEach(paragraph => {
      paragraph.innerHTML = filterSentences(paragraph.innerHTML, e.target.value);
    })
  }
}

customElements.define('sentence-searcher', SentenceSearcher, {extends: 'section'});