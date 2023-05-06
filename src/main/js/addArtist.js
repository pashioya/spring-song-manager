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
submitButton.addEventListener("click", trySubmitForm);

function trySubmitForm(event) {
    event.preventDefault();
    const formIsValid = form.checkValidity();
    form.classList.add('was-validated');

    if (formIsValid) {
        fetch('/api/artist', {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
                , [header]: token
            },
            body: JSON.stringify(
                {
                    "artistName": artistName.value,
                    "artistFollowers": parseInt(artistFollowers.value)
                })
        }).then(response => {
            form.reset();
            form.classList.remove('was-validated');
            window.location.href = "/allArtists";
            console.log(response)
        });
    }
}