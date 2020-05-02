package gad.maze;

public class Walker {
    private Result result;
    private boolean[][] maze;
    private int goalX, goalY;
    private boolean[][] visited;

    public Walker(boolean[][] maze, int goalX, int goalY, Result result) {
        this.maze = maze;
        this.goalX = goalX;
        this.goalY = goalY;
        this.result = result;
    }

    public boolean walk() {
        // TODO
    	
        //throw new RuntimeException("Noch nicht implementiert!");
    	return false;
    }

    public static void main(String[] args) {
        boolean[][] maze = Maze.generateStandardMaze(10, 10);
        Walker walker = new Walker(maze, 9, 8, new StudentResult());
        System.out.println(walker.walk());
        Maze.draw(0, 0, walker.maze, walker.visited);
    }
}
