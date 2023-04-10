
let pageNumberView = document.getElementById("current-page-view");
let firstPageButton = document.getElementById("first-page-button");
let lastPageButton = document.getElementById("last-page-button");
let nextPageButton = document.getElementById("next-page-button");
let previousPageButton = document.getElementById("previous-page-button");

let songPreviewColumn = document.getElementById("preview-song-names");
let albumPreviewColumn = document.getElementById("preview-song-names");
    /**
     * @param albumID
     */

    const header = document.querySelector('meta[name="_csrf_header"]').content;
    const token = document.querySelector('meta[name="_csrf"]').content;


    class albumPageController {
        constructor() {
            this.albums = [];
            this.albumsInView = [];
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
                        ,[header]: token
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
                    this.albums = data;
                    this.totalPages = Math.floor(Math.floor(this.albums.length / 10)) - 1;
                    this.renderAlbums();
                }
            )
        }

        setOnHover() {
            songPreviewColumn.innerHTML = "";
            let allRows = document.getElementsByClassName("entity");
            for (let row of allRows) {
                row.addEventListener("mouseover", () => {
                    albumPreviewColumn.innerHTML = "";
                    let id = row.getAttribute("data-href").split("/").pop();
                    this.fetchAlbumsSongs(id);
                });
            }

        }
        fetchAlbumsSongs(albumID){

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
                        data.forEach(song => {
                            if (!songPreviewColumn.innerHTML.includes(song.songTitle)) {
                                let songItem = document.createElement("tr");
                                songItem.setAttribute("scope","row");
                                songItem.innerHTML = song.songTitle;
                                songPreviewColumn.appendChild(songItem);
                            }
                        });
                    }
                )
        }

        renderAlbums() {
            pageNumberView.innerHTML = this.pageNumber;
            this.albumsTableBody.innerHTML = "";
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
                // set the row to be clickable
                albumRow.addEventListener("click", () => {
                //     on click, redirect to the album page
                    window.location.href = albumRow.getAttribute("data-href");
                });
                this.albumsTableBody.appendChild(albumRow);
                this.setOnHover();
            });
        }

        init() {
            this.fetchAlbums();
            console.log("page controller initialized");
        }

        firstPage() {
            this.pageNumber = 0;
            this.renderAlbums();
        }

        lastPage() {
            this.pageNumber = this.totalPages;
            this.renderAlbums();
        }
        nextPage() {
            if(this.pageNumber > this.totalPages) {
                this.renderAlbums();
                return;
            }
            this.pageNumber++;
            this.renderAlbums();
        }


        previousPage() {
            if (this.pageNumber === 0) {
                this.renderAlbums();
                return;
            }
            this.pageNumber--;
            this.renderAlbums();
        }
    }

    let albumPage = new albumPageController();
    albumPage.init();

    firstPageButton.addEventListener("click", () => {
        albumPage.firstPage();
    });
    lastPageButton.addEventListener("click", () => {
        albumPage.lastPage();
    });
    nextPageButton.addEventListener("click", () => {
        albumPage.nextPage();
    });
    previousPageButton.addEventListener("click", () => {
        albumPage.previousPage();
    });