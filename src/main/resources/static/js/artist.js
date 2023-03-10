let pageNumberView = document.getElementById("current-page-view");
let firstPageButton = document.getElementById("first-page-button");
let lastPageButton = document.getElementById("last-page-button");
let nextPageButton = document.getElementById("next-page-button");
let previousPageButton = document.getElementById("previous-page-button");


const albumPreviewColumn = document.getElementById("preview-album-names");
let allRows = document.getElementsByClassName("entity");

/**
 * @param artistID
 */


class pageController {
    constructor() {
        this.artists = [];
        this.artistsInView = [];
        this.artistsTable = document.getElementById("all-artists-table");
        this.artistsTableBody = document.getElementById("all-artists-table-body");
        this.artistsTableBody.innerHTML = "";
        this.pageNumber = 0;
        this.totalPages = 0;
    }

    fetchArtists() {
        fetch("/api/artists",
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
                    this.totalPages = Math.round(Math.floor(this.artists.length / 10));
                    this.renderArtists();
                }
            )
    }

    setOnHover() {
        let allRows = document.getElementsByClassName("entity");
        for (let row of allRows) {
            row.addEventListener("mouseover", () => {
                albumPreviewColumn.innerHTML = "";
                let id = row.getAttribute("data-href").split("/")[3];
                console.log("hovering over row: " + id);
                fetchArtistsAlbums(id);
            });
        }

    }

    renderArtists() {
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
            this.artistsTableBody.appendChild(artistRow);
        });
        pageNumberView.innerHTML = page.pageNumber;
        this.setOnHover();
        console.log(allRows);
    }

    init() {
        this.fetchArtists();
        console.log("page controller initialized");
    }

    firstPage() {
        this.pageNumber = 0;
        this.fetchArtists();
    }

    lastPage() {
        this.pageNumber = this.totalPages;
        this.fetchArtists();
    }
    nextPage() {
        this.pageNumber++;
        this.fetchArtists();
    }


    previousPage() {
        this.pageNumber--;
        this.fetchArtists();
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


function fetchArtistsAlbums(artistID){

    fetch(`/api/artist/${artistID}/albums`,
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
                console.log(data);
                data.forEach(album => {
                    let albumItem = document.createElement("tr");
                    albumItem.innerHTML = album.albumName;
                    albumPreviewColumn.appendChild(albumItem);
                });
            }
        )
}



