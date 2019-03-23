package know.knowledge;

//Класс для записи и вывода данных
class Video {
    private String name, description, director, image, url;
    private int numberOfViews;

    Video(String name, String description, String director, int numberOfViews, String image, String url) {
        super();
        this.name = name;
        this.description = description;
        this.director = director;
        this.numberOfViews = numberOfViews;
        this.image = image;
        this.url = url;
    }

    String getName() {
        return this.name;
    }

    String getDescription() {
        return this.description;
    }

    String getDirector() {
        return this.director;
    }

    int getNumberOfViews() {
        return this.numberOfViews;
    }

    String getImage() {
        return this.image;
    }

    String getUrl() {
        return this.url;
    }
}
