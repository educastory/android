package educa.educastory;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import educa.educastory.data.Header;

public class VolleyHelper {
    private static final String TAG = VolleyHelper.class.getName();
    private static final String HEADER = "HEADER";
    private static final Object LOCK = new Object();
    private static RequestQueue sQueue;

    private Context mContext;

    public static VolleyHelper createInstance(Context context) {
        synchronized (LOCK) {
            if (sQueue == null) {
                sQueue = Volley.newRequestQueue(context);
            }
        }
        return new VolleyHelper(context);
    }

    private VolleyHelper(Context context) {
        mContext = context;
    }

    public void requestHeaders(final HeadersCallback callback) {
        if (!Decision.isConnected(mContext)) {
            requestHeadersFailure(callback, null);
            return;
        }
        final String url = Const.BASE_URL + "/lessons.json";
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(final JSONArray response) {
                Log.d(TAG, "response = " + response);
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(HEADER, response.toString());
                editor.commit();
                requestHeadersFinish(callback, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestHeadersFailure(callback, error);
            }
        });
        request.setShouldCache(false);
        sQueue.add(request);
    }

    private void requestHeadersFailure(HeadersCallback callback, VolleyError error) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String json = preferences.getString(HEADER, "");
        try {
            JSONArray response = new JSONArray(json);
            requestHeadersFinish(callback, response);
            Log.w(TAG, "volley error", error);
        } catch (JSONException e) {
            Log.e(TAG, "volley error", error);
            callback.execute(new ArrayList<Header>());
        }
    }

    private void requestHeadersFinish(HeadersCallback callback, JSONArray response) {
        ArrayList<Header> headers = new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            try {
                /* think about last extra comma */
                if (response.isNull(i)) {
                    continue;
                }
                JSONObject json = response.getJSONObject(i);
                Header header = new Header();
                header.setNo(json.getInt("no"));
                header.setTitle(json.getString("title"));
                Log.d(TAG, "add header " + header.getTitle());
                headers.add(header);
            } catch (JSONException e) {
                Log.w(TAG, "json parse error", e);
                continue;
            }
        }
        callback.execute(headers);
    }

    public void requestLesson(final String lessonNo, final LessonCallback callback) {
        if (!Decision.isConnected(mContext)) {
            requestLessonFailure(callback, lessonNo, null);
            return;
        }

        String url = Const.BASE_URL + "/" + lessonNo + ".zip";
        LessonRequest request = new LessonRequest(url, new Response.Listener<byte[]>() {
            @Override
            public void onResponse(byte[] response) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(lessonNo, Base64.encodeToString(response, Base64.DEFAULT));
                editor.commit();
                requestLessonFinish(callback, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestLessonFailure(callback, lessonNo, error);
            }
        });
        request.setShouldCache(false);
        sQueue.add(request);
    }

    private void requestLessonFailure(LessonCallback callback, String lessonNo, VolleyError error) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String base64Text = preferences.getString(lessonNo, "");
        byte[] data = Base64.decode(base64Text, Base64.DEFAULT);
        requestLessonFinish(callback, data);
        Log.w(TAG, "volley error", error);
    }

    private void requestLessonFinish(LessonCallback callback, byte[] response) {
        callback.execute(response);
    }
}
