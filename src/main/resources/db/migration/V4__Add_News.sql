insert into news(news_title,news_body)
values('What\'s E-News?',
       'E-News - is a news portal, where everybody can post articles,
        with no need to be signed up. Articles you post, will be reviewed by a real moderator.
        In case of approving yours, it will be posted on the site and everybody
        will have a chance to view that.');

insert into moderated_news(moderator_fk,news_fk)
values((select u.user_id from user_role ur join users u on u.user_id=ur.user_fk
        join roles r on r.role_id=ur.role_fk where r.role_name='ROLE_MODERATOR' limit 1),
       (select n.news_id from news n where n.news_title='What\'s E-News?'));