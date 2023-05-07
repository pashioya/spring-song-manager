import {getCsrfHeader, getCsrfToken} from "./modules/csrf";

const header = getCsrfHeader();
const token = getCsrfToken();

let form = document.getElementById("add-artist-form");
let artistName = document.getElementById("artistName");
let artistFollowers = document.getElementById("artistFollowers");
let submitButton = form.querySelector('#create-artist-submit');
let url = window.location.href;
let artistId = url.substring(url.lastIndexOf("/") + 1);
if (artistId.indexOf("?") !== -1) {
    artistId = artistId.substring(0, artistId.indexOf("?"));
}

export function addArtist(artistName, artistFollowers) {
    return fetch('/api/artist', {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
            , [header]: token
        },
        body: JSON.stringify(
            {
                "artistName": artistName,
                "artistFollowers": parseInt(artistFollowers)
            })
    });
}

submitButton.addEventListener("click", async function (event) {
    event.preventDefault();
    event.stopPropagation();
    form.classList.add('was-validated');
    if (!form.checkValidity()) {
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


