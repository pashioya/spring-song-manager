let allRows = document.querySelectorAll(".table-row");


// get the album id from the url
let url = window.location.href;
let artistId = url.substring(url.lastIndexOf("/") + 1);

if (artistId.indexOf("?") !== -1) {
    artistId = artistId.substring(0, artistId.indexOf("?"));
}
console.log(artistId);

const header = document.querySelector('meta[name="_csrf_header"]').content;
const token = document.querySelector('meta[name="_csrf"]').content;


allRows.forEach(row => {
    row.addEventListener("click", () => {
        window.location.href = row.getAttribute("data-href");
    })
});




function deleteArtist(){
    fetch(`/api/artist/${artistId}`, {
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
                    window.location.href = "/allArtists";
                }
            }
        )
}

let deleteArtistButton = document.getElementsByClassName("delete-button")[0];
deleteArtistButton.addEventListener("click", () => {
    deleteArtist();
});