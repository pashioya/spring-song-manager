let deleteButtons = document.querySelectorAll('.delete-user-button');

deleteButtons.forEach(button => {
    button.addEventListener('click', async () => {
        const userId = button.dataset.userId;
        const response = await fetch(`api/user/${userId}`, {
            method: "DELETE",
            headers: {
                'Accept': 'application/json',
                "Content-Type": "application/json",
                [header]: token,
            }
        });
        if (response.status === 204) {
            button.parentElement.parentElement.remove();
        }
    });
});