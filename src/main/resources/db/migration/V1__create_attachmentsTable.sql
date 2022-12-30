CREATE TABLE Attachment (
    id CHAR(36) PRIMARY KEY,
    fileName VARCHAR(255) NOT NULL,
    fileType VARCHAR(255) NOT NULL ,
    data OID NOT NULL
);
