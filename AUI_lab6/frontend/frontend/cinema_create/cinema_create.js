import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const newCin = document.getElementById('newCin')
    newCin.addEventListener('submit', event => createCinema(event));
});


function createCinema(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();

    xhttp.open("POST", getBackendUrl() + '/api/genres/' + getParameterByName('genre')
        + '/cinemas', true);

    const request = {
        'name': document.getElementById("name").value,
        'city': document.getElementById("city").value,
        'year_of_establishment': document.getElementById("year_of_establishment").value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}