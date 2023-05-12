import {deleteSong, getSong} from "./modules/songModule";

const url = window.location.href;
let songId = url.substring(url.lastIndexOf("/") + 1);
if (songId.indexOf("?") !== -1) {
    songId = songId.substring(0, songId.indexOf("?"));
}
const deleteSongButton = document.getElementsByClassName("delete-button")[0];

let response = await getSong(songId);
const song = await response.json();

document.getElementById("song-user").innerText = song.user;
document.getElementById("song-title").innerText = song.title;
document.getElementById("song-track-number").innerText = song.trackNumber;
document.getElementById("song-duration-ms").innerText = song.durationMS;
document.getElementById("song-explicit").innerText = song.explicit;
document.getElementById("song-url").innerText = song.url;

deleteSongButton.addEventListener("click", () => {
    deleteSong(songId);
});

