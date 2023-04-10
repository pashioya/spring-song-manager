

const editUserForm = document.getElementById("edit-user-modal-form");

const header = document.querySelector('meta[name="_csrf_header"]').content;
const token = document.querySelector('meta[name="_csrf"]').content;
const submitEditButton = document.getElementById("edit-user-modal-submit-button");
const editUserButtons = document.querySelectorAll('.edit-user-button');
let id = 0;


function trySubmitForm(event) {
    event.preventDefault();
    const formData = new FormData(editUserForm);

    console.log(formData.get('username'));
    console.log(formData.get('password'));
    console.log(formData.get('role'));


    const userDto = {
        username: formData.get('username'),
        password: formData.get('password'),
        role: formData.get('role')
    };

    console.log(JSON.stringify(userDto));

    const url = "/api/user/" + id;
    console.log(url)
    fetch(url, {
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
            if (data.status === 201) {
                location.reload();
            }
        })
        .catch((error) => {
            console.error("Error:", error);
        });
}



editUserButtons.forEach(button => {
    button.addEventListener('click', async () => {
        const userId = button.dataset.userId;
        id = userId;
        const response = await fetch(`api/user/${userId}`);
        console.log(response);
        const userData = await response.json();
        console.log(userData)
        const usernameInput = document.querySelector('#new-username');
        const roleInput = document.querySelector('#new-role');
        usernameInput.value = userData.username;
        roleInput.value = userData.role;
    });
});



submitEditButton.addEventListener("click", trySubmitForm);