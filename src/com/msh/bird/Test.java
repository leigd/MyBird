package com.msh.bird;

/**
 * @author: lgd
 * @date: 2021/09/29/ 18:23
 */
public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            try {
                try {
                    if (i%3==0){
                        throw  new Exception("E0");
                    }
                    System.out.println(i);
                }catch (Exception e){
                    i*=2;
                    if (i%3==0){
                        throw new Exception("E1");
                    }
                }finally {
                    ++i;
                }
            }catch (Exception e){
                i+=3;
            }finally {
                --i;
            }

        }
    }
}
