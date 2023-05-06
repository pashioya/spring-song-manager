/**
 * @type {HTMLFormElement}
 */

const form = document.getElementById("add-song-form");

const songTitle = document.getElementById("songTitle");
const songDuration = document.getElementById("durationMS");
const songTrackNumber = document.getElementById("trackNumber");
let explicit = Boolean(document.getElementById("explicit"))
let url = window.location.href;
let albumId = url.substring(url.lastIndexOf("/") + 1);
if (albumId.indexOf("?") !== -1) {
    albumId = albumId.substring(0, albumId.indexOf("?"));
}

const submitButton = form.querySelector('button[type="submit"]');
submitButton.addEventListener("click", trySubmitForm);

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