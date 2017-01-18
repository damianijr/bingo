# Bingo
This is a simple bingo swing app, developed in mid 2011, when my family was tired of buy cards for the family bingo in christmas party.

Then.. i decided create this software and put here only in 2017.. a litle bit refactored.. 

![alt tag](https://raw.githubusercontent.com/damianijr/bingo/master/screenshots/bingo1.png)


# Starting
build project:
```
./gradlew clean build
```
Start docker-compose:
```
java -jar build/libs/bingo-1.0-SNAPSHOT.jar
```

# Features
- Sounds features:
    - When a ball is raffled, the software check if have audio in resources/sounds/balls/<numberBall>/ and play random sounds.
    - When the game is ending (10 balls left) the app turn on the "Thriller mode" and then a music and animatin start from each ball raffled.

![alt tag](https://raw.githubusercontent.com/damianijr/bingo/master/screenshots/bingo2.png)

- Keyfeatures
    - Supports to multilaser Ac164 (slider)

# contacts
damianijr@gmail.com
