const queryString = window.location.search;
const URLParams = new URLSearchParams(queryString);
const summonerId = URLParams.get("summonerId");

fetch(baseURL + "/summoners/" + summonerId)
    .then(response => response.json())
    .then(summoner => {
        document.getElementById("summoner-name").innerText = summoner.name;
        document.getElementById("summoner-account-id").innerText = summoner.accountId;
        document.getElementById("summoner-profile-icon-id").innerText = summoner.profileIconId;
        document.getElementById("summoner-puuid").innerText = summoner.puuid;
        document.getElementById("summoner-revision-date").innerText = summoner.revisionDate;
        document.getElementById("summoner-level").innerText = summoner.level;
    });


fetch(baseURL)
.then(respones => respones.json)
.then(result => {console.log(result)})