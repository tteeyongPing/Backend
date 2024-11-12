package dgu.cse.newsee.repository;

import dgu.cse.newsee.domain.enums.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserCategoryRepositoryImpl implements UserCategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserCategoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Category> findCategoriesByUserId(Long userId) {
        String sql = "SELECT category FROM user_category WHERE user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> Category.fromName(rs.getString("category")));
    }

    @Override
    public void updateUserCategories(Long userId, List<Category> categories) {
        String deleteSql = "DELETE FROM user_category WHERE user_id = ?";
        jdbcTemplate.update(deleteSql, userId);

        String insertSql = "INSERT INTO user_category (user_id, category_id) VALUES (?, ?)";
        for (Category category : categories) {
            jdbcTemplate.update(insertSql, userId, category.getId());
        }
    }
}
