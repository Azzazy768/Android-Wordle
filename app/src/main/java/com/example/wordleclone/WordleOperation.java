package com.example.wordleclone;

import android.text.SpannableString;

import androidx.core.app.NavUtils;

import java.util.HashMap;
import java.util.Random;
import java.util.Map;

public class WordleOperation {
    String[] WordsArray = {"ABACK","ABASE","ABATE","ABBEY","ABIDE","ABOUT","ABOVE","ABYSS","ACORN","ACRID",
            "ACTOR","ACUTE","ADAGE","ADAPT","ADEPT","ADMIN","ADMIT","ADOBE","ADOPT","ADORE",
            "ADULT","AFFIX","AFTER","AGAIN","AGAPE","AGATE","AGENT","AGILE","AGING","AGLOW",
            "AGONY","AGREE","AHEAD","AISLE","ALARM","ALBUM","ALERT","ALIEN","ALIKE","ALIVE",
            "ALLOW","ALOFT","ALONE","ALOOF","ALOUD","ALPHA","ALTAR","ALTER","AMASS","AMBER",
            "AMBLE","AMISS","AMPLE","ANGEL","ANGER","ANGLE","ANGRY","ANGST","ANODE","ANTIC",
            "ANVIL","AORTA","APART","APHID","APPLE","APPLY","APRON","APTLY","ARBOR","ARDOR",
            "WOOER","WORDY","WORLD","WORRY","WORSE","WORST","WOULD","WOVEN","WRATH","WREAK",
            "WRIST","WRITE","WRONG","WROTE","WRUNG","YACHT","YEARN","YEAST","YIELD","YOUNG",
            "YOUTH","ZEBRA","ZESTY"};

    String GameWord;
    Map<Character,Integer> charOccurences = new HashMap<>();


    public String giveStartingWord(){
        if(GameWord != null){
            return GameWord;
        }
        else {
            Random randomObject = new Random();
            int max = 98;
            int min = 0;
            int WordValue = randomObject.nextInt((max - min + 1) + min);
            GameWord = WordsArray[WordValue];
            char[] charArray = GameWord.toCharArray();
            for(int i = 0; i < GameWord.length(); i++){
                Character TempChar = charArray[i];
                if(charOccurences.containsKey(TempChar)){
                    charOccurences.put(TempChar,charOccurences.get(TempChar)+1);
                }
                else{
                    charOccurences.put(TempChar,1);
                }
            }
            return WordsArray[WordValue];
        }
    }

    public Map<Character,Integer> returnCharOccurences(){
        return charOccurences;
    }

    public String checkGuess(String userGuess, Map<Character,Integer> charOccurences){
        String returnString = "";
        char[] userGuessArr = userGuess.toCharArray();
        char[] gameWordarr = GameWord.toCharArray();
        for(int i = 0; i < userGuessArr.length; i++){
            if(userGuessArr[i] == gameWordarr[i]){
                returnString = returnString + "<font color='#008000'>" + userGuessArr[i] + "</font>";
                charOccurences.put(userGuessArr[i], charOccurences.get(userGuessArr[i])-1);
            }
            else if((userGuessArr[i] != gameWordarr[i] && GameWord.contains(String.valueOf(userGuessArr[i])) && (charOccurences.get(userGuessArr[i]) != 0)))
            {
                returnString = returnString +"<font color='#FFFF00'>" +  userGuessArr[i] +"</font>" ;
            }
            else{
              returnString = returnString  + userGuessArr[i];
            }
        }
        return returnString;
    }


    public String returnGameWord(){
        return GameWord;
    }

}
