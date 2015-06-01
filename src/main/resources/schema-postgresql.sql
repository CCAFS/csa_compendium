-- Compendium PostgreSql database creation script

DROP SCHEMA public cascade;
CREATE SCHEMA public;

-- Tables and Sequences

CREATE TABLE barriers (
    id integer NOT NULL,
    code character varying(255),
    name character varying(255),
    description text,
    documentation text,
    from_compendium integer NOT NULL
);

CREATE SEQUENCE barriers_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE barriers_id_seq OWNED BY barriers.id;

CREATE TABLE categories (
    id integer NOT NULL,
    code character varying(255),
    description text,
    documentation text,
    name character varying(255)
);

CREATE SEQUENCE categories_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE categories_id_seq OWNED BY categories.id;

CREATE TABLE conditions (
    id integer NOT NULL,
    code character varying(255),
    name character varying(255),
    description text,
    documentation text
	unit_id integer
);

CREATE SEQUENCE conditions_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE conditions_id_seq OWNED BY conditions.id;

CREATE TABLE context_values (
    id integer NOT NULL,
    code character varying(255),
    name character varying(255),
    description text,
    documentation text,
    context_variable_id integer
);

CREATE SEQUENCE context_values_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE context_values_id_seq OWNED BY context_values.id;

CREATE TABLE context_variables (
    id integer NOT NULL,
    code character varying(255),
    name character varying(255),
    description text,
    documentation text,
    scope character varying(255)
);

CREATE SEQUENCE context_variables_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE context_variables_id_seq OWNED BY context_variables.id;

CREATE TABLE countries (
    code character varying(255) NOT NULL,
    name character varying(255),
    region_code character varying(255)
);

CREATE TABLE experiment_articles (
    
    id integer NOT NULL,
    code character varying(255),
    title text,
    authors text,
    contacts text,
    link character varying(255),
    outline text,
    publication date,
    rating integer,
    farming_system_id integer,
    language character varying(255),
    location_id integer,
    theme_id integer,
    from_compendium integer NOT NULL
);

CREATE SEQUENCE experiment_articles_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE experiment_articles_id_seq OWNED BY experiment_articles.id;

CREATE TABLE experiment_context_values (
    experiment_article_id integer NOT NULL,
    context_value_id integer NOT NULL
);

CREATE TABLE farming_systems (
    id integer NOT NULL,
    code character varying(255),
    name character varying(255),
    description text,
    documentation text,
    region_code character varying(255)
);

CREATE SEQUENCE farming_systems_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE farming_systems_id_seq OWNED BY farming_systems.id;

CREATE TABLE indicator_pillars (
    id integer NOT NULL,
    pillar character varying(255),
    weight real NOT NULL,
    indicator_id integer
);

CREATE SEQUENCE indicator_pillars_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE indicator_pillars_id_seq OWNED BY indicator_pillars.id;

CREATE TABLE indicators (
    id integer NOT NULL,
    name character varying(255)
);

CREATE SEQUENCE indicators_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE indicators_id_seq OWNED BY indicators.id;

CREATE TABLE initial_conditions (
    id integer NOT NULL,
    state character varying(255),
    value real NOT NULL,
    condition_id integer,
    experiment_id integer,
    unit_id integer
);

CREATE SEQUENCE initial_conditions_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE initial_conditions_id_seq OWNED BY initial_conditions.id;

CREATE TABLE languages (
    code character varying(255) NOT NULL,
    english_name character varying(255),
    original_name character varying(255)
);

CREATE TABLE locations (
    id integer NOT NULL,
    altitude real NOT NULL,
    latitude real NOT NULL,
    longitude real NOT NULL,
    place character varying(255),
    country_code character varying(255)
);

CREATE SEQUENCE locations_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE locations_id_seq OWNED BY locations.id;

CREATE TABLE practice_context_values (
    practice_id integer NOT NULL,
    context_value_id integer NOT NULL
);

CREATE TABLE practice_levels (
    id integer NOT NULL,
    code character varying(255),
    name character varying(255),
    description text,
    documentation text,
    theme_id integer
);

CREATE SEQUENCE practice_levels_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE practice_levels_id_seq OWNED BY practice_levels.id;

