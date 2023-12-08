package com.baufest.transfer.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transfers")
public class TransferJpaEntity extends AbstractRecordMetadataModel{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transfersSeq")
    @SequenceGenerator(name = "transfersSeq", sequenceName = "transfersSeq", allocationSize = 1)
    private long id;
    @Column(name = "source_account_id")
    private Long sourceAccountId;
    @Column(name = "source_user_id")
    private String sourceUserId;
    @Column(name = "amount", nullable = false)
    private Double amount;
    @Column(name = "currency", nullable = false)
    private int currency;
    @Column(name = "target_account_id")
    private Long targetAccountId;
    @Column(name = "target_user_id")
    private String targetUserId;
    @Column(name = "status", nullable = false)
    private int status;
    @Column(name = "transfer_type", nullable = false)
    private int transferType;
    @Column(name = "error_details", length = 500)
    private String errorDetails;
}
