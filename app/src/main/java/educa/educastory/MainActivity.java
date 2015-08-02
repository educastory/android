package educa.educastory;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private static final String KEY_MODE = "MODE";
    private static final String KEY_SCORE = "SCORE";

    private int mMode;
    private int mScore;

    private ImageView image;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.image_view);
        text = (TextView) findViewById(R.id.text_view);
        setTitle(R.string.chapter);

        text.setOnClickListener(new TextClickListener());

        if (savedInstanceState == null) {
            mMode = 0;
            mScore = 0;
        } else {
            mMode = savedInstanceState.getInt(KEY_MODE, 0);
            mScore = savedInstanceState.getInt(KEY_SCORE, 0);
        }

        changeMode();
    }

    private void changeMode() {
        switch (mMode) {
            case 0:
                image.setImageResource(R.mipmap.scenario1);
                text.setText(getString(R.string.question1));
                break;
            case 1:
                DialogFragment dialog = AnswerDialogFragment.newInstance(R.string.answer1_1, R.string.answer1_2);
                dialog.show(getSupportFragmentManager(), "question");
                break;
            default:
                finish();
                break;
        }
    }

    private class TextClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            mMode++;
            changeMode();
        }
    }

    public static class AnswerDialogFragment extends DialogFragment {
        private AnswerDialogFragment self = this;
        private int mAnswer1;
        private int mAnswer2;

        public static DialogFragment newInstance(int answer1, int answer2) {
            AnswerDialogFragment fragment = new AnswerDialogFragment();
            fragment.mAnswer1 = answer1;
            fragment.mAnswer2 = answer2;
            return fragment;
        }

        public AnswerDialogFragment() {
            /* Don't remove this constructor */
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View content = inflater.inflate(R.layout.fragment_answer, null);
            TextView answer1 = (TextView) content.findViewById(R.id.answer1_text);
            TextView answer2 = (TextView) content.findViewById(R.id.answer2_text);
            builder.setView(content);

            answer1.setText(mAnswer1);
            answer1.setOnClickListener(new Answer1ClickListener());
            answer2.setText(mAnswer2);
            answer2.setOnClickListener(new Answer2ClickListener());

            return builder.create();
        }

        private class Answer1ClickListener implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                self.dismiss();
            }
        }

        private class Answer2ClickListener implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                self.dismiss();
            }
        }
    }
}