CREATE TABLE practice_themes (
    id integer NOT NULL,
    code character varying(255),
    name character varying(255),
    description text,
    documentation text
);

CREATE SEQUENCE practice_themes_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE practice_themes_id_seq OWNED BY practice_themes.id;

CREATE TABLE practices (
    id integer NOT NULL,
    code character varying(255),
    name character varying(255),
    description text,
    documentation text,
    tags character varying(255),
    level_id integer,
    from_compendium integer NOT NULL
);

CREATE SEQUENCE practices_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE practices_id_seq OWNED BY practices.id;

CREATE TABLE production_systems (
    id integer NOT NULL,
    code character varying(255),
    name character varying(255),
    description text,
    documentation text,
    category_id integer
);

CREATE SEQUENCE production_systems_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE production_systems_id_seq OWNED BY production_systems.id;

CREATE TABLE regions (
    code character varying(255) NOT NULL,
    name character varying(255)
);

CREATE TABLE sub_indicators (
    from_compendium integer NOT NULL,
    id integer NOT NULL,
    code character varying(255),
    description text,
    documentation text,
    name character varying(255),
    indicator_id integer
);

CREATE SEQUENCE sub_indicators_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE sub_indicators_id_seq OWNED BY sub_indicators.id;
CREATE TABLE synergies (
    id integer NOT NULL,
    description character varying(255),
    exclusive boolean,
    score integer,
    main_practice_id integer,
    second_practice_id integer,
    from_compendium integer NOT NULL
);

CREATE SEQUENCE synergies_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE synergies_id_seq OWNED BY synergies.id;

CREATE TABLE translations (
    id integer NOT NULL,
    column_name character varying(255),
    row_id integer,
    translation character varying(255),
    language_code character varying(255)
);

CREATE SEQUENCE translations_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE translations_id_seq OWNED BY translations.id;

CREATE TABLE treatment_barriers (
    id integer NOT NULL,
    cost real NOT NULL,
    barrier_id integer,
    treatment_id integer
);

CREATE SEQUENCE treatment_barriers_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE treatment_barriers_id_seq OWNED BY treatment_barriers.id;

CREATE TABLE treatment_outcomes (
    id integer NOT NULL,
    start_date date,
    end_date date,
    initial_value real,
    final_value real,
    perceived_change integer NOT NULL,
    result character varying(255),
    indicator_id integer,
    unit_id integer,
    treatment_id integer
);

CREATE SEQUENCE treatment_outcomes_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE treatment_outcomes_id_seq OWNED BY treatment_outcomes.id;

CREATE TABLE treatment_production_systems (
    treatment_id integer NOT NULL,
    production_system_id integer NOT NULL
);

CREATE TABLE treatments (
    id integer NOT NULL,
    control_for_treatments boolean,
    control_id integer,
    experiment_id integer,
    practice_id integer
);

CREATE SEQUENCE treatments_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE treatments_id_seq OWNED BY treatments.id;

CREATE TABLE units (
    id integer NOT NULL,
    name character varying(255),
    options character varying(255),
    symbols character varying(255)
);

CREATE SEQUENCE units_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE units_id_seq OWNED BY units.id;

CREATE TABLE workshop_authorities (
    id integer NOT NULL,
    authority character varying(255),
    user_id integer
);

CREATE SEQUENCE workshop_authorities_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE workshop_authorities_id_seq OWNED BY workshop_authorities.id;

CREATE TABLE workshop_barriers (
    id integer NOT NULL,
    workshop_id integer
);

CREATE TABLE workshop_comments (
    id integer NOT NULL,
    body character varying(255),
    workshop_id integer
);

CREATE SEQUENCE workshop_comments_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE workshop_comments_id_seq OWNED BY workshop_comments.id;

CREATE TABLE workshop_experiments (
    id integer NOT NULL,
    workshop_id integer
);

CREATE TABLE workshop_pillars (
    id integer NOT NULL,
    pillar character varying(255),
    weight real NOT NULL,
    workshop_id integer
);

CREATE SEQUENCE workshop_pillars_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE workshop_pillars_id_seq OWNED BY workshop_pillars.id;

