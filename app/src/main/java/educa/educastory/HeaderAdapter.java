package educa.educastory;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import educa.educastory.data.Header;

public class HeaderAdapter extends ArrayAdapter<Header> {
    public HeaderAdapter(Context context) {
        super(context, 0);
    }

    static class ViewHolder {
        TextView noText;
        TextView titleText;
        TextView unloadText;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.header_item, null);
            holder = new ViewHolder();
            holder.noText = (TextView) convertView.findViewById(R.id.no_text);
            holder.titleText = (TextView) convertView.findViewById(R.id.title_text);
            holder.unloadText = (TextView) convertView.findViewById(R.id.unload_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Header header = getItem(position);
        holder.noText.setText(getContext().getString(R.string.lesson_no, header.getNo()));
        holder.titleText.setText(header.getTitle());
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean isUnloaded = !preferences.contains(Integer.toString(header.getNo()));
        holder.unloadText.setVisibility(isUnloaded ? View.VISIBLE : View.GONE);
        return convertView;
    }
}
