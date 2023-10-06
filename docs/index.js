class GUI {
    constructor() {
        this.xml = null;
        this.eBook = null;
        this.eChapter = null;
        this.eVerseFrom = null;
        this.eVerseTo = null;
    }
    populateBooks() {
        let books = this.xml.querySelectorAll("book");
        for (const book of books) {
            this.eBook.add(new Option(book.getAttribute("name"), book.getAttribute("abbrev")));
        }
        this.populateChapters();
    }
    populateChapters() {
        this.eChapter.innerHTML = "";
        let bookAbbrev = this.eBook.value;
        let book = this.xml.querySelector(`book[abbrev='${bookAbbrev}']`);
        let chapters = book.querySelectorAll(`c`);
        for (const c of chapters) {
            this.eChapter.add(new Option(c.getAttribute("n"), c.getAttribute("n")));
        }
        this.populateVerses();
    }
    populateVerses() {
        this.eVerseFrom.innerHTML = "";
        this.eVerseTo.innerHTML = "";
        let bookAbbrev = this.eBook.value;
        let chapterNumber = this.eChapter.value;
        let chapter = this.xml.querySelector(`book[abbrev='${bookAbbrev}'] c[n='${chapterNumber}']`);
        let verses = chapter.querySelectorAll(`v`);
        for (const v of verses) {
            this.eVerseFrom.add(new Option(v.getAttribute("n"), v.getAttribute("n")));
            this.eVerseTo.add(new Option(v.getAttribute("n"), v.getAttribute("n")));
        }
        this.showVerse();
    }
    showVerse() {
        let bookAbbrev = this.eBook.value;
        let chapterNumber = this.eChapter.value;
        let verseNumberFrom = this.eVerseFrom.value;
        let verseNumberTo = this.eVerseTo.value;
        let from = parseInt(verseNumberFrom), to = parseInt(verseNumberTo);
        let response = document.querySelector("#response");
        response.innerHTML = "";
        for (let index = from; index <= to; index++) {
            let verse = this.xml.querySelector(`book[abbrev='${bookAbbrev}'] c[n='${chapterNumber}'] v[n='${index}']`);
            let p = document.createElement("p");
            p.innerHTML = `<sup>${index}</sup>` + verse.textContent;
            response.appendChild(p);
        }
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
        xhr.responseType = "document";
        xhr.onload = evt => {
            this.xml = evt.target.response;
            this.populateBooks();
        };
        xhr.open("get", "Biblia.xml");
        xhr.send();
    }
}
let gui = new GUI();
gui.registerEvents();