package dgu.cse.newsee.service.memo;

import dgu.cse.newsee.apiPayload.exception.MemoException;
import dgu.cse.newsee.domain.entity.News;
import dgu.cse.newsee.domain.entity.NewsMemo;
import dgu.cse.newsee.domain.entity.User;
import dgu.cse.newsee.repository.MemoRepository;
import dgu.cse.newsee.repository.NewsRepository;
import dgu.cse.newsee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemoServiceImpl implements MemoService {

    private final MemoRepository memoRepository;
    private final UserRepository userRepository;
    private final NewsRepository newsRepository;

    @Override
    public void updateMemo(Long userId, Long newsId, String memoContent) {
        NewsMemo memo = memoRepository.findByUserIdAndNewsId(userId, newsId)
                .orElseGet(() -> {
                    Optional<User> user = userRepository.findById(userId);
                    Optional<News> news = newsRepository.findById(newsId);
                    return NewsMemo.builder()
                            .user(user.get())
                            .news(news.get())
                            .memo(memoContent)
                            .build();
                });
        memo.setMemo(memoContent);
        memoRepository.save(memo);
    }

    @Override
    public void deleteMemo(Long userId, Long newsId) {
        Optional<NewsMemo> memo = memoRepository.findByUserIdAndNewsId(userId, newsId);
        if(memo.isEmpty()) throw new MemoException.MemoNotFoundException("메모가 존재하지 않습니다.");
        memoRepository.delete(memo.get());
    }
}