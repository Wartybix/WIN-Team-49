package cwk4;

/**
 * @author Andrei Cirlig, Janine Obiri, Lewis Turnbull, Zohaib Rehman
 */
public class Wing extends Force {
    final private int strikers;

    public Wing(String fleetReference, String name, int strikers) {
        super(fleetReference, name, 200, 20 * strikers,
                true, true, false);

        this.strikers = strikers;
    }

    /**
     * Returns generic force details with Wing specific details appended to it
     * @return a String of generic force details with Wing specific details appended to it
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nStrikers: " + strikers;
    }

    /**
     * Returns the number of strikers on the Wing
     * @return an integer value of the number of strikers on the Wing
     */
    public int getStrikers() {
        return strikers;
    }
}
