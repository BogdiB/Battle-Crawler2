package com.example.battlecrawler2.gamecontents;

import java.util.Random;

public class Entity implements EntityInterface
{
    protected short maxHP, currentHP, baseAttackDamage; // TODO: add levels
    protected boolean dead = false;
    protected final static Random rnd = new Random();

    public Entity(EntitiesNames en) throws RuntimeException// THROW CUSTOM EXCEPTION IF ENTITY NAME NOT FOUND
    {
        if (en == EntitiesNames.Player)
        {
            this.maxHP = 100;
            this.currentHP = this.maxHP;
            this.baseAttackDamage = 20;
        }
        else if (en == EntitiesNames.Goblin)
        {
            this.maxHP = 100;
            this.currentHP = this.maxHP;
            this.baseAttackDamage = 20;
        }
        else
        {
            System.out.println("Entity not found. Thank you for playing!\nPlease report this error on my GitHub (BogdiB, repository: BattleCrawler2).");
            throw new RuntimeException("Entity not found. Thank you for playing!\nPlease report this error on my GitHub (BogdiB, repository: BattleCrawler2).");
        }
    }

    public short getDamage()
    {
        return (short) rnd.nextInt(this.baseAttackDamage - 5, this.baseAttackDamage + 6); // bound is exclusive, that's why it's +6 instead of +5
    }

    @Override
    public void takeDamage(short damage) // returns 0 if alive, and 1 if not alive - maybe not
    {
        this.currentHP -= damage;
        if (this.currentHP <= 0)
            this.dead = true;
    }

    public void die()
    {
        this.dead = true;
    }

    @Override
    public boolean isDead()
    {
        return this.dead;
    }

    public short getCurrentHP()
    {
        return this.currentHP;
    }

    public short getMaxHP()
    {
        return this.maxHP;
    }
}