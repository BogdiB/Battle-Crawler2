package com.example.battlecrawler2.gamecontents;

public class Player extends Entity
{
    private final String name;
    private byte criticalStrikeChance, luck; // luck affects drops such as gold
    private int coins;

    public Player(String name)
    {
        super(EntitiesNames.Player); // must be first statement in constructor
        this.name = name;
        this.criticalStrikeChance = 5;
        this.coins = 0;
    }

    @Override
    public short getDamage()
    {
        // rnd is static
        short damage = (short) rnd.nextInt(this.baseAttackDamage - 5, this.baseAttackDamage + 6); // bound is exclusive, that's why it's +6 instead of +5;
        if ((byte) rnd.nextInt(0, 101) <= this.criticalStrikeChance)
        {
            // have a bool flag for critical strikes, so they can be displayed in a more special way
            damage += 5;
        }
        return damage;
    }

    public void getCoins(short coinsReceived)
    {
        this.coins += coinsReceived;
    }

    public void loseCoins(short coinsLost)
    {
        this.coins -= coinsLost;
        if (this.coins < 0)
            this.coins = 0;
    }
}