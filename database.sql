create database test;
use  test;

-- auto-generated definition
create table buyers
(
    id        int         not null
        primary key,
    fName     varchar(50) not null,
    lName     varchar(50) not null,
    email     varchar(30) not null,
    contactNo varchar(10) not null,
    address   varchar(50) not null,
    zipCode   varchar(10) not null,
    pass      varchar(40) not null
);
-- auto-generated definition
create table `order`
(
    id        int not null primary key,
    itemId     varchar(50),
    buyerId     varchar(50),
    name     varchar(70) not null,
    address varchar(1000) not null,
    state   varchar(50) not null,
    country   varchar(50) not null,
    zip   varchar(60) not null,
    contact   varchar(50) not null,
    qty      int not null
);

-- auto-generated definition
create table fund
(
    id            int         not null
        primary key,
    fundID        varchar(45) not null,
    fundName      varchar(45) not null,
    email         varchar(45) not null,
    address       varchar(45) not null,
    contactNumber varchar(45) not null,
    fundMethod    varchar(45) not null,
    amount        varchar(45) not null
);

-- auto-generated definition
create table items
(
    itemID    int         not null
        primary key,
    itemCode  varchar(50) not null,
    itemName  varchar(50) not null,
    itemPrice double      not null,
    itemDesc  varchar(40) not null
)
    charset = latin1;

-- auto-generated definition
create table products
(
    id          int           not null
        primary key,
    name        varchar(230)  not null,
    description varchar(1000) not null,
    price       double        not null,
    qty         varchar(40)   not null,
    shipping    varchar(90)   not null,
    image       varchar(1000) not null
);

-- auto-generated definition
create table project
(
    projectID     int auto_increment
        primary key,
    publisherName varchar(45)  not null,
    projectName   varchar(45)  not null,
    email         varchar(45)  not null,
    status        varchar(45)  not null,
    link          varchar(100) not null,
    updatedDate   varchar(45)  not null,
    submittedDate varchar(45)  not null,
    Description   varchar(100) not null
);

