set @var="水晶之牙"; 

SELECT
	ddd.*,
		round( 总成本 /(大墨水 / 6.3 )/ 10000, 2 ) AS 单张成本,
			ROUND(总成本/大墨水/ 10000, 2 ) AS 大墨水成本,
	( SELECT z.itemString FROM itemStr z WHERE z.itemNum = ddd.destroyTargetItemNum ) 分解物,

	Round( 当前总成本 /(大墨水 / 6.3 )/ 10000, 2 ) AS 当前物价单张成本,
	ROUND(当前总成本/大墨水/ 10000, 2 ) AS 当前物价大墨水成本

FROM
	(
	SELECT
		zde.*,
		来料单个均价 *分解次数* 5 AS 总成本,
		来料当前价格 *分解次数* 5 AS 当前总成本,
		(淡白颜料+碧蓝颜料/ 10 )/ 2 AS 大墨水 
	FROM
		(
		SELECT
			destroyTargetItemNum,
			sum( CASE WHEN a.backItemNum = '43109' THEN a.backItemCount END ) AS 淡白颜料,
			sum( CASE WHEN a.backItemNum = '39343' THEN a.backItemCount END ) AS 碧蓝颜料,
			( SELECT count( DISTINCT ( hashCode ) ) FROM destroyingH WHERE 1 = 1 AND destroyTargetItemNum IN ( a.destroyTargetItemNum ) ) 分解次数,
			( SELECT avg( a.price ) FROM QBuy a WHERE a.Q = @var AND a.itemNum = destroyTargetItemNum ) 来料单个均价,
			( SELECT z.minBuyout FROM TsmScan z WHERE Q = @var AND z.isLast = 1 AND z.itemNum = a.destroyTargetItemNum ) AS 来料当前价格 
		FROM
			destroyingH a 
		WHERE
			1 = 1 
			AND a.backItemNum IN ( 43109, 39343 ) 
		GROUP BY
			destroyTargetItemNum 
		) zde 
	) ddd 
ORDER BY
	当前物价单张成本