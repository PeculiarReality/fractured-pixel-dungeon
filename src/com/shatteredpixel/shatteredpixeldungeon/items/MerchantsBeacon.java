package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Shopkeeper;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

/**
 * Created by debenhame on 16/03/2015.
 */
public class MerchantsBeacon extends Item {

	private static final String AC_USE = "USE";


	{
		name = "merchant's beacon";
		image = ItemSpriteSheet.BEACON; //TODO: make sprite

		stackable = true;

		defaultAction = AC_USE;

		bones = true;
	}

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		actions.add(AC_USE);
		return actions;
	}

	@Override
	public void execute(Hero hero, String action) {
		if (action.equals(AC_USE)) {
			detach( hero.belongings.backpack );
			Shopkeeper.sell();
			Sample.INSTANCE.play( Assets.SND_BEACON );
		} else
			super.execute(hero, action);
	}

	@Override
	public boolean isUpgradable() {
		return false;
	}

	@Override
	public boolean isIdentified() {
		return true;
	}

	@Override
	public int price() {
		return 10 * quantity;
	}

	@Override
	//todo add description
	public String info() {
		return "This odd piece of dwarvern technology allows you to communicate from great distances." +
				"\n\nAfter being activated, this beacon will let you sell items to Pixel Mart from anywhere in the dungeon." +
				"\n\nOnce activated, the magic within the beacon will only last for one session though, so use it wisely.";
	}

}