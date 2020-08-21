public class RunningTrack implements Obstacle {
    int trackDistance;
    String trackName;

    @Override
    public void checkObstacle(String trackName) {
        this.trackName = trackName;

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
