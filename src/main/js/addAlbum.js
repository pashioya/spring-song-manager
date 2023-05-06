let form = document.getElementById("add-album-form");
let albumName = document.getElementById("albumName");
let albumStatus = document.getElementById("albumStatus");
let albumTrackCount = document.getElementById("officialTrackCount");
let albumGenre = document.getElementById("genre");
let albumReleaseDate = document.getElementById("releaseDate");
let submitButton = document.querySelector("#add-album-form > button");
let url = window.location.href;
let artistId = url.substring(url.lastIndexOf("/") + 1);
if (artistId.indexOf("?") !== -1) {
    artistId = artistId.substring(0, artistId.indexOf("?"));
}

submitButton.addEventListener("click", trySubmitForm);

export function trySubmitForm(event) {
    event.preventDefault();
    const formIsValid = form.checkValidity();

    const header = document.querySelector('meta[name="_csrf_header"]').content;
    const token = document.querySelector('meta[name="_csrf"]').content;

    form.classList.add('was-validated');

    if (formIsValid) {
        fetch('/api/album/artist/' + artistId, {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
                , [header]: token
            },
            body: JSON.stringify(
                {
                    "albumName": albumName.value,
                    "officialTrackCount": parseInt(albumTrackCount.value),
                    "albumStatus": albumStatus.value,
                    "genre": albumGenre.value,
                    "releaseDate": albumReleaseDate.value
                })
        }).then(async response => {
            if (response.status !== 201) {
                console.log("Error: " + response.status);
            } else {
                const newAlbum = await response.json();
                let newAlbumRow = document.createElement("tr");
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
            }
        });
    }
}