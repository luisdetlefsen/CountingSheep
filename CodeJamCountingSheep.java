package CodeJam.Y2016.Qualification.CountingSheep;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.*;

/**
 *
 * @author 30102913
 */
public class CodeJamCountingSheep {

    PrintWriter writer;
    static final Logger logger = Logger.getAnonymousLogger();
    private boolean numbersSeen[]
            = {false //0
                , false //1
                , false //2
                , false //3
                , false //4
                , false //5
                , false //6
                , false //7
                , false //8
                , false //9
            };

    private boolean allNumbersSeen() {
        boolean result = true;
        for (int i = 0; i < numbersSeen.length; i++) {
            result &= numbersSeen[i];
        }
        return result;
    }

    private void resetNumbersSeen() {
        for (int i = 0; i < numbersSeen.length; i++) {
            numbersSeen[i] = false;
        }
    }

    private void markNumbersAsSeen(long number) {
        String s = String.valueOf(number);
        for (int i = 0; i < s.length(); i++) {
            numbersSeen[Integer.valueOf(s.charAt(i) + "")] = true;
        }
    }

    private long count(long seed) throws Exception {
        // logger.info("N: " + seed);
        resetNumbersSeen();
        int iterations = 0;
        while (!allNumbersSeen() && iterations < Integer.MAX_VALUE) {
            iterations++;
            markNumbersAsSeen(iterations * seed);
        }
        if (iterations == Integer.MAX_VALUE) {
            throw new Exception("Max value reached");
        }
        return iterations * seed;
    }

    private void processFile(String filePath) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        try {
            int i = 0;
            String T = br.readLine();
            String N = "";
            while (i < Integer.parseInt(T)) {
                N = br.readLine();
                evaluate(Integer.parseInt(N), i + 1);
                i++;
            }
        } finally {
            br.close();
        }
    }

    private void evaluate(int n, int caseNumber) {
        try {
            if(n==0) throw new Exception("Known number that will give insomnia");
            writer.println("Case #" + caseNumber + ": " + count(n));
        } catch (Exception e) {            
            writer.println("Case #" + caseNumber + ": INSOMNIA");
        }
    }

    private void test() {
        logger.setLevel(Level.INFO);

        for (int i = 0; i < 1_000_000; i++) {
            try {
                System.out.println("Case #" + i + ": " + count(i));
            } catch (Exception e) {
                System.err.println("Case #" + i + ": INSOMNIA");
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            CodeJamCountingSheep p1 = new CodeJamCountingSheep();
            
            p1.writer = new PrintWriter("C:\\CodeJam\\A_CountingSheepLarge_LargeOutputT.txt", "UTF-8");
            p1.processFile("C:\\CodeJam\\A-large-practice.in");
            p1.writer.close();
            //p1.test();
        } catch (IOException ex) {
            Logger.getLogger(CodeJamCountingSheep.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
