-- Database referential integrity constrains

ALTER TABLE ONLY barriers ADD CONSTRAINT barriers_pkey PRIMARY KEY (id);

ALTER TABLE ONLY categories ADD CONSTRAINT categories_pkey PRIMARY KEY (id);

ALTER TABLE ONLY conditions ADD CONSTRAINT conditions_pkey PRIMARY KEY (id);

ALTER TABLE ONLY context_values ADD CONSTRAINT context_values_pkey PRIMARY KEY (id);

ALTER TABLE ONLY context_variables ADD CONSTRAINT context_variables_pkey PRIMARY KEY (id);

ALTER TABLE ONLY countries ADD CONSTRAINT countries_pkey PRIMARY KEY (code);

ALTER TABLE ONLY experiment_articles ADD CONSTRAINT experiment_articles_pkey PRIMARY KEY (id);

ALTER TABLE ONLY experiment_contexts ADD CONSTRAINT experiment_contexts_pkey PRIMARY KEY (id);

ALTER TABLE ONLY farming_systems ADD CONSTRAINT farming_systems_pkey PRIMARY KEY (id);

ALTER TABLE ONLY indicator_pillars ADD CONSTRAINT indicator_pillars_pkey PRIMARY KEY (id);

ALTER TABLE ONLY indicators ADD CONSTRAINT indicators_pkey PRIMARY KEY (id);

ALTER TABLE ONLY initial_conditions ADD CONSTRAINT initial_conditions_pkey PRIMARY KEY (id);

ALTER TABLE ONLY languages ADD CONSTRAINT languages_pkey PRIMARY KEY (code);

ALTER TABLE ONLY locations ADD CONSTRAINT locations_pkey PRIMARY KEY (id);

ALTER TABLE ONLY practice_levels ADD CONSTRAINT practice_levels_pkey PRIMARY KEY (id);

ALTER TABLE ONLY practice_themes ADD CONSTRAINT practice_themes_pkey PRIMARY KEY (id);

ALTER TABLE ONLY practices ADD CONSTRAINT practices_pkey PRIMARY KEY (id);

ALTER TABLE ONLY production_systems ADD CONSTRAINT production_systems_pkey PRIMARY KEY (id);

ALTER TABLE ONLY regions ADD CONSTRAINT regions_pkey PRIMARY KEY (code);

ALTER TABLE ONLY sub_indicators ADD CONSTRAINT sub_indicators_pkey PRIMARY KEY (id);

ALTER TABLE ONLY synergies ADD CONSTRAINT synergies_pkey PRIMARY KEY (id);

ALTER TABLE ONLY translations ADD CONSTRAINT translations_pkey PRIMARY KEY (id);

ALTER TABLE ONLY treatment_barriers ADD CONSTRAINT treatment_barriers_pkey PRIMARY KEY (id);

ALTER TABLE ONLY treatment_outcomes ADD CONSTRAINT treatment_outcomes_pkey PRIMARY KEY (id);

ALTER TABLE ONLY treatments ADD CONSTRAINT treatments_pkey PRIMARY KEY (id);

ALTER TABLE ONLY units ADD CONSTRAINT units_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshop_authorities ADD CONSTRAINT workshop_authorities_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshop_barriers ADD CONSTRAINT workshop_barriers_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshop_comments ADD CONSTRAINT workshop_comments_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshop_experiments ADD CONSTRAINT workshop_experiments_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshop_pillars ADD CONSTRAINT workshop_pillars_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshop_portfolios ADD CONSTRAINT workshop_portfolios_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshop_practices ADD CONSTRAINT workshop_practices_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshop_prioritizations ADD CONSTRAINT workshop_prioritizations_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshop_sub_indicators ADD CONSTRAINT workshop_sub_indicators_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshop_synergies ADD CONSTRAINT workshop_synergies_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshop_users ADD CONSTRAINT workshop_users_pkey PRIMARY KEY (id);

