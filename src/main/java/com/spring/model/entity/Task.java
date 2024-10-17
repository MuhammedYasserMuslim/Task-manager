package com.spring.model.entity;


import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Setter
@Getter
@Table(name = "tasks")
@Entity
@Builder
@AllArgsConstructor
@EntityListeners({AuditingEntityListener.class})
public class Task implements Serializable {

    @Id
    private String id;
    @Column(name = "location")
    private String location;
    @Column(name = "description")
    private String description;
    @Column(name = "summary")
    private String summary;
    @Column(name = "status")
    private String status;
    @Column(name = "sequence")
    private int sequence;
    @Column(name = "event_type")
    private String eventType;
    @Column(name = "etag")
    private String etag;
    @Column(name = "html_link")
    private String htmlLink;
    @Column(name = "i_caluid")
    private String iCalUID;
    @Column(name = "kind")
    private String kind;

    @Column(name = "start")
    private DateTime start;
    @Column(name = "end")
    private DateTime end;

    @Column(name = "created")
    @CreatedDate
    private DateTime created;

    @Column(name = "updated")
    @LastModifiedDate
    private DateTime updated;

    @OneToOne(cascade = CascadeType.ALL)
    private Creator creator;
    @OneToOne(cascade = CascadeType.ALL)
    private Organizer organizer;

    @Column(name = "use_default")
    private boolean useDefault;

    @Column(name = "is_offline")
    private boolean isOffline;

    public Task() {
    }
}
