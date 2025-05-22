package cz.cuni.mff.dockalea;

import cz.cuni.mff.dockalea.entities.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testAddXpLevelsUp() {
        Player player = new Player(100, 1, 0);
        player.addXp(100);

        assertTrue(player.getLevel() > 1);
    }

    @Test
    public void testTakeDamageReducesHealth() {
        Player player = new Player(100, 1, 0);
        player.takeDamage(20);

        assertEquals(80, player.getCurrentHealth());
    }

    @Test
    public void testIsAliveReturnsFalseWhenHealthZero() {
        Player player = new Player(100, 1, 0);
        player.takeDamage(100);

        assertFalse(player.isAlive());
    }
}
