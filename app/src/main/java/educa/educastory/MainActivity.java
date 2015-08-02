package educa.educastory;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    protected static TextToSpeech tts;

    String questionStr = "";
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionStr = getString(R.string.Question1);
        Log.e("questionStr", questionStr);
        TextView questionText = (TextView) findViewById(R.id.QuestionText);


        switch (count) {
            case 1:
                Log.e("シナリオ", "1");
                break;
            case 2:
                Log.e("シナリオ", "2");
                questionText.setText("シナリオ2の質問");
                break;
            case 3:
                Log.e("シナリオ", "3");
                break;
            case 4:
                Log.e("シナリオ", "4");
                break;
        }//end switch

        //ボタンを押した時の処理
        Button answerBtn1 = (Button) findViewById(R.id.AnswerButton1);
        answerBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "AnswerButton1", Toast.LENGTH_SHORT).show();
                Intent reactionIntent = new Intent(MainActivity.this, ReactionActivity.class);
                startActivity(reactionIntent);
            }
        });

        //ボタンを押した時の処理
        Button answerBtn2 = (Button) findViewById(R.id.AnswerButton2);
        answerBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "AnswerButton2", Toast.LENGTH_SHORT).show();
                Intent reactionIntent = new Intent(MainActivity.this, ReactionActivity.class);
                startActivity(reactionIntent);
                finish();
            }
        });
    }//end onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInit(int status) {
//        initTts(status);
    }

    private void initTts(int status) {
        boolean flg = false;
        if (TextToSpeech.SUCCESS == status) {
            Locale locale = Locale.ENGLISH;
            if (tts.isLanguageAvailable(locale) >= TextToSpeech.LANG_AVAILABLE) {
                tts.setLanguage(locale);
                flg = true;
            } else {
                Log.d("", "Error SetLocale");
            }
        } else {
            Log.d("", "Error Init");
        }
        if (!flg) {
            CheckTTSDialogFragment newFragment = new CheckTTSDialogFragment();
            newFragment.show(getSupportFragmentManager(), "chktts");
        }
    }

    private void speakText(String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    public static class CheckTTSDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Please download English voice data.")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent installIntent = new Intent();
                            installIntent.setAction(
                                    TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                            startActivity(installIntent);
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            return builder.create();
        }
    }
}
