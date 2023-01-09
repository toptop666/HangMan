package sample;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// this class processes the data base of the words. the class provides random word to logic
// class by demand
public class WordDataBase {

    private String[] _words;

    // constructor, puts all the words in array to provide easy access
    public WordDataBase() throws IOException {

        Scanner input = new Scanner(new File("hangman_words.txt"));
        ArrayList<String> lines = new ArrayList<>();
        while(input.hasNext()) {
            lines.add(input.next());
        }
        String[] arr = lines.toArray(new String[lines.size()]);
        this._words = arr;

    }

    // returns a random word.
    public String randomWord() {
        int i = (int) (Math.random()*this._words.length);
        return this._words[i];
    }


}
