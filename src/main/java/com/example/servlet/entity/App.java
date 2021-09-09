package org.example;

import com.github.davidmoten.rx.IO;
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.Background;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.bg.PixelBoundryBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.nlp.tokenizers.ChineseWordTokenizer;
import com.kennycason.kumo.palette.ColorPalette;
import com.kennycason.kumo.palette.LinearGradientColorPalette;
import com.kennycason.kumo.font.KumoFont;
import com.kennycason.kumo.font.scale.LinearFontScalar;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.kennycason.kumo.wordstart.CenterWordStart;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;


public class App
{
    static JSONArray jsonGenerator() throws IOException{
        JSONArray array = new JSONArray();
        JSONArray words = new JSONArray();

        File file = new File("src/main/resources/chinese.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();
        String temp;
        while ((temp = br.readLine()) != null) {
            JSONObject word = new JSONObject();
            word.put("name", temp);
            word.put("times", 2);
            array.add(word);
        }
        br.close();
        return array;
    }

    static List<WordFrequency> makeFrequencyList() throws IOException{
        List<WordFrequency> frequencyList = new Vector<WordFrequency>();

        File file = new File("src/main/resources/chinese.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();
        String temp;
        while ((temp = br.readLine()) != null) {
            //word: times(randomized)
            frequencyList.add(new WordFrequency(temp, (int)(Math.random() * Math.random() * 6)));
        }
        br.close();
        for(WordFrequency wf:frequencyList){
            System.out.print( wf.getFrequency());
            System.out.println( wf.getWord());
        }
        return frequencyList;
    }


    static WordCloud getCloud(List<WordFrequency> wordFrequencies, int colorStyle, Boolean dark) throws IOException {
        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        frequencyAnalyzer.setWordFrequenciesToReturn(100);
        frequencyAnalyzer.setMinWordLength(1);
        frequencyAnalyzer.setMaxWordLength(7);
//        frequencyAnalyzer.setWordTokenizer(new ChineseWordTokenizer()); //用了这个会做中文分词
//        wordFrequencies = frequencyAnalyzer.load("src/main/resources/chinese.txt");
        final Dimension dimension = new Dimension(512, 512);
        final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        wordCloud.setPadding(2);
        java.awt.Font font = new java.awt.Font("微软雅黑", Font.BOLD, 40);
        if(!dark)
            wordCloud.setBackgroundColor(new Color(255, 255, 255));
        wordCloud.setKumoFont(new KumoFont(font));
        wordCloud.setBackground(new CircleBackground(256));
        switch (colorStyle){
            case 0:
                //orange
                wordCloud.setColorPalette(new ColorPalette(new Color(0xD9CB04), new Color(0xD9B504), new Color(0xF29F05), new Color(0xDB8A1C), new Color(0xF6D80F)));
                break;
            case 1:
                //blue
                wordCloud.setColorPalette(new ColorPalette(new Color(0x4E7AC7), new Color(0x35478C), new Color(0x4E7AC7), new Color(0x7FB2F0), new Color(0x35A1D6)));
                break;
            case 2:
                //green
                wordCloud.setColorPalette(new ColorPalette(new Color(0xB3DA61), new Color(0xAAE555), new Color(0x6ECD8F), new Color(0x539D73), new Color(0x2B7337)));
                break;
            default:
                //purple
                wordCloud.setColorPalette(new ColorPalette(new Color(0x45206C), new Color(0x36175E), new Color(0x553285), new Color(0x7B52AB), new Color(0x9768D1)));
                break;
            // colors followed by and steps between
            // wordCloud.setColorPalette(new LinearGradientColorPalette(new Color(0xD5E638), new Color(0xD6A033), new Color(0xE64F2E), 30, 30));
        }

        wordCloud.setFontScalar( new SqrtFontScalar(15, 40));
        wordCloud.build(wordFrequencies);
        return wordCloud;

    }
    public static void main( String[] args ) throws IOException
    {
        //多配色方案可选 0:orange, 1:blue, 2:green, 3:purple
        List<WordFrequency> frequencyList = makeFrequencyList();
        for(int i = 0; i < 4; i++){
            WordCloud cloud = getCloud(frequencyList, i, false);
            cloud.writeToFile("src/main/output/cloud_colorStyle" + i + "_light.png");
            cloud = getCloud(frequencyList, i, true);
            cloud.writeToFile("src/main/output/cloud_colorStyle" + i + "_dark.png");
            // wordCloud.writeToStreamAsPNG("some outputstream"); //写入到输出流
        }

    }
}
