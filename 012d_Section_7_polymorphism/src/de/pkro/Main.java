package de.pkro;

class Movie {
    private String name;

    public Movie(String name) {
        this.name = name;
    }

    public String plot() {
        return "No plot here";
    }

    public String getName() {
        return name;
    }
}

class Jaws extends Movie {
    public Jaws() {
        super("Jaws");
    }

    @Override
    public String plot() {
        return "About sharks";
    }
}

class IndependenceDay extends Movie {
    public IndependenceDay() {
        super("ID");
    }

    @Override
    public String plot() {
        return "About evil Aliens";
    }
}

class StarWars extends Movie {
    public StarWars() {
        super("SW");
    }

    @Override
    public String plot() {
        return "About good Aliens";
    }
}

class Forgettable extends Movie {
    public Forgettable() {
        super("Forgetable");
    }

    // NO plot method!
}

public class Main {
    public static void main(String[] args) {
        /*Jaws j = new Jaws();
        System.out.println(j.plot());

        Forgettable f = new Forgettable();
        System.out.println(f.plot());*/
        for (int i = 0; i < 5; i++) {
            System.out.println(randomMovie().plot());
        }

    }

    public static Movie randomMovie() {
        int rand = (int) Math.ceil(Math.random() * 4);

        switch (rand) {
            case 1:
                return new Jaws();

            case 2:
                return new IndependenceDay();

            case 3:
                return new StarWars();

            case 4:
                return new Forgettable();
        }
        return null;

    }
}
