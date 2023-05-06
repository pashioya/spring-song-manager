import {getArtistsAlbums} from "./fullArtist";

const albumPreviewColumn = document.getElementById("preview-album-names");
const artistsTableBody = document.getElementById("all-artists-table-body");

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

let artists = await getArtists();
console.log(artists);
for (let artist of artists) {
    let artistRow = document.createElement("tr");
    artistRow.setAttribute("data-href", `/allArtists/fullArtist/${artist.id}`);
    artistRow.classList.add("table-row");
    artistRow.classList.add("entity");
    artistRow.innerHTML = `
                <td>${artist.name}</td>
                <td>${artist.artistFollowers}</td>
            `;
    // set the row to be clickable
    artistRow.addEventListener("click", () => {
        //     on click, redirect to the album page
        window.location.href = artistRow.getAttribute("data-href");
    });
    artistsTableBody.appendChild(artistRow);
}

setOnHover();