ALTER TABLE ONLY workshops ADD CONSTRAINT workshops_pkey PRIMARY KEY (id);

ALTER TABLE ONLY sub_indicators ADD CONSTRAINT fk_1kqakbvk3ff3p4nsh6nrgv0le FOREIGN KEY (indicator_id) REFERENCES indicators (id);

ALTER TABLE ONLY workshop_synergies ADD CONSTRAINT fk_205kejgb70l7jcnltnrfv9c7x FOREIGN KEY (id) REFERENCES synergies (id);

ALTER TABLE ONLY experiment_contexts ADD CONSTRAINT fk_2d28jmvyfvihfate9cti6xgbw FOREIGN KEY (farming_system_id) REFERENCES farming_systems (id);

ALTER TABLE ONLY initial_conditions ADD CONSTRAINT fk_33672548byds235b3dcpm3eg7 FOREIGN KEY (experiment_context_id) REFERENCES experiment_contexts (id);

ALTER TABLE ONLY practice_context_values ADD CONSTRAINT fk_3dortc5qg58e0hmhutdqrbuu6 FOREIGN KEY (practice_id) REFERENCES practices (id);

ALTER TABLE ONLY treatments ADD CONSTRAINT fk_3e5t67g283drsgjvkayl4a0gf FOREIGN KEY (control_id) REFERENCES treatments (id);

ALTER TABLE ONLY workshop_prioritizations ADD CONSTRAINT fk_3hx8obgmia4cg96fu1ao5nhg1 FOREIGN KEY (practice_id) REFERENCES practices (id);

ALTER TABLE ONLY experiment_contexts ADD CONSTRAINT fk_3ivrvfb1uykj7dq3d1avgase0 FOREIGN KEY (location_id) REFERENCES locations (id);

ALTER TABLE ONLY workshop_pillars ADD CONSTRAINT fk_3jm6kgq071032h0mwwlu5khgh FOREIGN KEY (workshop_id) REFERENCES workshops (id);

ALTER TABLE ONLY practices ADD CONSTRAINT fk_3npddgbtuua1e2pj9ufwtsmom FOREIGN KEY (level_id) REFERENCES practice_levels (id);

ALTER TABLE ONLY initial_conditions ADD CONSTRAINT fk_4b9baf02mln944s21p7sjqrou FOREIGN KEY (unit_id) REFERENCES units (id);

ALTER TABLE ONLY treatment_barriers ADD CONSTRAINT fk_4rttd27xmxwq1qrv69vcd0phq FOREIGN KEY (treatment_id) REFERENCES treatments (id);

ALTER TABLE ONLY workshop_sub_indicators ADD CONSTRAINT fk_5caj9y0bxnqgkq2ln0tbgrd7g FOREIGN KEY (workshop_id) REFERENCES workshops (id);

ALTER TABLE ONLY treatment_outcomes ADD CONSTRAINT fk_5r12n8gi294vn5a5s2llima88 FOREIGN KEY (production_system_id) REFERENCES production_systems (id);

ALTER TABLE ONLY workshop_prioritizations ADD CONSTRAINT fk_6o1dbd3yt4k8s89xuskycd8mt FOREIGN KEY (portfolio_id) REFERENCES workshop_portfolios (id);

ALTER TABLE ONLY conditions ADD CONSTRAINT fk_6oa01nrjf2ak8g4dcr9adbjjv FOREIGN KEY (unit_id) REFERENCES units (id);

ALTER TABLE ONLY workshop_sub_indicators ADD CONSTRAINT fk_767tmqbwlk3e9xlyafp4s46t FOREIGN KEY (id) REFERENCES sub_indicators (id);

ALTER TABLE ONLY treatments ADD CONSTRAINT fk_7bjc9mdti995r27r1js4pdj1s FOREIGN KEY (context_id) REFERENCES experiment_contexts (id);

