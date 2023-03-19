/**
 * @type {HTMLFormElement}
 */

const form = document.getElementById("add-song-form");

const url = document.getElementById("songUrl");
const songTitle = document.getElementById("songTitle");
const songDuration = document.getElementById("durationMS");
const songTrackNumber = document.getElementById("officialTrackNumber");
let explicit = Boolean(document.getElementById("explicit"))



const submitButton = document.querySelector("#add-song-form > button")

submitButton.addEventListener("click", trySubmitForm);

const pathname = window.location.pathname;

const parts = pathname.split('/');

// Find the index of the "album" string in the path
const albumIndex = parts.indexOf('album');

// The album ID is the next part of the path
const albumId = parts[albumIndex + 1];

console.log('/api/album/'+albumId+'/song/create')

function trySubmitForm(event) {
    event.preventDefault();
    const formIsValid = form.checkValidity();

    // const header = document.querySelector('meta[name="_csrf_header"]').content;
    // const token = document.querySelector('meta[name="_csrf"]').content;
    form.classList.add('was-validated');

    if (formIsValid) {
fetch('/api/album/'+albumId+'/song/create', {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
                // ,[header]: token
            },
            body: JSON.stringify(
                {
                    "url": url.value,
                "songTitle": songTitle.value,
                "songDuration": songDuration.value,
                "songTrackNumber": parseInt(songTrackNumber.value),
                "explicit": explicit
            })
        }).then(response => {
            if (response.status === 201) {

                form.reset();
                form.classList.remove('was-validated');
            }
        });
    }
}