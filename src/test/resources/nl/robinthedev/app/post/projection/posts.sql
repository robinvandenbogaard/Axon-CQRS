insert into post (id, public_id, content, title)
values (1, '52b9d5d0-4959-4c3d-a390-b415ec9dfaaa'::uuid, 'Let me tell you what happened.', 'Story of my life');

insert into comment (id, public_id, post_id, text)
values (1, 'ce6ef61b-292a-4b30-a172-d5355a6c6e19'::uuid, 1, 'Unbelievable!');