ALTER TABLE ONLY workshop_authorities ADD CONSTRAINT fk_7wvak5n5b9lxvpx036bgm3glx FOREIGN KEY (user_id) REFERENCES workshop_users (id);

ALTER TABLE ONLY experiment_contexts ADD CONSTRAINT fk_8c84silrs9u75bplmqnkmbshw FOREIGN KEY (experiment_id) REFERENCES experiment_articles (id);

ALTER TABLE ONLY workshop_synergies ADD CONSTRAINT fk_90s8ufwevavcmi3m8012b1iwi FOREIGN KEY (workshop_id) REFERENCES workshops (id);

ALTER TABLE ONLY countries ADD CONSTRAINT fk_943p9q9y89jyds5xeu05k836g FOREIGN KEY (region_code) REFERENCES regions (code);

ALTER TABLE ONLY production_systems ADD CONSTRAINT fk_9k30t5r8a2ef0bbh97f1sq0a0 FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE ONLY translations ADD CONSTRAINT fk_9sf2lkag5oqpyj3mp8xsh5q2c FOREIGN KEY (language_code) REFERENCES languages (code);

ALTER TABLE ONLY synergies ADD CONSTRAINT fk_9xhk6o8g6i0wcjhx33oqaqukc FOREIGN KEY (main_practice_id) REFERENCES practices (id);

ALTER TABLE ONLY experiment_production_systems ADD CONSTRAINT fk_aeilvrwpqdviugffpuftdq9kb FOREIGN KEY (production_system_id) REFERENCES production_systems (id);

ALTER TABLE ONLY workshop_portfolios ADD CONSTRAINT fk_alckg8nq7vy4au59gw5p9w5bi FOREIGN KEY (workshop_id) REFERENCES workshops (id);

ALTER TABLE ONLY treatment_outcomes ADD CONSTRAINT fk_aqd6q80ru4n5oh7hvre05wfxs FOREIGN KEY (sub_indicator_id) REFERENCES sub_indicators (id);

ALTER TABLE ONLY treatment_practices ADD CONSTRAINT fk_dlnc9akpl232ws0f6q13ucvoa FOREIGN KEY (practice_id) REFERENCES practices (id);

ALTER TABLE ONLY workshop_barriers ADD CONSTRAINT fk_e5c4kja99v0yhj63up5mg42lg FOREIGN KEY (id) REFERENCES barriers (id);

ALTER TABLE ONLY workshop_barriers ADD CONSTRAINT fk_ejdadbrntxyrsc4d0hju0077d FOREIGN KEY (workshop_id) REFERENCES workshops (id);

ALTER TABLE ONLY workshop_experiments ADD CONSTRAINT fk_eluktfavk59ncttypf1xsvgc5 FOREIGN KEY (id) REFERENCES experiment_articles (id);

ALTER TABLE ONLY workshop_used_treatments ADD CONSTRAINT fk_eqojoa3uhd4etlkh62ylnu4rn FOREIGN KEY (workshop_id) REFERENCES workshops (id);

ALTER TABLE ONLY context_values ADD CONSTRAINT fk_ewv24pqg14kq43ukg7fpt3dvv FOREIGN KEY (context_variable_id) REFERENCES context_variables (id);

ALTER TABLE ONLY treatment_barriers ADD CONSTRAINT fk_fpn42bnc9djhl93yhs9y098g7 FOREIGN KEY (barrier_id) REFERENCES barriers (id);

ALTER TABLE ONLY experiment_articles ADD CONSTRAINT fk_go9rbb5rhe5p37vsi7cs6msie FOREIGN KEY (theme_id) REFERENCES practice_themes (id);

ALTER TABLE ONLY experiment_context_values ADD CONSTRAINT fk_ipjmoiouifrb2h5ia0n1g88v6 FOREIGN KEY (context_value_id) REFERENCES context_values (id);

ALTER TABLE ONLY experiment_production_systems ADD CONSTRAINT fk_irv14g9pwo65kgoprev13f46b FOREIGN KEY (experiment_id) REFERENCES experiment_contexts (id);

