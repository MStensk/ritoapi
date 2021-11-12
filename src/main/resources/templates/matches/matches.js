const baseMatchByPuuId = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/"

//how many matches to show from start index to end count
const start = "start=0";
const count = "count=20";

//you find puuid by fetching summoner
const puuid = "0NiXceRFtc3QMCJRrWYJsvdirBXcjemLL9ujlCGpZCIlUPA9y6ruSQ-YOFQsJ2xSWNlwcZ3zv-wYaQ"

fetch(baseMatchByPuuId + puuid + "/ids?" + start + "&" + count + "&" + key)
    .then(response => response.json())
    .then(result => { console.log(result);
    });
