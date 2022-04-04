CREATE TABLE BOOKS_TYPES
(
    ID        INTEGER PRIMARY KEY NOT NULL,
    NAME      VARCHAR(50),
    CNT       INTEGER,
    FINE      INTEGER,
    DAY_COUNT INTEGER
);

CREATE TABLE CLIENTS
(
    ID             INTEGER PRIMARY KEY NOT NULL,
    FIRST_NAME     VARCHAR(20),
    LAST_NAME      VARCHAR(20),
    PATHER_NAME    VARCHAR(20),
    PASSPORT_SERIA VARCHAR(20),
    PASSPORT_NUM   VARCHAR(20)
);

CREATE TABLE BOOKS
(
    ID      INTEGER PRIMARY KEY NOT NULL,
    NAME    VARCHAR(20),
    CNT     INTEGER,
    TYPE_ID INTEGER,
    FOREIGN KEY (TYPE_ID) REFERENCES BOOKS_TYPES (ID)
);

CREATE TABLE JOURNAL
(
    ID        INTEGER PRIMARY KEY NOT NULL,
    BOOK_ID   INTEGER,
    CLIENT_ID INTEGER,
    DATE_BEG  TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP,
    DATE_END  TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP,
    DATE_RET  TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (CLIENT_ID) REFERENCES CLIENTS (ID),
    FOREIGN KEY (BOOK_ID) REFERENCES BOOKS (ID)
);
