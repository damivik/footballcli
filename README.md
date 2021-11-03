# footballcli
A CLI application to get Football info - Scores, Fixtures, Standings, Top Scorers

## Usage
+ Register at https://www.football-data.org/client/register to get an API key

+ Set Environmental variable with name: "FOOTBALL_DATA_AUTH_TOKEN" and the API key as the value

+ Clone the repository, cd into project and run maven goal : `mvn package appassembler:assemble`

+ You can use the shell script or bat file(depending on your platform) in the project/target/appassembler/bin directory to run the commands,
  for convenience you can add the directory to your PATH.
 
### Available Commands
```
Usage: fball [-hV] [COMMAND]
Football cli
  -h, --help      Show this help message and exit.
  -V, --version   Print version information and exit.
Commands:
  competitions  List available competitions
  matches       List matches
  standings     View current standings for a particular league
  scorers       View top scorers for a particular league
```

### Competitions Command

To view available competitions use the command : `$ fball competitions`

### Matches Command

To get scores of past/live matches and upcoming fixtures use the command : `$ fball matches`

```
Usage: fball matches [-hV] [-c=COMPETITION] [-d=DAYS]
List matches
  -c, --competition=COMPETITION
                    Competition
  -d, --days=DAYS   number of days from today
  -h, --help        Show this help message and exit.
  -V, --version     Print version information and exit.
```

### Standings Command

To get current league table for a particular league

```
Usage: fball standings [-hV] -c=COMPETITION
View current standings for a particular league
  -c, --competition=COMPETITION
                  Competition code
  -h, --help      Show this help message and exit.
  -V, --version   Print version information and exit.
```

### Scorers Command

To get the top scorers for a particular league

```
Usage: fball scorers [-hV] -c=COMPETITION
View top scorers for a particular league
  -c, --competition=COMPETITION
                  Competition code
  -h, --help      Show this help message and exit.
  -V, --version   Print version information and exit.
```
