create table classrooms(

    id bigint not null auto_increment,
    name varchar(100) not null,
    category varchar(100) not null,

    primary key(id)
);
/*
private Long id;
private String name;
private ClassCategory category;
*/


create table replys(

    id bigint not null auto_increment,
    message varchar(500) not null,
    timeStamp datetime not null,
    solution boolean not null,
    topic_id bigint not null,
    user_id bigint not null,

    primary key(id)

);
/*
private Long id;
private String message;
private LocalDateTime timeStamp;
private Boolean solution;
private Topic topic;
private User author;
*/

create table topics(

    id bigint not null auto_increment,
    title varchar(100) not null,
    message varchar(500) not null,
    timeStamp datetime not null,
    status varchar(100),
    user_id bigint not null,
    classroom_id bigint not null,
    active boolean not null,

    primary key(id)

);
/*
private Long id;
private String title;
private String message;
private LocalDateTime timeStamp;
private int status;
private User author;
private Classroom classroom;
private List<Reply> replys;
private Boolean active;
*/

create table users(

    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null,
    password varchar(300) not null,
    profile int default 0,

    primary key(id)

);
/*
private Long id;
private String name;
private String email;
private String password;
private List<Topic> topics;
private int profile;
*/