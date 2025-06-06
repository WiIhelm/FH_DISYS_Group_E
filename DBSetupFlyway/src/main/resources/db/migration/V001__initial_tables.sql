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