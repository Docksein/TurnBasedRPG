package cz.cuni.mff.dockalea;

import cz.cuni.mff.dockalea.entities.Enemy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    @Test
    public void testEnemyNameAndLevel() {
        Enemy enemy = new Enemy("Goblin", 2, false);

        assertEquals("Goblin", enemy.getName());
        assertEquals(2, enemy.getLevel());
    }

    @Test
    public void testEnemyIsBossFlag() {
        Enemy boss = new Enemy("Dragon", 10, true);
        Enemy minion = new Enemy("Rat", 1, false);

        assertTrue(boss.isBoss());
        assertFalse(minion.isBoss());
    }

    @Test
    public void testEnemyInitialHealthIsPositive() {
        Enemy enemy = new Enemy("Orc", 3, false);

        assertTrue(enemy.getCurrentHealth() > 0);
        assertEquals(enemy.getMaxHealth(), enemy.getCurrentHealth());
    }

    @Test
    public void testEnemyTakesDamageCorrectly() {
        Enemy enemy = new Enemy("Skeleton", 2, false);
        int initialHealth = enemy.getCurrentHealth();

        enemy.takeDamage(10);
        assertEquals(initialHealth - 10, enemy.getCurrentHealth());
    }

    @Test
    public void testEnemyHealthCannotGoNegative() {
        Enemy enemy = new Enemy("Slime", 1, false);

        enemy.takeDamage(999);
        assertEquals(0, enemy.getCurrentHealth());
    }

    @Test
    public void testIsAliveAfterTakingLethalDamage() {
        Enemy enemy = new Enemy("Zombie", 2, false);
        enemy.takeDamage(enemy.getMaxHealth());

        assertFalse(enemy.isAlive());
    }

    @Test
    public void testEnemyXpRewardRegular() {
        Enemy enemy = new Enemy("Wolf", 4, false);

        assertEquals(40, enemy.getXpReward()); // 4 * 10
    }

    @Test
    public void testEnemyXpRewardBoss() {
        Enemy boss = new Enemy("Lich", 6, true);

        assertEquals(300, boss.getXpReward()); // 6 * 50
    }
}
