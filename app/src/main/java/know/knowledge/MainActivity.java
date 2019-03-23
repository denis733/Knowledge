package know.knowledge;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ArrayList<Video> videoArrayList;
    VideoAdapter adapter;

    //Создаем новый объект и вносим в него данные для приложения
    Video[] videos = new Video[] {
            new Video("Создание 2D платформера на Unity 5",
                    "В этом видео уроке мы с вами создадим 2D платформер на игровом движке Unity 5 и сделаем это за 25 минут! Мы создадим проект, создадим игрока и научим его ходить, прыгать, а также появляться на старте в случае проигрыша.",
                    "Гоша Дударь",
                    363193,
                    "https://www.nintenderos.com/wp-content/uploads/2016/01/Unity-1024x572.jpg",
                    "Nak2AOrieTA"),
            new Video("Как разработать приложение на Android",
                    "Руководитель команды разработки «Эвотор» Илья Линник покажет и расскажет, как разработать приложение на Android, а также: 1. Про создание нового проекта: обои рабочего стола. 2. Про систему сборки проекта Gradle: настраиваем и добавляем зависимости. 3. Про манифест: разрешения и точки входа в приложение. 4. Про работу с сетью: RxJava2 + Retrofit + GSON. 5. Про эмулятор: запускаем Android на компьютере, ADB Shell. 6. Про UI: лейауты, адаптеры. 7. Про загрузку картинок: Picasso. 8. Про применение загруженных обоев через Android API. 9. Что еще можно сделать.",
                    "Skillbox - Программирование",
                    2260,
                    "https://expertology.ru/upload/medialibrary/e93/Skillbox.jpg",
                    "1v1HXSwihR4"),
            new Video("Удаленная разработка программного обеспечения",
                    "На этом вебинаре вы познакомитесь с основными принципами, преимуществами и недостатками удаленной разработки программного обеспечения. Мы рассмотрим техники повышения эффективности удаленной разработки. Познакомимся с основными инструментами удаленного разработчика. ",
                    "ITVDN",
                    6333,
                    "https://pimg.mycdn.me/getImage?disableStub=true&type=VIDEO_S_720&url=http%3A%2F%2Fi.ytimg.com%2Fvi%2FhzvQNfxEGWE%2F0.jpg&signatureToken=LlcxKSGEEV3ypeXlEGUvwQ",
                    "hzvQNfxEGWE"),
            new Video("Как стать WEB программистом? - Ответ профессионала",
                    "Вы хотите стать WEB программистом? Это возможно, слушайте от профессионала как.",
                    "Хауди Хо™ - Просто о мире IT!",
                    233586,
                    "https://i.ytimg.com/vi/k33IANaVS_s/maxresdefault.jpg",
                    "k33IANaVS_s"),
            new Video("Любимые языки программирования в Яндексе",
                    "В Яндексе работают сотни программистов, которые пишут на разных языках программирования. А на каких-то — не пишут. Почему разработчики выбирают C++ или Python? Чем их очаровывает Haskell? За что любят Perl? Именно об этом рассказывают сотрудники Яндекса в этом видео.",
                    "Яндекс",
                    522373,
                    "https://cdn.slidesharecdn.com/ss_thumbnails/random-150605193652-lva1-app6891-thumbnail-4.jpg?cb=1433533548",
                    "sSYRCfi3Sho"),
            new Video("Непрерывный рефакторинг и функциональное программирование",
                    "В настоящее время существуют люди и даже целые компании, которые рассматривают код как некий актив или священную корову. То, к чему лучше не прикасаться, пока оно работает. Я считаю такой подход в корне неверным. Код — лишь инструмент в достижении целей компании. Активом является непосредственная функциональность приложения, следовательно скорость доставки и качество этой функциональности — основная цель программирования. В докладе я расскажу, как перестать бояться и начать рефакторить, как функциональная парадигма может в этом помочь, и почему вам не нужна Scala.",
                    "codefestru",
                    269,
                    "https://i.ytimg.com/vi/hTQxmwbqQEQ/maxresdefault.jpg",
                    "hTQxmwbqQEQ"),
    };

    //Связываем данные
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setIcon(R.drawable.ic_action_logo);
            actionBar.setTitle("    " + actionBar.getTitle());
        }

        videoArrayList = new ArrayList<>(Arrays.asList(videos));
        adapter = new VideoAdapter(this, videoArrayList);

        ListView movieListView = (ListView) findViewById(R.id.movieListView);
        movieListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        SearchView searchView = searchItem.getActionView() as SearchView;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("Поиск", newText);

                videoArrayList.clear();

                if (newText.equals("")) {
                    videoArrayList.addAll(Arrays.asList(videos));
                } else {
                    for (Video video : videos) {
                        if (video.getName().toLowerCase().contains(newText.toLowerCase())) {
                            videoArrayList.add(video);
                        }
                    }
                }

                adapter.notifyDataSetChanged();

                return false;
            }
        });
            return super.onCreateOptionsMenu(menu);
    }

}
