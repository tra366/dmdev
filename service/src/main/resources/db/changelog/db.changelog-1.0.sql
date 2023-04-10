--liquibase formatted sql

/*
 DROP TABLE IF EXISTS av CASCADE;
 DROP TABLE IF EXISTS series CASCADE;
 DROP TABLE IF EXISTS matrix CASCADE;
 DROP TABLE IF EXISTS chart CASCADE;
 DROP TABLE IF EXISTS source;
 DROP TABLE IF EXISTS avt;
 DROP TABLE IF EXISTS users;
 DROP TABLE IF EXISTS type_series;
 DROP TABLE IF EXISTS name_series;
 */

--changeset tra:1
create TABLE IF NOT EXISTS source
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) UNIQUE NOT NULL
);
--rollback DROP TABLE IF EXISTS source;
--changeset tra:2
create TABLE IF NOT EXISTS avt
(
    id SERIAL PRIMARY KEY,
	resource VARCHAR(20) NOT NULL,
    name VARCHAR(200) NOT NULL,
	type VARCHAR(100) NOT NULL,
	UNIQUE(resource, name)
);
--rollback DROP TABLE IF EXISTS avt;
--changeset tra:3
create TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY,
	username VARCHAR(50) UNIQUE NOT NULL,
	password VARCHAR(50),
    first_name VARCHAR(100),
	last_name VARCHAR(100),
	role VARCHAR(20)
);
--rollback DROP TABLE IF EXISTS user;
--changeset tra:4
create TABLE IF NOT EXISTS chart
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    owner_id INT REFERENCES users(id),
    type_report VARCHAR(50) NOT NULL,
	period_report VARCHAR(50)  NOT NULL,
	object_building VARCHAR(50)  NOT NULL,
	type_building VARCHAR(50)  NOT NULL,
	source_id INT REFERENCES source(id),
	myguid VARCHAR(50) NOT NULL,
	actuality BOOLEAN DEFAULT TRUE
);
--rollback DROP TABLE IF EXISTS chart;
--changeset tra:5
create TABLE IF NOT EXISTS type_series
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) UNIQUE NOT NULL
);
--rollback DROP TABLE IF EXISTS type_series;
--changeset tra:6
create TABLE IF NOT EXISTS name_series
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) UNIQUE NOT NULL
);
--rollback DROP TABLE IF EXISTS name_series;
--changeset tra:7
create TABLE IF NOT EXISTS series
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
	chart_id INT REFERENCES chart(id),
    type_series_id INT REFERENCES type_series(id),
    name_series_id INT REFERENCES name_series(id),
	period_report VARCHAR(50)  NOT NULL,
	source_id INT REFERENCES source(id),
	myguid VARCHAR(50) NOT NULL,
	sort_order INT,
	visible BOOLEAN DEFAULT TRUE,
	actuality BOOLEAN DEFAULT TRUE
);
--rollback DROP TABLE IF EXISTS series;
--changeset tra:8
create TABLE IF NOT EXISTS matrix
(
    id SERIAL PRIMARY KEY,
    type_report VARCHAR(50) NOT NULL,
	period_report VARCHAR(50)  NOT NULL,
	object_building VARCHAR(50)  NOT NULL,
	type_building VARCHAR(50)  NOT NULL,
	source_id INT REFERENCES source(id),
	name_series_id INT REFERENCES name_series(id),
	sql_query TEXT NOT NULL
);
--rollback DROP TABLE IF EXISTS matrix;
--changeset tra:9
create TABLE IF NOT EXISTS av
(
    id BIGSERIAL PRIMARY KEY,
    avt_id INT REFERENCES avt(id),
	chart_id INT  REFERENCES chart(id),
	series_id INT REFERENCES series(id),
	value TEXT,
	UNIQUE(avt_id, chart_id, series_id)
);
--rollback DROP TABLE IF EXISTS av;
