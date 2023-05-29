import {deleteArtist, getArtist, getArtistsAlbums} from "./modules/artistModule.js";

const albumTableBody = document.getElementById("albums-table-body");
const url = window.location.href;
let artistId = url.substring(url.lastIndexOf("/") + 1);
if (artistId.indexOf("?") !== -1) {
    artistId = artistId.substring(0, artistId.indexOf("?"));
}


async function init() {
    try {
        const deleteArtistButton = document.querySelector(".delete-button");
        const response = await getArtistsAlbums(artistId);
        let artistsAlbums = await response;
        const artist = await getArtist(artistId);
        document.getElementById("artist-username").innerText = artist.username;
        document.getElementById("artist-name").innerText = artist.name;
        document.getElementById("artist-followers").innerText = artist.artistFollowers;

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

        deleteArtistButton.addEventListener("click", async () => {
            let response = await deleteArtist(artistId);
            if (response.status === 204) {
                window.location.href = "/allArtists";
            } else {
                alert("There was an error deleting the artist");
            }
        });
    } catch (error) {
        console.error(error);
        alert("Something went wrong. Please try again.");
    }
}

if (window.location.href.includes("fullArtist")) {
    init();
}