alter table cards add active tinyint;
update cards set active = 1;