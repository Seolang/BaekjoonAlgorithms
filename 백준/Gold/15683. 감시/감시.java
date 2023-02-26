import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;


public class Main {

    static int N, M, maxWatchingSpots, blindSpot;
    static int [][] map;
    static List<CCTV> CCTVList = new ArrayList<>();
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};

    static class CCTV {
        int cctvCode;
        int pi, pj;
        int dir;
        int type;

        public CCTV (int code, int i, int j, int type) {
            this.cctvCode = code;
            this.pi = i;
            this.pj = j;
            this.type = type;
        }

        public int watch(int direction) {
            int chki = this.pi + di[direction];
            int chkj = this.pj + dj[direction];
            int watchingSpots = 0;

            while(isValid(chki, chkj)) {
                if (map[chki][chkj] == 6) {
                    break;
                }
                else if (map[chki][chkj] == 0) {
                    map[chki][chkj] = this.cctvCode;
                    watchingSpots++;
                }
                chki += di[direction];
                chkj += dj[direction];
            }

            return watchingSpots;
        }

        public void unWatch(int direction) {
            int chki = this.pi + di[direction];
            int chkj = this.pj + dj[direction];

            while(isValid(chki, chkj)) {
                if (map[chki][chkj] == 6) {
                    break;
                }
                else if (map[chki][chkj] == this.cctvCode) {
                    map[chki][chkj] = 0;
                }
                chki += di[direction];
                chkj += dj[direction];
            }
        }

        public int startOverWatch() {
            int watchingSpots = 0;

            switch (this.type) {
                case 1:
                    watchingSpots += watch(this.dir);
                    break;
                case 2:
                    watchingSpots += watch(this.dir);
                    watchingSpots += watch(circleAdder(this.dir+2));
                    break;
                case 3:
                    watchingSpots += watch(this.dir);
                    watchingSpots += watch(circleAdder(this.dir+1));
                    break;
                case 4:
                    watchingSpots += watch(this.dir);
                    watchingSpots += watch(circleAdder(this.dir+1));
                    watchingSpots += watch(circleAdder(this.dir+2));
                    break;
                default:
                    watchingSpots += watch(0);
                    watchingSpots += watch(1);
                    watchingSpots += watch(2);
                    watchingSpots += watch(3);
                    break;
            }

            return watchingSpots;
        }

        public void endOverWatch() {
            switch (this.type) {
                case 1:
                    unWatch(this.dir);
                    break;
                case 2:
                    unWatch(this.dir);
                    unWatch(circleAdder(this.dir+2));
                    break;
                case 3:
                    unWatch(this.dir);
                    unWatch(circleAdder(this.dir+1));
                    break;
                case 4:
                    unWatch(this.dir);
                    unWatch(circleAdder(this.dir+1));
                    unWatch(circleAdder(this.dir+2));
                    break;
                default:
                    unWatch(0);
                    unWatch(1);
                    unWatch(2);
                    unWatch(3);
                    break;
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        int code = -1;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    blindSpot++;
                } else if (map[i][j] != 6){
                    CCTVList.add(new CCTV(code--, i, j, map[i][j]));
                }
            }
        }
        setCCTVDirs(0, 0);
        System.out.println(blindSpot - maxWatchingSpots);
    }

    static void setCCTVDirs(int idx, int localWatchingSpots) {
        if (idx == CCTVList.size()) {

            maxWatchingSpots = Math.max(maxWatchingSpots, localWatchingSpots);
            return;
        }

        CCTVList.get(idx).dir = 0;
        setCCTVDirs(idx+1, localWatchingSpots + CCTVList.get(idx).startOverWatch());
        CCTVList.get(idx).endOverWatch();

        CCTVList.get(idx).dir = 1;
        setCCTVDirs(idx+1, localWatchingSpots + CCTVList.get(idx).startOverWatch());
        CCTVList.get(idx).endOverWatch();

        CCTVList.get(idx).dir = 2;
        setCCTVDirs(idx+1, localWatchingSpots + CCTVList.get(idx).startOverWatch());
        CCTVList.get(idx).endOverWatch();

        CCTVList.get(idx).dir = 3;
        setCCTVDirs(idx+1, localWatchingSpots + CCTVList.get(idx).startOverWatch());
        CCTVList.get(idx).endOverWatch();
    }

    static boolean isValid(int i, int j) {
        return 0<=i && i<N && 0<=j && j<M;
    }

    static int circleAdder(int n) {
        if (n < 0) {
            return 4+n;
        } else {
            return n%4;
        }
    }

    static void printMap() {
        for(int[] line : map) {
            for(int n : line) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }

}