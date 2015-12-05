package educa.educastory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import educa.educastory.data.Lesson;
import icepick.Icepick;
import icepick.Icicle;

public class TitleActivity extends AppCompatActivity {
    @Icicle
    ArrayList<Lesson> mLessons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        ListView lessonList = (ListView) findViewById(R.id.lesson_list);
        final TitleAdapter adapter = new TitleAdapter(this);
        lessonList.setAdapter(adapter);
        lessonList.setOnItemClickListener(new LessonClickListener());

        if (savedInstanceState == null) {
            VolleyHelper helper = VolleyHelper.createInstance(this);
            helper.requestTitles(new LessonsCallback() {
                @Override
                public void execute(List<Lesson> lessons) {
                    mLessons = new ArrayList<>(lessons);
                    refreshListView(adapter);
                }
            });
        } else {
            Icepick.restoreInstanceState(this, savedInstanceState);
            refreshListView(adapter);
        }
    }

    private void refreshListView(TitleAdapter adapter) {
        for(Lesson lesson : mLessons) {
            adapter.add(lesson);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    private class LessonClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final Lesson lesson = (Lesson) parent.getItemAtPosition(position);
            final Intent intent = MainActivity.createIntent(TitleActivity.this, lesson.getNo());
            final String lessonNo = Integer.toString(lesson.getNo());
            final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(TitleActivity.this);
            if (preferences.contains(lessonNo)) {
                startActivity(intent);
            } else {
                VolleyHelper.createInstance(TitleActivity.this).requestLesson(lesson.getNo(), new LessonCallback() {
                    @Override
                    public void execute(byte[] data) {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(lessonNo, Base64.encodeToString(data, Base64.DEFAULT));
                        editor.commit();
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
