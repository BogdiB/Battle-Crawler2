package com.example.battlecrawler2.gamecontents;

public interface EntityInterface
{
    void takeDamage(short damage); // returns 0 if alive, and 1 if unalive :) - maybe
    boolean isDead(); // 0 if alive, 1 if not
}