import {getAlbumsSongs} from "./fullAlbum";

let allRows = document.getElementsByClassName("entity");
let songPreviewColumn = document.getElementById("preview-song-names");
let albumsTableBody = document.getElementById("albums-table-body");

export function getAlbums() {
    return fetch("/api/album/albums",
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
                return data;
            }
        )
}

let albums = await getAlbums();

for (let album of albums) {
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
    albumsTableBody.appendChild(albumRow);
    setOnHover();
}

function setOnHover() {
    for (let row of allRows) {
        row.addEventListener("mouseover", async () => {
            songPreviewColumn.innerHTML = "";
            let id = row.getAttribute("data-href").split("/").pop();
            let albumsSongs = await getAlbumsSongs(id);
            for (let song of albumsSongs) {
                if (!songPreviewColumn.innerHTML.includes(song.songTitle)) {
                    let songItem = document.createElement("tr");
                    songItem.setAttribute("scope", "row");
                    songItem.innerHTML = song.songTitle;
                    songPreviewColumn.appendChild(songItem);
                }
            }
        });
    }
}