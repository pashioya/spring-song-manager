// get the album id from the url
import {getCsrfInfo} from "./modules/csrf";


const {header, token} = getCsrfInfo();

export function deleteAlbum(albId) {
    fetch(`/api/album/${albId}`, {
        method: "DELETE",
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            [header]: token,
        },
    }).then((response) => {
        if (response.status !== 200) {
            console.log("Error: " + response.status);
        } else {
            window.location.href = "/allAlbums";
        }
    });
}

export function getAlbum(albId) {
    return fetch(`/api/album/${albId}`)
        .then((response) => response.json())
        .then((album) => {
            return album;
        });
}

export function getAlbumsArtists(alId) {
    return fetch(`/api/artist/album/${alId}/artists`)
        .then((response) => response.json())
        .then((artists) => {
            return artists;
        });
}

export function getAlbumsSongs(alId) {
    return fetch(`/api/song/album/${alId}/songs`)
        .then((response) => response.json())
        .then((songs) => {
            return songs;
        });
}

if (window.location.href.indexOf("fullAlbum") !== -1) {
    let deleteAlbumButton = document.getElementsByClassName("delete-button")[0];
    let url = window.location.href;
    let albumId = url.substring(url.lastIndexOf("/") + 1);
    if (albumId.indexOf("?") !== -1) {
        albumId = albumId.substring(0, albumId.indexOf("?"));
    }
    let songsList = document.getElementById("album-songsList");

    let album = await getAlbum(albumId);
    let albumsArtists = await getAlbumsArtists(albumId);
    let albumsSongs = await getAlbumsSongs(albumId);
    let artistsList = document.getElementById("album-artistsList");


    console.log(album);
    document.getElementById("album-username").innerHTML = album.username;
    document.getElementById("album-albumName").innerHTML = album.albumName;
    document.getElementById("album-genre").innerHTML = album.genre;
    document.getElementById("album-officialTrackCount").innerHTML =
        album.officialTrackCount;
    document.getElementById("album-albumStatus").innerHTML = album.albumStatus;
    document.getElementById("album-releaseDate").innerHTML = album.releaseDate;

    for (let artist of albumsArtists) {
        let artistRow = document.createElement("tr");
        let artistName = document.createElement("td");
        artistName.innerText = artist.name;
        artistRow.appendChild(artistName);
        artistRow.setAttribute(
            "data-href",
            `/allArtists/fullArtist/${artist.id}`
        );
        artistRow.classList.add("table-row");
        artistRow.classList.add("text-center");
        artistRow.addEventListener("click", () => {
            window.location.href = artistRow.getAttribute("data-href");
        });
        artistsList.appendChild(artistRow);
    }


    for (let song of albumsSongs) {
        let songRow = document.createElement("tr");
        songRow.innerHTML = `
                <td>${song.songTitle}</td>
                <td>${song.trackNumber}</td>
                <td>${song.explicit}</td>
                    `;
        songRow.setAttribute("data-href", `/allSongs/fullSong/${song.id}`);
        songRow.classList.add("table-row");
        songRow.addEventListener("click", () => {
            window.location.href = songRow.getAttribute("data-href");
        });
        songsList.appendChild(songRow);
    }
    if (deleteAlbumButton) {
        deleteAlbumButton.addEventListener("click", () => {
            deleteAlbum(albumId);
        });
    }
    let allRows = document.querySelectorAll(".table-row");
    allRows.forEach((row) => {
        row.addEventListener("click", () => {
            window.location.href = row.getAttribute("data-href");
        });
    });
}