CREATE TABLE workshop_portfolios (
    id integer NOT NULL,
    workshop_id integer
);

CREATE SEQUENCE workshop_portfolios_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE workshop_portfolios_id_seq OWNED BY workshop_portfolios.id;

CREATE TABLE workshop_practices (
    id integer NOT NULL,
    workshop_id integer
);

CREATE TABLE workshop_prioritizations (
    id integer NOT NULL,
    score real NOT NULL,
    portfolio_id integer,
    practice_id integer,
    workshop_id integer
);

CREATE SEQUENCE workshop_prioritizations_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE workshop_prioritizations_id_seq OWNED BY workshop_prioritizations.id;

CREATE TABLE workshop_sub_indicators (
    id integer NOT NULL,
    workshop_id integer
);

CREATE TABLE workshop_synergies (
    id integer NOT NULL,
    workshop_id integer
);

CREATE TABLE workshop_used_treatments (
    workshop_id integer NOT NULL,
    treatment_id integer NOT NULL
);

CREATE TABLE workshop_users (
    id integer NOT NULL,
    active boolean,
    email character varying(255),
    name character varying(255),
    passwd character varying(255)
);

CREATE SEQUENCE workshop_users_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE workshop_users_id_seq OWNED BY workshop_users.id;

CREATE TABLE workshops (
    id integer NOT NULL,
    start_date date,
    updated_date date,
    location_id integer
);

CREATE SEQUENCE workshops_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

ALTER SEQUENCE workshops_id_seq OWNED BY workshops.id;

-- Auto-increments

ALTER TABLE ONLY barriers ALTER COLUMN id SET DEFAULT nextval('barriers_id_seq'::regclass);

ALTER TABLE ONLY categories ALTER COLUMN id SET DEFAULT nextval('categories_id_seq'::regclass);

ALTER TABLE ONLY conditions ALTER COLUMN id SET DEFAULT nextval('conditions_id_seq'::regclass);

ALTER TABLE ONLY context_values ALTER COLUMN id SET DEFAULT nextval('context_values_id_seq'::regclass);

ALTER TABLE ONLY context_variables ALTER COLUMN id SET DEFAULT nextval('context_variables_id_seq'::regclass);

ALTER TABLE ONLY experiment_articles ALTER COLUMN id SET DEFAULT nextval('experiment_articles_id_seq'::regclass);

ALTER TABLE ONLY farming_systems ALTER COLUMN id SET DEFAULT nextval('farming_systems_id_seq'::regclass);

ALTER TABLE ONLY indicator_pillars ALTER COLUMN id SET DEFAULT nextval('indicator_pillars_id_seq'::regclass);

ALTER TABLE ONLY indicators ALTER COLUMN id SET DEFAULT nextval('indicators_id_seq'::regclass);

ALTER TABLE ONLY initial_conditions ALTER COLUMN id SET DEFAULT nextval('initial_conditions_id_seq'::regclass);

ALTER TABLE ONLY locations ALTER COLUMN id SET DEFAULT nextval('locations_id_seq'::regclass);

ALTER TABLE ONLY practice_levels ALTER COLUMN id SET DEFAULT nextval('practice_levels_id_seq'::regclass);

ALTER TABLE ONLY practice_themes ALTER COLUMN id SET DEFAULT nextval('practice_themes_id_seq'::regclass);

ALTER TABLE ONLY practices ALTER COLUMN id SET DEFAULT nextval('practices_id_seq'::regclass);

ALTER TABLE ONLY production_systems ALTER COLUMN id SET DEFAULT nextval('production_systems_id_seq'::regclass);

ALTER TABLE ONLY sub_indicators ALTER COLUMN id SET DEFAULT nextval('sub_indicators_id_seq'::regclass);

ALTER TABLE ONLY synergies ALTER COLUMN id SET DEFAULT nextval('synergies_id_seq'::regclass);

ALTER TABLE ONLY translations ALTER COLUMN id SET DEFAULT nextval('translations_id_seq'::regclass);

ALTER TABLE ONLY treatment_barriers ALTER COLUMN id SET DEFAULT nextval('treatment_barriers_id_seq'::regclass);

ALTER TABLE ONLY treatment_outcomes ALTER COLUMN id SET DEFAULT nextval('treatment_outcomes_id_seq'::regclass);

