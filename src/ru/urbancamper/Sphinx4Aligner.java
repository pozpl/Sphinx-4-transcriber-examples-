/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper;

import edu.cmu.sphinx.frontend.util.AudioFileDataSource;
import edu.cmu.sphinx.linguist.language.grammar.TextAlignerGrammar;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author pozpl
 */
public class Sphinx4Aligner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException {

        ConfigurationManager cm = new ConfigurationManager("config/sphinx-custom.xml");
        Recognizer recognizer = (Recognizer) cm.lookup("recognizer");

        TextAlignerGrammar grammar = (TextAlignerGrammar) cm.lookup("textAlignGrammar");
//        grammar.setText("one zero zero zero one nine oh two one oh zero one eight zero three");
//        grammar.setText("ergs one zero zero zero one nine oh two one oh zero one eight zero three");
        recognizer.addResultListener(grammar);

        /* allocate the resource necessary for the recognizer */
        recognizer.allocate();

        // configure the audio input for the recognizer
        AudioFileDataSource dataSource = (AudioFileDataSource) cm.lookup("audioFileDataSource");
//        dataSource.setAudioFile(new URL("file:media/10001-90210-01803.wav"), null);
        dataSource.setAudioFile(new URL("file:media/1-test-got.wav"), null);

        Result result;
        String resultTextAggregated = "";
        while ((result = recognizer.recognize()) != null) {

            String resultText = result.getTimedBestResult(false, true);
            System.out.println(resultText);
            resultTextAggregated += resultText;
        }
        System.out.println(resultTextAggregated + "\n");
    }


    public ArrayList<HashMap<String, ArrayList<Integer>>> parseAlignedText(String alignedText){
        String[] wordsDurationsArray = alignedText.split(" ");
        for(int wordsCounter = 0; wordsCounter < wordsDurationsArray.length; wordsCounter++){
            
        }
        return null;
    }
}
