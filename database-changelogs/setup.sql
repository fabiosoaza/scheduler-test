--password test
CREATE ROLE test LOGIN
  ENCRYPTED PASSWORD 'md505a671c66aefea124cc08b76ea6d30bb'
  SUPERUSER INHERIT CREATEDB CREATEROLE NOREPLICATION;


CREATE DATABASE test
  WITH OWNER = test
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.utf8'
       LC_CTYPE = 'en_US.utf8'
       CONNECTION LIMIT = -1;

-- Table: public.message

-- DROP TABLE public.message;

CREATE TABLE public.message
(
  id bigint NOT NULL DEFAULT nextval('message_id_seq'::regclass),
  mobile character varying(20) NOT NULL,
  msg text NOT NULL,
  external_key character varying(100),
  sender character varying(20),
  schedule timestamp with time zone NOT NULL,
  CONSTRAINT pk_messages PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.message
  OWNER TO test;
GRANT ALL ON TABLE public.message TO test;