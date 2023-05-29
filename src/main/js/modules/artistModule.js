import {getCsrfHeader, getCsrfToken} from "./csrf";
import axios from "axios";

export function addArtist(artistName, artistFollowers) {
    const header = getCsrfHeader();
    const token = getCsrfToken();
    return axios.post('/api/artists', {
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
    return axios.get('/api/artists/artists', {
        headers: {
            Accept: "application/json"
        }
    }).then(
        resp => {
            return resp.data;
        }
    )
}

export async function getArtist(artistId) {
    return axios.get(`/api/artists/${artistId}`, {
        headers: {
            Accept: "application/json"
        }
    }).then(
        resp => {
            return resp.data;
        }
    )
}

export async function deleteArtist(artistId) {
    const header = getCsrfHeader();
    const token = getCsrfToken();
    return await fetch(`/api/artists/${artistId}`, {
        method: "DELETE",
        headers: {
            Accept: "application/json",
            'Content-Type': 'application/json',
            [header]: token
        }
    })
}

export async function getArtistsAlbums(artistId) {
    return axios.get(`/api/albums/artist/${artistId}`, {
        headers: {
            Accept: "application/json"
        }
    }).then(
        resp => {
            return resp.data;
        }
    )
}
