INSERT INTO crop (id, recommended_days_between_each_irrigation_period, name, total_growing_period, reference_water_amount_per_season) VALUES(1, 10, 'Tommato', 100, 400.0);
INSERT INTO crop (id, recommended_days_between_each_irrigation_period, name, total_growing_period, reference_water_amount_per_season) VALUES(2, 10, 'Cotton', 100, 600.0);

INSERT INTO plot (id, area, cultivated_date, total_amount_of_water, crop_id) VALUES(3, 100.0, '2022-07-20 02:00:00.000', 40000.0, 1);
INSERT INTO plot (id, area, cultivated_date, total_amount_of_water, crop_id) VALUES(4, 100.0, '2022-07-10 02:00:00.000', 60000.0, 2);


INSERT INTO sensor (id, "name", status, plot_id) VALUES(5, 'sensor 1', 1, 3);
INSERT INTO sensor (id, "name", status, plot_id) VALUES(6, 'sensor 2', 0, 4);

