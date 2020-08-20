public class Cat implements Info {
    String name;
    int maxRunDistance;
    int maxJump;

    public Cat(String name, int maxRunDistance, int maxJump) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJump = maxJump;
    }

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public void runDistance(int maxRunDistance, int trackDistance) {

    }

    @Override
    public void jumpHeight() {

    }

    @Override
    public void run(String track) {
        System.out.println(name + " " + "failed to run the distance!" + " " + track);
    }

    @Override
    public void jump (String wall) {
        System.out.println(name + " " + "passed the height!" + " " + wall);
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", maxRunDistance=" + maxRunDistance +
                ", maxJump=" + maxJump +
                '}';
    }
}
