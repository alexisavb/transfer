package com.baufest.transfer.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

@MappedSuperclass
@Getter
@Setter
public class AbstractRecordMetadataModel implements Serializable {

    private static final long serialVersionUID = 1053842443021848949L;

    @NotNull
    @Column(name = "created", columnDefinition = "TIMESTAMP")
    private LocalDateTime created;
    @Column(name = "removed", columnDefinition = "TIMESTAMP")
    private LocalDateTime removed;
    @Column(name = "updated", columnDefinition = "TIMESTAMP")
    private LocalDateTime updated;

    @PrePersist
    private void onCreated(){
        this.created = LocalDateTime.now(ZoneId.of("UTC"));;
        this.updated = this.created;
    }
    @PreUpdate
    private void onUpdated(){
        this.updated = LocalDateTime.now(ZoneId.of("UTC"));;
    }
}
