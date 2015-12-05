package educa.educastory;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import educa.educastory.data.Lesson;

public class TitleAdapter extends ArrayAdapter<Lesson> {
    public TitleAdapter(Context context) {
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
            convertView = inflater.inflate(R.layout.lesson_item, null);
            holder = new ViewHolder();
            holder.noText = (TextView) convertView.findViewById(R.id.no_text);
            holder.titleText = (TextView) convertView.findViewById(R.id.title_text);
            holder.unloadText = (TextView) convertView.findViewById(R.id.unload_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Lesson lesson = getItem(position);
        holder.noText.setText("Lesson " + lesson.getNo());
        holder.titleText.setText(lesson.getTitle());
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean isUnloaded = !preferences.contains(Integer.toString(lesson.getNo()));
        holder.unloadText.setVisibility(isUnloaded ? View.VISIBLE : View.GONE);
        return convertView;
    }
}
