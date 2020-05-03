package gad.maze;

public class Walker {
    private Result result;
    private boolean[][] maze;
    private int goalX, goalY;
    private boolean[][] visited;
    private int rotationCounter, currentX, currentY;

    public Walker(boolean[][] maze, int goalX, int goalY, Result result) {
        this.maze = maze;
        this.goalX = goalX;
        this.goalY = goalY;
        this.result = result;
        this.visited = new boolean[this.maze.length][this.maze[0].length];
        this.rotationCounter = 0;
        this.currentX = 1;
        this.currentY = 0;
    }

    public boolean walk() {

        this.result.addLocation(this.currentX, this.currentY);
        this.visited[this.currentX][this.currentY] = true;

        if (this.checkWallRight()) { // ja: soll gehen oder weiter checken. nein: soll rechts drehen
            if (this.checkWallInFront()) { // ja: soll rechts drehen. nein soll gehen
                if (this.checkWallLeft()) { // ja: soll umdrehen. nein: soll nach links drehen
                    this.turnAround(); // wand rechts, vorne und links
                } else {
                    this.turnLeft(); // wand rechts und vorne
                }
            }
        } else {
            this.turnRight();
        }
        this.step(); // no wall in front
        if (currentX == this.maze.length - 1 && currentY == this.maze[0].length - 2) {
            return true;
        }

        while (!(currentX == 1 && currentY == 0)) {
            // for (int counter = 0; counter < 255; counter ++){
            if (this.checkWallRight()) { // ja: soll gehen oder weiter checken. nein: soll rechts drehen
                if (this.checkWallInFront()) { // ja: soll rechts drehen. nein soll gehen
                    if (this.checkWallLeft()) { // ja: soll umdrehen. nein: soll nach links drehen
                        this.turnAround(); // wand rechts, vorne und links
                    } else {
                        this.turnLeft(); // wand rechts und vorne
                    }
                }
            } else {
                this.turnRight();
            }
            this.step(); // no wall in front
            if (currentX == this.maze.length - 1 && currentY == this.maze[0].length - 2) {
                return true;
            }
            ;
        }

        return false;
    }

    public boolean checkWallRight() {
        // direction = 0 => South
        if (this.rotationCounter == 0) {
            if (this.maze[this.currentX - 1][this.currentY]) {
                System.out.println("wall on the right!");
                return true;
            }
        }
        // direction = 1 => East
        else if (this.rotationCounter == 1) {
            if (this.maze[this.currentX][this.currentY + 1]) {
                System.out.println("wall on the right!");
                return true;
            }
        }
        // direction = 2 => North
        else if (this.rotationCounter == 2) {
            if (this.maze[this.currentX + 1][this.currentY]) {
                System.out.println("wall on the right!");
                return true;
            }
        }
        // direction = 3 => West
        else if (this.rotationCounter == 3) {
            if (this.maze[this.currentX][this.currentY - 1]) {
                System.out.println("wall on the right!");
                return true;
            }
        }
        System.out.println("NO wall on the right!");
        return false; // No wall on the right!
    }

    public boolean checkWallInFront() {
        // direction = 0 => South
        if (this.rotationCounter == 0) {
            if (this.maze[this.currentX][this.currentY + 1]) {
                System.out.println("wall in front!");
                return true;
            }
        }
        // direction = 1 => East
        else if (this.rotationCounter == 1) {
            if (this.maze[this.currentX + 1][this.currentY]) {
                System.out.println("wall in front!");
                return true;
            }
        }
        // direction = 2 => North
        else if (this.rotationCounter == 2) {
            if (this.maze[this.currentX][this.currentY - 1]) {
                System.out.println("wall in front!");
                return true;
            }
        }
        // direction = 3 => West
        else if (this.rotationCounter == 3) {
            if (this.maze[this.currentX - 1][this.currentY]) {
                System.out.println("wall in front!");
                return true;
            }
        }
        System.out.println("NO wall in front!");
        return false; // No wall on the right!
    }

    public boolean checkWallLeft() {
        // direction = 0 => South
        if (this.rotationCounter == 0) {
            if (this.maze[this.currentX + 1][this.currentY]) {
                System.out.println("wall on the left!");
                return true;
            }
        }
        // direction = 1 => East
        else if (this.rotationCounter == 1) {
            if (this.maze[this.currentX][this.currentY - 1]) {
                System.out.println("wall on the left!");
                return true;
            }
        }
        // direction = 2 => North
        else if (this.rotationCounter == 2) {
            if (this.maze[this.currentX - 1][this.currentY]) {
                System.out.println("wall on the left!");
                return true;
            }
        }
        // direction = 3 => West
        else if (this.rotationCounter == 3) {
            if (this.maze[this.currentX][this.currentY + 1]) {
                System.out.println("wall on the left!");
                return true;
            }
        }
        System.out.println("NO wall on the left!");
        return false; // No wall on the right!
    }

    public void turnRight() {
        this.rotationCounter = this.rotationCounter - 1;
        System.out.println("turnRight. New rotCounter: " + this.rotationCounter);

    }

    public void turnLeft() {
        this.rotationCounter = this.rotationCounter + 1;
        System.out.println("turnLeft. New rotCounter: " + this.rotationCounter);
    }

    public void turnAround() {
        if (this.rotationCounter >= 2) {
            this.rotationCounter = this.rotationCounter - 2;
        } else if (this.rotationCounter < 2) {
            this.rotationCounter = this.rotationCounter + 2;
        }
        System.out.println("turnAround. New rotCounter: " + this.rotationCounter);
    }

    public void step() {

        if (this.rotationCounter == -1) {
            this.rotationCounter = 3;
        } else if (this.rotationCounter == 4) {
            this.rotationCounter = 0;
        }

        // direction = 0 => South
        if (this.rotationCounter == 0) {
            this.currentY = currentY + 1;
            System.out.println("step: " + this.rotationCounter);
            this.result.addLocation(this.currentX, this.currentY);
            this.visited[this.currentX][this.currentY] = true;
        }
        // direction = 1 => East
        else if (this.rotationCounter == 1) {
            this.currentX = currentX + 1;
            System.out.println("step: " + this.rotationCounter);
            this.result.addLocation(this.currentX, this.currentY);
            this.visited[this.currentX][this.currentY] = true;
        }
        // direction = 2 => North
        else if (this.rotationCounter == 2) {
            this.currentY = currentY - 1;
            System.out.println("step: " + this.rotationCounter);
            this.result.addLocation(this.currentX, this.currentY);
            this.visited[this.currentX][this.currentY] = true;
        }
        // direction = 3 => West
        else if (this.rotationCounter == 3) {
            this.currentX = currentX - 1;
            System.out.println("step: " + this.rotationCounter);
            this.result.addLocation(this.currentX, this.currentY);
            this.visited[this.currentX][this.currentY] = true;
        }
    }

    // ------MAIN-------//
    // ------MAIN-------//
    // ------MAIN-------//
    // ------MAIN-------//

    public static void main(String[] args) {
        boolean[][] maze = Maze.generateStandardMaze(50, 50);
        Walker walker = new Walker(maze, 9, 8, new StudentResult());
        System.out.println(walker.walk());
        Maze.draw(1, 0, walker.maze, walker.visited);
    }
}
