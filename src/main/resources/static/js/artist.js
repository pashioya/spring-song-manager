let pageNumberView = document.getElementById("current-page-view");
let firstPageButton = document.getElementById("first-page-button");
let lastPageButton = document.getElementById("last-page-button");
let nextPageButton = document.getElementById("next-page-button");
let previousPageButton = document.getElementById("previous-page-button");


const albumPreviewColumn = document.getElementById("preview-album-names");
/**
 * @param artistID
 */

class artistPageController {
    constructor() {
        this.artists = [];
        this.artistsInView = [];
        this.artistsTableBody = document.getElementById("all-artists-table-body");
        this.artistsTableBody.innerHTML = "";
        this.pageNumber = 0;
        this.totalPages = 0;
    }

    fetchArtists() {
        fetch("/api/artist/artists",
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
                    this.artists = data;
                    this.totalPages = Math.round(Math.floor(this.artists.length / 10)) - 1;
                    this.renderArtists();
                }
            )
    }
    fetchArtistsAlbums(artistID){

        fetch(`/api/album/artist/${artistID}/albums`,
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
                    data.forEach(album => {
                        let albumItem = document.createElement("tr");
                        albumItem.innerHTML = album.albumName;
                        albumPreviewColumn.appendChild(albumItem);
                    });
                }
            )
    }

    setOnHover() {
        let allRows = document.getElementsByClassName("entity");
        for (let row of allRows) {
            row.addEventListener("mouseover", () => {
                albumPreviewColumn.innerHTML = "";
                let id = row.getAttribute("data-href").split("/")[3];
                this.fetchArtistsAlbums(id);
            });
        }

    }

    renderArtists() {
        pageNumberView.innerHTML = this.pageNumber;
        this.artistsTableBody.innerHTML = "";
        this.artistsInView = this.artists.slice(this.pageNumber * 10, (this.pageNumber * 10) + 10);
        this.artistsInView.forEach(artist => {
            let artistRow = document.createElement("tr");
            artistRow.setAttribute("data-href", `/allArtists/fullArtist/${artist.id}`);
            artistRow.classList.add("table-row");
            artistRow.classList.add("entity");
            artistRow.innerHTML = `
                <td>${artist.name}</td>
                <td>${artist.artistFollowers}</td>
            `;
            // set the row to be clickable
            artistRow.addEventListener("click", () => {
                //     on click, redirect to the album page
                window.location.href = artistRow.getAttribute("data-href");
            });
            this.artistsTableBody.appendChild(artistRow);
        });
        this.setOnHover();
    }

    init() {
        this.fetchArtists();
        console.log("page controller initialized");
    }

    firstPage() {
        this.pageNumber = 0;
        this.renderArtists();
    }

    lastPage() {
        this.pageNumber = this.totalPages;
        this.renderArtists();
    }
    nextPage() {
        if(this.pageNumber > this.totalPages) {
            this.renderArtists();
            return;
        }
        this.pageNumber++;
        this.renderArtists();
    }


    previousPage() {
        if (this.pageNumber === 0) {
            this.renderArtists();
            return;
        }
        this.pageNumber--;
        this.renderArtists();
    }
}

let artistPage = new artistPageController();
artistPage.init();

firstPageButton.addEventListener("click", () => {
    artistPage.firstPage();
});

lastPageButton.addEventListener("click", () => {
    artistPage.lastPage();
});

nextPageButton.addEventListener("click", () => {
    artistPage.nextPage();
});

previousPageButton.addEventListener("click", () => {
    artistPage.previousPage();
});






