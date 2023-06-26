package com.jirengu.java.servlet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

/***
 * web服务器 开发团队
 */
public class TomcatService {
    public static void main(String[] args) throws Exception {
        //接受用户请求，接受请求路径调用对应的servlet来完成业务逻辑
        //1.接受用户的请求
        String url = getUserInputUrl();
        //2.根据请求路径找到对应的servlet
        Servlet servlet = getServlet(url);
        //3.调用Servlet,执行业务逻辑
        servlet.service();
    }

    private static String getUserInputUrl(){
        Scanner s = new Scanner(System.in);
        String url = s.nextLine();
        System.out.println(url);
        return url;
    };

    private static Servlet getServlet(String url) throws Exception {
        // 解析配置文件
        FileReader reader = new FileReader("/Users/a0110895/Desktop/java/untitled/src/com/jirengu/java/servlet/web.properties");
        Properties properties = new Properties();
        properties.load(reader);
        reader.close();
        String className = properties.getProperty(url);
        Class clazz = Class.forName(className);
        Object obj = clazz.newInstance();
        return (Servlet) obj;
    }

}
