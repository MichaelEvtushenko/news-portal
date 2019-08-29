insert into users(user_name,user_password)
values('moderator','$2a$10$hOPrztM5Mv63vsKbbZLnkO7pJt1YEm/3TeGb.ENNtSiGm3uNAdvJq');

insert into user_role(user_fk,role_fk)
values((select u.user_id from users u where u.user_name='moderator'),
       (select r.role_id from roles r where r.role_name='ROLE_USER'));

insert into user_role(user_fk,role_fk)
values((select u.user_id from users u where u.user_name='moderator'),
       (select r.role_id from roles r where r.role_name='ROLE_MODERATOR'));