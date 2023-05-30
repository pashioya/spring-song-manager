import {getCsrfHeader, getCsrfToken} from "./csrf";
import axios from "axios";


export function getUsers() {
    return axios.get('/api/users').then(r => {
            return r.data;
        }
    );
}

export function getUser(userId) {
    return axios.get(`/api/users/${userId}`).then(r => {
        return r.data;
    });
}


export function addUser(username, password, role) {
    const header = getCsrfHeader();
    const token = getCsrfToken();
    return axios.post('/api/users', {
            "username": username,
            "password": password,
            "role": role
        },
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
                , [header]: token
            }
        }
    ).then(r => {
        return r.data;
    });
}


export function editUser(id, newUsername, newRole) {
    const header = getCsrfHeader();
    const token = getCsrfToken();

    return axios.patch(`/api/users/${id}`, {
            "username": newUsername,
            "role": newRole
        },
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
                , [header]: token
            }
        }
    ).then(r => {
        return r.data;
    });
}


export function deleteUser(userId) {
    const header = getCsrfHeader();
    const token = getCsrfToken();

    return axios.delete(`/api/users/${userId}`, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
            , [header]: token
        }
    }).then(r => {
        return r;
    });
}