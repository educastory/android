package educa.educastory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kenji on 15/08/04.
 */
public class AnswerDialogFragment extends DialogFragment {
    private static final String KEY_ANSWER1 = "ANSWER1";
    private static final String KEY_ANSWER2 = "ANSWER2";

    private AnswerDialogFragment self = this;
    private OnChoiceListener mListener;

    public interface OnChoiceListener {
        void onChoice(int answer);
    }

    public static DialogFragment newInstance(int answer1, int answer2) {
        AnswerDialogFragment fragment = new AnswerDialogFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_ANSWER1, answer1);
        args.putInt(KEY_ANSWER2, answer2);
        fragment.setArguments(args);
        return fragment;
    }

    public AnswerDialogFragment() {
        /* Don't remove this constructor */
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        int answer1 = args.getInt(KEY_ANSWER1);
        int answer2 = args.getInt(KEY_ANSWER2);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View content = inflater.inflate(R.layout.fragment_answer, null);
        TextView answer1Text = (TextView) content.findViewById(R.id.answer1_text);
        TextView answer2Text = (TextView) content.findViewById(R.id.answer2_text);
        builder.setView(content);

        answer1Text.setText(answer1);
        answer1Text.setOnClickListener(new Answer1ClickListener());
        answer2Text.setText(answer2);
        answer2Text.setOnClickListener(new Answer2ClickListener());

        this.setCancelable(false);
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (OnChoiceListener) activity;
    }

    private class Answer1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            mListener.onChoice(1);
            self.dismiss();
        }
    }

    private class Answer2ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            mListener.onChoice(2);
            self.dismiss();
        }
    }
}
