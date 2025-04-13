package com.tsm.service.impl;

import com.tsm.entity.*;
import com.tsm.repository.*;
import com.tsm.service.TsmGetMsgService;
import lombok.AllArgsConstructor;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;


@AllArgsConstructor
@Service
public class TsmGetMsgServiceImpl implements TsmGetMsgService {
    private final TsmScanRepository tsmScanRepository;
    private final ItemRepository itemRepository;
    private final HasCheckRepository hasCheckRepository;
    private final FoceItemRepository foceItemRery;
    private final ItemCRepository itemCReposit2ory;
    private final BagHaveRepository bagHaveRepository;
    private final QBuyRepository qBuyRepository;
    private final QSaleRepository qSaleRepository;
    private final DestroyingHRepository destroyingHRepository;
    private final DirnewRepository dirnewRepository;
    private final Time2OutRepository time2OutRepository;


    final String trash = "trashIdList";
    final String notInGuild = "notInGuild";
    final String TsmDBPath = "TsmDBPath";
    final String serverList = "serverList";
    final String[] zy = {"Horde", "Alliance"};

    @PostConstruct
    public void init() throws IOException {
        System.out.println("启动！");
        /*System.out.println("当前时间: " + LocalDateTime.now());

        runtime();*/
    }

    @Override
    public void runtime() throws IOException {
        String currentDirectory = System.getProperty("user.dir");

        HashMap<String, String> configMap = readFileFromFileSystem(currentDirectory + "//TsmEConfig.txt");
        String path = configMap.get(TsmDBPath).trim();

        String[] Qlist = getList(configMap.get(serverList)).toArray(new String[0]);

        HashSet<String> trash_item = getList(configMap.get(trash));
        trash_item.addAll(getList(configMap.get(notInGuild)));




        String k = getDBMSg(path, Qlist, trash_item);

        k = k.replace("\r", "\n");
        if (!k.isEmpty()) {
            System.out.println("导出成功");
            System.out.print(k);
        } else {
            System.out.println("无导出");
        }
        for (String q : Qlist) {
            System.out.println(q);
            System.out.println(String.format("%-10s %-9s %-10s %-10s %-10s %-10s %-10s %-10s",
                    "ItemNum", "数量", "名称", "购买价格", "现在价格", "利润", "利润率", "仓库名"));
            sendMsg(q);
        }

      /*  }
        System.out.println("结束到处");*/
    }

    private void sendMsg(String Q) {
        List<Timt2out> timt2outList = time2OutRepository.gettimeOut(Q);

        for (Timt2out item : timt2outList) {

            System.out.println(String.format("%-10s %-10.2f %-10s %-15.2f %-10.2f %-10.2f %-10.2f %-10s",
                    item.getItemNum(), item.getSumda(), item.getItemString(),
                    item.getBuyavgprice(), item.getNowprice(), item.getLr(), item.getLrl(), item.getWho()));
            System.out.println("---------------------------------------------------------------------------------------------------");

        }

    }

