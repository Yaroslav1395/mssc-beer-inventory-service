package sakhno.sfg.beer.inventory.service.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;


import java.sql.Timestamp;
import java.util.UUID;

/**
 *
 */
@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @Id
    @UuidGenerator
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false )
    private UUID id;

    @Version
    private Long version;

    @CreationTimestamp
    @Column(updatable = false, name = "created_date")
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;

    public boolean isNew() {
        return this.id == null;
    }

    public BaseEntity(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate) {
        this.id = id;
        this.version = version;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}
