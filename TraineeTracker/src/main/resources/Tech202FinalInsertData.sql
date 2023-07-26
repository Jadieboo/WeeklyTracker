insert into users(first_name,last_name) values('Catherine','French');
insert into users(first_name,last_name) values('Laura','Tozer');
insert into users(first_name,last_name) values('Jade','Sale');
insert into users(first_name,last_name) values('Nicole','Akanbi');
insert into users(first_name,last_name) values('Parmita','Paul');

insert into course(course_name) values('JAVADEV');
insert into course(course_name) values('SDET');

insert into batch(batch_name,course_id,weeks) values('TECH202',1,10);
insert into batch(batch_name,course_id,weeks) values('TECH203',2,8);


insert into user_batch(user_id,batch_id) values(1,1);
insert into user_batch(user_id,batch_id) values(1,2);
insert into user_batch(user_id,batch_id) values(2,2);
insert into user_batch(user_id,batch_id) values(3,1);
insert into user_batch(user_id,batch_id) values(4,1);
insert into user_batch(user_id,batch_id) values(5,2);


insert into account(user_id,username,password,role) values(1,'cathy','123','trainer');
insert into account(user_id,username,password,role) values(2,'laura','456','trainer');
insert into account(user_id,username,password,role) values(3,'jade','password','trainee');
insert into account(user_id,username,password,role) values(4,'nicole','password','trainee');
insert into account(user_id,username,password,role) values(5,'parmita','password','trainee');


insert into tracker(week,start,stop,continue_field,comment,technical_skills,soft_skills,trainee_id)
values(1,'start','stop','continue','comments','Skilled','Skilled',3);

insert into tracker(week,trainee_id) values(2,3);
insert into tracker(week,trainee_id) values(3,3),(4,3),(5,3),(6,3),(7,3),(8,3),(9,3),(10,3);
insert into tracker(week,trainee_id) values(1,4),(2,4),(3,4),(4,4),(5,4),(6,4),(7,4),(8,4),(9,4),(10,4);
insert into tracker(week,trainee_id) values(1,5),(2,5),(3,5),(4,5),(5,5),(6,5),(7,5),(8,5);

insert into apikey(user_id,apikey) values(1,'abc');
insert into apikey(user_id,apikey) values(2,'def');





