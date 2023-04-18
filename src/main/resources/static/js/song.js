let pageNumberView = document.getElementById("current-page-view");
let firstPageButton = document.getElementById("first-page-button");
let lastPageButton = document.getElementById("last-page-button");
let nextPageButton = document.getElementById("next-page-button");
let previousPageButton = document.getElementById("previous-page-button");

class songPageController {

    constructor() {
        this.songs = [];
        this.songsInView = [];
        this.songsTableBody = document.getElementById("all-songs-table-body");
        this.songsTableBody.innerHTML = "";
        this.pageNumber = 0;
        this.totalPages = 0;
    }

    fetchSongs() {
        fetch("/api/song/songs", {
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
            })
            .then(data => {
                this.songs = data;
                this.totalPages = Math.round(Math.floor(this.songs.length / 10)) - 1;
                this.renderSongs();
            });
    }

    renderSongs() {
        pageNumberView.innerHTML = this.pageNumber;
        this.songsTableBody.innerHTML = "";
        this.songsInView = this.songs.slice(this.pageNumber * 10, (this.pageNumber * 10) + 10);
        this.songsInView.forEach(song => {
            let row = document.createElement("tr");
            row.setAttribute("data-href", "/allSongs/fullSong/" + song.id);
            row.classList.add("table-row");
            row.innerHTML = `
                <td>${song.songTitle}</td>
                <td>${song.trackNumber}</td>
                <td>${song.durationMs}</td>
                <td>${song.explicit}</td>
            `;
            // set the row to be clickable
            row.addEventListener("click", () => {
                //     on click, redirect to the album page
                window.location.href = row.getAttribute("data-href");
            });
            this.songsTableBody.appendChild(row);
        });
    }

    init() {
        this.fetchSongs();
        console.log("page controller initialized");
    }

    firstPage() {
        this.pageNumber = 0;
        this.renderSongs();
    }

    lastPage() {
        this.pageNumber = this.totalPages;
        this.renderSongs();
    }
    nextPage() {
        if(this.pageNumber > this.totalPages) {
            this.renderSongs();
            return;
        }
        this.pageNumber++;
        this.renderSongs();
    }


    previousPage() {
        if (this.pageNumber === 0) {
            this.renderSongs();
            return;
        }
        this.pageNumber--;
        this.renderSongs();
    }
}

let songPage = new songPageController();
songPage.init();

firstPageButton.addEventListener("click", () => {
    songPage.firstPage();
});

lastPageButton.addEventListener("click", () => {
    songPage.lastPage();
});

nextPageButton.addEventListener("click", () => {
    songPage.nextPage();
});

previousPageButton.addEventListener("click", () => {
    songPage.previousPage();
});
