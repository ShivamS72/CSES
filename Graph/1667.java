import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Collection;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Stack;
import java.util.ArrayList;
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
        MessageRoute solver = new MessageRoute();
        solver.solve(1, in, out);
        out.close();
    }
 	//------------------------------------------------------------SOLUTION-----------------------------------------------------------------
    static class MessageRoute {
        int v;
        ArrayList<Integer>[] ad;
 
        void addEdge(int u, int v) {
            ad[u].add(v);
            ad[v].add(u);
        }
 
        void bfs() {
            boolean[] visited = new boolean[v];
            int[] parent = new int[v];
            visited[1] = true;
            Queue<Integer> q = new LinkedList<Integer>();
            int u = 1;
            q.add(u);
            boolean found = false;
            while (!q.isEmpty()) {
                u = q.poll();
                if (u == v - 1) {
                    found = true;
                    tracePath(parent);
                }
                for (int child : ad[u]) {
                    if (!visited[child]) {
                        parent[child] = u;
                        visited[child] = true;
                        q.add(child);
                    }
                }
            }
            if (!found) {
                System.out.println("IMPOSSIBLE");
            }
        }
 
        void tracePath(int[] parent) {
            Stack<Integer> s = new Stack<Integer>();
            int i = v - 1;
            while (i != 1) {
                s.push(parent[i]);
                i = parent[i];
            }
            System.out.println(s.size() + 1);
            while (!s.isEmpty()) {
                System.out.print(s.pop() + " ");
            }
            System.out.println((v - 1));
        }
 
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            v = n + 1;
            ad = new ArrayList[v];
            for (int i = 0; i < v; i++) {
                ad[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < m; i++) {
                addEdge(in.nextInt(), in.nextInt());
            }
            bfs();
        }
 
    }
 	//-------------------------------------------------------------------------------------------------------------------------------------
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
 
        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
 
        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
 
        }
 
    }
}
