package educa.educastory;

import android.content.Context;
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

import educa.educastory.data.Lesson;

public class VolleyHelper {
    private static final String TAG = VolleyHelper.class.getName();
    private static RequestQueue sQueue;
    private static final Object sLock = new Object();

    public static VolleyHelper createInstance(Context context) {
        synchronized (sLock) {
            if (sQueue == null) {
                sQueue = Volley.newRequestQueue(context);
            }
        }
        return new VolleyHelper();
    }

    private VolleyHelper() {
        /* nop */
    }

    public void requestTitles(final LessonsCallback callback) {
        String url = Const.BASE_URL + "/lessons.json";
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(final JSONArray response) {
                Log.d(TAG, "response = " + response);
                ArrayList<Lesson> lessons = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        /* think about last extra comma */
                        if (response.get(i) == null) {
                            continue;
                        }
                        JSONObject json = response.getJSONObject(i);
                        Lesson lesson = new Lesson();
                        lesson.setNo(json.getInt("no"));
                        lesson.setTitle(json.getString("title"));
                        Log.d(TAG, "add lesson " + lesson.getTitle());
                        lessons.add(lesson);
                    } catch (JSONException e) {
                        Log.w(TAG, "json parse error", e);
                        continue;
                    }
                }
                callback.execute(lessons);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "volley error", error);
            }
        });
        request.setShouldCache(false);
        sQueue.add(request);
    }

    public void requestLesson(int lessonNo, final LessonCallback callback) {
        String url = Const.BASE_URL + "/" + lessonNo + ".zip";
        LessonRequest request = new LessonRequest(url, new Response.Listener<byte[]>() {
            @Override
            public void onResponse(byte[] response) {
                callback.execute(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "volley error", error);
            }
        });
        request.setShouldCache(false);
        sQueue.add(request);
    }
}
