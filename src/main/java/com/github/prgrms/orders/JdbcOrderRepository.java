package com.github.prgrms.orders;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.github.prgrms.utils.DateTimeUtils.dateTimeOf;
import static java.util.Optional.ofNullable;

@Repository
public class JdbcOrderRepository implements OrderRepository {


    static RowMapper<UOrder> mapper = (rs, rowNum) ->
            new UOrder.Builder()
                    .seq(rs.getLong("seq"))
                    .userSeq(rs.getLong("user_seq"))
                    .productSeq(rs.getLong("product_seq"))
                    .reviewSeq(rs.getLong("review_seq"))
                    .state(rs.getString("state"))
                    .requestMsg(rs.getString("request_msg"))
                    .rejectMsg(rs.getString("reject_msg"))
                    .rejectedAt(dateTimeOf(rs.getTimestamp("rejected_at")))
                    .createAt(dateTimeOf(rs.getTimestamp("create_at")))
                    .build();
    private final JdbcTemplate jdbcTemplate;

    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void update(UOrder uOrder) {
        if (uOrder.getReviewSeq() == 0) {
            uOrder.setReviewSeq(null);
        }
        jdbcTemplate.update("UPDATE orders SET review_seq=?,state=?,request_msg=?,reject_msg=?,completed_at=?,rejected_at=? WHERE seq=? ",
                uOrder.getReviewSeq(), uOrder.getState(), uOrder.getRequestMsg(), uOrder.getRejectMsg(), uOrder.getCompletedAt(), uOrder.getRejectedAt(), uOrder.getSeq());

    }

    @Override
    public Optional<UOrder> findById(Long id) {
        List<UOrder> results = jdbcTemplate.query(
                "SELECT * FROM orders WHERE seq=?",
                mapper,
                id
        );
        return ofNullable(results.isEmpty() ? null : results.get(0));
    }

    @Override
    public List<UOrder> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM orders ORDER BY seq DESC",
                mapper
        );
    }
}