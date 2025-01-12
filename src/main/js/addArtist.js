import {addArtist} from "./modules/artistModule.js";
import validator from "validator/es";

let form = document.getElementById("add-artist-form");
let artistName = document.getElementById("artistName");
let artistFollowers = document.getElementById("artistFollowers");
let submitButton = form.querySelector('#create-artist-submit');
let url = window.location.href;
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

    // check if artistFollowers is a number
    if (!validator.isInt(artistFollowers.value)) {
        alert("Followers must be a number.");
        return;
    }


    try {
        const response = await addArtist(artistName.value, artistFollowers.value);
        const newArtist = await response.json();
        const newArtistRow = document.createElement("tr");
        newArtistRow.setAttribute("data-href", `/allArtists/fullArtist/${newArtist.id}`);
        newArtistRow.classList.add("table-row");
        newArtistRow.innerHTML = `
            <td>${newArtist.name}</td>
            <td>${newArtist.artistFollowers}</td>
        `;
        // set the onclick event for each row
        newArtistRow.addEventListener("click", () => {
            window.location.href = newArtistRow.getAttribute("data-href");
        });
        document.getElementById("all-artists-table-body").appendChild(newArtistRow);
        document.querySelector(".modal-close-btn").click();
        form.classList.remove('was-validated');
        form.reset();
    } catch (error) {
        console.error(error);
        alert("Something went wrong. Please try again.");
    }
});


