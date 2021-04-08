package com.github.prgrms.orders.reviews;

import com.github.prgrms.orders.reviews.Review;
import com.github.prgrms.orders.reviews.ReviewRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import static com.github.prgrms.utils.DateTimeUtils.dateTimeOf;

@Repository
public class JdbcReviewRepository implements ReviewRepository {


    static RowMapper<Review> mapper = (rs, rowNum) ->
            new Review.Builder()
                    .seq(rs.getLong("seq"))
                    .userSeq(rs.getLong("user_seq"))
                    .productSeq(rs.getLong("product_seq"))
                    .content(rs.getString("content"))
                    .createAt(dateTimeOf(rs.getTimestamp("create_at")))
                    .build();
    private final JdbcTemplate jdbcTemplate;

    public JdbcReviewRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Review review) {
        jdbcTemplate.update(
                "INSERT INTO reviews(seq,user_seq,product_seq,content) VALUES (null,?,?,?) ",
                review.getUserSeq(), review.getProductSeq(), review.getContent());
    }
}