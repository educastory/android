package educa.educastory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class ReactionActivity extends ActionBarActivity {

    int countBtn = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction);

        Intent getIntent = getIntent();
        int getCount = getIntent.getIntExtra("count", 1);

        Button nextBtn = (Button) findViewById(R.id.ReactionButton);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("count", countBtn++);
                data.putExtras(bundle);


                setResult(RESULT_OK, data);
                countBtn++;
                finish();

//                Intent mainIntent = new Intent(ReactionActivity.this, MainActivity.class);
//
//                startActivity(mainIntent);
            }
        });
    }
}
