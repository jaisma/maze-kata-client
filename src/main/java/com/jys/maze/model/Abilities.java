package com.jys.maze.model;

/**
 * Model class to represent hero ability. There are currently total 5 available
 * abilities. Depending on the hero persona, each ability is set to true or
 * false.
 * 
 * Each ability is represented as boolean values.
 * 
 * @author Jai Sun
 *
 */
public class Abilities {
	boolean water, wood, fire, grass, quickFeet;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (water) {
			sb.append("water ");
		}
		if (wood) {
			sb.append("wood ");
		}
		if (fire) {
			sb.append("fire ");
		}
		if (grass) {
			sb.append("grass ");
		}
		if (quickFeet) {
			sb.append("quickFeet ");
		}

		return sb.toString().trim();

	}

	/**
	 * Checks if hero has certain ability.
	 * 
	 * Assume valid input / correct spelling. All invalid match will return false.
	 * 
	 * @param ability
	 *            string value of ability being checked.
	 * @return true if yes, false otherwise and all fails.
	 */
	public boolean hasAbility(String ability) {
		ability = ability.toLowerCase();
		if (ability.equals("water")) {
			return water;
		} else if (ability.equals("wood")) {
			return wood;
		} else if (ability.equals("fire")) {
			return fire;
		} else if (ability.equals("grass")) {
			return grass;
		} else if (ability.equals("quickFeet")) {
			return quickFeet;
		} else {
			return false;
		}
	}

	public boolean isWater() {
		return water;
	}

	public void setWater(boolean water) {
		this.water = water;
	}

	public boolean isWood() {
		return wood;
	}

	public void setWood(boolean wood) {
		this.wood = wood;
	}

	public boolean isFire() {
		return fire;
	}

	public void setFire(boolean fire) {
		this.fire = fire;
	}

	public boolean isGrass() {
		return grass;
	}

	public void setGrass(boolean grass) {
		this.grass = grass;
	}

	public boolean isQuickFeet() {
		return quickFeet;
	}

	public void setQuickFeet(boolean quickFeet) {
		this.quickFeet = quickFeet;
	}

}
