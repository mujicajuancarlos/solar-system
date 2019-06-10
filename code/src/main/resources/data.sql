create table WEATHER
(
	DAY int not null,
	WEATHER int not null
);

create unique index WEATHER_DAY_uindex
	on WEATHER (DAY);

alter table WEATHER
	add constraint WEATHER_pk
		primary key (DAY);

