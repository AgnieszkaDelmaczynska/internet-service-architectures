import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const newGenre = document.getElementById('newGenre')
    newGenre.addEventListener('submit', event => createGenre(event));
});


function createGenre(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();

    xhttp.open("POST", getBackendUrl() + '/api/genres', true);

    const request = {
        'name': document.getElementById("name").value,
        'year_of_creation': parseInt(document.getElementById("year").value),
        'precursor': document.getElementById("precursor").value,
        'popularity': parseInt(document.getElementById("popularity").value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}