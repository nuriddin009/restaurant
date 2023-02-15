package com.example.restaurant.entity.audit;

import com.example.restaurant.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@EqualsAndHashCode(exclude = {"createdBy", "updatedBy"})
@EntityListeners(AuditingEntityListener.class)
public abstract class UserAudit {

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(updatable = false)
    private User createdBy;

    @LastModifiedBy
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User updatedBy;

}
