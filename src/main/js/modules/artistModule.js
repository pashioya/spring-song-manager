import {getCsrfHeader, getCsrfToken} from "./csrf";

export function addArtist(artistName, artistFollowers) {
    const header = getCsrfHeader();
    const token = getCsrfToken();
    return fetch('/api/artist', {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
            , [header]: token
        },
        body: JSON.stringify(
            {
                "artistName": artistName,
                "artistFollowers": parseInt(artistFollowers)
            })
    });
}

export function getArtists() {
    return fetch("/api/artist/artists",
        {
            method: "GET",
            headers: {
                'Accept': 'application/json',
            }
        })
}

export async function getArtist(artistId) {
    return await fetch(`/api/artist/${artistId}`, {
        method: "GET",
        headers: {
            Accept: "application/json",
        }
    })
}

export async function deleteArtist(artistId) {
    const header = getCsrfHeader();
    const token = getCsrfToken();
    return await fetch(`/api/artist/${artistId}`, {
        method: "DELETE",
        headers: {
            Accept: "application/json",
            'Content-Type': 'application/json',
            [header]: token
        }
    })
}

export async function getArtistsAlbums(artistId) {
    return await fetch(`/api/album/artist/${artistId}/albums`, {
        headers: {
            Accept: "application/json",
        }
    })
}
