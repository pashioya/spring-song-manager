/**
 * @type {HTMLFormElement}
 */
const form = document.getElementById("add-album-form");
const albumName = document.getElementById("albumName");
const albumStatus = document.getElementById("albumStatus");
const albumTrackCount = document.getElementById("officialTrackCount");
const albumGenre = document.getElementById("genre");
const albumReleaseDate = document.getElementById("releaseDate");
const submitButton = document.querySelector("#add-album-form > button");

submitButton.addEventListener("click", trySubmitForm);


const pathname = window.location.pathname;
const parts = pathname.split('/');

// Find the index of the "artist" string in the path
const artistIndex = parts.indexOf('artist');

// The artist ID is the next part of the path
const artistId = parts[artistIndex + 1];

console.log('/api/artist/'+artistId+'/album/create')

function trySubmitForm(event) {
    event.preventDefault();
    const formIsValid = form.checkValidity();

    // const header = document.querySelector('meta[name="_csrf_header"]').content;
    // const token = document.querySelector('meta[name="_csrf"]').content;
    form.classList.add('was-validated');

    if (formIsValid) {
        fetch('/api/artist/'+artistId+'/album/create', {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
                // ,[header]: token
            },
            body: JSON.stringify(
                {
                "albumName": albumName.value,
                "officialTrackCount": parseInt(albumTrackCount.value),
                "albumStatus": albumStatus.value,
                "genre": albumGenre.value,
                "releaseDate": albumReleaseDate.value
            })
        }).then(response => {
            if (response.status === 201) {

                form.reset();
                form.classList.remove('was-validated');
            }
        });
    }
}