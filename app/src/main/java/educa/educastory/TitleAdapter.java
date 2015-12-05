package educa.educastory;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import educa.educastory.data.Title;

public class TitleAdapter extends ArrayAdapter<Title> {
    public TitleAdapter(Context context) {
        super(context, 0);
    }

    static class ViewHolder {
        TextView noText;
        TextView textText;
        TextView unloadText;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.title_item, null);
            holder = new ViewHolder();
            holder.noText = (TextView) convertView.findViewById(R.id.no_text);
            holder.textText = (TextView) convertView.findViewById(R.id.text_text);
            holder.unloadText = (TextView) convertView.findViewById(R.id.unload_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Title title = getItem(position);
        holder.noText.setText(getContext().getString(R.string.lesson_title, title.getNo()));
        holder.textText.setText(title.getText());
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean isUnloaded = !preferences.contains(Integer.toString(title.getNo()));
        holder.unloadText.setVisibility(isUnloaded ? View.VISIBLE : View.GONE);
        return convertView;
    }
}
