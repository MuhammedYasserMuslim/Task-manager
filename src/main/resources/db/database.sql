CREATE TABLE creator
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    is_self BOOLEAN NOT NULL,
    email   VARCHAR(255)
);

CREATE TABLE organizer
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    is_self BOOLEAN NOT NULL,
    email   VARCHAR(255)
);

CREATE TABLE tasks
(
    id           VARCHAR(255) PRIMARY KEY,
    creator_id   INT,
    organizer_id INT,
    is_offline   BOOLEAN NOT NULL,
    sequence     INT     NOT NULL,
    use_default  BOOLEAN NOT NULL,
    description  VARCHAR(255),
    etag         VARCHAR(255),
    event_type   VARCHAR(255),
    html_link    VARCHAR(255),
    i_caluid     VARCHAR(255),
    kind         VARCHAR(255),
    location     VARCHAR(255),
    status       VARCHAR(255),
    summary      VARCHAR(255),
    created      TIMESTAMP,
    start        TIMESTAMP,
    end          TIMESTAMP,
    updated      TIMESTAMP,
    FOREIGN KEY (creator_id) REFERENCES creator (id),
    FOREIGN KEY (organizer_id) REFERENCES organizer (id)
);