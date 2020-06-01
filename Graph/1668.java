import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.Collection;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.Writer;
import java.io.OutputStreamWriter;
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
        OutputWriter out = new OutputWriter(outputStream);
        BuildingTeams solver = new BuildingTeams();
        solver.solve(1, in, out);
        out.close();
    }
 	//------------------------------------------------------------SOLUTION-----------------------------------------------------------------
    static class BuildingTeams {
        int v;
        OutputWriter out;
        ArrayList<Integer>[] ad;
 
        void addEdge(int u, int v) {
            ad[u].add(v);
            ad[v].add(u);
        }
 
        void bfs() {
            boolean[] visited = new boolean[v];
            int[] colour = new int[v];
            Queue<Integer> q = new LinkedList<Integer>();
            // used for loop since graph can have isolated parts
            for (int i = 1; i < v; i++) {
                if (!visited[i]) {
                    int u = i;
                    q.add(u);
                    visited[u] = true;
                    colour[u] = 0;
                    while (!q.isEmpty()) {
                        u = q.poll();
                        for (int child : ad[u]) {
                            if (!visited[child]) {
                                visited[child] = true;
                                colour[child] = ~colour[u];
                                q.add(child);
                            } else if ((colour[u] ^ colour[child]) == 0) {
                                out.println("IMPOSSIBLE");
                                return;
                            }
                        }
                    }
                }
            }
            printTeams(colour);
        }
 
        void printTeams(int[] colour) {
            for (int i = 1; i < colour.length; i++) {
                if (colour[i] == 0)
                    out.print("1 ");
                else
                    out.print("2 ");
            }
        }
 
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            // Graph Colouring - Bipartite Graph
            int n = in.nextInt();
            int m = in.nextInt();
            this.out = out;
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
    static class OutputWriter {
        private final PrintWriter writer;
 
        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }
 
        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }
 
        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0)
                    writer.print(' ');
                writer.print(objects[i]);
            }
        }
 
        public void println(Object... objects) {
            print(objects);
            writer.println();
        }
 
        public void close() {
            writer.close();
        }
 
    }
 
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
 
