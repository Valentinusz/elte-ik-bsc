class MarkDownGallery {
    textArea;
    list;
    images;

    constructor(textArea) {
        this.list = document.createElement('ul');
        this.textArea = textArea;
        this.textArea.insertAdjacentElement('afterend', this.list);
        this.textArea.addEventListener('input', _.throttle(this.onInput, 500));
        this.images = this.getImages();
        this.updateGallery();
    }

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

    onInput = () => {
        const images = this.getImages();

        if (JSON.stringify(this.images) !== JSON.stringify(images)) {
            this.images = images;
            this.updateGallery();
        }
    }

    getImages() {
        const images = [];

        for (const url of this.textArea.value.matchAll( /!\[\]\((.*?)\)/g )) {
            images.push(url.toString().substring(4));
        }

        return images;
    }
}

new MarkDownGallery(document.querySelector("textarea[data-markdown]"))
