package com.thebund1st.liyang.adapter.jdbc;

import com.thebund1st.liyang.application.ExpiringDelayJobQuery;
import com.thebund1st.liyang.domain.model.DelayJob;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static com.thebund1st.liyang.domain.model.DelayJob.Status.PENDING;

@RequiredArgsConstructor
public class JdbcExpiringDelayJobQuery implements ExpiringDelayJobQuery {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Page<DelayJob.Identifier> findBy(Long expireBeforeThis, Pageable pageable) {
        long totalElements = jdbcTemplate.queryForObject("SELECT COUNT(ID) AS TOTAL_ELEMENTS FROM DELAY_JOB " +
                        "WHERE EXPIRES <= ? AND STATUS = ? ",
                new Object[]{expireBeforeThis, PENDING.getValue()},
                (rs, rowNum) -> rs.getLong("TOTAL_ELEMENTS"));
        List<DelayJob.Identifier> content = jdbcTemplate.query("SELECT ID FROM DELAY_JOB " +
                        "WHERE EXPIRES <= ? AND STATUS = ? " +
                        "ORDER BY CREATED_AT ASC " +
                        "LIMIT ?, ?",
                new Object[]{
                        expireBeforeThis,
                        PENDING.getValue(),
                        pageable.getOffset(),
                        pageable.getPageSize()
                },
                (rs, rowNum) -> new DelayJob.Identifier(rs.getString("ID")));
        return new PageImpl<>(content, pageable, totalElements);
    }
}
