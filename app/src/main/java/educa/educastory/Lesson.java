package educa.educastory;

/**
 * Created by kenji on 15/10/03.
 */
public class Lesson {
    private String mTitle;
    private String mFirstNarration;
    private Conversation mConversation1;
    private Conversation mConversation2;
    private String mLastMessage;
    private Result mResult1;
    private Result mResult2;
    private Result mResult3;
    private Result mResult4;

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

    public Result getResult1() {
        return mResult1;
    }

    public void setResult1(Result result1) {
        this.mResult1 = result1;
    }

    public Result getResult2() {
        return mResult2;
    }

    public void setResult2(Result result2) {
        this.mResult2 = result2;
    }

    public Result getResult3() {
        return mResult3;
    }

    public void setResult3(Result result3) {
        this.mResult3 = result3;
    }

    public Result getResult4() {
        return mResult4;
    }

    public void setResult4(Result result4) {
        this.mResult4 = result4;
    }
}
