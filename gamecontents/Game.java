package com.example.battlecrawler2.gamecontents;

import java.util.ArrayList;
import java.util.List;

public class Game implements GameInterface
{
    private static boolean debug_mode;
    private Player player;
    private Entity enemy;
    // TODO: make pity list, at 2 consecutive losses, enemy becomes weaker
    // TODO: make history
    // I am very aware that these variables being lists is inefficient and nonsensical, I don't like it either, but I don't have other uses for lists, and I have to use 2 of them because of the exercise
    private List<Boolean> pity = new ArrayList<>(); // this gets cleared every time the player wins, pity does not accumulate through sessions, e.g.: if you restart the game your pity starts at 0
    private List<Boolean> history = new ArrayList<>(); // this is per session

    public Game(String arg) throws IllegalArgumentException // arg is a boolean value which is 0 in case of no debug mode, 1 in case of debug mode, if other input is found, throw error
    {
        // if args in the app is empty, then arg in this constructor still gets sent a "0" string, for no debug
        if (arg.equals("0"))
            debug_mode = false;
        else if (arg.equals("1"))
        {
            debug_mode = true;
            System.out.println("Debug Mode On\n");
        }
        else throw new IllegalArgumentException("Argument given not boolean (0/1).");
    }

    public void start()
    {
        // here it creates the player
        this.player = new Player("Hero");
        if (debug_mode)
            System.out.println("Player created.");
    }

    public void play()
    {
        // here it is decided which entity the player will be facing
        this.enemy = new Entity(EntitiesNames.Goblin);
        if (debug_mode)
            System.out.println("Enemy created.\n");
    }

    public byte nextTurn() // should receive a parameter with which button was pressed (maybe make an enum?) - or not since I only have the attack button right now
    {
        // 0 if no one is dead, -1 if player is dead, 1 if enemy is dead
        short dmg = this.player.getDamage();
        if (pity.size() >= 2)
            dmg += 5;
        this.enemy.takeDamage(dmg);
        if (debug_mode)
            System.out.println("Enemy took " + dmg + " damage.");
        if (this.enemy.isDead())
        {
            this.win();
            return 1;
        }
        dmg = this.enemy.getDamage();
        this.player.takeDamage(dmg);
        if (debug_mode)
            System.out.println("Player took " + dmg + " damage.\n");
        if (this.player.isDead())
        {
            this.lose();
            return -1;
        }
        return 0;
    }

    public void win()
    {
        this.history.add(true);
        this.pity.clear();
        if (debug_mode)
            System.out.println("Game won.\n");
    }

    public void lose()
    {
        this.history.add(false);
        this.pity.add(false);
        if (debug_mode)
            System.out.println("Game lost.\n");
    }

    public boolean getDebugMode()
    {
        // false if no debug
        return debug_mode;
    }

    public short getPity()
    {
        return (short) this.pity.size();
    }

    public short getPlayerHP()
    {
        return player.getCurrentHP();
    }

    public short getEnemyHP()
    {
        return enemy.getCurrentHP();
    }

    public short getPlayerMaxHP()
    {
        return player.getMaxHP();
    }

    public short getMaxHP()
    {
        return enemy.getMaxHP();
    }
}