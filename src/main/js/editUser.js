import '../css/global.scss'

const editUserForm = document.getElementById("edit-user-modal-form");
const header = document.querySelector('meta[name="_csrf_header"]').content;
const token = document.querySelector('meta[name="_csrf"]').content;
const submitEditButton = document.getElementById("edit-user-modal-submit-button");
const editUserButtons = document.querySelectorAll('.edit-user-button');
let id = 0;


async function trySubmitForm(event) {
    event.preventDefault();
    const formData = new FormData(editUserForm);

    const userDto = {
        username: formData.get('username'),
        role: formData.get('role')
    }
    const url = "/api/user/" + id;
    await fetch(url, {
        method: "PATCH",
        headers: {
            'Accept': 'application/json',
            "Content-Type": "application/json",
            [header]: token,
        },
        body: JSON.stringify(userDto),
    })
        .then((response) => response.json())
        .then((data) => {
            const closeButton = document.querySelector('#edit-user-modal > div > div > div.modal-header > button');


            const oldRow = document.querySelector(`[data-user-id="${data.id}"]`).parentElement.parentElement;
            const row = document.createElement("tr");
            const tableBody = document.getElementById("users-table-body");
            row.innerHTML = `
                                    <td>${data.username}</td>
                                    <td>${data.role}</td>
                                    <td>
                                        <button type="button" class="btn btn-danger" th:data-user-id="${data.id}">Delete</button>
                                        <button type="button" class="btn btn-secondary edit-user-button" data-bs-toggle="modal" data-bs-target="#edit-user-modal" data-user-id="${data.id}">Edit</button>
                                    </td>
                                    `;
            closeButton.click();
            oldRow.remove();
            tableBody.appendChild(row);
        })
}


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


submitEditButton.addEventListener("click", trySubmitForm);