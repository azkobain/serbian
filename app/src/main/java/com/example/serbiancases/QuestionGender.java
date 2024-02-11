package com.example.serbiancases;


import java.util.List;

//public class Question {
//    private String text;
//    private List<String> options;
//    private String correctOption;
//
//    // Add getters and setters
//
//    // Example usage:
//    // Question question = new Question();
//    // question.setText("What is the capital of France?");
//    // question.setOptions(Arrays.asList("Paris", "Madrid", "London", "Rome"));
//    // question.setCorrectOption("Paris");
//}


public class QuestionGender {
    private String serb; // Serbian word
    private String rus; // Russian translation
    private String gender; // Male(M) / Female(F) / Neutral(N)
    private int points; // points for right answer, exceptions give 3 points

    public void setSerb(String text) {serb = text;}
    public String getSerb() {return serb;}
    public void setRus(String text) {rus = text;}
    public String getRus() { return rus;}
    public void setGender(String text) {gender = text;}
    public String getGender() {return gender;}
    public void setPoints(String text) {points =  Integer.valueOf(text);}
    public int getPoints() {return points;}
}

