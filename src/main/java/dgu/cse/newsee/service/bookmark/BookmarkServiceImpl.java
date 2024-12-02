package dgu.cse.newsee.service.bookmark;

import dgu.cse.newsee.apiPayload.exception.BookmarkException;
import dgu.cse.newsee.app.dto.BookmarkDto;
import dgu.cse.newsee.domain.entity.Bookmark;
import dgu.cse.newsee.domain.entity.News;
import dgu.cse.newsee.domain.entity.User;
import dgu.cse.newsee.repository.BookmarkQueryRepository;
import dgu.cse.newsee.repository.BookmarkRepository;
import dgu.cse.newsee.repository.NewsQueryRepository;
import dgu.cse.newsee.service.news.NewsService;
import dgu.cse.newsee.service.user.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final BookmarkQueryRepository bookmarkQueryRepository;
    private final NewsQueryRepository newsQueryRepository;
    private final NewsService newsService;
    private final UserAccountService userAccountService;

    @Override
    public List<News> getBookmarkList(Long userId) {
        List<News> newsList = newsQueryRepository.findNewsListByBookmark(userId);
        if(newsList == null) throw new BookmarkException.BookmarkNonExistsException("북마크에 저장된 뉴스가 없습니다.");
        return newsList;
    }

    @Override
    public void addNews(Long userId, List<BookmarkDto.BookmarkRequestDto> dtoList) {
        List<Bookmark> bookmarks = dtoList.stream()
                .map(dto -> {
                    Long newsId = dto.getNewsId();
                    News news = newsService.getNewsById(newsId);
                    Optional<User> user = userAccountService.findById(userId);
                    return Bookmark.createBookmark(user.get(), news);
                })
                .collect(Collectors.toList());
        bookmarkRepository.saveAll(bookmarks);
    }

    @Override
    public void deleteNews(Long userId, List<BookmarkDto.BookmarkRequestDto> dtoList) {
        List<Long> newsIds = dtoList.stream()
                .map(BookmarkDto.BookmarkRequestDto::getNewsId)
                .collect(Collectors.toList());

        List<Bookmark> bookmarksToDelete = bookmarkRepository.findAllByUserIdAndNewsIdIn(userId, newsIds);

        if (bookmarksToDelete.isEmpty()) {
            throw new BookmarkException.BookmarkNonExistsException("삭제할 북마크가 존재하지 않습니다.");
        }
        bookmarkRepository.deleteAll(bookmarksToDelete);
    }

    @Override
    public boolean checkSubscribe(Long userId, Long newsId) {
        return bookmarkQueryRepository.existsByUserIdAndNewsId(userId, newsId);
    }
}
