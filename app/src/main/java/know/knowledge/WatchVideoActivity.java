package know.knowledge;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

public class WatchVideoActivity extends AppCompatActivity implements YouTubePlayer.OnInitializedListener {

    public String urlPath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Задаем внешний вид интерфейса
        setContentView(R.layout.watch_tutorial);
        //Добавляем размещение элементов
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        YouTubePlayerSupportFragment frag =
                (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.youtubePlay);
        frag.initialize(YouTubeConfig.getApiKey(), this);

        final Intent intent = getIntent();
        //Получаем нужные данные
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        int numberOfViews = intent.getIntExtra("numberOfViews", 0);
        String director = intent.getStringExtra("director");
        String image = intent.getStringExtra("image");

        if (actionBar != null) {
            actionBar.setTitle(name);
        }

        TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
        TextView descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);

        nameTextView.setText(name);
        descriptionTextView.setText(description);

        this.urlPath = intent.getStringExtra("url");
    }

    public String getUrlPath() {
        return urlPath;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(getUrlPath());
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
