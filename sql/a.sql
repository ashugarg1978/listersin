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
