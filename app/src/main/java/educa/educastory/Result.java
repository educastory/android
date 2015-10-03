package educa.educastory;

import java.io.Serializable;

/**
 * Created by kenji on 15/10/03.
 */
public class Result implements Serializable {
    private String mNarration;
    private byte[] mNarrationImage;
    private int mNarrationResId;

    public String getNarration() {
        return mNarration;
    }

    public void setNarration(String narration) {
        this.mNarration = narration;
    }

    public byte[] getNarrationImage() {
        return mNarrationImage;
    }

    public void setNarrationImage(byte[] narrationImage) {
        this.mNarrationImage = narrationImage;
    }

    public int getNarrationResId() {
        return mNarrationResId;
    }

    public void setNarrationResId(int narrationResId) {
        this.mNarrationResId = narrationResId;
    }
}