ALTER TABLE ONLY treatments ALTER COLUMN id SET DEFAULT nextval('treatments_id_seq'::regclass);

ALTER TABLE ONLY units ALTER COLUMN id SET DEFAULT nextval('units_id_seq'::regclass);

ALTER TABLE ONLY workshop_authorities ALTER COLUMN id SET DEFAULT nextval('workshop_authorities_id_seq'::regclass);

ALTER TABLE ONLY workshop_comments ALTER COLUMN id SET DEFAULT nextval('workshop_comments_id_seq'::regclass);

ALTER TABLE ONLY workshop_pillars ALTER COLUMN id SET DEFAULT nextval('workshop_pillars_id_seq'::regclass);

ALTER TABLE ONLY workshop_portfolios ALTER COLUMN id SET DEFAULT nextval('workshop_portfolios_id_seq'::regclass);

ALTER TABLE ONLY workshop_prioritizations ALTER COLUMN id SET DEFAULT nextval('workshop_prioritizations_id_seq'::regclass);

ALTER TABLE ONLY workshop_users ALTER COLUMN id SET DEFAULT nextval('workshop_users_id_seq'::regclass);

ALTER TABLE ONLY workshops ALTER COLUMN id SET DEFAULT nextval('workshops_id_seq'::regclass);

-- Primary Keys

ALTER TABLE ONLY barriers
    ADD CONSTRAINT barriers_pkey PRIMARY KEY (id);

ALTER TABLE ONLY categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);

ALTER TABLE ONLY conditions
    ADD CONSTRAINT conditions_pkey PRIMARY KEY (id);

ALTER TABLE ONLY context_values
    ADD CONSTRAINT context_values_pkey PRIMARY KEY (id);

ALTER TABLE ONLY context_variables
    ADD CONSTRAINT context_variables_pkey PRIMARY KEY (id);

ALTER TABLE ONLY countries
    ADD CONSTRAINT countries_pkey PRIMARY KEY (code);

ALTER TABLE ONLY experiment_articles
    ADD CONSTRAINT experiment_articles_pkey PRIMARY KEY (id);

ALTER TABLE ONLY farming_systems
    ADD CONSTRAINT farming_systems_pkey PRIMARY KEY (id);

ALTER TABLE ONLY indicator_pillars
    ADD CONSTRAINT indicator_pillars_pkey PRIMARY KEY (id);

ALTER TABLE ONLY indicators
    ADD CONSTRAINT indicators_pkey PRIMARY KEY (id);

ALTER TABLE ONLY initial_conditions
    ADD CONSTRAINT initial_conditions_pkey PRIMARY KEY (id);

ALTER TABLE ONLY languages
    ADD CONSTRAINT languages_pkey PRIMARY KEY (code);

ALTER TABLE ONLY locations
    ADD CONSTRAINT locations_pkey PRIMARY KEY (id);

ALTER TABLE ONLY practice_levels
    ADD CONSTRAINT practice_levels_pkey PRIMARY KEY (id);

ALTER TABLE ONLY practice_themes
    ADD CONSTRAINT practice_themes_pkey PRIMARY KEY (id);

ALTER TABLE ONLY practices
    ADD CONSTRAINT practices_pkey PRIMARY KEY (id);

ALTER TABLE ONLY production_systems
    ADD CONSTRAINT production_systems_pkey PRIMARY KEY (id);

ALTER TABLE ONLY regions
    ADD CONSTRAINT regions_pkey PRIMARY KEY (code);

ALTER TABLE ONLY sub_indicators
    ADD CONSTRAINT sub_indicators_pkey PRIMARY KEY (id);

ALTER TABLE ONLY synergies
    ADD CONSTRAINT synergies_pkey PRIMARY KEY (id);

ALTER TABLE ONLY translations
    ADD CONSTRAINT translations_pkey PRIMARY KEY (id);

ALTER TABLE ONLY treatment_barriers
    ADD CONSTRAINT treatment_barriers_pkey PRIMARY KEY (id);

ALTER TABLE ONLY treatment_outcomes
    ADD CONSTRAINT treatment_outcomes_pkey PRIMARY KEY (id);

