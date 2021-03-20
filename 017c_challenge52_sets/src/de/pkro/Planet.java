package de.pkro;

public class Planet extends HeavenlyBody {
    public Planet(String name, double orbitalPeriod) {
        super(name, orbitalPeriod, BodyTypes.PLANET);
    }

    @Override
    public boolean addSatellite(HeavenlyBody satellite) {
        Key key = satellite.getKey();
        if(key.getBodyType() != BodyTypes.MOON) {
            return false;
        }
        return super.addSatellite(satellite);
    }
}
