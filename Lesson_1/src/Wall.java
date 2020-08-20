public class Wall implements Obstacle {
    int wallHeight;
    String wallName;

    @Override
    public void checkObstacle() {
        
    }

    public Wall(int wallHeight) {
        this.wallHeight = wallHeight;
    }

    public Wall(String wallName) {
        this.wallName = wallName;
    }

    public String getWallName() {
        return wallName;
    }
}
