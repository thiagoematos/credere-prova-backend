CREATE TABLE public.status
(
  x integer NOT NULL,
  y integer NOT NULL,
  face "char" NOT NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.status
  OWNER TO postgres;