ALTER TABLE ONLY treatments
    ADD CONSTRAINT treatments_pkey PRIMARY KEY (id);

ALTER TABLE ONLY units
    ADD CONSTRAINT units_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshop_authorities
    ADD CONSTRAINT workshop_authorities_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshop_barriers
    ADD CONSTRAINT workshop_barriers_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshop_comments
    ADD CONSTRAINT workshop_comments_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshop_experiments
    ADD CONSTRAINT workshop_experiments_pkey PRIMARY KEY (id);


ALTER TABLE ONLY workshop_pillars
    ADD CONSTRAINT workshop_pillars_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshop_portfolios
    ADD CONSTRAINT workshop_portfolios_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshop_practices
    ADD CONSTRAINT workshop_practices_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshop_prioritizations
    ADD CONSTRAINT workshop_prioritizations_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshop_sub_indicators
    ADD CONSTRAINT workshop_sub_indicators_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshop_synergies
    ADD CONSTRAINT workshop_synergies_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshop_users
    ADD CONSTRAINT workshop_users_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshops
    ADD CONSTRAINT workshops_pkey PRIMARY KEY (id);

-- Foreing Keys

ALTER TABLE ONLY experiment_articles
    ADD CONSTRAINT fk_1jyo6oufn86xrxgljrc87afxp FOREIGN KEY (location_id) REFERENCES locations(id);

ALTER TABLE ONLY sub_indicators
    ADD CONSTRAINT fk_1kqakbvk3ff3p4nsh6nrgv0le FOREIGN KEY (indicator_id) REFERENCES indicators(id);
ALTER TABLE ONLY workshop_synergies
    ADD CONSTRAINT fk_205kejgb70l7jcnltnrfv9c7x FOREIGN KEY (id) REFERENCES synergies(id);

ALTER TABLE ONLY treatment_production_systems
    ADD CONSTRAINT fk_2r3mufjkqvt5jp0cd06krp24f FOREIGN KEY (treatment_id) REFERENCES treatments(id);

ALTER TABLE ONLY practice_context_values
    ADD CONSTRAINT fk_3dortc5qg58e0hmhutdqrbuu6 FOREIGN KEY (practice_id) REFERENCES practices(id);

ALTER TABLE ONLY treatments
    ADD CONSTRAINT fk_3e5t67g283drsgjvkayl4a0gf FOREIGN KEY (control_id) REFERENCES treatments(id);

ALTER TABLE ONLY workshop_prioritizations
    ADD CONSTRAINT fk_3hx8obgmia4cg96fu1ao5nhg1 FOREIGN KEY (practice_id) REFERENCES practices(id);

ALTER TABLE ONLY workshop_pillars
    ADD CONSTRAINT fk_3jm6kgq071032h0mwwlu5khgh FOREIGN KEY (workshop_id) REFERENCES workshops(id);

ALTER TABLE ONLY practices
    ADD CONSTRAINT fk_3npddgbtuua1e2pj9ufwtsmom FOREIGN KEY (level_id) REFERENCES practice_levels(id);

ALTER TABLE ONLY initial_conditions
    ADD CONSTRAINT fk_3o31e9jy06uoq66ve3os4prwc FOREIGN KEY (experiment_id) REFERENCES experiment_articles(id);

ALTER TABLE ONLY initial_conditions
    ADD CONSTRAINT fk_4b9baf02mln944s21p7sjqrou FOREIGN KEY (unit_id) REFERENCES units(id);

ALTER TABLE ONLY treatment_barriers
    ADD CONSTRAINT fk_4rttd27xmxwq1qrv69vcd0phq FOREIGN KEY (treatment_id) REFERENCES treatments(id);

ALTER TABLE ONLY workshop_sub_indicators
    ADD CONSTRAINT fk_5caj9y0bxnqgkq2ln0tbgrd7g FOREIGN KEY (workshop_id) REFERENCES workshops(id);
ALTER TABLE ONLY workshop_prioritizations
    ADD CONSTRAINT fk_6o1dbd3yt4k8s89xuskycd8mt FOREIGN KEY (portfolio_id) REFERENCES workshop_portfolios(id);

