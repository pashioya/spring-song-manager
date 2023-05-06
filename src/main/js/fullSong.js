import {getCsrfInfo} from "./modules/csrf";

const url = window.location.href;
let songId = url.substring(url.lastIndexOf("/") + 1);
if (songId.indexOf("?") !== -1) {
    songId = songId.substring(0, songId.indexOf("?"));
}

const deleteSongButton = document.getElementsByClassName("delete-button")[0];
const {header, token} = getCsrfInfo();

getSong();
deleteSongButton.addEventListener("click", () => {
    deleteSong(songId);
});

function deleteSong(songId) {
    fetch(`/api/song/${songId}`, {
        method: "DELETE",
        headers: {
            Accept: "application/json",
            'Content-Type': 'application/json',
            [header]: token
        }
    })
        .then(response => {
                if (response.status !== 200) {
                    console.log("Error: " + response.status);
                } else {
                    window.location.href = "/allSongs";
                }
            }
        )
}

function getSong() {
    fetch(`/api/song/${songId}`)
        .then(response => response.json())
        .then(song => {
            document.getElementById("song-user").innerText = song.username;
            document.getElementById("song-title").innerText = song.songTitle;
            document.getElementById("song-track-number").innerText = song.trackNumber;
            document.getElementById("song-duration-ms").innerText = song.durationMs;
            document.getElementById("song-explicit").innerText = song.explicit;
            document.getElementById("song-url").innerText = song.url;
        })
}