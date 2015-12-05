package educa.educastory.data;

import java.io.Serializable;

/**
 * Created by kenji on 15/12/05.
 */
public class Header implements Serializable {
    private int mNo;
    private String mTitle;

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
}
