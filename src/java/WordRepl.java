import li.scrabb.GADDAG;
import li.scrabb.Tile;
import li.scrabb.WordPlay;

import java.io.File;
import java.util.Scanner;
import java.util.Set;

public class WordRepl {

    private static final double nanosecondsToSeconds = 1000000000.;

    public static void main(String[] args) throws Exception {
        GADDAG g = new GADDAG();
        long startTime = System.nanoTime();

        if (args.length == 1){
            Scanner dictionary = new Scanner(new File(args[0]));
            while(dictionary.hasNext()) {
                g.add(dictionary.nextLine());
            }
            long duration = System.nanoTime() - startTime;
            System.out.println("Time to build li.scrabb.GADDAG: " + duration / nanosecondsToSeconds + " seconds"); //Nano seconds to seconds
            repl(g);
        } else {
            g.add(new String[]{"Abstract", "Absolute", "Absolve", "Boats", "Bannana", "Abs"});
            repl(g);
        }
    }

    public static void repl(GADDAG g){
        System.out.println("Welcome to the word finder");
        System.out.println("Enter in 'r', followed by the rack you want to use to search.");
        System.out.println("Use 'h' followed by a character to change the hook.");
        Scanner scan = new Scanner(System.in);
        System.out.print("> ");
        Tile hookTile = new Tile(' ');
        while(scan.hasNext()){
            String nextLine = scan.next();
            if (nextLine.equals("r")){
                String rack = scan.next();
                Set<WordPlay> wordsInAlpha = g.findWordsWithRackAndHook(GADDAG.stringToTileArray(rack), hookTile);
                System.out.println( wordsInAlpha);
            } else if (nextLine.equals("h")){
                hookTile = new Tile(scan.next().toLowerCase().charAt(0));
                System.out.println("Hook is now: " + hookTile.getValue());
            }
            System.out.print("> ");
        }
    }
}