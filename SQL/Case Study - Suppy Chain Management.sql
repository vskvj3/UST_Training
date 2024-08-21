-- Case Study - Supply Chain Management
USE warehouseDB

-- db and table already exists
SELECT *
FROM fmcg;

-- a) Find the Shape of the FMCG Table. 
SELECT COUNT(*) AS num_rows
FROM fmcg;

SELECT COUNT(*) AS num_columns
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_NAME = 'fmcg';

-- b) Evaluate the Impact of Warehouse Age on Performance.

-- get age
SELECT
	YEAR(GETDATE()) - wh_est_year AS warehouse_age
FROM
	fmcg;

-- find correlation
SELECT 
	YEAR(GETDATE()) - wh_est_year AS warehouse_age,
	AVG(storage_issue_reported_l3m) AS avg_storage_issue
FROM
	fmcg
WHERE
	wh_est_year IS NOT NULL
GROUP BY
	YEAR(GETDATE()) - wh_est_year
ORDER BY
	warehouse_age;
-- insight:
-- studying the querry response we can say there is positive correlation or direct relation between
-- warehouse age and average storage issues reported


-- c) Analyze the Relationship Between Flood-Proof Status and Transport Issues. 
SELECT
	flood_proof,
	SUM(transport_issue_l1y) AS total_transport_issue
FROM
	fmcg
GROUP BY
	flood_proof;

-- insight:
-- More tranport issues has been found for non-flood proof warehouses.

-- d) Evaluate the Impact of Government Certification on Warehouse Performance. 
SELECT
	approved_wh_govt_certificate, 
	count(*) as num,
	SUM(wh_breakdown_l3m) AS total_breakdowns, 
	SUM(storage_issue_reported_l3m) AS total_storage_issues
FROM 
	fmcg
WHERE
	approved_wh_govt_certificate != 'NA'
GROUP BY
	approved_wh_govt_certificate;
------ another analysis
SELECT
	approved_wh_govt_certificate, 
	wh_breakdown_l3m, 
	SUM(storage_issue_reported_l3m) AS total_storage_issues
FROM 
	fmcg
WHERE
	approved_wh_govt_certificate != 'NA'
GROUP BY
	approved_wh_govt_certificate,
	wh_breakdown_l3m
ORDER BY
	approved_wh_govt_certificate,
	wh_breakdown_l3m;
----
-- insight:
-- no relationship found between govt certificate and total breakdowns.
-- comparing values of approved govt. certificate and sum of breakdowns and sum of average of storage issues, 
-- no relations found among all.

-- e) Determine the Optimal Distance from Hub for Warehouses:
SELECT
	avg(dist_from_hub) avg_dist_from_hub,
	transport_issue_l1y
FROM
	fmcg
GROUP BY
	transport_issue_l1y
ORDER BY
	transport_issue_l1y ASC;
--
-- Insights:
-- The optimal distance from hub must be less than equal 162 for no transportaion issues.

-- f) Identify the Zones with the Most Operational Challenges.
SELECT
	zone,
	SUM(transport_issue_l1y) AS total_transportaion_issue,
	SUM(storage_issue_reported_l3m) AS total_storage_issue,
	SUM(wh_breakdown_l3m) AS total_breakdowns,
	(SUM(transport_issue_l1y) + SUM(storage_issue_reported_l3m) + SUM(wh_breakdown_l3m)) AS total_issues
FROM
	fmcg
GROUP BY
	zone
ORDER BY
	total_transportaion_issue,
	total_storage_issue,
	total_breakdowns;
-- Insights:
-- 
-- g) Identify High-Risk Warehouses Based on Breakdown Incidents and Age. 
-- Question: Which warehouses are at high risk of breakdowns, especially considering their age
-- and the number of breakdown incidents reported in the last 3 months?
SELECT
	Ware_house_ID,
	YEAR(GETDATE()) - wh_est_year AS warehouse_age,
	wh_breakdown_l3m
FROM
	fmcg
ORDER BY
	wh_breakdown_l3m DESC,
	warehouse_age DESC;
