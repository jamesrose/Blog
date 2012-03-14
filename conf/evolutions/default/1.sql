# Posts schema

# --- !Ups
CREATE SEQUENCE post_id_seq;
CREATE TABLE post (
    id integer NOT NULL DEFAULT nextval('post_id_seq'),
    title varchar(255),
    body text
  )
  
# --- !Downs

DROP TABLE post;
DROP SEQUENCE post_id_seq;