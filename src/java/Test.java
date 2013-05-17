import java.io.File;
import java.util.Scanner;

public class Test {

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
        if (args.length != 1){
            System.out.println("Please enter a dictionary file when executing");
            System.exit(-1);
        }

        GADDAG g = new GADDAG(); // change to trie to see a trie.
        //g.addAll(new String[]{"Abstract", "Absolute", "Absolve", "Butts", "Boats", "Bannana", "Abs"});
        long startTime = System.nanoTime();

        Scanner dictionary = new Scanner(new File(args[0]));
        while(dictionary.hasNext()) {
            g.add(dictionary.nextLine());
        }

        long duration = System.nanoTime() - startTime;
        System.out.println("Time to build GADDAG: " + duration/1000000000. + " seconds");
        repl(g);
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
                //System.out.println(g.findWordsWithRackAndHook(toCharacterArray(rack.toUpperCase()), new Tile(hook)));
            }
            else if (nextLine.equals("h")){
                hook = scan.next().toUpperCase().charAt(0);
                System.out.println("Hook is now: " + hook);
            }
            System.out.print("> ");
        }
    }
}