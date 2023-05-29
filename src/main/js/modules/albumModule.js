import {getCsrfHeader, getCsrfToken} from "./csrf";
import axios from "axios";

export async function addAlbum(albumName, albumStatus, albumTrackCount, albumGenre, albumReleaseDate, artistId) {
    try {
        const header = getCsrfHeader();
        const token = getCsrfToken();
        return axios.post('/api/albums/artist/' + artistId, {
            "albumName": albumName,
            "officialTrackCount": parseInt(albumTrackCount),
            "albumStatus": albumStatus,
            "genre": albumGenre,
            "releaseDate": albumReleaseDate
        }, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                [header]: token
            }
        })
    } catch (error) {
        console.error(error);
    }
}

export function deleteAlbum(albId) {
    const header = getCsrfHeader();
    const token = getCsrfToken();
    return axios.delete(`/api/albums/${albId}`, {
            headers: {
                Accept: "application/json",
                [header]: token
            }
        }
    )
}

export function getAlbum(albId) {
    return axios.get(`/api/albums/${albId}`, {
            headers: {
                Accept: "application/json"
            }
        }
    ).then(
        resp => {
            return resp.data;
        }
    )
}

export function getAlbums() {
    return axios.get('/api/albums', {
            headers: {
                Accept: "application/json"
            }
        }
    ).then(
        resp => {
            return resp.data;
        }
    )
}

export function getAlbumsArtists(alId) {
    return axios.get(`/api/artists/album/${alId}`, {
            headers: {
                Accept: "application/json"
            }
        }
    ).then(
        resp => {
            return resp.data;
        }
    )
}

export function getAlbumsSongs(alId) {
    return axios.get(`/api/songs/album/${alId}`, {
            headers: {
                Accept: "application/json"
            }
        }
    ).then(
        resp => {
            return resp.data;
        }
    )
}