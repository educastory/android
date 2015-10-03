package educa.educastory;

import android.graphics.Bitmap;

/**
 * Created by kenji on 15/10/03.
 */
public class Conversation {
    private String mQuestion;
    private Bitmap mQuestionImage;
    private String mAnswer1;
    private String mAnswer2;
    private String mReaction1;
    private Bitmap mReaction1Image;
    private String mReaction2;
    private Bitmap mReaction2Image;
    private int mScore;

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        this.mQuestion = question;
    }

    public Bitmap getQuestionImage() {
        return mQuestionImage;
    }

    public void setQuestionImage(Bitmap questionImage) {
        this.mQuestionImage = questionImage;
    }

    public String getAnswer1() {
        return mAnswer1;
    }

    public void setAnswer1(String answer1) {
        this.mAnswer1 = answer1;
    }

    public String getAnswer2() {
        return mAnswer2;
    }

    public void setAnswer2(String answer2) {
        this.mAnswer2 = answer2;
    }

    public String getReaction1() {
        return mReaction1;
    }

    public void setReaction1(String reaction1) {
        this.mReaction1 = reaction1;
    }

    public Bitmap getReaction1Image() {
        return mReaction1Image;
    }

    public void setReaction1Image(Bitmap reaction1Image) {
        this.mReaction1Image = reaction1Image;
    }

    public String getReaction2() {
        return mReaction2;
    }

    public void setReaction2(String reaction2) {
        this.mReaction2 = reaction2;
    }

    public Bitmap getReaction2Image() {
        return mReaction2Image;
    }

    public void setReaction2Image(Bitmap reaction2Image) {
        this.mReaction2Image = reaction2Image;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int mScore) {
        this.mScore = mScore;
    }
}
