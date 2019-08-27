package ro.demo.asignment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractAuditable {
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @PrePersist
    public void addCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }
}
