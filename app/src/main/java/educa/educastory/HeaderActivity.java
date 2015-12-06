package educa.educastory;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import educa.educastory.data.Header;
import icepick.Icepick;
import icepick.Icicle;

public class HeaderActivity extends AppCompatActivity {
    private static final int RC_HEADER = 1001;
    private static final String NEED_RELOAD = "NEED_RELOAD";

    private ListView mHeaderList;

    @Icicle
    ArrayList<Header> mHeaders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);

        mHeaderList = (ListView) findViewById(R.id.header_list);
        mHeaderList.setEmptyView(findViewById(R.id.empty_text));
        final HeaderAdapter adapter = new HeaderAdapter(this);
        mHeaderList.setAdapter(adapter);
        mHeaderList.setOnItemClickListener(new HeaderClickListener());

        if (savedInstanceState == null) {
            VolleyHelper helper = VolleyHelper.createInstance(this);
            helper.requestHeaders(new HeadersCallback() {
                @Override
                public void execute(List<Header> headers) {
                    mHeaders = new ArrayList<>(headers);
                    refreshListView(adapter);
                    reloadLessonsIfNeed();
                }
            });
        } else {
            Icepick.restoreInstanceState(this, savedInstanceState);
            refreshListView(adapter);
            reloadLessonsIfNeed();
        }
    }

    private void refreshListView(HeaderAdapter adapter) {
        for (Header header : mHeaders) {
            adapter.add(header);
        }
        adapter.notifyDataSetChanged();
    }

    private void reloadLessonsIfNeed() {
        if (!Decision.isConnected(this)) {
            return;
        }
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        if (!preferences.getBoolean(NEED_RELOAD, true)) {
            return;
        }
        new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(getString(R.string.confirm_load_lessons))
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                reloadLessons();
                            }
                        })
                        .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                /* NOP */
                            }
                        });
                return builder.create();
            }
        }.show(getSupportFragmentManager(), null);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(NEED_RELOAD, false);
        editor.commit();
    }

    private void reloadLessons() {
        VolleyHelper helper = VolleyHelper.createInstance(this);
        helper.requestHeaders(new HeadersCallback() {
            @Override
            public void execute(List<Header> headers) {
                for(Header header : headers) {
                    reloadLesson(header);
                }
            }
        });
    }

    private void reloadLesson(Header header) {
        String lessonNo = Integer.toString(header.getNo());
        VolleyHelper.createInstance(HeaderActivity.this).requestLesson(lessonNo, new LessonCallback() {
            @Override
            public void execute(byte[] data) {
                HeaderAdapter adapter = (HeaderAdapter) mHeaderList.getAdapter();
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.header, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_reload_lessons:
                reloadLessons();
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    private class HeaderClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Header header = (Header) parent.getItemAtPosition(position);
            String lessonNo = Integer.toString(header.getNo());
            VolleyHelper.createInstance(HeaderActivity.this).requestLesson(lessonNo, new LessonCallback() {
                @Override
                public void execute(byte[] data) {
                    Intent intent = MainActivity.createIntent(HeaderActivity.this, data);
                    startActivityForResult(intent, RC_HEADER);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_HEADER) {
            HeaderAdapter adapter = (HeaderAdapter) mHeaderList.getAdapter();
            adapter.notifyDataSetChanged();
        }
    }
}
