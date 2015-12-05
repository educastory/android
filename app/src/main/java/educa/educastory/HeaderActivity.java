package educa.educastory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    private ListView mHeaderList;

    @Icicle
    ArrayList<Header> mHeaders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        mHeaderList = (ListView) findViewById(R.id.header_list);
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
                }
            });
        } else {
            Icepick.restoreInstanceState(this, savedInstanceState);
            refreshListView(adapter);
        }
    }

    private void refreshListView(HeaderAdapter adapter) {
        for (Header header : mHeaders) {
            adapter.add(header);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    private class HeaderClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final Header header = (Header) parent.getItemAtPosition(position);
            final Intent intent = MainActivity.createIntent(HeaderActivity.this, header.getNo());
            final String lessonNo = Integer.toString(header.getNo());
            VolleyHelper.createInstance(HeaderActivity.this).requestLesson(lessonNo, new LessonCallback() {
                @Override
                public void execute(byte[] data) {
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
