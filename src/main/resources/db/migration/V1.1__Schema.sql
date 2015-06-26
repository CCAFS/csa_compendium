-- Schema creation script
COMMENT ON DATABASE compendium IS 'Compendium of Climate Smart Practices and Prioritization Tool Data';

CREATE TABLE barriers (
  id              INTEGER NOT NULL,
  code            TEXT,
  name            TEXT,
  description     TEXT,
  documentation   TEXT,
  from_compendium INTEGER NOT NULL
);

CREATE SEQUENCE barriers_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE categories (
  id            INTEGER NOT NULL,
  code          TEXT,
  name          TEXT,
  description   TEXT,
  documentation TEXT

);

CREATE SEQUENCE categories_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE conditions (
  id            INTEGER NOT NULL,
  code          TEXT,
  name          TEXT,
  description   TEXT,
  documentation TEXT,
  unit_id       INTEGER
);

CREATE SEQUENCE conditions_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE context_values (
  id                  INTEGER NOT NULL,
  code                TEXT,
  name                TEXT,
  description         TEXT,
  documentation       TEXT,
  context_variable_id INTEGER
);

CREATE SEQUENCE context_values_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE context_variables (
  id            INTEGER NOT NULL,
  code          TEXT,
  name          TEXT,
  description   TEXT,
  documentation TEXT,
  scope         TEXT
);

CREATE SEQUENCE context_variables_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE countries (
  code        TEXT NOT NULL,
  name        TEXT,
  region_code TEXT
);

CREATE TABLE experiment_articles (
  id              INTEGER NOT NULL,
  authors         TEXT,
  code            TEXT,
  contacts        TEXT,
  link            TEXT,
  outline         TEXT,
  publication     DATE,
  rating          INTEGER,
  title           TEXT,
  language        TEXT,
  theme_id        INTEGER,
  from_compendium INTEGER NOT NULL
);

CREATE SEQUENCE experiment_articles_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE experiment_context_values (
  experiment_article_id INTEGER NOT NULL,
  context_value_id      INTEGER NOT NULL
);

CREATE TABLE experiment_contexts (
  id                INTEGER NOT NULL,
  experiment_id     INTEGER,
  farming_system_id INTEGER,
  location_id       INTEGER
);

CREATE SEQUENCE experiment_contexts_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE experiment_production_systems (
  experiment_id        INTEGER NOT NULL,
  production_system_id INTEGER NOT NULL
);

CREATE TABLE farming_systems (
  id            INTEGER NOT NULL,
  code          TEXT,
  name          TEXT,
  description   TEXT,
  documentation TEXT,
  region_code   TEXT
);

CREATE SEQUENCE farming_systems_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE indicator_pillars (
  id           INTEGER          NOT NULL,
  pillar       TEXT,
  weight       DOUBLE PRECISION NOT NULL,
  indicator_id INTEGER
);

CREATE SEQUENCE indicator_pillars_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE indicators (
  id   INTEGER NOT NULL,
  name TEXT
);

CREATE SEQUENCE indicators_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE initial_conditions (
  id                    INTEGER          NOT NULL,
  state                 TEXT,
  value                 DOUBLE PRECISION NOT NULL,
  condition_id          INTEGER,
  experiment_context_id INTEGER,
  unit_id               INTEGER
);

CREATE SEQUENCE initial_conditions_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE languages (
  code          TEXT NOT NULL,
  english_name  TEXT,
  original_name TEXT
);

CREATE TABLE locations (
  id           INTEGER NOT NULL,
  latitude     REAL    NOT NULL,
  longitude    REAL    NOT NULL,
  max_altitude REAL,
  min_altitude REAL,
  place        TEXT,
  country_code TEXT
);

CREATE SEQUENCE locations_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE practice_context_values (
  practice_id      INTEGER NOT NULL,
  context_value_id INTEGER NOT NULL
);

CREATE TABLE practice_levels (
  id            INTEGER NOT NULL,
  code          TEXT,
  name          TEXT,
  description   TEXT,
  documentation TEXT,
  theme_id      INTEGER
);

CREATE SEQUENCE practice_levels_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE practice_themes (
  id            INTEGER NOT NULL,
  code          TEXT,
  name          TEXT,
  description   TEXT,
  documentation TEXT
);

