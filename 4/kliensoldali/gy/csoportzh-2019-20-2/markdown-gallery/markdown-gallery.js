// 2.e
class MarkDownGallery {
    textArea;
    list;
    images;

    constructor(textArea) {
        this.list = document.createElement('ul');
        this.textArea = textArea;

        // 2.a
        this.textArea.insertAdjacentElement('afterend', this.list);
        this.textArea.addEventListener('input', this.onInput);
        this.images = this.getImageFromTextarea();

        // 2.d
        this.updateGallery();
    }

    // 2.c
    updateGallery = () => {
        this.list.innerHTML = '';
        for (const imageURL of this.images) {
            const li = document.createElement('li');
            const image = document.createElement('img');
            image.src = imageURL;
            li.appendChild(image);
            this.list.appendChild(li);
        }
    }

    // 2.b
    onInput = () => {
        const newImages = this.getImageFromTextarea();
        console.log(newImages.length);

        // csak akkor frissítsük a galériát ha megváltozott a képek száma
        if (this.images.length !== newImages.length) {
            this.images = newImages;
            this.updateGallery();
        }
    }

    getImageFromTextarea() {
        const images = [];

        for (const url of this.textArea.value.matchAll( /!\[\]\((.*?)\)/g )) {
            images.push(url.toString().substring(4));
        }

        return images;
    }
}

// Adott egy (vagy akár több)
document.querySelectorAll("textarea[data-markdown]").forEach(e => {
    new MarkDownGallery(e);
})

