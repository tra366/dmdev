-- DROP TABLE IF EXISTS public.type_report;
CREATE TABLE IF NOT EXISTS public.type_report
(
    id serial PRIMARY KEY,
    name varchar(200)
);
ALTER TABLE IF EXISTS public.type_report
    OWNER to postgres;
	
-- DROP TABLE IF EXISTS public.period_report;
CREATE TABLE IF NOT EXISTS public.period_report
(
    id serial PRIMARY KEY,
    name varchar(200),
	sort_order int
);
ALTER TABLE IF EXISTS public.period_report
    OWNER to postgres;	
	
-- DROP TABLE IF EXISTS public.object;
CREATE TABLE IF NOT EXISTS public.object
(
    id serial PRIMARY KEY,
    name varchar(200)
);
ALTER TABLE IF EXISTS public.object
    OWNER to postgres;	
	
-- DROP TABLE IF EXISTS public.type_building;
CREATE TABLE IF NOT EXISTS public.type_building
(
    id serial PRIMARY KEY,
    name varchar(200)
);
ALTER TABLE IF EXISTS public.type_building
    OWNER to postgres;		
	
-- DROP TABLE IF EXISTS public.source;
CREATE TABLE IF NOT EXISTS public.source
(
    id serial PRIMARY KEY,
    name varchar(200)
);
ALTER TABLE IF EXISTS public.source
    OWNER to postgres;
	
-- DROP TABLE IF EXISTS public.avt;
CREATE TABLE IF NOT EXISTS public.avt
(
    id serial PRIMARY KEY,
    name varchar(200),
	type varchar(100)
);
ALTER TABLE IF EXISTS public.avt
    OWNER to postgres;	
	
-- DROP TABLE IF EXISTS public.av;
CREATE TABLE IF NOT EXISTS public.av
(
    id bigserial PRIMARY KEY,
    avt_id int,
	value text,
	
	CONSTRAINT fk_av_avt
    FOREIGN KEY(avt_id) 
	REFERENCES avt(id)
	ON DELETE Restrict
);
ALTER TABLE IF EXISTS public.av
    OWNER to postgres;	
	
-- DROP TABLE IF EXISTS public.user;
CREATE TABLE IF NOT EXISTS public.users
(
    id serial PRIMARY KEY,
    first_name varchar(100),
	last_name varchar(100)
);
ALTER TABLE IF EXISTS public.user
    OWNER to postgres;		

-- DROP TABLE IF EXISTS public.chart;
CREATE TABLE IF NOT EXISTS public.chart
(
    id serial PRIMARY KEY,
    name varchar(200),
    owner_id int,
    type_report_id int,
	period_report_id int,
	object_id int,
	type_building_id int,
	source_id int,
	av_id bigint,
	myguid varchar(50),
	actuality Boolean, 
	CONSTRAINT fk_chart_type_report
    FOREIGN KEY(type_report_id) 
	REFERENCES type_report(id)
	ON DELETE Restrict,
	
	CONSTRAINT fk_chart_period_report
    FOREIGN KEY(period_report_id) 
	REFERENCES period_report(id)
	ON DELETE Restrict,
	
	CONSTRAINT fk_chart_object
    FOREIGN KEY(object_id) 
	REFERENCES object(id)
	ON DELETE Restrict,
	
	CONSTRAINT fk_chart_type_building
    FOREIGN KEY(type_building_id) 
	REFERENCES type_building(id)
	ON DELETE Restrict,
	
    CONSTRAINT fk_chart_source
    FOREIGN KEY(source_id) 
	REFERENCES source(id)
	ON DELETE Restrict,
	
	CONSTRAINT fk_chart_av
    FOREIGN KEY(av_id) 
	REFERENCES av(id)
	ON DELETE Restrict,
	
	CONSTRAINT fk_chart_owner
    FOREIGN KEY(owner_id) 
	REFERENCES users(id)
	ON DELETE Restrict
);
ALTER TABLE IF EXISTS public.chart
    OWNER to postgres;

-- DROP TABLE IF EXISTS public.type_series;
CREATE TABLE IF NOT EXISTS public.type_series
(
    id serial PRIMARY KEY,
    name varchar(200)
);
ALTER TABLE IF EXISTS public.type_series
    OWNER to postgres;
	
-- DROP TABLE IF EXISTS public.name_series;
CREATE TABLE IF NOT EXISTS public.name_series
(
    id serial PRIMARY KEY,
    name varchar(200)
);
ALTER TABLE IF EXISTS public.name_series
    OWNER to postgres;	

-- DROP TABLE IF EXISTS public.series;
CREATE TABLE IF NOT EXISTS public.series
(
    id serial PRIMARY KEY,
    name varchar(200),
	chart_id int,
    type_series_id int,
    name_series_id int,
	period_report_id int,
	source_id int,
	av_id bigint,
	myguid varchar(50),
	sort_order int,
	visible Boolean,
	actuality Boolean,
	
	CONSTRAINT fk_series_chart
    FOREIGN KEY(chart_id) 
	REFERENCES chart(id)
	ON DELETE Restrict,
	
	CONSTRAINT fk_series_type_series
    FOREIGN KEY(type_series_id) 
	REFERENCES type_series(id)
	ON DELETE Restrict, 
		
	CONSTRAINT fk_series_name_series
    FOREIGN KEY(name_series_id) 
	REFERENCES name_series(id)
	ON DELETE Restrict,
	
    CONSTRAINT fk_series_period_report
    FOREIGN KEY(period_report_id) 
	REFERENCES period_report(id)
	ON DELETE Restrict,
		
    CONSTRAINT fk_series_source
    FOREIGN KEY(source_id) 
	REFERENCES source(id)
	ON DELETE Restrict,
	
    CONSTRAINT fk_series_av
    FOREIGN KEY(av_id) 
	REFERENCES av(id)
	ON DELETE Restrict
);
ALTER TABLE IF EXISTS public.series
    OWNER to postgres;
	
-- DROP TABLE IF EXISTS public.matrix;
CREATE TABLE IF NOT EXISTS public.matrix
(
    id serial PRIMARY KEY,
    type_report_id int,
	period_report_id int,
	object_id int,
	type_building_id int,
	source_id int,
	name_series_id int,
	sql_query text,
	
	CONSTRAINT fk_matrix_type_report
    FOREIGN KEY(type_report_id) 
	REFERENCES type_report(id)
	ON DELETE Restrict,
	
	CONSTRAINT fk_matrix_period_report
    FOREIGN KEY(period_report_id) 
	REFERENCES period_report(id)
	ON DELETE Restrict,	
		
	CONSTRAINT fk_matrix_object
    FOREIGN KEY(object_id) 
	REFERENCES object(id)
	ON DELETE Restrict,	
			
	CONSTRAINT fk_matrix_type_building
    FOREIGN KEY(type_building_id) 
	REFERENCES type_building(id)
	ON DELETE Restrict,	
				
	CONSTRAINT fk_matrix_source
    FOREIGN KEY(source_id) 
	REFERENCES source(id)
	ON DELETE Restrict,
	
    CONSTRAINT fk_matrix_name_series
    FOREIGN KEY(name_series_id) 
	REFERENCES name_series(id)
	ON DELETE Restrict
);
ALTER TABLE IF EXISTS public.matrix
    OWNER to postgres;
	