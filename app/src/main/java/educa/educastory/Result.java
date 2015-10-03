package educa.educastory;

import android.graphics.Bitmap;

/**
 * Created by kenji on 15/10/03.
 */
public class Result {
    private String mNarration;
    private Bitmap mNarrationImage;

    public String getNarration() {
        return mNarration;
    }

    public void setNarration(String narration) {
        this.mNarration = narration;
    }

    public Bitmap getNarrationImage() {
        return mNarrationImage;
    }

    public void setNarrationImage(Bitmap mnarrationimage) {
        this.mNarrationImage = mnarrationimage;
    }
}
