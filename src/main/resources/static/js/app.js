

$(document).ready(function($) {
    $(".table-row").click(function() {
        window.document.location = $(this).data("href");

    });
});

$(document).ready(function($) {
    $("#all-artists-table-body").on("click", ".table-row", function() {
        window.document.location = $(this).data("href");
    });
});

$(document).ready(function($) {
    $("#all-albums-table-body").on("click", ".table-row", function() {
        window.document.location = $(this).data("href");
    });
});

$(document).ready(function($) {
    $("#all-songs-table-body").on("click", ".table-row", function() {
        window.document.location = $(this).data("href");
    });
});

(function () {
    const forms = document.querySelectorAll('.needs-validation');
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }
                form.classList.add('was-validated')
            }, false)
        })
})()


