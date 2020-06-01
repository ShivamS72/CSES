import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
 
/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Shivam
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        CountingRooms solver = new CountingRooms();
        solver.solve(1, in, out);
        out.close();
    }
    //-------------------------------------------------------SOLUTION-------------------------------------------------------
 	static class Graph {
        int v;
        ArrayList<Integer>[] ad;
        int count;
 
        Graph(int v) {
            this.v = v;
            this.count = 0;
            ad = new ArrayList[this.v];
            for (int i = 0; i < this.v; i++) {
                ad[i] = new ArrayList<Integer>();
            }
        }
 
        void addEdge(int u, int v) {
            ad[u].add(v);
            ad[v].add(u);
        }
 
        void DFSUtil(int u, boolean[] visited) {
            visited[u] = true;
            for (int child : ad[u]) {
                if (!visited[child]) {
                    visited[child] = true;
                    DFSUtil(child, visited);
                }
            }
        }
 
        void dfs(int u, char c[][]) {
            boolean[] visited = new boolean[v];
            count = 0;
            int n = c.length;
            int m = c[0].length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < c[0].length; j++) {
                    int child = i * m + j;
                    if (!visited[child] && c[i][j] == '.') {
                        count++;
                        DFSUtil(child, visited);
                    }
                }
            }
        }
 
        int getRooms() {
            return this.count;
        }
 
    }
    static class CountingRooms {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            Graph g = new Graph((n * m) + 1);
            char c[][] = new char[n][m];
            int dots = 0;
            for (int i = 0; i < n; i++) {
                char s[] = in.nextLine().toCharArray();
                for (int j = 0; j < m; j++) {
                    c[i][j] = s[j];
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (c[i][j] == '.') {
                        dots++;
                        if (i - 1 >= 0 && c[i - 1][j] == '.')
                            g.addEdge(i * m + j - m, i * m + j);
                        if (j - 1 >= 0 && c[i][j - 1] == '.')
                            g.addEdge(i * m + j, i * m + j - 1);
 
                    }
                }
            }
            if (dots == n * m) {
                System.out.println("1");
                return;
            }
            g.dfs(0, c);
            out.println(g.getRooms());
        }
 
    }
 	//----------------------------------------------------------------------------------------------------------------------
    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[8192];
        private int curChar;
        private int numChars;
        private InputReader.SpaceCharFilter filter;
 
        public InputReader(InputStream stream) {
            this.stream = stream;
        }
 
        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }
 
        public int nextInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }
 
        public String nextLine() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isEndOfLine(c));
            return res.toString();
        }
 
        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
 
        private boolean isEndOfLine(int c) {
            return c == '\n' || c == '\r' || c == -1;
        }
 
        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
 
        }
 
    }
 
    
}
