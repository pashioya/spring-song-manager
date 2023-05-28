import {editUser} from "./modules/userModule.js";

const editUserForm = document.getElementById("edit-user-modal-form");
const submitEditButton = document.getElementById("edit-user-modal-submit-button");
const editUserButtons = document.querySelectorAll('.edit-user-button');
let id;
let username = document.getElementById("new-username").value;
let role = document.getElementById("new-role").value;

submitEditButton.addEventListener("click", async function (event) {
    event.preventDefault();
    event.stopPropagation();
    editUserForm.classList.add('was-validated');
    if (!editUserForm.checkValidity()) {
        return;
    }
    try {
        let response = await editUser(id, username, role);
        let newUser = await response.json();
        let newUserRow = document.createElement("tr");
        newUserRow.innerHTML = `
            <td>${newUser.username}</td>
            <td>${newUser.role}</td>
            <td>
                <button type="button" class="btn btn-danger" th:data-user-id="${newUser.id}">Delete</button>
                <button type="button" class="btn btn-secondary edit-user-button" data-bs-toggle="modal" data-bs-target="#edit-user-modal" data-user-id="${newUser.id}">Edit</button>
            </td>
        `;
        const closeButton = document.querySelector('#edit-user-modal > div > div > div.modal-header > button');
        const oldRow = document.querySelector(`[data-user-id="${id}"]`).parentElement.parentElement;
        const tableBody = document.getElementById("users-table-body");
        closeButton.click();
        oldRow.remove();
        tableBody.appendChild(newUserRow);
        document.querySelector(".modal-close-btn").click();
        editUserForm.classList.remove('was-validated');
        editUserForm.reset();
    } catch (error) {
        console.error(error);
        alert("Something went wrong. Please try again.");

    }
});


editUserButtons.forEach(button => {
    button.addEventListener('click', async () => {
        const userId = button.dataset.userId;
        id = userId;
        const response = await fetch(`api/user/${userId}`);
        const userData = await response.json();
        const usernameInput = document.querySelector('#new-username');
        const roleInput = document.querySelector('#new-role');
        usernameInput.value = userData.username;
        roleInput.value = userData.role;
    });
});
