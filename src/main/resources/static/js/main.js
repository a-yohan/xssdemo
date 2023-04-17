(function () {
    var burger = document.querySelector('.burger');
    if (burger) {
        var menu = document.querySelector('#' + burger.dataset.target);
        burger.addEventListener('click', function () {
            burger.classList.toggle('is-active');
            menu.classList.toggle('is-active');
        });
    }

    if (document.readyState !== 'loading') {
        OverlayScrollbars(document.querySelectorAll("body"), {});
    } else {
        document.addEventListener("DOMContentLoaded", function () {
            OverlayScrollbars(document.querySelectorAll("body"), {});
        });
    }
    var searchBox = document.getElementById("search")
    if(searchBox){
        searchBox.addEventListener('keydown', function (e) {
            e.stopPropagation();
            if (e.key === 'Enter') {
                document.location = `${document.location.origin}/?q=${searchBox.value}`;
            }
        });
    }
})();