package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Controller {

    private final int NUMBER_OF_GUESSES = 6;

    private Logic logic;
    @FXML
    private Canvas canv;
    private GraphicsContext gc;
    private Label label;
    @FXML
    private TextField input;
    @FXML
    private Text guess;
    @FXML
    private Text systemOutput;


    @FXML
    void update(ActionEvent event) {

    }


    // the update method starts when the user presses the "send" button. the method reads a string from the
    // textfield. if the input is illegal the method asks from the user another input and returns
    // if the input is legal, the method calls for guess() method of Logic(), the method shows the user the
    // correctness of his guess and how many guesses he has. if the user runs out of guesses the method shows him
    // the correct word. if he guessed the entire word, the method shows him the correct word.
    public void update(javafx.event.ActionEvent actionEvent) {
        String temp = input.getText();
        temp.toLowerCase(Locale.ROOT);
        if(temp.length() != 1) {
            this.systemOutput.setText("illegal input, should be 1 english letter");
            return;
        }
        this.label.setText(temp);
        input.setText("");
        switch (logic.guess(this.label.getText().charAt(0))) {
            // these magic numbers are the coordinates of the hangmam. no point in converting these to proper variables
            case BAD_GUESS:
                switch (logic.getBadPoints()) {
                    case 1:
                        gc.fillOval(125, 150, 50, 50);
                        this.systemOutput.setText("incorrect, you have " + (NUMBER_OF_GUESSES - logic.getBadPoints()) + " guesses left");
                        break;
                    case 2:
                        gc.strokeLine(150, 150, 150, 300);
                        this.systemOutput.setText("incorrect, you have " + (NUMBER_OF_GUESSES - logic.getBadPoints()) + " guesses left");
                        break;
                    case 3:
                        gc.strokeLine(150, 210, 200, 240);
                        this.systemOutput.setText("incorrect, you have " + (NUMBER_OF_GUESSES - logic.getBadPoints()) + " guesses left");
                        break;
                    case 4:
                        gc.strokeLine(150, 210, 100, 240);
                        this.systemOutput.setText("incorrect, you have " + (NUMBER_OF_GUESSES - logic.getBadPoints()) + " guesses left");
                        break;
                    case 5:
                        gc.strokeLine(150, 300, 200, 330);
                        this.systemOutput.setText("incorrect, you have " + (NUMBER_OF_GUESSES - logic.getBadPoints()) + " guesses left");
                        break;
                    case 6:
                        gc.strokeLine(150, 300, 100, 330);
                        this.guess.setText("you lost!");
                        this.systemOutput.setText(" the word was: " + logic.getWord());
                        return;
                    default:
                }
                break;
            case GOOD_GUESS:
                this.guess.setText(logic.getGuess());
                this.systemOutput.setText("correct!, you have " + (NUMBER_OF_GUESSES - logic.getBadPoints()) + " guesses left");
                break;
            case FINISHING_GUESS:
                this.guess.setText("Great, you win!");
                this.systemOutput.setText("the word was: " + logic.getWord());
                break;
            default:
                break;
        }
    }

    // helper method, used to clean the canvas, draw the gallows and giving orders to the user
    private void clearCanvas() {
        gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());
        gc.setStroke(Color.BLACK);
        // these magic numbers are coordinates for the gallows, no point in converting these to proper variables
        gc.strokeLine(50, 400, 150, 400);
        gc.strokeLine(100, 400, 100, 100);
        gc.strokeLine(100, 100, 150, 100);
        gc.strokeLine(150, 100, 150, 150);
        this.label = new Label("no input");
        this.systemOutput.setText("input should be 1 english letter");
    }

    // init method, initialize the fields
    public void initialize() throws IOException {
        this.logic = new Logic();
        gc = canv.getGraphicsContext2D();
        clearCanvas();
        this.label = new Label("no input");
        this.guess.setText(logic.getGuess());

    }

    // replay function, starts when the user hits the replay button, clearing the canvas and choosing a new
    // random word.
    public void replay(javafx.event.ActionEvent actionEvent) {
        clearCanvas();
        this.logic.newWord();
        this.guess.setText(this.logic.getGuess());

    }


}
