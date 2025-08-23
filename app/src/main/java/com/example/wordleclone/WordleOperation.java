package com.example.wordleclone;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;

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

    public SpannableString checkGuess(String userGuess, Map<Character,Integer> charOccurences){
//        String returnString = "";
        SpannableString returnString = new SpannableString(userGuess);
        char[] userGuessArr = userGuess.toCharArray();
        char[] gameWordarr = GameWord.toCharArray();
        for(int i = 0; i < userGuessArr.length; i++){
            if(userGuessArr[i] == gameWordarr[i]){
//                returnString = returnString + "<font color='#008000'>" + userGuessArr[i] + "</font>";
                returnString.setSpan(new ForegroundColorSpan(Color.GREEN),i,i+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                charOccurences.put(userGuessArr[i], charOccurences.get(userGuessArr[i])-1);
            }
            else if((userGuessArr[i] != gameWordarr[i] && GameWord.contains(String.valueOf(userGuessArr[i])) && (charOccurences.get(userGuessArr[i]) != 0)))
            {
                returnString.setSpan(new ForegroundColorSpan(Color.YELLOW),i,i+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                returnString = returnString +"<font color='#FFFF00'>" +  userGuessArr[i] +"</font>" ;
            }
            else{
//              returnString = returnString  + userGuessArr[i];
            }
        }
        return returnString;
    }


    public String returnGameWord(){
        return GameWord;
    }

}
