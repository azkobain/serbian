package com.example.serbiancases;

public class MainConfig {
    private static MainConfig instance;
    private Section global;
    private Section nouns;

    public Section getGlobal() {
        return global;
    }

    public void setGlobal(Section global) {
        this.global = global;
    }

    public Section getNouns() {
        return nouns;
    }

    public void setNouns(Section nouns) {
        this.nouns = nouns;
    }


    public static class Section {
        private int score;
        private String firstTime;
//        private String deathMode;

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getFirstTime() {
            return firstTime;
        }

        public void setFirstTime(String firstTime) {
            this.firstTime = firstTime;
        }

//        public String getDeathMode() {
//            return deathMode;
//        }

//        public void setDeathMode(String deathMode) {
//            this.deathMode = deathMode;
//        }
    }

    private String sharedText;

    private MainConfig() {
        // Private constructor to enforce singleton pattern
    }

    public static MainConfig getInstance() {
        if (instance == null) {
            instance = new MainConfig();
        }
        return instance;
    }

    public String getSharedText() {
        return sharedText;
    }

    public void setSharedText(String sharedText) {
        this.sharedText = sharedText;
    }
}
