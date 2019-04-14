package in.a_comic.quizapp.current_affairs_quiz;

public class CurrentAffairModel {
    private String image,title,date,question;

    public CurrentAffairModel(String image, String title, String date, String question) {
        this.image = image;
        this.title = title;
        this.date = date;
        this.question = question;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getQuestion() {
        return question;
    }
}
