import {getCsrfHeader, getCsrfToken} from "./modules/csrf";
import {deleteUser} from "./user";

const header = getCsrfHeader();
const token = getCsrfToken();

const submitAddButton = document.getElementById("add-user-modal-submit-button");
const form = document.getElementById("add-user-modal-form");
const username = document.getElementById("username");
const password = document.getElementById("password");
const role = document.getElementById("role");

export function addUser(username, password, role) {
    return fetch('/api/user', {
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

submitAddButton.addEventListener("click", async function (event) {
    event.preventDefault();
    event.stopPropagation();
    form.classList.add('was-validated');

    if (!form.checkValidity()) {
        return;
    }

    try {
        const response = await addUser(username.value, password.value, role.value);
        const newUser = await response.json();
        const newUserRow = document.createElement("tr");
        newUserRow.innerHTML = `
            <td>${newUser.username}</td>
            <td>${newUser.role}</td>
            <td>
                <button type="button" class="btn btn-danger" th:data-user-id="${newUser.id}">Delete</button>
                <button type="button" class="btn btn-secondary edit-user-button" data-bs-toggle="modal" data-bs-target="#edit-user-modal" data-user-id="${newUser.id}">Edit</button>
            </td>
        `;
        const tableBody = document.getElementById("users-table-body");
        tableBody.appendChild(newUserRow);

        // apply onclick to the new delete button
        const deleteButton = newUserRow.querySelector('.btn-danger');
        deleteButton.onclick = function () {
            deleteUser(newUser.id);
        };
        document.querySelector(".modal-close-btn").click();
        form.classList.remove('was-validated');
        form.reset();
    } catch (error) {
        console.error(error);
        alert("Something went wrong. Please try again.");
    }
});

