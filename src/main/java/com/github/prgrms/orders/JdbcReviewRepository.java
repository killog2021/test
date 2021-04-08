package com.github.prgrms.orders;

import com.github.prgrms.orders.Review;
import com.github.prgrms.orders.ReviewRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.github.prgrms.utils.DateTimeUtils.dateTimeOf;
import static java.util.Optional.ofNullable;

@Repository
public class JdbcReviewRepository implements ReviewRepository {

  private final JdbcTemplate jdbcTemplate;

  public JdbcReviewRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void save(Review review) {
    Integer seq= jdbcTemplate.update(
      "INSERT INTO reviews( user_seq, product_seq, content, create_at)  VALUES(?,?,?,?) ",
    review.getUserSeq(),review.getProductSeq(),review.getContent(),review.getCreateAt());
  }



  static RowMapper<Review> mapper = (rs, rowNum) ->
    new Review.Builder()
      .seq(rs.getLong("seq"))
      .userSeq(rs.getLong("user_seq"))
      .productSeq(rs.getLong("product_seq"))
      .content(rs.getString("content"))
      .createAt(dateTimeOf(rs.getTimestamp("create_at")))
      .build();

}