-- 2nd:
SELECT 
	Ware_house_ID,
	YEAR(GETDATE()) - wh_est_year AS warehouse_age,
	wh_breakdown_l3m,
	CASE 
		WHEN wh_breakdown_l3m > 4 THEN 'High_Risk'
		WHEN wh_breakdown_l3m > 2 THEN 'Mid_Risk'
		ELSE 'Low_Risk'
	END AS Risk_Level
FROM
	fmcg
WHERE 
	(YEAR(GETDATE()) - wh_est_year) > 15
ORDER BY
	wh_breakdown_l3m DESC;

-- h) Examine the Effectiveness of Warehouse Distribution Strategy. 
-- Question: How effective is the current distribution strategy in each zone,
-- based on the number of distributors connected to warehouses and their respective product weights?
SELECT
	zone, 
	SUM(distributor_num) AS avg_distributors,
	SUM(product_wg_ton) AS avg_product_wg,
	SUM(product_wg_ton) / SUM(distributor_num) AS product_wg_distribution
FROM
	fmcg
GROUP BY
	zone
ORDER BY
	avg_distributors,
	avg_product_wg;
-- Insight:
-- current distribution plan is best in east zone and worst in south and west

-- i) Correlation Between Worker Numbers and Warehouse Issues. 
-- Question: Is there a correlation between the number of workers in a warehouse
-- and the number of storage or breakdown issues reported?
SELECT
	workers_num,
	AVG(storage_issue_reported_l3m) AS avg_storage_issues,
	AVG(wh_breakdown_l3m) AS avg_breakdowns
FROM
	fmcg
WHERE 
	workers_num IS NOT NULL
GROUP BY
	workers_num
ORDER BY
	workers_num;
-- Insight:
-- No relation found

-- j) Assess the Zone-wise Distribution of Flood Impacted Warehouses.
-- Question: Which zones are most affected by flood impacts,
-- and how does this affect their overall operational stability?
select * from fmcg;
SELECT
	zone,
	SUM(flood_impacted) AS total_flood_impacted,
	SUM(wh_breakdown_l3m) AS avg_breakdowns,
	SUM(storage_issue_reported_l3m) AS avg_storage_issues,
	CASE
		WHEN sum(flood_impacted) > 1000 THEN 'Highly_Impacted'
		WHEN sum(flood_impacted) > 500 THEN 'Moderately_Impacted'
		ELSE 'Low_Impacted'
	END AS impact_cat
FROM
	fmcg
GROUP BY
	zone;
-----
SELECT 
	zone,
	COUNT(*) AS total_warehouse,
	SUM(flood_impacted) AS flood_impacted_wh_count,
	SUM(flood_impacted) * 100 / COUNT(*) AS flood_impact_percentage
FROM
	fmcg
GROUP BY
	zone
ORDER BY
	flood_impact_percentage DESC;
-- Insight
-- By analysing all zones flood impacted warehouse percentage can be concluded that
-- north zone warehouse has highly affected by flood.

-- k) Calculate the Cumulative Sum of Total Working Years for Each Zone. 
-- Question: How can you calculate the cumulative sum of total working years for each zone?
-- to find sum
SELECT
	zone,
	SUM(YEAR(GETDATE()) - wh_est_year) AS year_sum
FROM
	fmcg
GROUP BY
	zone;
-- to find cummulative sum
SELECT
	zone,
	YEAR(GETDATE()) - wh_est_year AS curr_age,
	SUM(YEAR(GETDATE()) - wh_est_year)
		OVER(ORDER BY zone ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW)
		AS cummulative_age
FROM
	fmcg;

---
WITH cummulative AS (
SELECT
	zone,
	SUM(YEAR(GETDATE()) - wh_est_year)
		OVER(ORDER BY zone ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW)
		AS cummulative_year_sum
FROM
	fmcg
)
SELECT zone, MAX(cummulative_year_sum)
FROM cummulative
GROUP BY zone;

