import {getCsrfHeader, getCsrfToken} from "./csrf";
import axios from "axios";

export function addArtist(artistName, artistFollowers) {
    const header = getCsrfHeader();
    const token = getCsrfToken();
    return axios.post('/api/artist', {
        "artistName": artistName,
        "artistFollowers": parseInt(artistFollowers)
    }, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            [header]: token
        }
    })
}

export function getArtists() {
    return axios.get('/api/artist/artists', {
        headers: {
            Accept: "application/json"
        }
    })
}

export async function getArtist(artistId) {
    return axios.get(`/api/artist/${artistId}`, {
        headers: {
            Accept: "application/json"
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
    return axios.get(`/api/artist/${artistId}/albums`, {
        headers: {
            Accept: "application/json"
        }
    })
}
