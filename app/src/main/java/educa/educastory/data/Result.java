package educa.educastory.data;

import java.io.Serializable;

/**
 * Created by kenji on 15/10/03.
 */
public class Result implements Serializable {
    private String mNarration;
    private byte[] mNarrationImage;

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
}