-- cummulative sum of total workers for each warehouse govt. write a query to calculate
-- cummulative sum of total workers for each warehosue govt.rating
SELECT
	approved_wh_govt_certificate,
	workers_num,
	SUM(workers_num)
		OVER(
			PARTITION BY approved_wh_govt_certificate 
			ORDER BY approved_wh_govt_certificate ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW)
		AS cummulative_workers_sum
FROM
	fmcg;

-- l) Rank Warehouses Based on Distance from the Hub. 
-- Question: How would you rank warehouses based on their distance from the hub?
SELECT
	Ware_house_ID,
	dist_from_hub,
	DENSE_RANK() OVER(ORDER BY dist_from_hub) AS WH_rank
FROM 
	fmcg;

-- m) Calculate the Running Total of Product Weight in Tons for Each Zone:
-- Question: How can you calculate the running total of product weight in tons for each zone?

SELECT
	zone,
	product_wg_ton,
	AVG(product_wg_ton)
		OVER(
			PARTITION BY zone
			ORDER BY zone ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW)
		AS running_product_avg
FROM
	fmcg;

-- n) Rank Warehouses Based on Total Number of Breakdown Incidents. 
-- Question: How can you rank warehouses based on the total number of breakdown incidents in
-- the last 3 months?

SELECT
	Ware_house_ID,
	wh_breakdown_l3m,
	DENSE_RANK() OVER(ORDER BY wh_breakdown_l3m) AS rank
FROM
	fmcg;

-- O) Determine the Relation Between Transport Issues and Flood Impact.
-- Question: Is there any significant relationship between the number of transport issue
-- and flood impact status of warehouses?
SELECT
	flood_impacted,
	SUM(transport_issue_l1y) total_transportation_issue,
	AVG(CAST(transport_issue_l1y AS FLOAT)) avg_transportation_issue
FROM
	fmcg
GROUP BY
	flood_impacted;

-- P) Calculate the Running Total of Product Weight in Tons for Each Zone.
-- Question: How can you calculate the running total of product weight in tons for each zone?

-- Q)Rank Warehouses by Product Weight within Each Zone:
-- Question: How do you rank warehouses based on the product weight they handle within each zone,
-- allowing ties?


SELECT
	Ware_house_ID,
	zone,
	product_wg_ton,
	DENSE_RANK() OVER(PARTITION BY zone ORDER BY product_wg_ton DESC) AS rank
FROM
	fmcg;

-- R) Determine the Most Efficient Warehouses Using DENSE_RANK.
select * from fmcg;
SELECT
	Ware_house_ID,
	transport_issue_l1y,
	storage_issue_reported_l3m,
	wh_breakdown_l3m,
	dist_from_hub,
	DENSE_RANK() OVER(ORDER BY
		transport_issue_l1y,
		storage_issue_reported_l3m,
		wh_breakdown_l3m,
		dist_from_hub) AS rank
FROM
	fmcg;

-- Question: How can you use DENSE_RANK to find the most efficient warehouses in terms of breakdown
-- incidents within each zone?
SELECT
	Ware_house_ID,
	zone,
	wh_breakdown_l3m,
	DENSE_RANK() OVER(PARTITION BY zone ORDER BY wh_breakdown_l3m) AS rank
FROM
	fmcg;

-- S) Calculate the Difference in Storage Issues Using LAG.
-- Question: How can you use LAG to calculate the difference in storage issues reported,
-- between consecutive warehouses within each zone?
SELECT
	Ware_house_ID,
	zone,
	storage_issue_reported_l3m,
	LAG(storage_issue_reported_l3m, 1)
		OVER(PARTITION BY zone ORDER BY Ware_house_ID) AS prev_storage_issue,
	storage_issue_reported_l3m - LAG(storage_issue_reported_l3m, 1)
		OVER(PARTITION BY zone ORDER BY Ware_house_ID) AS diff
FROM
	fmcg;

