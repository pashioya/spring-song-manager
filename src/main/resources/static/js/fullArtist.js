let allRows = document.querySelectorAll(".table-row");
// get the album id from the url
let url = window.location.href;
let artistId = url.substring(url.lastIndexOf("/") + 1);

if (artistId.indexOf("?") !== -1) {
    artistId = artistId.substring(0, artistId.indexOf("?"));
}
let deleteArtistButton = document.getElementsByClassName("delete-button")[0];
const header = document.querySelector('meta[name="_csrf_header"]').content;
const token = document.querySelector('meta[name="_csrf"]').content;


allRows.forEach(row => {
    row.addEventListener("click", () => {
        window.location.href = row.getAttribute("data-href");
    })
});

function deleteArtist() {
    fetch(`/api/artist/${artistId}`, {
        method: "DELETE",
        headers: {
            Accept: "application/json",
            'Content-Type': 'application/json',
            [header]: token

        }
    })
        .then(response => {
                if (response.status !== 200) {
                    console.log("Error: " + response.status);
                } else {
                    window.location.href = "/allArtists";
                }
            }
        )
}

function getArtist() {
    fetch(`/api/artist/${artistId}`)
        .then(response => response.json())
        .then(artist => {
            document.getElementById("artist-username").innerText = artist.username;
            document.getElementById("artist-name").innerText = artist.name;
            document.getElementById("artist-followers").innerText = artist.artistFollowers;
        })
        .catch(error => console.log(error));
}

function getArtistsAlbums() {
    fetch(`/api/album/artist/${artistId}/albums`)
        .then(response => response.json())
        .then(albums => {
            const albumTableBody = document.getElementById("albums-table-body");
            albums.forEach(album => {
                let row = document.createElement("tr");
                row.setAttribute("data-href", `/allAlbums/fullAlbum/${album.id}`);
                row.classList.add("table-row");
                row.innerHTML = `
                    <td>${album.albumName}</td>
                    <td>${album.officialTrackCount}</td>
                    <td>${album.albumStatus}</td>
                    <td>${album.genre}</td>
                    <td>${album.releaseDate}</td>
                `;
                // set the onclick event for each row
                row.addEventListener("click", () => {
                    window.location.href = row.getAttribute("data-href");
                });
                albumTableBody.appendChild(row);
            })
        })
        .catch(error => console.log(error));
}

getArtist();
getArtistsAlbums();
deleteArtistButton.addEventListener("click", () => {
    deleteArtist();
});