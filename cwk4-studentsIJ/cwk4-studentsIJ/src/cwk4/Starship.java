package cwk4;

/**
 * @author Andrei Cirlig, Janine Obiri, Lewis Turnbull, Zohaib Rehman
 */
public class Starship extends Force {
    final private int laserCanons;
    final private int photonTorpedoes;

    public Starship(String fleetReference, String name, int laserCanons, int photonTorpedoes) {
        super(fleetReference, name, 30 * laserCanons, 5 * photonTorpedoes + 10 * laserCanons,
                true, false, true);

        this.laserCanons = laserCanons;
        this.photonTorpedoes = photonTorpedoes;
    }

    /**
     * Returns generic force details with Starship specific details appended to it
     * @return a String of generic force details with Starship specific details appended to it
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nLaser canons: " + laserCanons +
                "\nPhoton torpedoes: " + photonTorpedoes;
    }

    /**
     * Returns the number of laser canons on the Starship
     * @return integer number of laser canons on the Starship
     */
    public int getLaserCanons() {
        return laserCanons;
    }

    /**
     * Returns the number of photon torpedoes on the Starship
     * @return an integer number of photon torpedoes on the Starship
     */
    public int getPhotonTorpedoes() {
        return photonTorpedoes;
    }
}