const allRows = document.getElementsByClassName("table-row");

export function getSongs() {
    return fetch("/api/song/songs", {
        headers: {
            Accept: "application/json"
        }
    })
        .then(resp => {
                if (resp.status !== 200) {
                    console.log("Error: " + resp.status);
                } else {
                    return resp.json();
                }
            }
        )
        .then(data => {
                return data;
            }
        );
}

for (let row of allRows) {
    row.addEventListener("click", () => {
        //     on click, redirect to the album page
        window.location.href = row.getAttribute("data-href");
    });
}