import {addAlbum} from "./modules/albumModule.js";
import validator from "validator/es";

const form = document.querySelector('.needs-validation');
const albumName = document.getElementById("albumName");
const albumStatus = document.getElementById("albumStatus");
const albumTrackCount = document.getElementById("officialTrackCount");
const albumGenre = document.getElementById("genre");
const albumReleaseDate = document.getElementById("releaseDate");
const submitButton = document.querySelector("#add-album-form > button");
const url = window.location.href;
let artistId = url.substring(url.lastIndexOf("/") + 1);
if (artistId.indexOf("?") !== -1) {
    artistId = artistId.substring(0, artistId.indexOf("?"));
}


submitButton.addEventListener("click", async function (event) {
    event.preventDefault();
    event.stopPropagation();
    form.classList.add('was-validated');

    if (!form.checkValidity()) {
        return;
    }

    if (!validator.isInt(albumTrackCount.value)) {
        alert("Track count must be a number.");
        return;
    }

    if (!validator.isAlpha(albumStatus.value.replace(/\s/g, ''))) {
        alert("Status must only contain letters.");
        return;
    }

    if (!validator.isInt(albumTrackCount.value)) {
        alert("Official track count must be a number.");
        return;
    }

    if (!validator.isAlpha(albumGenre.value.replace(/\s/g, ''))) {
        alert("Genre must only contain letters.");
        return;
    }

    if (!validator.isDate(albumReleaseDate.value)) {
        alert("Release date must be a date.");
        return;
    }


    try {
        const response = await addAlbum(albumName.value, albumStatus.value, albumTrackCount.value, albumGenre.value, albumReleaseDate.value, artistId);
        const newAlbum = await response.json();
        const newAlbumRow = document.createElement("tr");
        newAlbumRow.setAttribute("data-href", `/allAlbums/fullAlbum/${newAlbum.id}`);
        newAlbumRow.classList.add("table-row");
        newAlbumRow.innerHTML = `
            <td>${newAlbum.albumName}</td>
            <td>${newAlbum.officialTrackCount}</td>
            <td>${newAlbum.albumStatus}</td>
            <td>${newAlbum.genre}</td>
            <td>${newAlbum.releaseDate}</td>
        `;
        // set the onclick event for each row
        newAlbumRow.addEventListener("click", () => {
            window.location.href = newAlbumRow.getAttribute("data-href");
        });
        document.getElementById("albums-table-body").appendChild(newAlbumRow);
        document.querySelector(".modal-close-btn").click();
        form.classList.remove('was-validated');
        form.reset();
    } catch (error) {
        console.error(error);
        alert("Something went wrong. Please try again.");
    }
});

