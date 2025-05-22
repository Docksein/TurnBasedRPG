package cz.cuni.mff.dockalea;

import cz.cuni.mff.dockalea.items.HealthPotion;
import cz.cuni.mff.dockalea.items.Inventory;
import cz.cuni.mff.dockalea.items.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    @Test
    public void testAddItemIncreasesSize() {
        Inventory inventory = new Inventory(5);
        Item item = new HealthPotion("Potion", "Heals 10 HP", 10);

        assertTrue(inventory.addItem(item));
        assertEquals(1, inventory.getSize());
    }

    @Test
    public void testAddItemOverCapacityFails() {
        Inventory inventory = new Inventory(1);
        inventory.addItem(new HealthPotion("Potion", "Heals 10 HP", 10));
        boolean result = inventory.addItem(new HealthPotion("Elixir", "Heals 20 HP", 20));

        assertFalse(result);
        assertEquals(1, inventory.getSize());
    }

    @Test
    public void testRemoveItemDecreasesSize() {
        Inventory inventory = new Inventory(5);
        Item item = new HealthPotion("Potion", "Heals 10 HP", 10);
        inventory.addItem(item);
        inventory.removeItem(item);

        assertEquals(0, inventory.getSize());
    }
}
