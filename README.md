# GithubListLanguageApi
REST microservice that list the languages used by the 100 trending public repos on GitHub.

Api Description
=======

list languages used by the 100 trending public repos on Github created in the last 30 days with the following informations :
  * Number of repos using each language
  * List of repos using each language

API Endpoints
=======

## All
---------------

``` html
https://git-repos-languages.herokuapp.com/languages/list
```
  ### Response Example
  ---------------

``` json
[
    {
        "name": "C#",
        "repos": [
            "AeonLucid/Impostor",
            "jorgejgnz/HPTK-Sample",
            "G0ldenGunSec/SharpSecDump",
            "r3nhat/GRAT2"
        ],
        "count": 4
    },
    {
        "name": "C",
        "repos": [
            "spawnfest/bakeware",
            "charliesome/doslinux",
            "DerekSelander/yacd",
            "bellard/quickjs",
            "TheOfficialFloW/GePatch"
        ],
        "count": 5
    },
    {
        "name": "Go",
        "repos": [
            "blushft/go-diagrams",
            "nakabonne/ali",
            "muesli/duf",
            "deroproject/graviton",
            "certikfoundation/shentu",
            "lucasepe/modgv",
            "tailscale/depaware",
            "mitchellh/go-glint",
            "denverquane/amongusdiscord"
        ],
        "count": 9
    }
]
```

