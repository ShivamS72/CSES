import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.util.PriorityQueue;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.AbstractCollection;
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
        ShortestRoutesI solver = new ShortestRoutesI();
        solver.solve(1, in, out);
        out.close();
    }
 	//------------------------------------------------------------SOLUTION-----------------------------------------------------------------
    static class ShortestRoutesI {
        OutputWriter out;
 
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            this.out = out;
            Graph g = new Graph(n);
            for (int i = 0; i < m; i++) {
                g.addEdge(in.nextInt(), in.nextInt(), in.nextInt());
            }
            g.dijsktra(1);
            g.printDistance();
        }
 
        class Graph {
            int v;
            ArrayList<Node>[] ad;
            long[] dist;
            int[] prev;
 
            Graph(int v) {
                this.v = v + 1;
                ad = new ArrayList[this.v];
                for (int i = 0; i < this.v; i++)
                    ad[i] = new ArrayList<Node>();
            }
 
            void addEdge(int u, int v, int cost) {
                Node to_v = new Node(v, cost);
                ad[u].add(to_v);
            }
 
            void dijsktra(int u) {
                boolean visited[] = new boolean[v];
                prev = new int[v];
                dist = new long[v];
                Arrays.fill(dist, Long.MAX_VALUE);
                Arrays.fill(prev, -1);
                PriorityQueue<Node> pq = new PriorityQueue<Node>();
 
                dist[u] = 0;
                pq.add(new Node(u, 0));
 
                while (!pq.isEmpty()) {
 
                    Node min = pq.poll();
                    u = min.node;
                    long cost = min.cost;
                    visited[u] = true;
 
                    if (dist[u] < cost)
                        continue;
 
                    for (Node curr : ad[u]) {
                        if (visited[curr.node])
                            continue;
                        long d = dist[u] + curr.cost;
                        if (d < dist[curr.node]) {
                            dist[curr.node] = d;
                            prev[curr.node] = u;
                            pq.add(new Node(curr.node, d));
                        }
                    }
 
                }
            }
 
            void printDistance() {
                for (int i = 1; i < dist.length; i++)
                    out.print(dist[i] + " ");
                out.println();
            }
 
            class Node implements Comparable<Node> {
                public int node;
                public long cost;
 
                public Node(int node, long cost) {
                    this.node = node;
                    this.cost = cost;
                }
 
                public int compareTo(Node b) {
                    if (this.cost < b.cost)
                        return -1;
                    else if (this.cost > b.cost)
                        return 1;
                    return 0;
                }
 
            }
 
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
}
