package educa.educastory;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    private static final String TAG = MainActivity.class.getName();
    private static final int RC_REACTION_1 = 1001;
    private static final int RC_REACTION_2 = 1002;

    String questionStr = "";
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("onCreate","onCreate");
        count = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionStr = getString(R.string.Question1);
        Log.e("questionStr", questionStr);
        TextView questionText = (TextView) findViewById(R.id.QuestionText);


        switch (count) {
            case 1:
                Log.e("シナリオ","1");
                break;
            case 2:
                Log.e("シナリオ","2");
                questionText.setText("シナリオ2の質問");
                break;
            case 3:
                Log.e("シナリオ","3");
                break;
            case 4:
                Log.e("シナリオ","4");
                break;
        }//end switch

        //ボタンを押した時の処理
        Button answerBtn1 = (Button) findViewById(R.id.AnswerButton1);
        answerBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "AnswerButton1", Toast.LENGTH_SHORT).show();
                Intent reactionIntent = new Intent(MainActivity.this, ReactionActivity.class);
                reactionIntent.putExtra("count", count);
                //startActivity(reactionIntent);
                startActivityForResult(reactionIntent, RC_REACTION_1);
            }
        });

        //ボタンを押した時の処理
        Button answerBtn2 = (Button) findViewById(R.id.AnswerButton2);
        answerBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "AnswerButton2", Toast.LENGTH_SHORT).show();
                Intent reactionIntent = new Intent(MainActivity.this, ReactionActivity.class);
                startActivityForResult(reactionIntent, RC_REACTION_2);
            }
        });
    }//end onCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCount, Intent data){
        super.onActivityResult(count, resultCount, data);
        Bundle bundle = data.getExtras();
        count++;
        switch (requestCode){
            case RC_REACTION_1:
                Log.i(TAG, "from " + RC_REACTION_1);
                break;
            case RC_REACTION_2:
                Log.i(TAG, "from " + RC_REACTION_2);
                break;
        }
        Log.e("result", "resultCount:" + count);
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
}
