

let url = window.location.href;
let songId = url.substring(url.lastIndexOf("/") + 1);

if (songId.indexOf("?") !== -1) {
    songId = songId.substring(0, songId.indexOf("?"));
}

console.log(songId);

const header = document.querySelector('meta[name="_csrf_header"]').content;
const token = document.querySelector('meta[name="_csrf"]').content;


let deleteSongButton = document.getElementsByClassName("delete-button")[0];
deleteSongButton.addEventListener("click", () => {
    deleteSong();
});

function deleteSong() {
    fetch(`/api/song/${songId}/delete`, {
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
                    window.location.href = "/allSongs";
                }
            }
        )
}