import {getArtistsAlbums} from "./modules/artistModule.js";

const albumPreviewColumn = document.getElementById("preview-album-names");
const allRows = document.getElementsByClassName("entity");


function setOnHover() {
    let allRows = document.getElementsByClassName("entity");
    for (let row of allRows) {
        row.addEventListener("mouseover", async () => {
            albumPreviewColumn.innerHTML = "";
            let id = row.getAttribute("data-href").split("/")[3];
            let artistsAlbums = await getArtistsAlbums(id);
            for (let album of artistsAlbums) {
                let albumItem = document.createElement("tr");
                albumItem.innerHTML = album.albumName;
                albumPreviewColumn.appendChild(albumItem);
            }
        });
    }
}

for (let row of allRows) {
    row.addEventListener("click", () => {
        window.location.href = row.getAttribute("data-href");
    });

}

setOnHover();