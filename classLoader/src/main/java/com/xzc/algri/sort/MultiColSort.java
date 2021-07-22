package com.xzc.algri.sort;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName MultiColSort
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/7/20 19:22
 * @Version 1.0
 **/
public class MultiColSort {
    public static void main(String[] args) {
        // 姓名，性别，年龄，薪资，级别，籍贯
        List<Person> lists = Lists.newArrayList();
        Person person = new Person("张三", "男", 22, 10000, "T2", "贵州遵义");
        lists.add(person);
        person = new Person("李四", "女", 23, 11000, "T2", "贵州遵义");
        lists.add(person);
        person = new Person("王五", "女", 23, 12000, "T3", "北京海淀");
        lists.add(person);
        person = new Person("王六", "男", 23, 13000, "T3", "北京昌平");
        lists.add(person);
        person = new Person("王七", "男", 24, 14000, "T3", "北京昌平");
        lists.add(person);
        person = new Person("王八", "女", 23, 12000, "T2", "北京昌平");
        lists.add(person);
        person = new Person("胡三", "男", 26, 12000, "T3", "北京朝阳");
        lists.add(person);
        person = new Person("胡四", "男", 26, 13000, "T3", "北京朝阳");
        lists.add(person);
        person = new Person("张五", "女", 26, 14000, "T3", "北京海淀");
        lists.add(person);
        person = new Person("张六", "男", 27, 17000, "T4", "北京朝阳");
        lists.add(person);
        person = new Person("张七", "男", 23, 12000, "T3", "北京朝阳");
        lists.add(person);
        person = new Person("黄五", "女", 24, 11000, "T2", "北京海淀");
        lists.add(person);
        person = new Person("黄三", "男", 24, 10000, "T2", "北京大兴");
        lists.add(person);
        person = new Person("刘爱", "男", 22, 9000, "T1", "北京大兴");
        lists.add(person);
        person = new Person("刘跟", "男", 27, 18000, "T4", "贵州遵义");
        lists.add(person);
        person = new Person("李根", "男", 28, 20000, "T5", "贵州贵阳");
        lists.add(person);
        person = new Person("郭艾琳", "男", 24, 12000, "T3", "贵州贵阳");
        lists.add(person);
//        lists = lists.stream().sorted(Comparator.comparing(Person::getAge, Comparator.reverseOrder())
//                .thenComparing(Person::getSalay)).collect(Collectors.toList());


        lists = lists.stream().sorted(Comparator.comparing(Person::getAge).reversed()
                .thenComparing(Person::getSalay)).collect(Collectors.toList());

        lists.sort((o1, o2) -> ComparisonChain.start()
                .compare(o1.getAge(), o2.getAge())
                .compare(o1.getSalay(), o2.getSalay(), Ordering.natural().reverse())
                .compare(o1.getLevel(), o2.getLevel(), Ordering.natural().nullsLast()).result());


        /**
         * @Description 输出结果
         **/
        lists.forEach(System.out::println);

//        Map<String, Boolean> colSortMaps = Maps.newTreeMap();
//        colSortMaps.put("1-age", true); // 年龄升序
//        colSortMaps.put("2-salary", true); // 薪资升序
//        colSortMaps.put("3-level", true); // 级别升序
//        lists.sort(listComparator(colSortMaps));
    }


    private static Comparator<Person> listComparator(Map<String, Boolean> colSortMaps) {
        Ordering ordering = Ordering.natural();
        return (p1, p2) -> {
//可以使用到了Guava的ComparisonChain，ComparisonChain是一个链式排序逻辑，把需要排序的列先后往ComparisonChain中添加即可
            ComparisonChain compareChain = ComparisonChain.start();
            for (String index : colSortMaps.keySet()) {
                if (index.equals("1-age")) {
                    Object currObj = Optional.ofNullable(p1.getAge()).orElse(0);
                    Object compObj = Optional.ofNullable(p2.getAge()).orElse(0);
                    Comparator<Object> objComparator = objComparator(ordering, colSortMaps.getOrDefault(index, true));
                    compareChain = compareChain.compare(currObj, compObj, objComparator);
                }
                if (index.equals("2-salary")) {
                    Object currObj = Optional.ofNullable(p1.getSalay()).orElse(0);
                    Object compObj = Optional.ofNullable(p2.getSalay()).orElse(0);
                    Comparator<Object> objComparator = objComparator(ordering, colSortMaps.getOrDefault(index, true));
                    compareChain = compareChain.compare(currObj, compObj, objComparator);
                }
                if (index.equals("3-level")) {
                    Object currObj = Optional.ofNullable(p1.getLevel()).orElse("");
                    Object compObj = Optional.ofNullable(p2.getLevel()).orElse("");
                    Comparator<Object> objComparator = objComparator(ordering, colSortMaps.getOrDefault(index, true));
                    compareChain = compareChain.compare(currObj, compObj, objComparator);
                }
            }
            return compareChain.result();
        };
    }

    private static Comparator<Object> objComparator(Ordering ordering, boolean asc) {
        if (asc) {
            return ordering::compare;
        } else {
            return ordering.reverse()::compare;
        }
    }
}
