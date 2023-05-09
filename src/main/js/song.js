const allRows = document.getElementsByClassName("table-row");

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

export function getSongByTitle(title) {
    return fetch(`/api/song/title/${title}`, {
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

const songTableBody = document.getElementById("all-songs-table-body");
const searchBar = document.getElementById("search-bar");
const songsError = document.getElementById("songs-error");
songsError.style.display = "none";

// on key up in the search bar, empty the table and repopulate it with the search results
searchBar.addEventListener("keyup", async () => {

        if (searchBar.value.length > 0) {
            songTableBody.innerHTML = "";
            let filteredSongs = await getSongByTitle(searchBar.value)
            buildSongsTable(filteredSongs);
        } else {
            songTableBody.innerHTML = "";
            let songs = await getSongs();
            buildSongsTable(songs);
        }

        //     if the table is empty, set the error to visible
        if (songTableBody.innerHTML === "") {
            songsError.style.display = "block";
        } else {
            songsError.style.display = "none";
        }
    }
);

function buildSongsTable(songs) {
    for (let song of songs) {
        let row = document.createElement("tr");
        row.setAttribute("data-href", `/allSongs/fullSong/${song.id}`);
        row.classList.add("table-row");
        row.innerHTML = `
                <td>${song.songTitle}</td>
                <td>${song.trackNumber}</td>
                <td>${song.durationMs}</td>
                <td>${song.explicit}</td>
            `;
        songTableBody.appendChild(row);
    }
}


for (let row of allRows) {
    row.addEventListener("click", () => {
        //     on click, redirect to the album page
        window.location.href = row.getAttribute("data-href");
    });
}