package com.baufest.transfer.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "account", uniqueConstraints = { @UniqueConstraint(name = "user_id_unique_constraint", columnNames = "user_id")})
public class AccountJpaEntity extends AbstractRecordMetadataModel{
    @Id
    @Column(name = "account_id", nullable = false)
    private Long accountId;
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "currency", nullable = false)
    private int currency;
}
