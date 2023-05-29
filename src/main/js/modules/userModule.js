import {getCsrfHeader, getCsrfToken} from "./csrf";

export function addUser(username, password, role) {
    const header = getCsrfHeader();
    const token = getCsrfToken();
    return fetch('/api/users', {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
            , [header]: token
        },
        body: JSON.stringify(
            {
                "username": username,
                "password": password,
                "role": role
            })
    });
}


export function editUser(id, newUsername, newRole) {
    const header = getCsrfHeader();
    const token = getCsrfToken();
    return fetch(`/api/users/${id}`, {
        method: "PATCH",
        headers: {
            'Accept': 'application/json',
            "Content-Type": "application/json",
            [header]: token,
        },
        body: JSON.stringify({
            username: newUsername,
            role: newRole
        }),
    });
}


export function deleteUser(userId) {
    const header = getCsrfHeader();
    const token = getCsrfToken();
    return fetch(`/api/users/${userId}`, {
        method: "DELETE",
        headers: {
            'Accept': 'application/json',
            "Content-Type": "application/json",
            [header]: token,
        }
    }).then(r => {
        return r;
    });
}