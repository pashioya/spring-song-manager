let pageNumberView = document.getElementById("current-page-view");
let firstPageButton = document.getElementById("first-page-button");
let lastPageButton = document.getElementById("last-page-button");
let nextPageButton = document.getElementById("next-page-button");
let previousPageButton = document.getElementById("previous-page-button");



class pageController {

    constructor() {
        this.songs = [];
        this.songsInView = [];
        this.songsTable = document.getElementById("all-songs-table");
        this.songsTableBody = document.getElementById("all-songs-table-body");
        this.songsTableBody.innerHTML = "";
        this.pageNumber = 0;
        this.totalPages = 0;
    }

    fetchSongs() {
        fetch("/api/songs",
            {
                headers: {
                    Accept: "application/json"
                }
            })
            .then(resp => {
                    if (resp.status !== 200) {
                        console.log("Error: " + resp.status);
                    } else {
                        return resp.json();
                    }
                }
            )
            .then(data => {
                    this.songs = data;
                    this.totalPages = Math.round(Math.floor(this.songs.length / 10));
                    this.renderSongs();
                }
            )
    }

    renderSongs() {
        this.songsTableBody.innerHTML = "";
        this.songsInView = this.songs.slice(this.pageNumber * 10, (this.pageNumber * 10) + 10);
        this.songsInView.forEach(song => {
            let row = document.createElement("tr");
            row.setAttribute("data-href", "/allSongs/fullSong/" + song.id);
            row.classList.add("table-row");
            row.innerHTML = `
                <td>${song.songTitle}</td>
                <td>${song.trackNumber}</td>
                <td>${song.durationMS}</td>
                <td>${song.explicit}</td>
            `;
            this.songsTableBody.appendChild(row);
        });
        pageNumberView.innerHTML = this.pageNumber + 1;
    }

    init() {
        this.fetchSongs();
        console.log("page controller initialized");
    }

    firstPage() {
        this.pageNumber = 0;
        this.fetchSongs();
    }

    lastPage() {
        this.pageNumber = this.totalPages;
        this.fetchSongs();
    }
    nextPage() {
        this.pageNumber++;
        this.fetchSongs();
    }


    previousPage() {
        this.pageNumber--;
        this.fetchSongs();
    }
}

let page = new pageController();
page.init();

firstPageButton.addEventListener("click", () => {
    page.firstPage();
});

lastPageButton.addEventListener("click", () => {
    page.lastPage();
});

nextPageButton.addEventListener("click", () => {
    page.nextPage();
});

previousPageButton.addEventListener("click", () => {
    page.previousPage();
});
