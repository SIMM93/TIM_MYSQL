SELECT
	d.* 
FROM
	(
	SELECT
		a.`物品编号` AS 物品编号,
		a.`专业` AS 专业,
		a.`物品名称`,
		a.`最大成本` / 10000 AS 大成本,
		a.`最小售价` / 10000 AS 小售价,
		a.堆叠,
		 ROUND(( a.`最小售价` * 0.95- a.`最大成本` )/ 10000, 4 ) AS 最低利润,
		ROUND(( ( a.`最小售价` * 0.95 - a.`最大成本` ) / a.`最大成本` )* 100, 2 ) AS 利润率,
		a.`服务器` AS 服务器,
		a.`扫描时间` AS 扫描时间 
	FROM
		全制造所有区 a 
	) d 
WHERE
	1 = 1 
	AND 最低利润 > 1 
	AND 利润率 < 10000 
	#	AND 专业  IN ( '工程学' )
	AND 专业 IN ( '', '珠宝加工' ) #珠宝加工	AND `服务器` in ("逐風者","古雷曼格" )
	AND `服务器` IN ( "逐風者" )
#	and `物品名称`  like '%翡翠'
	 #and 物品名称 like '%晶' "
#	AND `物品名称` = "附魔武器 - 狂暴"
#	AND `物品编号` = 58480
	AND `物品编号` > 52000 #	AND `物品编号` < 62200
#AND `物品编号` = 44493 
#and 物品编号 in (SELECT itemNum from foceItem )
	
ORDER BY
	服务器 DESC,
	专业 DESC,
	`最低利润` DESC,
	物品编号 DESC,
	`利润率` DESC;