import {deleteAlbum, getAlbum, getAlbumsArtists, getAlbumsSongs} from "./modules/albumModule.js";

async function init() {
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
        deleteAlbumButton.addEventListener("click", async () => {
            let response = await deleteAlbum(albumId);
            if (response.status === 200) {
                window.location.href = "/allAlbums";
            } else {
                alert("Error deleting album");
            }
        });
    }
    let allRows = document.querySelectorAll(".table-row");
    allRows.forEach((row) => {
        row.addEventListener("click", () => {
            window.location.href = row.getAttribute("data-href");
        });
    });
}

if (window.location.href.indexOf("fullAlbum") !== -1)
    init();