DECLARE @Databasename VARCHAR(128) = 'GreenTaxiDB';

CREATE DATABASE GreenTaxiDB

USE GreenTaxiDB;

DROP TABLE TaxiTrips;

CREATE TABLE TaxiTrips (
    VendorID INT NULL,
    lpep_pickup_datetime DATETIME NULL,
    lpep_dropoff_datetime DATETIME NULL,
    store_and_fwd_flag CHAR(5) NULL,
    RatecodeID INT NULL,
    PULocationID INT NULL,
    DOLocationID INT NULL,
    passenger_count INT NULL,
    trip_distance FLOAT NULL,
    fare_amount FLOAT NULL,
    extra FLOAT NULL,
    mta_tax FLOAT NULL,
    tip_amount FLOAT NULL,
    tolls_amount FLOAT NULL,
    ehail_fee FLOAT NULL,
    improvement_surcharge FLOAT NULL,
    total_amount FLOAT NULL,
    payment_type INT NULL,
    trip_type INT NULL,
    congestion_surcharge FLOAT NULL
);

BULK INSERT [dbo].TaxiTrips
FROM 'C:\Users\Administrator\Downloads\2021_Green_Taxi_Trip_Data.csv'
WITH
(
    FIELDTERMINATOR = ',',  -- filed terminated by '|' ';' '\t' ' '
    ROWTERMINATOR = '0x0a',   -- '\r\n', '\n', '0x0a'(LF as hex),  carriage and newline
    FIRSTROW = 2            -- Skip header row (if there is one)
);

SELECT * 
FROM TaxiTrips;

-- 1) Find the Shape Table. 
SELECT COUNT(*) AS num_rows
FROM TaxiTrips;

SELECT COUNT(*) AS num_columns
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_NAME = 'TaxiTrips';

-- 2) Show Summary of Green Taxi Rides – Total Rides, Total Customers, Total Sales,
SELECT
	COUNT(*) AS total_rides,
	SUM(passenger_count) AS total_customers,
	SUM(total_amount) AS total_sales
FROM TaxiTrips;

-- 3) Total Rides with Surcharge and its percentage.
SELECT
	COUNT(*) as total_rides,
	SUM(congestion_surcharge) + SUM(improvement_surcharge) as total_surcharge,
	SUM(total_amount) AS total_payment,
	(SUM(congestion_surcharge) + SUM(improvement_surcharge)) / SUM(total_amount) * 100 AS percentage_of_sucharge
FROM TaxiTrips;

-- 3) copy
SELECT 
	VendorID, 
	trip_distance, 
	total_amount, 
	(improvement_surcharge + congestion_surcharge) AS total_surcharge,
	ROUND((improvement_surcharge + congestion_surcharge) / total_amount * 100,2) AS surcharge_percentage
FROM
	TaxiTrips
WHERE 
	total_amount > 0 and improvement_surcharge > 0;


-- 4) Cumulative Sum of Total Fare Amount for Each Pickup Location
SELECT
	PULocationID,
	SUM(total_amount)
		OVER(PARTITION BY PULocationID ORDER BY PULocationID ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW)
		AS cummulative_total_fare
FROM
	TaxiTrips;

-- 5) Which Payment Type is Most Common in Each Drop-off Location
---
WITH PaymentTypeCounts AS (
    SELECT
        DOLocationID,
        payment_type,
        COUNT(*) AS trip_count
    FROM TaxiTrips
	WHERE payment_type IS NOT NULL
    GROUP BY DOLocationID, payment_type
),

RankedPaymentTypes AS (
    SELECT
        DOLocationID,
        payment_type,
        trip_count,
        RANK() OVER (PARTITION BY DOLocationID ORDER BY trip_count DESC) AS rank
    FROM PaymentTypeCounts
)
SELECT
    DOLocationID,
	rank, 
    payment_type AS most_common_payment_type,
    trip_count AS trip_count
FROM RankedPaymentTypes
WHERE rank = 1
ORDER BY DOLocationID;

