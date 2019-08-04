package com.thebund1st.liyang.adapter.jdbc;

import com.thebund1st.liyang.domain.exception.NoSuchEntityException;
import com.thebund1st.liyang.domain.model.DelayJob;
import com.thebund1st.liyang.domain.model.DelayJobRepository;
import com.thebund1st.liyang.domain.model.JobSource;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

@RequiredArgsConstructor
public class JdbcDelayJobRepository implements DelayJobRepository {

    private static final String DJ_COLUMNS = "ID, VERSION, SRC_CONTEXT, SRC_OBJ_ID, TOPIC, EXPIRES, STATUS, " +
            "CREATED_AT, LAST_MODIFIED_AT";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(DelayJob model) {
        jdbcTemplate.update(insertSql(),
                model.getId().getValue(),
                model.getVersion(),
                model.getSource().getContext(),
                model.getSource().getObjectId(),
                model.getTopic(),
                model.getExpires(),
                model.getStatus().getValue(),
                model.getCreatedAt(),
                model.getLastModifiedAt()
        );
    }

    private String insertSql() {
        return String.format("INSERT INTO DELAY_JOB(%s) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                DJ_COLUMNS);
    }

    @Override
    public Optional<DelayJob> findBy(DelayJob.Identifier id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM DELAY_JOB WHERE ID = ?",
                new Object[]{id.getValue()},
                (rs, rowNum) -> {
                    DelayJob model = new DelayJob();
                    model.setId(new DelayJob.Identifier(rs.getString("ID")));
                    model.setVersion(rs.getInt("VERSION"));
                    model.setSource(JobSource.of(rs.getString("SRC_CONTEXT"),
                            rs.getString("SRC_OBJ_ID")));
                    model.setTopic(rs.getString("TOPIC"));
                    model.setExpires(rs.getLong("EXPIRES"));
                    model.setStatus(DelayJob.Status.of(rs.getInt("STATUS")));
                    model.setCreatedAt(rs.getLong("CREATED_AT"));
                    model.setLastModifiedAt(rs.getLong("LAST_MODIFIED_AT"));
                    return model;
                })
        );
    }

    @Override
    public DelayJob mustFindBy(DelayJob.Identifier id) {
        return findBy(id).orElseThrow(() -> new NoSuchEntityException(DelayJob.class, id));
    }

    @Override
    public void update(DelayJob delayJob) {
        String id = delayJob.getId().getValue();
        int version = delayJob.getVersion();
        int rowUpdated = jdbcTemplate.update("UPDATE DELAY_JOB SET VERSION = VERSION + 1, " +
                        "STATUS = ?, " +
                        "LAST_MODIFIED_AT = ? " +
                        "WHERE ID = ? " +
                        "AND VERSION = ?",
                delayJob.getStatus().getValue(),
                delayJob.getLastModifiedAt(),
                id,
                version
        );
        if (rowUpdated != 1) {
            throw new OptimisticLockingFailureException(String.format("Conflict when updating Delay Job [%s][%d]",
                    id, version));
        }
    }
}
