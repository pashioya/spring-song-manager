import {getCsrfHeader, getCsrfToken} from "./modules/csrf";

const header = getCsrfHeader();
const token = getCsrfToken();

const albumTableBody = document.getElementById("albums-table-body");
const url = window.location.href;
let artistId = url.substring(url.lastIndexOf("/") + 1);
if (artistId.indexOf("?") !== -1) {
    artistId = artistId.substring(0, artistId.indexOf("?"));
}

export async function getArtist(artistId) {
    return await fetch(`/api/artist/${artistId}`, {
        method: "GET",
        headers: {
            Accept: "application/json",
        }
    })
}

export async function deleteArtist(artistId) {
    return await fetch(`/api/artist/${artistId}`, {
        method: "DELETE",
        headers: {
            Accept: "application/json",
            'Content-Type': 'application/json',
            [header]: token
        }
    })
}

export async function getArtistsAlbums(artistId) {
    return await fetch(`/api/album/artist/${artistId}/albums`, {
        headers: {
            Accept: "application/json",
        }
    })
}


async function init() {
    try {
        const deleteArtistButton = document.querySelector(".delete-button");
        const response = await getArtistsAlbums(artistId);
        const artistsAlbums = await response.json();
        const artistResponse = await getArtist(artistId);
        const artist = await artistResponse.json();
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