-- 6). Create a New Column for Trip Distance Band and Show Distribution
ALTER TABLE TaxiTrips 
DROP COLUMN trip_distance_band;

ALTER TABLE TaxiTrips ADD trip_distance_band VARCHAR(30)

UPDATE TaxiTrips
SET trip_distance_band = CASE
    WHEN trip_distance <= 1 THEN '0-1 miles'
    WHEN trip_distance > 1 AND trip_distance <= 5 THEN '1-5 miles'
    WHEN trip_distance > 5 AND trip_distance <= 10 THEN '5-10 miles'
    ELSE '10+ miles'
END;


SELECT
    trip_distance_band,
    COUNT(*) AS trip_count
FROM
    TaxiTrips
GROUP BY
	trip_distance_band
ORDER BY
    trip_distance_band;

-- 7) Find the Most Frequent Pickup Location (Mode) with rides fare greater than average of ride fare

WITH AverageFare AS (
    SELECT AVG(fare_amount) AS avg_fare
    FROM TaxiTrips
),
PickupLocationCounts AS (
    SELECT PULocationID, COUNT(*) AS trip_count
    FROM TaxiTrips, AverageFare 
	WHERE fare_amount > avg_fare
	GROUP BY PULocationID
)
SELECT TOP 1
	PULocationID,
    trip_count
FROM
	PickupLocationCounts
ORDER BY
	trip_count DESC;

-- 8) Show the Rate Code with the Highest Percentage of Usage.
---

---
WITH TotalCount AS
(
SELECT COUNT(*) AS total_trips
FROM TaxiTrips
),
CountRates AS
(
SELECT 
	RatecodeID,
	COUNT(RatecodeID) AS count_ratecode
FROM
	TaxiTrips
GROUP BY
	RatecodeID
)
SELECT TOP 1
	RatecodeID,
	count_ratecode,
	total_trips,
	CAST(count_ratecode AS FLOAT) /  CAST(total_trips AS FLOAT)  * 100 AS percentage
FROM
	TotalCount, CountRates
ORDER BY
	percentage DESC;

-- 9) Show Distribution of Tips, Find the Maximum Chances of Getting a Tip.
SELECT
    COUNT(*) AS trip_count,
    MAX(tip_amount) AS max_tip,
    AVG(tip_amount) AS avg_tip,
    SUM(CASE WHEN tip_amount > 0 THEN 1 ELSE 0 END) * 100.0 / COUNT(*) AS percentage_with_tip
FROM
    TaxiTrips;

-- 
WITH TipDistribution AS (
SELECT
    CASE
        WHEN tip_amount <= 0 THEN 'No Tip'
        WHEN tip_amount > 0 AND tip_amount <= 5 THEN '0-5'
        WHEN tip_amount > 5 AND tip_amount <= 10 THEN '5-10'
        WHEN tip_amount > 10 AND tip_amount <= 20 THEN '10-20'
        ELSE '20+'
    END AS tip_range
FROM
    TaxiTrips

)
SELECT 
	tip_range,
	COUNT(*) AS trip_count
FROM TipDistribution
GROUP BY
	tip_range   
ORDER BY
	 tip_range DESC, trip_count DESC;
select * from TaxiTrips;
-- 10) Calculate the Rank of Trips Based on Fare Amount within Each Pickup Location.
select * from TaxiTrips;
SELECT
	*,
    RANK() OVER (PARTITION BY PULocationID ORDER BY fare_amount DESC) AS fare_rank
FROM
    TaxiTrips
ORDER BY 
	fare_rank;

-- 11) Find Top 20 Most Frequent Rides Routes.
SELECT 
	PULocationID,
	DOLocationID,
	COUNT(*) AS Route_Frequency
FROM
	TaxiTrips
GROUP BY
	PULocationID,
	DOLocationID
ORDER BY Route_Frequency DESC;


-- 12) Calculate the Average Fare of Completed Trips vs. Cancelled Trips
-- show this
WITH TripStatusTable AS
(
SELECT 
	*,
    CASE 
        WHEN trip_distance > 0 THEN 'Completed'
        ELSE 'Cancelled'
    END AS TripStatus
FROM TaxiTrips
)
SELECT
	TripStatus,
	AVG(fare_amount) AS AverageFare
