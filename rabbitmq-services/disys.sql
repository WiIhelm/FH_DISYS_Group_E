CREATE TABLE energy_usage
(
    hour               TIMESTAMP PRIMARY KEY,
    community_produced NUMERIC(10, 3) NOT NULL DEFAULT 0.000,
    community_used     NUMERIC(10, 3) NOT NULL DEFAULT 0.000,
    grid_used          NUMERIC(10, 3) NOT NULL DEFAULT 0.000
);

CREATE TABLE percentage_service
(
    hour               TIMESTAMP PRIMARY KEY,
    community_depleted NUMERIC(5, 2) NOT NULL,
    grid_portion       NUMERIC(5, 2) NOT NULL
);

INSERT INTO energy_usage (hour, community_produced, community_used, grid_used)
VALUES ('2025-01-10 12:00:00', 14.500, 14.200, 0.300),
       ('2025-01-10 13:00:00', 15.015, 14.033, 2.049),
       ('2025-01-10 14:00:00', 18.050, 18.050, 1.076),
       ('2025-01-10 15:00:00', 16.000, 15.800, 0.100);

INSERT INTO percentage_service (hour, community_depleted, grid_portion)
VALUES ('2025-01-10 12:00:00', 98.00, 2.00),
       ('2025-01-10 13:00:00', 93.50, 12.75),
       ('2025-01-10 14:00:00', 100.00, 5.63),
       ('2025-01-10 15:00:00', 98.75, 0.62);

SELECT * FROM energy_usage;
SELECT * FROM percentage_service;

/* delete everything in the database */
DELETE FROM percentage_service;
DELETE FROM energy_usage;