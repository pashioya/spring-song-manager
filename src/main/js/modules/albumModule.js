import {getCsrfHeader, getCsrfToken} from "./csrf";

export async function addAlbum(albumName, albumStatus, albumTrackCount, albumGenre, albumReleaseDate, artistId) {
    try {
        const header = getCsrfHeader();
        const token = getCsrfToken();
        return await fetch('/api/album/artist/' + artistId, {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                [header]: token
            },
            body: JSON.stringify({
                "albumName": albumName,
                "officialTrackCount": parseInt(albumTrackCount),
                "albumStatus": albumStatus,
                "genre": albumGenre,
                "releaseDate": albumReleaseDate
            })
        });
    } catch (error) {
        console.error(error);
    }
}

export function deleteAlbum(albId) {
    const header = getCsrfHeader();
    const token = getCsrfToken();
    return fetch(`/api/album/${albId}`, {
        method: "DELETE",
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            [header]: token,
        },
    })
}

export function getAlbum(albId) {
    return fetch(`/api/album/${albId}`,
        {
            headers: {
                Accept: "application/json"
            }
        })
}

export function getAlbums() {
    return fetch("/api/album/albums",
        {
            headers: {
                Accept: "application/json"
            }
        })
}

export function getAlbumsArtists(alId) {
    return fetch(`/api/artist/album/${alId}/artists`,
        {
            headers: {
                Accept: "application/json"
            }
        })
}

export function getAlbumsSongs(alId) {
    return fetch(`/api/song/album/${alId}/songs`
        , {
            headers: {
                Accept: "application/json"
            }
        })
}