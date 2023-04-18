// get the album id from the url
let url = window.location.href;
let albumId = url.substring(url.lastIndexOf("/") + 1);

if (albumId.indexOf("?") !== -1) {
albumId = albumId.substring(0, albumId.indexOf("?"));
}
console.log(albumId);

const header = document.querySelector('meta[name="_csrf_header"]').content;
const token = document.querySelector('meta[name="_csrf"]').content;

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


let deleteAlbumButton = document.getElementsByClassName("delete-button")[0];
deleteAlbumButton.addEventListener("click", () => {
    deleteAlbum();
});



let allRows = document.querySelectorAll(".table-row");
allRows.forEach(row => {
    row.addEventListener("click", () => {
        window.location.href = row.getAttribute("data-href");
    })
});