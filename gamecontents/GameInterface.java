package com.example.battlecrawler2.gamecontents;

public interface GameInterface
{
    public void start(); // gets the player
    public void play(); // gets the enemy
    public void nextTurn(); // does whatever must happen in a turn on getting user command
    public boolean getDebugMode(); // debug mode is set from the start app arguments, if 1, game outputs in standard output messages for every turn
}