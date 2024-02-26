package com.example.serbiancases;

import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ConfigManager {

    private static final String FILE_NAME = "config.xml";

    public static void saveConfig(Context context, MainConfig config) {
        try (FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
             OutputStreamWriter osw = new OutputStreamWriter(fos)) {

            XmlSerializer serializer = Xml.newSerializer();
            serializer.setOutput(osw);

            serializer.startDocument(null, true);
            serializer.startTag(null, "application");

            // Serialize the global settings
            serializeSection(serializer, "global", config.getGlobal());

            // Serialize the nouns
            serializeSection(serializer, "nouns", config.getNouns());

            serializer.endTag(null, "application");

            serializer.endDocument();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void serializeSection(XmlSerializer serializer, String sectionName, MainConfig.Section section)
            throws IOException {
        serializer.startTag(null, sectionName);

        // Serialize the score
        serializer.startTag(null, "score");
        serializer.text(Integer.toString(section.getScore()));
        serializer.endTag(null, "score");

        // Serialize the first_time
        serializer.startTag(null, "first_time");
        serializer.text(section.getFirstTime());
        serializer.endTag(null, "first_time");

//        // Serialize the death_mode
//        serializer.startTag(null, "death_mode");
//        serializer.text(section.getDeathMode());
//        serializer.endTag(null, "death_mode");

        serializer.endTag(null, sectionName);
    }

    public static MainConfig loadConfig(Context context) {
        try (FileInputStream fis = context.openFileInput(FILE_NAME);
             InputStreamReader isr = new InputStreamReader(fis)) {

            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(isr);

            int eventType = parser.getEventType();
            MainConfig config = MainConfig.getInstance();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    String tagName = parser.getName();

                    if ("global".equals(tagName)) {
                        config.setGlobal(parseSection(parser));
                    } else if ("nouns".equals(tagName)) {
                        config.setNouns(parseSection(parser));
                    }
                }

                eventType = parser.next();
            }

            return config;
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static MainConfig.Section parseSection(XmlPullParser parser)
            throws IOException, XmlPullParserException {
        int eventType = parser.next();
        MainConfig.Section section = new MainConfig.Section();

        while (eventType != XmlPullParser.END_TAG) {
            if (eventType == XmlPullParser.START_TAG) {
                String tagName = parser.getName();

                switch (tagName) {
                    case "score":
                        section.setScore(Integer.parseInt(parser.nextText()));
                        break;
                    case "first_time":
                        section.setFirstTime(parser.nextText());
                        break;
//                    case "death_mode":
//                        section.setDeathMode(parser.nextText());
//                        break;
                    // Add more cases for additional fields
                }
            }

            eventType = parser.next();
        }

        return section;
    }


//    public static class Section {
//        private int score;
//        private String firstTime;
////        private String deathMode;
//
//        public int getScore() {
//            return score;
//        }
//
//        public void setScore(int score) {
//            this.score = score;
//        }
//
//        public String getFirstTime() {
//            return firstTime;
//        }
//
//        public void setFirstTime(String firstTime) {
//            this.firstTime = firstTime;
//        }
//
////        public String getDeathMode() {
////            return deathMode;
////        }
//
////        public void setDeathMode(String deathMode) {
////            this.deathMode = deathMode;
////        }
//    }
}
