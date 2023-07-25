
create database Tech202;

create table users( user_id int primary key auto_increment,
					first_name varchar(50) not null,
                    last_name varchar(50) not null);
                    
                    
create table course(course_id int primary key auto_increment,
					course_name varchar(50) not null);
                    
                    
create table batch(batch_id int primary key auto_increment,
					batch_name varchar(50) not null,
                    course_id int,
                    weeks int not null,
                    foreign key(course_id) references course(course_id)
                    );
                    
                    
create table user_batch(user_batch_id int primary key auto_increment,
						user_id int,
                        batch_id int,
                        foreign key(user_id) references users(user_id),
                        foreign key(batch_id) references batch(batch_id));   
                        
                        
create table account(account_id int primary key auto_increment,
					user_id int,
                    username varchar(50) not null,
                    password varchar(50) not null,
                    role varchar(50) not null,
                    foreign key(user_id) references users(user_id));
                    
                    
create table tracker(tracker_id int primary key auto_increment,
					 week int not null,
                     start varchar(1000),
                     stop varchar(1000),
                     continue_field varchar(1000),
                     comment varchar(1000),
                     technical_skills varchar(50),
                     soft_skills varchar(50),
                     trainee_id int,
                     foreign key(trainee_id) references users(user_id));
                     
                     
                     
		
                     
                     
                     
                     
                    
                    

                    
					