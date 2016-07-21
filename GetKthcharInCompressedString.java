package mypackage;

import main.FastScanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GetKthcharInCompressedString {

    class Pair {
        public String a;
        public int b;
        public int cumm;

        @Override
        public String toString() {
            return "Pair{" +
                "a='" + a + '\'' +
                ", b=" + b +
                ", cumm=" + cumm +
                '}';
        }

        public Pair(String p, int q, int c) {
            a = p;
            b = q;
            cumm = c;
        }
    }

    public void solve(int testNumber, FastScanner in, PrintWriter out) {

        String str = "ab2cd3ef2";
        // This string will be ==>  abab cd abab cd abab cd ef   abab cd abab cd abab cd ef

        // given a index x find the charqacter at that index

        StringBuilder sb = new StringBuilder();
        List<Pair> lst = new ArrayList<Pair>();

        for(int i = 0; i < str.length(); ++i) {
            if(str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                int k = str.charAt(i) - '0';
                lst.add(new Pair(sb.toString(), k, k * sb.length()));
                sb.setLength(0);
            } else {
                sb.append(str.charAt(i));
            }
        }

        for(Pair p: lst) {
            out.println(p.a + " " + p.b);
        }

        //int x = 30;

        // We are just iterating for some starting x values and trying to find the characters
        for(int z = 0; z < 37; ++z) {
            int x = z;
            Stack<Pair> st = new Stack<Pair>();
            int len = 0;
            for (Pair p : lst) {
                len += p.a.length();
                p.cumm = len;
                len *= p.b;

                st.push(p);
                //out.println(p);
                if (len > x)
                    break;
            }

            while (st.empty() == false) {
                Pair pp = st.pop();

                x = x % pp.cumm;
                len = pp.cumm;
                //out.println(len + " " + x + " " + pp);
                if(st.empty()) {
                    out.printf("%c", pp.a.charAt(x));
                    break;
                }
                else if (len - pp.a.length() <= x) {
                    out.printf("%c", pp.a.charAt(x - len + pp.a.length()));
                    break;
                }

            }
        }


    }
}
