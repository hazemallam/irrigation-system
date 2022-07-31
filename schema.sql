
CREATE TABLE crop (
	id NUMBER NOT NULL,
	recommended_days_between_each_irrigation_period NUMBER NOT NULL,
	"name" varchar(255) NOT NULL,
	total_growing_period NUMBER NOT NULL,
	reference_water_amount_per_season NUMBER NOT NULL,
	CONSTRAINT crop_pkey PRIMARY KEY (id)
);

CREATE TABLE plot (
	id NUMBER NOT NULL,
	area float8 NOT NULL,
	cultivated_date timestamp NOT NULL,
	total_amount_of_water NUMBER NOT NULL,
	crop_id NUMBER NOT NULL,
	CONSTRAINT plot_pkey PRIMARY KEY (id),
	CONSTRAINT fkot9wcqqqaj1vleg46kkqblgn4 FOREIGN KEY (crop_id) REFERENCES crop(id)
);

CREATE TABLE sensor (
	id NUMBER NOT NULL,
	"name" varchar(255) NOT NULL,
	status NUMBER NOT NULL,
	plot_id NUMBER NULL,
	CONSTRAINT sensor_pkey PRIMARY KEY (id),
	CONSTRAINT uk_qvn53tw3wvo2666envx77r0ad UNIQUE (plot_id),
	CONSTRAINT fk3lar9dla9a54pqybrbq2q94g2 FOREIGN KEY (plot_id) REFERENCES plot(id)
);

CREATE TABLE slot (
	id NUMBER NOT NULL,
	amount_of_water NUMBER NULL,
	start_date timestamp NULL,
	time_slot time NULL,
	status NUMBER NULL,
	plot_id NUMBER NULL,
	sensor NUMBER NULL,
	CONSTRAINT slot_pkey PRIMARY KEY (id),
	CONSTRAINT fk125ys8oepve7b13n71vx34v78 FOREIGN KEY (sensor) REFERENCES sensor(id),
	CONSTRAINT fk4rin8wcy42kwd9r5g2m50phfg FOREIGN KEY (plot_id) REFERENCES plot(id)
);