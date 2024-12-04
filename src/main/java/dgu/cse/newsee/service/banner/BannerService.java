package dgu.cse.newsee.service.banner;

import dgu.cse.newsee.app.dto.NewsDto;
import dgu.cse.newsee.domain.enums.Category;

import java.util.List;

public interface BannerService {
    NewsDto.NewsAlarmResponseDto getRandomAlarmNews(Long userId, List<Category> userCategories);
}