CREATE SEQUENCE practice_themes_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE practices (

  id              INTEGER NOT NULL,
  code            TEXT,
  name            TEXT,
  description     TEXT,
  documentation   TEXT,
  active          BOOLEAN NOT NULL,
  tags            TEXT,
  level_id        INTEGER,
  from_compendium INTEGER NOT NULL
);

CREATE SEQUENCE practices_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE production_systems (
  id            INTEGER NOT NULL,
  code          TEXT,
  name          TEXT,
  description   TEXT,
  documentation TEXT,
  category_id   INTEGER
);

CREATE SEQUENCE production_systems_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE regions (
  code TEXT NOT NULL,
  name TEXT
);

CREATE TABLE sub_indicators (
  from_compendium INTEGER NOT NULL,
  id              INTEGER NOT NULL,
  code            TEXT,
  name            TEXT,
  description     TEXT,
  documentation   TEXT,
  indicator_id    INTEGER
);

CREATE SEQUENCE sub_indicators_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE synergies (
  id                 INTEGER NOT NULL,
  description        TEXT,
  exclusive          BOOLEAN,
  score              INTEGER,
  main_practice_id   INTEGER,
  second_practice_id INTEGER,
  from_compendium    INTEGER NOT NULL
);

CREATE SEQUENCE synergies_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE translations (
  id            INTEGER NOT NULL,
  column_name   TEXT,
  row_id        INTEGER,
  translation   TEXT,
  language_code TEXT
);

CREATE SEQUENCE translations_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE treatment_barriers (
  id           INTEGER          NOT NULL,
  cost         DOUBLE PRECISION NOT NULL,
  barrier_id   INTEGER,
  treatment_id INTEGER
);

CREATE SEQUENCE treatment_barriers_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE treatment_outcomes (
  id                   INTEGER NOT NULL,
  mean_value           DOUBLE PRECISION,
  end_date             DATE,
  start_date           DATE,
  result               TEXT,
  soil_depth           TEXT,
  standard_deviation   DOUBLE PRECISION,
  standard_error       DOUBLE PRECISION,
  unit_id              INTEGER,
  production_system_id INTEGER,
  sub_indicator_id     INTEGER,
  treatment_id         INTEGER
);

CREATE SEQUENCE treatment_outcomes_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE treatment_practices (
  treatment_id INTEGER NOT NULL,
  practice_id  INTEGER NOT NULL
);

CREATE TABLE treatments (
  id                     INTEGER NOT NULL,
  control_for_treatments BOOLEAN,
  information            TEXT,
  control_id             INTEGER,
  context_id             INTEGER
);

CREATE SEQUENCE treatments_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE units (
  id     INTEGER NOT NULL,
  name   TEXT,
  symbol TEXT
);

CREATE SEQUENCE units_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE workshop_authorities (
  id        INTEGER NOT NULL,
  authority TEXT,
  user_id   INTEGER
);

CREATE SEQUENCE workshop_authorities_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE workshop_barriers (
  id          INTEGER NOT NULL,
  workshop_id INTEGER
);

CREATE TABLE workshop_comments (
  id          INTEGER NOT NULL,
  body        TEXT,
  workshop_id INTEGER
);

CREATE SEQUENCE workshop_comments_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE workshop_experiments (
  id          INTEGER NOT NULL,
  workshop_id INTEGER
);

CREATE TABLE workshop_pillars (
  id          INTEGER          NOT NULL,
  pillar      TEXT,
  weight      DOUBLE PRECISION NOT NULL,
  workshop_id INTEGER
);

CREATE SEQUENCE workshop_pillars_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE workshop_portfolios (
  id          INTEGER NOT NULL,
  workshop_id INTEGER
);

CREATE SEQUENCE workshop_portfolios_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE workshop_practices (
  id          INTEGER NOT NULL,
  workshop_id INTEGER
);

CREATE TABLE workshop_prioritizations (
  id           INTEGER          NOT NULL,
  score        DOUBLE PRECISION NOT NULL,
  portfolio_id INTEGER,
  practice_id  INTEGER,
  workshop_id  INTEGER
);

CREATE SEQUENCE workshop_prioritizations_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE workshop_sub_indicators (
  id          INTEGER NOT NULL,
  workshop_id INTEGER
);

CREATE TABLE workshop_synergies (
  id          INTEGER NOT NULL,
  workshop_id INTEGER
);

