package org.example;

import java.io.IOException;

/**
 * @program: javaAgentDemo
 * @description:
 * @author: potats0
 * @create: 2021-10-14 15:38
 **/
public class main {
    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec("whoami");
    }
}
