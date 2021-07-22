package com.xzc.util;

import com.alibaba.fastjson.JSON;
import com.xzc.util.copybean.ClassTest;
import com.xzc.util.copybean.People;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 目前流行的较为公用认可的工具类：
 * Apache的两个版本：（反射机制）
 * <p>
 * org.apache.commons.beanutils.PropertyUtils.copyProperties(Object dest, Object orig)
 * <p>
 * org.apache.commons.beanutils.BeanUtils.copyProperties(Object dest, Object orig)
 * <p>
 * Spring版本：（反射机制）
 * <p>
 * org.springframework.beans.BeanUtils.copyProperties(Object source, Object target, Class editable, String[] ignoreProperties)
 * <p>
 * 新版本的spring也集成了cglib版：org.springframework.cglib.beans.BeanCopier
 * 其性能要比Spring的BeanUtils，Apache的BeanUtils和PropertyUtils要好很多，尤其是数据量比较大的情况下。
 * <p>
 * cglib版本：（使用动态代理，效率高）
 * <p>
 * net.sf.cglib.beans.BeanCopier.copy(Object paramObject1, Object paramObject2, Converter paramConverter)
 * <p>
 * dozer版本（xml配置映射，性能最低下）：
 * <p>
 * org.dozer.DozerBeanMapper.map(Object source, Class<T> destinationClass)
 * 就API而言，使用哪个工具类无所谓，我们通常关心两点，功能完善性以及性能。
 *
 * @Author zhichao.xing
 * @Date 2021/7/22 10:38
 * @Version 1.0
 **/
public class CopyBeanUtil {

    public void test() {

    }

    /**
     * @return void
     * @Description BeanUtils.copyProperties
     * spring有BeanUtils
     * apache的commons也有BeanUtils。
     * @Author xzc
     * @Date 11:08 2021/7/22
     **/
    public static void main(String[] args) {
        // 测试数组的复制
        People[] member = new People[3];
        member[0] = new People(1, "老师", 30, 1);
        member[1] = new People(2, "班长", 15, 1);
        member[2] = new People(3, "学生", 15, 1);
        People[] member1 = new People[]{};
        BeanUtils.copyProperties(member, member1);
        System.out.println("是否可以复制数组：" + (member1.length == 0 ? false : true));
        // 测试List的复制（Map也不能复制，测试略）
        List<People> student = new ArrayList<>();
        student.add(member[1]);
        student.add(member[2]);
        List<People> student1 = new ArrayList<>();
        BeanUtils.copyProperties(student, student1);
        System.out.println("BeanUtils.copyProperties是否可以复制List：" + (student1.isEmpty() ? false : true));
        // 通过JSON工具实现List的复制（不仅仅是List，数组和Map等也可以通过类似方法实现复制，需要有无参构造方法，否则报错）
        student1 = JSON.parseArray(JSON.toJSONString(student), People.class);
        System.out.println("通过JSON工具复制List：" + student1);
        System.out.println("通过JSON工具是否深复制：" + (student.get(0) != student1.get(0) ? true : false));
        // 测试是否深复制
        ClassTest source = new ClassTest();
        source.setMember(member);
        source.setTeacher(member[0]);
        source.setStudent(student);
        ClassTest target = new ClassTest();
        BeanUtils.copyProperties(source, target);
        System.out.println("BeanUtils.copyProperties是否深复制：" + (source.getMember() != target.getMember() ? true : false));
    }


    /**
     * @return java.util.List
     * @Description 通过以下方式解决复制List
     * @Author xzc
     * @Date 10:42 2021/7/22
     **/
    public static <T> List copyList(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList();
        }
        return JSON.parseArray(JSON.toJSONString(list), list.get(0).getClass());
    }

    /**
     * @return java.util.List
     * @Description 通过以下方式解决复制Map
     * @Author xzc
     * @Date 10:42 2021/7/22
     **/
    public static Map<String, Object> copyMap(Map map) {
        return JSON.parseObject(JSON.toJSONString(map));
    }
}
