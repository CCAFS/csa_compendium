-- Simple correction for imported data 

update practices set level_id = 60 where level_id = 61
delete from practice_levels where id = 61
update practice_levels set theme_id = 10 where theme_id = 11 or  theme_id = 12 or theme_id = 13 or  theme_id = 14
update practice_levels set theme_id = 6 where theme_id = 7 or  theme_id = 8
delete from practice_themes where id = 7 or id = 8 or id = 11 or id = 12 or id = 13 or id = 14