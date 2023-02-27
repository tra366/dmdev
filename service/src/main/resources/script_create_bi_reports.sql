
/*
 DROP TABLE IF EXISTS public.av CASCADE;
 DROP TABLE IF EXISTS public.series CASCADE;
 DROP TABLE IF EXISTS public.matrix CASCADE;
 DROP TABLE IF EXISTS public.chart CASCADE;
 DROP TABLE IF EXISTS public.type_report;
 DROP TABLE IF EXISTS public.period_report;
 DROP TABLE IF EXISTS public.object;
 DROP TABLE IF EXISTS public.type_building;
 DROP TABLE IF EXISTS public.source;
 DROP TABLE IF EXISTS public.avt;
 DROP TABLE IF EXISTS public.users;
 DROP TABLE IF EXISTS public.type_series;
 DROP TABLE IF EXISTS public.name_series;
 */

-- DROP TABLE IF EXISTS public.type_report;
CREATE TABLE IF NOT EXISTS public.type_report
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) UNIQUE NOT NULL
);
-- DROP TABLE IF EXISTS public.period_report;
CREATE TABLE IF NOT EXISTS public.period_report
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) UNIQUE NOT NULL,
	sort_order INT
);
-- DROP TABLE IF EXISTS public.object;
CREATE TABLE IF NOT EXISTS public.object
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) UNIQUE NOT NULL
);

-- DROP TABLE IF EXISTS public.type_building;
CREATE TABLE IF NOT EXISTS public.type_building
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) UNIQUE NOT NULL
);
-- DROP TABLE IF EXISTS public.source;
CREATE TABLE IF NOT EXISTS public.source
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(200)
);
-- DROP TABLE IF EXISTS public.avt;
CREATE TABLE IF NOT EXISTS public.avt
(
    id SERIAL PRIMARY KEY,
	resource_type_id INTEGER NOT NULL,
    name VARCHAR(200) NOT NULL,
	type VARCHAR(100) NOT NULL,
	UNIQUE(resource_type_id, name)
);
-- DROP TABLE IF EXISTS public.av;
CREATE TABLE IF NOT EXISTS public.av
(
    id BIGSERIAL PRIMARY KEY,
    avt_id INT REFERENCES avt(id) ON DELETE Restrict,
	resource_id INT NOT NULL,
	value TEXT, 
	UNIQUE(avt_id, resource_id)
);
-- DROP TABLE IF EXISTS public.user;
CREATE TABLE IF NOT EXISTS public.users
(
    id SERIAL PRIMARY KEY,
	username VARCHAR(50) UNIQUE NOT NULL,
	password VARCHAR(50),
    first_name VARCHAR(100),
	last_name VARCHAR(100),
	role VARCHAR(20)
);
-- DROP TABLE IF EXISTS public.chart;
CREATE TABLE IF NOT EXISTS public.chart
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    owner_id INT REFERENCES users(id) ON DELETE RESTRICT NOT NULL,
    type_report_id INT REFERENCES type_report(id) ON DELETE RESTRICT NOT NULL,
	period_report_id INT REFERENCES period_report(id) ON DELETE RESTRICT NOT NULL,
	object_id INT REFERENCES object(id) ON DELETE RESTRICT NOT NULL,
	type_building_id INT REFERENCES type_building(id) ON DELETE RESTRICT NOT NULL,
	source_id INT REFERENCES source(id) ON DELETE RESTRICT NOT NULL,
	myguid VARCHAR(50) NOT NULL,
	actuality BOOLEAN DEFAULT TRUE
);
-- DROP TABLE IF EXISTS public.type_series;
CREATE TABLE IF NOT EXISTS public.type_series
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) UNIQUE NOT NULL
);
-- DROP TABLE IF EXISTS public.name_series;
CREATE TABLE IF NOT EXISTS public.name_series
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) UNIQUE NOT NULL
);
-- DROP TABLE IF EXISTS public.series;
CREATE TABLE IF NOT EXISTS public.series
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
	chart_id INT REFERENCES chart(id) ON DELETE RESTRICT,
    type_series_id INT REFERENCES type_series(id) ON DELETE RESTRICT, 
    name_series_id INT REFERENCES name_series(id) ON DELETE RESTRICT,
	period_report_id INT REFERENCES period_report(id) ON DELETE RESTRICT,
	source_id INT REFERENCES source(id) ON DELETE RESTRICT,
	myguid VARCHAR(50) NOT NULL,
	sort_order INT,
	visible BOOLEAN DEFAULT TRUE,
	actuality BOOLEAN DEFAULT TRUE
);
-- DROP TABLE IF EXISTS public.matrix;
CREATE TABLE IF NOT EXISTS public.matrix
(
    id SERIAL PRIMARY KEY,
    type_report_id INT REFERENCES type_report(id) ON DELETE RESTRICT,
	period_report_id INT REFERENCES period_report(id) ON DELETE RESTRICT,
	object_id INT REFERENCES object(id) ON DELETE RESTRICT, 
	type_building_id INT REFERENCES type_building(id) ON DELETE RESTRICT,
	source_id INT REFERENCES source(id) ON DELETE RESTRICT,
	name_series_id INT REFERENCES name_series(id) ON DELETE RESTRICT,
	sql_query TEXT NOT NULL
);

	