import {getCsrfHeader, getCsrfToken} from "./modules/csrf";

const header = getCsrfHeader();
const token = getCsrfToken();

export function deleteArtist(artistId) {
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

export function getArtist(artistId) {
    return fetch(`/api/artist/${artistId}`)
        .then(response => response.json())
        .then(artist => {
            return artist;
        });
}

export function getArtistsAlbums(artistId) {
    return fetch(`/api/album/artist/${artistId}/albums`)
        .then(response => response.json())
        .then(albums => {
            return albums;
        });
}

// check if  the page name is "fullArtist"
// if it is, then run the code below
// this is to prevent the code from running on other pages
if (window.location.href.indexOf("fullArtist") !== -1) {

    let allRows = document.querySelectorAll(".table-row");
    const albumTableBody = document.getElementById("albums-table-body");
    let url = window.location.href;
    let artistId = url.substring(url.lastIndexOf("/") + 1);
    if (artistId.indexOf("?") !== -1) {
        artistId = artistId.substring(0, artistId.indexOf("?"));
    }
    let artist = await getArtist(artistId);
    let artistsAlbums = await getArtistsAlbums(artistId);
    let deleteArtistButton = document.getElementsByClassName("delete-button")[0];

    document.getElementById("artist-username").innerText = artist.username;
    document.getElementById("artist-name").innerText = artist.name;
    document.getElementById("artist-followers").innerText = artist.artistFollowers;

    allRows.forEach(row => {
        row.addEventListener("click", () => {
            window.location.href = row.getAttribute("data-href");
        })
    });

    for (let album of artistsAlbums) {
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
    }

    deleteArtistButton.addEventListener("click", () => {
        deleteArtist(artistId);
    });

}