FROM TripStatusTable
GROUP BY TripStatus;

-- 12. Calculate the Average Fare of Completed Trips vs. Cancelled Trips
SELECT
    store_and_fwd_flag AS trip_status,
    AVG(fare_amount) AS average_fare
FROM
    TaxiTrips
GROUP BY
    store_and_fwd_flag;
---

with cancelled_trip as
(
select avg(fare_amount)as tripcanceled 
from taxitrips 
where trip_distance=0
),
completed_trip as
(
select avg(fare_amount)as completetrip 
from taxitrips 
where trip_distance!=0
)
select
	(select completetrip from completed_trip)as trip_completed_avg ,
	(select tripcanceled from cancelled_trip) as trip_cancelled_avg ;

-- 13. Rank the Pickup Locations by Average Trip Distance and Average Total Amount.
select PULocationID, avg(trip_distance) as avg_trip_dist, avg(total_amount) as avg_total_amt,
dense_rank() over(order by avg(trip_distance) desc, avg(total_amount) desc) 
as PU_rank from taxitrips group by PULocationID;

-- 14. Find the Relationship Between Trip Distance & Fare Amount
select trip_distance,avg(fare_amount) as avg_fare from taxitrips 
group by trip_distance order by trip_distance;

-- 15. Identify Trips with Outlier Fare Amounts within Each Pickup Location
select PULocationID, max(total_amount) as total_amount
from taxitrips group by PULocationID order by total_amount desc;

-- 16. Categorize Trips Based on Distance Travelled
select trip_distance ,tile,
case
	when tile>=3 then 'large'
	when tile>=2 then 'medium'
	else 'low'
end as category
from(select trip_distance ,ntile(3) over
(order by trip_distance) as tile
from taxitrips)as TileCTE;

-- 17. Top 5 Busiest Pickup Locations, Drop Locations with Fare less than median total fare
with medianfare as (select PERCENTILE_CONT(0.5) within group (order by total_amount) over() 
as MedianPrice
from taxitrips)
select top(5) PULocationID, DOLocationID, total_amount 
from taxitrips where total_amount<(select max(MedianPrice) from medianfare) and total_amount>0 
order by total_amount desc;

-- 18. Distribution of Payment Types
select payment_type, count(*) as count_type from taxitrips group by(payment_type);

-- 19. Trips with Congestion Surcharge Applied and Its Percentage Count.
select PULocationID, congestion_surcharge,
case
	when total_amount>0 then (congestion_surcharge * 100.0 / total_amount)
	else 0
end as perc
from taxitrips where congestion_surcharge>0;

-- 20. Top 10 Longest Trip by Distance and Its summary about total amount.
select top(10) trip_distance,total_amount from taxitrips order by trip_distance desc;

-- 21. Trips with a Tip Greater than 20% of the Fare
select PULocationID, tip_amount,((tip_amount * 100.0 / total_amount)) as perc
from taxitrips where total_amount>0 and tip_amount>0 and (tip_amount * 100.0 / total_amount)>20;

-- 22. Average Trip Duration by Rate Code
select 
	RatecodeID, 
	avg(datediff(MINUTE,lpep_pickup_datetime,lpep_dropoff_datetime)) as
		avg_diff 
from 
	taxitrips 
group by 
	RatecodeID 
order by 
	RatecodeID;

-- 23. Total Trips per Hour of the Day
select DATEPART(HOUR,lpep_pickup_datetime ) as newt,count(DATEPART(HOUR,lpep_pickup_datetime )) as counts 
from taxitrips group by DATEPART(HOUR,lpep_pickup_datetime ) order by counts desc;

-- 24. Show the Distribution about Busiest Time in a Day.
select CONVERT(VARCHAR(8),lpep_pickup_datetime,108) as newt,count( CONVERT(VARCHAR(8),lpep_pickup_datetime,108)) as counts 
from taxitrips group by lpep_pickup_datetime order by counts desc;