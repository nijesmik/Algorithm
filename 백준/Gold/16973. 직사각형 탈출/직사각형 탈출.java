import java.util.*;

class Main {
    static int boardR, boardC, board[][], height, width, startR, startC, endR, endC;
    static int[] dr = { 1, -1, 0, 0 }, dc = { 0, 0, 1, -1 };
    static boolean[][] visited;
    static List<Point> walls;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boardR = sc.nextInt();
        boardC = sc.nextInt();
        board = new int[boardR][boardC];
        walls = new ArrayList<>();
        for (int i = 0; i < boardR; i++) {
            for (int j = 0; j < boardC; j++) {
                int item = sc.nextInt();
                board[i][j] = item;
                if (item == 1) {
                    walls.add(new Point(i, j));
                }
            }
        }
        height = sc.nextInt();
        width = sc.nextInt();
        startR = sc.nextInt() - 1;
        startC = sc.nextInt() - 1;
        endR = sc.nextInt() - 1;
        endC = sc.nextInt() - 1;
        System.out.println(bfs());
    }

    static int bfs() {
        visited = new boolean[boardR][boardC];
        Queue<Record> q = new LinkedList<>();
        q.add(new Record(startR, startC, 0));

        while (!q.isEmpty()) {
            Record cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i], nc = cur.c + dc[i];
                if (nr == endR && nc == endC) {
                    return cur.time + 1;
                }
                if (canGo(nr, nc)) {
                    q.add(new Record(nr, nc, cur.time + 1));
                }
            }
        }
        return -1;
    }

    static boolean canGo(int r, int c) {
        if (r < 0 || c < 0 || r + height - 1 >= boardR || c + width - 1 >= boardC || visited[r][c]) {
            return false;
        }
        for (Point wall : walls) {
            if (wall.r >= r && wall.r < r + height && wall.c >= c && wall.c < c + width) {
                return false;
            }
        }
        return true;
    }

    static class Record extends Point {
        int time;

        Record(int r, int c, int time) {
            super(r, c);
            visited[r][c] = true;
            this.time = time;
        }
    }

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}