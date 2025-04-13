SELECT
	a.destroyTargetItemNum,
	max(destroyType),
	a.backItemNum,
	( SELECT z.itemString FROM itemStr z WHERE z.itemNum = a.destroyTargetItemNum ) AS 分解物,
	( SELECT z.itemString FROM itemStr z WHERE z.itemNum = a.backItemNum ) AS 得到物,
	sum( a.backItemCount ) AS 得到数量,
	( SELECT count( DISTINCT ( hashCode ) ) FROM destroyingH WHERE 1 = 1 AND destroyTargetItemNum IN ( a.destroyTargetItemNum ) ) 分解次数 ,
ROUND(( SELECT avg( a.price ) FROM QBuy a WHERE a.Q = "水晶之牙" AND a.itemNum = destroyTargetItemNum )/ 10000, 2 ) 来料单个均价 
FROM
	destroyingH a 
WHERE
	1 = 1 #and a.destroyTargetItemNum >35000
#AND a.backItemNum IN ( 43109,39343 )
	
	AND a.destroyTargetItemNum IN ( 36910 ) 
	and destroyTargetItemNum not like '%:%'
#and destroyType  in (31252)
GROUP BY
	a.destroyTargetItemNum,
	a.backItemNum