import {deleteUser} from "./modules/userModule";

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

