// get the album id from the url
let url = window.location.href;
let albumId = url.substring(url.lastIndexOf("/") + 1);

if (albumId.indexOf("?") !== -1) {
    albumId = albumId.substring(0, albumId.indexOf("?"));
}
console.log(albumId);

const header = document.querySelector('meta[name="_csrf_header"]').content;
const token = document.querySelector('meta[name="_csrf"]').content;

function deleteAlbum() {
    fetch(`/api/album/${albumId}`, {
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
                    window.location.href = "/allAlbums";
                }
            }
        )
}

function getAlbum() {
    fetch(`/api/album/${albumId}`)
        .then(response => response.json())
        .then(album => {
            console.log(album);
            document.getElementById("album-username").innerHTML = album.username;
            document.getElementById("album-albumName").innerHTML = album.albumName;
            document.getElementById("album-genre").innerHTML = album.genre;
            document.getElementById("album-officialTrackCount").innerHTML = album.officialTrackCount;
            document.getElementById("album-albumStatus").innerHTML = album.albumStatus;
            document.getElementById("album-releaseDate").innerHTML = album.releaseDate;
        })
}

function getAlbumsArtists() {
    fetch(`/api/artist/album/${albumId}/artists`)
        .then(response => response.json())
        .then(artists => {
            let artistsList = document.getElementById("album-artistsList");
            artists.forEach(artist => {
                let artistRow = document.createElement("tr");
                let artistName = document.createElement("td");
                artistName.innerText = artist.name;
                artistRow.appendChild(artistName);
                artistRow.setAttribute("data-href", `/allArtists/fullArtist/${artist.id}`);
                artistRow.classList.add("table-row");
                artistRow.classList.add("text-center");
                artistRow.addEventListener("click", () => {
                    window.location.href = artistRow.getAttribute("data-href");
                });
                artistsList.appendChild(artistRow);
            })
        })
}

function getAlbumsSongs() {
    fetch(`/api/song/album/${albumId}/songs`)
        .then(response => response.json())
        .then(songs => {
            console.log(songs);
            let songsList = document.getElementById("album-songsList");
            for (let song of songs) {
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
        })
}


let deleteAlbumButton = document.getElementsByClassName("delete-button")[0];

getAlbum();
getAlbumsArtists();
getAlbumsSongs();
deleteAlbumButton.addEventListener("click", () => {
    deleteAlbum();
});
let allRows = document.querySelectorAll(".table-row");
allRows.forEach(row => {
    row.addEventListener("click", () => {
        window.location.href = row.getAttribute("data-href");
    })
});