package educa.educastory.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by kenji on 15/10/03.
 */
public class Lesson implements Serializable {
    private static final String TAG = Lesson.class.getName();

    private String mTitle;
    private String mFirstNarration;
    private Conversation mConversation1 = new Conversation();
    private Conversation mConversation2 = new Conversation();
    private String mLastMessage;
    private byte[] mLastMessageImage;
    private Result mResult1 = new Result();
    private Result mResult2 = new Result();
    private Result mResult3 = new Result();
    private Result mResult4 = new Result();

    public static Lesson createLesson(Context context, int lessonNo) {
        return new Lesson(context, lessonNo);
    }

    public Lesson() {
        /* nop */
    }

    private Lesson(Context context, int lessonNo) {
        String key = Integer.toString(lessonNo);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        byte[] data = Base64.decode(preferences.getString(key, ""), Base64.DEFAULT);
        ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(data));
        try {
            do {
                ZipEntry ze = zis.getNextEntry();
                if (ze == null) {
                    break;
                }

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int count;
                while ((count = zis.read(buffer)) != -1) {
                    baos.write(buffer, 0, count);
                }

                String filename = ze.getName();
                Log.d(TAG, "filename = " + filename);
                if ("title.txt".equals(filename)) {
                    mTitle = getString(baos);
                } else if ("first_narration.txt".equals(filename)) {
                    mFirstNarration = getString(baos);
                } else if ("question1.txt".equals(filename)) {
                    mConversation1.setQuestion(getString(baos));
                } else if ("question1.jpg".equals(filename)) {
                    mConversation1.setQuestionImage(getImage(baos));
                } else if ("answer1_1.txt".equals(filename)) {
                    mConversation1.setAnswer1(getString(baos));
                } else if ("answer1_2.txt".equals(filename)) {
                    mConversation1.setAnswer2(getString(baos));
                } else if ("reaction1_1.txt".equals(filename)) {
                    mConversation1.setReaction1(getString(baos));
                } else if ("reaction1_1.jpg".equals(filename)) {
                    mConversation1.setReaction1Image(getImage(baos));
                } else if ("reaction1_2.txt".equals(filename)) {
                    mConversation1.setReaction2(getString(baos));
                } else if ("reaction1_2.jpg".equals(filename)) {
                    mConversation1.setReaction2Image(getImage(baos));
                } else if ("question2.txt".equals(filename)) {
                    mConversation2.setQuestion(getString(baos));
                } else if ("question2.jpg".equals(filename)) {
                    mConversation2.setQuestionImage(getImage(baos));
                } else if ("answer2_1.txt".equals(filename)) {
                    mConversation2.setAnswer1(getString(baos));
                } else if ("answer2_2.txt".equals(filename)) {
                    mConversation2.setAnswer2(getString(baos));
                } else if ("reaction2_1.txt".equals(filename)) {
                    mConversation2.setReaction1(getString(baos));
                } else if ("reaction2_1.jpg".equals(filename)) {
                    mConversation2.setReaction1Image(getImage(baos));
                } else if ("reaction2_2.txt".equals(filename)) {
                    mConversation2.setReaction2(getString(baos));
                } else if ("reaction2_2.jpg".equals(filename)) {
                    mConversation2.setReaction2Image(getImage(baos));
                } else if ("last_message.txt".equals(filename)) {
                    mLastMessage = getString(baos);
                } else if ("last_message.jpg".equals(filename)) {
                    mLastMessageImage = getImage(baos);
                } else if ("narration1.txt".equals(filename)) {
                    mResult1.setNarration(getString(baos));
                } else if ("narration1.jpg".equals(filename)) {
                    mResult1.setNarrationImage(getImage(baos));
                } else if ("narration2.txt".equals(filename)) {
                    mResult2.setNarration(getString(baos));
                } else if ("narration2.jpg".equals(filename)) {
                    mResult2.setNarrationImage(getImage(baos));
                } else if ("narration3.txt".equals(filename)) {
                    mResult3.setNarration(getString(baos));
                } else if ("narration3.jpg".equals(filename)) {
                    mResult3.setNarrationImage(getImage(baos));
                } else if ("narration4.txt".equals(filename)) {
                    mResult4.setNarration(getString(baos));
                } else if ("narration4.jpg".equals(filename)) {
                    mResult4.setNarrationImage(getImage(baos));
                }
            } while (true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                zis.close();
            } catch (IOException e) {
                /* nop */
            }
        }
    }

    private String getString(ByteArrayOutputStream baos) {
        return new String(baos.toByteArray());
    }

    private byte[] getImage(ByteArrayOutputStream baos) {
        return baos.toByteArray();
    }

    public String getTitle() {
        return mTitle;
    }

    public String getFirstNarration() {
        return mFirstNarration;
    }

    public Conversation getConversation1() {
        return mConversation1;
    }

    public Conversation getConversation2() {
        return mConversation2;
    }

    public String getLastMessage() {
        return mLastMessage;
    }

    public byte[] getLastMessageImage() {
        return mLastMessageImage;
    }

    public Result getResult1() {
        return mResult1;
    }

    public Result getResult2() {
        return mResult2;
    }

    public Result getResult3() {
        return mResult3;
    }

    public Result getResult4() {
        return mResult4;
    }
}
