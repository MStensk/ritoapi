const tbodyElement = document.getElementById("matches-tbody");

fetch(baseURL+"/matches")
    .then(response => response.json())
    .then(result => {
        result.map(matches => createTable(matches));
    });


function createTable(matches) {
    const cardElement = document.createElement("tr");
    cardElement.innerHTML = `
        <td>${escapeHTML(matches.matchId.toString())}</td>
        <td>${escapeHTML(matches.summonerName)}</td>
        <td>${escapeHTML(matches.win.toString())}</td>
        <td>${escapeHTML(matches.kills.toString())}</td>
        <td>${escapeHTML(matches.deaths.toString())}</td>
        
        
    `;

    tbodyElement.appendChild(cardElement);
}