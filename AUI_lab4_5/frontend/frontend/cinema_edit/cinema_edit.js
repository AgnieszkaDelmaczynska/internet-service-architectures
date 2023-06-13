import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => updateInfoAction(event));
    fetchAndDisplayCinema();
});

function fetchAndDisplayCinema() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/genres/' + getParameterByName('genre')
        + '/cinemas/' + getParameterByName('cinema'), true);
    xhttp.send();
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayCinema();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/genres/' + getParameterByName('genre')
        + '/cinemas/' + getParameterByName('cinema'), true);

    const request = {
        'name': document.getElementById('name').value,
        'city': document.getElementById('city').value,
        'year_of_establishment': document.getElementById('year_of_establishment').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}