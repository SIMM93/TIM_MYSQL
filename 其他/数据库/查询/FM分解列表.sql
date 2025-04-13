SELECT
	d.* 
FROM
	(
	SELECT
		a.`物品编号` AS 物品编号,
		a.`专业` AS 专业,
		( SELECT z.itemString FROM itemStr_ALL z WHERE z.itemNum = a.物品编号 ) 物品名称,
		a.`最小成本` / 10000 AS 小成本,
		a.`最大成本` / 10000 AS 大成本,
		a.`最小售价` / 10000 AS 小售价,
		a.`最大售价` / 10000 AS 大售价,
		ROUND(( a.`最小售价` * 0.95- a.`最大成本` )/ 10000, 4 ) AS 最低利润,
		ROUND(( ( a.`最小售价` * 0.95 - a.`最大成本` ) / a.`最大成本` )* 100, 2 ) AS 利润率,
		a.`服务器` AS 服务器,
		a.`扫描时间` AS 扫描时间 
	FROM
		全制造所有区 a 
	) d 
WHERE
	1 = 1 
	AND 专业 IN ( '裁缝', '锻造', '制皮', '工程学', '珠宝加工' ) 
		AND `服务器` = "逐風者" 
		AND `物品名称` not like '%石'
	AND `物品编号` > 48000 #	AND 利润率 < 10000
	AND 大成本 <(
	SELECT
		minBuyout / 10000 
	FROM
		TsmScan k 
	WHERE
		1 = 1 
		AND Q = "逐風者" 
		AND k.isLast = 1 
	AND k.itemNum IN ( SELECT itemNum FROM itemStr d WHERE d.itemString = '强效天界精华' )) 
