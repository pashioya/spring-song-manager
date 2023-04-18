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


function trySubmitForm(event) {
    event.preventDefault();
    const formIsValid = form.checkValidity();

    const header = document.querySelector('meta[name="_csrf_header"]').content;
    const token = document.querySelector('meta[name="_csrf"]').content;

    form.classList.add('was-validated');

    if (formIsValid) {
        fetch('/api/album/artist/'+artistId, {
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
            form.reset();
            form.classList.remove('was-validated');
            window.location.href = "/allArtists/fullArtist/"+artistId;
            console.log(response)
        });
    }
}