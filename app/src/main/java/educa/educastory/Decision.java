package educa.educastory;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by kenji on 15/12/05.
 */
public class Decision {
    // オンライン、オフライン判定
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null) {
            return cm.getActiveNetworkInfo().isConnected();
        }
        return false;
    }
}