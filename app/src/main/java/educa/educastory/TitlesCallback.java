package educa.educastory;

import java.util.List;

import educa.educastory.data.Title;

/**
 * Created by kenji on 15/10/04.
 */
public interface TitlesCallback {
    void execute(List<Title> lessons);
}
