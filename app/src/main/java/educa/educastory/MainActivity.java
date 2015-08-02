package educa.educastory;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Locale;

public class MainActivity extends ActionBarActivity  implements TextToSpeech.OnInitListener{

    protected static TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

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
        if(!flg){
            CheckTTSDialogFragment newFragment = new CheckTTSDialogFragment();
            newFragment.show(getSupportFragmentManager(), "chktts");
        }
    }
    private void speakText(String text){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
        else {
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
