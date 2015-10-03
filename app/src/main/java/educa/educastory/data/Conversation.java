package educa.educastory.data;

import java.io.Serializable;

/**
 * Created by kenji on 15/10/03.
 */
public class Conversation implements Serializable {
    private String mQuestion;
    private byte[] mQuestionImage;
    private int mQuestionResId;
    private String mAnswer1;
    private String mAnswer2;
    private String mReaction1;
    private byte[] mReaction1Image;
    private int mReaction1ResId;
    private String mReaction2;
    private byte[] mReaction2Image;
    private int mReaction2ResId;
    private int mScore;

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        this.mQuestion = question;
    }

    public byte[] getQuestionImage() {
        return mQuestionImage;
    }

    public void setQuestionImage(byte[] questionImage) {
        this.mQuestionImage = questionImage;
    }

    public int getQuestionResId() {
        return mQuestionResId;
    }

    public void setQuestionResId(int questionResId) {
        mQuestionResId = questionResId;
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

    public byte[] getReaction1Image() {
        return mReaction1Image;
    }

    public void setReaction1Image(byte[] reaction1Image) {
        this.mReaction1Image = reaction1Image;
    }

    public int getReaction1ResId() {
        return this.mReaction1ResId;
    }

    public void setReaction1ResId(int reaction1ResId) {
        this.mReaction1ResId = reaction1ResId;
    }

    public String getReaction2() {
        return mReaction2;
    }

    public void setReaction2(String reaction2) {
        this.mReaction2 = reaction2;
    }

    public byte[] getReaction2Image() {
        return mReaction2Image;
    }

    public void setReaction2Image(byte[] reaction2Image) {
        this.mReaction2Image = reaction2Image;
    }

    public int getReaction2ResId() {
        return this.mReaction2ResId;
    }

    public void setReaction2ResId(int reaction2ResId) {
        this.mReaction2ResId = reaction2ResId;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int mScore) {
        this.mScore = mScore;
    }
}
