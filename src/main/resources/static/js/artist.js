const albumPreviewColumn = document.getElementById("preview-album-names");
const allRows = document.querySelectorAll("#all-artists-table tbody tr");
allRows.forEach(row => {
        row.addEventListener("mouseover", () => {
            albumPreviewColumn.innerHTML = "";

            let id = row.getAttribute("data-href").split("/")[3];
            console.log("hovering over row: " + id);
            fetchAlbumsSongs(id);
        });
    }
);


/**
 * @param artistID
 */
function fetchAlbumsSongs(artistID){

    fetch(`/api/artist/${artistID}/albums`,
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
                console.log(data);
                data.forEach(album => {
                    let albumItem = document.createElement("tr");
                    albumItem.innerHTML = album.albumName;
                    albumPreviewColumn.appendChild(albumItem);
                });
            }
        )
}


