public class Human implements Info {
    String name;
    int maxRunDistance;
    int maxJump;

    public Human(String name, int maxRunDistance, int maxJump) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJump = maxJump;
    }

    public Human (String name) {
        this.name = name;
    }
    public void showCompetitors () {
       /* for (int i = 0; i < competitors1.length; i++) {
            System.out.println(competitors1[i].toString());
       }

        */
        System.out.println("Cool " + name + " " + maxRunDistance + " " + maxJump);
    }

    @Override
    public void runDistance (int maxRunDistance, int trackDistance) {
        if (maxRunDistance > trackDistance) {
            System.out.println(name + " " + "passed the distance!");
        }
        else System.out.println(name + " " + "failed!");
    }

    @Override
    public void jumpHeight() {

    }

    @Override
    public void run(String track) {
        System.out.println(name + " " + "passed the distance!" + " " + track);
    }

    @Override
    public void jump (String wall) {
        System.out.println(name + " " + "failed to make the jump up the!" + " " + wall);
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", maxRunDistance=" + maxRunDistance +
                ", maxJump=" + maxJump +
                '}';
    }

    public int getMaxRunDistance() {
        return maxRunDistance;
    }

    public String getName() {
        return name;
    }
}
