package dgu.cse.newsee.util;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class RandomGenerator {
    private final Random random = new Random();

    public <T> T getRandomElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("리스트가 비어있거나 null입니다.");
        }
        return list.get(random.nextInt(list.size()));
    }
}
