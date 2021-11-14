const baseSummonerByNameURL = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/"


fetch(baseSummonerByNameURL + "erificeret" + "?" + key)
.then(respones => respones.json)
.then(result => {console.log(result)})