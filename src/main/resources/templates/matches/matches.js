const queryString = window.location.search;
const URLParams = new URLSearchParams(queryString);
const matchId = URLParams.get()
const tbodyElement = document.getElementById("matches-tbody");

fetch(baseURL+"/matches")
    .then(response => response.json())
    .then(result => {
        result.map(matches => createTable(matches));
    });


function createTable(matches) {
    const tableRowElementElement = document.createElement("tr");
    tableRowElementElement.innerHTML = `
        <td>${escapeHTML(matches.matchId.toString())}</td>
        <td>${escapeHTML(matches.summonerName)}</td>
        <td>${escapeHTML(matches.win.toString())}</td>
        <td>${escapeHTML(matches.kills.toString())}</td>
        <td>${escapeHTML(matches.deaths.toString())}</td>
        
        
    `;

    tbodyElement.appendChild(tableRowElementElement);
}

function createCard(matches){
    const divElement = document.createElement("div")
}