import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Deque;
import java.io.Writer;
import java.io.OutputStreamWriter;
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
        PalindromeReorder solver = new PalindromeReorder();
        solver.solve(1, in, out);
        out.close();
    }
//-------------------------------------------------------SOLUTION------------------------------------------------------- 
    static class PalindromeReorder {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            String s = in.nextString();
            if (s.length() == 1) {
                out.println(s);
                return;
            }
            int freq[] = new int[26];
            for (int i = 0; i < s.length(); i++) {
                freq[s.charAt(i) - 65]++;
            }
            int counto = 0;
            for (int i = 0; i < 26; i++) {
                if ((freq[i] & 1) == 1)
                    counto++;
            }
            if (counto >= 2) {
                out.println("NO SOLUTION");
                return;
            }
            if (counto == 1 && (s.length() & 1) == 0) {
                out.println("NO SOLUTION");
                return;
            }
            boolean flag = false;
            Deque<Character> q = new LinkedList<Character>();
            char extra = 'a';
            for (int i = 0; i < 26; ) {
                if ((freq[i] & 1) == 0 && freq[i] > 0) {
                    q.addFirst((char) (i + 65));
                    q.addLast((char) (i + 65));
                    freq[i] -= 2;
                } else if (freq[i] > 0) {
                    flag = true;
                    extra = (char) (i + 65);
                    freq[i]--;
                }
                if (freq[i] == 0)
                    i++;
            }
            int count = 0;
            int size = q.size();
            for (char c : q) {
                if (count == size / 2 && flag)
                    out.print(extra);
                out.print(c);
                count++;
            }
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
 
        public String nextString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
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
 
