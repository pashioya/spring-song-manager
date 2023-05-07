import {getArtistsAlbums} from "./fullArtist";

const albumPreviewColumn = document.getElementById("preview-album-names");
const allRows = document.getElementsByClassName("entity");

export function getArtists() {
    return fetch("/api/artist/artists")
}

function setOnHover() {
    let allRows = document.getElementsByClassName("entity");
    for (let row of allRows) {
        row.addEventListener("mouseover", async () => {
            albumPreviewColumn.innerHTML = "";
            let id = row.getAttribute("data-href").split("/")[3];
            let response = await getArtistsAlbums(id);
            let artistsAlbums = await response.json();
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