class GUI {
    constructor() {
        this.xml = null;
        this.eBook = null;
        this.eChapter = null;
        this.eVerseFrom = null;
        this.eVerseTo = null;
    }
    registerEvents() {
        this.eBook = document.querySelector("#livro");
        this.eBook.onchange = this.populateChapters.bind(this);
        this.eChapter = document.querySelector("#capitulo");
        this.eChapter.onchange = this.populateVerses.bind(this);
        this.eVerseFrom = document.querySelector("#versiculoDe");
        this.eVerseFrom.onchange = this.showVerse.bind(this);
        this.eVerseTo = document.querySelector("#versiculoPara");
        this.eVerseTo.onchange = this.showVerse.bind(this);
        let xhr = new XMLHttpRequest();
        xhr.responseType = "json";
        xhr.onload = evt => {
            this.xml = evt.target.response;
            this.populateBooks();
        };
        let url = new URL("execute");
        url.searchParams.append();
        xhr.open("get", "execute");
        xhr.send();
    }
}
let gui = new GUI();
gui.registerEvents();