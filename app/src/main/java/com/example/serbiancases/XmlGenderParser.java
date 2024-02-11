package com.example.serbiancases;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlGenderParser {
    public static List<QuestionGender> parseXml(Context context, String xmlFileName) {
        List<QuestionGender> questions = new ArrayList<>();

        try {
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(xmlFileName);

            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);

            int eventType = parser.getEventType();
            QuestionGender currentQuestion = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("word".equals(tagName)) {
                            currentQuestion = new QuestionGender();
                        } else if ("serb".equals(tagName)) {
                            currentQuestion.setSerb(parser.nextText());
                        } else if ("rus".equals(tagName)) {
                            currentQuestion.setRus(parser.nextText());
                        } else if ("gender".equals(tagName)) {
                            currentQuestion.setGender(parser.nextText());
                        }
                        else if ("points".equals(tagName)) {
                            currentQuestion.setPoints(parser.nextText());
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if ("word".equals(tagName) && currentQuestion != null) {
                            questions.add(currentQuestion);
                        }
                        break;
                }

                eventType = parser.next();
            }

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }

        return questions;
    }

    private static List<String> parseOptions(XmlPullParser parser) throws IOException, XmlPullParserException {
        List<String> options = new ArrayList<>();

        int eventType = parser.next();

        while (eventType != XmlPullParser.END_TAG || !"word".equals(parser.getName())) {
            if (eventType == XmlPullParser.START_TAG && "word".equals(parser.getName())) {
                options.add(parser.nextText());
            }
            eventType = parser.next();
        }

        return options;
    }
}
