import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayGenre();
    fetchAndDisplayCinemas();
    displayCreateCinema();
});

function fetchAndDisplayCinemas() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayCinemas(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/genres/' + getParameterByName('genre')
        + '/cinemas', true);
    xhttp.send();
}

function displayCinemas(cinemas) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    cinemas.cinemas.forEach(cinema => {
        tableBody.appendChild(createTableRow(cinema));
    });

}

function displayCreateCinema() {
    let newCinBtn = document.getElementById('newCin');
    newCinBtn.appendChild(createLinkCell('Create a new cinema', '../cinema_create/cinema_create.html?genre='
        + getParameterByName('genre')))
}

function createTableRow(cinema) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(cinema.id));
    tr.appendChild(createTextCell(cinema.name));
    tr.appendChild(createLinkCell('view', '../cinema_view/cinema_view.html?genre='
        + getParameterByName('genre') + '&cinema=' + cinema.id));
    tr.appendChild(createLinkCell('edit', '../cinema_edit/cinema_edit.html?genre='
        + getParameterByName('genre') + '&cinema=' + cinema.id));
    tr.appendChild(createButtonCell('delete', () => deleteCinema(cinema.id)));
    return tr;
}

function deleteCinema(cinema) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayCinemas();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/genres/' + getParameterByName('genre')
        + '/cinemas/' + cinema, true);
    xhttp.send();
}

function fetchAndDisplayGenre() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayGenre(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/genres/' + getParameterByName('genre'), true);
    xhttp.send();
}

function displayGenre(genre) {
    setTextNode('genre', genre.name);
    setTextNode('year_of_creation', genre.year_of_creation);
    setTextNode('precursor', genre.precursor);
    setTextNode('popularity', genre.popularity);
}