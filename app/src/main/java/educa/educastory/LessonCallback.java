package educa.educastory;

import java.util.List;

import educa.educastory.data.Lesson;

/**
 * Created by kenji on 15/10/04.
 */
public interface LessonCallback {
    void execute(List<Lesson> lessons);
}
