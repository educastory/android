package educa.educastory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
}
