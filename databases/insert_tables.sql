use `servlet-jsp-jdbc`;

-- ==============================================INSERT DB FOR TABLES category==============================================
insert into category(code, name) values('news', 'News');
insert into category(code, name) values('view', 'View');
insert into category(code, name) values('world', 'World');
insert into category(code, name) values('video', 'Video');
insert into category(code, name) values('business', 'Business');
insert into category(code, name) values('politics', 'Politics');
insert into category(code, name) values('sport', 'Sport');
insert into category(code, name) values('entertainment', 'Entertainment');
insert into category(code, name) values('science', 'Science');
insert into category(code, name) values('law', 'Law');
insert into category(code, name) values('education', 'Education');
insert into category(code, name) values('health', 'Health');
insert into category(code, name) values('life', 'Life');
insert into category(code, name) values('tourism', 'Tourism');

-- ==============================================INSERT DB FOR TABLES role==============================================
insert into role(code,name) values('ADMIN', 'ADMIN');
insert into role(code,name) values('USER', 'USER');

-- ==============================================INSERT DB FOR TABLES user==============================================
insert into user(username,password,fullname,status, roleid) values('admin', '123456', 'admin', 1, 1);
insert into user(username,password,fullname,status, roleid) values('duongminh', '123456', 'duong ngoc minh', 1, 2);
insert into user(username,password,fullname,status, roleid) values('messi', '123456', 'lionel messi', 1, 2);
