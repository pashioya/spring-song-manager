/**
 * @type {HTMLFormElement}
 */

const form = document.getElementById("add-song-form");

const url = document.getElementById("url");
const songTitle = document.getElementById("songTitle");
const songDuration = document.getElementById("durationMS");
const songTrackNumber = document.getElementById("trackNumber");
let explicit = Boolean(document.getElementById("explicit"))


const submitButton = form.querySelector('button[type="submit"]');
submitButton.addEventListener("click", trySubmitForm);
const pathname = window.location.pathname;
const parts = pathname.split('/');

// Find the index of the "album" string in the path
const albumIndex = parts.indexOf('album');

// The album ID is the next part of the path
const albumId = parts[albumIndex + 1];

console.log('/api/album/' + albumId + '/song/create')

function trySubmitForm(event) {
    event.preventDefault();
    const formIsValid = form.checkValidity();

    const header = document.querySelector('meta[name="_csrf_header"]').content;
    const token = document.querySelector('meta[name="_csrf"]').content;
    form.classList.add('was-validated');

    if (formIsValid) {
        fetch('/api/song/album/' + albumId, {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
                , [header]: token
            },
            body: JSON.stringify({
                "url": url.value,
                "songTitle": songTitle.value,
                "durationMS": songDuration.value,
                "trackNumber": parseInt(songTrackNumber.value),
                "explicit": explicit
            })
        }).then(response => {
            console.log(response)
        });
    }
}