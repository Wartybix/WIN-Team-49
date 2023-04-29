package cwk4;

import java.util.ArrayList;
import java.io.Serializable;
/**
 * @author Andrei Cirlig, Janine Obiri, Lewis Turnbull, Zohaib Rehman
 */
public class Battle implements Serializable{
    final private int battleNo;
    final private BattleType type;
    final private String enemyName;
    final private int enemyStrength;
    final private int losses;
    final private int gains;

    public Battle(int battleNo, BattleType type, String enemyName, int enemyStrength, int losses, int gains) {
        this.battleNo = battleNo;
        this.type = type;
        this.enemyName = enemyName;
        this.enemyStrength = enemyStrength;
        this.losses = losses;
        this.gains = gains;
    }

    /**
     * Returns a list of details (i.e. fields) about the battle
     * @return a string containing a list of details (i.e. fields) about the battle
     */
    @Override
    public String toString() {
        return "Battle number: " + battleNo +
                "\nBattle type: " + type.toString() +
                "\nEnemy name: " + enemyName +
                "\nEnemy strength: " + enemyStrength +
                "\nPlayer losses: " + losses + " bit coins" +
                "\nPlayer gains: " + gains + " bit coins";
    }

    /**
     * Returns the battle's battle number
     * @return an integer of the battle's battle number
     */
    public int getBattleNo() {
        return battleNo;
    }

    /**
     * Returns whether the battle is an ambush, skirmish, or fight
     * @return A BattleType value based on if the battle is an ambush, skirmish, or fight
     */
    public BattleType getType() {
        return type;
    }

    /**
     * Returns the enemy's name
     * @return a string containing the enemy's name
     */
    public String getEnemyName() {
        return enemyName;
    }

    /**
     * Returns the strength of the enemy
     * @return an integer of the strength of the enemy
     */
    public int getEnemyStrength() {
        return enemyStrength;
    }

    /**
     * Returns how many bit coins the admiral loses if they lose the battle
     * @return an integer representing the number of bit coins the admiral has deducted from their war chest if they lose the battle
     */
    public int getLosses() {
        return losses;
    }

    /**
     * Returns how many bit coins the admiral gains if they win the battle
     * @return an integer representing the number of bit coins the admiral has added to their war chest if they win the battle
     */
    public int getGains() {
        return gains;
    }

    public boolean battleVictory(int forceStrength) {
        return forceStrength >= getEnemyStrength();
    }
}