package dgu.cse.newsee.service.bookmark;

import dgu.cse.newsee.app.dto.BookmarkDto;
import dgu.cse.newsee.domain.entity.News;

import java.util.List;

public interface BookmarkService {
    List<News> getBookmarkList(Long userId);

    void addNews(Long userId, List<BookmarkDto.BookmarkRequestDto> dtoList);

    void deleteNews(Long userId, List<BookmarkDto.BookmarkRequestDto> dtoList);
}
