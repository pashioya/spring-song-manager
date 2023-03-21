// get the album id from the url
let url = window.location.href;
let albumId = url.substring(url.lastIndexOf("/") + 1);

const header = document.querySelector('meta[name="_csrf_header"]').content;
const token = document.querySelector('meta[name="_csrf"]').content;

// fetch the album

function fetchAlbum() {
fetch(`/api/album/${albumId}`)
    .then(response => response.json())
    .then(album => {
        console.log(album);
        // set the album name
        let albumName = document.getElementById("album-name");
        albumName.content = album.albumName;
        // set the album release date
        let albumReleaseDate = document.getElementById("album-release-date");
        albumReleaseDate.content = album.releaseDate;
        // set the album followers
        // set the album genres
        let albumGenres = document.getElementById("album-genre");
        albumGenres.content = album.genre;
        // set the album tracks
        let albumTracks = document.getElementById("album-official-track-count");
        albumTracks.content = album.officialTrackCount;
        // set the album tracks
        let albumStatus = document.getElementById("album-status");
        albumStatus.content = albumStatus.officialTrackCount;
    }
);
}

function deleteAlbum() {
    fetch(`/api/album/${albumId}`, {
        method: "DELETE",
        headers: {
            Accept: "application/json",
            'Content-Type': 'application/json',
            [header]: token

        }
    })
        .then(response => {
            if (response.status !== 200) {
                console.log("Error: " + response.status);
            } else {
                window.location.href = "/allAlbums";
            }
        }
    )
}

fetchAlbum();

let deleteAlbumButton = document.getElementById("delete-album-button");
deleteAlbumButton.addEventListener("click", () => {
    deleteAlbum();
});


