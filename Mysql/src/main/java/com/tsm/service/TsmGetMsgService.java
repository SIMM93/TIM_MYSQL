package com.tsm.service;

import java.io.IOException;
import java.util.HashSet;

public interface TsmGetMsgService {

    String getDBMSg(String filePath, String[] Qlist, HashSet<String> trash_item);

    void runtime() throws IOException;


}
