import {addSong} from "./modules/songModule.js";
import validator from "validator/es";

const form = document.getElementById("add-song-form");
const songTitle = document.getElementById("songTitle");
const durationMS = document.getElementById("durationMS");
const trackNumber = document.getElementById("trackNumber");
let explicit = Boolean(document.getElementById("explicit"))
let url = window.location.href;
let albumId = url.substring(url.lastIndexOf("/") + 1);
if (albumId.indexOf("?") !== -1) {
    albumId = albumId.substring(0, albumId.indexOf("?"));
}

const submitButton = form.querySelector('button[type="submit"]');


submitButton.addEventListener("click", async function (event) {
    event.preventDefault();
    event.stopPropagation();
    form.classList.add('was-validated');

    if (!form.checkValidity()) {
        return;
    }

    // check if durationMS is a number
    if (!validator.isInt(durationMS.value)) {
        alert("Duration must be a number.");
        return;
    }

    // check if trackNumber is a number
    if (!validator.isInt(trackNumber.value)) {
        alert("Track number must be a number.");
        return;
    }


    try {
        const response = await addSong(songTitle.value, durationMS.value, trackNumber.value, explicit.value, albumId.value);
        const newSong = await response.json();
        const newSongRow = document.createElement("tr");
        newSongRow.setAttribute("data-href", `/allSongs/fullSong/${newSong.id}`);
        newSongRow.classList.add("table-row");
        newSongRow.innerHTML = `
            <td>${newSong.songTitle}</td>
            <td>${newSong.trackNumber}</td>
            <td>${newSong.explicit}</td>
        `;
        // set the onclick event for each row
        newSongRow.addEventListener("click", () => {
                window.location.href = newSongRow.getAttribute("data-href");
            }
        );
        document.getElementById("album-songsList").appendChild(newSongRow);
        document.querySelector(".modal-close-btn").click();
        form.classList.remove('was-validated');
        form.reset();
    } catch (error) {
        console.error(error);
        alert("Something went wrong. Please try again.");
    }
});