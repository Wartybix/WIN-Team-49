package cwk4;


import java.io.Serializable;

/**
 * Represents a force that can be inherited from, but never instantiated itself.
 * @author Andrei Cirlig, Janine Obiri, Lewis Turnbull, Zohaib Rehman
 */
abstract class Force implements Serializable {
    final private String fleetReference;
    final private String name;
    private ForceState state;
    final private int activationFee;
    final private int battleStrength;

    final private boolean canSkirmish;
    final private boolean canAmbush;
    final private boolean canFight;

    public Force(String fleetReference, String name, int activationFee, int battleStrength,
                 boolean canSkirmish, boolean canAmbush, boolean canFight) {
        this.state = ForceState.DOCKED;

        this.fleetReference = fleetReference;
        this.name = name;
        this.activationFee = activationFee;
        this.battleStrength = battleStrength;

        this.canSkirmish = canSkirmish;
        this.canAmbush = canAmbush;
        this.canFight = canFight;
    }

    /**
     * Lists all details (aka fields) about the force in text
     * @return a list of all details (aka fields) about the force in text
     */
    public String toString() {
        return "Fleet reference: " + fleetReference +
                "\nName: " + name +
                "\nForce state: " + state +
                "\nActivation fee: " + activationFee + " bit coins" +
                "\nBattle strength: " + battleStrength +
                "\nCan skirmish: " + canSkirmish +
                "\nCan ambush: " + canAmbush +
                "\nCan fight: " + canFight;
    }

    /**
     * Returns the force's fleet reference
     * @return a String of the force's fleet reference
     */
    public String getFleetReference() {
        return fleetReference;
    }

    /**
     * Returns the force's name
     * @return a String of the force's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the state of the force (i.e. docked, active, destroyed)
     * @return a ForceState value representing the state of the force
     */
    public ForceState getState() {
        return state;
    }

    /**
     * Returns the force's activation fee
     * @return an integer value representing the force's activation fee
     */
    public int getActivationFee() {
        return activationFee;
    }

    /**
     * Returns the force's battle strength
     * @return an integer value representing the force's battle strength
     */
    public int getBattleStrength() {
        return battleStrength;
    }

    /**
     * Returns whether the force can skirmish
     * @return a Boolean value of whether the force can skirmish
     */
    public boolean getCanSkirmish() {
        return canSkirmish;
    }

    /**
     * Returns whether the force can ambush
     * @return a Boolean value of whether the force can ambush
     */
    public boolean getCanAmbush() {
        return canAmbush;
    }

    /**
     * Returns whether the force can fight
     * @return a Boolean value of whether the force can fight
     */
    public boolean getCanFight() {
        return canFight;
    }

    /**
     * Sets the force's state to active
     * (i.e. puts the force in the ASF)
     */
    public void activate()
    {
        state = ForceState.ACTIVE;
    }

    /**
     * Sets the force's state to docked
     * (i.e. recalls the force back to the UFF dock)
     */
    public void recall()
    {
        state = ForceState.DOCKED;
    }

    /**
     * Sets the force's state to destroyed
     * (i.e. destroys the force)
     */
    public void destroy() {
        state = ForceState.DESTROYED;
    }
}