package educa.educastory;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

public class LessonRequest extends Request<byte[]> {
    private Response.Listener<byte[]> mListener;

    public LessonRequest(String url, Response.Listener<byte[]> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        mListener = listener;
    }

    @Override
    protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {
        Cache.Entry entry = HttpHeaderParser.parseCacheHeaders(response);
        return Response.success(response.data, entry);
    }

    @Override
    protected void deliverResponse(byte[] data) {
        mListener.onResponse(data);
    }
}
