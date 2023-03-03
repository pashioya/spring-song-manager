

fetch(`/api/albums/${getAlbumID()}/songs`,
    {
        headers: {
            Accept: "application/json"
        }
    })
    .then(resp => {
        if (resp.status !== 200) {
            console.log("Error: " + resp.status);
        } else {
            return resp.json();
        }
    })
    .then(data => {
        console.log(data);
        let songs = data;
        let songList = document.getElementById("songList");
        songs.forEach(song => {
            let songItem = document.createElement("li");
            songItem.innerHTML = song.name;
            songList.appendChild(songItem);
        });
    })
