import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayGenres();
});

function fetchAndDisplayGenres() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayGenres(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/genres', true);
    xhttp.send();
}

function displayGenres(genres) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    genres.genres.forEach(genre => {
        tableBody.appendChild(createTableRow(genre.name));
    })
}

function createTableRow(genre) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(genre));
    tr.appendChild(createLinkCell('view', '../genre_view/genre_view.html?genre='
        + genre));
    tr.appendChild(createLinkCell('edit', '../genre_edit/genre_edit.html?genre='
        + genre));
    tr.appendChild(createButtonCell('delete', () => deleteGenre(genre)));
    return tr;
}

function deleteGenre(genre) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayGenres();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/genres/' + genre, true);
    xhttp.send();
}