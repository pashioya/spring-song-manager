
let pageNumberView = document.getElementById("current-page-view");
let firstPageButton = document.getElementById("first-page-button");
let lastPageButton = document.getElementById("last-page-button");
let nextPageButton = document.getElementById("next-page-button");
let previousPageButton = document.getElementById("previous-page-button");

let songPreviewColumn = document.getElementById("preview-song-names");
let allRows = document.querySelectorAll(".table-row");

    /**
     * @param albumID
     */
    function fetchAlbumsSongs(albumID){

        fetch(`/api/album/${albumID}/songs`,
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
                    data.forEach(song => {
                        let songItem = document.createElement("tr");
                        songItem.setAttribute("scope","row");
                        songItem.innerHTML = song.songTitle;
                        songPreviewColumn.appendChild(songItem);
                    });
                }
            )
    }

    class pageController {
        constructor() {
            this.albums = [];
            this.albumsInView = [];
            this.albumsTable = document.getElementById("all-albums-table");
            this.albumsTableBody = document.getElementById("all-albums-table-body");
            this.albumsTableBody.innerHTML = "";
            this.pageNumber = 0;
            this.totalPages = 0;
        }

        fetchAlbums() {
            fetch("/api/albums",
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
                    this.albums = data;
                    this.albumsTableBody.innerHTML = "";
                    this.totalPages = Math.round(Math.floor(this.albums.length / 10));
                    this.renderAlbums();
                }
            )
        }

        renderAlbums() {
            pageNumberView.innerHTML = page.pageNumber;
            this.albumsInView = this.albums.slice(this.pageNumber * 10, (this.pageNumber * 10) + 10);
            this.albumsInView.forEach(album => {
                let albumRow = document.createElement("tr");
                albumRow.classList.add("table-row");
                albumRow.classList.add("entity");
                albumRow.setAttribute("data-href", `/allAlbums/fullAlbum/${album.id}`);
                albumRow.innerHTML = `
                    <td>${album.albumName}</td>
                    <td>${album.officialTrackCount}</td>
                    <td>${album.albumStatus}</td>
                    <td>${album.genre}</td>
                    <td>${album.releaseDate}</td>
                `;
                this.albumsTableBody.appendChild(albumRow);
            });
        }

        init() {
            this.fetchAlbums();
            pageNumberView.innerHTML = page.pageNumber;
        }

        firstPage() {
            this.pageNumber = 0;
            this.fetchAlbums();
        }

        lastPage() {
            this.pageNumber = this.totalPages;
            this.fetchAlbums();
        }
        nextPage() {
            this.pageNumber++;
            this.fetchAlbums();
        }


        previousPage() {
            this.pageNumber--;
            this.fetchAlbums();
        }
    }

    let page = new pageController();
    page.init();

    firstPageButton.addEventListener("click", () => {
        console.log("first page button clicked");
        page.firstPage();
    });
    lastPageButton.addEventListener("click", () => {
        console.log("last page button clicked");
        page.lastPage();
    });
    nextPageButton.addEventListener("click", () => {
        page.nextPage();
    });
    previousPageButton.addEventListener("click", () => {
        page.previousPage();
    });

    allRows.forEach(row => {
            row.addEventListener("mouseover", () => {
                songPreviewColumn.innerHTML = "";

                let id = row.getAttribute("data-href").split("/")[3];
                console.log("hovering over row: " + id);
                fetchAlbumsSongs(id);
            });
        }
    );



