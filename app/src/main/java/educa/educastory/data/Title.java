package educa.educastory.data;

import java.io.Serializable;

/**
 * Created by kenji on 15/12/05.
 */
public class Title implements Serializable {
    private int mNo;
    private String mText;

    public int getNo() {
        return mNo;
    }

    public void setNo(int no) {
        this.mNo = no;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
    }
}
