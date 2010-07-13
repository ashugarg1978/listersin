SELECT @minid:=MIN(id) FROM items;

UPDATE items 
SET schedule = DATE_ADD(NOW(), INTERVAL (id-@minid)*37 MINUTE)
WHERE ItemID IS NULL;

SELECT schedule FROM items WHERE schedule != '0000-00-00 00:00:00' ORDER BY schedule ASC  LIMIT 5;
SELECT schedule FROM items WHERE schedule != '0000-00-00 00:00:00' ORDER BY schedule DESC LIMIT 5;
