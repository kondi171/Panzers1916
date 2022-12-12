
# Panzers1916

A game based on Tank 1990 game released on pegasus. This project is a little bit
different, because there is on co-operation, instead players fight each other.

## How to run?

First of all install a Java Runtime Environment (JRE) or Java Development Kit (JDK)
in version above 12! After that run  **Panzers 1916®** file.

Download:

[Java Runtime Environment](https://www.java.com/pl/download/manual.jsp)
[Java Development Kit](https://www.oracle.com/pl/java/technologies/downloads/)

**ATTENTION!** Full screen does not work on some screens! Epilepsy warning!## Game Rules

The board has been divided into 26 x 13 tiles, excluding black
border. Players and their bullets can only move around
fixed grid. For each player was reserved part of screen where is stats are displayed
screen where his stats and player name are displayed.

There are 4 types of blocks on the Board:
- Brick - A static destructible unit that can be obtained for 100 points for
  each hit and destruction,
- Stone - Static and indestructible unit,
- Water - A unit that only bullets can pass through,
- Empty - A block that players can move freely on

Due to the Grid template, the player can only move in 4 directions, no indirect
route possible. After firing the maximum number of bullets allowed in a burst
(set to two), begins a shot restriction - on the board can
be a maximum of two shoots per player, when player reach
limit, the next time he can fire only when there is a bullet collision
and the bullets will disappear from the board.
Each player has 3 lives, each life taken from the opponent
awards 1000 points to the player who managed to hit the opponent,
and takes the life of the unit hit. For every hit
destructible bricks, the player gets 100 points, which diversify the game and
decide which player was better in a given game. Every tank
can fire two bullets in one burst. When the life of one of the players
reaches zero - the game is over.

## Control keys

    1. Menu:
        - Up Arrow - go up
        - Down Arrow - go down
        - Enter - Accept
    2. First player:
        - W - go up
        - S- go down
        - A - go left
        - D - go right
        - Space - shoot
    3. Second player:
        - Up Arrow - go up
        - Down Arrow - go down
        - Left Arrow - go left
        - Right Arrow - go right
        - K - shoot

## Documentation

Whole documentation is in **documentation** folder after running **index.html** file.

## Tech Stack

- Java
- Swing
- AWT

## Authors

- [@kondi171](https://github.com/kondi171)
- [@kwypych4](https://github.com/kwypych4)