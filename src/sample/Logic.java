package sample;
import java.io.IOException;

// this is logic class. it is doing the logic of the hangman game
public class Logic {

    // enums are unchangeable, so no security problem occurs by keeping it public
    public enum states {BAD_GUESS, GOOD_GUESS, FINISHING_GUESS};

    private String _word;
    private WordDataBase _dataBase;

    // guess is a string in the length of the word. the chars of guess are initialized to '#'
    // and if the user guesses a correct char the appearances of the char are revealed
    private String _guess;

    // badPoints is a variable representing the state of the hangman. 0 is no limb is on the gallows
    // while 6 means all his body parts are on the gallows, resulting a loss.
    private int _badPoints;

    // constructor of logic
    public Logic() throws IOException {
        this._dataBase = new WordDataBase();
        this._word = this._dataBase.randomWord();
        this._badPoints = 0;
        this._guess = this._word;
        this._guess = this._guess.replaceAll(".", "#");
    }

    // getter to bad points
    public int getBadPoints() {
        return _badPoints;
    }

    // getter to the word the user needs to guess
    public String getWord() {
        return _word;
    }

    // getter to the guessed letters. if the user correctly guessed the word, _guess = _word
    public String getGuess() {
        return _guess;
    }

    // kind of reset to Logic(). the database returns a random word and the method resets the fields
    // according to the new word.
    public boolean newWord() {
        String temp = _dataBase.randomWord();
        while(temp.equals(this._word)) {
            temp = _dataBase.randomWord();
        }
        this._badPoints = 0;
        this._word = temp;
        this._guess = this._word;
        this._guess = this._guess.replaceAll(".", "#");
        return true;
    }

    // the guess method input is a char and outputs a state according to the validity
    // og the guess
    public states guess(char a) {
        states current = states.BAD_GUESS;
        for(int i = 0; i<this._word.length(); i++) {
            if(this._word.charAt(i) == a) {
                current = states.GOOD_GUESS;
                this._guess = this._guess.substring(0, i) + a + this._guess.substring(i+1);
            }
        }
        if(this._guess.equals(this._word)) {
            current = states.FINISHING_GUESS;
        }
        if(current == states.BAD_GUESS) {
            this._badPoints++;
        }
        return current;
    }



}
