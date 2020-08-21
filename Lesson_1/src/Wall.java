public class Wall implements Obstacle {
    int wallHeight;
    String wallName;

    public Wall(int wallHeight) {
        this.wallHeight = wallHeight;
    }

    public Wall(String wallName) {
        this.wallName = wallName;
    }

    @Override
    public void checkObstacle(int wallHeight) {
        this.wallHeight = wallHeight;
    }

    public String getWallName() {
        return wallName;
    }
}
