public class RunningTrack implements Obstacle {
    int trackDistance;
    String trackName;

    @Override
    public void checkObstacle(String trackName, String name) {
        this.trackName = trackName;
        System.out.println(name + " " + "passed the distance!" + " " + trackName);

    }

    public RunningTrack(int trackDistance) {
        this.trackDistance = trackDistance;
    }

    public RunningTrack(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackName() {
        return trackName;
    }
}
