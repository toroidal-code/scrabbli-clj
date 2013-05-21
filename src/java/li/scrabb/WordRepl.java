package li.scrabb;

import java.io.File;
import java.util.*;

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
        Comparator<WordPlay> comp = WordPlay.alphaComparator();
        List<WordPlay> wordList;
        while(scan.hasNext()){
            String nextLine = scan.next();
            switch (nextLine) {
                case "r":
                    String rack = scan.next();
                    wordList = new ArrayList<>();
                    wordList.addAll(g.findWordsWithRackAndHook(GADDAG.stringToTileArray(rack), hookTile));
                    Collections.sort(wordList, comp);
                    System.out.println(wordList);
                    break;
                case "h":
                    hookTile = new Tile(scan.next().toLowerCase().charAt(0));
                    System.out.println("Hook is now: " + hookTile.getLabel());
                    break;
                case "c":
                    if (scan.next().equals("a"))
                        comp = WordPlay.alphaComparator();
                    else if (scan.next().equals("s"))
                        comp = WordPlay.scoreComparator();
                    break;
            }
            System.out.print("> ");
        }
    }
}