const songsTableBody = document.getElementById("all-songs-table-body");


export function getSongs() {
    return fetch("/api/song/songs", {
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
        );
}

let songs = await getSongs();

for (let song of songs) {
    let row = document.createElement("tr");
    row.setAttribute("data-href", "/allSongs/fullSong/" + song.id);
    row.classList.add("table-row");
    row.innerHTML = `
                <td>${song.songTitle}</td>
                <td>${song.trackNumber}</td>
                <td>${song.durationMs}</td>
                <td>${song.explicit}</td>
            `;
    // set the row to be clickable
    row.addEventListener("click", () => {
        //     on click, redirect to the album page
        window.location.href = row.getAttribute("data-href");
    });
    songsTableBody.appendChild(row);
}