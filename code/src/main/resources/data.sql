
drop table WEATHER;

create table WEATHER
(
	DAY int not null,
	WEATHER varchar(20) not null
);

create unique index WEATHER_DAY_uindex
	on WEATHER (DAY);

alter table WEATHER
	add constraint WEATHER_pk
		primary key (DAY);

