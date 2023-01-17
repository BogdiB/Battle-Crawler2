package com.example.battlecrawler2.gamecontents;

import java.util.Objects;

public class Game
{
    private static boolean debug_mode;
    private Player player;
    private Entity enemy;
    // TODO: make pity list, at 2 consecutive losses, enemy becomes weaker

    public Game(String arg) throws RuntimeException // arg is a boolean value which is 0 in case of no debug mode, 1 in case of debug mode, if other input is found, throw error
    {
        // if args in the app is empty, then arg in this constructor still gets sent a "0" string, for no debug
        if (arg.equals("0"))
            debug_mode = false;
        else if (arg.equals("1"))
            debug_mode = true;
        else throw new RuntimeException("Argument given not boolean (0/1).");
    }

    public void start()
    {
        // here it creates the player
        this.player = new Player("Hero");
    }

    public void play()
    {
        // here it is decided which entity the player will be facing
        this.enemy = new Entity(EntitiesNames.Goblin);
    }

    public void nextTurn() // should receive a parameter with which button was pressed (maybe make an enum?)
    {
        ;
    }

    public boolean getDebugMode()
    {
        // false if no debug
        return debug_mode;
    }
}