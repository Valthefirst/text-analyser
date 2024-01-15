/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.analyser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author nneji
 */
public class TextAnalyser extends Application {

    private String path_url = "C:\\Users\\nneji\\OneDrive\\Desktop\\x.txt";
    private Label vowelLabel;
    private Label senLabel;
    private Label ranLabel;

    @Override
    public void start(Stage primaryStage) {
        //Labels containing the messages
        Label line1 = new Label("Click the button to see the text analysis.");
        Label line3 = new Label("Number of sentences: ");
        Label line4 = new Label("Number of vowels: ");
        Label line5 = new Label("A random word: ");

        //Labels containing info pertaining to the text
        senLabel = new Label();
        vowelLabel = new Label();
        ranLabel = new Label();

        //Button
        Button btn = new Button();
        btn.setText("Analyze Text");
        btn.setOnAction(new BtnHandler());

        //HBoxes
        HBox hbox1 = new HBox(line3, senLabel);
        HBox hbox2 = new HBox(line4, vowelLabel);
        HBox hbox3 = new HBox(line5, ranLabel);

        //VBox
        VBox vbox = new VBox(10, line1, btn, hbox1, hbox2, hbox3);

        //Set the VBox allingment to center
        vbox.setAlignment(Pos.CENTER);

        //Set the VBox padding to 10 pixels
        vbox.setPadding(new Insets(10));
        
        Scene scene = new Scene(vbox);

        primaryStage.setTitle("Text Analysis");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    class BtnHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String text;
            int vowels = 0;
            int sentences = 0;
            int words = 0;
            char ch;
            String randomWord = null;

            try {
                //Opening the file and putting it in a string
                File file = new File(path_url);
                Scanner textFile = new Scanner(file);
                text = textFile.nextLine();

                //Vowels
                for (int i = 0; i < text.length(); i++) {
                    ch = text.charAt(i);
                    switch (ch) {
                        case 'a':
                        case 'e':
                        case 'i':
                        case 'o':
                        case 'u':
                        case 'A':
                        case 'E':
                        case 'I':
                        case 'O':
                        case 'U':
                            vowels++;
                    }
                }

                //Sentences
                for (int j = 0; j < text.length(); j++) {
                    ch = text.charAt(j);
                    if (ch == '.') {
                        sentences++;
                    }
                }

                //Random word
                //Count the words
                for (int k = 0; k < text.length(); k++) {
                    ch = text.charAt(k);
                    if ((ch == ' ' || ch == '.') && ((ch == ' ') || !(ch == '.'))) {
                        words++;
                    }
                }
                //Put the words into an array
                String[] arrWords;
                arrWords = text.split("\\s+");
                Random ran = new Random();
                int ranNum = ran.nextInt(words + 1);
                randomWord = arrWords[ranNum];
            } catch (FileNotFoundException e) {
                System.out.println("The file " + path_url + " does not exist.\n" + e.getMessage());
            }

            //Putting the numbers in the Labels
            vowelLabel.setText(Integer.toString(vowels));
            senLabel.setText(Integer.toString(sentences));
            ranLabel.setText(randomWord);
        }
    }

}
