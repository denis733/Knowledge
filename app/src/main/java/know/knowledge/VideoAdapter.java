package know.knowledge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//Класс для упрощения связывания данных с элементов управдения
class VideoAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Video> videoArrayList;
    private LayoutInflater inflater;

    VideoAdapter(Activity activity, ArrayList<Video> videoArrayList) {
        this.activity = activity;
        this.videoArrayList = videoArrayList;
    }

    @Override
    public int getCount() {
        return this.videoArrayList.size();
    }

    @Override
    public Video getItem(int position) {
        return this.videoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.tutorial_list_item, null);

        TextView name = (TextView) convertView.findViewById(R.id.nameTextView);
        TextView description = (TextView) convertView.findViewById(R.id.descriptionTextView);
        TextView numberOfViews = (TextView) convertView.findViewById(R.id.numberOfViewTextView);
        TextView director = (TextView) convertView.findViewById(R.id.directorTextView);
        ImageView image = (ImageView) convertView.findViewById(R.id.imageView);

        final Video video = getItem(position);
        name.setText(video.getName());
        if (video.getDescription().length() <= 150) {
            description.setText(video.getDescription());
        } else {
            description.setText(video.getDescription().substring(0, 150) + "...");
        }
        numberOfViews.setText(video.getNumberOfViews() + " просмотров");
        director.setText(video.getDirector());

        Picasso.get().load(video.getImage()).into(image);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, WatchVideoActivity.class);
                intent.putExtra("name", video.getName());
                intent.putExtra("description", video.getDescription());
                intent.putExtra("numberOfViews", video.getNumberOfViews());
                intent.putExtra("director", video.getDirector());
                intent.putExtra("image", video.getImage());
                intent.putExtra("url", video.getUrl());

                activity.startActivity(intent);
            }
        });

        return convertView;
    }
}
