package de.pkro;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class HeavenlyBody {
    private final Key key;
    private final double orbitalPeriod;
    private final Set<HeavenlyBody> satellites;

    public HeavenlyBody(String name, double orbitalPeriod, BodyTypes bodyType) {
        this.key = new Key(name, bodyType);
        this.orbitalPeriod = orbitalPeriod;
        satellites = new HashSet<HeavenlyBody>();
    }

    public Key getKey() {
        return key;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public Set<HeavenlyBody> getSatellites() {
        return satellites;
    }

    public static Key makeKey(String name, BodyTypes bodyType) {
        return new Key(name, bodyType);
    }

    public boolean addSatellite(HeavenlyBody satellite) {
        return satellites.add(satellite);
    }

    public String toString() {
        return key.getName() + ": " + key.getBodyType() + ", " + orbitalPeriod;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return key.equals(((HeavenlyBody) o).getKey());
    }

    @Override
    public final int hashCode() {
        return key.hashCode();
    }

    protected enum BodyTypes {
        MOON, PLANET, DWARF_PLANET
    }

    protected static class Key {
        private String name;
        private BodyTypes bodyType;

        private Key(String name, BodyTypes bodyType) {
            this.name = name;
            this.bodyType = bodyType;
        }

        public String getName() {
            return name;
        }

        public BodyTypes getBodyType() {
            return bodyType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return Objects.equals(name, key.name) && bodyType == key.bodyType;
        }

        @Override
        public int hashCode() {
            return name.hashCode() + bodyType.hashCode() + 57;
        }

        @Override
        public String toString() {
            return name + ": " + bodyType;
        }
    }
}
