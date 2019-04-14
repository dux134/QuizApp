package in.a_comic.quizapp.exam_list_util;

public class ExamModel {
    private String image,title;

    public ExamModel(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
