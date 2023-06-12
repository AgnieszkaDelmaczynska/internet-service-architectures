import {
    getParameterByName,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayCinema();
});

function fetchAndDisplayCinema() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayCinema(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/genres/' + getParameterByName('genre')
        + '/cinemas/' + getParameterByName('cinema'), true);
    xhttp.send();
}

function displayCinema(cinema) {
    setTextNode('name', cinema.name);
    setTextNode('city', cinema.city);
    setTextNode('year_of_establishment', cinema.year_of_establishment);
    setTextNode('genre', cinema.genre);
}
