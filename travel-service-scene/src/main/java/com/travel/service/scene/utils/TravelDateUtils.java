package com.travel.service.scene.utils;

import com.alibaba.druid.support.json.JSONUtils;
import com.travel.service.scene.entity.TravelScene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;


public class TravelDateUtils {

    private static final Logger logger = LoggerFactory.getLogger(TravelDateUtils.class);



    //解析文件
    public static List<TravelScene> readFile(int fileDir,int pageNum, String city) {
        List<TravelScene> lists = new ArrayList<>();
   //     while (fileDir <= 164) {
            int fileName = 1;
            while (fileName <= pageNum) {
                StringBuilder str = new StringBuilder();
                File file = new File("D://WorkFile/PycharmProjects/travel/venv/Include/travel/" + fileDir + "/" + fileName + ".txt");
                try {
                    InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
                    BufferedReader br = new BufferedReader(reader);
                    String line = br.readLine();
                    while (line != null) {
                        str.append(line);
                        line = br.readLine();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String data = str.toString();
                List<TravelScene> travelScenes = dataToObject(data,city);
                lists.addAll(travelScenes);
                fileName++;
            }
            fileDir++;

//        }
          System.out.println("共存储" + lists.size() + "条数据");
          return lists;
    }

    //将字符串转化为对象类型
    public static List<TravelScene> dataToObject(String str,String city) {
        List<TravelScene> resoults = new ArrayList<>();
        LinkedHashMap parse = (LinkedHashMap) JSONUtils.parse(str);
        LinkedHashMap data = (LinkedHashMap) parse.get("data");
        ArrayList list = (ArrayList) data.get("list");
        for (int i = 0; i < list.size(); i++) {
            TravelScene travelScene = new TravelScene();
            LinkedHashMap map = (LinkedHashMap) list.get(i);
            travelScene.setId(Integer.parseInt((String) map.get("id")));
            travelScene.setCatename((String) map.get("catename"));
            travelScene.setCity(city);
            travelScene.setCnname((String) map.get("cnname"));
            travelScene.setCommentCount(0);
            travelScene.setGrade(Double.parseDouble((String)map.get("grade")));
            if(map.get("hotgrade")!=null){
                travelScene.setHotgrade(Integer.parseInt((String) map.get("hotgrade")));
            }
            if(map.get("photo")!=null){
                String photo =  (String) map.get("photo");
                UUID uuid = UUID.randomUUID();
                String filePath = "D://images/"+ uuid +".png ";
                String photoUrl = "http://192.168.219.130:8888/group1/M00/00/00/"+ uuid +".png ";
                boolean b = ImageUtils.saveToFile(photo, filePath);
                if(b){
                    travelScene.setPhoto(photoUrl);
                }
            }

            logger.info("解析成功" + travelScene.toString());
            resoults.add(travelScene);
        }
        return resoults;
    }
}
