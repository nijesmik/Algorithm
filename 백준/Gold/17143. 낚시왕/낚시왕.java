import java.util.Scanner;

public class Main {
    static int R, C, M;
    static Shark[][] sea;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        M = sc.nextInt();
        sea = new Shark[R+1][C+1];
        for (int i = 0; i < M; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            int speed = sc.nextInt();
            int dir = sc.nextInt();
            int size = sc.nextInt();
            add(new Shark(r, c, speed, dir, size));
        }
        int ans = 0;
        for (int i = 1; i <= C; i++) {
            ans += fishing(i);
            move();
        }
        System.out.println(ans);
    }

    static void move() {
        Shark[][] old = sea;
        sea = new Shark[R+1][C+1];
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (old[i][j] != null) {
                    old[i][j].move();
                    add(old[i][j]);
                }
            }
        }
    }

    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, 1, -1};

    static class Shark {
        int r, c, speed, dir, size;
        Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            speed = s;
            dir = d;
            size = z;
        }

        void move() {
            int time = speed;
            int nr = r, nc = c;
            while (time-- > 0) {
                if (nr == 1 && dir == 1) dir = 2;
                else if (nr == R && dir == 2) dir = 1;
                else if (nc == C && dir == 3) dir = 4;
                else if (nc == 1 && dir == 4) dir = 3;
                nr = nr + dr[dir];
                nc = nc + dc[dir];
            }
            r = nr;
            c = nc;
        }
    }

    static int fishing(int c) {
        for (int i = 1; i <= R; i++) {
            if (sea[i][c] != null) {
                Shark shark = sea[i][c];
                sea[i][c] = null;
                return shark.size;
            }
        }
        return 0;
    }

    static void add(Shark shark) {
        int r = shark.r, c = shark.c;
        if (sea[r][c] == null || sea[r][c].size < shark.size) {
            sea[r][c] = shark;
        }
    }
}
