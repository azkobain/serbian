package com.example.serbiancases;
public class QuestionGender {
    private String serbian; // Serbian word
    private String russian; // Russian translation
    private String gender; // Male(M) / Female(F) / Neutral(N)
    private int points; // points for right answer, exceptions give 3 points

    QuestionGender()
    {
    }
    QuestionGender(String serbian, String russian, String gender, int points)
    {
        this.serbian = serbian;
        this.russian = russian;
        this.gender = gender;
        this.points = points;
    }
    public void setSerbian(String text)
    {
        serbian = text;
    }
    public String getSerbian()
    {
        return serbian;
    }
    public void setRussian(String text)
    {
        russian = text;
    }
    public String getRussian()
    {
        return russian;
    }
    public void setGender(String text)
    {
        gender = text;
    }
    public String getGender()
    {
        return gender;
    }
    public void setPoints(String text)
    {
        points =  Integer.parseInt(text);
    }
    public int getPoints()
    {
        return points;
    }
}