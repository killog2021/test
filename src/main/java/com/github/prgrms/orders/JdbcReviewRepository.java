package com.github.prgrms.reivews;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.github.prgrms.utils.DateTimeUtils.dateTimeOf;
import static java.util.Optional.ofNullable;

@Repository
public class JdbcReivewRepository implements ReivewRepository {

  private final JdbcTemplate jdbcTemplate;

  public JdbcReivewRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Integer save(Reivew review) {
    Integer seq= jdbcTemplate.update(
      "INSERT INTO reviews( user_seq, product_seq, content, create_at)  VALUES(?,?,?,?) ",
    review.getUserSeq(),review.getProductSeq(),review.getContent(),review.getCreateAt());
    return seq;
  }



  static RowMapper<Reivew> mapper = (rs, rowNum) ->
    new Reivew.Builder()
      .seq(rs.getLong("seq"))
      .userSeq(rs.getLong("user_seq"))
      .productSeq(rs.getLong("product_seq"))
      .content(rs.getString("content"))
      .createAt(dateTimeOf(rs.getTimestamp("create_at")))
      .build();

}