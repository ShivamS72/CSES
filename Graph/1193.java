import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Collection;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;
import java.util.Queue;
import java.util.LinkedList;
import java.io.InputStream;
 
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
        Labyrinth solver = new Labyrinth();
        solver.solve(1, in, out);
        out.close();
    }
 	//-------------------------------------------------------SOLUTION-------------------------------------------------------
    static class Labyrinth {
        char[][] parent;
        boolean found = false;
 
        public boolean check(char x) {
            return x == '.' || x == 'A' || x == 'B';
        }
 
        public void bfs(char c[][], int sx, int sy, int ex, int ey) {
            int n = c.length;
            int m = c[0].length;
            Queue<Integer> x = new LinkedList<Integer>();
            Queue<Integer> y = new LinkedList<Integer>();
            boolean[][] visited = new boolean[n][m];
            parent = new char[n][m];
            x.add(sx);
            y.add(sy);
            visited[sx][sy] = true;
 
            while (!x.isEmpty()) {
                int i = x.poll();
                int j = y.poll();
                if (c[i][j] == 'B') {
                    found = true;
                    break;
                }
                if (i - 1 >= 0 && check(c[i - 1][j]) && !visited[i - 1][j]) {
                    parent[i - 1][j] = 'D';
                    visited[i - 1][j] = true;
                    x.add(i - 1);
                    y.add(j);
                }
                if (i + 1 < n && check(c[i + 1][j]) && !visited[i + 1][j]) {
                    parent[i + 1][j] = 'U';
                    visited[i + 1][j] = true;
                    x.add(i + 1);
                    y.add(j);
                }
                if (j - 1 >= 0 && check(c[i][j - 1]) && !visited[i][j - 1]) {
                    parent[i][j - 1] = 'R';
                    visited[i][j - 1] = true;
                    x.add(i);
                    y.add(j - 1);
                }
                if (j + 1 < m && check(c[i][j + 1]) && !visited[i][j + 1]) {
                    parent[i][j + 1] = 'L';
                    visited[i][j + 1] = true;
                    x.add(i);
                    y.add(j + 1);
                }
            }
        }
 
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            char c[][] = new char[n][m];
            int sx = -1, sy = -1, ex = -1, ey = -1;
            for (int i = 0; i < n; i++) {
                char s[] = in.nextLine().toCharArray();
                for (int j = 0; j < m; j++) {
                    c[i][j] = s[j];
                    if (c[i][j] == 'A') {
                        sx = i;
                        sy = j;
                    } else if (c[i][j] == 'B') {
                        ex = i;
                        ey = j;
                    }
                }
            }
            bfs(c, sx, sy, ex, ey);
            int i = ex, j = ey;
            parent[sx][sy] = 'X';
            if (found) {
                System.out.println("YES");
                Stack<Character> s = new Stack<Character>();
                char x = 'Z';
                while (x != 'X') {
                    x = parent[i][j];
                    if (x == 'D')
                        i++;
                    else if (x == 'U')
                        i--;
                    else if (x == 'R')
                        j++;
                    else
                        j--;
                    s.push(x);
                }
                s.pop();
                System.out.println(s.size());
                while (!s.isEmpty()) {
                    char z = s.pop();
                    if (z == 'U')
                        out.print("D");
                    else if (z == 'D')
                        out.print("U");
                    else if (z == 'L')
                        out.print("R");
                    else if (z == 'R')
                        out.print("L");
                }
            } else
                out.println("NO");
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
