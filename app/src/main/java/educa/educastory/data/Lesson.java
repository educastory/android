package educa.educastory.data;

import android.content.Context;

import java.io.Serializable;

import educa.educastory.R;

/**
 * Created by kenji on 15/10/03.
 */
public class Lesson implements Serializable {
    private int mNo;
    private String mTitle;
    private String mFirstNarration;
    private Conversation mConversation1;
    private Conversation mConversation2;
    private String mLastMessage;
    private byte[] mLastMessageImage;
    private int mLastMessageResId;
    private Result mResult1;
    private Result mResult2;
    private Result mResult3;
    private Result mResult4;

    public static Lesson createLesson(Context context, int lessonNo) {
        return new Lesson(context);
    }

    public Lesson() {
        /* nop */
    }

    private Lesson(Context context) {
        mNo = 1;
        mTitle = context.getString(R.string.lesson);
        mFirstNarration = context.getString(R.string.first_narration);
        
        mConversation1 = new Conversation();
        mConversation1.setQuestion(context.getString(R.string.question1));
        mConversation1.setQuestionResId(R.drawable.question1);
        mConversation1.setAnswer1(context.getString(R.string.answer1_1));
        mConversation1.setAnswer2(context.getString(R.string.answer1_2));
        mConversation1.setReaction1(context.getString(R.string.reaction1_1));
        mConversation1.setReaction1ResId(R.drawable.reaction1_1);
        mConversation1.setReaction2(context.getString(R.string.reaction1_2));
        mConversation1.setReaction2ResId(R.drawable.reaction1_2);

        mConversation2 = new Conversation();
        mConversation2.setQuestion(context.getString(R.string.question2));
        mConversation2.setQuestionResId(R.drawable.question2);
        mConversation2.setAnswer1(context.getString(R.string.answer2_1));
        mConversation2.setAnswer2(context.getString(R.string.answer2_2));
        mConversation2.setReaction1(context.getString(R.string.reaction2_1));
        mConversation2.setReaction1ResId(R.drawable.reaction2_1);
        mConversation2.setReaction2(context.getString(R.string.reaction2_2));
        mConversation2.setReaction2ResId(R.drawable.reaction2_2);

        mLastMessage = context.getString(R.string.last_message);
        mLastMessageResId = R.drawable.last_message;
        mResult1 = new Result();
        mResult1.setNarration(context.getString(R.string.narration1));
        mResult1.setNarrationResId(R.drawable.narration_not_happy);
        mResult2 = new Result();
        mResult2.setNarration(context.getString(R.string.narration2));
        mResult2.setNarrationResId(R.drawable.narration_happy);
        mResult3 = new Result();
        mResult3.setNarration(context.getString(R.string.narration3));
        mResult3.setNarrationResId(R.drawable.narration_happy);
        mResult4 = new Result();
        mResult4.setNarration(context.getString(R.string.narration4));
        mResult4.setNarrationResId(R.drawable.narration_so_happy);
    }

    public int getNo() {
        return mNo;
    }

    public void setNo(int no) {
        this.mNo = no;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getFirstNarration() {
        return mFirstNarration;
    }

    public void setFirstNarration(String firstNarration) {
        this.mFirstNarration = firstNarration;
    }

    public Conversation getConversation1() {
        return mConversation1;
    }

    public void setConversation1(Conversation conversation1) {
        this.mConversation1 = conversation1;
    }

    public Conversation getConversation2() {
        return mConversation2;
    }

    public void setConversation2(Conversation conversation2) {
        this.mConversation2 = conversation2;
    }

    public String getLastMessage() {
        return mLastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.mLastMessage = lastMessage;
    }

    public byte[] getLastMessageImage() {
        return mLastMessageImage;
    }

    public void setLastMessageImage(byte[] lastMessageImage) {
        this.mLastMessageImage = lastMessageImage;
    }

    public int getLastMessageResId() {
        return mLastMessageResId;
    }

    public  void setLastMessageResId(int lastMessageResId) {
        this.mLastMessageResId = lastMessageResId;
    }

    public Result getResult0() {
        return mResult1;
    }

    public void setResult1(Result result1) {
        this.mResult1 = result1;
    }

    public Result getResult1() {
        return mResult2;
    }

    public void setResult2(Result result2) {
        this.mResult2 = result2;
    }

    public Result getResult2() {
        return mResult3;
    }

    public void setResult3(Result result3) {
        this.mResult3 = result3;
    }

    public Result getResult3() {
        return mResult4;
    }

    public void setResult4(Result result4) {
        this.mResult4 = result4;
    }
}
