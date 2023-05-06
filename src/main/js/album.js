import {getAlbumsSongs} from "./fullAlbum";

let allRows = document.getElementsByClassName("entity");
let songPreviewColumn = document.getElementById("preview-song-names");


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

setOnHover();