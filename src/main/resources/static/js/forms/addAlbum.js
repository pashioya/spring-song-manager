let form = document.getElementById("add-album-form");
let albumName = document.getElementById("albumName");
let albumStatus = document.getElementById("albumStatus");
let albumTrackCount = document.getElementById("officialTrackCount");
let albumGenre = document.getElementById("genre");
let albumReleaseDate = document.getElementById("releaseDate");
let submitButton = document.querySelector("#add-album-form > button");

submitButton.addEventListener("click", trySubmitForm);


let pathname = window.location.pathname;
let parts = pathname.split('/');

// Find the index of the "artist" string in the path
let artistIndex = parts.indexOf('artist');

// The artist ID is the next part of the path
let artistId = parts[artistIndex + 1];

console.log('/api/artist/'+artistId+'/album/create')

function trySubmitForm(event) {
    event.preventDefault();
    const formIsValid = form.checkValidity();

    console.log("albumName: " + albumName.value);
    console.log("albumStatus: " + albumStatus.value);
    console.log("albumTrackCount: " + albumTrackCount.value);
    console.log("albumGenre: " + albumGenre.value);
    console.log("albumReleaseDate: " + albumReleaseDate.value);


    const header = document.querySelector('meta[name="_csrf_header"]').content;
    const token = document.querySelector('meta[name="_csrf"]').content;

    form.classList.add('was-validated');

    if (formIsValid) {
        fetch('/api/artist/'+artistId+'/album/create', {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
                ,[header]: token
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