insert into post (id, public_id, content, title)
values (1, '52b9d5d0-4959-4c3d-a390-b415ec9dfaaa'::uuid, 'Let me tell you what happened.', 'Story of my life');

insert into comment (id, post_id, text)
values (1, 1, 'Unbelievable!');