import java.io.File;
import java.util.Scanner;
import java.util.TreeSet;

public class Test {

    private static final double nanosecondsToSeconds = 1000000000.;

    public static Character[] toCharacterArray(String s) {
        if (s == null) {
            return null;
        }
        Character[] array = new Character[s.length()];
        for (int i = 0; i < s.length(); i++) {
            array[i] = s.charAt(i);
        }
        return array;
    }

    public static void main(String[] args) throws Exception {
        GADDAG g = new GADDAG(); // change to trie to see a trie.
        long startTime = System.nanoTime();

        if (args.length == 1){
            Scanner dictionary = new Scanner(new File(args[0]));
            while(dictionary.hasNext()) {
                g.add(dictionary.nextLine());
            }
            long duration = System.nanoTime() - startTime;
            System.out.println("Time to build GADDAG: " + (duration / nanosecondsToSeconds) + " seconds"); //Nano seconds to seconds
            repl(g);
        } else {
            g.addAll(new String[]{"Abstract", "Absolute", "Absolve", "Boats", "Bannana", "Abs"});
            repl(g);
        }
    }

    public static void repl(GADDAG g){
        System.out.println("Welcome to the word finder");
        System.out.println("Enter in 'r', followed by the rack you want to use to search.");
        System.out.println("Use 'h' followed by a character to change the hook.");
        Scanner scan = new Scanner(System.in);
        System.out.print("> ");
        char hook = ' ';
        while(scan.hasNext()){
            String nextLine = scan.next();
            if (nextLine.equals("r")){
                String rack = scan.next();
                TreeSet<WordPlay> wordsInAlpha = (TreeSet<WordPlay>)g.findWordsWithRackAndHook(GADDAG.stringToTileArray(rack), new Tile(hook));
                System.out.println( wordsInAlpha);
            } else if (nextLine.equals("h")){
                hook = scan.next().toLowerCase().charAt(0);
                System.out.println("Hook is now: " + hook);
            }
            System.out.print("> ");
        }
    }
}