CREATE TABLE workshop_used_treatments (
  workshop_id  INTEGER NOT NULL,
  treatment_id INTEGER NOT NULL
);

CREATE TABLE workshop_users (
  id     INTEGER NOT NULL,
  active BOOLEAN,
  email  TEXT,
  name   TEXT,
  passwd TEXT
);

CREATE SEQUENCE workshop_users_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


CREATE TABLE workshops (
  id           INTEGER NOT NULL,
  start_date   DATE,
  updated_date DATE,
  location_id  INTEGER
);

CREATE SEQUENCE workshops_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


ALTER TABLE ONLY barriers ALTER COLUMN id SET DEFAULT nextval('barriers_id_seq');

ALTER TABLE ONLY categories ALTER COLUMN id SET DEFAULT nextval('categories_id_seq');

ALTER TABLE ONLY conditions ALTER COLUMN id SET DEFAULT nextval('conditions_id_seq');

ALTER TABLE ONLY context_values ALTER COLUMN id SET DEFAULT nextval('context_values_id_seq');

ALTER TABLE ONLY context_variables ALTER COLUMN id SET DEFAULT nextval('context_variables_id_seq');

ALTER TABLE ONLY experiment_articles ALTER COLUMN id SET DEFAULT nextval('experiment_articles_id_seq');

ALTER TABLE ONLY experiment_contexts ALTER COLUMN id SET DEFAULT nextval('experiment_contexts_id_seq');

ALTER TABLE ONLY farming_systems ALTER COLUMN id SET DEFAULT nextval('farming_systems_id_seq');

ALTER TABLE ONLY indicator_pillars ALTER COLUMN id SET DEFAULT nextval('indicator_pillars_id_seq');

ALTER TABLE ONLY indicators ALTER COLUMN id SET DEFAULT nextval('indicators_id_seq');

ALTER TABLE ONLY initial_conditions ALTER COLUMN id SET DEFAULT nextval('initial_conditions_id_seq');

ALTER TABLE ONLY locations ALTER COLUMN id SET DEFAULT nextval('locations_id_seq');

ALTER TABLE ONLY practice_levels ALTER COLUMN id SET DEFAULT nextval('practice_levels_id_seq');

ALTER TABLE ONLY practice_themes ALTER COLUMN id SET DEFAULT nextval('practice_themes_id_seq');

ALTER TABLE ONLY practices ALTER COLUMN id SET DEFAULT nextval('practices_id_seq');

ALTER TABLE ONLY production_systems ALTER COLUMN id SET DEFAULT nextval('production_systems_id_seq');

ALTER TABLE ONLY sub_indicators ALTER COLUMN id SET DEFAULT nextval('sub_indicators_id_seq');

ALTER TABLE ONLY synergies ALTER COLUMN id SET DEFAULT nextval('synergies_id_seq');

ALTER TABLE ONLY translations ALTER COLUMN id SET DEFAULT nextval('translations_id_seq');

ALTER TABLE ONLY treatment_barriers ALTER COLUMN id SET DEFAULT nextval('treatment_barriers_id_seq');

ALTER TABLE ONLY treatment_outcomes ALTER COLUMN id SET DEFAULT nextval('treatment_outcomes_id_seq');

ALTER TABLE ONLY treatments ALTER COLUMN id SET DEFAULT nextval('treatments_id_seq');

ALTER TABLE ONLY units ALTER COLUMN id SET DEFAULT nextval('units_id_seq');

ALTER TABLE ONLY workshop_authorities ALTER COLUMN id SET DEFAULT nextval('workshop_authorities_id_seq');

ALTER TABLE ONLY workshop_comments ALTER COLUMN id SET DEFAULT nextval('workshop_comments_id_seq');

ALTER TABLE ONLY workshop_pillars ALTER COLUMN id SET DEFAULT nextval('workshop_pillars_id_seq');

ALTER TABLE ONLY workshop_portfolios ALTER COLUMN id SET DEFAULT nextval('workshop_portfolios_id_seq');

ALTER TABLE ONLY workshop_prioritizations ALTER COLUMN id SET DEFAULT nextval('workshop_prioritizations_id_seq');

ALTER TABLE ONLY workshop_users ALTER COLUMN id SET DEFAULT nextval('workshop_users_id_seq');

ALTER TABLE ONLY workshops ALTER COLUMN id SET DEFAULT nextval('workshops_id_seq');
