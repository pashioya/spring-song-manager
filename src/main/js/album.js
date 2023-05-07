import {getAlbumsSongs} from "./fullAlbum";

let allRows = document.getElementsByClassName("entity");
let songPreviewColumn = document.getElementById("preview-song-names");


export function getAlbums() {
    return fetch("/api/album/albums")
}

function setOnHover() {
    for (let row of allRows) {
        row.addEventListener("mouseover", async () => {
            songPreviewColumn.innerHTML = "";
            let id = row.getAttribute("data-href").split("/").pop();
            let response = await getAlbumsSongs(id);
            let albumsSongs = await response.json();
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

for (let row of allRows) {
    row.addEventListener("click", () => {
            window.location.href = row.getAttribute("data-href");
        }
    );
}

setOnHover();