ALTER TABLE ONLY initial_conditions ADD CONSTRAINT fk_jmw0whqem516ibh03pxlr086g FOREIGN KEY (condition_id) REFERENCES conditions (id);

ALTER TABLE ONLY indicator_pillars ADD CONSTRAINT fk_kn4vfqlmynkjtnrctg36jvljd FOREIGN KEY (indicator_id) REFERENCES indicators (id);

ALTER TABLE ONLY treatment_outcomes ADD CONSTRAINT fk_l3qydnd2rlu7d6g6lty0agx3b FOREIGN KEY (unit_id) REFERENCES units (id);

ALTER TABLE ONLY experiment_context_values ADD CONSTRAINT fk_ndfqh9jgmm1htes0me1a3kfou FOREIGN KEY (experiment_article_id) REFERENCES experiment_articles (id);

ALTER TABLE ONLY practice_levels ADD CONSTRAINT fk_oy96a8jeoro58xcbfuty40gd FOREIGN KEY (theme_id) REFERENCES practice_themes (id);

ALTER TABLE ONLY experiment_articles ADD CONSTRAINT fk_qbntahilqnmg4l5b2b5839l04 FOREIGN KEY (language) REFERENCES languages (code);

ALTER TABLE ONLY workshop_prioritizations ADD CONSTRAINT fk_qcwhyh2sav9uuka11bh9460a2 FOREIGN KEY (workshop_id) REFERENCES workshops (id);

ALTER TABLE ONLY workshop_practices ADD CONSTRAINT fk_qhsmpy2ve5nfwmk27xmffifj5 FOREIGN KEY (id) REFERENCES practices (id);

ALTER TABLE ONLY practice_context_values ADD CONSTRAINT fk_qqg5qvam72ax6sbh64pim4iol FOREIGN KEY (context_value_id) REFERENCES context_values (id);

ALTER TABLE ONLY workshop_used_treatments ADD CONSTRAINT fk_qud1fb7nx35vja3v20idgguvc FOREIGN KEY (treatment_id) REFERENCES treatments (id);

ALTER TABLE ONLY synergies ADD CONSTRAINT fk_rkcx1o3he0gtm0jqqm4puq14k FOREIGN KEY (second_practice_id) REFERENCES practices (id);

ALTER TABLE ONLY workshops ADD CONSTRAINT fk_rqibysak27kfsf9xl8x9clvb7 FOREIGN KEY (location_id) REFERENCES locations (id);

ALTER TABLE ONLY treatment_practices ADD CONSTRAINT fk_ru3kkiui56ptt1mvojo14dpyu FOREIGN KEY (treatment_id) REFERENCES treatments (id);

ALTER TABLE ONLY farming_systems ADD CONSTRAINT fk_s148ehc4kcqvx0klx5iesden3 FOREIGN KEY (region_code) REFERENCES regions (code);

ALTER TABLE ONLY workshop_experiments ADD CONSTRAINT fk_s541pwai7q8pudh7wxbtjtfmd FOREIGN KEY (workshop_id) REFERENCES workshops (id);

ALTER TABLE ONLY locations ADD CONSTRAINT fk_s6ji3n59cekij9vts42f9quvl FOREIGN KEY (country_code) REFERENCES countries (code);

ALTER TABLE ONLY workshop_comments ADD CONSTRAINT fk_syertki5rx1evo2tlafh3wf7i FOREIGN KEY (workshop_id) REFERENCES workshops (id);

ALTER TABLE ONLY treatment_outcomes ADD CONSTRAINT fk_t60x3hfjy89ci2te9gv5hsrnc FOREIGN KEY (treatment_id) REFERENCES treatments (id);

ALTER TABLE ONLY workshop_practices ADD CONSTRAINT fk_tbdp7cm6qxygsf7dnrctwn4ws FOREIGN KEY (workshop_id) REFERENCES workshops (id);

