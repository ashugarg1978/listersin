SELECT accountid, (deleted = 0) AS allitems, (deleted = 0 AND ItemID IS NULL AND schedule > NOW()) AS scheduled, (deleted = 0 AND ItemID IS NOT NULL AND ListingDetails_EndTime > NOW()) AS active, (deleted = 0 AND ItemID IS NOT NULL AND SellingStatus_QuantitySold > 0) AS sold, (deleted = 0 AND ItemID IS NOT NULL AND ListingDetails_EndTime < Now() AND SellingStatus_QuantitySold = 0) AS unsold, (deleted = 0 AND ItemID IS NULL) AS saved, (deleted = 1) AS trash, COUNT(*) AS cnt FROM items GROUP BY accountid, allitems, scheduled, active, sold, unsold, saved, trash;

\q

select count(*) from items;

SELECT 
(ItemID IS NULL AND schedule > NOW()) AS scheduled,
(ItemID IS NOT NULL AND ListingDetails_EndTime > Now()) AS active, 
(ItemID IS NOT NULL AND SellingStatus_QuantitySold > 0) AS sold,
(ItemID IS NOT NULL AND ListingDetails_EndTime < Now() AND SellingStatus_QuantitySold = 0) AS unsold,
(ItemID IS NULL) AS saved, 
COUNT(*) AS cnt

FROM items

GROUP BY scheduled, active, sold, unsold, saved;
