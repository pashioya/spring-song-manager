const songPreviewColumn = document.getElementById("preview-song-names");
const allRows = document.querySelectorAll("#all-albums-table tbody tr");
    allRows.forEach(row => {
        row.addEventListener("mouseover", () => {
            // clear the preview
            songPreviewColumn.innerHTML = "";

            let id = row.getAttribute("data-href").split("/")[3];
            console.log("hovering over row: " + id);
            fetchAlbumsSongs(id);
        });
    }
    );


    /**
     * @param albumID
     */
    function fetchAlbumsSongs(albumID){

        fetch(`/api/album/${albumID}/songs`,
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
                data.forEach(song => {
                    let songItem = document.createElement("tr");
                    songItem.innerHTML = song.songTitle;
                    songPreviewColumn.appendChild(songItem);
                });
            }
        )
    }
