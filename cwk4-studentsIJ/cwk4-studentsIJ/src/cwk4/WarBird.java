package cwk4;

/**
 * @author Andrei Cirlig, Janine Obiri, Lewis Turnbull, Zohaib Rehman
 */
public class WarBird extends Force {
    final private boolean canCloak; //True if the WarBird can cloak, false if it cannot

    public WarBird(String fleetReference, String name, boolean canCloak, int battleStrength) {
        super(fleetReference, name, canCloak ? 400 : 300, battleStrength,
                false, canCloak, true);

        this.canCloak = canCloak;
    }

    /**
     * Returns generic force details with WarBird specific details appended to it
     * @return a String of generic force details with WarBird specific details appended to it
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nCan cloak: " + canCloak;
    }

    /**
     * Returns the value of whether the WarBird can cloak
     * @return true Boolean value if WarBird can cloak, false Boolean value if WarBird cannot cloak
     */
    public boolean getCanCloak() {
        return canCloak;
    }
}