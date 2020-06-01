import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Vector;
import java.io.Writer;
import java.io.OutputStreamWriter;
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
        RoundTrip solver = new RoundTrip();
        solver.solve(1, in, out);
        out.close();
    }
 	//-------------------------------------------------------SOLUTION-------------------------------------------------------
    static class RoundTrip {
        int v;
        ArrayList<Integer>[] ad;
        boolean flag = false;
        OutputWriter out;
 
        void addEdge(int u, int v) {
            ad[u].add(v);
            ad[v].add(u);
        }
 
        void DFSUtil(int u, boolean[] visited, int[] parent) {
            visited[u] = true;
            for (int child : ad[u]) {
                if (!visited[child] && !flag) {
                    parent[child] = u;
                    DFSUtil(child, visited, parent);
                } else if (parent[u] != child && !flag) {
                    flag = true;
                    printTrace(parent, u, child);
                    return;
                }
            }
        }
 
        void printTrace(int[] parent, int start, int end) {
            int i = start;
            Stack<Integer> s = new Stack<Integer>();
            s.push(end);
            s.push(start);
            while (parent[i] != -1 && i != end) {
                s.push(parent[i]);
                i = parent[i];
            }
            out.println(s.size());
            while (!s.isEmpty()) {
                out.print(s.pop() + " ");
            }
        }
 
        void dfs() {
            boolean[] visited = new boolean[v];
            int[] parent = new int[v];
            for (int i = 0; i < v && !flag; i++) {
                if (!visited[i]) {
                    parent[i] = -1;
                    DFSUtil(i, visited, parent);
                }
            }
            if (!flag) {
                System.out.println("IMPOSSIBLE");
            }
        }
 
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            v = n + 1;
            this.out = out;
            ad = new ArrayList[v];
            for (int i = 0; i < v; i++) {
                ad[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < m; i++) {
                addEdge(in.nextInt(), in.nextInt());
            }
            dfs();
        }
 
    }
 	//----------------------------------------------------------------------------------------------------------------------
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
