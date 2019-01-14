package com.hosmos.learning.lambda;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleDemo {

    public static void simpleTest() {
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer", "Roger Federer",
                "Andy Murray", "Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players = Arrays.asList(atp);
        //正常循环方式
        for (String player : players) {
            System.out.println(player + "; ");
        }
        //使用 lambda 表达式
        //函数操作(functional operation)
        players.forEach((player) -> System.out.print(player + "; "));
        //使用双冒号操作符(double colon operator)
        players.forEach(System.out::println);

    }

    public static void threadTest() {
        //创建线程
        Thread td = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello,world!");
            }
        });
        td.start();
        //lambda写法
        Thread td1 = new Thread(() -> System.out.println("1234"));
        td1.start();
        /*Thread td0 = new Thread((name) -> System.out.println(name)); //Cannot resolve method 'println(<lambda parameter>)'
        td0.start();*/
    }

    public static void listTest() {
        /**
         * 将字符列表按照字母表排序
         */
        List<String> list = Arrays.asList(new String[]{"c", "d", "r", "n", "f", "a"});
        //匿名类
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return str1.compareTo(str2);
            }
        });
        list.forEach((string) -> System.out.print(string + ";"));
        //lambda写法
        List<String> lists = Arrays.asList(new String[]{"c", "d", "r", "n", "f", "a"});
        Collections.sort(lists, (str1, str2) -> str1.compareTo(str2));
        lists.forEach((string) -> System.out.print(string + ";"));
        /**
         * 将字符串列表中的字符串大写转换成小写
         */
        List<String> proNames = Arrays.asList(new String[]{"Ni", "Hao", "Lambda"});
        //普通写法
        List<String> lowercaseName = new ArrayList<String>();
        for (String str : proNames) {
            lowercaseName.add(str.toLowerCase());
        }
        //lambda写法
        List<String> lowercaseName1 = proNames.stream().map(name -> {
            return name.toLowerCase();
        }).collect(Collectors.toList());
        //或者(单语句写法)
        List<String> lowercaseName2 = proNames.stream().map(name -> name.toLowerCase()).collect(Collectors.toList());
        //或者(方法引用写法)
        List<String> lowercaseName3 = proNames.stream().map(String::toLowerCase).collect(Collectors.toList());
        lowercaseName1.forEach((name) -> System.out.print(name));
    }

    public static void paramTest() {
        /**
         * outsideParam 外部变量
         * middleParam 传递变量
         * insertParam 内部自定义变量
         */
        String outsideParam = "lambda :";
        List<String> proStrs = Arrays.asList(new String[]{"Ni", "Hao", "Ya"});
        List<String> execStrs = proStrs.stream().map(middleParam -> {
            //outsideParam = outsideParam + " ";// lambda无法改变外部变量
            Long insertParam = System.currentTimeMillis();
            return outsideParam + "<-->" + middleParam + "<-->" + insertParam;
        }).collect(Collectors.toList());
        execStrs.forEach(message -> System.out.println(message));
    }

    public static void streamTest() {
        Stream<String> stringStream = Stream.of("abc");
        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        Stream<Double> doubleStream = Stream.generate(new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        }).limit(10);
        Stream<Double> doubleStream1 = Stream.generate(() -> Math.random()).limit(10);
        Stream<Double> doubleStream2 = Stream.generate(Math::random).limit(10);
        Stream<Integer> integerStream1 = Stream.iterate(1, item -> item + 1).limit(10);
        integerStream1.forEach(a -> System.out.println(a));
    }

    public static void translationTest() {
        List<String> list = creatArrayIntList("a", "b", "c", null, "d", "e", "f", "g", null, "h", "i");
        Stream<String> stringStream = list.stream();
        System.out.print("All(全部输出)：\t\t");
        stringStream.forEach(a -> System.out.print(a + ";"));
        System.out.print("\nFilter(过滤)：\t\t");
        list.stream().filter(a -> null != a).forEach(a -> System.out.print(a + ";"));
        System.out.print("\nLimit(限制个数)：\t");
        list.stream().limit(6).forEach(a -> System.out.print(a + ";"));
        System.out.print("\nSkip(跳过个数)：\t\t");
        list.stream().skip(4).forEach(a -> System.out.print(a + ";"));
        System.out.print("\nDistinct(去重)：\t\t");
        list.stream().distinct().forEach(a -> System.out.print(a + ";"));
        System.out.print("\nPeek(重新生成)：\t\t");
        //collection.stream().peek(a -> System.out.print(a + ";"));//这样不会有输出
        list.stream().peek(a -> {
            a += a;
            System.out.print(a + ";");
        }).collect(Collectors.toList());
        System.out.print("\nMap(重新生成)：\t\t");
        list.stream().filter(a -> null != a).map(String::toUpperCase).forEach(a -> System.out.print(a + ";"));
        System.out.print("\n整体调用：\t\t\t");
        List<Integer> nums = creatArrayIntList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        System.out.println("sum is:" + nums.stream().filter(num -> num != null).distinct().mapToInt(num -> num * 2).peek(System.out::print).skip(2).limit(4).sum());
    }

    public static void reduceStreamTest() {
        List<Integer> nums = creatArrayIntList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        List<Integer> numsWithoutNull = nums.stream().filter(num -> num != null).peek(System.out::print).collect(() -> new ArrayList<Integer>(), (list, item) -> list.add(item), (list1, list2) -> list1.addAll(list2));
        List<Integer> numsWithoutNull1 = nums.stream().filter(num -> num != null).collect(Collectors.toList());
        List<Integer> ints = creatArrayIntList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.print("\nReduce：\n");
        //求和
        System.out.println("ints sum is:" + ints.stream().reduce((sum, item) -> sum + item).get());
        //求和
        System.out.println("ints sum is:" + ints.stream().reduce(0, (sum, item) -> sum + item));
        //计数
        System.out.println("ints count is:" + ints.stream().count());
        //是否所有元素都满足条件
        System.out.println(ints.stream().allMatch(num -> num%2 == 0));
        //是否存在某一个元素满足条件
        System.out.println(ints.stream().anyMatch(num -> num%2 == 0));
        //是否所有元素都不满足条件
        System.out.println(ints.stream().noneMatch(num -> num%2 == 0));
        //返回Stream中的第一个元素，如果Stream为空，返回空Optional
        System.out.println(ints.stream().findFirst());
        //返回Stream中的第一个元素，如果Stream为空，返回空Optional
        System.out.println(ints.stream().findFirst().get());
        ints.stream().max((o1, o2) -> o1.compareTo(o2)).ifPresent(System.out::println);
        System.out.println(ints.stream().min((o1, o2) -> o1.compareTo(o2)).get());
    }

    public static List creatArrayIntList(Object... key) {
        try {
            if (Test.isEmpty(key)) {
                return null;
            }
            List nums = Arrays.asList(key);
            return nums;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        reduceStreamTest();
    }
}