import {getArtistsAlbums} from "./fullArtist";

const albumPreviewColumn = document.getElementById("preview-album-names");
const allRows = document.getElementsByClassName("entity");

console.log("hello");

export function getArtists() {
    return fetch("/api/artist/artists",
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
        .then(artists => {
                return artists;
            }
        )
}


function setOnHover() {
    let allRows = document.getElementsByClassName("entity");
    for (let row of allRows) {
        row.addEventListener("mouseover", () => {
            albumPreviewColumn.innerHTML = "";
            let id = row.getAttribute("data-href").split("/")[3];
            getArtistsAlbums(id).then(albums => {
                albums.forEach(album => {
                    let albumItem = document.createElement("tr");
                    albumItem.innerHTML = album.albumName;
                    albumPreviewColumn.appendChild(albumItem);
                });
            });
        });
    }
}

for (let row of allRows) {
    row.addEventListener("click", () => {
        window.location.href = row.getAttribute("data-href");
    });

}

setOnHover();