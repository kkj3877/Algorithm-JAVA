import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// You Can Check gradle projcect on
// https://github.com/kkj3877/Algorithm-JAVA

// BJ-5938 | S3 | Daisy Chains in the Field
// https://www.acmicpc.net/problem/5938

public class Main
{
    // Reader and Writer for solve problem
    private static BufferedReader   br;
    private static BufferedWriter   bw;

    // Readers for KAT
    private static BufferedReader   brAns;
    private static BufferedReader   brOut;
    
    private static InputStream      in;
    private static OutputStream     out;

    public static void main(String[] args)
        throws IOException
    {
        boolean forSubmit = true;

        init(forSubmit);
        solve();
        doFinal(forSubmit);
    }
    
    private static void init(boolean forSubmit)
        throws IOException
    {
        in  = System.in;

        out = forSubmit ? System.out : new FileOutputStream("output.txt");
        
        br = new BufferedReader(new InputStreamReader(in));
        bw = new BufferedWriter(new OutputStreamWriter(out));
    }

    public static class Cow
    {
        private Queue<Integer> conn;
        
        public Cow()
        {
            conn = new LinkedList<Integer>();
        }
        
        public void addConnection(int num)
        {
            conn.add(num);
        }

        public Queue<Integer> getConnected()
        {
            return conn;
        }
    }

    private static void solve()
        throws IOException
    {
        int N, M;
        int i;

        // Read N, M
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Cow[] cows = new Cow[N + 1];
        for (i = 1; i < N + 1; ++i)
            cows[i] = new Cow();
        boolean[] visited = new boolean[N + 1];
        
        // Read connection state
        for (i = 0; i < M; ++i)
        {
            st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            cows[c1].addConnection(c2);
            cows[c2].addConnection(c1);
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        int now;

        visited[1] = true;
        queue.add(1);

        while (!(queue.isEmpty()))
        {
            now = queue.poll();
            Queue<Integer> conn = cows[now].getConnected();
            for (int connNum : conn)
            {
                if (!visited[connNum])
                {
                    visited[connNum] = true;
                    queue.add(connNum);
                }
            }
        }

        boolean fullyConnected = true;
        for (i = 1; i < N + 1; ++i)
            if (!(visited[i]))
            {
                bw.write(i + "\n");
                fullyConnected = false;
            }
        
        if (fullyConnected)
            bw.write("0\n");
        
        bw.flush();
    }

    private static void doFinal(boolean forSubmit)
        throws IOException
    {
        if (bw != null)
            bw.close();

        if (br != null)
            br.close();
        
        if (!forSubmit)
            checkAnswer();
    }

    private static void checkAnswer()
        throws RuntimeException, IOException
    {
        Util.printInfo("Checking Answer...");
        try
        {
            brAns = new BufferedReader(new InputStreamReader(new FileInputStream("answer.txt")));
            brOut = new BufferedReader(new InputStreamReader(new FileInputStream("out.txt")));
    
            String outLine, answerLine;
    
            while ((outLine = brAns.readLine()) != null && (answerLine = brOut.readLine()) != null) {
                if (!outLine.equals(answerLine)) {
                    throw new RuntimeException("KAT FAILED");
                }
            }
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            if (brAns != null)
                brAns.close();
            if (brOut != null)
                brOut.close();
        }

        Util.printSuccess("KAT PASSED");
    }

    private static class Util
    {
        private static String bold = "\u001B[1m";
        private static String green = bold + "\u001B[32m";
        private static String blue = bold + "\u001B[34m";
        private static String reset = "\u001B[0m"; // Reset to default color

        public static void printInfo(String message)
        {
            System.out.println(blue + "INFO| " + reset + bold + message);
        }

        public static void printSuccess(String message)
        {
            System.out.println(green + "INFO| " + message + reset);
        }
    }
}
