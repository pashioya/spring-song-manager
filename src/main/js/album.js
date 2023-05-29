import {getAlbumsSongs} from "./modules/albumModule.js";

let allRows = document.getElementsByClassName("entity");
let songPreviewColumn = document.getElementById("preview-song-names");


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

for (let row of allRows) {
    row.addEventListener("click", () => {
            window.location.href = row.getAttribute("data-href");
        }
    );
}

setOnHover();