-- T) Compare Current and Next Warehouse's Distance Using LEAD:
-- Question: How can you compare the distance from the hub of the current warehouse
-- to the next one using LEAD?
SELECT
	Ware_house_ID,
	dist_from_hub,
	LEAD(dist_from_hub, 1, 0)
		OVER(ORDER BY Ware_house_ID) AS next_dist_from_hub,
	dist_from_hub - LEAD(dist_from_hub, 1, 0)
		OVER(ORDER BY Ware_house_ID) AS diff_in_dist
FROM
	fmcg;

-- U) Calculate Cumulative Total of Product Weight by Zone
-- Question: How can you calculate the cumulative total of product weight handled by
-- warehouses within each zone?
SELECT
	zone,
	product_wg_ton,
	SUM(product_wg_ton)
		OVER(
			PARTITION BY zone 
			ORDER BY product_wg_ton ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW)
		AS cummulative_product_wg_sum
FROM
	fmcg;

-- V) Categorize Warehouses by Product Weight. 
-- Question: How can you categorize warehouses as 'Low', 'Medium', or 'High'
-- based on the amount of product weight they handle?
--
WITH td AS(
SELECT 
	ware_house_ID,
	product_wg_ton,
	NTILE(3) OVER(ORDER BY product_wg_ton ASC) AS place
FROM
	fmcg
)
SELECT
	Ware_house_ID,
	product_wg_ton,
	place,
	CASE 
		WHEN place = 1 THEN 'Low'
		WHEN place = 2 THEN 'Medium'
		ELSE 'High'
	END AS product_wg_category
FROM
	td;
 
--Create a Stored Procedure to Fetch High-Risk Warehouses:
-- Question: How would you create a stored procedure that returns all warehouses classified as 'High Risk'
-- based on the number of breakdowns and storage issues??
CREATE PROCEDURE
HighRiskWarehouse AS
BEGIN
    SELECT 
        ware_house_ID,
        transport_issue_l1y,
        storage_issue_reported_l3m,
        wh_breakdown_l3m
    FROM
        fmcg
    WHERE
        (transport_issue_l1y > 0 AND storage_issue_reported_l3m > 10)
        OR wh_breakdown_l3m > 5;
END;

-- Execute stored procedure
EXEC HighRiskWarehouse

-- X) Create a Stored Procedure to Calculate Warehouse Efficiency:
-- Question: How would you create a stored procedure to calculate and
-- return the efficiency of each warehouse based on its product weight and number of distributors?

CREATE PROCEDURE WarehouseEfficiency
AS
BEGIN
	WITH td AS
	(
	SELECT
		Ware_house_ID,
		product_wg_ton,
		distributor_num,
		NTILE(3) OVER(ORDER BY (product_wg_ton) / (distributor_num) DESC)  AS efficiency
	FROM fmcg
	)
	SELECT 
		Ware_house_ID,
		product_wg_ton,
		distributor_num,
		efficiency,
		CASE 
			WHEN efficiency = 1 THEN 'High Efficient'
			WHEN efficiency = 2 THEN 'Medium Effiency'
			ELSE 'Low Efficient'
		END AS wh_efficiency
	FROM td;
END;

DROP PROCEDURE [dbo].WarehouseEfficiency;

EXEC WarehouseEfficiency;

-- Z) Create a View for Warehouse Overview:
-- Question: How can you create a view that shows an overview of warehouses,
-- including their location, product weight, distributer num and flood-proof status?

CREATE VIEW WarehouseView AS
SELECT	
	Ware_house_ID
	Location_type,
	product_wg_ton,
	distributor_num,
	flood_impacted
FROM 
	fmcg;

DROP VIEW WarehouseView;

SELECT
	*
FROM 
	WarehouseView;

-- Insight:
-- above sql shows how to create a view (Temporary Table).
--- view is declared using table or tables or a SQL query.
-- It will not store any records in it.

-- create a view for high capacity ware houses wight > 100 
CREATE VIEW HighCapacityWarehouses AS
SELECT
	*
FROM 
	fmcg
WHERE
	product_wg_ton > 10000;

DROP VIEW HighCapacityWarehouses;

SELECT
	COUNT(*)
FROM
	HighCapacityWarehouses;