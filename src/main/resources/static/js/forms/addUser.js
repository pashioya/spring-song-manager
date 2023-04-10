const addUserForm = document.getElementById("add-user-modal-form");
const submitAddButton = document.getElementById("add-user-modal-submit-button");
function trySubmitForm(event) {
    event.preventDefault();
    const header = document.querySelector('meta[name="_csrf_header"]').content;
    const token = document.querySelector('meta[name="_csrf"]').content;
    const formData = new FormData(addUserForm);
    const url = "/api/user";
    fetch(url, {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            [header]: token,
        },
        body: formData,
    })
        .then((response) => response.json())
        .then((data) => {
            console.log(data)
            // click the close button
            const closeButton = document.querySelector('#add-user-modal > div > div > div.modal-header > button');
            closeButton.click();

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
            console.log(row)
            tableBody.appendChild(row);
        })
    .catch((error) => {
        console.error("Error:", error);
    });
}

submitAddButton.addEventListener("click", trySubmitForm);
