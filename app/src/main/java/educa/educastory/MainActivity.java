package educa.educastory;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import educa.educastory.data.Conversation;
import educa.educastory.data.Lesson;
import educa.educastory.data.Result;
import icepick.Icepick;
import icepick.Icicle;

public class MainActivity extends AppCompatActivity implements AnswerDialogFragment.OnChoiceListener {
    private static final String TAG = MainActivity.class.getName();
    private static final String KEY_LESSON_NO = "LESSON_NO";

    private ImageView mLessonImage;
    private TextView mLessonText;

    @Icicle
    Lesson mLesson;

    @Icicle
    int mMode;

    @Icicle
    int mScore;

    @Icicle
    int mAnswer;

    public static Intent createIntent(Context context, int lessonNo) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(KEY_LESSON_NO, lessonNo);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLessonImage = (ImageView) findViewById(R.id.lesson_image);
        mLessonText = (TextView) findViewById(R.id.lesson_text);
        mLessonText.setOnClickListener(new TextClickListener());

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            int lessonNo = intent.getIntExtra(KEY_LESSON_NO, 1);
            mLesson = Lesson.createLesson(this, lessonNo);
            mMode = 0;
            mScore = 0;
            mAnswer = 0;
        } else {
            Icepick.restoreInstanceState(this, savedInstanceState);
        }

        setTitle(mLesson.getTitle());
        changeMode();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    private void changeMode() {
        Conversation conversation1 = mLesson.getConversation1();
        Conversation conversation2 = mLesson.getConversation2();
        Result result0 = mLesson.getResult0();
        Result result1 = mLesson.getResult1();
        Result result2 = mLesson.getResult2();
        Result result3 = mLesson.getResult3();

        switch (mMode) {
            case 0:
                mLessonImage.setImageDrawable(null);
                mLessonText.setText(mLesson.getFirstNarration());
                mLessonText.setLines(10);
                break;
            case 1:
                mLessonImage.setImageBitmap(createBitmap(conversation1.getQuestionImage(), conversation1.getQuestionResId()));
                mLessonText.setText(conversation1.getQuestion());
                mLessonText.setLines(5);
                break;
            case 2:
                AnswerDialogFragment.newInstance(conversation1.getAnswer1(), conversation1.getAnswer1())
                        .show(getSupportFragmentManager(), "question");
                break;
            case 3:
                if (mAnswer == 1) {
                    mLessonImage.setImageBitmap(createBitmap(conversation1.getReaction1Image(), conversation1.getReaction1ResId()));
                    mLessonText.setText(conversation1.getReaction1());
                } else {
                    mLessonImage.setImageBitmap(createBitmap(conversation1.getReaction2Image(), conversation1.getReaction2ResId()));
                    mLessonText.setText(conversation1.getReaction2());
                }
                break;
            case 4:
                mLessonImage.setImageBitmap(createBitmap(conversation2.getQuestionImage(), conversation2.getQuestionResId()));
                mLessonText.setText(conversation2.getQuestion());
                break;
            case 5:
                AnswerDialogFragment.newInstance(conversation2.getAnswer1(), conversation2.getAnswer2())
                        .show(getSupportFragmentManager(), "question");
                break;
            case 6:
                switch (mAnswer) {
                    case 1:
                        mLessonImage.setImageBitmap(createBitmap(conversation2.getReaction1Image(), conversation2.getReaction1ResId()));
                        mLessonText.setText(conversation2.getReaction1());
                        break;
                    default:
                        mLessonImage.setImageBitmap(createBitmap(conversation2.getReaction2Image(), conversation2.getReaction2ResId()));
                        mLessonText.setText(conversation2.getReaction2());
                        break;
                }
                break;
            case 7:
                mLessonText.setText(mLesson.getLastMessage());
                mLessonImage.setImageBitmap(createBitmap(mLesson.getLastMessageImage(), mLesson.getLastMessageResId()));
                break;
            case 8:
                switch (mScore) {
                    case 0:
                        mLessonImage.setImageBitmap(createBitmap(result0.getNarrationImage(), result0.getNarrationResId()));
                        mLessonText.setText(result0.getNarration());
                        break;
                    case 1:
                        mLessonImage.setImageBitmap(createBitmap(result1.getNarrationImage(), result1.getNarrationResId()));
                        mLessonText.setText(result1.getNarration());
                        break;
                    case 2:
                        mLessonImage.setImageBitmap(createBitmap(result2.getNarrationImage(), result2.getNarrationResId()));
                        mLessonText.setText(result2.getNarration());
                        break;
                    default:
                        mLessonImage.setImageBitmap(createBitmap(result3.getNarrationImage(), result3.getNarrationResId()));
                        mLessonText.setText(result3.getNarration());
                        break;
                }
                break;
            default:
                finish();
                break;
        }
    }

    private Bitmap createBitmap(byte[] data, int resId) {
        if (data != null) {
            return BitmapFactory.decodeByteArray(data, 0, data.length);
        } else {
            return BitmapFactory.decodeResource(getResources(), resId);
        }
    }

    @Override
    public void onChoice(int answer) {
        Log.d(TAG, "mode = " + mMode + ", answer = " + answer);
        switch (mMode) {
            case 2:
                mScore += (answer == 1 ? 1 : 0);
                break;
            case 5:
                mScore += (answer == 1 ? 0 : 2);
                break;
        }
        mAnswer = answer;
        mMode++;
        changeMode();
    }

    private class TextClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            mMode++;
            changeMode();
        }
    }
}
