package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

	@Test
	public void testTheTruth() {
		assertTrue(true);
	}
	@Test
	public void exampleTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 19, quality);
	}


	@Test
	public void sellInCheck() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Conjured Mana Cake", 3, 6));
		inn.oneDay();
		inn.oneDay();
		inn.oneDay();
		
		//access a list of items, get the sell by date of the one set
		List<Item> items = inn.getItems();
		int sellIn = items.get(0).getSellIn();
		
		assertEquals("Should have reached sell by date", 0, sellIn);
	}
	
	@Test
	public void qualityNotZero() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Conjured Mana Cake", 3, 6));
		inn.oneDay();
		inn.oneDay();
		inn.oneDay();
		inn.oneDay();
		inn.oneDay();

		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Should have reached 0", 0, quality);
	}
	
	@Test
	public void qualityBelowZero() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Conjured Mana Cake", 3, 6));
		inn.oneDay();
		inn.oneDay();
		inn.oneDay();
		inn.oneDay();
		inn.oneDay();
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Quality gone under", 0, quality);
	}

	
	@Test
	public void brieCheck() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 2, 0));
		for(int i = 0; i <50; i++) {
			inn.oneDay();
		}
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Should be max quality", 50, quality);
	}
	
	@Test
	public void passCheck() {
		GildedRose inn = new GildedRose();
        inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        for(int i = 0; i < 11; i++) {
			inn.oneDay();
        }
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Pass aged incorrectly", 38, quality);
	}
	
	@Test
	public void passExpirationCheck() {
		GildedRose inn = new GildedRose();
        inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        for(int i = 0; i <= 15; i++) {
			inn.oneDay();
        }
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Pass should be expired", 0, quality);
	}
	
	@Test
	public void handCheck() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		inn.oneDay();
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		assertEquals("Legendary Items should not age", 80, quality);
	}
	
	//-------------------------------
	//TASK 3 tests below this line
	//-------------------------------
	
	@Test
	public void passBreakCheck() {
		GildedRose inn = new GildedRose();
        inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 22));
        for(int i = 0; i < 15; i++) {
			inn.oneDay();
        }
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Pass aged incorrectly", 50, quality);
	}
	
	@Test
	public void handBreakCheck() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", -1, 80));
		inn.oneDay();
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		assertEquals("Legendary Items should not age", 80, quality);
	}

	//------------------------------
	//TASK 4 tests below this line
	//------------------------------
	
	@Test
	public void brieCheckSpecific() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 2, 0));
		for(int i = 0; i <20; i++) {
			inn.oneDay();
		}
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Cheese aged wrong", 38, quality);
	}

}