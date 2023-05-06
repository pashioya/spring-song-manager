import {getCsrfHeader, getCsrfToken} from "./modules/csrf";

const header = getCsrfHeader();
const token = getCsrfToken();

let deleteButtons = document.querySelectorAll('.delete-user-button');
deleteButtons.forEach(button => {
    button.addEventListener('click', async () => {
        const userId = button.dataset.userId;
        const response = await deleteUser(userId);

        if (response.status === 200) {
            button.closest('tr').remove();
        } else {
            console.log("Error: " + response.status);
        }
    });
});


export function deleteUser(userId) {
    return fetch(`/api/user/${userId}`, {
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