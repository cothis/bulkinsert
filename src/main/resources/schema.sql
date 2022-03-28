CREATE TABLE allotment
(
    id            bigint,
    amount        decimal(12, 6),
    create_date   datetime,
    modified_date datetime,
    creator       varchar(20),
    modifier      varchar(20),
    PRIMARY KEY (id)
);
