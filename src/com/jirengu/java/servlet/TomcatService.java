package com.jirengu.java.servlet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

/***
 * web服务器 开发团队
 */
public class TomcatService {
    private static HashMap<String,Servlet>  servletMap = new HashMap<>();
    public static void main(String[] args) throws Exception {
        while(true){
            //接受用户请求，接受请求路径调用对应的servlet来完成业务逻辑
            //1.接受用户的请求
            String url = getUserInputUrl();
            //2.根据请求路径找到对应的servlet
            Servlet servlet = getServlet(url);
            if(servlet ==null) {
                System.out.println("输入的rul有误，请重新输入");
                continue;
            }
            //3.调用Servlet,执行业务逻辑
            servlet.service();
        }

    }

    private static String getUserInputUrl(){
        Scanner s = new Scanner(System.in);
        String url = s.nextLine();
        return url;
    };

    private static Servlet getServlet(String url) throws Exception {
        if(servletMap.containsKey(url)){
            System.out.println("不需要创建对象");
            return servletMap.get(url);
        }
        // 解析配置文件
        FileReader reader = new FileReader("/Users/a0110895/Desktop/java/untitled/src/com/jirengu/java/servlet/web.properties");
        Properties properties = new Properties();
        properties.load(reader);
        reader.close();
        String className = properties.getProperty(url);
        if(className == null) {
            return null;
        }
        System.out.println("创建对象");
        Class clazz = Class.forName(className);
        Object obj = clazz.newInstance();
        Servlet servlet = (Servlet) obj;
        servletMap.put(url,servlet);
        return (Servlet) obj;
    }

}