ALTER TABLE ONLY conditions
    ADD CONSTRAINT fk_6oa01nrjf2ak8g4dcr9adbjjv FOREIGN KEY (unit_id) REFERENCES units(id);
ALTER TABLE ONLY experiment_articles
    ADD CONSTRAINT fk_6uua9tagm438jdaxh8dgh4p44 FOREIGN KEY (farming_system_id) REFERENCES farming_systems(id);

ALTER TABLE ONLY workshop_sub_indicators
    ADD CONSTRAINT fk_767tmqbwlk3e9xlyafp4s46t FOREIGN KEY (id) REFERENCES sub_indicators(id);
ALTER TABLE ONLY treatments
    ADD CONSTRAINT fk_7q7awii8op2kg32hwtgthcfg7 FOREIGN KEY (practice_id) REFERENCES practices(id);

ALTER TABLE ONLY workshop_authorities
    ADD CONSTRAINT fk_7wvak5n5b9lxvpx036bgm3glx FOREIGN KEY (user_id) REFERENCES workshop_users(id);

ALTER TABLE ONLY workshop_synergies
    ADD CONSTRAINT fk_90s8ufwevavcmi3m8012b1iwi FOREIGN KEY (workshop_id) REFERENCES workshops(id);

ALTER TABLE ONLY countries
    ADD CONSTRAINT fk_943p9q9y89jyds5xeu05k836g FOREIGN KEY (region_code) REFERENCES regions(code);

ALTER TABLE ONLY production_systems
    ADD CONSTRAINT fk_9k30t5r8a2ef0bbh97f1sq0a0 FOREIGN KEY (category_id) REFERENCES categories(id);

ALTER TABLE ONLY translations
    ADD CONSTRAINT fk_9sf2lkag5oqpyj3mp8xsh5q2c FOREIGN KEY (language_code) REFERENCES languages(code);

ALTER TABLE ONLY synergies
    ADD CONSTRAINT fk_9xhk6o8g6i0wcjhx33oqaqukc FOREIGN KEY (main_practice_id) REFERENCES practices(id);

ALTER TABLE ONLY workshop_portfolios
    ADD CONSTRAINT fk_alckg8nq7vy4au59gw5p9w5bi FOREIGN KEY (workshop_id) REFERENCES workshops(id);

ALTER TABLE ONLY treatment_outcomes
    ADD CONSTRAINT fk_clyb412hu2atb1cpu7nrl13h3 FOREIGN KEY (indicator_id) REFERENCES indicators(id);


ALTER TABLE ONLY workshop_barriers
    ADD CONSTRAINT fk_e5c4kja99v0yhj63up5mg42lg FOREIGN KEY (id) REFERENCES barriers(id);

ALTER TABLE ONLY workshop_barriers
    ADD CONSTRAINT fk_ejdadbrntxyrsc4d0hju0077d FOREIGN KEY (workshop_id) REFERENCES workshops(id);

ALTER TABLE ONLY workshop_experiments
    ADD CONSTRAINT fk_eluktfavk59ncttypf1xsvgc5 FOREIGN KEY (id) REFERENCES experiment_articles(id);

ALTER TABLE ONLY workshop_used_treatments
    ADD CONSTRAINT fk_eqojoa3uhd4etlkh62ylnu4rn FOREIGN KEY (workshop_id) REFERENCES workshops(id);

ALTER TABLE ONLY context_values
    ADD CONSTRAINT fk_ewv24pqg14kq43ukg7fpt3dvv FOREIGN KEY (context_variable_id) REFERENCES context_variables(id);

ALTER TABLE ONLY treatment_barriers
    ADD CONSTRAINT fk_fpn42bnc9djhl93yhs9y098g7 FOREIGN KEY (barrier_id) REFERENCES barriers(id);

ALTER TABLE ONLY treatments
    ADD CONSTRAINT fk_gg0pfh4qt12221uj2111yhvkp FOREIGN KEY (experiment_id) REFERENCES experiment_articles(id);

ALTER TABLE ONLY experiment_articles
    ADD CONSTRAINT fk_go9rbb5rhe5p37vsi7cs6msie FOREIGN KEY (theme_id) REFERENCES practice_themes(id);

