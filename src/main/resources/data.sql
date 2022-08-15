insert into user_tb (user_id, user_email, user_password, user_first_name, user_last_name, user_username, user_birth, user_phone, user_gender) values (1, 'aaa@gmail.com', 'aaaaaaaa', 'A', 'BC', 'ABCDE', '2000-01-01', '+8211112222', 1);
insert into user_tb (user_id, user_email, user_password, user_first_name, user_last_name, user_username, user_birth, user_phone, user_gender) values (2, 'fff@gmail.com', 'ffffffff', 'F', 'GH', 'FGHIJ', '2005-05-05', '+8233334444', 2);
insert into user_tb (user_id, user_email, user_password, user_first_name, user_last_name, user_username, user_birth, user_phone, user_gender) values (3, 'kkk@gmail.com', 'kkkkkkkk', 'K', 'LM', 'KLMNO', '2010-10-10', '+8255556666', 3);

insert into mood_tb (mood_id, mood_category, mood_situation, mood_title, mood_reason, mood_date, user_id) values (1, 'HAPPY', 'WEATHER', 'HELLO', 'HOW ARE YOU TODAY', now(), 1);
insert into mood_tb (mood_id, mood_category, mood_situation, mood_title, mood_reason, mood_date, user_id) values (2, 'HAPPY', 'WEATHER', 'HELLO', 'HOW ARE YOU TODAY', now(), 1);
insert into mood_tb (mood_id, mood_category, mood_situation, mood_title, mood_reason, mood_date, user_id) values (3, 'HAPPY', 'WEATHER', 'HELLO', 'HOW ARE YOU TODAY', now(), 1);
insert into mood_tb (mood_id, mood_category, mood_situation, mood_title, mood_reason, mood_date, user_id) values (4, 'BORED', 'WORK', 'HELLO', 'HOW ARE YOU TODAY', now(), 1);
insert into mood_tb (mood_id, mood_category, mood_situation, mood_title, mood_reason, mood_date, user_id) values (5, 'BORED', 'WORK', 'HELLO', 'HOW ARE YOU TODAY', now(), 1);
insert into mood_tb (mood_id, mood_category, mood_situation, mood_title, mood_reason, mood_date, user_id) values (6, 'SAD', 'WEATHER', 'HELLO', 'HOW ARE YOU TODAY', now(), 1);
insert into mood_tb (mood_id, mood_category, mood_situation, mood_title, mood_reason, mood_date, user_id) values (7, 'ANGRY', 'WEATHER', 'HELLO', 'HOW ARE YOU TODAY', now(), 2);
insert into mood_tb (mood_id, mood_category, mood_situation, mood_title, mood_reason, mood_date, user_id) values (8, 'HAPPY', 'WEATHER', 'HELLO', 'HOW ARE YOU TODAY', now(), 3);