    //遍历文件
    public HashSet<String> de() {
        HashSet<String> d = new HashSet<>();
        Path directory = Paths.get("C:\\Users\\roma\\Desktop\\TIM\\TSM_Backup");  // 替换为你想遍历的目录路径
        try {

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory, "*.lua")) {
                for (Path file : stream) {
                    if (Files.isRegularFile(file)) {
                        // 获取文件的最后修改时间
                        FileTime lastModifiedTime = Files.getLastModifiedTime(file);
                        d.add(file.toAbsolutePath().toString());
                        System.out.println("文件路径: " + file + ", 修改时间: " + lastModifiedTime);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return d;
    }


    /**
     * 提取配置文件
     *
     * @param filePath 路径
     * @return
     * @throws IOException
     */
    public HashMap<String, String> readFileFromFileSystem(String filePath) throws IOException {
        String k = new String(Files.readAllBytes(Paths.get(filePath)));
        HashMap<String, String> getMap = new HashMap<>();

        getMap.put(trash, getMap(trash, k));
        getMap.put(notInGuild, getMap(notInGuild, k));
        getMap.put(TsmDBPath, getMap(TsmDBPath, k));

        return getMap;
    }

    public HashSet<String> getList(String str) {
        HashSet<String> trash_item = new HashSet<>();
        String[] list = str.split(",");
        for (String item : list) {
            trash_item.add(item.trim());
        }
        return trash_item;
    }

    private String getMap(String key, String str) {
        String ret = "";
        String s = key + "@";
        String e = key + "#";
        int si = str.indexOf(s) + s.length();
        int ei = str.indexOf(e);
        ret = str.substring(si, ei);
        return ret;
    }

    @Override
    public String getDBMSg(String filePathL, String[] Q_list, HashSet<String> trash_item) {
        int isneedgo = 0;
        int isLast = 0;

        StringBuilder retMsg = new StringBuilder();
        Globals globals = JsePlatform.standardGlobals();
        String[] filePathList = filePathL.split(",");

        for (String filePath : filePathList) {
            System.out.println("开始读取" + filePath);
            if (filePath.isEmpty()) {
                continue;
            }
            filePath = filePath.replace("\r", "");
            filePath = filePath.replace("\n", "");

            File file = new File(filePath);
            String lastModified = String.valueOf(file.lastModified());
            if (lastModified.equals("0")) {
                System.out.println("未访问到文件" + filePath);
                continue;
            }
            String pathHas = String.valueOf(filePath.hashCode());
            String fixhash = String.valueOf(lastModified.hashCode());
            String ke = dirnewRepository.countByHashCode(pathHas, fixhash);

            if (ke != null) {
                continue;
            } else {
                Dirnew d = new Dirnew();
                d.setLastfix(lastModified);
                d.setLastfixhash(fixhash);
                d.setPathurl(filePath);
                d.setPathhash(pathHas);
                dirnewRepository.save(d);
            }


            try {
                // 读取Lua文件，以UTF-8编码读取
                FileInputStream fis = new FileInputStream(filePath);
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder luaScript = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    luaScript.append(line).append("\n");
                }
                br.close();

                // 加并执行Lua脚本
                LuaValue chunk = globals.load(luaScript.toString(), "TSMItemInfoDB.lua");
                chunk.call();
                // 获取Lua表TSMItemInfoDB
                LuaValue tsmItemInfoDB = globals.get("TSMItemInfoDB");
                LuaValue tradeSkillMasterDB = globals.get("TradeSkillMasterDB");
                //垃圾数据列表


                for (String qy : zy) {

                    //AH根据区域循环导入
                    if (tradeSkillMasterDB.istable()) {
                        LuaTable tsmDB = (LuaTable) tradeSkillMasterDB;
                        for (String Q : Q_list) {
                            String se = "f@" + qy + " - " + Q + "@internalData@csvAuctionDBScan";
                            LuaValue itemStrings = tsmDB.get(se);
                            if (!itemStrings.isnil()) {
                                String hashCode = String.valueOf(itemStrings.hashCode());
                                //不允许重复导入
                                //逻辑，不允许区内容重复
                                int isHaveId = hasCheckRepository.hasCheckRetId(Q, hashCode);
                                if (isHaveId == 1) {
                                    //不导入
                                    retMsg.append("已有数据，AH未更新").append(Q).append("\r");
                                    continue;
                                } else {
                                    //更新控制
                                    hasC hasCEntity = new hasC();
                                    hasCEntity.setVer(Q);
                                    hasCEntity.setChecktt(hashCode);
                                    hasCheckRepository.save(hasCEntity);
                                }
                                isneedgo++;
                                String dataStr = itemStrings.tojstring();
                                if (dataStr.length() > 10) {
                                    String[] stringList = dataStr.split("\\ni:");
                                    String time = getTime(stringList[1].split(",")[5]);
                                    System.out.println(Q + ":AH扫描时间为" + time);
                                    isLast = (tsmScanRepository.q_insert_isLast(Q, time));
                                    List<TsmScan> tsmscanList = in_TsmScan(stringList, Q, isLast, time, trash_item);
                                    tsmScanRepository.save(tsmscanList);
                                    retMsg.append(Q).append(":新增AH数据: ").append(tsmscanList.size()).append(" 条").append("\r");
                                }
                            }
                        }
                    }
                }
                //购买导入
                if (tradeSkillMasterDB.istable()) {
                    LuaTable tsmDB = (LuaTable) tradeSkillMasterDB;
                    for (String Q : Q_list) {
                        String se = "r@" + Q + "@internalData@csvBuys";
                        LuaValue itemStrings = tsmDB.get(se);
                        if (!itemStrings.isnil()) {
                            String dataStr = itemStrings.tojstring();
                            if (dataStr.length() > 2) {
                                String[] stringList = dataStr.split("\\ni:");
                                HashSet<String> hashSet = qBuyRepository.getAllHashCode(Q);
                                List<QBuy> tsmscanList = in_QBuy(stringList, Q, hashSet);
                                qBuyRepository.save(tsmscanList);
                                retMsg.append(Q).append(":新增购买导入: ").append(tsmscanList.size()).append(" 条").append("\r");
                            }
                        }
                    }
                }
                //卖出信息
                if (tradeSkillMasterDB.istable()) {
                    LuaTable tsmDB = (LuaTable) tradeSkillMasterDB;
                    for (String Q : Q_list) {
                        String se = "r@" + Q + "@internalData@csvSales";
                        LuaValue itemStrings = tsmDB.get(se);
                        if (!itemStrings.isnil()) {
                            String dataStr = itemStrings.tojstring();
                            if (dataStr.length() > 2) {
                                String[] stringList = dataStr.split("\\ni:");
                                HashSet<String> hashSet = qSaleRepository.getAllHashCode(Q);
                                List<QSale> tsmscanList = in_QSela(stringList, Q, hashSet);
                                qSaleRepository.save(tsmscanList);
                                retMsg.append(Q).append(":新增销售导入: ").append(tsmscanList.size()).append(" 条").append("\r");
                            }
                        }
                    }
                }

                //分解信息
                if (tradeSkillMasterDB.istable()) {
                    LuaTable tsmDB = (LuaTable) tradeSkillMasterDB;
                    String se = "g@ @internalData@destroyingHistory";
                    LuaValue itemStrings = tsmDB.get(se);
                    List<DestroyingH> getDesList = getdestroyingHList(itemStrings);
                    destroyingHRepository.save(getDesList);
                    retMsg.append("分解信息增加: ").append(getDesList.size()).append(" 条").append("\r");
                }

                //导入道具编号
                if (tsmItemInfoDB.istable() && isneedgo > 0) {
                    LuaTable table = (LuaTable) tsmItemInfoDB;
                    String[] key = getItemNum(table.get("itemStrings"));
                    String[] value = getItemNum(table.get("names"));
                    List<ItemStrEntity> itemStrEntityList = update_item(key, value);
                    itemRepository.save(itemStrEntityList);
                    retMsg.append("更新道具: ").append(itemStrEntityList.size()).append(" 条\r");
                }
                //TSM关注道具
                if (tradeSkillMasterDB.istable() && isneedgo > 0) {
                    List<FoceItem> foceItems = getFoceList(tradeSkillMasterDB);
                    foceItemRery.deleteAll();
                    foceItemRery.save(foceItems);
                    retMsg.append("更新关注道具: ").append(foceItems.size()).append(" 条\r");

                }

                //可制作列表
                if (tradeSkillMasterDB.istable() && isneedgo > 0) {
                    LuaTable tsmDB = (LuaTable) tradeSkillMasterDB;
                    for (String qy : zy) {


                        for (String Q : Q_list) {
                            LuaValue itemStrings = tsmDB.get("f@" + qy + " - " + Q + "@internalData@crafts");
                            List<ItemC> itemCSList = getCList(itemStrings, Q);
                            int k = itemCSList.size();
                            if (k > 0) {
                                itemCReposit2ory.save(itemCSList);
                                retMsg.append(Q).append(":更新制作列表: ").append(k).append(" 条\r");
                            }
                        }
                    }
                }
                //背包数据
                List<BagHave> bagHaveList = setAllBag(tradeSkillMasterDB);
                if (bagHaveList.size() > 0) {
                    bagHaveRepository.deleteAll();
                    bagHaveRepository.save(bagHaveList);
                }


            } catch (IOException e) {
                System.out.println("未找到文件：TradeSkillMaster.lua");
                // e.printStackTrace();
            } catch (LuaError e) {
                System.out.println("TradeSkillMaster.lua 文件错误，请重新扫描");
            }
            String currentDirectory = System.getProperty("user.dir");
            backUp(filePath, currentDirectory + "/Tsmbackup");
            retMsg.append(modiTime(filePath)).append(" \n");
        }

        return retMsg.toString();


    }

    public List<QSale> in_QSela(String[] stringList, String Q, HashSet<String> hashSet) {
        List<QSale> T_List = new ArrayList<>();
        for (int i = 1; i < stringList.length; i++) {
            int k = 0;
            String hascode = String.valueOf(stringList[i].hashCode());
            if (!hashSet.contains(hascode)) {
                String[] values = stringList[i].split(",");
                QSale q = new QSale();
                if (values.length == 8) {
                    q.setItemNum(values[k++]);
                    q.setStackSize(values[k++]);
                    q.setQuantity(values[k++]);
                    q.setPrice(values[k++]);
                    q.setOtherPlayer(values[k++]);
                    q.setPlayer(values[k++]);
                    q.setTime(getTime(values[k++]));
                    q.setSource(values[k++]);
                    q.setHashCode(hascode);
                    q.setQ(Q);
                    T_List.add(q);
                }
            }
        }
        return T_List;
    }

    public List<QBuy> in_QBuy(String[] stringList, String Q, HashSet<String> hashSet) {
        List<QBuy> T_List = new ArrayList<>();
        for (int i = 1; i < stringList.length; i++) {
            int k = 0;
            String hascode = String.valueOf(stringList[i].hashCode());
            if (!hashSet.contains(hascode)) {
                String[] values = stringList[i].split(",");

                QBuy qBuy = new QBuy();
                if (values.length == 8) {
                    qBuy.setItemNum(values[k++]);
                    qBuy.setStackSize(values[k++]);
                    qBuy.setQuantity(values[k++]);
                    qBuy.setPrice(values[k++]);
                    qBuy.setOtherPlayer(values[k++]);
                    qBuy.setPlayer(values[k++]);
                    qBuy.setTime(getTime(values[k++]));
                    qBuy.setSource(values[k++]);
                    qBuy.setHashCode(hascode);
                    qBuy.setQ(Q);
                    T_List.add(qBuy);
                }
            }
        }

        return T_List;
    }


    public static HashSet<String> getProfile_List(LuaValue itemStrings) {
        HashSet<String> hs = new HashSet<>();
        if (itemStrings.istable()) {
            LuaTable TB = (LuaTable) itemStrings;
            for (LuaValue C_Name : TB.keys()) {
                String k = String.valueOf(itemStrings.get(C_Name));
                hs.add(k);
            }
        }
        return hs;
    }

    public static HashMap<String, String> getQ_N_List(LuaValue itemStrings) {
        HashMap<String, String> hm = new HashMap<>();
        if (itemStrings.istable()) {
            LuaTable TB = (LuaTable) itemStrings;
            for (LuaValue C_Name : TB.keys()) {
                String[] k = C_Name.tojstring().split("-");
                if (k.length == 2 && !hm.containsKey(k[1].trim())) {
                    hm.put(k[1].trim(), k[0].trim());
                } else {
                    hm.put(k[1].trim(), hm.get(k[1].trim()) + "," + k[0].trim());
                }
            }
        }
        return hm;
    }

    public BagHave setBagHave(String role, String itemCount, String itemNum, String Q, String ComeType) {
        BagHave bagHave = new BagHave();
        bagHave.setRole(role);
        bagHave.setItemCount(itemCount);
        bagHave.setItemNum(itemNum.replace("i:", ""));
        bagHave.setQ(Q);
        bagHave.setComeType(ComeType);
        return bagHave;
    }

    public List<BagHave> setAllBag(LuaValue tradeSkillMasterDB) {
        //背包 银行 仓库 全部存仪表
        List<BagHave> bagHaveList = new ArrayList<>();
        if (tradeSkillMasterDB.istable()) {
            LuaTable tsmDB = (LuaTable) tradeSkillMasterDB;
            LuaValue getBag = tsmDB.get("_currentProfile");
            HashMap<String, String> Q_P_List = getQ_N_List(getBag);
            Set<String> keys = Q_P_List.keySet();
            for (String q : keys) {
                String[] nameList = Q_P_List.get(q).split(",");
                for (String qy : zy) {
                    for (String name : nameList) {


                        LuaValue bagList = tsmDB.get("s@" + name + " - " + qy + " - " + q + "@internalData@bagQuantity");
                        HashMap<String, Integer> N_List = new HashMap<>();

                        if (bagList.istable()) {
                            LuaTable TB = (LuaTable) bagList;
                            for (LuaValue C_Name : TB.keys()) {
                                String itemName = C_Name.toString();
                                int itemC = Integer.parseInt(String.valueOf(TB.get(C_Name)));
                                if (N_List.containsKey(itemName)) {
                                    itemC = N_List.get(itemName) + itemC;
                                }
                                N_List.put(itemName, itemC);
                            }
                        }
                        //人物仓库
                        LuaValue bankList = tsmDB.get("s@" + name + " - " + qy + " - " + q + "@internalData@bankQuantity");
                        if (bankList.istable()) {

                            LuaTable TB = (LuaTable) bankList;
                            for (LuaValue C_Name : TB.keys()) {
                                String itemName = C_Name.toString();
                                int itemC = Integer.parseInt(String.valueOf(TB.get(C_Name)));
                                if (N_List.containsKey(itemName)) {
                                    itemC = N_List.get(itemName) + itemC;
                                }
                                N_List.put(itemName, itemC);
                            }
                        }
                        Set<String> nl = N_List.keySet();
                        for (String k : nl) {
                            String itemC = String.valueOf(N_List.get(k));
                            bagHaveList.add(setBagHave(name, itemC, k, q, "背包"));
                        }
                    }
                }
            }

            //工会信息插入
            for (String qy : zy) {
                for (String q : keys) {

                    LuaValue guildVaults = tsmDB.get("f@" + qy + " - " + q + "@internalData@guildVaults");
                    if (guildVaults.istable()) {
                        LuaTable TB = (LuaTable) guildVaults;
                        for (LuaValue C_Name : TB.keys()) {
                            String guild_name = C_Name.toString();

                            LuaValue guild_luaValue = TB.get(guild_name);
                            if (guild_luaValue.istable()) {
                                LuaTable guild_item_list = (LuaTable) guild_luaValue;
                                for (LuaValue d : guild_item_list.keys()) {
                                    String itName = d.toString();
                                    String itemC = String.valueOf(guild_item_list.get(d));
                                    bagHaveList.add(setBagHave(String.valueOf(guild_name), itemC, itName, q, "工会银行"));
                                }
                            }
                        }
                    }
                }
            }
        }
        return bagHaveList;
    }


    public List<FoceItem> getFoceList(LuaValue tradeSkillMasterDB) {
        List<FoceItem> itemStrEntityList = new ArrayList<>();
        LuaTable tsmDB = (LuaTable) tradeSkillMasterDB;
        LuaValue getBag = tsmDB.get("_currentProfile");
        HashSet<String> profileList = getProfile_List(getBag);
        for (String profile : profileList) {
            LuaTable tsmDB2 = (LuaTable) tradeSkillMasterDB;
            LuaValue itemStrings = tsmDB2.get("p@" + profile + "@userData@items");
            StringBuilder str = new StringBuilder();
            if (itemStrings.istable()) {
                LuaTable itemsTable = (LuaTable) itemStrings;
                for (LuaValue key : itemsTable.keys()) {
                    str.append(key);

                }
            }
            String[] splitList = str.toString().split("i:");
            for (String k : splitList) {
                if (!k.isEmpty()) {
                    FoceItem f = new FoceItem();
                    f.setItemNum(k);
                    f.setProfile(profile);
                    itemStrEntityList.add(f);
                }
            }
        }
        return itemStrEntityList;
    }


    public static String[] getItemNum(LuaValue itemStrings) {
        StringBuilder str = new StringBuilder();
        if (itemStrings.istable()) {
            LuaTable itemsTable = (LuaTable) itemStrings;
            for (int i = 1; i <= itemsTable.length(); i++) {
                str.append(itemsTable.get(i).tojstring()).append("\u0002");
            }
        }
        str = new StringBuilder(str.toString().replace("i:", ""));

        return str.toString().split("\u0002");
    }

    public List<DestroyingH> getdestroyingHList(LuaValue itemStrings) {
        List<DestroyingH> DestroyingHList = new ArrayList<>();
        if (itemStrings.istable()) {
            LuaTable TB = (LuaTable) itemStrings;
            HashSet<String> hasInDBList = destroyingHRepository.getAllHashCode();
            for (LuaValue desType : TB.keys()) {
                String desTypeS = desType.toString();
                LuaValue V1 = TB.get(desType);
                LuaTable T1 = (LuaTable) V1;
                for (LuaValue T1K : T1.keys()) {
                    LuaValue V2 = T1.get(T1K);
                    LuaTable T2 = (LuaTable) V2;
                    String targetItem = String.valueOf(V2.get("item")).replace("i:", "");
                    String time = getTime(String.valueOf(V2.get("time")));
                    String hasNeed = String.valueOf((desTypeS + time + targetItem).hashCode());
                    if (hasInDBList.contains(hasNeed)) {
                        continue;
                    }
                    for (LuaValue T2K : T2.keys()) {
                        if (Objects.equals(String.valueOf(T2K), "result")) {
                            LuaValue V3 = T2.get(T2K);
                            LuaTable T3 = (LuaTable) V3;
                            for (LuaValue backItem : T3.keys()) {
                                String backItemS = String.valueOf(backItem).replace("i:", "");
                                String backItemCount = String.valueOf(T3.get(backItem));
                                DestroyingH destroyingH = new DestroyingH();
                                destroyingH.setDestroyType(desTypeS);
                                destroyingH.setTime(time);
                                destroyingH.setDestroyTargetItemNum(targetItem);
                                destroyingH.setBackItemNum(backItemS);
                                destroyingH.setBackItemCount(backItemCount);
                                destroyingH.setHashCode(hasNeed);
                                destroyingH.setBatchNum(hasNeed + "b");
                                DestroyingHList.add(destroyingH);
                            }
                        }
                    }
                }
            }
        }
        return DestroyingHList;
    }

    public List<ItemC> getCList(LuaValue itemStrings, String Q) {
        List<ItemC> itemCSList = new ArrayList<>();
        if (itemStrings.istable()) {
            LuaTable TB = (LuaTable) itemStrings;
            HashSet<String> itemInDBList = itemCReposit2ory.getHaveItemInDBList(Q);
            for (LuaValue C_Name : TB.keys()) {
                LuaValue itemBox = TB.get(C_Name);
                String itemNum = String.valueOf(itemBox.get("itemString")).replace("i:", "");
                String profession = String.valueOf(itemBox.get("profession"));
                String name = String.valueOf(itemBox.get("name"));
                if (!itemInDBList.contains(itemNum)) {
                    LuaTable CuseItem = (LuaTable) itemBox.get("mats");
                    for (LuaValue k : CuseItem.keys()) {
                        LuaValue neetCount = CuseItem.get(k);//需要的材料 及其数量
                        ItemC itemCS = new ItemC();
                        itemCS.setItemNum(itemNum);
                        itemCS.setItemNeed(k.tojstring().replace("i:", ""));
                        itemCS.setItemNeedCount(String.valueOf(neetCount));
                        itemCS.setQ(Q);
                        itemCS.setCtype(profession);
                        itemCS.setItemString(name);
                        itemCSList.add(itemCS);
                    }
                    System.out.println("新增制作" + itemNum);
                }
            }
        }
        return itemCSList;
    }

    public List<ItemStrEntity> update_item(String[] keyList, String[] strList) {
        List<ItemStrEntity> itemStrEntityList = new ArrayList<>();


        if (keyList.length == strList.length) {
            HashSet<String> haveCount = itemRepository.ItemNumListInDB();

            for (int i = 0; i < keyList.length; i++) {
                if (!haveCount.contains(keyList[i])) {
                    ItemStrEntity itemStrEntity = new ItemStrEntity();
                    itemStrEntity.setItemString(strList[i].trim());
                    itemStrEntity.setItemNum(keyList[i]);
                    itemStrEntityList.add(itemStrEntity);
                    //  System.out.println("xz" + strList[i].trim());
                }
            }

        }
        return itemStrEntityList;
    }


    public List<TsmScan> in_TsmScan(String[] stringList, String Q, int isLast, String time, HashSet<String> trashList) {
        //更新最后的标识
        if (isLast > 0) {
            tsmScanRepository.update_Last(Q);
        }
        List<TsmScan> tsmscanList = new ArrayList<>();
        for (int i = 1; i < stringList.length; i++) {

            String[] values = stringList[i].split(",");
            TsmScan tsmscan = new TsmScan();
            if (values.length == 6) {
                String itemNum = values[0].trim();
                String min = values[1];
                String hi = values[2];
                if (trashList.contains(itemNum)) {
                    min = "0";
                    hi = "10000";
                }
                tsmscan.setItemNum(values[0]);
                tsmscan.setMinBuyout(min);
                tsmscan.setMarketValue(hi);
                tsmscan.setNumAuctions(values[3]);
                tsmscan.setQuantity(values[4]);
                tsmscan.setLastScan(time);
                tsmscan.setIsLast(isLast);
                tsmscan.setQ(Q);
                tsmscanList.add(tsmscan);
            }
        }

        return tsmscanList;
    }


    private String getTime(String time) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(time)), ZoneId.systemDefault());
        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 格式化日期时间
        return dateTime.format(formatter);
    }

    private String modiTime(String filePath) {
        File file = new File(filePath);

        if (file.exists() && file.isFile()) {
            // 获取文件的最后修改时间
            long lastModified = file.lastModified();
            // 格式化日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date lastModifiedDate = new Date(lastModified);

            // 输出文件的修改日期
            return ("文件最后修改日期是：" + sdf.format(lastModifiedDate));
        } else {
            return ("未找到文件");
        }
    }

    private void backUp(String Old, String NewPath) {
        // 源文件路径
        Path sourcePath = Paths.get(Old);

        // 目标文件夹路径
        Path targetDirectory = Paths.get(NewPath);

        // 检查目标文件夹是否存在，如果不存在则创建
        if (!Files.exists(targetDirectory)) {
            try {
                Files.createDirectories(targetDirectory);
            } catch (IOException e) {
                System.err.println("创建文件夹失败： " + e.getMessage());
                return;
            }
        }
        // 目标文件路径
        Path targetPath = targetDirectory.resolve(sourcePath.getFileName());

        try {
            // 拷贝文件
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("备份文件至" + targetPath);

            // 移动文件到自己文件夹
            Path newTargetPath = targetDirectory.resolve(getNow() + sourcePath.getFileName());
            Files.move(targetPath, newTargetPath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            System.err.println("文件备份失败: " + e.getMessage());
        }
    }

    public String getNow() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 定义格式化器，格式化到秒
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

        // 格式化当前时间
        return now.format(formatter);

    }
}
