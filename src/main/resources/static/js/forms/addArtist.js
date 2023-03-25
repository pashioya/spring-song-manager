/**
 * @type {HTMLFormElement}
 */

let form = document.getElementById("add-artist-form");
let artistName = document.getElementById("artistName");
let artistFollowers = document.getElementById("artistFollowers");
let submitButton =  form.querySelector('button[type="submit"]');



submitButton.addEventListener("click", trySubmitForm);

function trySubmitForm(event) {
    event.preventDefault();
    const formIsValid = form.checkValidity();

    const header = document.querySelector('meta[name="_csrf_header"]').content;
    const token = document.querySelector('meta[name="_csrf"]').content;
    form.classList.add('was-validated');

    if (formIsValid) {
        fetch('/api/artist/create', {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
                ,[header]: token
            },
            body: JSON.stringify(
                {
                "artistName": artistName.value,
                "artistFollowers": parseInt(artistFollowers.value)
            })
        }).then(response => {
            if (response.status === 201) {

                form.reset();
                form.classList.remove('was-validated');
                window.location.href = "/allArtists";
            }
        });
    }
}