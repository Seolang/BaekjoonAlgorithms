import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static char[][] field;
    static Tank tank;
    static int H, W;

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            field = new char[H][W];

            for(int i=0; i<H; i++) {
                field[i] = br.readLine().toCharArray();
                for(int j=0; j<W; j++) {
                    if (field[i][j] == '<' ||
                            field[i][j] == '>' ||
                            field[i][j] == '^' ||
                            field[i][j] == 'v'
                    ) {
                        tank = new Tank(i, j, field[i][j]);
                        field[i][j] = '.';
                    }
                }
            }

            br.readLine();
            for(char cmd : br.readLine().toCharArray()) {
                if (cmd == 'S') {
                    tank.shoot();
                } else {
                    tank.move(cmd);
                }
            }
            field[tank.r][tank.c] = tank.DirToArrow();

            sb.append("#").append(t).append(" ");
            for(int i=0; i<H; i++) {
                sb.append(new String(field[i])).append("\n");
            }
        }
        System.out.println(sb);
    }

    static void printField() {
        for(int i=0; i<H; i++) {
            System.out.println(new String(field[i]));
        }
    }

    static class Tank {
        int r;
        int c;
        int dir;

        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};

        public Tank(int r, int c, char dir) {
            this.r = r;
            this.c = c;
            this.dir = ArrowToDir(dir);

        }

        public void move(char dir) {
            int cmdDir = ArrowToDir(dir);
            this.dir = cmdDir;

            field[r][c] = DirToArrow();


            int nextRow = this.r + dr[this.dir];
            int nextCol = this.c + dc[this.dir];

            if (isValid(nextRow, nextCol) && field[nextRow][nextCol] == '.') {
                field[r][c] = '.';
                this.r = nextRow;
                this.c = nextCol;
                field[r][c] = DirToArrow();
            }
        }

        public void shoot() {
            if (this.dir == 0) {
                for(int i=this.c; i<W; i++) {
                    if (field[this.r][i] == '#') return;
                    else if (field[this.r][i] == '*') {
                        field[this.r][i] = '.';
                        break;
                    }
                }
            } else if (this.dir == 1) {
                for(int i=this.r; i<H; i++) {
                    if (field[i][this.c] == '#') return;
                    else if (field[i][this.c] == '*') {
                        field[i][this.c] = '.';
                        break;
                    }
                }
            } else if (this.dir == 2) {
                for(int i=this.c; i >= 0; i--) {
                    if (field[this.r][i] == '#') return;
                    else if (field[this.r][i] == '*') {
                        field[this.r][i] = '.';
                        break;
                    }
                }
            } else {
                for(int i=this.r; i>=0; i--) {
                    if (field[i][this.c] == '#') return;
                    else if (field[i][this.c] == '*') {
                        field[i][this.c] = '.';
                        break;
                    }
                }
            }
        }

        public int ArrowToDir(char c) {
            if (c == '>' || c == 'R') return 0;
            else if (c == 'v' || c == 'D') return 1;
            else if (c == '<' || c == 'L' ) return 2;
            else if (c == '^' || c == 'U') return 3;
            else return -1;
        }

        public char DirToArrow() {
            if (dir == 0) {
                return '>';
            } else if (dir == 1) {
                return 'v';
            } else if (dir == 2) {
                return '<';
            } else {
                return '^';
            }
        }

        public boolean isValid(int r, int c) {
            return 0 <= r && r < H && 0 <= c && c < W;
        }
    }
}