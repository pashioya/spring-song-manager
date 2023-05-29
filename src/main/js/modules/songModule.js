import {getCsrfHeader, getCsrfToken} from "./csrf";

export function getSongs() {
    return fetch("/api/songs/songs", {
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
            }
        )
        .then(data => {
                return data;
            }
        );
}

export function getSongByTitle(title) {
    return fetch(`/api/songs/title/${title}`, {
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
            }
        )
        .then(data => {
                return data;
            }
        );
}

export function addSong(songTitle, durationMS, trackNumber, explicit, albumId) {
    const header = getCsrfHeader();
    const token = getCsrfToken();
    return fetch('/api/songs/album/' + albumId, {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
            , [header]: token
        },
        body: JSON.stringify(
            {
                "songTitle": songTitle,
                "durationMS": parseInt(durationMS),
                "trackNumber": parseInt(trackNumber),
                "explicit": explicit
            })
    });
}

export function deleteSong(songId) {
    const header = getCsrfHeader();
    const token = getCsrfToken();
    return fetch(`/api/songs/${songId}`, {
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

export function getSong(songId) {
    return fetch(`/api/songs/${songId}`
        , {
            headers: {
                Accept: "application/json"
            }
        })
}