ALTER TABLE ONLY treatment_production_systems
    ADD CONSTRAINT fk_i3l0a0y9gpnmw35198v1vbwa1 FOREIGN KEY (production_system_id) REFERENCES production_systems(id);

ALTER TABLE ONLY experiment_context_values
    ADD CONSTRAINT fk_ipjmoiouifrb2h5ia0n1g88v6 FOREIGN KEY (context_value_id) REFERENCES context_values(id);

ALTER TABLE ONLY initial_conditions
    ADD CONSTRAINT fk_jmw0whqem516ibh03pxlr086g FOREIGN KEY (condition_id) REFERENCES conditions(id);

ALTER TABLE ONLY indicator_pillars
    ADD CONSTRAINT fk_kn4vfqlmynkjtnrctg36jvljd FOREIGN KEY (indicator_id) REFERENCES indicators(id);

ALTER TABLE ONLY treatment_outcomes
    ADD CONSTRAINT fk_l3qydnd2rlu7d6g6lty0agx3b FOREIGN KEY (unit_id) REFERENCES units(id);

ALTER TABLE ONLY experiment_context_values
    ADD CONSTRAINT fk_ndfqh9jgmm1htes0me1a3kfou FOREIGN KEY (experiment_article_id) REFERENCES experiment_articles(id);

ALTER TABLE ONLY practice_levels
    ADD CONSTRAINT fk_oy96a8jeoro58xcbfuty40gd FOREIGN KEY (theme_id) REFERENCES practice_themes(id);


ALTER TABLE ONLY experiment_articles
    ADD CONSTRAINT fk_qbntahilqnmg4l5b2b5839l04 FOREIGN KEY (language) REFERENCES languages(code);

ALTER TABLE ONLY workshop_prioritizations
    ADD CONSTRAINT fk_qcwhyh2sav9uuka11bh9460a2 FOREIGN KEY (workshop_id) REFERENCES workshops(id);

ALTER TABLE ONLY workshop_practices
    ADD CONSTRAINT fk_qhsmpy2ve5nfwmk27xmffifj5 FOREIGN KEY (id) REFERENCES practices(id);

ALTER TABLE ONLY practice_context_values
    ADD CONSTRAINT fk_qqg5qvam72ax6sbh64pim4iol FOREIGN KEY (context_value_id) REFERENCES context_values(id);

ALTER TABLE ONLY workshop_used_treatments
    ADD CONSTRAINT fk_qud1fb7nx35vja3v20idgguvc FOREIGN KEY (treatment_id) REFERENCES treatments(id);

ALTER TABLE ONLY synergies
    ADD CONSTRAINT fk_rkcx1o3he0gtm0jqqm4puq14k FOREIGN KEY (second_practice_id) REFERENCES practices(id);

ALTER TABLE ONLY workshops
    ADD CONSTRAINT fk_rqibysak27kfsf9xl8x9clvb7 FOREIGN KEY (location_id) REFERENCES locations(id);

ALTER TABLE ONLY farming_systems
    ADD CONSTRAINT fk_s148ehc4kcqvx0klx5iesden3 FOREIGN KEY (region_code) REFERENCES regions(code);

ALTER TABLE ONLY workshop_experiments
    ADD CONSTRAINT fk_s541pwai7q8pudh7wxbtjtfmd FOREIGN KEY (workshop_id) REFERENCES workshops(id);

ALTER TABLE ONLY locations
    ADD CONSTRAINT fk_s6ji3n59cekij9vts42f9quvl FOREIGN KEY (country_code) REFERENCES countries(code);

ALTER TABLE ONLY workshop_comments
    ADD CONSTRAINT fk_syertki5rx1evo2tlafh3wf7i FOREIGN KEY (workshop_id) REFERENCES workshops(id);

ALTER TABLE ONLY treatment_outcomes
    ADD CONSTRAINT fk_t60x3hfjy89ci2te9gv5hsrnc FOREIGN KEY (treatment_id) REFERENCES treatments(id);

ALTER TABLE ONLY workshop_practices
    ADD CONSTRAINT fk_tbdp7cm6qxygsf7dnrctwn4ws FOREIGN KEY (workshop_id) REFERENCES workshops(id);